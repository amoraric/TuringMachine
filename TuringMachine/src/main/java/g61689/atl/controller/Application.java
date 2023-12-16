package g61689.atl.controller;

import g61689.atl.model.ModelFacade;
import g61689.atl.view.ConsoleView;
import util.Observable;
import util.Observer;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Represents the game application, handling user interactions and game flow.
 */
public class Application implements Observer {
    private final ModelFacade modelFacade;
    private final Scanner scanner;

    /**
     * Constructor initializing the game application with a given model facade.
     *
     * @param modelFacade The model facade to be used in this application.
     */
    public Application(ModelFacade modelFacade) {
        this.modelFacade = modelFacade;
        modelFacade.register(this);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the game loop, handling the main flow of the game.
     */
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

    /**
     * Performs actions based on user input.
     */
    private void performUserAction() {
        try {
            ConsoleView.getUserActionChoice();
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1 -> setCode();
                case 2 -> testValidator();
                case 3 -> moveToNextRound();
                case 4 -> testCode();
                case 5 -> modelFacade.finishGame();
                case 6 -> modelFacade.undo();
                case 7 -> modelFacade.redo();
                default -> ConsoleView.displayInvalidActionMessage();
            }
        } catch (InputMismatchException e) {
            ConsoleView.displayInvalidActionMessage();
            scanner.nextLine();
        }
    }

    /**
     * Checks if a given string is an integer.
     *
     * @param str The string to check.
     * @return true if the string is an integer, false otherwise.
     */
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Sets the user code based on user input.
     */
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

    /**
     * Validates if a given code is within the specified range and format.
     *
     * @param code The code to validate.
     * @return true if the code is valid, false otherwise.
     */
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

    /**
     * Handles the user action to test a code.
     */
    private void testCode() {
        ConsoleView.promptUserCode();
        int userChoice = scanner.nextInt();
        modelFacade.enterCode(userChoice);
    }

    /**
     * Handles the user action to test a validator.
     */
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

    /**
     * Moves the game to the next round.
     */
    private void moveToNextRound() {
        modelFacade.moveToNextRound();
    }

    /**
     * Main method to start the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        ModelFacade modelFacade = new ModelFacade();
        Application app = new Application(modelFacade);
        app.startGame();
    }

    @Override
    public void update(Observable observable) {
        if (observable instanceof ModelFacade) {
            ConsoleView.showState(modelFacade);
        }
    }
}