/**
 * Author: Jun-sang Kim and Camden Fergen 
 **/

#include <stdio.h>
#include <math.h>

void calcGrade();
void letter();

void main () {
    double stuGrades[2][4];

    printf("Please enter assignment grades for student 1 and student 2\n");

    for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 4; j++) {
            printf("Student-%d Assignment%d grade: ", i+1, j+1);
            scanf("%lf", &stuGrades[i][j]);
        }
    }

    printf("\n");

    double stu1 = 0;
    for (int i = 0; i < 4; i++) {
        stu1 += stuGrades[0][i];
    }
    stu1 = stu1 / (double)4;
    calcGrade(stu1, 1);
    letter(stu1, 1);

    double stu2 = 0;
    for (int i = 0; i < 4; i++) {
        stu2 += stuGrades[1][i];
    }
    stu2 = stu2 / (double)4;
    calcGrade(stu2, 2);
    letter(stu2, 2);
}

void calcGrade (double average, int stuID) {
    printf("Student %d Avg = %.2lf\n", stuID, average);
}

void letter (double g, int stuID) {
    printf("Student %d Grade =", stuID);
    if (g >=  85.0) {
        printf(" A\n");
    } else if (g >= 75.0) {
        printf(" B\n");
    } else if (g >= 60.0) {
        printf(" C\n");
    } else {
        printf(" F\n");
    }
}