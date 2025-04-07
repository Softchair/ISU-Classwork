package lab5;

//public class embeddedSensor {
//
//    //Humidity
//    public int maxHum;
//    public int minHum;
//    public int lastHum;
//    public int curHum;
//
//
//    //Temp
//    public int maxTemp;
//    public int minTemp;
//    public int lastTemp;
//    public int curTemp;
//
//    // Constructor
//    public embeddedSensor(int temp, int hum) {
//        setHum(hum);
//        setTemp(temp);
//        minHum = hum;
//        minTemp = temp;
//    }
//
//    // Empty constructor
//    public embeddedSensor() {
//    }
//
//    public void setTemp(int newTemp) {
//        // Checking bounds
//        if (newTemp > 150 || newTemp < 0) {
//            return;
//        }
//        // Set min max
//        if (newTemp > maxTemp) {
//            maxTemp = newTemp;
//        }
//        if (newTemp < minTemp) {
//            minTemp = newTemp;
//        }
//        lastTemp = curTemp;
//        curTemp = newTemp;
//    }
//
//    public void setHum(int newHum) {
//        // Checking bounds
//        if (newHum > 100 || newHum < 0) {
//            return;
//        }
//        // Set min max
//        if (newHum > maxHum) {
//            maxHum = newHum;
//        }
//        if (newHum < minHum) {
//            minHum = newHum;
//        }
//        lastHum = curHum;
//        curHum = newHum;
//    }
//
//    public String getHumTrend() {
//        if (lastHum < curHum) {
//            return "Increasing";
//        } else if (lastHum > curHum) {
//            return "Decreasing";
//        } else {
//            return "Stable";
//        }
//    }
//
//    public String getHumStatus() {
//        if (curHum < 25) {
//            return "Low";
//        } else if (curHum > 25 && curHum < 55) {
//            return "OK";
//        } else {
//            return "High";
//        }
//    }
//
//    public void getHumOutput() {
//        System.out.println("Cur humidity: " + curHum + "%");
//        System.out.println("Max humidity: " + maxHum + "%");
//        System.out.println("Min humidity: " + minHum + "%");
//        System.out.println("Humidity trend: " + getHumTrend());
//        System.out.println("Humidity check: " + getHumStatus());
//    }
//
//    public String getTempTrend() {
//        if (lastTemp < curTemp) {
//            return "Increasing";
//        } else if (lastTemp > curTemp) {
//            return "Decreasing";
//        } else {
//            return "Stable";
//        }
//    }
//
//    public void getTempOuput() {
//        System.out.println("Cur temp: " + curTemp);
//        System.out.println("Max temp: " + maxTemp);
//        System.out.println("Min temp: " + minTemp);
//        System.out.println("Temp check: " + getTempTrend());
//    }
//
//    public void reset() {
//        curTemp = 0;
//        lastTemp = 0;
//        maxTemp = 0;
//        minTemp = 0;
//        curHum = 0;
//        lastHum = 0;
//        maxHum = 0;
//        minHum = 0;
//    }
//
//
//}

// Updated Version
public class embeddedSensor {

    // Humidity
    public int maxHum;
    public int minHum;
    public int lastHum;
    public int curHum;

    // Temperature
    public int maxTemp;
    public int minTemp;
    public int lastTemp;
    public int curTemp;

    // Constructor
    public embeddedSensor(int temp, int hum) {
        setValue(temp, 1); // Temperature
        setValue(hum, 2); // Humidity
        minHum = hum;
        minTemp = temp;
    }

    // Empty constructor
    public embeddedSensor() {
    }

    // Generic method for setting values
    public void setValue(int newValue, int type) {
        if (type == 1) { // Temperature
            if (newValue > 150 || newValue < 0) {
                return;
            }
            updateValues(newValue, maxTemp, minTemp, lastTemp, curTemp);
        } else if (type == 2) { // Humidity
            if (newValue > 100 || newValue < 0) {
                return;
            }
            updateValues(newValue, maxHum, minHum, lastHum, curHum);
        }
    }

    // Method to update values
    private void updateValues(int newValue, int max, int min, int last, int cur) {
        if (newValue > max) {
            max = newValue;
        }
        if (newValue < min) {
            min = newValue;
        }
        last = cur;
        cur = newValue;
    }

    // Trend calculation method
    public String getTrend(int last, int cur) {
        if (last < cur) {
            return "Increasing";
        } else if (last > cur) {
            return "Decreasing";
        } else {
            return "Stable";
        }
    }

    // Methods for outputting information
    public void getHumOutput() {
        System.out.println("Cur humidity: " + curHum + "%");
        System.out.println("Max humidity: " + maxHum + "%");
        System.out.println("Min humidity: " + minHum + "%");
        System.out.println("Humidity trend: " + getTrend(lastHum, curHum));
    }

    public void getTempOutput() {
        System.out.println("Cur temp: " + curTemp);
        System.out.println("Max temp: " + maxTemp);
        System.out.println("Min temp: " + minTemp);
        System.out.println("Temp trend: " + getTrend(lastTemp, curTemp));
    }

    // Method to reset all values
    public void reset() {
        curTemp = 0;
        lastTemp = 0;
        maxTemp = 0;
        minTemp = 0;
        curHum = 0;
        lastHum = 0;
        maxHum = 0;
        minHum = 0;
    }
}
