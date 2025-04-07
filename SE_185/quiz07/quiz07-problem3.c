#include <stdio.h>

void examStat();

int main() {
    int midterm1Score[30];

    for (int i = 0; i < 30; i++) {
        printf("Enter quiz scores for student %d: ", i + 1);
        scanf(" %d", &midterm1Score[i]);
    }

    int result[5];
    examStat(midterm1Score, 30, result, 5);

    printf("Average score: %d\n", result[0]);
    printf("Max score: %d\n", result[1]);
    printf("Min score: %d\n", result[2]);
    printf("Number of fails: %d\n", result[3]);
    printf("Number of A's: %d\n", result[4]);

    return 0;
}

void examStat(int scores[], int sizeScores, int result[], int sizeResult) {
    int total = 0;

    int max = scores[0];
    int min = scores[0];

    int fails = 0; //Stores fails
    int A = 0; //Stores A scores

    for (int i = 0; i < sizeScores; i++) {
        int cur = scores[i]; //Current score

        total += cur; //Add current to total for average

        if (cur < 60) { //For fails
            fails += 1;
        } else if (cur >= 93) { //For A scores
            A += 1;
        }

        if (cur > max) { //For finding max
            max = cur;
        }
        if (cur < min) { //For finding min
            min = cur;
        }
    }

    result[0] = total / sizeScores;
    result[1] = max;
    result[2] = min;
    result[3] = fails;
    result[4] = A;
}