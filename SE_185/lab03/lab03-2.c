/*----------------------------------------------------------------------------
-		    SE 185: Lab 03 - Introduction to the DS4 and Functions	    	 -
-	Name:	 Camden Fergen													 -
- 	Section: 2																 -
-	NetID:	 cfergen													     -
-	Date:	 2/8/2022														 -
-----------------------------------------------------------------------------*/

/*----------------------------------------------------------------------------
-								Includes									 -
-----------------------------------------------------------------------------*/
#include <stdio.h>
#include <math.h>

/*----------------------------------------------------------------------------
-	                            Prototypes                                   -
-----------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------
-	                                Notes                                    -
-----------------------------------------------------------------------------*/
// Compile with gcc lab03-2.c -o lab03-2
// Run with ./ds4rd.exe -d 054c:05c4 -D DS4_BT -b | ./lab03-2

/*----------------------------------------------------------------------------
-								Implementation								 -
-----------------------------------------------------------------------------*/
int main(int argc, char *argv[])
{
    int numButtons();
    int t, o, x, s;

    while (1)
    {
        scanf("%d, %d, %d, %d", &t, &o, &x, &s);

        printf("Buttons pressed: %d\n", numButtons(t, o, x, s));
        fflush(stdout);
    }

    return 0;
}

/* Put your functions here, and be sure to put prototypes above. */

int numButtons(int t, int o, int x, int s) {
    int numButtons;
    if (t > 0) {
            numButtons += 1;
           // printf("TRIANGLE");
        } if (o > 0) {
            numButtons += 1;
           // printf("CIRCLE");
        } if (x > 0) {
            numButtons += 1;
           // printf("X");
        } if (s > 0) {
            numButtons += 1;
           // printf("SQUARE");
        }
        return numButtons;
}

