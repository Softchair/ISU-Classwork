/**
 * Author: Camden Fergen and Jun-sang Kim
**/

#include <stdio.h>

double DrivingCost();

int main() {
    double drivenMiles;
    double milesPerGallon;
    double dollarsPerGallon;

    printf("Enter driven Miles: ");
    scanf("%lf", &drivenMiles);

    printf("Enter Miles per Gallon: ");
    scanf("%lf", &milesPerGallon);

    printf("Enter dollars per Gallon: ");
    scanf("%lf", &dollarsPerGallon);

    printf("Driving cost = %.2lf", DrivingCost(drivenMiles, milesPerGallon, dollarsPerGallon));
}

double DrivingCost(double miles, double mpg, double dpg) {
    
    double temp = miles / mpg; //Takes miles divied by mpg to get gallons used
    temp = temp * dpg; //Takes gallons used times how much per gallon
    return temp;
}