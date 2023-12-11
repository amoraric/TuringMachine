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
        p2.move(dx, dy);
    }

    @Override
    public boolean isInside(Point p) {
        double dirCoeff = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
        return Math.abs(dirCoeff * p.getX() - p.getY() - dirCoeff * p1.getX() + p1.getY())
                / (Math.sqrt(dirCoeff*dirCoeff + 1)) < 0.5;
    }

    @Override
    public String toString() {
        return "Line";
    }
}
