package g61689.atl.ascii.model;

import java.util.List;

public class GroupCommand implements Command {
    private final Group group;
    private final Drawing drawing;
    private final int[] shapes;

    public GroupCommand(Group group, Drawing drawing, int... shapes) {
        this.group = group;
        this.drawing = drawing;
        this.shapes = shapes;
    }

    @Override
    public void execute() {
        for (int shape : shapes) {
            group.addShape(drawing.getShapeAt(shape));
        }

        drawing.addShape(group);

        for (int i = shapes.length-1; i >= 0; i--) {
            drawing.remove(shapes[i]);
        }
    }

    @Override
    public void cancel() {
        for (ColoredShape shape : group.getShapes()) {
            drawing.addShape(shape);
        }

        drawing.remove(group);
    }
}
