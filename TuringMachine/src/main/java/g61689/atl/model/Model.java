package g61689.atl.model;

import g61689.atl.view.ConsoleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the game model, handling game logic, problem management, and game state.
 */
public class Model {
    private List<Problem> problems;
    private Problem currentProblem;
    private int currentRound;
    private boolean gameFinished;
    private final List<Validator> availableValidators;
    private int userCode;
    private int guessCode;
    private int score;
    private Map<Integer, String> roundValidators;
    private Map<Integer, Boolean> validatorsTestedMap;
    private boolean userWon;

    /**
     * Constructor initializing the game model with known problems and validators.
     */
    public Model() {
        this.problems = ProblemLoader.loadProblems("/known_problems.csv");
        this.availableValidators = new ArrayList<>();
    }

    /**
     * Initializes validators based on the given problem.
     *
     * @param problem The problem for which validators are to be initialized.
     * @return A list of validators.
     */
    private List<Validator> initializeValidators(Problem problem) {
        List<Validator> validators = new ArrayList<>();
        List<List<Integer>> validatorNos = problem.getValidatorNos();

        for (Integer validatorNo : validatorNos.get(0)) {
            int secretCode = problem.getCode();
            switch (validatorNo) {
                case 1:
                    validators.add(new Validator1(secretCode));
                    break;
                case 2:
                    validators.add(new Validator2(secretCode));
                    break;
                case 3:
                    validators.add(new Validator3(secretCode));
                    break;
                case 4:
                    validators.add(new Validator4(secretCode));
                    break;
                case 5:
                    validators.add(new Validator5(secretCode));
                    break;
                case 6:
                    validators.add(new Validator6(secretCode));
                    break;
                case 7:
                    validators.add(new Validator7(secretCode));
                    break;
                case 8:
                    validators.add(new Validator8(secretCode));
                    break;
                case 9:
                    validators.add(new Validator9(secretCode));
                    break;
                case 10:
                    validators.add(new Validator10(secretCode));
                    break;
                case 11:
                    validators.add(new Validator11(secretCode));
                    break;
                case 12:
                    validators.add(new Validator12(secretCode));
                    break;
                case 13:
                    validators.add(new Validator13(secretCode));
                    break;
                case 14:
                    validators.add(new Validator14(secretCode));
                    break;
                case 15:
                    validators.add(new Validator15(secretCode));
                    break;
                case 16:
                    validators.add(new Validator16(secretCode));
                    break;
                case 17:
                    validators.add(new Validator17(secretCode));
                    break;
                case 18:
                    validators.add(new Validator18(secretCode));
                    break;
                case 19:
                    validators.add(new Validator19(secretCode));
                    break;
                case 20:
                    validators.add(new Validator20(secretCode));
                    break;
                case 21:
                    validators.add(new Validator21(secretCode));
                    break;
                case 22:
                    validators.add(new Validator22(secretCode));
                    break;
                default:
                    System.out.println("Unknown validator number: " + validatorNo);
            }
        }
        return validators;
    }

    /**
     * Retrieves the current score of the game.
     *
     * @return The current score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Retrieves the number of rounds played so far in the game.
     *
     * @return The current round number.
     */
    public int getRoundsPlayed() {
        return currentRound;
    }

    /**
     * Retrieves a list of available validators.
     *
     * @return A list of validators that are currently available.
     */
    public List<Validator> getAvailableValidators() {
        return new ArrayList<>(availableValidators);
    }

    /**
     * Sets the user code to a specified value.
     *
     * @param userCode The user code to set.
     */
    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    /**
     * Retrieves the user code that is currently set.
     *
     * @return The current user code.
     */
    public int getUserCode() {
        return userCode;
    }

    /**
     * Removes the current user code by setting it to 0.
     */
    public void removeUserCode() {
        this.userCode = 0;
    }

    /**
     * Checks if the user code is set.
     *
     * @return true if the user code is set, false otherwise.
     */
    public boolean isUserCodeSet() {
        return userCode != 0;
    }

    /**
     * Retrieves the current guess code.
     *
     * @return The current guess code.
     */
    public int getGuessCode() {
        return guessCode;
    }

    /**
     * Checks if the guess code is set.
     *
     * @return true if the guess code is set, false otherwise.
     */
    public boolean isGuessCodeSet() {
        return userCode != 0;
    }

    /**
     * Marks the game as finished.
     */
    public void finishGame() {
        gameFinished = true;
    }

    /**
     * Processes the entered code and determines if it matches the correct code.
     *
     * @param userChoice The code entered by the user.
     */
    public void enterCode(int userChoice) {
        int correctCodeArray = currentProblem.getCode();

        if (userChoice == correctCodeArray) {
            userWon = true;
            ConsoleView.gameOver(0);
        } else {
            userWon = false;
            ConsoleView.gameOver(1);
        }
        gameFinished = true;
    }

    /**
     * Retrieves the result of the last user action.
     *
     * @return true if the user's last action was successful, false otherwise.
     */
    public boolean getUserResult() {
        return userWon;
    }

    /**
     * Checks if a validator can be applied in the current state.
     *
     * @return true if a validator can be applied, false otherwise.
     */
    public boolean canApplyValidator() {
        return validatorsTestedMap.size() < 3;
    }

    /**
     * Chooses a validator based on the given index and applies it.
     *
     * @param chosenValidatorIndex The index of the chosen validator.
     */
    public void chooseValidator(int chosenValidatorIndex) {
        if (chosenValidatorIndex >= 1 && chosenValidatorIndex <= availableValidators.size()) {
            Validator chosenValidator = availableValidators.get(chosenValidatorIndex - 1);
            applyValidator(chosenValidator, chosenValidatorIndex-1);
        } else {
            System.out.println("Invalid validator.");
        }
    }

    /**
     * Retrieves a list of validator numbers for the current problem.
     *
     * @return A list of lists containing validator numbers.
     */
    public List<List<Integer>> getValidatorNumbers() {
        return currentProblem.getValidatorNos();
    }

    /**
     * Applies the given validator and updates the game state accordingly.
     *
     * @param validator The validator to apply.
     * @param chosenValidatorIndex The index of the chosen validator.
     */
    public void applyValidator(Validator validator, int chosenValidatorIndex) {
        ConsoleView.applyValidator(validator, validator.validate(userCode));
        score++;
        validatorsTestedMap.put(chosenValidatorIndex, validator.validate(userCode));
        roundValidators.put(currentRound, validator.getDescription());
    }

    /**
     * Retrieves a map of validators that have been tested.
     *
     * @return A map indicating which validators have been tested and their outcomes.
     */
    public Map<Integer, Boolean> getValidatorsTestedMap() {
        return validatorsTestedMap;
    }

    /**
     * Undoes the last applied validator and updates the game state.
     *
     * @param chosenValidatorIndex The index of the validator to undo.
     */
    public void undoValidator(int chosenValidatorIndex) {
        score--;
        validatorsTestedMap.remove(chosenValidatorIndex-1);
        roundValidators.remove(currentRound);
    }

    /**
     * Moves the game to the next round and updates the game state.
     */
    public void moveToNextRound() {
        currentRound++;
        score += 5;
        validatorsTestedMap.clear();
    }

    /**
     * Moves the game back to the previous round and restores the state.
     *
     * @param validatorsTestedMap A map of validators tested in the last round.
     * @param availableValidators A list of validators available in the last round.
     */
    public void moveToLastRound(Map<Integer, Boolean> validatorsTestedMap, List<Validator> availableValidators) {
        currentRound--;
        score -= 5;
        this.validatorsTestedMap = validatorsTestedMap;
        this.availableValidators.addAll(availableValidators);
    }

    /**
     * Retrieves a list of available problems.
     *
     * @return A list of problems that can be selected for the game.
     */
    public List<Problem> getAvailableProblems() {
        return new ArrayList<>(problems);
    }

    /**
     * Starts a new game with the selected problem.
     *
     * @param selectedProblemIndex The index of the problem to start with.
     * @throws IllegalArgumentException If the selected problem index is invalid.
     */
    public void startGame(int selectedProblemIndex) throws IllegalArgumentException {
        if (selectedProblemIndex < 0 || selectedProblemIndex >= problems.size()) {
            throw new IllegalArgumentException("Problem not found.");
        }
        Problem selectedProblem = problems.get(selectedProblemIndex);

        if (currentRound != 1 || gameFinished) {
            currentProblem = selectedProblem;
            availableValidators.clear();
            availableValidators.addAll(initializeValidators(selectedProblem));
            currentRound = 0;
            score = 0;
            userCode = 0;
            guessCode = 0;
            this.validatorsTestedMap = new HashMap<>();
            roundValidators = new HashMap<>();
            gameFinished = false;
        }
    }

    /**
     * Resets the game to its initial state.
     */
    public void reset() {
        currentProblem = null;
        currentRound = 0;
        gameFinished = false;
        userCode = 0;
        guessCode = 0;
        score = 0;
        availableValidators.clear();
        validatorsTestedMap.clear();
        roundValidators.clear();

        problems = ProblemLoader.loadProblems("/known_problems.csv");
    }

    /**
     * Determines if the game has finished.
     *
     * @return true if the game is finished, false otherwise.
     */
    public boolean isGameFinished() {
        return gameFinished;
    }
}