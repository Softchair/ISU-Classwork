/*----------------------------------------------------------------------------
-		        SE 185: Lab 02 - Solving Simple Problems in C	    	 	 -
-	Name:	 Camden Fergen													 -
- 	Section: 2																 -
-	NetID:	 cfergen													     -
-	Date:	 2/1/2022														 -
-----------------------------------------------------------------------------*/

/*----------------------------------------------------------------------------
-								Includes									 -
-----------------------------------------------------------------------------*/
#include <stdio.h>
#include <math.h>

/*----------------------------------------------------------------------------
-								Implementation								 -
-----------------------------------------------------------------------------*/
int main(int argc, char *argv[]) {
    int tempInt;
    double tempDouble;

    tempInt = 6427 + 1725; //A
    printf("6427 + 1725 = %d\n", tempInt);

    tempInt = (6971 * 3925) - 95; //B
    printf("(6971 * 3925) - 95 = %d\n", tempInt);

    tempDouble = 79 + 12 / 5; //C
    printf("79 + 12 / 5 =  %f\n", tempDouble);

    tempDouble = 3640.0 / 107.9; //D
    printf("3640.0 / 107.9 = %f\n", tempDouble);

    tempInt = (22 / 3) * 3; //E
    printf("(22 / 3) * 3 = %d\n", tempInt);

    tempInt = 22 / (3 * 3); //F
    printf("22 / (3 * 3) = %d\n", tempInt);

    tempDouble = 22 / (3 * 3); //G
    printf("22 / (3 * 3) =  %f\n", tempDouble);

    tempDouble = 22 / 3 * 3; //H
    printf("22 / 3 * 3 =  %f\n", tempDouble);

    tempDouble = (22.0 / 3) * 3.0; //I
    printf("(22.0 / 3) * 3.0 = %f\n", tempDouble);

    tempInt = 22.0 / (3 * 3.0) ; //J
    printf("22.0 / (3 * 3.0) = %d\n", tempInt);

    tempDouble = 22.0 / 3.0 * 3.0; //K
    printf("22.0 / 3.0 * 3.0 = %f\n", tempDouble);

    //L
    double circleArea = M_PI * pow(23.567, 2);
    printf("Circle area: %f\n", circleArea);

    //M
    double conversionMeters = 14 / 3.281;
    printf("Feet: 14 -> Meters: %f\n", conversionMeters);

    //N
    double conversionCelsius = (76 - 32) / 1.8;
    printf("Fahrenheit: 76 -> Celsius: %f", conversionCelsius);

}