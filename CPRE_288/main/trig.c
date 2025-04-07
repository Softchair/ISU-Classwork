///*
// * trig.c
// *
// *  Created on: Nov 29, 2023
// *      Author: bddunne
// */
//
#include <Math.h>
#define PI 3.14159265


float objectXCalc(float distance, float startX, float ang)
{
    float endX;
    float diffX;
    float convert = PI/180;

    diffX = cos(convert * ang) * distance;

    endX = startX + diffX;

    return endX;
}

float objectYCalc(float distance, float startY, float ang)
{
    float endY;
    float diffY;
    float convert = PI/180;

    diffY = sin(convert * ang) * distance;

    endY = startY + diffY;

    return endY;
}



