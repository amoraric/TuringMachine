package g61689.atl.ascii.model;

public class Point {
    private double x;
    private double y;

    public Point() {
        this(0,0);
    }

    public Point(double dx, double dy) {
        this.x = dx;
        this.y = dy;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public String toString() {
        return "("+this.x+", "+this.y+")";
    }

    public Point(Point p) {
        this(p.x, p.y);
    }
}
