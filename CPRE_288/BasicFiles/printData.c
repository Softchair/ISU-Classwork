/*
 * File for holding functions related to printing data
 */

#include <stdio.h>
#include <String.h>
#include <cyBot_uart.h>
#include <lcd.h>
#include <printData.h>

/*
 * Structs used in this file
 */

struct scanData {
    int degree;
    float distance;
};

struct object {
    int startDeg;
    int endDeg;
    float distance;
    float width; //endDeg-startDeg
};

//Function to print basic scan data - IR sensor
void printData(struct scanData data[]) {
    int i;
    int j;


    //Print out the intro
    char intro[] = "Degree \t\t Distance (cm)\r\n";
    for(j = 0; j < strlen(intro); j++){
        cyBot_sendByte(intro[j]);
    }

    //Iterate over the array and build strings, then print
    for(i = 0; i < 90; i++) {
        //printf("%d \t %0.0f \r\n", data[i].degree, data[i].distance);
        char curLine[30];
        sprintf(curLine, "%d \t\t\t %0.0f \r\n", data[i].degree+1, data[i].distance);

        //Prints the string one byte at a time
        int j;
        for(j = 0; j < strlen(curLine); j++){
            cyBot_sendByte(curLine[j]);
        }
    }
}

//Prints the num of objects as well as information about them
void printObjs(struct object objs[], int len) {
    int i;
    int j;

    //Print out the intro
    char intro[] = "Object# \t Angle \t \t Distance \t Width\r\n";
    for(j = 0; j < strlen(intro); j++) {
        cyBot_sendByte(intro[j]);
    }

    //Iterates over the array to build strings, and then print
    for(i = 0; i < len; i++) {
        char curLine[50];
        sprintf(curLine, "%d \t\t %d \t \t %0.0f \t\t %0.0f \r\n", i+1, objs[i].startDeg, objs[i].distance, objs[i].width);

        //Prints the string one byte at a time
        for(j = 0; j < strlen(curLine); j++){
            cyBot_sendByte(curLine[j]);
        }
    }
}
