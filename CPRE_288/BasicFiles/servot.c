#include "servo.h"


void servo_init(void) {
    //enable clock to GPIO port B
    SYSCTL_RCGCGPIO_R |= 0x02;
    //enable clock for timer 1B
    SYSCTL_RCGCTIMER_R |= 0x02;
    while ((SYSCTL_RCGCTIMER_R & 0x02) == 0) {}
    //TURN ON PB5
    GPIO_PORTB_DEN_R |= 0x20;
    GPIO_PORTB_DIR_R |= 0x20;
    //configure PB5 as T1B
    GPIO_PORTB_AFSEL_R |= 0x20;
    GPIO_PORTB_PCTL_R &= ~0xF00000;
    GPIO_PORTB_PCTL_R |= 0x700000;

    //disable timer
    TIMER1_CTL_R &= ~0x100;
    TIMER1_CFG_R |= 0x00000004;
    //bits tbams to 0x1, tbcmr to 0x0, field tbmr to 0x2
    TIMER1_TBMR_R &= ~0x1F;
    TIMER1_TBMR_R |= 0xA;
    //we are not inverting the output
    TIMER1_CTL_R &= ~0x4000;
    //loading the prescaler and main timer start values
    TIMER1_TBPR_R = 0x04;
    TIMER1_TBILR_R = 0xE200;

    //Fill the match register to go to 90 degrees
    TIMER1_TBMATCHR_R = 0x8440;
    TIMER1_TBPMR_R = 0x04;
    //enable timer
    TIMER1_CTL_R |= 0x100;

}

void Set_Right_And_Left(int Right_calibration_value_0,int Left_calibration_value_180){

  //  if(){
        right_calibration_value_0 = Right_calibration_value_0;
  //  }

  //  if(){
        left_calibration_value_180 = Left_calibration_value_180;
  //  }

}

void cyBOT_SERVO_cal(){

    int Right_calibration_value_0 = 0;
    int Left_calibration_value_180 = 0;

    int numCycles = 304000;

    lcd_printf("Button 1 to move left, Button 2 to move right and Button 3 to submit\n Right calibration value set 0");

    // For calibrating the right side
    while( button_getButton() != 3){

        lcd_printf("Button 1 to move left, Button 2 to move right and Button 3 %d", numCycles);

        if(button_getButton() == 1){

            numCycles += 100;

        }else if(button_getButton() == 2){

            numCycles -= 100;

        }

        TIMER1_CTL_R &= ~0x100;
        TIMER1_TBMATCHR_R = numCycles & 0xFFFF; //last 4 hex
        TIMER1_TBPMR_R = (numCycles & 0xFF0000) >> 16; //first two hex
        TIMER1_CTL_R |= 0x100;

    }

    Right_calibration_value_0 = numCycles;

    lcd_printf("Button 1 to move left, Button 2 to move right and Button 4 to submit/n Left calibration value 180");
    // For calibrating the left side
    while( button_getButton() != 4){

        lcd_printf("Button 1 to move left, Button 2 to move right and Button 4 to submit/n %d", numCycles);

        if(button_getButton() == 1){

            numCycles += 100;

        }else if(button_getButton() == 2){

            numCycles -= 100;

        }

        TIMER1_CTL_R &= ~0x100;
        TIMER1_TBMATCHR_R = numCycles & 0xFFFF; //last 4 hex
        TIMER1_TBPMR_R = (numCycles & 0xFF0000) >> 16; //first two hex
        TIMER1_CTL_R |= 0x100;

    }

    Left_calibration_value_180 = numCycles;

    char Send_String[] = " jhkajhkfgajhklfgjhkgjhkjhkl";

        sprintf(Send_String,"Left %d\n\rRight %d\n\r",Left_calibration_value_180,Right_calibration_value_0);

        uart_sendStr(Send_String);

    Set_Right_And_Left( Right_calibration_value_0, Left_calibration_value_180);
}

void servo_move(uint16_t degrees) {
   // long numCycles = 304000 - ((degrees / 180.0)/(0.0000625));
  if(degrees>=0 && degrees<=180){
      long numCycles = right_calibration_value_0 + (degrees * ((left_calibration_value_180-right_calibration_value_0)/180.0));
    //load numCycles into the match registers
    TIMER1_CTL_R &= ~0x100;
    TIMER1_TBMATCHR_R = numCycles & 0xFFFF; //last 4 hex
    TIMER1_TBPMR_R = (numCycles & 0xFF0000) >> 16; //first two hex
    TIMER1_CTL_R |= 0x100;
  }
}
