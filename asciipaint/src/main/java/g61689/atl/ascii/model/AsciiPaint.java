package g61689.atl.ascii.model;

/**
 * Facade of the model that allows to modify it.
 */
public class AsciiPaint {
    private final Drawing drawing;

    /**
     * Default constructor
     */
    public AsciiPaint() {
        this.drawing = new Drawing(50, 50);
    }

    /**
     * Constructor that defines the height and the width of the drawing
     *
     * @param width width
     * @param height height
     */
    public AsciiPaint(int width, int height) {
        this.drawing = new Drawing(width, height);
    }

    /**
     * Adds a new circle to the drawing list of shapes.
     *
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     * @param radius circle radius
     * @param color shape's color
     */
    public void newCircle(int x, int y, double radius, char color) {
        Circle circle = new Circle(new Point(x, y), radius, color);
        this.drawing.addShape(circle);
    }

    /**
     * Adds a new rectangle to the drawing list of shapes.
     *
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     * @param width horizontal length
     * @param height vertical length
     * @param color shape's color
     */
    public void newRectangle(int x, int y, double width, double height, char color) {
        Rectangle rectangle = new Rectangle(new Point(x, y), width, height, color);
        this.drawing.addShape(rectangle);
    }

    /**
     * Adds a new square to the drawing list of shapes.
     *
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     * @param side each side's length
     * @param color shape's color
     */
    public void newSquare(int x, int y, double side, char color) {
        Square square = new Square(new Point(x, y), side, color);
        this.drawing.addShape(square);
    }

    /**
     * Moves a shape.
     *
     * @param number the number of the shape that the user wants to move
     * @param dx x-axis distance to move
     * @param dy y-axis distance to move
     */
    public void move(int number, int dx, int dy) {
        // TODO
    }

    /**
     * Changes the color of a shape.
     *
     * @param number the number of the shape that the user wants to change the color
     * @param c the color
     */
    public void color(int number, char c) {
        // TODO
    }

    /**
     * Gathers the whole drawing table into a string for output.
     *
     * @return the drawing table string
     */
    public String asAscii() {
        StringBuilder asciiArt = new StringBuilder();

        for (int y = 0; y < drawing.getHeight(); y++) {
            for (int x = 0; x < drawing.getWidth(); x++) {
                Point currentPoint = new Point(x, y);
                Shape shapeAtPoint = drawing.getShapeAt(currentPoint);

                if (shapeAtPoint != null) {
                    asciiArt.append(shapeAtPoint.getColor());
                } else {
                    asciiArt.append(' '); // If no shapes at this point, add an empty space
                }
            }
            asciiArt.append('\n'); // New line
        }

        return asciiArt.toString();
    }
}
