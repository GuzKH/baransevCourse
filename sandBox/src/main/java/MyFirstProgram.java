import secondTask.Point;

import java.awt.geom.Point2D;

public class MyFirstProgram{

	public static void main(String[] args){
		System.out.println("hello, world!");
		System.out.println("------------");

		Point p1 = new Point(3,4);
		Point p2 = new Point(7,1);

		double v = distance(p1, p2);
		System.out.println("Distance between two points: " + v);
		System.out.println("------------");
	}

	public static double distance(Point p1, Point p2) {

		double x1 = p1.getX();
		double y1 = p1.getY();
		double x2 = p2.getX();
		double y2 = p2.getY();
		double a = Point2D.distance(x1, y1, x2, y2);
		return a;
	}







}
