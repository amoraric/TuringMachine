package g61689.atl.ascii.model;

/**
 * An abstract class that implements the Shape interface and handles the color.
 */
public abstract class ColoredShape implements Shape {
    private char color;

    /**
     * ColoredShape's constructor.
     *
     * @param color a color
     */
    public ColoredShape(char color) {
        this.color = color;
    }

    /**
     * Sets a color for the shape.
     *
     * @param color a color
     */
    public void setColor(char color) {
        this.color = color;
    }

    /**
     * Returns the color of the shape.
     *
     * @return a color
     */
    public char getColor() {
        return color;
    }
}
