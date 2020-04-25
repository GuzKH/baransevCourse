package firstTask;
import java.awt.geom.Point2D;

public class Point {
    double x1, y1, x2, y2;

    public Point (double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    public double distance(){
        double a = Point2D.distance(this.x1, this.y1, this.x2, this.y2);
        return a;
    }

//    @Override
//    public String toString() {
//        return this.x1 + ";" + this.y1;
//    }
//
//    public String toString1() {
//        return this.x2 + ";" + this.y2;
//    }

}
