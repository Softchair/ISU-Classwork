/**
 * Author: Camden Fergen and Jun-sang Kim
**/

#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool compare();

int main() {
    const char userName[18] = "se185@iastate.edu";
    const char password[6] = "ds4rd";

    char inputUserName[30];
    char inputPassword[30];

    printf("Enter your password: ");
    scanf("%s", inputUserName);

    printf("Enter your password: ");
    scanf("%s", inputPassword);

    if (compare(userName, inputUserName) && compare(password, inputPassword)) {
        printf("Success!");

    } else {
        printf("Username or password is incorrect");
    }
}

bool compare(char base[], char input[]) {
    if (strcmp(base, input) != 0) {
        return false;
    }
    return true;
}