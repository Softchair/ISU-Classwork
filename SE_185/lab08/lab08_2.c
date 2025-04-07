/*-----------------------------------------------------------------------------
-					  		SE 185 Lab 08
-             Developed for 185-Rursch by T.Tran and K.Wang
-	Name:    Camden Fergen
-   Section: 2
-	NetID:   cfergen
-	Date:    04/04/2022
-----------------------------------------------------------------------------*/

/*-----------------------------------------------------------------------------
-								Includes
-----------------------------------------------------------------------------*/
#include <stdio.h>
#include <math.h>
#include <ncurses/ncurses.h>
#include <unistd.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>

/*-----------------------------------------------------------------------------
-								Defines
-----------------------------------------------------------------------------*/
/* Mathmatical constants */
#define PI 3.14159

/* 	Screen geometry
    Use ROWS and COLUMNS for the screen height and width (set by system)
    MAXIMUMS */
#define COLUMNS 100
#define ROWS 80

/* 	Character definitions taken from the ASCII table */
#define AVATAR 'A'
#define WALL '*'
#define EMPTY_SPACE ' '

/*	Number of samples taken to form an moving average for the gyroscope data
    Feel free to tweak this. */
#define NUM_SAMPLES 10


/*-----------------------------------------------------------------------------
-								Static Data
-----------------------------------------------------------------------------*/
/* 2D character array which the maze is mapped into */
char MAZE[COLUMNS][ROWS];


/*-----------------------------------------------------------------------------
-								Prototypes
-----------------------------------------------------------------------------*/
/*	POST: Generates a random maze structure into MAZE[][]
    You will want to use the rand() function and maybe use the output %100.
    You will have to use the argument to the command line to determine how
    difficult the maze is (how many maze characters are on the screen). */
void generate_maze(int difficulty);

/*	PRE: MAZE[][] has been initialized by generate_maze()
    POST: Draws the maze to the screen */
void draw_maze(void);

/*	PRE: 0 < x < COLUMNS, 0 < y < ROWS, 0 < use < 255
    POST: Draws character use to the screen and position x,y */
void draw_character(int x, int y, char use);

/*	PRE: -1.0 < mag < 1.0
    POST: Returns tilt magnitude scaled to -1.0 -> 1.0
    You may want to reuse the roll function written in previous labs. */
double calc_roll(double mag);

/* 	Updates the buffer with the new_item and returns the computed
    moving average of the updated buffer */
double m_avg(double buffer[], int avg_size, double new_item);

bool canMoveDown(int x, int y);

bool canMoveLeft(int x, int y);

bool canMoveRight(int x, int y);

bool checkIfStuck(int x, int y);

bool bucketCheck(int x, int y);

/*-----------------------------------------------------------------------------
-								Implementation
-----------------------------------------------------------------------------*/

/*	Main - Run with './ds4rd.exe -t -g -b' piped into STDIN */
void main(int argc, char* argv[])
{
    /* Variables for m_avg function as well as variables for averages */
    double x[10], y[10], z[10];
    double new_x, new_y, new_z;
    double avg_x, avg_y, avg_z;
    int bufferLength = 10;
    
    /* Other variables */
    int time = 0;
    int nextMove = 1000; //Variable for the next time the character should move
    int lastX = ROWS / 2, lastY = 0;
    int curX = ROWS / 2, curY = 0;
    int buttons[4];
    bool stuck = false;

    if (argc != 2 ) {
        printw("You must enter the difficulty level on the command line.");
        refresh();
        return;
    } else {
        /* 	Setup screen for Ncurses
        The initscr functionis used to setup the Ncurses environment
        The refreash function needs to be called to refresh the outputs
        to the screen */
        initscr();
        refresh();

        /* WEEK 2 Generate the Maze */
        int temp = 0;
        sscanf(argv[1], "%d", &temp);
        generate_maze(temp);
        draw_maze();

        /* Read gyroscope data and fill the buffer before continuing */
        for(int i = 0; i < bufferLength; i++) {
            scanf("%d, %lf, %lf, %lf", &time, &new_x, &new_y, &new_z);
            x[i] = new_x;
            y[i] = new_y;
            z[i] = new_z;
        }

        /* Event loop */
        do {
            /* Read data, update average */
            scanf("%d, %lf, %lf, %lf", &time, &new_x, &new_y, &new_z);

            avg_x = m_avg(x, bufferLength, new_x); //Left is pos right is neg
            avg_y = m_avg(y, bufferLength, new_y); //Up is pos down is neg
            avg_z = m_avg(z, bufferLength, new_z); //Forward is pos backward is neg

            /* Is it time to move?  if so, then move avatar */
            if (time >= nextMove) {

                

                if(avg_x > 0.500 || avg_x < -0.500) {
                    if(avg_x > 0 && canMoveLeft(curX, curY)) {
                        lastX = curX;
                        lastY = curY;
                        curX -= 1;
                    } else if(avg_x < 0 && canMoveRight(curX, curY)) {
                        lastX = curX;
                        lastY = curY;
                        curX += 1;
                    }
                    draw_character(curX, curY, AVATAR);
                    draw_character(lastX, lastY, EMPTY_SPACE);

                }              

                //Checks to see if the character can move down
                if(canMoveDown(curX, curY)) {
                    //Updates the last position
                    lastY = curY;
                    lastX = curX;

                    curY += 1;
                    
                    draw_character(curX, curY, AVATAR);
                    draw_character(lastX, lastY, EMPTY_SPACE);

                }

                if(checkIfStuck(curX, curY) == true) {
                    stuck = true;
                    break;
                } 
                // if (bucketCheck(curX, curY) == true) {
                //     stuck = true;
                //     break;
                // }

                //Add a second for the next movement
                nextMove += 200;
            }

        } while(curY < ROWS && stuck != true); // Change this to end game at right time

        /* Print the win message */


        /* This function is used to cleanup the Ncurses environment.
        Without it, the characters printed to the screen will persist
        even after the progam terminates */
        endwin();

    }

    if(stuck == true) {
        printf("You lose :(\n");
    } else {
        printf("YOU WIN!\n");
    }

}

/*------------------------------------ Part 2 ------------------------------------*/

/*	POST: Generates a random maze structure into MAZE[][]
    You will want to use the rand() function and maybe use the output %100.
    You will have to use the argument to the command line to determine how
    difficult the maze is (how many maze characters are on the screen). */
void generate_maze(int difficulty) {
    srand(time(NULL));

    int wallOrNot = rand()%100;

    for(int i = 0; i < ROWS; i++) {
        for(int j = 0; j < COLUMNS; j++) {

            wallOrNot = rand()%100;
            if(wallOrNot <= difficulty) {
                MAZE[i][j] = WALL;
            } else {
                MAZE[i][j] = EMPTY_SPACE;
            }

        }
    }

    MAZE[ROWS/2][0] = AVATAR;
}

/*	PRE: MAZE[][] has been initialized by generate_maze()
    POST: Draws the maze to the screen */
void draw_maze(void) {
    for(int i = 0; i < ROWS; i++) {
        for(int j = 0; j < COLUMNS; j++) {

            draw_character(i, j, MAZE[i][j]);

        }
    }
}

/* Looks under the character and determines if it can move down */
bool canMoveDown(int x, int y) {
    if(MAZE[x][y + 1] != WALL) {
        return true;
    } else {
        return false;
    }
}

bool canMoveLeft(int x, int y) {
    if(MAZE[x-1][y] != WALL && x <= COLUMNS) {
        return true;
    } else {
        return false;
    }
}

bool canMoveRight(int x, int y) {
    if(MAZE[x+1][y] != WALL && x <= COLUMNS) {
        return true;
    } else {
        return false;
    }
}

/* Returns true if stuck */
bool checkIfStuck(int x, int y) {
    if(canMoveLeft(x,y) == true || canMoveRight(x,y) == true || canMoveDown(x,y) == true) {
        return false;
    }
    sleep(1);
    return true;
}

/* Returns true if its stuck, checks for if its stuck in a bucket */
// bool bucketCheck(int x, int y) {
//     int xTemp = x;
//     int yTemp = y;
//     int leftWall = -1;
//     int rightWall = -1;
//     bool hole = false;
    
//     while(leftWall == -1 || xTemp != 0) { //Finds the left wall
//         if(MAZE[xTemp][y] == WALL) {
//             leftWall = xTemp;
//         }
//         xTemp--;
//     }

//     if(leftWall == -1 || leftWall == 0) {
//         return false;
//     }

//     xTemp = x;

//     while(rightWall == -1 || xTemp != COLUMNS) { //Finds the right wall
//         if(MAZE[xTemp][y] == WALL) {
//             rightWall = xTemp;
//         }
//         xTemp++;
//     }

//     if(rightWall == -1 || rightWall == COLUMNS) {
//         return false;
//     }

//     int i = 1;
//     while(xTemp != rightWall || xTemp != COLUMNS) {
//         if(MAZE[leftWall+i][y-1] != WALL) {
//             hole == true;
//             return false;
//         }
//         i++;
//     }

//     if(hole == false) {
//         return true;
//     }
// }

/*------------------------------------ Part -1 -----------------------------------*/

/* Updates the buffer with the new_item and returns the computed
moving average of the updated buffer */
double m_avg(double buffer[], int avg_size, double new_item) {

    //Basically just a for loop, but a stdio function
    memmove(&buffer[0], &buffer[1], avg_size * sizeof(double));

    buffer[avg_size] = new_item;

    double sum = 0;
    for (int i = 0; i < avg_size; i++) {
        sum += buffer[i];
    }

    return sum / avg_size;
}

/* 	PRE: 0 < x < COLUMNS, 0 < y < ROWS, 0 < use < 255
    POST: Draws character use to the screen and position x,y
    THIS CODE FUNCTIONS FOR PLACING THE AVATAR AS PROVIDED.
    DO NOT NEED TO CHANGE THIS FUNCTION. */
void draw_character(int x, int y, char use) {
    if (x < 0) {
        x = 0;
    } else if (x > COLUMNS) {
        x = COLUMNS;
    }

    if (y < 0) {
        y = 0;
    } else if (y > ROWS) {
        y = ROWS;
    }


    mvaddch(y,x,use);
    refresh();
}
