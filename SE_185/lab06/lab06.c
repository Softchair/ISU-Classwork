/*----------------------------------------------------------------------------
-		                    SE 185: Lab 06 - Bop-It!	    	             -
-	Name:	 Camden Fergen													 -
- 	Section: 2																 -
-	NetID:	 cfergen													     -
-	Date:	 3/8/2022														 -
-----------------------------------------------------------------------------*/

/*----------------------------------------------------------------------------
-								Includes									 -
-----------------------------------------------------------------------------*/
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>

int game();
int keyPressed();
int timeSub();
char* button();
int buttonCheck();

/*----------------------------------------------------------------------------
-	                            Notes                                        -
-----------------------------------------------------------------------------*/
// Compile with gcc lab06.c -o lab06
// Run with ./ds4rd.exe -d 054c:05c4 -D DS4_BT -t -b | ./lab06

/*----------------------------------------------------------------------------
-								Implementation								 -
-----------------------------------------------------------------------------*/

/*
** Bop-it like game using a PS4 controller and buttons
** Button vals are: | 1 - Triangle | 2 - Circle | 3 - X | 4 - Square |
*/
int main(int argc, char *argv[])
{
    int tri, cir, x, squ; //Triangle, circle, X, square
    int time;
    int score;

    bool buttonPressed = false;
    bool lose = false;

    printf("Press the X button to start Iop-bt: \n");
    while(buttonPressed != true) { //Wait for button to be pressed
        // scanf("%d, %d, %d, %d, %d", &time, &tri, &cir, &x, &squ);
        x = keyPressed();

        if (x == 3) {
            buttonPressed = true;
            printf("Okay!\n");
        }
    }

    score = game(time);
    printf("\nScore: %d\n", score);

    return 0;
}

/*
** Game function
*/
int game(int startTime) {
    /* Button vals are: | 1 - Triangle | 2 - Circle | 3 - X | 4 - Square | */

    srand(time(NULL)); /* This will ensure a random game each time. */

    int tri, cir, x, squ; //Triangle, circle, X, square

    int totalTime = 10000; //Base val
    int timeLeft = totalTime, conTime, lastConTime = 0; //Times

    int aBut = -1; //Error val
    int userBut = 5; //Error val
    int score = 0; //Error val

    while (timeLeft > 0) {
        aBut = (rand() % 4) + 1;

        printf("Press the %s button: \n", button(aBut));
        printf("You have %d milliseconds to respond!\n", timeLeft);

        scanf("%d, %d, %d, %d, %d", &conTime, &tri, &cir, &x, &squ);
        
        conTime = conTime - startTime; //Finds when the game actually started
        timeLeft = timeSub(timeLeft, conTime, lastConTime); //Calculates how much time is left


        while (userBut == 5 && timeLeft > 0) {
            userBut = keyPressed(); //Returns a number based on which button is pressed
            if (userBut == aBut) {
                score += 1;
            } else if (userBut != aBut && userBut < 5) {
                printf("Wrong button! Game over\n");
                return score;
            }
        }

        userBut = 5; //Reset val
        lastConTime = conTime; //Sets the lastTime to current conTime
    }
    return score;
}

/*
** Waits till none of the buttons are pressed to get input again
** Returns: Button num 1-4
*/
int keyPressed() {
    int userBut = 5;
    int conTime, tri, cir, x, squ;
    int temp = 5;

    while(userBut == 5) {
        scanf("%d, %d, %d, %d, %d", &conTime, &tri, &cir, &x, &squ);
        userBut = buttonCheck(tri, cir, x, squ);
    }
    temp = userBut;

    while(userBut != 5) {
        scanf("%d, %d, %d, %d, %d", &conTime, &tri, &cir, &x, &squ);
        userBut = buttonCheck(tri, cir, x, squ);
    }

    return temp;
}

/*
** Finds the amount of time left
*/
int timeSub(int timeLeft, int conTime, int lastConTime) {
    return timeLeft - (conTime - lastConTime);
}

char* button(int but) {
    switch(but) {
        case 1: return "Triangle"; break;
        case 2: return "Circle"; break;
        case 3: return "X button"; break;
        case 4: return "Square"; break;
        default: return "ERROR";
    }
}

/*
** Function to check for which button was presssed
*/
int buttonCheck(int tri, int cir, int x, int squ) {
    /* Button vals are: | 1 - Triangle | 2 - Circle | 3 - X | 4 - Square | */

    if (tri == 1) {
        return 1;
    } else if (cir == 1) {
        return 2;
    } else if (x == 1) {
        return 3;
    } else if (squ == 1) {
        return 4;
    } else {
        return 5;
    }
}