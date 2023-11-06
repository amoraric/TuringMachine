package g61689.atl.ascii.model;

public class Line extends ColoredShape {
    private final Point p1;
    private final Point p2;

    /**
     * ColoredShape's constructor.
     *
     * @param color a color
     */
    public Line(Point p1, Point p2, char color) {
        super(color);
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public void move(double dx, double dy) {
        p1.move(dx, dy);
        p2.move(dx, dy);
    }

    @Override
    public boolean isInside(Point p) {
        return false;
    }

    @Override
    public String toString() {
        return "Line";
    }
}
