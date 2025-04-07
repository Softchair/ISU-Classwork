#include <stdio.h>
#include <stdlib.h>

int main() {

    int num = 5;

    int* myPtr = &num;

    printf("num is stored at: %p\n", &num);
    printf("myPtr is stored at: %p\n", &myPtr);
    printf("num holds the value: %d\n", num);
    printf("myPtr holds the value: %d\n", *myPtr);
    printf("myPtry points to this value: %p", myPtr);
    
}