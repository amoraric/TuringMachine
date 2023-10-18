package g61689.atl.ascii.model;

import static java.lang.Math.*;

/**
 * Represents a point in a 2D plane.
 *
 * @author Augustin
 */
public class Point {
    private double x;
    private double y;

    /**
     * Point constructor that takes 2 coordinates.
     *
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Point constructor that takes another point for the construction.
     *
     * @param p a point
     */
    public Point(Point p) {
        this(p.x, p.y);
    }

    /**
     * Moves a point.
     *
     * @param dx x-axis distance to move
     * @param dy y-axis distance to move
     */
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    /**
     * Returns the distance between 2 points.
     *
     * @param other other point
     * @return the distance
     */
    public double distanceTo(Point other) {
        return sqrt(abs(other.x - this.x)*abs(other.x - this.x)
                + abs(other.y - this.y)*abs(other.y - this.y));
    }

    /**
     * Returns the x coordinate of the point.
     *
     * @return x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y coordinate of the point.
     *
     * @return y coordinate
     */
    public double getY() {
        return y;
    }
}
