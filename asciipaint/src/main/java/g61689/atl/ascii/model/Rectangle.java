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
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("The width and height can't be smaller or equal to zero!");
        }
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    @Override
    public void move(double dx, double dy) {
        upperLeft.move(dx, dy);
    }

    @Override
    public boolean isInside(Point p) {
        return     p.getX() >= upperLeft.getX()
                && p.getX() < upperLeft.getX() + width
                && p.getY() >= upperLeft.getY()
                && p.getY() < upperLeft.getY() + height;
    }

    @Override
    public String toString() {
        return "Rectangle";
    }
}
