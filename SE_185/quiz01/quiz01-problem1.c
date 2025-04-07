/**
 * Author: Jun-sang Kim and Camden Fergen 
 **/

#include <stdio.h>
#include <math.h>

int main (void)
{
	long long int phoneNumber;
	
	printf("\nPlease enter the 10 digits phone number: ");
	scanf("%lld", &phoneNumber);
	
	int areaCode = (phoneNumber / 10000000);
	int exchangeCode = ((phoneNumber / 10000) % 1000);
	int userNumber = (phoneNumber % 10000);
	
	printf("\nThe area code is: %d", areaCode);
	printf("\nThe exchange code is: %d", exchangeCode);
	printf("\nThe user number is: %d\n", userNumber);
	
	return 0;
}