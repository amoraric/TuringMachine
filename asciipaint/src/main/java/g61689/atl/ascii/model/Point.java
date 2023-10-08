package g61689.atl.ascii.model;

import static java.lang.Math.*;

/**
 * Class of a point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Point constructor that takes 2 coordinates.
     *
     * @param dx x-axis coordinate
     * @param dy y-axis coordinate
     */
    public Point(double dx, double dy) {
        this.x = dx;
        this.y = dy;
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
        this.x += dx;
        this.y += dy;
    }

    /**
     * Returns the distance between 2 points.
     *
     * @param other other point
     * @return the distance
     */
    public double distanceTo(Point other) {
        return sqrt(abs(other.x - this.x) + abs(other.y - this.y));
    }

    /**
     * Returns the x coordinate of the point.
     *
     * @return x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y coordinate of the point.
     *
     * @return y coordinate
     */
    public double getY() {
        return this.y;
    }
}
