#include "servo.h"
#include "Timer.h"
#include "lcd.h"

unsigned long pwm_period = 0x4E200;

void servo_init() {

    SYSCTL_RCGCGPIO_R |= 0x02;
    SYSCTL_RCGCTIMER_R |= 0x02;
    while ((SYSCTL_PRGPIO_R & 0x02) != 0x02) {};
    GPIO_PORTB_DIR_R &= ~0x20;
    GPIO_PORTB_AFSEL_R |= 0x20;
    GPIO_PORTB_PCTL_R = (GPIO_PORTB_PCTL_R & 0xFF7FFFFF) | 0x00700000;
    GPIO_PORTB_DEN_R |= 0x20;


    TIMER1_CTL_R &= ~0x100;
    TIMER1_CFG_R = 0x4;
    TIMER1_TBMR_R = 0x0A;
    // start values
    TIMER1_TBPR_R = pwm_period >> 16;
    TIMER1_TBILR_R = pwm_period & 0xFFFF;
    TIMER1_CTL_R |= 0x100;

}

void servo_move(uint16_t degrees) {
    float millis;
    int high;
    int low;
    float a = (0.0044*degrees)+.466;
//    millis = (0.0056 * degrees) + 1;
    millis = (0.0056 * degrees) + a;
    high = millis * 1000000 / 62.5;
    low = 320000 - high;
    TIMER1_TBMATCHR_R = low;
    TIMER1_TBPMR_R = low >> 16;
}
