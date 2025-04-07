package point;

public class Point {
	
	private int x; // instance variables are typically private
	private int y;
	
	public Point()  // default constructor
	{
		// x and y get default value 0
	}
	
	public Point(int x, int second)
	{
		this.x = x;  // this.x is an instance variable
		y = second;  // x is a local variable 
	}
	
	public int getX()  // accessor 
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void putX(int x) // mutator
	{
		this.x = x; 
	}
	
	public double distance(Point other)
	{
		double dx = x - other.x;  // dx and dy are local variables
		double dy = y - other.y;
		return Math.sqrt(dx * dx + dy * dy); 		
	}	
}
