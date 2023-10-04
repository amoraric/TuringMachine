package g61689.atl.ascii.model;

public interface Shape {
    public void move(double dx, double dy);

    public boolean isInside(Point p);

    public char getColor();

    public void setColor(char color);
}
