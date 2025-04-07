/*
 * objDetect.c
 *
 *  Created on: Dec 5, 2023
 *      Author: bddunne
 */

#include "movement.h"
#include "trig.h"
#include "doScan.h"
#include "objDetect.h"
#include "uart.h"
#include <Math.h>


#define PI 3.14159265


extern objects obj[180] = {}; // Data Struct that stores all data associated with each object

int detectObj(){

    int count = 0;
    int k = 0;
    int i = 0;
    int startInd;
    int endInd;
    int midPt;
    float convert = PI/180;
    // Begin iterating through data
    for(i = 0; i < 180; i++){
        // If the edge of an object is detected, the start index is saved and a count is kept of the angular width of the object
        if( ((data[i].distance - data[i - 1].distance) > 180 || (data[i].distance - data[i - 1].distance) < -180) && (data[i].distance < 2700) && (data[i].distance > 400)){ // start of obj detected

            startInd = i;

            while( ((data[i].distance - data[i - 1].distance) < 260 || (data[i].distance - data[i - 1].distance) > -260 ) && (data[i].distance < 2700) && (data[i].distance > 400)){ // while the end index is not found
                i++;
                k++;
            }
            //If the angular width is more than 8 this is most likely a real object
            if(k > 8){
            endInd = i; // set end index

            obj[count].botPosX = position[0]; // loading position vals into struct array
            obj[count].botPosY = position[1];
            midPt = (endInd - startInd) / 2;
            
            // Conversion from Raw IR to cm + width calculations
            float y = 80959 * pow(data[midPt + startInd].distance, -1.134);
            float tempVal = pow(y, 2) + pow(y, 2) - (2 * y * y * cos(convert*k));
            float width = sqrt(tempVal);

            // Loading all associated object values into a struct array to send the data to the GUI
            obj[count].distance = y;
            obj[count].ang = midPt + startInd;
            obj[count].objPosX = objectXCalc(obj[count].distance, position[0], obj[count].ang);
            obj[count].objPosY = objectYCalc(obj[count].distance, position[1], obj[count].ang);
            obj[count].objID = count;
            obj[count].kCount = k;
            obj[count].width = width;
            obj[count].objID = count;

            count++;
            k = 0;
            } else{
                k = 0;
                continue;

            }
        }
    }
    return count;
}


void printObj(int c){ // Sends all discovered objects to thee GUI

    char temp[100];
    int i = 0;
    
    for(i = 0; i < c; i++){
        sprintf(temp, "Obj ID: %d, Obj X: %.2f, Obj Y: %.2f, Bot X: %.2f, Bot Y: %.2f, Distance: %.2f, Angle: %d, Width: %.2f\n", obj[i].objID, obj[i].objPosX, obj[i].objPosY, obj[i].botPosX, obj[i].botPosY, obj[i].distance, obj[i].ang, obj[i].width);
        uart_sendStr(temp);
    }

    uart_sendStr("END\n");

}

