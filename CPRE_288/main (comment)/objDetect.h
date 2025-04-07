/*
 * objDetect.h
 *
 *  Created on: Dec 5, 2023
 *      Author: bddunne
 */

#ifndef OBJDETECT_H_
#define OBJDETECT_H_

typedef struct{
    float objPosX;
    float objPosY;
    float botPosX;
    float botPosY;
    float width;
    int ang;
    float distance;
    int objID;
    int kCount;
}objects;

extern objects obj[180];

int detectObj();

void printObj(int c);



#endif /* OBJDETECT_H_ */
