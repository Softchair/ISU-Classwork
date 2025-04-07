/*---------------------------------------
-   SE 185: Quiz05 Problem 1	 
-	Name: Jun-Sang Kim, Camden Fergen 														 													 
----------------------------------------*/

#include<stdio.h>

int main() {
	double hw[5];
	double exam[3];
	double hw_avg[1];
	double exam_avg[1]; 
	double weighted_avg[1];
	
	printf("\n");
	
	for(int i = 0; i < 5; i++) {
		printf("Enter your grade (%%) for HW #0%d: ", i+1);
		scanf("%lf", &hw[i]);
	}
	
	for(int j = 0; j < 3; j++) {
		printf("Enter your grade (%%) for Exam #0%d: ", j+1);
		scanf("%lf", &exam[j]);
	}
	
	for(int k = 0; k < 5; k++) {
		hw_avg[0] = hw_avg[0] + hw[k];
	}
	
	hw_avg[0] = hw_avg[0] / 5;
	
	for(int l = 0; l < 3; l++) {
		exam_avg[0] = exam_avg[0] + exam[l];
	}
	
	exam_avg[0] = exam_avg[0] / 3;
	
	weighted_avg[0] = (0.40 * hw_avg[0]) + (0.60 * exam_avg[0]);
	
	printf("\nFinal grade = %.2lf%%\n", weighted_avg[0]);
	
	return 0;
}