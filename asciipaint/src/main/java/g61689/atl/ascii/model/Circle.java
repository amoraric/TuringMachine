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
//        double dx = Math.abs(p.getX() - center.getX());
//        double dy = Math.abs(p.getY() - center.getY());
//        double distanceSquared = dx * dx + dy * dy;
//        double radiusSquared = radius * radius;
//
//        // Check if the point is inside the circle with some tolerance (e.g., 1 pixel)
//        return Math.abs(distanceSquared - radiusSquared) <= 1;

        double dx = p.getX() - center.getX()+1;
        double dy = p.getY() - center.getY()+1;
        return dx * dx + dy * dy < radius * radius;
    }
}
