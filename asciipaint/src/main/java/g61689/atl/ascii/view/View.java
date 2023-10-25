package g61689.atl.ascii.view;

import g61689.atl.ascii.model.*;

/**
 * The view class.
 */
public class View {
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