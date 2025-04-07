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


extern objects obj[180] = {};

int detectObj(){

    int count = 0;
    int k = 0;
    int i = 0;
    int startInd;
    int endInd;
    int midPt;
    float convert = PI/180;

    for(i = 0; i < 180; i++){
        if( ((data[i].distance - data[i - 1].distance) > 100 || (data[i].distance - data[i - 1].distance) < -100) && (data[i].distance < 1850) && (data[i].distance > 1000)){ // start of obj detected

            startInd = i;

            while( ((data[i].distance - data[i - 1].distance) < 100 || (data[i].distance - data[i - 1].distance) > -100 ) && (data[i].distance < 1850) && (data[i].distance > 1000)){ // while the end index is not found
                i++;
                k++;
            }

            if(k > 12){
            endInd = i; // set end

            obj[count].botPosX = position[0]; // loading vals into struct array
            obj[count].botPosY = position[1];
            midPt = (endInd - startInd) / 2;

            int angWidth = endInd - startInd;




            //float y = (25060280.339 / pow(data[midPt + startInd].distance, 1.98));
            float y = 21000000 * pow(data[midPt + startInd].distance, -1.858);
            //float y = pow((8749*data[midPt + startInd].distance), (1/0.534));


            float tempVal = pow(y, 2) + pow(y, 2) - (2 * y * y * cos(convert*k));

            float width = sqrt(tempVal);

            obj[count].distance = y;
            obj[count].ang = midPt + startInd;
            obj[count].objPosX = objectXCalc(obj[count].distance, position[0], obj[count].ang);
            obj[count].objPosY = objectYCalc(obj[count].distance, position[1], obj[count].ang);
            obj[count].objID = count;
            obj[count].kCount = k;
            obj[count].width = width;

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


void printObj(int c){

    char header[100] = {"Bot Position \t Obj Position \t Distance \t Angle    ID    kCount     Width\n\r"};
    char temp[100];
    int i = 0;

    //uart_sendStr(header);

    for(i = 0; i < c; i++){
        sprintf(temp, "Bot Position: %.2f %.2f     Obj Pos: %.2f %.2f      Distance: %.2f      Angle: %d     ID: %d    kCount: %d  Width: %.2f\r\n", obj[i].botPosX, obj[i].botPosY, obj[i].objPosX, obj[i].objPosY, obj[i].distance, obj[i].ang, obj[i].objID, obj[i].kCount, obj[i].width);
        uart_sendStr(temp);
    }

}

void printObjAll(){

    char header[100] = {"Bot Position \t Obj Position \t Distance \t Angle  ID   kCount\n\r"};
    char temp[100];
    int i = 0;

    uart_sendStr(header);

    for(i = 0; i < 180; i++){
        sprintf(temp, "%.2f %.2f      %.2f %.2f       %.2f       %d      %d    %d\r\n", obj[i].botPosX, obj[i].botPosY, obj[i].objPosX, obj[i].objPosY, obj[i].distance, obj[i].ang, obj[i].objID, obj[i].kCount);
        uart_sendStr(temp);
    }

}


