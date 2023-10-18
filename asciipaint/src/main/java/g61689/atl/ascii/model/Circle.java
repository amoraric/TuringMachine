package g61689.atl.ascii.model;

/**
 * A circle class.
 */
public class Circle extends ColoredShape {
    private final double radius;
    private final Point center;

    /**
     * Circle's constructor that takes its center point, its radius and its color.
     *
     * @param center point representing the center of the circle
     * @param radius the radius of the circle
     * @param color a color
     */
    public Circle(Point center, double radius, char color) {
        super(color);
        if (radius <= 0) {
            throw new IllegalArgumentException("radius must be positive. Received: " + radius);
        }
        this.center = new Point(center);
        this.radius = radius;
    }

    /**
     * Moves the circle.
     *
     * @param dx x-axis distance to move
     * @param dy y-axis distance to move
     */
    @Override
    public void move(double dx, double dy) {
        center.move(dx, dy);
    }

    /**
     * Returns true or false depending on if the point p is inside the circle.
     *
     * @param p a point
     * @return a boolean
     */
    @Override
    public boolean isInside(Point p) {
        return center.distanceTo(p) <= radius;
    }

    /**
     * Override of the toString method. Helps to output the name of the shape.
     *
     * @return name of the shape
     */
    @Override
    public String toString() {
        return "Circle";
    }
}
