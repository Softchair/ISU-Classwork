#include <math.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdbool.h>
#include "trig.h"


float objectDist(float startX, float startY, float endX, float endY)
{
    float totalDist;
    float xcalc = endX - startX;
    float ycalc = endY - startY;

    totalDist = sqrt( pow(xcalc, 2) * pow(ycalc, 2));

    return totalDist;
}

float objectAng(float startX, float startY, float endX, float endY)
{
    float totalAng;
    float xcalc = endX - startX;
    float ycalc = endY - startY;

    totalAng = atan(ycalc/xcalc);

    totalAng = totalAng * (180/M_PI);

    return totalAng;
}
