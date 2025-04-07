/*----------------------------------------------------------------------------
-		         SE 185: Lab 05 - Conditionals (What's up?)	    	         -
-	Name:	 Camden Fergen													 -
- 	Section: 2																 -
-	NetID:	 cfergen														 -
-	Date:	 2/22/22														 -
-----------------------------------------------------------------------------*/

/*----------------------------------------------------------------------------
-								Includes									 -
-----------------------------------------------------------------------------*/
#include <stdio.h>
#include <math.h>
#include <stdbool.h>

double magnitude();
bool closeTo();
int side();
bool buttonCheck();
bool checkLast();
void sidePrint();


/*----------------------------------------------------------------------------
-	                            Prototypes                                   -
-----------------------------------------------------------------------------*/

/*----------------------------------------------------------------------------
-	                                Notes                                    -
-----------------------------------------------------------------------------*/
// Compile with gcc lab05.c -o lab05
// Run with ./ds4rd.exe -d 054c:05c4 -D DS4_BT -a -g -b | ./lab05

/*----------------------------------------------------------------------------
-								Implementation								 -
-----------------------------------------------------------------------------*/
int main(int argc, char *argv[])
{
    int triangle, circle, x_button, square;
    double ax, ay, az, gx, gy, gz;

    bool buttonPressed = false;
    int sideVal = 0; //The value of side;

    while (buttonPressed != true)
    {
        scanf("%lf, %lf, %lf, %lf, %lf, %lf, %d, %d, %d, %d",
              &ax, &ay, &az, &gx, &gy, &gz, &triangle, &circle, &x_button, &square);

        /* printf for observing values scanned in from ds4rd.exe,
         * be sure to comment or remove in final program */
        //printf("Echoing output: %lf, %lf, %lf, %lf, %lf, %lf, %d, %d, %d, %d \n", ax, ay, az, gx, gy, gz, triangle, circle, x_button, square);

        /* It would be wise (mainly save time) if you copy your code to calculate
         * the magnitude from lab03-1.c. You will also need to copy your
         * prototypes and functions to the appropriate sections in this program. */

        //printf("The acceleration's current magnitude is: %lf\n", magnitude(ax, ay, az));

        //Testing print functions
        //printf("AX: %lf, AY: %lf. AZ: %lf\n", gx, gy, gz);
        //printf("CHECK THIS NUMBER: %d\n", side(gx, gy, gz));

        //Final code
        int tempSideVal = side(gx, gy, gz); //The new side val
        //printf("last value: %d -- new value: %d\n", tempSideVal, sideVal);

        buttonPressed = buttonCheck(triangle); //Checks if the button has been pressed
        
        if (checkLast(tempSideVal, sideVal) && tempSideVal != 7) { //this stupid 7 caused me massive pain
            sideVal = tempSideVal;
            sidePrint(tempSideVal);
        }

    }

    printf("End program");

    return 0;
}

/* Put your functions here, and be sure to put prototypes above. */

/*
* Finds the magnitude of the controller
*/
double magnitude(double x, double y, double z) {
    return sqrt(pow(x, 2) + pow(y, 2) + pow(z, 2));
}

/*
* Determines if a value is close to 1 (or point)
*/
bool closeTo(double tolerance, double point, double curVal) {
    if (fabs(curVal) >= point - tolerance && fabs(curVal) <= point + tolerance) {
        return true;
    } 

    return false;
}

/*
* Checks the values and sees if any of them are close, if they are, returns the number
* that corresponds with the side as found in the CONST in checkSide
*/
int side(double gx, double gy, double gz) {
    const double tolerance = 0.25;
    const double point = 1;

    if (closeTo(tolerance, point, gx)) {
        if (gx > 0) {
            return 3; //Left
        } else {
            return 4; //Right
        }
    } else if (closeTo(tolerance, point, gy)) {
        if (gy > 0) {
            return 1; //Top
        } else {
            return 2; //Bottom
        }
    } else if (closeTo(tolerance, point, gz)) {
        if (gz > 0) {
            return 6; //Back
        } else {
            return 5; //Front
        }
    }
    return 7; //Sets to seven if it doesnt change
}

/*
* Checks to see if the triangle button has been pressed
*/
bool buttonCheck(int button) {
    if (button > 0) {
        return true;
    }
    return false;
}

/*
* Returns true if the new side value is different from the last
*/
bool checkLast(int new, int last) {
    if (new != last) {
        return true;
    }
    return false;
}

void sidePrint(int side) {
    //TOP = 1, BOTTOM = 2, LEFT = 3, RIGHT = 4, FRONT = 5, BACK = 6;

     switch(side) {
         case 1: printf("TOP\n"); break;
         case 2: printf("BOTTOM\n"); break;
         case 3: printf("LEFT\n"); break;
         case 4: printf("RIGHT\n"); break;
         case 5: printf("FRONT\n"); break;
         case 6: printf("BACK\n"); break;
     }
}

char* test(int side) { //Dont worry about this
    char* str = "hello";

    switch(side) {
         case 1: str = "TOP\n"; break;
         case 2: str = "BOTTOM\n"; break;
         case 3: printf("LEFT\n"); break;
         case 4: printf("RIGHT\n"); break;
         case 5: printf("FRONT\n"); break;
         case 6: printf("BACK\n"); break;
     }

    return str;
}
