package lab4a;

import java.awt.Point;

public class Rectangle {
	
	private Point origin;
	   private Point opposite;

	   public Rectangle(Point origin, Point oppositeCorner) {
	      this.origin = origin;
	      this.opposite = oppositeCorner;
	   }

	   public Rectangle(Point origin) {
	      this.opposite = origin;
		  this.origin = origin;
	   }

	   public int area() throws Exception {
		   int xSide = Math.abs(origin.x - opposite.x);
		   int ySide = Math.abs(origin.y - opposite.y);
		   if (xSide > 100 || ySide > 100) {
			   throw new Exception();
		   } else {
			   return xSide * ySide;
		   }
	   }

	   public void setOppositeCorner(Point opposite) {
	      this.opposite = opposite;
	   }

	   public Point origin() {
	      return origin;
	   }

	   public Point opposite() {
	      return opposite;
	   }
	   
	   @Override
	   public String toString() {
	      return "Rectangle(origin " + origin + " opposite " + opposite + ")";
	   }

}
