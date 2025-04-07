import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Gameboard class to find all possible moves
 * Author: Camden Fergen
 */
public class GameBoard {

    //Hold the vehicles for the gameboard
    private ArrayList<Vehicle> vehicles;
    //Easy reference to the ice cream truck
    private Vehicle iceCreamTruck;

    /**
     * Reads the input given by a filename and creates a game board from it
     * @param FileName Valid file format for making a game
     */
    public void readInput(String FileName) throws IOException {


        //Start a scanner to read the file
        Scanner fileReader = new Scanner(new File(FileName));

        //Current vehicle id
        int vehicleId = 0;
        String numOfVeh = fileReader.nextLine();

        vehicles = new ArrayList<>(Integer.parseInt(numOfVeh));

        //While the file still has next lines, continue
        while (fileReader.hasNextLine()) {
            //Get the current line its reading
            String currentLine = fileReader.nextLine();

            //Create another scanner to read through the current line
            Scanner lineReader = new Scanner(currentLine);

            //Get the first location of the line, which will be the start location
            int startLocation = lineReader.nextInt();

            //Hold the end and length
            int endLocation = 0;
            int length = 1;

            //Read the rest of ints to determine how long it is
            while (lineReader.hasNextInt()) {
                length++;
                endLocation = lineReader.nextInt();
            }
            
            //Store the current vehicle
            Vehicle curVeh;

            //Create a new vehicle and update it
            if ((endLocation - startLocation) % 6 == 0 ) {
                curVeh = new Vehicle(startLocation, length, vehicleId, 'v');
                vehicles.add(curVeh);
            } else {
                curVeh = new Vehicle(startLocation, length, vehicleId, 'h');
                vehicles.add(curVeh);
            }

            //If its the first vehicle, associate it with the ice cream truck
            if (vehicleId == 0) {
                iceCreamTruck = curVeh;
            }

            //Iterate the vehicleId
            vehicleId++;
        }

    }

    /**
     * Finds a shortest path for the gameboard
     * @return ArrayList<Pair> of all the Pairs for the move
     */
    public ArrayList<Pair> getPlan() {
        ArrayList<Pair> plan = new ArrayList<>();

        //Checking if the game is already won
        if (iceCreamTruck.getDirection() == 'v' || vehicles.get(0).getEndLocation() == 18) {
            return plan;
        }
        // Create a queue and a hashmap to act as our graph
        ArrayList<ArrayList<Vehicle>> theQueue = new ArrayList<>();
        HashMap<HashKey, Pair> allMoves =  new HashMap<>();

        //Add the current vehicles to the queue
        theQueue.add(vehicles);
        //Setting the int array for hashing
        int[] startingLocations = setVehicleArray(vehicles);

        //Creating a hashkey for the starting locations
        HashKey initKey = new HashKey(startingLocations);

        //Add the first state to the moves, so that we can find a path
        allMoves.put(initKey, null);

        boolean isGameWon = false;

        //Generate all possible paths till you find the end state
        while (!theQueue.isEmpty() && !isGameWon) {
            // Pop from the queue
            ArrayList<Vehicle> currentState = theQueue.remove(0);
            vehicles = currentState;
            // Get the list of moves that can be made in this state
            ArrayList<Pair> allPossibleMoves = getAllMoves(currentState);

            //For all possible moves, try them
            for(Pair curMove: allPossibleMoves) {
                //Deep copy the state so that we can modify it and not have references to the old
                ArrayList<Vehicle> newState = deepCopy(currentState);
                updateState(newState, curMove);
                vehicles = newState;

                //Update the int array to create a new hash based on the vehicles
                startingLocations = setVehicleArray(vehicles);
                HashKey curKey = new HashKey(startingLocations);
                
                
                //Check the hashmap, and if the hash doesnt exist continue with it
                //as it hasnt been visited
                if (!allMoves.containsKey(curKey)) {
                    //Putting key into hashmap with move
                    allMoves.put(curKey, curMove);
                    theQueue.add(newState);

                    //Checking if the game is won, if so break this loop
                    if (newState.get(0).getEndLocation() == 18){
                        isGameWon = true;
                        break;
                    }
                }
            }
        }

        //Start at the end point to be able to find the start for path
        HashKey curHash = new HashKey(startingLocations);
        Pair backMove = allMoves.get(curHash);

        //Continue going till you find the starting state which doesnt have a move
        while (backMove != null) {
            //Add the current move to the plan to return later
            plan.add(0, backMove);

            //Find the opposite of the move to find the next state
            backMove = reverseMove(backMove);
            updateState(vehicles, backMove);

            //Update the hash based on the new state, so that we can get the next move
            startingLocations[backMove.id] = vehicles.get(backMove.id).getStartingLocation();
            curHash = new HashKey(startingLocations);

            //Change the move to the new move we just got
            backMove = allMoves.get(curHash);
        }

        //Yay!
        return plan;
    }

    /**
     * Just to please the driver
     * @return nothing of use (int)
     */
    public int getNumOfPaths() {
        Random ran = new Random();
        return ran.nextInt();
    }

    /**
     * Gets all the possible moves for the current game state
     * @param Vehs Arraylist of the current state in vehicles
     * @return arraylist of pairs
     */
    private ArrayList<Pair> getAllMoves(ArrayList<Vehicle> Vehs){
        //Arraylist of pairs of possible moves
        ArrayList<Pair> possibleMoves = new ArrayList<>();

        //Try moving all vehicles, if the move exists ad it to the possible moves
        for(Vehicle curVeh : Vehs) {
            //trying forward and back
            Pair forwards = moveForward(curVeh);
            Pair backwards = moveBackwards(curVeh);

            //If null, move doesnt exist, otherwise add it
            if (forwards != null) {
                possibleMoves.add(forwards);
            }
            if (backwards != null) {
                possibleMoves.add(backwards);
            }
        }

        return possibleMoves;
    }

    /**
     * Checks a board space and returns if its free
     * @param boardSpace the space to be checked as int
     * @return true or false depending on if its free
     */
    private boolean checkSpace(int boardSpace) {
        // Check each vehicle in my list to make sure they aren't occupying the space I want
        for (Vehicle curVeh: vehicles) {
            int middleLocation = -1;

            //Finding the middle location of the vehicle
            if (curVeh.getLength() == 3) {
                if (curVeh.getDirection() == 'v') { //If its vertical, add 6
                    middleLocation = curVeh.getStartingLocation() + 6;
                }
                else { //else add 1
                    middleLocation = curVeh.getStartingLocation() + 1;
                }
            }

            //Checking if the vehicle occupies any space
            if (curVeh.getStartingLocation() == boardSpace || curVeh.getEndLocation() == boardSpace || middleLocation == boardSpace) {
                return false;
            }
        }

        //Its empty!
        return true;
    }

    /**
     * Returns a pair if the move can be made
     * @param curVeh the current vehicle
     * @return Pair if its a possible move, null else
     */
    private Pair moveBackwards(Vehicle curVeh) {
        int nextSpace = 0;
        // If horizontal, make sure we don't skip rows
        if (curVeh.getDirection() == 'h') {
            //Finding the next space to move to
            nextSpace = curVeh.getStartingLocation() - 1;

            //If its out of bounds
            if (nextSpace % 6 == 0){
                return null;
            }

            //Checking if the space is free
            if (checkSpace(nextSpace)) {
                return new Pair(curVeh.getId(), 'w');
            }

            return null;
        } else {
            //For vertical vehicles
            nextSpace = curVeh.getStartingLocation() - 6;

            //Check if its going out of bounds
            if (nextSpace < 1) {
                return null;
            }

            //Checking if the space is free on the board
            if (checkSpace(nextSpace)){
                return new Pair(curVeh.getId(), 'n');
            }
            
            return null;
        }
    }

    /**
     * Returns a pair if the move can be made
     * @param curVeh the current vehicle
     * @return Pair if its a possible move, null else
     */
    private Pair moveForward(Vehicle curVeh) {
        //This is all literally the same as move backwards
        //Not going to comment and explain

        int nextSpace = 0;
        if (curVeh.getDirection() == 'h') {
            // end of row, can't move forward
            if (curVeh.getEndLocation() % 6 == 0){
                return null;
            }
            if (checkSpace(curVeh.getEndLocation() + 1)) {
                return new Pair(curVeh.getId(), 'e');
            }
            return null;
        } else {
            nextSpace = curVeh.getEndLocation() + 6;
            if (nextSpace > 36) {
                return null;
            }
            if (checkSpace(nextSpace)) {
                return new Pair(curVeh.getId(), 's');
            }
            return null;
        }
    }

    //Update the current state based on the given pair
    private void updateState(ArrayList<Vehicle> state, Pair moveToMake) {
        Vehicle move = state.get(moveToMake.id);
        int start = move.getStartingLocation();
        switch (moveToMake.direction) {
            case 'n' -> move.setStartLocation(start - 6);
            case 's' -> move.setStartLocation(start + 6);
            case 'e' -> move.setStartLocation(start + 1);
            case 'w' -> move.setStartLocation(start - 1);
        }
        state.set(moveToMake.id, move);
    }

    //Find the opposite of a move to traverse the graph
    private Pair reverseMove(Pair p) {
        Pair newPair = new Pair(p.id, 'z');
        switch (p.direction) {
            case 'n' -> newPair.setDirection('s');
            case 'e' -> newPair.setDirection('w');
            case 's' -> newPair.setDirection('n');
            case 'w' -> newPair.setDirection('e');
        }
        return newPair;
    }

    /**
     * Deep copy of gameboard
     * @param pastState the state to be copied
     * @return an array list of new state with no references to the past state
     */
    private ArrayList<Vehicle> deepCopy(ArrayList<Vehicle> pastState) {
        //Create an arraylist to return
        ArrayList<Vehicle> copiedState = new ArrayList<>();

        //Iterate over all of the vehicles and copy them
        for (Vehicle v : pastState) {
            Vehicle newVehicle = new Vehicle(v);
            copiedState.add(newVehicle);
        }

        return copiedState;
    }

    /**
     * Sets an int array based on a vehicle array for hasing
     * @param vehs Arraylist of vehicles
     * @return int array for hashing
     */
    private int[] setVehicleArray(ArrayList<Vehicle> vehs) {
        int[] returnArray = new int[vehs.size()];

        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = vehs.get(i).getStartingLocation();
        }

        return returnArray;
    }

}

/**
 * Pair class to hold the moves
 */
class Pair {
    int id; //Id of the vehicle
    char direction; // {’e’, ’w’, ’n’, ’s’}

    /**
     * Create a new pair
     * @param i id of the vehicle being moved
     * @param moveDir the move its making
     */
    public Pair(int i, char moveDir) {
        id = i;
        direction = moveDir;
    }

    /**
     * Gets the direction of the pair
     * @return char direction
     */
    char getDirection() {
        return direction;
    }

    /**
     * Gets the id of the vehicle being moved
     * @return int id
     */
    int getId() {
        return id;
    }

    /**
     * Sets the direction of the pair
     * @param moveDir char move direction
     */
    void setDirection(char moveDir) {
        direction = moveDir;
    }

}

/**
 * Class to hold vehicle data
 */
class Vehicle {
    private int startLocation; //Start of vehicle
    private int endLocation; //End of vehicle
    private int length; //Length of the vehicle
    private int id; //id of the vehicle
    private char direction; //direction of the vehicle, h or v

    public Vehicle(int start, int length, int id, char dir) {
        this.startLocation = start;
        this.length = length;
        this.id = id;
        this.direction = dir;
        if (this.direction == 'v') {
            this.endLocation = startLocation + (6 * (this.length - 1));
        }
        else {
            this.endLocation = startLocation + this.length - 1;
        }
    }

    public Vehicle(Vehicle v) {
        this(v.getStartingLocation(), v.getLength(), v.getId(), v.getDirection());
    }

    public int getId() {
        return id;
    }

    public int getStartingLocation() {
        return startLocation;
    }

    public int getLength() {
        return length;
    }

    public void setStartLocation(int startLocation) {
        this.startLocation = startLocation;
        if (this.direction == 'v') {
            this.endLocation = startLocation + (6 * (this.length - 1));
        }
        else {
            this.endLocation = startLocation + this.length - 1;
        }
    }

    public char getDirection() {
        return direction;
    }

    public int getEndLocation() {
        return endLocation;
    }

}

/**
 * Hashkey!!!
 */
class HashKey {
    int[] c; // attribute
    public HashKey(int[] inputc) {
        c = new int[inputc.length];
        c = inputc;
    }
    public boolean equals(Object o) {
        boolean flag = true;
        if (this == o) return true; // same object
        if ((o instanceof HashKey)) {
            HashKey h = (HashKey)o;
            int[] locs1 = h.c;
            int[] locs = c;
            if (locs1.length == locs.length) {
                for (int i=0; i<locs1.length; i++) { // mismatch
                    if (locs1[i] != locs[i]) {
                        flag = false;
                        break;
                    }
                }
            }
            else // different size

                flag = false;
        }
        else // not an instance of HashKey
            flag = false;
        return flag;
    }
    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return Arrays.hashCode(c); // using default hashing of arrays
    }
}