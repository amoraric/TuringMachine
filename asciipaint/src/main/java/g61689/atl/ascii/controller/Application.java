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
        int x = Integer.parseInt(dividedInput[2]);
        int y = Integer.parseInt(dividedInput[3]);
        int r = Integer.parseInt(dividedInput[4]);
        char c = dividedInput[5].charAt(0);
        paint.newCircle(x, y, r, c);
    }

    /**
     * Handler to add a rectangle
     *
     * @param dividedInput user input
     */
    private void handleAddRectangle(String[] dividedInput) {
        int x = Integer.parseInt(dividedInput[2]);
        int y = Integer.parseInt(dividedInput[3]);
        int w = Integer.parseInt(dividedInput[4]);
        int h = Integer.parseInt(dividedInput[5]);
        char c = dividedInput[6].charAt(0);
        paint.newRectangle(x, y, w, h, c);
    }

    /**
     * Handler to add a square
     *
     * @param dividedInput user input
     */
    private void handleAddSquare(String[] dividedInput) {
        int x = Integer.parseInt(dividedInput[2]);
        int y = Integer.parseInt(dividedInput[3]);
        int s = Integer.parseInt(dividedInput[4]);
        char c = dividedInput[5].charAt(0);
        paint.newSquare(x, y, s, c);
    }

    /**
     * This method starts the program by asking for input and gives answers upon the input.
     */
    public void start() {
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
                }
            } else if (dividedInput[0].equals("move")) {
                // the command looks like this : move [shape number] [dx] [dy]
                int shape = Integer.parseInt(dividedInput[1]);
                int x = Integer.parseInt(dividedInput[2]);
                int y = Integer.parseInt(dividedInput[3]);
                paint.move(shape, x, y);
            } else if (dividedInput[0].equals("color")) {
                // change color of shape like this : color [shape number] [char]
                int shape = Integer.parseInt(dividedInput[1]);
                char c = dividedInput[2].charAt(0);
                paint.changeColor(shape, c);
            } else if (dividedInput[0].equals("group")) {
                // group 2 shapes
                char c = dividedInput[1].charAt(0);
                int[] shapes = new int[dividedInput.length - 2];
                for (int i = 0; i < shapes.length; i++) {
                    shapes[i] = Integer.parseInt(dividedInput[i + 2]);
                }
                paint.group(c, shapes);
            } else if (dividedInput[0].equals("ungroup")) {
                // ungroup the shapes
                int group = Integer.parseInt(dividedInput[1]);
                paint.ungroup(group);
            } else if (dividedInput[0].equals("delete")) {
                int index = Integer.parseInt(dividedInput[1]);
                paint.delete(index);
            } else if (dividedInput[0].equals("list")) {
                // show a numbered list of presented shapes
                View.displayAsciiArtList(paint);
            } else if (input.equalsIgnoreCase("show")) {
                // shows the drawing
                View.displayAsciiArt(paint);
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