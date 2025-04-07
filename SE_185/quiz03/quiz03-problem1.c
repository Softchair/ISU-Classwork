/*---------------------------------------
-   SE 185: Quiz03 Problem 1	 
-	Name: Jun-Sang Kim, Camden Fergen 														 													 
----------------------------------------*/

#include<stdio.h>
#include<math.h>

int main() {

	double gradePercentage;
	
	printf("\nPlease eneter your grading percentage: ");
	scanf("%lf", &gradePercentage);
	
	if ((gradePercentage <= 100) && (gradePercentage >= 93)) {
		printf("\nYour grade letter is A.\n");
	}
	else if ((gradePercentage < 93) && (gradePercentage >= 90)) {
		printf("\nYour grade letter is A-.\n");
	}
	else if ((gradePercentage < 90) && (gradePercentage >= 87)) {
		printf("\nYour grade letter is B+.\n");
	}
	else if ((gradePercentage < 87) && (gradePercentage >= 83)) {
		printf("\nYour grade letter is B.\n");
	}
	else if ((gradePercentage < 83) && (gradePercentage >= 80)) {
		printf("\nYour grade letter is B-.\n");
	}
	else if ((gradePercentage < 80) && (gradePercentage >= 77)) {
		printf("\nYour grade letter is C+.\n");
	}
	else if ((gradePercentage < 77) && (gradePercentage >= 73)) {
		printf("\nYour grade letter is C.\n");
	}
	else if ((gradePercentage < 73) && (gradePercentage >= 70)) {
		printf("\nYour grade letter is C-.\n");
	}
	else if ((gradePercentage < 70) && (gradePercentage >= 67)) {
		printf("\nYour grade letter is D+.\n");
	}
	else if ((gradePercentage < 67) && (gradePercentage >= 63)) {
		printf("\nYour grade letter is D.\n");
	}
	else if ((gradePercentage < 63) && (gradePercentage >= 60)) {
		printf("\nYour grade letter is D-.\n");
	}
	else if ((gradePercentage < 60) && (gradePercentage >= 0)) {
		printf("\nYour grade letter is F.\n");
	}
	else {
		printf("\nYour grade must be between 0 percent and 100 percent inclusively.\n");
	}
	
	return 0;
	}