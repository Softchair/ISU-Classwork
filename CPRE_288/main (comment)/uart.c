/*
*
*   uart_extra_help.c
* Description: This is file is meant for those that would like a little
*              extra help with formatting their code, and followig the Datasheet.
*/

#include <stdint.h>
#include <stdbool.h>
#include "timer.h"
#include "lcd.h"
#include <inc/tm4c123gh6pm.h>
#include "driverlib/interrupt.h"
#include <math.h>
#include <uart.h>
#define REPLACE_ME 0x00

volatile  char uart_data;
volatile  char flag;


void uart_init(int baud)
{
    SYSCTL_RCGCGPIO_R |= 0x00000002;      // enable clock GPIOB (page 340)
    SYSCTL_RCGCUART_R |= 0x00000002;      // enable clock UART1 (page 344)
    GPIO_PORTB_AFSEL_R = 0x00000003;      // sets PB0 and PB1 as peripherals (page 671)
    GPIO_PORTB_PCTL_R  |= 0x00000011;       // pmc0 and pmc1       (page 688)  also refer to page 650
    GPIO_PORTB_DEN_R   = 0x00000003;        // enables pb0 and pb1
    GPIO_PORTB_DIR_R   |= 0x00000001;        // sets pb0 as output, pb1 as input

    //compute baud values [UART clock= 16 MHz] 
    double fbrd;
    int    ibrd;
    double brd;

    brd = 16000000.0 / (16 * 115200);
    fbrd = brd - floor(brd); // page 903 for formula
    ibrd = floor(brd);
    fbrd = fbrd * 64 + 0.5;
    //fbrd = fbrd * 64 + 0.5;

    UART1_CTL_R &= 0xFFFFFFFE;      // disable UART1 (page 918) /// bit 0 is set to 0... UARTEN
    UART1_IBRD_R = ibrd;        // write integer portion of BRD to IBRD
    UART1_FBRD_R = fbrd;   // write fractional portion of BRD to FBRD
    UART1_LCRH_R = 0b01100000;        // write serial communication parameters (page 916) * 8bit and no parity
    UART1_CC_R   = 0x00000000;          // use system clock as clock source (page 939)
    UART1_CTL_R |= 0x00000301;        // enable UART1

}

void uart_sendChar(char data)
{
    //TODO
    while (UART1_FR_R & 0x20){
    }
    UART1_DR_R = data;
   
}

char uart_receive(void)
{
    //TODO
    char rdata;

    while (UART1_FR_R & 0x10){
  }
    rdata = (char)(UART1_DR_R & 0xFF);
    return rdata;
}

void uart_sendStr(const char *data)
{
    //TODO
    while(*data != '\0')
    {
        uart_sendChar(*data);
        data++;
    }

}

// _PART3


void uart_interrupt_init()
{
    UART1_ICR_R |= 0x10;
    // Enable interrupts for receiving bytes through UART1
    UART1_IM_R |= 0x0010; //Enable interrupt on receive - page 924

    // Find the NVIC enable register and bit responsible for UART1 in table 2-9

    // Note: NVIC register descriptions are found in chapter 3.4
    NVIC_EN0_R |= 0x0040; //enable uart1 interrupts - page 104

    // Find the vector number of UART1 in table 2-9 ! UART1 is 22 from vector number page 104
    IntRegister(INT_UART1, uart_interrupt_handler); //give the microcontroller the address of our interrupt handler - page 104 22 is the vector number

    IntMasterEnable();
}

void uart_interrupt_handler()
{
// STEP1: Check the Masked Interrup Status

//    flag = 1;

    if (UART1_MIS_R & 0x10){
        flag = 1;
        uart_data = UART1_DR_R & 0xFF;
        UART1_ICR_R |= 0x10;
    }
//STEP2:  Copy the data 

//STEP3:  Clear the interrupt

}
