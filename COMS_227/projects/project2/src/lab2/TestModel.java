package lab2;

public class TestModel
{
  public static void main(String[] args)
  {
    RabbitModel model = new RabbitModel();

    // A year goes by...
    model.simulateYear();
    System.out.println(model.getPopulation());
    System.out.println("Expected 1");
    
    //2 years
    model.simulateYear();
    System.out.println(model.getPopulation());
    System.out.println("Expected 2");
    
    //3 years
    model.simulateYear();
    System.out.println(model.getPopulation());
    System.out.println("Expected 3");
    model.simulateYear();
    System.out.println(model.getPopulation());
    System.out.println("Expected 4");
    model.simulateYear();
    System.out.println(model.getPopulation());
    System.out.println("Expected 5");
    model.simulateYear();
    System.out.println(model.getPopulation());
    System.out.println("Expected 0");
    model.simulateYear();
    System.out.println(model.getPopulation());
    System.out.println("Expected 1");
  }
}
