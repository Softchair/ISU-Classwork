
/*
 * Inits the IR sensor
 * Send in a 1 for PING calibration
 */
void adc_init(int);

/*
 * Creates a line of best fit for the IR sensor to get accurate data - DONT CALL
 */
void calcBestFitLine(double adcValues[], double distances[], int n);

/*
 * Calculates the sum of an array
 */
double sum(double arr[], int n);



/*
 * Reads the raw data from the IR sensor
 */
int adc_read(void);

double getADCDistanceOne();

/*
 * Takes the average over a number of samples
 */
double avgAdc(int avgNum);

/*
 * Calculates the distance based on the line of best fit - MOST ACCURATE
 */
double getADCDistance();
