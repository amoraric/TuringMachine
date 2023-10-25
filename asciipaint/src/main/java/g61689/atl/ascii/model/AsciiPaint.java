package g61689.atl.ascii.model;

import java.util.List;

/**
 * Facade of the model that allows to modify it.
 */
public class AsciiPaint {
    private static final int DEFAULT_WIDTH = 50;
    private static final int DEFAULT_HEIGHT = 50;

    private final Drawing drawing;

    /**
     * Default constructor
     */
    public AsciiPaint() {
        drawing = new Drawing(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Constructor that defines the height and the width of the drawing
     *
     * @param width width
     * @param height height
     */
    public AsciiPaint(int width, int height) {
        drawing = new Drawing(width, height);
    }

    /**
     * Adds a new circle to the drawing list of shapes.
     *
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     * @param radius circle radius
     * @param color shape's color
     */
    public void newCircle(int x, int y, double radius, char color) {
        if (radius <= 0) {
            throw new IllegalArgumentException("The radius can't be smaller or equal to zero!");
        }
        Circle circle = new Circle(new Point(x, y), radius, color);
        drawing.addShape(circle);
    }

    /**
     * Adds a new rectangle to the drawing list of shapes.
     *
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     * @param width horizontal length
     * @param height vertical length
     * @param color shape's color
     */
    public void newRectangle(int x, int y, double width, double height, char color) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("The width and height can't be smaller or equal to zero!");
        }
        Rectangle rectangle = new Rectangle(new Point(x, y), width, height, color);
        this.drawing.addShape(rectangle);
    }

    /**
     * Adds a new square to the drawing list of shapes.
     *
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     * @param side each side's length
     * @param color shape's color
     */
    public void newSquare(int x, int y, double side, char color) {
        Square square = new Square(new Point(x, y), side, color);
        this.drawing.addShape(square);
    }

    /**
     * Moves a shape.
     *
     * @param index the number of the shape that the user wants to move
     * @param dx x-axis distance to move
     * @param dy y-axis distance to move
     */
    public void move(int index, int dx, int dy) {
        if (index < 0 || index >= drawing.getListSize()) {
            throw new IllegalArgumentException("The index can't be smaller than zero or greater than the size of the list!");
        }
        drawing.move(index, dx, dy);
    }

    /**
     * Changes the color of a shape.
     *
     * @param number the number of the shape that the user wants to change the color
     * @param c the color
     */
    public void changeColor(int number, char c) {
        if (number < 0 || number >= drawing.getListSize()) {
            throw new IllegalArgumentException("The index can't be smaller than zero or greater than the size of the list!");
        }
        drawing.changeColor(number, c);
    }

    /**
     * Returns a list of shapes from the drawing
     *
     * @return list of shapes
     */
    public List<ColoredShape> getShapes() {
        return drawing.getShapes();
    }

    /**
     * Gathers the whole drawing table into a string for output.
     *
     * @return the drawing table string
     */
    public String asAscii() { // dans la vue.
        StringBuilder asciiArt = new StringBuilder();

        for (int y = 0; y < drawing.getHeight(); y++) {
            for (int x = 0; x < drawing.getWidth(); x++) {
                Point currentPoint = new Point(x, y);
                Shape shapeAtPoint = drawing.getShapeAt(currentPoint);

                if (shapeAtPoint != null) {
                    asciiArt.append(shapeAtPoint.getColor());
                } else {
                    asciiArt.append(' '); // If no shapes at this point, add an empty space
                }
            }
            asciiArt.append('\n'); // New line
        }

        return asciiArt.toString();
    }

    /**
     * Groups up multiple shapes
     *
     * @param color color of the group
     * @param shapes indexes of the shapes to be added to the group
     */
    public void group(char color, int... shapes) {
        Group group = new Group(color);

        for (int shape : shapes) {
            group.addShape(drawing.getShapeAt(shape));
        }

        drawing.addShape(group);

        for (int i = shapes.length-1; i >= 0; i--) {
            drawing.remove(shapes[i]);
        }
    }

    /**
     * Ungroups a collection of shapes
     *
     * @param index index of the group
     */
    public void ungroup(int index) {
        Shape groupShape = drawing.getShapeAt(index);

        if (groupShape instanceof Group group) {
            List<ColoredShape> groupShapes = group.getShapes();

            for (ColoredShape shape : groupShapes) {
                drawing.addShape(shape);
            }

            drawing.remove(index);
        } else {
            throw new IllegalArgumentException("Shape at index " + index + " is not a group.");
        }
    }

    /**
     * Deletes a shape or a group
     *
     * @param index index
     */
    public void delete(int index) {
        drawing.remove(index);
    }

    public int getHeight() {
        return drawing.getHeight();
    }

    public int getWidth() {
        return drawing.getWidth();
    }

    public Shape getShapeAt(Point point) {
        return drawing.getShapeAt(point);
    }
}
