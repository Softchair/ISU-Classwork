/*
 * doScan.c
 *
 *  Created on: Nov 29, 2023
 *      Author: bddunne
 */

#include "servo.h"
#include "adc.h"
#include "lcd.h"
#include "uart.h"
#include "movement.h"
#include <Math.h>

typedef struct{
    int angle;
    float distance;
    float posX;
    float posY;
}scanData;

extern volatile scanData data[180] = {};

void doScan()
{

    char temp[100];
    int i;

    float avg;
    float temp1;
    float temp2;
    float temp3;

    servo_move(0);
    timer_waitMillis(700);


    for(i = 0; i < 180; i++){
        servo_move(i);
        timer_waitMillis(100);

        temp1 = adc_read();
        temp2 = adc_read();
        temp3 = adc_read();

        avg = (temp1 + temp2 + temp3) / 3;

        //float y = (25060280.339 / pow(avg, 1.98));
        float y = 21000000 * pow(avg, -1.858);
        //float y = pow((8749*avg), (1/.534));

        data[i].distance = avg;
        data[i].angle = i;
        data[i].posX = position[0];
        data[i].posY = position[1];

        sprintf(temp, "Distance: %.2f Angle: %d \r\n ", y, data[i].angle);

        uart_sendStr(temp);
    }
return;
}


void doScanPoint(int ang)
{


    char temp[100];
    servo_move(ang);

    timer_waitMillis(700);

    float dist = adc_read();
    int angle = ang;

    sprintf(temp, "Distance: %.2f Angle: %d \r\n ", dist, angle);

    uart_sendStr(temp);

}


