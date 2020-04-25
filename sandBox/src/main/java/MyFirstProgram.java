import firstTask.Point;

import static java.awt.geom.Point2D.distance;
import static java.awt.geom.Point2D.distanceSq;

public class MyFirstProgram{

	public static void main(String[] args){
		System.out.println("hello, world!");
		System.out.println("------------");



		double x1 = 3;
		double y1 = 4;
		double x2 = 7;
		double y2 = 1;
		Point p = new Point( x1, y1, x2, y2);
		System.out.println("Distance between two points: " + p.distance());
		System.out.println("------------");

	}





}
