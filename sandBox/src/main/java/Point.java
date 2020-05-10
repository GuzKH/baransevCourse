public class Point {
    double x, y;

    public Point (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distance(Point p2) {
        double x1 = this.getX();
        double y1 = this.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

}
