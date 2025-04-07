/*
* A simple game where you roll dice against a computer
* By Camden Fergen
*/

#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>

int playerDice();
int compDice();

void main() {
    int luckyNum;
    int p, c; //Var for holding sum of the two players dice

    printf("Enter your lucky number!\n");

    while (luckyNum != -99) {
        scanf("%d", &luckyNum);

        p = playerDice(luckyNum);
        c = compDice();

        if (p > c) {
            printf("Player 1 is the winner!\n");
        } else if (c > p) {
            printf("Player 2 is the winner!\n");
        } else {
            printf("No one is lucky today. It is a tie\n");
        }

        printf("Enter another lucky number to play again or -99 to quit!\n");
    }

    printf("Thanks for playing!");

}

int playerDice(int lNum) {
    int d1, d2; //Variables for holding 2 dice info
    srand(lNum);
    
    d1 = rand() % 6 + 1;
    d2 = rand() % 6 + 1;
    
    printf("Player 1 Dice: %d - %d\n", d1, d2);
    printf("Player 1 sum: %d\n", d1 + d2);

    return d1 + d2;
}

int compDice() {
    int d1, d2;
    srand(time(NULL)); //Time based seed

    d1 = rand() % 6 + 1;
    d2 = rand() % 6 + 1;

    printf("Player 2 Dice: %d - %d\n", d1, d2);
    printf("Player 2 sum: %d\n", d1 + d2);

    return d1 + d2;
}