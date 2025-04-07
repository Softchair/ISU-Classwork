#define PI 3.14159265

char Send_String[100];

// Struct to hold object data
typedef struct{

    int Degree_bot_view;
    double diameter;
    double distance;

}objectDetected;

// Test the diameter of objects are allwise bigger then 0 and that driving forward has another room to stop.
double Finding_diameter(double a, double b, double angle){

   // return sqrt(((a*a)+(b*b))-(2*a*b*cos((angle*(PI / 180.0)))));

    return 2*3.1415926*a*(angle/360.0);
}

// displays the data collected from scanning
void Display_scanData(LocationData* All_Scans, int Intervel){



    uart_sendStr( "Degrees   PING Distance(CM)   IR Value \n\r");

    int i;

    for(i = 0 ; i <= (180/Intervel); i++){

        sprintf(Send_String, "%d\t%f\t\t%d\n\r", i*2, (All_Scans +i) -> sound_dist, (All_Scans +i) -> IR_dist);
        uart_sendStr(Send_String);

    }

}

// Displaying a signal object to Putty
void Display_Object(objectDetected *Objects, int Index){



    sprintf(Send_String, "%d\t%d\t%f\t\t%f\n\r",Index, Objects->Degree_bot_view, Objects->diameter,Objects->distance);

    uart_sendStr(Send_String);

}

// Goes through all objects to be displayed and sends the objects to display to Display_Object
void Display_ObjectData(objectDetected Objects[], int Number_of_objects){

    int i;

    uart_sendStr("Select your object by typing its Index number\n\rIndex    Degrees   diameter   distance \n\r");

    for(i = 0 ; i < Number_of_objects; i++){

        if((Objects + i)->diameter != 0){

            Display_Object( Objects + i,i);
        }
    }
}

// Goes through scan data and finds the objects to potential drive to
int Object_Dection(objectDetected* temp, int Number_of_objects, int Intervel){

    // Scans seen
    int Num_of_scans = (180/Intervel)+2;

    LocationData All_Scans[Num_of_scans];

    malloc(sizeof *All_Scans);

    Scan180(All_Scans, Intervel);

    // Detects objects from scan data
    int i;
    int Location_in_ObjectArray = 0;
    int begining_index = -1;

    char Gui[100];

    for(i= 0; i <= Num_of_scans; i++){

        if(All_Scans[i].IR_dist >= 1000 && All_Scans[i+1].IR_dist >= 1000 && begining_index == -1 && Location_in_ObjectArray != Number_of_objects){

            begining_index = i;

        }else if(begining_index != -1 && (All_Scans[i].IR_dist < 1000 || i == (Num_of_scans - 1)) && (All_Scans[i+1].IR_dist < 1000|| i == (Num_of_scans - 1))){

            lcd_printf("%d",Location_in_ObjectArray);

            (temp + Location_in_ObjectArray)-> Degree_bot_view = (2*(begining_index + (((i)-begining_index)/2)));

            (temp + Location_in_ObjectArray) ->diameter = Finding_diameter(All_Scans[begining_index].sound_dist, All_Scans[i].sound_dist, (2*((i)-begining_index)));

            (temp + Location_in_ObjectArray) ->distance = All_Scans[(begining_index + ((i)-begining_index)/2)].sound_dist;

            begining_index = -1;

            lcd_printf("%d %f %f",(temp + Location_in_ObjectArray)-> Degree_bot_view, (temp + Location_in_ObjectArray) ->diameter, (temp + Location_in_ObjectArray) ->distance);

            Location_in_ObjectArray++;
        }

        if(begining_index == -1){
            Gui[i] = '-';
        }else{
            Gui[i] = '0';
        }
    }


    // Displays detetced object data

     Display_ObjectData( temp,7);


     /*
     //displays object data in a field from 0-180 degrees
     //Displays objects so if object is on the left Gui will display object to the left of the char array
    for(i = 0; i <3; i++){
        int j = Num_of_scans - 1;
        while(j != 0) {
            uart_sendChar(Gui[j]);
            --j;
        }
        uart_sendStr("\n\r");
    }
    */
     //displays object data in a field from 0-180 degrees
     //Displays objects so if object is on the left Gui will display object to the right of the char array corresponding to angle of bot from right(0) to left(180)
    Gui[Num_of_scans] = '\n';
    Gui[Num_of_scans+1] = '\r';
        for(i = 0; i <3; i++){

            uart_sendStr(Gui);
        }
    return 0;

}
