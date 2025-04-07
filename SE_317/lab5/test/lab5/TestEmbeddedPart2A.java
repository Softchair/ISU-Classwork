package lab5;


import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestEmbeddedPart2A {

    @Test
    public void testTemperatureTrendIncreasing() {
        embeddedSensor sensor = new embeddedSensor(20, 50);
        sensor.setTemp(25);
        assertEquals("Increasing", sensor.getTempTrend());
    }

    @Test
    public void testTemperatureTrendDecreasing() {
        embeddedSensor sensor = new embeddedSensor(25, 50);
        sensor.setTemp(20);
        assertEquals("Decreasing", sensor.getTempTrend());
    }

    @Test
    public void testTemperatureTrendStable() {
        embeddedSensor sensor = new embeddedSensor(25, 50);
        sensor.setTemp(25);
        assertEquals("Stable", sensor.getTempTrend());
    }

    @Test
    public void testHumidityTrendIncreasing() {
        embeddedSensor sensor = new embeddedSensor(25, 50);
        sensor.setHum(60);
        assertEquals("Increasing", sensor.getHumTrend());
    }

    @Test
    public void testHumidityTrendDecreasing() {
        embeddedSensor sensor = new embeddedSensor(25, 60);
        sensor.setHum(50);
        assertEquals("Decreasing", sensor.getHumTrend());
    }

    @Test
    public void testHumidityTrendStable() {
        embeddedSensor sensor = new embeddedSensor(25, 50);
        sensor.setHum(50);
        assertEquals("Stable", sensor.getHumTrend());
    }

    @Test
    public void testMaxTemperatureUpdate() {
        embeddedSensor sensor = new embeddedSensor(20, 50);
        sensor.setTemp(25);
        assertEquals(25, sensor.maxTemp);
    }

    @Test
    public void testMinTemperatureUpdate() {
        embeddedSensor sensor = new embeddedSensor(25, 50);
        sensor.setTemp(20);
        assertEquals(20, sensor.minTemp);
    }

    @Test
    public void testMaxHumidityUpdate() {
        embeddedSensor sensor = new embeddedSensor(25, 50);
        sensor.setHum(60);
        assertEquals(60, sensor.maxHum);
    }

    @Test
    public void testMinHumidityUpdate() {
        embeddedSensor sensor = new embeddedSensor(25, 60);
        sensor.setHum(50);
        assertEquals(50, sensor.minHum);
    }
}
