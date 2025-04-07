/*---------------------------------------
-   SE 185: Quiz02 Problem 1	 
-	Name: Jun-Sang Kim, Camden Fergen 														 													 
----------------------------------------*/

#include<stdio.h>
#include<string.h>

int main() {
	char firstName[20];
	char lastName[20];
	char major[15][15];
	char yearinCollege[10];
	int gradYear;
	
	printf("Enter your first name: ");
	scanf("%s", &firstName);
	printf("Enter the last name: ");
	scanf("%s", &lastName);
	printf("Enter your major: ");
	scanf("%s", &major);
	printf("Enter year in college (ex. Freshman, sophomore, etc.): ");
	scanf("%s", &yearinCollege);
	printf("Enter your graudation year: ");
	scanf("%d", &gradYear);
	
	printf("\n\nName (last, first): Name (%s, %s)", lastName, firstName);
	printf("\nMajor: %s", major);
	printf("\nYear: %s", yearinCollege);
	printf("\nGraduation year: %d", gradYear);
	
	return 0;
}