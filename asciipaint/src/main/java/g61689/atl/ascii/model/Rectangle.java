package g61689.atl.ascii.model;

public class Rectangle implements Shape {
    private final Point bl;
    private final Point ur;
    private char color;

    public Rectangle(Point bl, Point ur) {
        if (bl.getX() >= ur.getX() || bl.getY() >= ur.getY()) {
            throw new IllegalArgumentException("Bottom left's coordinates should be smaller than Upper right's ones.");
        }
        this.bl = new Point(bl);
        this.ur = new Point(ur);
    }

    @Override
    public void move(double dx, double dy) {
        this.bl.move(dx, dy);
        this.ur.move(dx, dy);
    }

    @Override
    public boolean isInside(Point p) {
        return (p.getX() <= ur.getX() && p.getX() >= bl.getX())
                && (p.getY() <= ur.getY() && p.getY() >= bl.getY());
    }

    @Override
    public char getColor() {
        return this.color;
    }

    @Override
    public void setColor(char color) {
        this.color = color;
    }
}
