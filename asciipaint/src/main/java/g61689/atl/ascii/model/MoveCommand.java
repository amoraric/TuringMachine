package g61689.atl.ascii.model;

import g61689.atl.util.Command;

/**
 * This class executes the command move
 */
public class MoveCommand implements Command {
    private final Shape shape;
    private final int dx;
    private final int dy;

    /**
     * Default constructor
     *
     * @param shape shape
     * @param dx x-axis distance to move
     * @param dy y-axis distance to move
     */
    public MoveCommand(Shape shape, int dx, int dy) {
        this.shape = shape;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void execute() {
        shape.move(dx, dy);
    }

    @Override
    public void cancel() {
        shape.move(-dx, -dy);
    }
}
