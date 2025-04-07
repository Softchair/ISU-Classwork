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

/*----------------------------------------------------------------------------
-								Implementation								 -
-----------------------------------------------------------------------------*/
int main(int argc, char *argv[])
{
    int integer_result;
    double decimal_result;

    decimal_result = (double) 77 / 5; //added the (double) tag so it would use floating point
    printf("The value of 77/5 is %lf, using integer math.\n", decimal_result); //changed integer_result to decimal_result

    integer_result = 2 + 3;
    printf("The value of 2+3 is %d.\n", integer_result); //added the `, integer_result` so it would print a variable

    decimal_result = 1.0 / 22.0;
    printf("The value 1.0/22.0 is %f.\n", decimal_result); //changed %d to %f to print floating point number

    return 0;
}
