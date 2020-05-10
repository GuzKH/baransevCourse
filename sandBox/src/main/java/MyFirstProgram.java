public class MyFirstProgram{

	public static void main(String[] args){
		System.out.println("hello, world!");
		System.out.println("------------");

		Point p1 = new Point(3,4);
		Point p2 = new Point(7,1);

		System.out.println("Distance between two points: " + p1.distance(p2));
		System.out.println("------------");
	}

}
