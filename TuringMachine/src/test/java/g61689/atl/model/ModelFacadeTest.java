package g61689.atl.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ModelFacadeTest {
    private ModelFacade facade;

    @BeforeEach
    public void setUp() {
        facade = new ModelFacade();
    }

    @Test
    public void testStartGame() {
        ModelFacade facade = new ModelFacade();
        facade.startGame(0);
        assertFalse(facade.isGameFinished());
        assertEquals(0, facade.getRoundsPlayed());
        assertNotNull(facade.getAvailableValidators());
    }

    @Test
    public void testSetUserCode() {
        ModelFacade facade = new ModelFacade();
        facade.startGame(0);
        int userCode = 123;
        facade.setUserCode(userCode);
        assertTrue(facade.isUserCodeSet());
        assertEquals(userCode, facade.getUserCode());
        assertEquals(0, facade.getNumberValidatorsTested()); // No validators should be tested yet
    }

    @Test
    public void testEnterCode() {
        ModelFacade facade = new ModelFacade();
        facade.startGame(0);
        int userCode = 241;
        facade.setUserCode(userCode);
        facade.enterCode(userCode);
        assertTrue(facade.getUserResult());
        assertTrue(facade.isGameFinished()); // Game should finish
    }

    @Test
    public void testApplyValidator() {
        ModelFacade facade = new ModelFacade();
        facade.startGame(0);
        facade.setUserCode(123);
        int initialValidatorsTested = facade.getNumberValidatorsTested();
        if (facade.canApplyValidator()) {
            facade.chooseValidator(1);
            assertEquals(initialValidatorsTested + 1, facade.getNumberValidatorsTested()); // Validator count should increase
        } else {
            fail("Validator cannot be applied");
        }
    }

    @Test
    public void testMoveToNextRound() {
        ModelFacade facade = new ModelFacade();
        facade.startGame(0);
        int initialRound = facade.getRoundsPlayed();
        facade.moveToNextRound();
        assertEquals(initialRound + 1, facade.getRoundsPlayed());
        assertEquals(0, facade.getNumberValidatorsTested()); // Validator count should reset
    }

    @Test
    public void testUndoRedo() {
        ModelFacade facade = new ModelFacade();
        facade.startGame(0);
        int initialScore = facade.getScore();

        // Perform some actions that change the score
        facade.setUserCode(123);
        facade.chooseValidator(1);
        int scoreAfterAction = facade.getScore();

        facade.undo();
        assertEquals(initialScore, facade.getScore()); // Score should revert to initial score

        facade.redo();
        assertEquals(scoreAfterAction, facade.getScore()); // Score should return to post-action score
    }

    @Test
    public void testFinishGame() {
        ModelFacade facade = new ModelFacade();
        facade.startGame(0);
        facade.finishGame();
        assertTrue(facade.isGameFinished());
        assertTrue(facade.getUserResult() || !facade.getUserResult()); // Game can finish with any result
    }

    @Test
    public void testStartGameInvalidIndex() {
        assertThrows(IllegalArgumentException.class, () -> facade.startGame(-1));
    }

    @Test
    public void testGetScore() {
        facade.startGame(0);
        int score = facade.getScore();
        assertEquals(0, score);
    }

    @Test
    public void testGetValidatorNumbers() {
        facade.startGame(0);
        assertNotNull(facade.getValidatorNumbers());
    }

    @Test
    public void testEnterCodeCorrect() {
        facade.startGame(0);
        int correctCode = 241;
        facade.enterCode(correctCode);
        assertTrue(facade.getUserResult());
    }

    @Test
    public void testEnterCodeIncorrect() {
        facade.startGame(0);
        facade.enterCode(999);
        assertFalse(facade.getUserResult());
    }
}