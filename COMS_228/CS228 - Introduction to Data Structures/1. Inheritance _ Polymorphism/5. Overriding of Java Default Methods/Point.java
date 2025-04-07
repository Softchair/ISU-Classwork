package pointClone;



public class Point implements Cloneable
{ 
	private int x;
	private int y;
  
	public Point()
	{
		x =0 ; y= 0;// x and y get default value 0
	}
  
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
  
	// Copy constructor
	public Point(Point existing)
	{
		this.x = existing.x;
		this.y = existing.y;
	}
  
	public int getX()
	{
		return x;
	}
  
	public int getY()
	{
		return y;
	}
  
	public double distance(Point other)
	{
		double dx = x - other.x;
		double dy = y - other.y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || obj.getClass() != this.getClass())
		{
			return false;
		}
    
		Point other = (Point) obj;
		return x == other.x && y == other.y;   
	}

	@Override
	public Object clone()
	{
		Point copy = null;
		try
		{
			// Object.clone() creates a field-by-field copy
			copy = (Point) super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			// Can't happen
		}
		return copy;
	}
  
	public Point makeClone()
	{
		Point copy = new Point();
		copy.x = this.x;
		copy.y = this.y;
		return copy;
	}
}

