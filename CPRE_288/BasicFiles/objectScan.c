/*
 * File for using the two sensors on the robot to detect objects
 */

#include <cyBot_Scan.h>
#include <objectScan.h>


/*
 * Structs used in this file
 */

struct scanData {
    int degree;
    float distance;
};

struct object {
    int startDeg;
    int endDeg;
    float distance;
    float width; //endDeg-startDeg
};

//Detects any objects and adds them to array
//Returns the len of the objs array
int detectObjectsBasic(struct scanData data[], struct object (*objs)[], int len) {
    int i;
    int j = 0;
    float distance = -1;
    int startDeg = 0;
    float startDis = 0;

    int q;
    int avgDis = 0;
    for(q = 0; q < 90; q++) {
        avgDis += data[q].distance;
    }
    distance = avgDis/90;

    //Workers note, maybe roll over all the data and find an average amount for the wall
    //or maybe a shortest distance to the wall to determine where objects start
    //This could be done if you iterate over all the data, and slowly add up the averages when they are within
    //x cm of the last one, and if its greater skip over it. The find and compare the shorest value to the avg
    //and then use that as the distance above.

    //Iterate over all the data and find objects
    for(i = 1; i < len; i++) {
        if(data[i].distance < distance) {

            startDeg = data[i].degree;
            startDis = data[i].distance;
            while(startDis < data[i].distance) {
                i++;
            }

            i--;

            //------ Start Create a new obj struct ---- //
            // DO NOT MODIFY

            //Create a new object with start and end degree
            struct object curObj = {startDeg, data[i].degree, -1, -1};
            //Assign the width based on start and end deg
            curObj.width = curObj.endDeg - curObj.startDeg;

            //Iterate over the obj to find avg distance
            int k; int w = 0; int tolDistance = 0;
            for(k = curObj.startDeg; k <= curObj.endDeg; k++) {
                tolDistance += data[k].distance;
                w++;
            }
            //Finds avg distance
            float avgDistance = tolDistance/w;

            //Assigns the avg distance of the obj
            curObj.distance = avgDistance;

            (*objs)[j] = curObj;
            j++;

            //DO NOT MODIFY
            //------ End Create a new obj struct ---- //
            if (j > 10) {
                return j;
            }
        }
    }

    return j;
}
