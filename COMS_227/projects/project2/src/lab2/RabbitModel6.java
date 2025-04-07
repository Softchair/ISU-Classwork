package lab2;

/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModel6
{
  // TODO - add instance variables as needed
  
	private int population = 1;
	private int lastYear;
	private int yearBefore;

  /**
   * Constructs a new RabbitModel.
   */
  public RabbitModel6()
  {
    // TODO
  }  
 
  /**
   * Returns the current number of rabbits.
   * @return
   *   current rabbit population
   */
  public int getPopulation()
  {
    return population;
  }
  
  /**
   * Updates the population to simulate the
   * passing of one year.
   */
  public void simulateYear()
  {
	
	yearBefore = lastYear + population;

	lastYear = population;
	
    population = yearBefore;
    
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
    population = 1;
    lastYear = 0;
	yearBefore = 0;
  }
}
