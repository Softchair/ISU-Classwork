// Lab 09 DS4Talker Skeleton Code       

#include <ctype.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <ncurses/ncurses.h>
#include <unistd.h> //For sleep function
#define WORDLENGTH 11
#define MAXWORDS 100
#define DEBUGM 0   // Set this to 0 to disable debug output

// Reads words from the file into WL and trims the whitespace off of the end
// DO NOT MODIFY THIS FUNCTION
int read_words(char* WL[MAXWORDS], char* file_name);

// modifies str to trim white space off the right side
// DO NOT MODIFY THIS FUNCTION
void trimws(char* str);

/* Implemented functions */

/* Simple way to scan the controller */
void scanFunc(int* time, int* joysticks, int* temp, int* buttons);

/* Sets up the screen with 6 cols of 15 words each */
void screenSetup(char *wordlist[], int listLen);

/* Function that determines if the cursor should move depending on the ints stored in joystick
   Also dependent on cleanJoy to reset the joys if they are within an acceptable range to be reset
   as to not cause double movements */
void joyStickInput(int* joysticks, int* curWord, char* wordlist[], int* row, int* col, int* wasPos);

/* Takes the input of the joysticks and checks to see if its reset, then sets the
   wasPos array back to 0 for the respective movment */
void cleanJoy(int* curJoysticks, int* wasPos);

/* Makes sure a button only outputs one press per press */
void cleanButtons(int* buttons, int* isPres);

/* Prints out the sentence with the correct spacing */
void sentencePrint(char sentence[][11], int numWords);


/* Run with ./ds4rd-real.exe -d 054c:09cc -D DS4_BT -t -b -j -bt | ./lab9 wordslist-1.txt
   Compile with gcc -o lab9 lab9.c -lncurses

   This program produces a word chart that you can pick and choose from to form sentences
   with the use of a ds4 controller.*/
int main(int argc, char* argv[]) {
	char* wordlist[MAXWORDS];
	int wordcount;
	int i;
	wordcount = read_words(wordlist, argv[1]);

	if (DEBUGM) {
		printf("Read %d words from %s \n", wordcount, argv[1]);
		for (i = 0; i < wordcount; i++) {
			printf("%s,", wordlist[i]);

		}
		printf("\n");
	}

	// most of your code goes here. Do not forget to include the ncurses library 

	//Screen initialization
	initscr();
    refresh();
	screenSetup(wordlist, wordcount);

	/* Variables related to the controller and input */
	int temp; //Burner variable
	int time; //Time
	int buttons[7] = {0, 0, 0, 0, 0, 0, 0}; //tri, cir, x, squ, rightJoy, leftJoy, options
	int isPressed[7] = {0, 0, 0, 0, 0, 0, 0}; //Holds 1 if a button has been pressed
	int joysticks[4] = {0, 0, 0, 0}; //leftY leftX rightY rightX - Joysticks Y = + is down - is up || X = (- is left + is right)
	int wasPos[4] = {0, 0, 0, 0}; //Holds 1 if it was a direction, up right down left
	bool end = false;
	/* End */

	/* Sentence related variables */
	char sentence[20][11]; //Stores the sentence
	char lastWord[11]; //Holds the last word
	char curWord[11]; //Current word for capitalization
	int curWordNum = 0; //Keeps the location of the current word
	/* End */

	int curWord = 0; int row = 0; int col = 0; //For moving cursor

	wordlist[0] = "the";
	mvprintw(0, 0, "%15s >", wordlist[0]); //Starts cursor at top left

	do {
		//Scan the controller inputs and copys the last positions
		scanFunc(&time, joysticks, &temp, buttons);

		/* These two function calls determine if the cursor should move, and then make sure
		   it doesnt move more than once before resetting */
		joyStickInput(joysticks, &curWord, wordlist, &row, &col, wasPos);
		cleanJoy(joysticks, wasPos);

		//todo select words and output them to the screen

		//Function to add words/spaces to sentence array
		if(buttons[3] > 0 && isPressed[3] != 1 && curWordNum < 21) {
			isPressed[3] = 1;
			strcpy(lastWord, sentence[curWordNum - 1]);
			strcpy(sentence[curWordNum], wordlist[curWord]);
			strcpy(curWord, sentence[curWordNum]);
			curWordNum++;
		} else if(buttons[0] > 0 && isPressed[0] != 1 && curWordNum < 21) {
			isPressed[0] = 1;
			strcpy(lastWord, " ");
			strcpy(sentence[curWordNum], " ");
			curWordNum++;
		}

		//Function to remove the last word
		if(buttons[2] > 0 && isPressed[2] != 1 && curWordNum > 0) {
			isPressed[2] = 1;
			strcpy(sentence[curWordNum], "");
			curWordNum--;
			strcpy(lastWord, sentence[curWordNum - 1]);
		}

		//Function to capitalize a first letter word
		if(buttons[1] > 0 && isPressed[1] != 1) {
			isPressed[1] = 1;
			char temp[10];
			strcpy(temp, curWord);
			temp[0] = toupper(temp[0]);
			strcpy(sentence[curWordNum], curWord);

			mvprintw(23, 0, "HERE %s", temp);
			mvprintw(24, 0, "HERE2 %s", sentence[curWordNum]);
			//TODO doesnt print out correctly
		}

		//Printing sentence
		mvprintw(19, 0, "Sentence: ", sentence[0]);
		sentencePrint(sentence, curWordNum);

		//Quits the program with the options button
		if (buttons[6] > 0) {
			end = true;
		}

		cleanButtons(buttons, isPressed);

		refresh();
	} while(end != true);


	//Cleans the screen
	endwin();
	return 0;
}

/* Prints out the sentence with the correct spacing */
void sentencePrint(char sentence[][11], int numWords) {
	//Cleans the space where the sentence go incase a word is deleted
	for(int i = 10; i < 150; i++) {
		mvprintw(19, i, " ");
	}

	//Iterates over the sentence array and prints it out
	int nextWordSpace = 10;
	for(int i = 0; i < numWords; i++) {
		mvprintw(19, nextWordSpace, "%s", sentence[i]);
		nextWordSpace += strlen(sentence[i]);
	}
}

/* Makes sure a button only outputs one press per press */
void cleanButtons(int* buttons, int* isPres) {
	for(int i = 0; i < 7; i++) {
		if(buttons[i] == 0) {
			isPres[i] = 0;
		}
	}
}

/* Takes the input of the joysticks and checks to see if its reset, then sets the
   wasPos array back to 0 for the respective movment */
void cleanJoy(int* curJoysticks, int* wasPos) {
	//This checks both joysticks as you can use both to move around
	if((curJoysticks[0] > -10 && curJoysticks[0] < 10) && (curJoysticks[2] > -10 && curJoysticks[2] < 10)) {
		wasPos[1] = 0;
		wasPos[3] = 0;
	}
	if((curJoysticks[1] > -10 && curJoysticks[1] < 10) && (curJoysticks[3] > -10 && curJoysticks[3] < 10)) {
		wasPos[0] = 0;
		wasPos[2] = 0;
	}
}

/* Function that determines if the cursor should move depending on the ints stored in joystick
   Also dependent on cleanJoy to reset the joys if they are within an acceptable range to be reset
   as to not cause double movements */
void joyStickInput(int* joysticks, int* curWord, char* wordlist[], int* row, int* col, int* wasPos) {
	bool moved = false; //If the cursor moved
	int lastRow = *row; int lastCol = *col; //Where the cursor was
	int lastWord = *curWord;
	int tol = 100; //Tolerance for if it should move

	/* 1 - The first argument in the if statements is to see if it should move, it has a variable
	   called tol that you can change if you feel like it should be easier to move
	   2 - The seconc argument is to check if it already moved in a direction this is used
	   in conjuonction with the cleanJoy function to make sure you cant move if the joystick
	   hasnt been moved back to 0
	   3 - The last argument is to make sure you cannot go off the screen in terms of the
	   6 rows there are and 15 words per col */ 
	if((joysticks[0] > tol || joysticks[2] > tol) && (wasPos[1] != 1) && (*col < 100)) { 
		moved = true;
		wasPos[1] = 1;
		*col += 20;
		*curWord += 15;
	} else if((joysticks[0] < -tol || joysticks[2] < -tol) && (wasPos[3] != 1) && (*col > 0)) {
		moved = true;
		wasPos[3] = 1;
		*col -= 20;
		*curWord -= 15;
	} else if((joysticks[1] < -tol || joysticks[3] < -tol) && (wasPos[0] != 1) && (*row > 0)) {
		moved = true;
		wasPos[0] = 1;
		*row -= 1;
		*curWord -= 1;
	} else if((joysticks[1] > tol || joysticks[3] > tol) && (wasPos[2] != 1) && (*row < 14)) {
		moved = true;
		wasPos[2] = 1;
		*row += 1;
		*curWord += 1;
	}

	/* This if statement checks to see if it moved, and if it did cleans the last word so
	   there is not a floating > */
	if(moved) {
		mvprintw(lastRow, lastCol, "%15s  ", wordlist[lastWord]);
	}

	mvprintw(*row, *col, "%15s >", wordlist[*curWord]);
	refresh();
}

/* Simple way to scan the controller */
void scanFunc(int* time, int* joysticks, int* temp, int* buttons) {
	scanf("%d, %d,%d,%d,%d, %d,%d,%d,%d,%d,%d,%d,%d, %d, %d, %d, %d", time, &buttons[0], &buttons[1], &buttons[2], &buttons[3], &buttons[4], &buttons[5], &buttons[6], temp, temp, temp, temp, temp, &joysticks[0], &joysticks[1], &joysticks[2], &joysticks[3]);
}

/* Sets up the screen with 6 cols of 15 words each */
void screenSetup(char *wordlist[], int listLen) {
	int col = 0;
	int wordOn = 0; //Word the program is on
	for(int row = 0; wordOn < listLen; row++) { //Iterates over wordlist and prints them out
		if(row >= 15) { //Max of 15 words per col
			col += 20; //Moves col to next size
			row = 0; //Sets row back to 0
		}
		mvprintw(row, col, "%15s", wordlist[wordOn]);
		wordOn++;
	}
	refresh();
}

/* End implemented functions */

// DO NOT MODIFY THIS FUNCTION!
void trimws(char* str) {
	int length = strlen(str);
	int x;
	if (length == 0) return;
	x = length - 1;
	while (isspace(str[x]) && (x >= 0)) {
		str[x] = '\0';
		x -= 1;
	}
}


// DO NOT MODIFY THIS FUNCTION!
int read_words(char* WL[MAXWORDS], char* file_name)
{
	int numread = 0;
	char line[WORDLENGTH];
	char *p;
	FILE* fp = fopen(file_name, "r");
	while (!feof(fp)) {
		p = fgets(line, WORDLENGTH, fp);
		if (p != NULL) 
		{
			trimws(line);
			WL[numread] = (char *)malloc(strlen(line) + 1);
			strcpy(WL[numread], line);
			numread++;
		}
	}
	fclose(fp);
	return numread;
}