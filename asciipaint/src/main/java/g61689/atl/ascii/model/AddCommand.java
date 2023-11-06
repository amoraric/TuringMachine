package g61689.atl.ascii.model;

/**
 * This class executes the command add
 */
public class AddCommand implements Command {
    private final Shape shape;
    private final Drawing drawing;

    /**
     * Default constructor
     *
     * @param shape shape
     * @param drawing drawing
     */
    public AddCommand(Shape shape, Drawing drawing) {
        this.shape = shape;
        this.drawing = drawing;
    }

    @Override
    public void execute() {
        drawing.addShape((ColoredShape) shape);
    }

    @Override
    public void cancel() {
        drawing.remove(shape);
    }
}
