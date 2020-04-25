package secondTask;
import java.awt.geom.Point2D;

public class Point {
  // double x1, y1, x2, y2;
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
        double a = Point2D.distance(x1, y1, x2, y2);
        return a;
    }
}
