//package lab5;
//
//
//import org.junit.Test;
//import static org.junit.Assert.assertEquals;
//
//public class TestEmbeddedPart2B {
//
//    // Style 1
//    @Test
//    public void testTemperatureSequence2() {
//        embeddedSensor sensor = new embeddedSensor(20, 20);
//        sensor.reset();
//
//        // Temperature sequence
//        int[] temperatures = {66, 68, 69, 67, 63, 59, 53};
//        // Arbitrary humidity values
//        int[] humidities = {50, 55, 60, 65, 70, 75, 80};
//
//        for (int i = 0; i < temperatures.length; i++) {
//            sensor.setTemp(temperatures[i]);
//            sensor.setHum(humidities[i]);
//        }
//
//        // Testing the trend after the sequence
//        assertEquals("Decreasing", sensor.getTempTrend());
//    }
//
//    // Style 2
//    @Test
//    public void testTemperatureSequence3() {
//        embeddedSensor sensor = new embeddedSensor(20, 20);
//        sensor.reset();
//
//        // Temperature sequence
//        int[] temperatures = {66, 68, 69, 67, 63, 59, 53};
//        // Arbitrary humidity values
//        int[] humidities = {50, 55, 60, 65, 70, 75, 80};
//
//        for (int i = 0; i < temperatures.length; i++) {
//            sensor.setTemp(temperatures[i]);
//            sensor.setHum(humidities[i]);
//
//            // Testing the trend after each input
//            if (i == 0) {
//                assertEquals("Increasing", sensor.getTempTrend());
//            } else if (i == 1) {
//                assertEquals("Increasing", sensor.getTempTrend());
//            } else if (i == 2) {
//                assertEquals("Increasing", sensor.getTempTrend());
//            } else if (i == 3) {
//                assertEquals("Decreasing", sensor.getTempTrend());
//            } else if (i == 4) {
//                assertEquals("Decreasing", sensor.getTempTrend());
//            } else if (i == 5) {
//                assertEquals("Decreasing", sensor.getTempTrend());
//            } else if (i == 6) {
//                assertEquals("Decreasing", sensor.getTempTrend());
//            }
//        }
//    }
//
//
//    // Hum
//
//    @Test
//    public void testHumiditySequence2() {
//        embeddedSensor sensor = new embeddedSensor();
//        sensor.reset();
//
//        // Humidity sequence
//        int[] humidities = {53, 51, 48, 49, 54, 56, 56};
//        // Arbitrary temperature values
//        int[] temperatures = {20, 21, 22, 23, 24, 25, 26};
//
//        for (int i = 0; i < humidities.length; i++) {
//            sensor.setTemp(temperatures[i]);
//            sensor.setHum(humidities[i]);
//        }
//
//        // Testing the trend after the sequence
//        assertEquals("Stable", sensor.getHumTrend());
//    }
//
//    @Test
//    public void testHumiditySequence3() {
//        embeddedSensor sensor = new embeddedSensor();
//        sensor.reset();
//
//        // Humidity sequence
//        int[] humidities = {53, 51, 48, 49, 54, 56, 56};
//        // Arbitrary temperature values
//        int[] temperatures = {20, 21, 22, 23, 24, 25, 26};
//
//        for (int i = 0; i < humidities.length; i++) {
//            sensor.setTemp(temperatures[i]);
//            sensor.setHum(humidities[i]);
//
//            // Testing the trend after each input
//            if (i == 0) {
//                assertEquals("Increasing", sensor.getHumTrend());
//            } else if (i == 1) {
//                assertEquals("Decreasing", sensor.getHumTrend());
//            } else if (i == 2) {
//                assertEquals("Decreasing", sensor.getHumTrend());
//            } else if (i == 3) {
//                assertEquals("Increasing", sensor.getHumTrend());
//            } else if (i == 4) {
//                assertEquals("Increasing", sensor.getHumTrend());
//            } else if (i == 5) {
//                assertEquals("Increasing", sensor.getHumTrend());
//            } else if (i == 6) {
//                assertEquals("Stable", sensor.getHumTrend());
//            }
//        }
//    }
//}
