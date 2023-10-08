package g61689.atl.ascii.model;

import java.util.ArrayList;
import java.util.List;

/**
 * An illustration that has a size and contains a collection of shapes.
 */
public class Drawing {
    private final List<Shape> shapes;
    private final int height;
    private final int width;

    /**
     * Default constructor
     */
    public Drawing() {
        this.shapes = new ArrayList<>();
        this.height = 50;
        this.width = 50;
    }

    /**
     * Constructor that defines the height and the width of the drawing.
     *
     * @param width width
     * @param height height
     */
    public Drawing(int width, int height) {
        this.shapes = new ArrayList<>();
        this.width = width;
        this.height = height;
    }

    /**
     * Adds a shape to the collection.
     *
     * @param shape shape
     */
    public void addShape(Shape shape) {
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
     * returns the height of the drawing table.
     *
     * @return the height
     */
    int getHeight() {
        return this.height;
    }

    /**
     * returns the width of the drawing table.
     *
     * @return the width
     */
    int getWidth() {
        return this.width;
    }
}
