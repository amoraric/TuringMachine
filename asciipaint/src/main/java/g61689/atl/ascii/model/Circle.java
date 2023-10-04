package g61689.atl.ascii.model;

public class Circle implements Shape {
    private final double radius;
    private final Point center;
    private char color;

    public Circle(Point center, double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("radius must be positive. Received: " + radius);
        }
        this.center = new Point(center);
        this.radius = radius;
    }

    @Override
    public void move(double dx, double dy) {
        center.move(dx, dy);
    }

    @Override
    public boolean isInside(Point p) {
        return (p.getX() <= radius + center.getX() && p.getX() >= center.getX() - radius)
                && (p.getY() <= radius + center.getY() && p.getY() >= center.getY() - radius);
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
