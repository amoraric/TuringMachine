package g61689.atl.controller;

import g61689.atl.model.ModelFacade;
import g61689.atl.view.ConsoleView;
import util.Observable;
import util.Observer;

import java.util.Scanner;

public class Application implements Observer {
    private final ModelFacade modelFacade;
    private final Scanner scanner;

    public Application(ModelFacade modelFacade) {
        this.modelFacade = modelFacade;
        modelFacade.register(this);
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        ConsoleView.displayWelcomeMessage();
        int selectedProblemIndex = ConsoleView.chooseProblemOrRandom(scanner, modelFacade);

        modelFacade.startGame(selectedProblemIndex-1);

        ConsoleView.showValidators(modelFacade.getAvailableValidators());

        // Entering the loop of the game
        while (!modelFacade.isGameFinished()) {
            performUserAction();
        }
    }

    private void performUserAction() {
        ConsoleView.getUserActionChoice();
        int userChoice = scanner.nextInt();

        switch (userChoice) {
            case 1:
                setCode();
                break;
            case 2:
                testValidator();
                break;
            case 3:
                moveToNextRound();
                break;
            case 4:
                testCode();
                break;
            case 5:
                endGame();
                break;
            default:
                ConsoleView.displayInvalidActionMessage();
        }
    }

    private void endGame() {
        modelFacade.finishGame();
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void setCode() {
        if (modelFacade.getNumberValidatorsTested() == 0) {
            ConsoleView.promptUserCode();
            String userCode = scanner.next();
            while (!isInteger(userCode) || userCode.length() != 3 || !codeIsValid(Integer.parseInt(userCode))) {
                ConsoleView.displayInvalidCodeMessage();
                userCode = scanner.next();
            }
            modelFacade.setUserCode(Integer.parseInt(userCode));
        } else {
            ConsoleView.displayInvalidActionMessage();
        }
    }

    private boolean codeIsValid(int code) {
        int[] codeArray = new int[3];
        codeArray[0] = code / 100;
        codeArray[1] = (code / 10) % 10;
        codeArray[2] = code % 10;
        for (int i : codeArray) {
            if (i < 0 || i > 5) {
                return false;
            }
        }
        return true;
    }

    private void testCode() {
        ConsoleView.promptUserCode();
        int userChoice = scanner.nextInt();
        modelFacade.enterCode(userChoice);
    }

    private void testValidator() {
        if (!modelFacade.canApplyValidator()) {
            System.out.println("Too many validators tested this round!");
        } else {
            if (modelFacade.isUserCodeSet()) {
                ConsoleView.showValidators(modelFacade.getAvailableValidators());
                int chosenValidatorIndex = scanner.nextInt();
                modelFacade.chooseValidator(chosenValidatorIndex);
            } else {
                ConsoleView.displayInvalidActionMessage();
            }
        }
    }

    private void moveToNextRound() {
        modelFacade.moveToNextRound();
    }
    public static void main(String[] args) {
        ModelFacade modelFacade = new ModelFacade();
        Application app = new Application(modelFacade);
        app.startGame();
    }

    @Override
    public void update(Observable observable) {
        if (observable instanceof ModelFacade) {
            ModelFacade modelFacade = (ModelFacade) observable;
            ConsoleView.showState(modelFacade);
        }
    }
}