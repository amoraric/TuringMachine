package g61689.atl.model;

import g61689.atl.view.ConsoleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {
    int MAX_ROUNDS = 10;
    private final List<Problem> problems;
    private Problem currentProblem;
    private int currentRound;
    private boolean gameFinished;
    private final List<Validator> availableValidators;
    private int validatorsTested;
    private int userCode;
    private int score;
    private Map<Integer, String> roundValidators;
    private Map<Integer, String> validatorsTestedMap;

    public Model() {
        this.problems = ProblemLoader.loadProblems("/known_problems.csv");
        this.availableValidators = new ArrayList<>();
    }

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

    public int getScore() {
        return score;
    }

    public int getNumberValidatorsTested() {
        return validatorsTested;
    }

    public int getRoundsPlayed() {
        return currentRound;
    }

    public List<Validator> getAvailableValidators() {
        return new ArrayList<>(availableValidators);
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public void removeUserCode() {
        this.userCode = -1;
    }

    public boolean isUserCodeSet() {
        return userCode != -1;
    }

    public void finishGame() {
        gameFinished = true;
    }

    public void enterCode(int userChoice) {
        int correctCodeArray = currentProblem.getCode();

        if (userChoice == correctCodeArray) {
            ConsoleView.gameOver(0);
        } else {
            ConsoleView.gameOver(1);
        }
        gameFinished = true;
    }

    public boolean canApplyValidator() {
        return validatorsTestedMap.size() < 3;
    }

    public void chooseValidator(int chosenValidatorIndex) {
        if (chosenValidatorIndex >= 1 && chosenValidatorIndex <= availableValidators.size()) {
            Validator chosenValidator = availableValidators.get(chosenValidatorIndex - 1);
            applyValidator(chosenValidator, chosenValidatorIndex-1);
        } else {
            System.out.println("Invalid validator.");
        }
    }

    public List<List<Integer>> getValidatorNumbers() {
        return currentProblem.getValidatorNos();
    }

    public void applyValidator(Validator validator, int chosenValidatorIndex) {
//        String add = ConsoleView.applyValidator(validator.validate(userCode));
        ConsoleView.applyValidator(validator.validate(userCode));
//        validator.addDescription(add);
        ConsoleView.printValidator(validator, userCode);
        score++;
        validatorsTestedMap.put(chosenValidatorIndex, validator.getDescription());
        roundValidators.put(currentRound, validator.getDescription());
//        validator.addDescription(add);
    }

    public Map<Integer, String> getValidatorsTestedMap() {
        return validatorsTestedMap;
    }

    public boolean getValidatorState(int chosenValidator) {
        return availableValidators.get(chosenValidator-1).validate(userCode);
    }

    public void undoValidator(int chosenValidatorIndex) {
        score--;
        Validator chosenValidator = availableValidators.get(chosenValidatorIndex-1);
        validatorsTestedMap.remove(chosenValidatorIndex, chosenValidator.getDescription());
        roundValidators.remove(currentRound, chosenValidator.getDescription());
//        chosenValidator.removeAddition();
    }

    public void moveToNextRound() {
        currentRound++;
        score += 5;
//        validatorsTested = 0;
        validatorsTestedMap.clear();
//        for (Validator v : availableValidators) {
//            v.removeAddition();
//        }
        if (currentRound > MAX_ROUNDS) {
            gameFinished = true;
        }
    }

    public void moveToLastRound(Map<Integer, String> validatorsTestedMap, List<Validator> availableValidators) {
        currentRound--;
        score -= 5;
//        this. validatorsTested = validatorsTested;
        this.validatorsTestedMap = validatorsTestedMap;
        this.availableValidators.addAll(availableValidators);
    }

    public List<Problem> getAvailableProblems() {
        return new ArrayList<>(problems);
    }

    public void startGame(int selectedProblemIndex) {
        if (selectedProblemIndex >= 0 && selectedProblemIndex < problems.size()) {
            Problem selectedProblem = problems.get(selectedProblemIndex);

            if (currentRound != 1 || gameFinished) {
                currentProblem = selectedProblem;
                availableValidators.clear();
                availableValidators.addAll(initializeValidators(selectedProblem));
                currentRound = 0;
                score = 0;
                userCode = -1;
//                validatorsTested = 0;
                this.validatorsTestedMap = new HashMap<>();
                roundValidators = new HashMap<>();
                gameFinished = false;
            }
        } else {
            System.out.println("Problem not found.");
        }
    }

    public boolean isGameFinished() {
        return gameFinished;
    }
}