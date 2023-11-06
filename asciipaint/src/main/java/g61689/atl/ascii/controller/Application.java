package g61689.atl.ascii.controller;

import g61689.atl.ascii.model.AsciiPaint;
import g61689.atl.ascii.view.View;

import java.util.Scanner;

/**
 * App initializes the program and takes inputs from the user.
 */
public class Application {
    private final AsciiPaint paint;

    /**
     * Default constructor
     */
    public Application() {
        paint = new AsciiPaint();
    }

    /**
     * Handler to add a circle
     *
     * @param dividedInput user input
     */
    private void handleAddCircle(String[] dividedInput) {
        if (dividedInput.length != 6) {
            View.errorArgs("Circle");
        } else {
            int x = Integer.parseInt(dividedInput[2]);
            int y = Integer.parseInt(dividedInput[3]);
            int r = Integer.parseInt(dividedInput[4]);
            char c = dividedInput[5].charAt(0);
            paint.newCircle(x, y, r, c);
        }
    }

    /**
     * Handler to add a rectangle
     *
     * @param dividedInput user input
     */
    private void handleAddRectangle(String[] dividedInput) {
        if (dividedInput.length != 7) {
            View.errorArgs("Rectangle");
        } else {
            int x = Integer.parseInt(dividedInput[2]);
            int y = Integer.parseInt(dividedInput[3]);
            int w = Integer.parseInt(dividedInput[4]);
            int h = Integer.parseInt(dividedInput[5]);
            char c = dividedInput[6].charAt(0);
            paint.newRectangle(x, y, w, h, c);
        }
    }

    /**
     * Handler to add a square
     *
     * @param dividedInput user input
     */
    private void handleAddSquare(String[] dividedInput) {
        if (dividedInput.length != 6) {
            View.errorArgs("Square");
        } else {
            int x = Integer.parseInt(dividedInput[2]);
            int y = Integer.parseInt(dividedInput[3]);
            int s = Integer.parseInt(dividedInput[4]);
            char c = dividedInput[5].charAt(0);
            paint.newSquare(x, y, s, c);
        }
    }

    /**
     * Handler to add a line
     *
     * @param dividedInput user input
     */
    private void handleAddLine(String[] dividedInput) {
        if (dividedInput.length != 7) {
            View.errorArgs("Line");
        } else {
            int x1 = Integer.parseInt(dividedInput[2]);
            int y1 = Integer.parseInt(dividedInput[3]);
            int x2 = Integer.parseInt(dividedInput[4]);
            int y2 = Integer.parseInt(dividedInput[5]);
            char c = dividedInput[6].charAt(0);
            paint.newLine(x1, y1, x2, y2, c);
        }
    }

    /**
     * This method starts the program by asking for input and gives answers upon the input.
     */
    public void start() {
        View.displayCommands();
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        while (isRunning) {
            System.out.print("Enter a command: ");
            String input = scanner.nextLine();
            String[] dividedInput = input.trim().split(" ");

            // Reading the commands
            if (input.equalsIgnoreCase("exit")) {
                isRunning = false;
            } else if (dividedInput[0].equals("add")) {
                switch (dividedInput[1]) {
                    case "circle" -> {
                        handleAddCircle(dividedInput);
                        break;
                    }
                    case "rectangle" -> {
                        handleAddRectangle(dividedInput);
                        break;
                    }
                    case "square" -> {
                        handleAddSquare(dividedInput);
                        break;
                    }
                    case "line" -> {
                        handleAddLine(dividedInput);
                        break;
                    }
                }
            } else if (dividedInput[0].equals("move")) {
                if (dividedInput.length != 4) {
                    View.errorArgs("Move");
                } else {
                    int shape = Integer.parseInt(dividedInput[1]);
                    int x = Integer.parseInt(dividedInput[2]);
                    int y = Integer.parseInt(dividedInput[3]);
                    paint.move(shape, x, y);
                }
            } else if (dividedInput[0].equals("color")) {
                if (dividedInput.length != 3) {
                    View.errorArgs("Color");
                } else {
                    int shape = Integer.parseInt(dividedInput[1]);
                    char c = dividedInput[2].charAt(0);
                    paint.changeColor(shape, c);
                }
            } else if (dividedInput[0].equals("group")) {
                if (dividedInput.length < 4) {
                    View.errorArgs("Group");
                } else {
                    int[] shapes = new int[dividedInput.length - 2];
                    for (int i = 0; i < shapes.length; i++) {
                        shapes[i] = Integer.parseInt(dividedInput[i + 1]);
                    }
                    char c = dividedInput[dividedInput.length - 1].charAt(0);
                    paint.group(c, shapes);
                }
            } else if (dividedInput[0].equals("ungroup")) {
                if (dividedInput.length != 2) {
                    View.errorArgs("Ungroup");
                } else {
                    int group = Integer.parseInt(dividedInput[1]);
                    paint.ungroup(group);
                }
            } else if (dividedInput[0].equals("delete")) {
                if (dividedInput.length != 2) {
                    View.errorArgs("Delete");
                } else {
                    int index = Integer.parseInt(dividedInput[1]);
                    paint.delete(index);
                }
            } else if (dividedInput[0].equals("undo")) {
                paint.undo();
            } else if (dividedInput[0].equals("redo")) {
                paint.redo();
            } else if (dividedInput[0].equals("list")) {
                View.displayAsciiArtList(paint);
            } else if (input.equalsIgnoreCase("show")) {
                View.displayAsciiArt(paint);
            } else if (input.equalsIgnoreCase("help")) {
                View.displayCommands();
            } else {
                System.out.println("Command not recognized.");
            }
        }

        scanner.close();
    }

    /**
     * Main method that initializes the game and calls the start method.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        Application app = new Application();
        app.start();
    }
}