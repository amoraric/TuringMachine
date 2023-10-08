package g61689.atl.ascii.model;

/**
 * A square class.
 */
public class Square extends Rectangle {
    /**
     * Square's constructor that takes the upper left point of the square, the sides length and the color.
     *
     * @param upperLeft a point that represents the upper left point of the rectangle
     * @param side the side's length
     * @param color color
     */
    public Square(Point upperLeft, double side, char color) {
        super(upperLeft, side, side, color);
    }

    /**
     * Override of the toString method. Helps to output the name of the shape.
     *
     * @return name of the shape
     */
    @Override
    public String toString() {
        return "Square";
    }
}
