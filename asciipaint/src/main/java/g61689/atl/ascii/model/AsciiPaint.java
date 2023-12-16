package g61689.atl.ascii.model;

import g61689.atl.util.Command;
import g61689.atl.util.CommandManager;

import java.util.List;

/**
 * Facade of the model that allows to modify it.
 */
public class AsciiPaint {
    private static final int DEFAULT_WIDTH = 50;
    private static final int DEFAULT_HEIGHT = 50;

    private final Drawing drawing;
    private final CommandManager commandManager;

    /**
     * Default constructor
     */
    public AsciiPaint() {
        drawing = new Drawing(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        commandManager = new CommandManager();
    }

    /**
     * Constructor that defines the height and the width of the drawing
     *
     * @param width width
     * @param height height
     */
    public AsciiPaint(int width, int height) {
        drawing = new Drawing(width, height);
        commandManager = new CommandManager();
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
        Command command = new AddCommand(circle, drawing);
        commandManager.newCommand(command);
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
        Command command = new AddCommand(rectangle, drawing);
        commandManager.newCommand(command);
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
        Command command = new AddCommand(square, drawing);
        commandManager.newCommand(command);
    }

    /**
     * Adds a new line to the drawing list of shapes.
     *
     * @param x1 first point's x-axis coordinate
     * @param y1 first point's y-axis coordinate
     * @param x2 second point's x-axis coordinate
     * @param y2 second point's y-axis coordinate
     * @param color shape's color
     */
    public void newLine(int x1, int y1, int x2, int y2, char color) {
        Line line = new Line(new Point(x1, y1), new Point(x2, y2), color);
        Command command = new AddCommand(line, drawing);
        commandManager.newCommand(command);
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
        Command command = new MoveCommand(drawing.getShapeAt(index), dx, dy);
        commandManager.newCommand(command);
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
        drawing.changeColor(number, c); // command !
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
     * Groups up multiple shapes
     *
     * @param color color of the group
     * @param shapes indexes of the shapes to be added to the group
     */
    public void group(char color, int... shapes) {
        Group group = new Group(color);
        Command command = new GroupCommand(group, drawing, shapes);
        commandManager.newCommand(command);
    }

    /**
     * Ungroups a collection of shapes
     *
     * @param index index of the group
     */
    public void ungroup(int index) {
        Shape groupShape = drawing.getShapeAt(index);
        Command command = new UngroupCommand(groupShape, drawing, index);
        commandManager.newCommand(command);
    }

    /**
     * Deletes a shape or a group
     *
     * @param index index
     */
    public void delete(int index) { // command !
        drawing.remove(index);
    }

    /**
     * Returns the height of the drawing
     *
     * @return height
     */
    public int getHeight() {
        return drawing.getHeight();
    }

    /**
     * Returns the width of the drawing
     *
     * @return the width
     */
    public int getWidth() {
        return drawing.getWidth();
    }

    /**
     * Returns the shape at a given point in the 2d plane
     *
     * @param point a point
     * @return the shape at the point
     */
    public Shape getShapeAt(Point point) {
        return drawing.getShapeAt(point);
    }

    /**
     * Undoes the command
     */
    public void undo() {
        commandManager.undo();
    }

    /**
     * Redoes the command
     */
    public void redo() {
        commandManager.redo();
    }
}
