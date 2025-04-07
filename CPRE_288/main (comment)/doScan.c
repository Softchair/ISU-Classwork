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

// Stores the angle and distance of objects the bot picks up and also the position the cybot picked up the objects in a struct so it can be sent to the GUI. 

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
    float temp4;
    float temp5;
    float temp6;

    servo_move(0);
    timer_waitMillis(700);

    // Averages 6 raw IR readings at each angle so it can be more precise in calculating distance.

    for(i = 0; i < 180; i++){
        servo_move(i);
        timer_waitMillis(100);

        temp1 = adc_read();
        temp2 = adc_read();
        temp3 = adc_read();
        temp4 = adc_read();
        temp5 = adc_read();
        temp6 = adc_read();

        avg = (temp1 + temp2 + temp3 + temp4 + temp5 +temp6) / 6;
        
// Converts averaged IR readings to cm. We used Excel to calibrate the conversion to Cybot 5. The conversion is different for each bot.
        
        //float y = (25060280.339 / pow(avg, 1.98));
        //float y = 21000000 * pow(avg, -1.858);
        //float y = pow((8749*avg), (1/.534));
        float y = 80959 * pow(avg, -1.134);
        
//stores the average and position in a character array so it can be printed on the cybot and sent to a file to be read on our GUI.
        
        data[i].distance = avg;
        data[i].angle = i;
        data[i].posX = position[0];
        data[i].posY = position[1];

        sprintf(temp, "%.2f %d\n", y/100, data[i].angle);

        uart_sendStr(temp);
    }

    uart_sendStr("END\n");
}

// does a scan at a certain angle and gives its IR value. We used this mostly in our calibration to convert from IR data to distance in cm for the cybot.
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


