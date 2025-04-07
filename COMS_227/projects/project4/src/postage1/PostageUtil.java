package postage1;
import java.util.Scanner;

public class PostageUtil
{

  public static void main(String[] args) {
	  int weight;
	  Scanner sc = new Scanner(System.in);
	  System.out.println("Enter weight: ");
	  weight = sc.nextInt();
	  System.out.println("Postage cost: " + computePostage(weight));
	  sc.close();
  }
  
  
  /**
   * Returns the cost of postage for a letter of the given weight.
   * @param weight
   *   given weight in ounces
   * @return
   *   cost of postage for the weight
   */
  public static double computePostage(double weight)
  {
	double cost = 0;
    if (weight <= 1) {
    	cost =.47;
    } else if (weight <= 3.5) {
    	cost = .47 + Math.ceil(weight - 1) * .21;
    } else {
    	cost = .94 + Math.ceil(weight - 1) * .21;
    }
    return cost;
  }
}