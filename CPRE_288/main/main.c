

/**
 * main.c
 */
#include "open_interface.h"
#include "movement.h"
#include "timer.h"
#include "lcd.h"
#include "cyBot_uart.h"
#include "uart.h"
#include "adc.h"
#include "PING))).h"
#include "objectScan.h"
#include "doScan.h"
#include "servo.h"
#include "button.h"
#include <stdio.h>
#include <stdlib.h>
#include"objDetect.h"

//struct scanData {
//    int degree;
//    float distance;
//};
//
//struct object {
//    int startDeg;
//    int endDeg;
//    float distance;
//    float width; //endDeg-startDeg
//};

void main() {



    timer_init();
    lcd_init();
    uart_init(115200);
    uart_interrupt_init();
    adc_init();
    servo_init();

    oi_t *sensor_data = oi_alloc();

    oi_init(sensor_data);
    char ang[10] = {};
    char temp[100];
    int i;
    int intValue = 0;

    uart_sendStr("GO\n");

//    move(50, 200, sensor_data);
//    turn90(LEFT, sensor_data);
//    move(50, 200, sensor_data);

    while(1){

// cybot 9 left wheel faster


        if(uart_data == 'w'){
            uart_sendStr(temp);

            uart_sendStr("Enter distance in cm: ");
            for(i = 0; i < 10; i++){
                ang[i] = uart_receive();

                uart_sendChar(ang[i]);
                if(ang[i] == '\r'){

                     break;
                }
            }
            uart_sendStr(ang);
            intValue = atoi(ang);

            intValue *= 10;

            move(100,intValue,sensor_data);
            uart_data = 'x';

        } else if(uart_data == 'd'){
            turn90(RIGHT, sensor_data);
            uart_sendStr(temp);
            uart_data = 'x';
        } else if(uart_data == 'a'){
            turn90(LEFT, sensor_data);
            uart_sendStr(temp);
            uart_data = 'x';
        } else if(uart_data == 's'){
            move(50, -50, sensor_data);
            uart_sendStr(temp);
            uart_data = 'x';
        } else if(uart_data == 'x'){
            oi_setWheels(0, 0);
        } else if(uart_data == 'm'){
            doScan();
            uart_data = 'x';
        } else if(uart_data == 'o'){
            uart_sendStr("Enter angle: ");
            for(i = 0; i < 10; i++){
                ang[i] = uart_receive();

                uart_sendChar(ang[i]);
                if(ang[i] == '\r'){

                    break;
                }
            }
            uart_sendStr(ang);

            intValue = atoi(ang);

            doScanPoint(intValue);
            uart_data = 'x';
        } else if(uart_data == 'p'){
            int c = detectObj();
            printObj(c);
            uart_data = 'x';
        } else if(uart_data == 'z'){
            oi_free(sensor_data);
        }

        sprintf(temp, "X: %.2f cm, Y: %.2f cm \r\n", position[0], position[1]);

        lcd_printf(temp);


    }





return;
}



