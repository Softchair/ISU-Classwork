/*-----------------------------------------------------------------------------
-					     SE 185 Lab 07 - The DS4 Equalizer
-                   Developed for 185-Rursch by T.Tran and K.Wang
-
-	Name:    Camden Fergen
- 	Section: 2
-	NetID:   cfergen
-	Date:    03/09/2022
-
-   This file provides the outline for your program
-   Please implement the functions given by the prototypes below and
-   complete the main function to make the program complete.
-   You must implement the functions which are prototyped below exactly
-   as they are requested.
-----------------------------------------------------------------------------*/

/*-----------------------------------------------------------------------------
-								Includes
-----------------------------------------------------------------------------*/
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <stdbool.h>


/*-----------------------------------------------------------------------------
-								Defines
-----------------------------------------------------------------------------*/
#define PI 3.141592653589

/* NO GLOBAL VARIABLES ALLOWED */

void test();

//int keyPressed();

/*------------------------------------------------------------------------------------
    PRE: Arguments must point to double variables or int variables as appropriate
    This function scans a line of DS4 data, and returns
    True when left button is pressed
    False Otherwise
    POST: it modifies its arguments to return values read from the input line.
------------------------------------------------------------------------------------*/
bool read_input( int* time,
                double* g_x, double* g_y, double* g_z,
                int* button_T, int* button_C, int* button_X, int* button_S,
                int* l_joy_x, int* l_joy_y, int* r_joy_x, int* r_joy_y, int* mode );

/*-----------------------------------------------------------------------------
    PRE: ~(-1.0) <= mag <= ~(1.0)
    This function scales the roll/pitch value to fit on the screen.
    Input should be capped at either -1.0 or 1.0 before the rest of your
    conversion.
    POST: -39 <= return value <= 39
-----------------------------------------------------------------------------*/
int scaleMagForScreen(double rad);

/*-----------------------------------------------------------------------------
    PRE: -128 <= mag <= 127
    This function scales the joystick value to fit on the screen.
    POST: -39 <= return value <= 39
-----------------------------------------------------------------------------*/
int scaleJoyForScreen(int rad);

/*----------------------------------------------------------------------------
    PRE: -39 <= number <= 39
    Uses print_chars to graph a number from -39 to 39 on the screen.
    You may assume that the screen is 80 characters wide.
----------------------------------------------------------------------------*/
void graph_line(int number, int mode);

/*-----------------------------------------------------------------------------
    PRE: num >= 0
    This function prints the character "use" to the screen "num" times
    This function is the ONLY place printf is allowed to be used
    POST: nothing is returned, but "use" has been printed "num" times
-----------------------------------------------------------------------------*/
void print_chars(int num, char use);


/*-----------------------------------------------------------------------------
-								Implementation
-----------------------------------------------------------------------------*/
int main()
{
    double x, y, z;                     /* Values of x, y, and z axis*/
    int time;                           /* Variable to hold the time value */
    int t, o, bx, s;                     /* Variables to hold the button statuses */
    int j_LX, j_LY, j_RX, j_RY;         /* Variables to hold the joystick statuses */
    int scaled_pitch, scaled_roll; 	    /* Value of the roll/pitch adjusted to fit screen display */
    int scaled_joy;                     /* Value of joystick adjusted to fit screen display */
    bool end = false;                   /* Bool for if X has been pressed and if to end */
    int mode = 0;                       /* Which mode 0 = Pitch 1 = Roll 2 = Joy */

    /* time || x for roll -1 left -- 1 right || y || z for pitch 1 forward -1 backward */
    /* buttons || joystick left = -128 up = -128*/


    /* Put pre-loop preparation code here */

    do {

        /* Scan input */
        //scanf("%d, %lf, %lf, %lf, %d, %d, %d, %d, %d, %d, %d, %d", time, x, y, z, t, o, x, s, j_LX, j_LY, j_RX, j_RY);
        end = read_input(&time, &x, &y, &z, &t, &o, &bx, &s, &j_LX, &j_LY, &j_RX, &j_RY, &mode);


        /* Calculate and scale for pitch AND roll AND joystick */
        /* Roll = LEFT RIGHT -- Pitch = FROWARD  BACKWARD */


        /* Switch between roll, pitch, and joystick with the up, down, and right button, respectivly */

        if (mode == 0) {
            graph_line(scaleMagForScreen(x), mode);
        } else if (mode == 1) {
            graph_line(scaleMagForScreen(z), mode);
        } else if (mode == 2) {
            graph_line(scaleJoyForScreen(j_RX), 0);
        }


        /* Output your graph line */


        fflush(stdout);

    } while (end != true); /* Modify to stop when left button is pressed */

    return 0;

}

/*------------------------------------------------------------------------------------
    PRE: Arguments must point to double variables or int variables as appropriate
    This function scans a line of DS4 data, and returns
    True when left button is pressed
    False Otherwise
    POST: it modifies its arguments to return values read from the input line.
------------------------------------------------------------------------------------*/
bool read_input( int* time,
                double* g_x, double* g_y, double* g_z,
                int* tri, int* cir, int* bX, int* squ,
                int* l_joy_x, int* l_joy_y, int* r_joy_x, int* r_joy_y, int* mode ) {
    //here
    scanf("%d, %lf, %lf, %lf, %d, %d, %d, %d, %d, %d, %d, %d", time, g_x, g_y, g_z, tri, cir, bX, squ, l_joy_x, l_joy_y, r_joy_x, r_joy_y);
    
    if(*cir != 0){
        *mode = (*mode + 1) % 3;
        while(*cir){
            scanf("%d, %lf, %lf, %lf, %d, %d, %d, %d, %d, %d, %d, %d", time, g_x, g_y, g_z, tri, cir, bX, squ, l_joy_x, l_joy_y, r_joy_x, r_joy_y);
        }
    }

    if ((*squ) > 0) {
        return true;
    }

    return false;
}

/*-----------------------------------------------------------------------------
    PRE: ~(-1.0) <= mag <= ~(1.0)
    This function scales the roll/pitch value to fit on the screen.
    Input should be capped at either -1.0 or 1.0 before the rest of your
    conversion.
    POST: -39 <= return value <= 39
-----------------------------------------------------------------------------*/
int scaleMagForScreen(double rad) {
    if (rad > 1) { //Make sure it is within input range
        rad = 1.0;
    } else if (rad < -1.0) {
        rad = -1.0;
    }

    if (rad >= 0) {
        return (int) (rad * 39);
    } else {
        double temp = rad * -1;
        return -(temp * 39);
    }
}

/*-----------------------------------------------------------------------------
    PRE: -128 <= mag <= 127
    This function scales the joystick value to fit on the screen.
    POST: -39 <= return value <= 39
-----------------------------------------------------------------------------*/
int scaleJoyForScreen(int rad) {
    if (rad > 0) {
        return -(rad / 3.28);
    } else if (rad < 0) {
        int temp = -rad;
        return temp / 3.28; //Val to keep between 39
    }
}

/*----------------------------------------------------------------------------
    PRE: -39 <= number <= 39
    Uses print_chars to graph a number from -39 to 39 on the screen.
    You may assume that the screen is 80 characters wide.
----------------------------------------------------------------------------*/
void graph_line(int number, int mode) {
    char letter = 'Q';

    if (number < 0) {

        if (mode == 0) {
            letter = 'R';
        } else if (mode == 1) {
            letter = 'B';
        }

        print_chars(40, ' ');
        print_chars((number * -1), letter); //Converts to positive to print
        print_chars((39 - (-number)), ' '); //Prints rest of chars as space
        print_chars(1, '\n');
    } else if (number > 0) {

        if (mode == 0) {
            letter = 'L';
        } else if (mode == 1) {
            letter = 'F';
        }

        print_chars((39 - number), ' ');
        print_chars(number, letter);
        print_chars(40, ' ');
        print_chars(1, '\n');
    } else {
        print_chars(39, ' ');
        print_chars(1, '0');
        print_chars(39, ' ');
        print_chars(1, '\n');
    }
}

/*-----------------------------------------------------------------------------
    PRE: num >= 0
    This function prints the character "use" to the screen "num" times
    This function is the ONLY place printf is allowed to be used
    POST: nothing is returned, but "use" has been printed "num" times
-----------------------------------------------------------------------------*/
void print_chars(int num, char use) {
    for(int i = 0; i < num; i++) {
        printf("%c", use);
    }
}