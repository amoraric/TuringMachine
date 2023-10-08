package g61689.atl.ascii.model;

/**
 * A rectangle class.
 */
public class Rectangle extends ColoredShape {
    private final Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Rectangle's constructor that takes its upper left point, size and color.
     *
     * @param upperLeft a point that represents the upper left point of the rectangle
     * @param width width
     * @param height height
     * @param color color
     */
    public Rectangle(Point upperLeft, double width, double height, char color) {
        super(color);
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Moves the rectangle.
     *
     * @param dx x-axis distance to move
     * @param dy y-axis distance to move
     */
    @Override
    public void move(double dx, double dy) {
        this.upperLeft.move(dx, dy);
    }

    /**
     * Returns true or false depending on if the point p is inside the rectangle.
     *
     * @param p a point
     * @return a boolean
     */
    @Override
    public boolean isInside(Point p) {
        return (p.getX() >= upperLeft.getX()-1 && p.getX() < upperLeft.getX() + this.width-1)
                && (p.getY() >= upperLeft.getY()-1 && p.getY() < upperLeft.getY() + this.height-1);
    }
}
