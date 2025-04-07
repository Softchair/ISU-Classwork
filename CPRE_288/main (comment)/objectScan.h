
struct scanData;
struct object;

/*
 * Basic object detection only using the IR sensor. Pass in a struct array of scanData, as well as objects
 */
int detectObjectsBasic(struct scanData data[], struct object (*objs)[], int len);
