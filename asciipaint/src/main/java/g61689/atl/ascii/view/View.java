package g61689.atl.ascii.view;

import g61689.atl.ascii.model.*;

import java.util.Objects;

/**
 * The view class.
 */
public class View {
    /**
     * Outputs the commands that the user can use.
     */
    public static void displayCommands() {
        System.out.println("\nWelcome to AsciiPaint!\n");

        System.out.println("Here are the available commands:");
        System.out.println("1. add circle [x] [y] [r] [C]");
        System.out.println("   - Adds a circle centered at (x,y) with a radius of r and color 'C'.");
        System.out.println("2. add rectangle [x] [y] [w] [h] [C]");
        System.out.println("   - Adds a rectangle with top-left corner at (x,y), width w, height h, and color 'C'.");
        System.out.println("3. add square [x] [y] [s] [C]");
        System.out.println("   - Adds a square with top-left corner at (x,y), sides length s, and color 'C'.");
        System.out.println("4. add line [x] [y] [x'] [y'] [C]");
        System.out.println("   - Adds a line passing through points (x,y) and (x',y') with color 'C'.");
        System.out.println("5. list");
        System.out.println("   - Provides a nr.ed list of the shapes present.");
        System.out.println("6. move [nr.] [dx] [dy]");
        System.out.println("   - Moves the shape (e.g., rectangle) nr. by dx horizontally and dy vertically.");
        System.out.println("7. group [nr.] [nr.] ... [C]");
        System.out.println("   - Groups shapes of nr., C being their new color.");
        System.out.println("8. ungroup [nr.]");
        System.out.println("   - Ungroups the group nr., returning its shapes to the drawing.");
        System.out.println("9. color [nr.] [C]");
        System.out.println("   - Colors shape nr. (e.g., the first of the ungrouped shapes) with color 'C'.");
        System.out.println("10. show");
        System.out.println("   - Displays the drawing.");
        System.out.println("11. delete [nr.]");
        System.out.println("   - Deletes shape nr..");
        System.out.println("12. undo");
        System.out.println("   - Reverts the last command (e.g., delete [nr.]).");
        System.out.println("13. redo");
        System.out.println("   - Re-executes the last undone command (e.g., delete [nr.]).");
        System.out.println("14. help");
        System.out.println("   - Shows the available commands.");
        System.out.println("15. exit");
        System.out.println("   - Quits the application.");
    }

    /**
     * Outputs an error message when the number of parameters is incorrect.
     */
    public static void errorArgs(String command) {
        if (Objects.equals(command, "Circle")) {
            System.out.println("Incorrect command. It should look like this: add circle [x] [y] [r] [C]");
        } else if (Objects.equals(command, "Rectangle")) {
            System.out.println("Incorrect command. It should look like this: add rectangle [x] [y] [w] [h] [C]");
        } else if (Objects.equals(command, "Square")) {
            System.out.println("Incorrect command. It should look like this: add square [x] [y] [s] [C]");
        } else if (Objects.equals(command, "Line")) {
            System.out.println("Incorrect command. It should look like this: add line [x] [y] [x'] [y'] [C]");
        } else if (Objects.equals(command, "Move")) {
            System.out.println("Incorrect command. It should look like this: move [nr.] [dx] [dy]");
        } else if (Objects.equals(command, "Color")) {
            System.out.println("Incorrect command. It should look like this: color [nr.] [C]");
        } else if (Objects.equals(command, "Group")) {
            System.out.println("Incorrect command. It should look like this: group [nr.] [nr.] ... [C]");
        } else if (Objects.equals(command, "Ungroup")) {
            System.out.println("Incorrect command. It should look like this: ungroup [nr.]");
        } else if (Objects.equals(command, "Delete")) {
            System.out.println("Incorrect command. It should look like this: delete [nr.]");
        }
    }

    /**
     * Outputs the drawing.
     */
    public static void displayAsciiArt(AsciiPaint asciiPaint) {
        StringBuilder asciiArt = new StringBuilder();

        for (int y = 0; y < asciiPaint.getHeight(); y++) {
            for (int x = 0; x < asciiPaint.getWidth(); x++) {
                Point currentPoint = new Point(x, y);
                Shape shapeAtPoint = asciiPaint.getShapeAt(currentPoint);

                if (shapeAtPoint != null) {
                    asciiArt.append(shapeAtPoint.getColor());
                } else {
                    asciiArt.append(' '); // If no shapes at this point, add an empty space
                }
            }
            asciiArt.append('\n'); // New line
        }

        System.out.println("ASCII illustration :");
        System.out.println(asciiArt);
    }

    /**
     * Outputs the list of shapes that are on the drawing.
     */
    public static void displayAsciiArtList(AsciiPaint asciiPaint) {
        System.out.println("List of shapes used in the drawing :");
        StringBuilder shapes = new StringBuilder();
        int cc = 0;
        for (ColoredShape shape : asciiPaint.getShapes()) {
            shapes.append(cc).append(". ").append(shape).append('\n');
            cc ++;
        }
        System.out.println(shapes);
    }
}