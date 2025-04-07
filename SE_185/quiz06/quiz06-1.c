/*---------------------------------------
-   SE 185: Quiz06	 
-	Name: Jun-Sang Kim, Camden Fergen 														 													 
----------------------------------------*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

int main(void) {
	
	char first_name[50], last_name[50];
	char *full_name = NULL;
	int num_letters = 0;
	
	printf("What's your first name?\n");
	fgets(first_name, 50, stdin);
	
	printf("What's your last name?\n");
	fgets(last_name, 50, stdin);

	printf("\n");
	
	//Int for how long the two names are, plus a space
	int fullNameLength = strlen(first_name) + strlen(last_name) + 1;

	full_name = (char*)malloc(fullNameLength * sizeof(char));

	//Using strncat to avoid the \0 at the end of a string
	strncat(full_name, first_name, strlen(first_name) - 1); 
	strcat(full_name, " ");
	strcat(full_name, last_name);

	printf("First name: %s", first_name);

	printf("Last name: %s", last_name);

	printf("Full name: %s\n", full_name);

	int i = 0;
	char *p;

	for (p = full_name, i = 0; *p != '\0'; p++, i++) {
		if (isalpha(full_name[i])) {
			num_letters += 1;
		}
	}

	printf("Num letters: %d", num_letters);
		
	free(full_name);
	
	return 0;
}