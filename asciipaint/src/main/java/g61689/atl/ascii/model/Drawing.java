package g61689.atl.ascii.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An illustration that has a size and contains a collection of shapes.
 */
public class Drawing {
    private final List<ColoredShape> shapes;
    private final int height;
    private final int width;
    private static final int DEFAULT_WIDTH = 50;
    private static final int DEFAULT_HEIGHT = 50;

    /**
     * Default constructor
     */
    public Drawing() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * Constructor that defines the height and the width of the drawing.
     *
     * @param width width
     * @param height height
     */
    public Drawing(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("The width and height of the drawing can't be smaller than zero!");
        }
        this.shapes = new ArrayList<>();
        this.width = width;
        this.height = height;
    }

    /**
     * Adds a shape to the collection.
     *
     * @param shape shape
     */
     void addShape(ColoredShape shape) {
        shapes.add(shape);
    }

    /**
     * Returns the shape that exists at a certain point of the drawing table.
     *
     * @param p a point
     * @return the shape that's located at the given point
     */
    public Shape getShapeAt(Point p) {
        for (Shape shape : shapes) {
            if (shape.isInside(p)) {
                return shape;
            }
        }
        return null;
    }

    /**
     * Moves a shape.
     *
     * @param number the number of the shape that the user wants to move
     * @param dx x-axis distance to move
     * @param dy y-axis distance to move
     */
    void move(int number, int dx, int dy) {
        shapes.get(number).move(dx, dy);
    }

    /**
     * Changes the color of a shape.
     *
     * @param number the number of the shape that the user wants to change the color
     * @param c the color
     */
    void changeColor(int number, char c) {
        shapes.get(number).setColor(c);
    }

    /**
     * Returns the list of shapes that exist on the drawing.
     *
     * @return list of shapes
     */
    public List<ColoredShape> getShapes() {
        return Collections.unmodifiableList(shapes);
    }

    /**
     * returns the height of the drawing table.
     *
     * @return the height
     */
    int getHeight() {
        return height;
    }

    /**
     * returns the width of the drawing table.
     *
     * @return the width
     */
    int getWidth() {
        return width;
    }

    /**
     * Returns the shape found at the given index
     *
     * @param index given index
     * @return a shape
     */
    public ColoredShape getShapeAt(int index) {
        return shapes.get(index);
    }

    /**
     * Removes the shape at the given index
     *
     * @param index given index
     */
    protected void remove(int index) {
        shapes.remove(index);
    }

    /**
     * Returns the size of the list of shapes
     *
     * @return size of the list
     */
    public int getListSize() {
        return shapes.size();
    }

    protected boolean remove(Shape shape) {
        return shapes.remove(shape);
    }
}
