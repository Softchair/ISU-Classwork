#include "open_interface.h"
#include "movement.h"
#include "uart.h"

extern volatile enum dir direction = UP;
extern volatile float position[2] = {0, 0};

//Moves the robot for a certain distance, at a certain speed. Pass in robot sensor data
//Distance in mm
double move(int speed, int distance, oi_t *robot) {
    //Sum of distance traveled
    float sumDistance = 0;
    //int i = 0;

    if (distance > 0) { //Forward movement
        //Sets the speed of the wheels
        oi_setWheels(speed, speed + 1);
        //While the distance traveled is less than the distanced desired, go
        while (sumDistance < distance) {
            oi_update(robot); //Updates the robot data
            sumDistance += robot->distance; //Adds the distance since last update
            if(robot -> cliffLeftSignal < 100 || robot -> cliffFrontLeftSignal < 100 || robot -> cliffRightSignal < 100 || robot -> cliffFrontRightSignal < 100 ||
                            robot -> cliffLeftSignal > 2600 || robot -> cliffFrontLeftSignal > 2600 || robot -> cliffRightSignal > 2600 || robot -> cliffFrontRightSignal > 2600 ){ //checking

                        oi_setWheels(0,0); //stop
                        uart_data = 'x';
                        char Send_String[100];

                        sprintf(Send_String, "Boundary hit\n\r");

                        uart_sendStr(Send_String);
                        return sumDistance;
            }
            if (robot->bumpLeft || robot->bumpRight) {
                posCalc(sumDistance);
                return sumDistance;
            }
        }
    } else if (distance < 0) { //Backward movement
        //Sets the speed of the wheels, backwards
        oi_setWheels(-speed, -speed);
        //While the distance traveled is greater than the distance desired, go
        while (sumDistance > distance) {
            oi_update(robot); //Updates robot data
            sumDistance += robot->distance; //Subtracts the distance since last update
        }
    }
    posCalc(sumDistance);
    oi_setWheels(0, 0);
    return distance;
}

//Turns the robot x degrees
void turn(double degrees, oi_t *robot) {

    //Offset of degrees
    double offset = 5.0;

    double degreesTurned = 0;
    if (degrees > 0) { //Counter clockwise
        oi_setWheels(125, -125);
        while (degreesTurned < (degrees - offset)) {
            oi_update(robot);
            degreesTurned += robot->angle;
        }
        oi_setWheels(0, 0);
    } else if (degrees < 0) { //Clockwise
        oi_setWheels(-125, 125);
        while (degreesTurned > (degrees + offset)) {
            oi_update(robot);
            degreesTurned += robot->angle;
        }
        oi_setWheels(0, 0);
    } else {
        return;
    }
}



void bumped(oi_t *robot) {
    if (robot->bumpLeft) {
        oi_update(robot);
        move(100, -150, robot);
        turn(-90, robot);
        move(150, 250, robot);
        turn(90, robot);
        oi_setWheels(0, 0);
        return;
    } else if (robot->bumpRight) {
        oi_update(robot);
        move(100, -150, robot);
        turn(90, robot);
        move(150, 250, robot);
        turn(-90, robot);
        oi_setWheels(0, 0);
        oi_update(robot);
        return;
    }
}

void posCalc(float distance){

    if(direction == UP){
        position[1] += distance / 10;
    } else if(direction == DOWN){
        position[1] -= distance / 10;
    } else if(direction == RIGHT){
        position[0] += distance / 10;
    } else if(direction == LEFT){
        position[0] -= distance / 10;
    }
return;
}

void turn90(enum dir turn, oi_t *robot){
    //Offset of degrees
    double offset = 3.0;

    double degreesTurned = 0;
    if (turn == LEFT) { //Counter clockwise
        if(direction == UP){
            direction = LEFT;
        } else {
            direction -= 1;
        }
        oi_setWheels(125, -125);
        while (degreesTurned < (78)) {
            oi_update(robot);
            degreesTurned += robot->angle;
        }
        oi_setWheels(0, 0);
    } else if (turn == RIGHT) { //Clockwise
        if(direction == LEFT){
            direction = UP;
        } else {
            direction += 1;
        }
        oi_setWheels(-125, 125);
        while (degreesTurned > (-81)) {
            oi_update(robot);
            degreesTurned += robot->angle;
        }
        oi_setWheels(0, 0);
    } else {
        return;
    }
}
