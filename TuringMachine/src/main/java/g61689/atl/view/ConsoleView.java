package g61689.atl.view;

import g61689.atl.model.ModelFacade;
import g61689.atl.model.Problem;
import g61689.atl.model.Validator;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

public class ConsoleView {
    public static void displayWelcomeMessage() {
        System.out.println("Welcome to the Turing Machine game !");
    }

    public static int chooseProblemOrRandom(Scanner scanner, ModelFacade modelFacade) {
        int selectedProblemIndex = 0;
        boolean correct = false;
        while (!correct) {
            System.out.println("Do you want to choose a problem or to get a random one? [1 Choose] / [2 Random] ");
            String userInput = scanner.next();
            System.out.println();
            if (Objects.equals(userInput, "Choose") || Objects.equals(userInput, "1")) {
                System.out.println("Choose a problem to solve :");
                List<Problem> problems = modelFacade.getAvailableProblems();
                for (Problem problem : problems) {
                    System.out.println(problem.getDescription());
                }

                selectedProblemIndex = scanner.nextInt();
                System.out.println();
                correct = true;
            } else if (Objects.equals(userInput, "Random")  || Objects.equals(userInput, "2")) {
                Random random = new Random();
                selectedProblemIndex = random.nextInt(16) + 1;
                correct = true;
            } else {
                System.out.println("You have to answer with either [Choose] or [Random]");
            }
        }
        return selectedProblemIndex;
    }

    public static void showValidators(List<Validator> validators) {
        System.out.println();
        for (int i = 0; i < validators.size(); i++) {
            System.out.println(i+1 + ". " + validators.get(i).getDescription());
        }
    }

    public static void showState(ModelFacade modelFacade) {
        System.out.println("\n| -------------------------- |");
        System.out.println("Score : " + modelFacade.getScore());
        System.out.println("Validators tested : " + modelFacade.getNumberValidatorsTested());
        System.out.println("Round played : " + modelFacade.getRoundsPlayed());
        System.out.println("| -------------------------- |\n");
    }

    public static void promptUserCode() {
        System.out.println("\nEnter your desired code: ");
    }

    public static void displayInvalidCodeMessage() {
        System.out.println("\nPlease enter a proper code: ");
    }

    public static void getUserActionChoice() {
        System.out.println("\nEnter your action [1 enter code] [2 test validator] [3 skip] [4 test code] [5 exit] [6 undo] [7 redo] : ");
    }

    public static void displayInvalidActionMessage() {
        System.out.println("\nInvalid action. Please retry.");
    }

    public static void applyValidator(Validator validator, boolean passed) {
        if (passed) {
            System.out.print("Your code passed this validator : ");
        } else {
            System.out.print("Your code DID NOT pass this validator : ");
        }
        System.out.println(validator.getDescription());
    }

    public static void gameOver(int state) {
        if (state == 0) {
            System.out.println("Congratulations you found the correct code!");
        } else {
            System.out.println("Wrong code! You lost!");
        }
    }
}