#ifndef SERVO_H_
#define SERVO_H_

#include <stdint.h>
#include <inc/tm4c123gh6pm.h>
#include "Timer.h"
#include "button.h"
#include "lcd.h"

void servo_init(void);
void servo_move(uint16_t degrees);

void cyBOT_SERVO_cal(void);

void Set_Right_And_Left(int Right_calibration_value_0,int Left_calibration_value_180);

 int right_calibration_value_0;// = 304000; // sets a default right
 int left_calibration_value_180;// = 288000;

#endif /* SERVO_H_ */
