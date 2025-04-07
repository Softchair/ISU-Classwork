package lab2;

/**
 * Try out the Basketball class.
 */
public class BasketballTest
{
  /**
   * Entry point.
   */
  public static void main(String[] args)
  {
	  //Construct basketball and look attributes
	  Basketball b;
	  b = new Basketball(4.0);
	  System.out.println("Diameter of b: " + b.getDiameter());
      System.out.println("Is b dribbleable? " + b.isDribbleable());
	  
      //New basketball with diameter of 6
      Basketball b2 = new Basketball(6.0);
    
      // Inflate the first one
      b.inflate();
      
      b.deflate();
      
      // First one is now dribbleable
      System.out.println("First basketball dribbleable? " + b.isDribbleable());
    
      // Second one is unchanged
      System.out.println("Second basketball dribbleable?  " + b2.isDribbleable());
    
  }
}