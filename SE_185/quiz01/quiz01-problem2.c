/**
 * Author: Camden Fergen and Jun-sang Kim
 * 
 * This program finds the distance between two euclidean points
 * The current points are hard coded for Mike, Mary, Gary, and Logan
 * You cannot enter your own points
**/

#include <stdio.h>
#include <math.h>

int main() {
    double findEuclidean(double x, double y);

    double eDistance [4];
    char names[4][6] = {"Mike", "Mary", "Gary", "Logan"};

    eDistance[0] = findEuclidean(22.05, 85.10); //Mike
    eDistance[1] = findEuclidean(43.25, 9.80); //Mary
    eDistance[2] = findEuclidean(2.55, 72.86); //Gary
    eDistance[3] = findEuclidean(15.15, 40.40); //Logan

    for (int i = 0; i < 4; i++) {
        printf("The E distance for %s is: %lf\n", names[i], eDistance[i]);
    }

    return 0;
}

double findEuclidean(double x, double y) {
    double schoolX = 15.55;
    double schoolY = 55.15;

    return sqrt(pow(schoolX - x, 2) + pow(schoolY - y, 2));
}