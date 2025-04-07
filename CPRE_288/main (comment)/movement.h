#include <stdio.h>
#include <open_interface.h>



extern volatile float position[2];
extern volatile enum dir{UP, RIGHT, DOWN, LEFT} direction;
//Moves the robot for a certain distance, at a certain speed. Pass in robot sensor data
//Distance in mm
double move(int speed, int distance, oi_t *robot);

//Turns the robot x degrees
void turn(double degrees, oi_t *robot);

void bumped(oi_t *robot);

void posCalc(float distance);

void turn90(enum dir direction, oi_t *robot);
