#include <stdio.h>
#include <stdlib.h>

typedef struct time {
	int hr;
	int min;
	int sec;
} time;

void convTime(time* mileTime, int seconds);
void printTime(time* mileTime, int scd);

void main() {
	
	time *mileTime;
	mileTime = (time*)malloc(sizeof(time));
	
	int seconds;
	
	printf("Total seconds: ");
	scanf("%d", &seconds);
	
	convTime(mileTime, seconds);
	printTime(mileTime, seconds);
}

void convTime(time* mileTime, int seconds) {
	mileTime -> hr = seconds / 3600;
	mileTime -> min = (seconds % 3600) / 60;
	mileTime -> sec = ((seconds % 3600) % 60);
}

void printTime(time* mileTime, int scd) {
	printf("%d seconds = %d hr %d min %d sec\n", scd, (*mileTime).hr, (*mileTime).min, (*mileTime).sec);
}