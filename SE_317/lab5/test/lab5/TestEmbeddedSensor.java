package lab5;

import java.util.Scanner;

public class TestEmbeddedSensor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        embeddedSensor sensor = new embeddedSensor();

        while (true) {
            System.out.println("Enter temperature (or type 'exit' to quit):");
            String tempInput = scanner.nextLine();
            if (tempInput.equalsIgnoreCase("exit")) {
                break;
            }
            int temp = Integer.parseInt(tempInput);

            System.out.println("Enter humidity (or type 'exit' to quit):");
            String humInput = scanner.nextLine();
            if (humInput.equalsIgnoreCase("exit")) {
                break;
            }
            int hum = Integer.parseInt(humInput);

            sensor.setTemp(temp);
            sensor.setHum(hum);
            sensor.getHumOutput();
            sensor.getTempOuput();
        }

        scanner.close();




        // Test 1: Setting initial values
        embeddedSensor sensor1 = new embeddedSensor(25, 50);
        sensor1.setTemp(30);
        sensor1.setHum(60);
        sensor1.getHumOutput();
        sensor1.getTempOuput();

        // Test 2: Setting new values
        sensor1.setTemp(35);
        sensor1.setHum(70);
        sensor1.getHumOutput();
        sensor1.getTempOuput();

        // Test 3: Setting extreme values
        sensor1.setTemp(150); // This should not change the current temperature due to bounds check
        sensor1.setHum(-10); // This should not change the current humidity due to bounds check
        sensor1.getHumOutput();
        sensor1.getTempOuput();

        // Resetting the sensor
        sensor1.reset();
        sensor1.getHumOutput();
        sensor1.getTempOuput();
    }
}
