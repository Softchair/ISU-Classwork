/*
 * Code for the IR sensor
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <timer.h>
#include <inc/tm4c123gh6pm.h>
#include <PING))).h>
#include <adc.h>

/*
 * Values to hold the data for calibration
 */
double m;
double c;

/*
 * Init the adc/IR sensor
 * Send in a 1 for adc calibration
 * Assumes that the PING))) sensor is initiated
 */
void adc_init(int a) {
        SYSCTL_RCGCGPIO_R |= (1<<1);   /* Enable Clock to GPIOB or AN0 */
        SYSCTL_RCGCADC_R |= (1<<0);    /* AD0 clock enable*/

        GPIO_PORTB_AFSEL_R |= (1<<4);       /* Enable alternate function */
        GPIO_PORTB_DEN_R &= ~(1<<4);        /* Disable digital function */
        GPIO_PORTB_AMSEL_R |= (1<<4);       /* Enable analog function */
        GPIO_PORTB_DIR_R &= ~(0<<4);         /* Set pin B4 to input */

        ADC0_ACTSS_R &= ~(1<<1);        /* disable SS1 during configuration */
        ADC0_EMUX_R &= 0xFF0F;          /* software trigger conversion */
        ADC0_SSMUX1_R = 0xA;         /* get input from channel 0 */
        ADC0_SSCTL1_R |= (1<<1)|(1<<2);        /* take one sample at a time, set flag at 1st sample */
        ADC0_ACTSS_R |= (1<<1);         /* Enable ADC0 sequencer 1 */

        if (a == 1) {
            double adcValues[5];
            double pingValues[5];

            int i = 0;
            for (i = 0; i < 5; i++) {
                adcValues[i] = adc_read();
                ping_trigger();
                pingValues[i] = ping_getDistance();
                timer_waitMillis(500);
            }

            calcBestFitLine(adcValues, pingValues, 5);
        }
}

//-------------------------- START INIT FUNCTIONS --------------------------

/*
 * Creates a line of best fit for the IR sensor to get accurate data - DONT CALL
 */
void calcBestFitLine(double adcValues[], double distances[], int n) {
    double m1, c1;

    // Calculate the slope (m) and y-intercept (c)
    m1 = (n * sum(adcValues, n) * sum(distances, n) - sum(adcValues, n) * sum(distances, n)) / (n * sum(adcValues, n) * sum(adcValues, n) - sum(adcValues, n) * sum(adcValues, n));
    c1 = (sum(distances, n) - m1 * sum(adcValues, n)) / n;

    m = m1;
    c = c1;
}

/*
 * Calculates the sum of an array
 */
double sum(double arr[], int n) {
    double s = 0;

    int i;
    for (i = 0; i < n; i++)
        s += arr[i];
    return s;
}
//-------------------------- END INIT FUNCTIONS --------------------------

/*
 * Reads the raw data from the IR sensor
 */
int adc_read(void) {
    unsigned int adc_value;

    ADC0_PSSI_R |= (1<<1);        /* Enable SS1 conversion or start sampling data from AIN10 */
    while((ADC0_ACTSS_R & (1<<1)) == 0){};   /* Wait until conversion has started */
    //while((ADC0_RIS_R & 8) == 0){};   /* Wait until sample conversion completed */

    adc_value = ADC0_SSFIFO1_R; /* Read ADC conversion result from SS1 FIFO */

    ADC0_ISC_R = 8;          /* Clear conversion clear flag bit */

    return adc_value;
}

/*
 * Calculates the distance based on the line of best fit - MOST ACCURATE
 */
double getADCDistance() {
    //Gets an average ADC value
    double avgAdcVal = avgAdc(8);

    //Find cm distance
    double estimatedDistance = (m * avgAdcVal + c);

    //Uses line of best fit
    return estimatedDistance;
}

double getADCDistanceOne() {
    double estimatedDistance = (m * adc_read() + c);

    return estimatedDistance;
}

/*
 * Takes the average over a number of samples
 */
double avgAdc(int avgNum) {
    int* data = calloc(avgNum, sizeof(int));

    //Takes the number of samples in to average
    int i = 0;
    for (i = 0; i < avgNum; i++) {
        data[i] = adc_read();
    }

    double sum = 0;
    for (i = 0; i < avgNum; i++) {
        sum += data[i];
    }

    free(data);

    //Return the average of all the data
    return sum/avgNum;
}

////Builds and displays data graphically
//void displayGrahicalData(int data[], sizeof(data)/sizeof(int)) {
//    // Find the maximum value in the data
//        int max = data[0];
//        for (int i = 1; i < size; i++) {
//            if (data[i] > max) {
//                max = data[i];
//            }
//        }
//
//        // Generate the bar graph
//        for (int i = 0; i < size; i++) {
//            // Calculate the height of the bar
//            int height = (data[i] * 10) / max;
//
//            // Print the bar
//            printf("%d: ", data[i]); //todo update this so it uses uart
//            for (int j = 0; j < height; j++) {
//                printf("*"); //todo update this so it uses uart
//            }
//            printf("\n"); //todo update this so it uses uart
//        }
//}
//
