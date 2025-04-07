/*----------------------------------------------------------------------------
-		                 SE 185: Lab 04 - Debugging Code	    	         -
-	Name:	 Camden Fergen													 -
- 	Section: 2																 -
-	NetID:	 cfergen													     -
-	Date:	 2/15/2022												    	 -
-----------------------------------------------------------------------------*/

/*----------------------------------------------------------------------------
-								Includes									 -
-----------------------------------------------------------------------------*/
#include <stdio.h>
#include <math.h>

/*----------------------------------------------------------------------------
-	                                Notes                                    -
-----------------------------------------------------------------------------*/
// Compile with gcc lab04-1_4.c -o lab04-1_4
// Run with ./lab04-1_4
/* This program calculates the energy of one photon
 * of user-inputted wave-length of light */

/*----------------------------------------------------------------------------
-								Implementation								 -
-----------------------------------------------------------------------------*/
int main(int argc, char *argv[])
{
    //removed !
    double speed_of_light;
    //removed -
    double wavelength;
    //removed ~
    double length_in_meters;
    //put const at the start, and moved the declaration up here
    const double plank = 6.62606957 * pow(10, -34); //Planck's constant
    double energy; //removed 0

    //removed 1
    speed_of_light = 2.99792458 * pow(10, 8); // Constant for the speed of light
    //removed -
    wavelength = 0;
    //removed ~
    length_in_meters = 0; 
    //removed 0
    energy = 0; 

    printf("Welcome! This program will give the energy, in Joules,\n");
    printf("of 1 photon with a certain wave-length.\n");
    printf("Please input a wave-length of light in nano-meters.\n");
    printf("Please do not enter a negative, or zero, wave-length.\n");

    scanf("%lf", &wavelength);

    if (wavelength > 0.0)
    {
        length_in_meters = wavelength / pow(10, 9); // Converting nano-meters to meters
        //removed 0 and const after plank
        energy = (plank * speed_of_light) / length_in_meters; // Calculating the energy of 1 photon
        //removed 0
        printf("A photon with a wave-length of %08.3lf nano-meters, carries "
               "\napproximately %030.25lf joules of energy.", wavelength, energy);
    } else
    {
        printf("Sorry, you put in an invalid number.");
        printf("Please rerun the program and try again.");
    }

    return 0;
}
