/*
 * doScan.h
 *
 *  Created on: Nov 29, 2023
 *      Author: bddunne
 */

#ifndef DOSCAN_H_
#define DOSCAN_H_

void doScan();

typedef struct{
   int angle;
   float distance;
   float posX;
   float posY;
}scanData;

extern volatile scanData data[180];

void doScanPoint(int ang);

#endif /* DOSCAN_H_ */
