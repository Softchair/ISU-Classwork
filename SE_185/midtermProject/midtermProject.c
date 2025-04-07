/*----------------------------------------------------------------------------
-		         SE 185: Midterm Project   	               -
1 - Camden Fergen - Role: Quiz Creator and debugger - Participation: 50%
	- Created quizzes and wrote the program overall.
2 - Jun-sang Kim - Role: Quiz Creator and debugger - Participation: 50%
	- Created quizzes and wrote the program overall.														                                     -
-----------------------------------------------------------------------------*/

/*----------------------------------------------------------------------------
-								Includes									                                   -
-----------------------------------------------------------------------------*/

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <math.h>

/*----------------------------------------------------------------------------
-	                            Functions Prototypes                                   -
-----------------------------------------------------------------------------*/

int quiz1();
int quiz2();
int quiz3();

/*----------------------------------------------------------------------------
-	                                Notes                                    -
-----------------------------------------------------------------------------*/

// Compile with gcc midtermProject.c -o proj

/*----------------------------------------------------------------------------
-								Implementation								                               -
-----------------------------------------------------------------------------*/

void main()
{
    int userNum = 0;
	int attempts = 0;
    int scores[50];
    int quizRand;
    bool isDone = false;
    bool taken1 = false, taken2 = false, taken3 = false;
    char end[5];

    printf("\nWelcome to midterm quiz!\n");
    printf("\nThere are 3 quizzes in total and each quiz has three questions. Each question worth 5 points.\n");

    printf("\nEnter a number from 1 to 9 below which will take you to take one quiz randomly.\n\nYou will be taking the same quiz until you get all three questions correct.");
    printf("\n\nYour Number: ");
		
	scanf("%d", &userNum);
	
    if (userNum >= 10 || userNum <= 0) {
        printf("\nSorry thats not a number between 0 and 10, try again: ");
        scanf("%d", &userNum);
    }
		
	srand(userNum);

    while (!isDone) {
		quizRand = rand() % 10;
		
		if (quizRand < 4) {
            if (taken1 == true) {
                continue;
            }

            int j = 0; //Used for amount of attempts for THIS quiz
            int temp = attempts; //Starting point for THIS quiz

            scores[attempts] = quiz1();
            while (scores[attempts] != 15) {
                attempts++;
                scores[attempts] = quiz1();
            }

            double totalScore = 0;
		    for (int i = temp; i <= attempts; i++) {
                j++;
                totalScore += scores[i];
            }

            totalScore = totalScore / j;

            printf("\nAverage score: %.1lf\n", totalScore);
            
            taken1 = true;
		} else if (quizRand > 3 && quizRand < 7) {
            if (taken2 == true) {
                continue;
            }

            int j = 0; //Used for amount of attempts for THIS quiz
            int temp = attempts; //Starting point for this quiz

            scores[attempts] = quiz2();
            while (scores[attempts] != 15) {
                attempts++;
                scores[attempts] = quiz2();
            }

            double totalScore = 0;
		    for (int i = temp; i <= attempts; i++) {
                j++;
                totalScore += scores[i];
            }
            
            totalScore = totalScore / j;

            printf("\nAverage score: %.1lf\n", totalScore);

            taken2 = true;
        } else if (quizRand > 6) {
            if (taken3 == true) {
                continue;
			}

            int j = 0; //Used for amount of attempts for THIS quiz
            int temp = attempts; //Starting point for this quiz

            scores[attempts] = quiz3();
            while (scores[attempts] != 15) {
                attempts++;
                scores[attempts] = quiz3();
            }

            double totalScore = 0;
		    for (int i = temp; i <= attempts; i++) {
                j++;
                totalScore += scores[i];
            }

            totalScore = totalScore / j;

            printf("\nAverage score: %.1lf\n", totalScore);

            taken3 = true;
        } else {
            printf("error\n");
        }

        attempts++;

        if (taken1 && taken2 && taken3) {
            isDone = true;
        } else {
            printf("\nIf you would like to take another quiz, type 'yes', else type 'no' to exit the program: ");
            scanf("%s", &end);
            if (strcmp(end, "no") == 0) {
                isDone = true;
            } else {
                continue;
            }
        }
		
    }

    
    printf("\nThanks for playing! You can see your average score below.\n");

    double totalScore = 0;
		for (int i = 0; i < attempts; i++) {
        totalScore += scores[i];
    }

    totalScore = totalScore / attempts;

    printf("\nAverage score: %.1lf\n", totalScore);
}

int quiz1() {
    char answer[10];
    int score = 0;
    printf("\nQuiz 1: \n\n");

    printf("1. The following prints second letter of exstr. (True / False)\n\nint main() {\n    char exstr[] = 'Awesome';\n    printf(\"test\", exstr[2]);\n}\n\nYour Answer: ");
    scanf("%s", answer); //false ^
    if (strcmp(answer, "false") == 0) {
        printf("\nThats right!\n\n");
        score += 5;
    } else {
        printf("\nSorry, thats not the correct answer.\n\n");
    }

    printf("2. How many iterations are in the following loop? (Mutiple Choice) \n\nfor (int i = 0; i < 10; i++) {\n   printf(\"%d\", i);\n }");
    printf("\n\n1. 0\n2. 9\n3. 10\n4. Infinite\n\nYour Answer: ");
    scanf("%s", answer); //3 ^
    if (strcmp(answer, "3") == 0) {
        printf("\nThats right!\n\n");
        score += 5;
    } else {
        printf("\nSorry, thats not the correct answer.\n\n");
    }

    printf("3. The following prints the odd numbers from 1 to 100. The output should be in the following format: 1, 3, 5, 7, ...\nFill in the blank to successfully finish the following code. (Filling blank)");
    printf("\n\nfor (int i = 1; i < (blank); i += 2) {\n    printf(\"%d, \", i);\n}\n\nYour Answer: ");
    scanf("%s", answer); //101 ^
    if (strcmp(answer, "101") == 0) {
        printf("\nThats right!\n");
        score += 5;
    } else {
        printf("\nSorry, thats not the correct answer.\n");
    }

    printf("\nCongrats, you finished Quiz 1!\n");

    return score;
}

int quiz2() {
    char answer[10];
    int score = 0;
    printf("\nQuiz 2: \n\n");

    printf("1. The following is the valid variable declaration in C programming. (Ture / False)\n");
    printf("\n11Keyboard\n\nYour Answer: ");
    scanf("%s", answer); //false ^
    if (strcmp(answer, "false") == 0) {
        printf("\nThats right!\n\n");
        score += 5;
    } else {
        printf("\nSorry, thats not the correct answer.\n\n");
    }

    printf("2. What size array will the compiler create for the following string? (Multiple Choice)\n\n");
    printf("char exstr[] = \"seven\";\n\n");
    printf("1. 10\n2. 6\n3. 7\n4. Does not compile\n\nYour Answer: ");
    scanf("%s", answer); //2 ^
    if (strcmp(answer, "2") == 0) {
        printf("\nThats right!\n\n");
        score += 5;
    } else {
        printf("\nSorry, thats not the correct answer.\n\n");
    }

    printf("3. Fill in the blank in order to compile the program without any error. Two blanks have a same answer. (Filling blank)\n\n");
    printf("long int i; \nint main() {\n    scanf(\"(blank)\", &i);\n   printf(\"(blank)\", i);\n}\n\nYour Answer: ");
    scanf("%s", answer); //%ld ^
    if (strcmp(answer, "%ld") == 0) {
        printf("\nThats right!\n");
        score += 5;
    } else {
        printf("\nSorry, thats not the correct answer.\n");
    }

    printf("\nCongrats, you finished Quiz 2!\n");

    return score;
}

int quiz3() {
    char answer[10];
    int score = 0;
    printf("\nQuiz 3: \n\n");

    printf("1. When you convert the decimal value 547 into binary value, you get 001000100011. (True / False)\n\nYour Answer: ");
    scanf("%s", answer); //true ^
    if (strcmp(answer, "true") == 0) {
        printf("\nThats right!\n\n");
        score += 5;
    } else {
        printf("\nSorry, thats not the correct answer.\n\n");
    }

    printf("2. What is the output of the following? (Mutiple Choice)\n\n");
    printf("int main() {\n    int x = 7;\n    int y = 10;\n    int z = 3;\n    int output;\n    \n    output = (x + y) + (y / z) - (y / x);\n    printf(\"%d \", output);\n}\n\n");
    printf("1. Error\n2. 12\n3. 11\n4. 13\n\nYour Answer: ");
    scanf("%s", answer); //2 ^
    if (strcmp(answer, "2") == 0) {
        printf("\nThats right!\n\n");
        score += 5;
    } else {
        printf("\nSorry, thats not the correct answer.\n\n");
    }

    printf("3. Fill in the blank to copy exstr into exstr1. (Filling blank)\n\n");
    printf("(blank)(exstr1, exstr);\n\nYour Answer: ");
    scanf("%s", answer); //strcpy ^
	if (strcmp(answer, "strcpy") == 0) {
        printf("\nThats right!\n");
        score += 5;
    } else {
        printf("\nSorry, thats not the correct answer.\n");
    }
    
    printf("\nCongrats, you finished Quiz 3!\n");

    return score;
}
