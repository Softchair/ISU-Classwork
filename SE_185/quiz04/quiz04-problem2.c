/**
 * Author: Camden Fergen and Jun-sang Kim
**/

#include <stdio.h>
#include <string.h>

void main() {
    char password[50];
    printf("Please enter a password: ");
    scanf("%s", password);

    for(int i = 0; i < strlen(password); i++) {
        switch(password[i]) {
            case 'i': password[i] = '1'; break;
            case 'a': password[i] = '@'; break;
            case 'm': password[i] = 'M'; break;
            case 'B': password[i] = '8'; break;
            case 's': password[i] = 'S'; break;
        }
    }

    printf("\nYour updated password: %s", strcat(password, "!"));
}