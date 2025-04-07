
struct scanData;
struct object;

/*
 * Prints the data from the scanData struct array and sends it
 * over UART to be displayed in PUTTY
 */
void printData(struct scanData data[]);

/*
 * Prints out an array of objects and sends it over UART
 * to be displayed in PUTTY
 */
void printObjs(struct object objs[], int len);

