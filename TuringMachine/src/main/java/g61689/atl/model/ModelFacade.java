package g61689.atl.model;

import util.Command;
import util.CommandManager;
import util.Observable;
import util.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Facade for the game model, providing a simplified interface to the game logic.
 */
public class ModelFacade implements Observable {
    private final Model model;
    private final CommandManager commandManager;
    private final List<Observer> observers = new ArrayList<>();

    /**
     * Constructor initializing the ModelFacade with a new game model and command manager.
     */
    public ModelFacade() {
        this.model = new Model();
        commandManager = new CommandManager();
    }

    /**
     * Starts a new game with the selected problem.
     *
     * @param selectedProblemIndex Index of the selected problem.
     */
    public void startGame(int selectedProblemIndex) {
        model.startGame(selectedProblemIndex);
        notifyObservers();
    }

    /**
     * Retrieves a list of validator numbers for the current problem.
     *
     * @return A list of lists containing validator numbers.
     */
    public List<List<Integer>> getValidatorNumbers() {
        return model.getValidatorNumbers();
    }

    /**
     * Enters a code chosen by the user and updates the game state.
     *
     * @param userChoice The code entered by the user.
     */
    public void enterCode(int userChoice) {
        model.enterCode(userChoice);
        notifyObservers();
    }

    /**
     * Checks if a validator can be applied in the current state.
     *
     * @return true if a validator can be applied, false otherwise.
     */
    public boolean canApplyValidator() {
        return model.canApplyValidator();
    }

    /**
     * Chooses a validator based on the given index and applies it.
     *
     * @param chosenValidatorIndex The index of the chosen validator.
     */
    public void chooseValidator(int chosenValidatorIndex) {
        Command command = new TestValidatorCommand(chosenValidatorIndex, model);
        commandManager.newCommand(command);
        notifyObservers();
    }

    /**
     * Moves the game to the next round.
     */
    public void moveToNextRound() {
        Command command = new NextRoundCommand(model, model.getValidatorsTestedMap(), getAvailableValidators());
        commandManager.newCommand(command);
        notifyObservers();
    }

    /**
     * Retrieves a list of available problems.
     *
     * @return A list of problems that can be selected for the game.
     */
    public List<Problem> getAvailableProblems() {
        return model.getAvailableProblems();
    }

    /**
     * Determines if the game has finished.
     *
     * @return true if the game is finished, false otherwise.
     */
    public boolean isGameFinished() {
        return model.isGameFinished();
    }

    /**
     * Retrieves the result of the last user action.
     *
     * @return true if the user's last action was successful, false otherwise.
     */
    public boolean getUserResult() {
        return model.getUserResult();
    }

    /**
     * Retrieves the current score of the game.
     *
     * @return The current score.
     */
    public int getScore() {
        return model.getScore();
    }

    /**
     * Retrieves the number of validators tested in the current round.
     *
     * @return The number of validators tested.
     */
    public int getNumberValidatorsTested() {
        return model.getValidatorsTestedMap().size();
    }

    /**
     * Retrieves a map of validators that have been tested.
     *
     * @return A map indicating which validators have been tested and their outcomes.
     */
    public Map<Integer, Boolean> getValidatorsTestedMap() {
        return model.getValidatorsTestedMap();
    }

    /**
     * Retrieves the number of rounds played so far in the game.
     *
     * @return The current round number.
     */
    public int getRoundsPlayed() {
        return model.getRoundsPlayed();
    }

    /**
     * Retrieves the user code that is currently set.
     *
     * @return The current user code.
     */
    public int getUserCode() {
        return model.getUserCode();
    }

    /**
     * Retrieves the current guess code.
     *
     * @return The current guess code.
     */
    public int getGuessCode() {
        return model.getGuessCode();
    }

    /**
     * Retrieves a list of available validators.
     *
     * @return A list of validators that are currently available.
     */
    public List<Validator> getAvailableValidators() {
        return model.getAvailableValidators();
    }

    /**
     * Sets the user code to a specified value.
     *
     * @param userCode The user code to set.
     */
    public void setUserCode(int userCode) {
        Command command = new SetCodeCommand(userCode, model);
        commandManager.newCommand(command);
        notifyObservers();
    }

    /**
     * Checks if the user code is set.
     *
     * @return true if the user code is set, false otherwise.
     */
    public boolean isUserCodeSet() {
        return model.isUserCodeSet();
    }

    /**
     * Checks if the guess code is set.
     *
     * @return true if the guess code is set, false otherwise.
     */
    public boolean isGuessCodeSet() {
        return model.isGuessCodeSet();
    }

    /**
     * Marks the game as finished.
     */
    public void finishGame() {
        model.finishGame();
        notifyObservers();
    }

    /**
     * Resets the game to its initial state.
     */
    public void resetGame() {
        model.reset();

        commandManager.clearCommands();

        notifyObservers();
    }

    /**
     * Undoes the command
     */
    public void undo() {
        commandManager.undo();
        notifyObservers();
    }

    /**
     * Redoes the command
     */
    public void redo() {
        commandManager.redo();
        notifyObservers();
    }

    @Override
    public boolean register(Observer obs) {
        if (!observers.contains(obs)) {
            observers.add(obs);
            return true;
        }
        return false;
    }

    @Override
    public boolean unregister(Observer obs) {
        if (observers.contains(obs)) {
            observers.remove(obs);
            return true;
        }
        return false;
    }

    /**
     * Notifies all registered observers of changes in the model state.
     */
    private void notifyObservers() {
        for (Observer obs : observers) {
            obs.update(this);
        }
    }
}