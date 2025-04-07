#include <stdio.h>
#include <math.h>

typedef struct imaginary {
    int real;
    int imagin;
} iNum;

iNum addINum(iNum num1, iNum num2) {
    iNum total;
    total.real = num1.real + num2.real;
    total.imagin = num1.imagin + num2.imagin;
    return total;
}

iNum subINum(iNum num1, iNum num2) {
    iNum total;
    total.real = num1.real - num2.real;
    total.imagin = num1.imagin - num2.imagin;
    return total;
}

iNum mulINum(iNum num1, iNum num2) {
    iNum total;
    total.real = (num1.real * num2.real) - (num1.imagin * num2.imagin);
    total.imagin = (num1.real * num2.imagin) + (num2.real * num1.imagin);
    return total;
}

void main() {
    iNum num1;
    iNum num2;

    printf("Enter real and imaginary values of number 1: ");
    scanf("%d %d", &num1.real, &num1.imagin);

    printf("Enter real and imaginary values of number 2: ");
    scanf("%d %d", &num2.real, &num2.imagin);

    printf("%d %d %d %d", num1.real, num1.imagin, num2.real, num2.imagin);

    iNum addResult = addINum(num1, num2);
    printf("\nADD = %d+%di", addResult.real, addResult.imagin);

    iNum subResult = subINum(num1, num2);
    printf("\nSUB = %d%+di", subResult.real, subResult.imagin);

    iNum mulResult = mulINum(num1, num2);
    printf("\nMUL = %d%+di", mulResult.real, mulResult.imagin);
}