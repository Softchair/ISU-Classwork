//void main(void)
//{
//    lcd_init();
//    timer_init();
//
//    adc_init();
//
//
//    //Need to Enable ADC Interrupt
//
//
//    //need to get IR values
//    float voltage;
//    unsigned int adc_value;
//    float distcm;
//    //float avgdist[10];
//    int i;
//    float avgvolt = 0.000;
//
//    while(1)
//    {
//
//
//        for(i=0;i<9;i++){
//            adc_value = adc_read();
//
//            /* Convert digital value back into voltage */
//            voltage = (adc_value * 0.0008);
//
//            avgvolt = avgvolt + voltage;
//            timer_waitMillis(50);
//        }
//
//        avgvolt = avgvolt / 10;
//
//        distcm = (230.274*pow(2.7182818,-(avgvolt*1.95896))+0.5);
//
//        lcd_printf("A = %d \nV = %f \ncm = %f", adc_value, avgvolt, distcm);
//
//        timer_waitMillis(500);
//
//    }

//}





#include "cyBot_uart.h"
#include "lcd.h"
#include "Timer.h"
#include "cyBot_Scan.h"
#include "open_interface.h"
#include "movement.h"
#include <stdbool.h>
#include <stdint.h>
#include <stdbool.h>
#include <inc/tm4c123gh6pm.h>
#include "driverlib/interrupt.h"
#include <math.h>
#include <uart.h>
#include "movement.h"
#include "adc.h"


//PART 3
typedef struct{
    int firstAngle;
    int lastAngle;
    int width;
    int objects;
    int distance;
    int IR_raw_val;
} object_t;

int main(void)
{

    lcd_init();
    timer_init();
    oi_t *sensor_data = oi_alloc();
    oi_init(sensor_data);
    uart_init(115200);
    cyBOT_init_Scan(0b0111);

    char t ;
    char c ;
    char buffer[100];
    char data[100];
    char smallObj[100];
    int width = 0;
    int currentObject = 0;
    int object = 0;
    int distance = 0;
    int loop = 0;
    //cyBOT_SERVO_cal();
    cyBot_uart_init();
    object_t objectsFound[10];


    adc_init();
    unsigned int adc_value;

    while (1)
    {

    c = uart_receive();
    printf("%c", c);
    lcd_putc(c);
    uart_sendChar(c);
        // clear lcd
        if(c == '-'){
            lcd_clear();
        }
        if(t == '-'){
            lcd_clear();
        }
// Object detection
    cyBOT_Scan_t scan;
    int ang = 0;
    int i = 0;
    int j = 0;
    int avgIR=0;


    while (ang < 180){
        cyBOT_Scan(ang, &scan);

        for(i=0;i<3;i++){
            adc_value = adc_read();
            timer_waitMillis(10);
            avgIR += adc_value;
        }

        avgIR = avgIR/4;
        ang++;
        ang++;
        sprintf(buffer, "Angle: %d     Distance: %f    IR_val: %d\n\r",ang, scan.sound_dist, adc_value);
        for (i = 0; i < strlen(buffer); i++){
            uart_sendChar(buffer[i]);
        }
       uart_sendChar('\n');
       if(adc_value > 950){
           if(width < 1){
               objectsFound[currentObject].firstAngle = ang;
           }
           width++;
           width++;
           printf("%d\n", width);
       }
       if(width > 3 && adc_value < 850){
           object++;
           objectsFound[currentObject].width = width;
           objectsFound[currentObject].lastAngle = ang;
           objectsFound[currentObject].objects = object;
           distance = scan.sound_dist;
//           IR_raw_val = adc_value;
           objectsFound[currentObject].distance = distance;
           objectsFound[currentObject].IR_raw_val = adc_value;
           currentObject++;
           width = 0;
       }
    }

    for(loop = 0; loop < object; loop++) {
          sprintf(data, "Objects #: %d   Start_angle: %d   Distance: %d    Width: %d\n\r", objectsFound[loop].objects, objectsFound[loop].firstAngle, objectsFound[loop].distance, objectsFound[loop].width);
          for (j = 0; j < strlen(data); j++){
                          uart_sendChar(data[j]);
                      }
    }

// End Object Detection
// smallest obj detection and turn angle calculation
    int p = 0;
    int h = 0;
    int SmallObjWidth = 100;
    int SmallestObj = 0;
    int turnAng = 0;
    int startAng = 0;
    for (p = 0; p < object; p++){
        if (objectsFound[p].width < SmallObjWidth){
           SmallObjWidth = objectsFound[p].width;
        }
        if (SmallObjWidth == objectsFound[p].width){
            startAng = objectsFound[p].firstAngle;
            SmallestObj = objectsFound[p].objects;
        }
        p++;
    }

    turnAng = (SmallObjWidth/2) + startAng;

    sprintf(smallObj, "Smallest object width = %d, on object # %d   turn to %d degrees\n\r", SmallObjWidth, SmallestObj, turnAng);
             for (h = 0; h < strlen(smallObj); h++){
                             uart_sendChar(smallObj[h]);
             }
// END smallest obj detection and turn angle calculation

// Make bot turn to small object
    if (turnAng < 90){
        turn(turnAng, sensor_data);
    }
    else if (turnAng > 90){
        turn(-turnAng, sensor_data);
    }
// end turn to small object

// drive to small object
    int driveDist = 0;
    int smallObjDist = 0;
    //determine distance to drive forward
    smallObjDist = objectsFound[SmallestObj].distance;
    // Calibrate distance to stop before hitting obj --- depends on bot-> SET FOR BOT 12
    driveDist = (smallObjDist*10)-150;
    //drive to to object
    move(200, driveDist, sensor_data);

    return 0;

    }// end while(1) loop
}



