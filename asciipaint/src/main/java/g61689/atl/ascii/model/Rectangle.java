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
        // vérifier height and width >0
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
        upperLeft.move(dx, dy);
    }

    /**
     * Returns true or false depending on if the point p is inside the rectangle.
     *
     * @param p a point
     * @return a boolean
     */
    @Override
    public boolean isInside(Point p) {
        return     p.getX() >= upperLeft.getX()
                && p.getX() < upperLeft.getX() + width
                && p.getY() >= upperLeft.getY()
                && p.getY() < upperLeft.getY() + height;
    }

    /**
     * Override of the toString method. Helps to output the name of the shape.
     *
     * @return name of the shape
     */
    @Override
    public String toString() {
        return "Rectangle";
    }
}
