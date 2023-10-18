package g61689.atl.ascii.model;

/**
 * Interface of a shape.
 *
 * @author Augustin
 */
public interface Shape {


    /**
     * Method that moves a shape.
     *
     * @param dx x-axis distance to move
     * @param dy y-axis distance to move
     */
    void move(double dx, double dy);

    /**
     * Method that returns true or false depending on if a point is inside a shape.
     *
     * @param p a point
     * @return a boolean
     */
    boolean isInside(Point p);

    /**
     * Method that returns the color of the shape.
     *
     * @return a color
     */
    char getColor();

    // setColor
}
