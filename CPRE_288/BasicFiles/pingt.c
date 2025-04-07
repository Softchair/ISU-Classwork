#include "ping.h"
#include "Timer.h"

volatile unsigned long START_TIME = 0;
volatile unsigned long END_TIME = 0;
volatile enum{LOW, HIGH, DONE} STATE = LOW; // State of ping echo pulse

void ping_init (void){

    //static uint8_t initialized = 0;

    //Check if already initialized
//    if(initialized){
//        return;
//    }


    // YOUR CODE HERE
    // 2) Set the buttons as inputs, do not modify other PORTB wires
    SYSCTL_RCGCGPIO_R |= 0b00010;
    // You may need to add a delay here of several clock cycles for the clock to start, e.g., execute a simple dummy assignment statement, such as "long delay = SYSCTL_RCGCGPIO_R".
    // Instead, use the PRGPIO register and busy-wait on the peripheral ready bit for PORTB.
    while ((SYSCTL_PRGPIO_R & 0b00010) == 0) {};

    SYSCTL_RCGCTIMER_R |= 0x8;
    // You may need to add a delay here of several clock cycles for the clock to start, e.g., execute a simple dummy assignment statement, such as "long delay = SYSCTL_RCGCGPIO_R".
    // Instead, use the PRGPIO register and busy-wait on the peripheral ready bit for PORTB.
    while ((SYSCTL_RCGCTIMER_R & 0x8) == 0) {};

    GPIO_PORTB_AFSEL_R |= 0x08;

    //enable digital functionality on port B pins
    GPIO_PORTB_DEN_R |= 0x8;

    GPIO_PORTB_PCTL_R |= 0x7000;

    GPIO_PORTB_DIR_R &= ~0x8;


    //Configure the timer
    TIMER3_CTL_R &= ~0x100;
    TIMER3_CFG_R |= 0x4;

    TIMER3_TBMR_R &= ~0x10;
    TIMER3_TBMR_R |= 0x7;

    TIMER3_TBPR_R = 0xFF;
    TIMER3_TBILR_R = 0xFFFF;
    TIMER3_CTL_R |= 0xC00;

    TIMER3_ICR_R |= 0x400;
    TIMER3_IMR_R |= 0x400;
    TIMER3_CTL_R |= 0x100;

    NVIC_PRI9_R = 0x20;
    NVIC_EN1_R |= 0x10;

    IntRegister(INT_TIMER3B, TIMER3B_Handler);

    IntMasterEnable();

    //initialized = 1;
}

void ping_trigger (void){
    STATE = LOW;

    // Disable timer and disable timer interrupt
    TIMER3_CTL_R &= ~0x100;
    TIMER3_IMR_R &= ~0x400;
    // Disable alternate function (disconnect timer from port pin)
    GPIO_PORTB_AFSEL_R &= ~0x08;

    GPIO_PORTB_DIR_R |= 0x8;

    GPIO_PORTB_DATA_R &= ~0x8;
    // YOUR CODE HERE FOR PING TRIGGER/START PULSE
    GPIO_PORTB_DATA_R |= 0x8;

    timer_waitMillis(.005);

    GPIO_PORTB_DATA_R &= ~0x8;
    // Clear an interrupt that may have been erroneously triggered
    TIMER3_ICR_R |= 0x400;
            // Re-enable alternate function, timer interrupt, and timer
    GPIO_PORTB_AFSEL_R |= 0x8;

    TIMER3_IMR_R |= 0x400;
    TIMER3_CTL_R |= 0x100;



}

void TIMER3B_Handler(void){

    if(STATE == LOW){

        START_TIME = TIMER3_TBR_R;

        STATE = HIGH;

    }else if(STATE == HIGH){

        END_TIME = TIMER3_TBR_R;

        STATE = DONE;
    }
    TIMER3_ICR_R |= 0x400;

}

float ping_getDistance (void){

    ping_trigger();

    while(STATE !=DONE){}// Busy waits until TIMES are collected

    float  Distacnce = 0;

    if(START_TIME > END_TIME){

        Distacnce = (((START_TIME-END_TIME)*(1.0/16000000))*343*100)/2.0;

    } else{// in the case of over flow
        Distacnce = ((((START_TIME-END_TIME) + 1048576)*(1.0/16000000))*343*100)/2.0;


    }

    return Distacnce;
}
