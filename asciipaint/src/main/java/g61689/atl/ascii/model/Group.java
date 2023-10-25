package g61689.atl.ascii.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A group of shapes class
 */
public class Group extends ColoredShape {
    private final List<ColoredShape> shapes;

    /**
     * Default constructor
     *
     * @param color color of the group
     */
    public Group(char color) {
        super(color);
        this.shapes = new ArrayList<>();
    }

    /**
     * Adds a shape to the group
     *
     * @param shape shape
     */
    public void addShape(ColoredShape shape) {
        shapes.add(shape);
    }

    @Override
    public void move(double dx, double dy) {
        for (Shape shape : shapes) {
            shape.move(dx, dy);
        }
    }

    @Override
    public boolean isInside(Point p) {
        for (Shape shape : shapes) {
            if (shape.isInside(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a list of the colored shapes in the group
     *
     * @return list of colored shapes
     */
    public List<ColoredShape> getShapes() {
        return this.shapes;
    }

    @Override
    public String toString() {
        return "Group";
    }
}
