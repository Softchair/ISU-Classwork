

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
    char objSend[100] = {};
    char dataSend[100] = {};
    int prevObjCount = 0;
    char lastChar;

    while (uart_data != '\n') {} // polling while waiting for GUI to connect 

    while(1){

        lcd_printf(temp);

        lastChar = uart_data;

        if(lastChar == 'w'){ // Move Forward
            move(45,50,sensor_data);
            sprintf(temp, "X: %.2f cm, Y: %.2f cm \r\n", position[0], position[1]);
            uart_sendStr(temp);
            lastChar = 'x';
        } else if(lastChar == 'd'){ // Turn Right
            turn90(RIGHT, sensor_data);
            sprintf(temp, "X: %.2f cm, Y: %.2f cm \r\n", position[0], position[1]);
            uart_sendStr(temp);
            lastChar = 'x';
        } else if(lastChar == 'a'){ // Turn Left
            turn90(LEFT, sensor_data);
            sprintf(temp, "X: %.2f cm, Y: %.2f cm \r\n", position[0], position[1]);
            uart_sendStr(temp);
            lastChar = 'x';
        } else if(lastChar == 's'){ // Move Backwards
            move(45, -50, sensor_data);
            sprintf(temp, "X: %.2f cm, Y: %.2f cm \r\n", position[0], position[1]);
            uart_sendStr(temp);
            lastChar = 'x';
        } else if(lastChar == 'x'){ // Stops wheels
            oi_setWheels(0, 0);
        } else if(lastChar == 'm'){ // Scan 180
            doScan();
            lastChar = 'x';
        } else if(lastChar == 'p'){ // Sort collected data into objects and send to GUI.
            int c = detectObj();
            printObj(c);
            lastChar = 'x';
        } else if(lastChar == 'z'){ // Oi Free
            oi_free(sensor_data);
        } 


    }
}



