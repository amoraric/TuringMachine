package g61689.atl.ascii.model;

import java.util.List;

public class UngroupCommand implements Command {
    private final Shape groupShape;
    private final Drawing drawing;
    private final int index;

    public UngroupCommand(Shape groupShape, Drawing drawing, int index) {
        this.groupShape = groupShape;
        this.drawing = drawing;
        this.index = index;
    }

    @Override
    public void execute() {
        if (groupShape instanceof Group group) {
            List<ColoredShape> groupShapes = group.getShapes();

            for (ColoredShape shape : groupShapes) {
                drawing.addShape(shape);
            }

            drawing.remove(index);
        } else {
            throw new IllegalArgumentException("Shape at index " + index + " is not a group.");
        }
    }

    @Override
    public void cancel() {

    }
}
