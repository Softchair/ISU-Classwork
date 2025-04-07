//import java.io.File;
//import java.io.FileNotFoundException;
//import java.lang.invoke.VarHandle;
//import java.util.*;
//
//public class GameBoard {
//    private State initialState; //Initial gameboard state
//    private State endState; //Ending state
//    private int numEndStates; //Number of end states
//    private ArrayList<Vehicle> vehicles; //Arraylist to store all the vehicles before the game is made
//    private Vehicle iceCreamTruck;
//
//
//    /**
//     * Reads the input given by a filename and creates a game board from it
//     * @param FileName Valid file format for making a game
//     */
//    public void readInput(String FileName) {
//        try {
//            //Creates a scanner on the file
//            Scanner fileReader = new Scanner(new File(FileName));
//
//            int curId = 0;
//            int numOfVehicles = Integer.parseInt(fileReader.nextLine()); //Skip over the first line
//            vehicles = new ArrayList<>();
//            while (fileReader.hasNextLine()) {
//                //Gets current line as a string
//                String curLine = fileReader.nextLine();
//                //Splits the line by spaces, some gross regex
//                String[] vehLocs = curLine.split("\\s+");
//
//                //Variable to hold the currently being created vehicle
//                Vehicle curVeh;
//                //Create new vehicles based on the cur line
//                if (vehLocs.length > 2) {
//                    //A gross way to make a new vehicle
//                    curVeh = new Vehicle(curId, Integer.parseInt(vehLocs[0]), Integer.parseInt(vehLocs[1]), Integer.parseInt(vehLocs[2]));
//                } else {
//                    //More gross vehicles
//                    curVeh = new Vehicle(curId, Integer.parseInt(vehLocs[0]), Integer.parseInt(vehLocs[1]));
//                }
//
//                //add the current vehicle to the list of vehicles
//                vehicles.add(curVeh);
//                //Increment the cur id when new vehicle
//                curId++;
//            }
//
//            iceCreamTruck = vehicles.get(0);
//
//            fileReader.close();
//            //Create the hashmap gameboard
//            initialState = new State(vehicles);
//
//            //Testing copying a state
//            //initialState = new State();
//            //initialState = copyState(gameboard);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public ArrayList<Pair> getPlan() {
//        ArrayList<Pair> thePlan = new ArrayList<>();
//
//        //Check for edge cases, if its vertical, or in end state
//        if (iceCreamTruck.getDirection() == 'v' || iceCreamTruck.getLocation()[1] == 18) {
//            return thePlan;
//        }
//
//        //Create a queue for BFS
//        Queue<ArrayList<Vehicle>> theQueue = new LinkedList<>();
//        //Create a graph for BFS exploration
//        HashMap<HashKey, Pair> allPaths = new HashMap<>();
//
//        //Start off with the base vehicles
//        theQueue.add(vehicles);
//        //Build the startingLocation array
//        int[] locations = setVehicleArray(vehicles);
//
//        //Key for the initial state
//        HashKey initHash = new HashKey(locations);
//
//        //Put the first state in the hash
//        allPaths.put(initHash, null);
//        boolean isGameWon = false;
//
//        //Start BFS on the graph while the queue isnt empty
//        while (!theQueue.isEmpty() && !isGameWon) {
//            //Pop out the top state from the queue
//            ArrayList<Vehicle> curState = theQueue.remove();
//            vehicles = curState;
//
//            printGameBoard(vehicles);
//
//            //Gets all the possible moves for the current state
//            ArrayList<Pair> allPossibleMoves = getPossibleMoves(curState);
//
//            //Iterate over all the moves and execute them
//            for (Pair p: allPossibleMoves) {
//                //Create a deep copy
//                ArrayList<Vehicle> newState = deepCopy(curState);
//
//                update(newState, p);
//
//                vehicles = newState;
//
//                //printGameBoard(vehicles);
//
//                //Create new locations for the array to create hashes from
//                locations = setVehicleArray(newState);
//                //Create a new hash
//                HashKey curHash = new HashKey(locations);
//
//                if (!allPaths.containsKey(curHash)) {
//                    //Add the new valid state to the path
//                    allPaths.put(curHash, p);
//                    //Add the current state to the queue to find neighbors
//                    theQueue.add(newState);
//
//                    //If the new state is an end state
//                    if (newState.get(0).getLocation()[1] == 18) {
//                        isGameWon = true;
//                        break;
//                    }
//                }
//            }
//        }
//
//        //Find the shortest path//
//        //Create a spot to start from
//        HashKey hash = new HashKey(locations);
//        //Get the point to start BFS from
//        Pair curPair = allPaths.get(hash); //Starting point
//
//        //Iterate back until it gets to the start point
//        while (curPair != null) {
//            //Add the current pair to the plan
//            thePlan.add(0, curPair);
//
//            //Reverse up the graph to find the starting point
//            curPair = reverseTheMove(curPair);
//
//            update(vehicles, curPair);
//
//            printGameBoard(vehicles);
//
//            //After updating array, update the locs to get the next hash
//            locations[curPair.getId()] = vehicles.get(curPair.getId()).getLocation()[0];
//
//            //Create a new hashkey to start from again
//            hash = new HashKey(locations);
//
//            //Get the next pair to continue from
//            curPair = allPaths.get(hash);
//        }
//
//        return thePlan;
//    }
//
//    /**
//     * Just something to please the program
//     * @return random number lol
//     */
//    public int getNumOfPaths() {
//        Random ran = new Random();
//        return ran.nextInt();
//    }
//
//    /**
//     * Generates all possible and valid moves to make
//     * @param state State of the game board
//     * @return Arraylist of pairs of moves
//     */
//    private ArrayList<Pair> getPossibleMoves(ArrayList<Vehicle> state) {
//        ArrayList<Pair> moves = new ArrayList<>();
//
//        for (Vehicle curVeh : state) {
//            Pair tryForward = tryForward(curVeh);
//            Pair tryBackwards = tryBackwards(curVeh);
//            if (tryBackwards != null) {
//                moves.add(tryBackwards);
//            } else if (tryForward != null) {
//                moves.add(tryForward);
//            }
//        }
//
//        return moves;
//    }
//
////    /**
////     * Trys to make a move, returns null if the move is invalid
////     * @param curVeh The vehicle to try moving
////     * @param State current gameboard state
////     * @return null if it cant move, a pair if it can
////     */
////    public Pair tryForward(Vehicle curVeh, ArrayList<Vehicle> State) {
////        int endLocation = curVeh.getLocation()[curVeh.getLocation().length - 1];
////
////        if (curVeh.getDirection() == 'h') { //horizontal, east RIGHT
////            //Checking for end of row
////            if (endLocation % 6 == 0) {
////                return null;
////            }
////            //Check that the spot is free to move to
////            if (checkSpace(State, endLocation + 1)) {
////                return new Pair(curVeh.getId(), 'e');
////            }
////
////            //If nothing passes
////            return null;
////        } else { //vertical, south DOWN
////            //Checking for out of bounds
////            if ((endLocation + 6) > 36) {
////                return null;
////            }
////            //Checking if space is free
////            if (checkSpace(State, endLocation + 6)) {
////                return new Pair(curVeh.getId(), 's');
////            }
////
////            //If nothing passes
////            return null;
////        }
////    }
////
////    /**
////     * Try to make a move, returns null if the move is invalid
////     * @param curVeh The vehicle to try moving
////     * @param State current gameboard state
////     * @return null if it cant move, a pair if it can
////     */
////    public Pair tryBackwards(Vehicle curVeh, ArrayList<Vehicle> State) {
////        int startLocation;
////
////        if (curVeh.getDirection() == 'h') { //horizontal, west LEFT
////            startLocation = curVeh.getLocation()[0];
////
////            //Check for end of row
////            if ((startLocation - 1) % 6 == 0) {
////                return null;
////            }
////
////            //Check if the spot is open
////            if (checkSpace(State, startLocation - 1)) {
////                return new Pair(curVeh.getId(), 'w');
////            }
////
////            return null;
////        } else { //vertical, north UP
////            startLocation = curVeh.getLocation()[curVeh.getLocation().length - 1];
////
////            //Check for out of bounds
////            if ((startLocation - 6) < 0) {
////                return null;
////            }
////
////            //Check if next spot is open
////            if (checkSpace(State, startLocation - 6)) {
////                return new Pair(curVeh.getId(), 'n');
////            }
////
////            return null;
////        }
////    }
//
//    // Returns a Pair for the move that can be made if v can move backwards, null otherwise
//    private Pair tryBackwards(Vehicle v) {
//        int nextSpace = 0;
//        // If horizontal, make sure we don't skip rows
//        if (v.getDirection() == 'h') {
//            nextSpace = v.getStartingLocation() - 1;
//            if (nextSpace % 6 == 0){
//                return null;
//            }
//            // Put in separate if statement, or we might pass 0 to isFree.
//            if (checkSpace(nextSpace)) {
//                return new Pair(v.getId(), 'w');
//            }
//
//            return null;
//        }
//        else {
//            nextSpace = v.getStartingLocation() - 6;
//            if (nextSpace < 1) {
//                return null;
//            }
//            if (checkSpace(nextSpace)){
//                return new Pair(v.getId(), 'n');
//            }
//            return null;
//        }
//    }
//
//    // Returns a Pair for the move that can be made if v can move forwards, null otherwise
//    private Pair tryForward(Vehicle v) {
//        int nextSpace = 0;
//        if (v.getDirection() == 'h') {
//            // end of row, can't move forward
//            if (v.getEndLocation() % 6 == 0){
//                return null;
//            }
//            if (checkSpace(v.getEndLocation() + 1)) {
//                return new Pair(v.getId(), 'e');
//            }
//            return null;
//        }
//        else {
//            nextSpace = v.getEndLocation() + 6;
//            if (nextSpace > 36) {
//                return null;
//            }
//            if (checkSpace(nextSpace)) {
//                return new Pair(v.getId(), 's');
//            }
//            return null;
//        }
//    }
//
//    /**
//     * Returns if a move can be made
//     * @param spotToCheck Location that needs to be checked
//     * @return false if the move cant be made, true if it can be made
//     */
//    private boolean checkSpace(int spotToCheck) {
//        for (Vehicle curVeh : vehicles) {
//            for (int a : curVeh.getLocation()) {
//                if (a == spotToCheck) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    /**
//     * Updates the location of one of the vehicles
//     * @param state Current state of the vehicles
//     * @param moveToMake A Pair of move to make
//     */
//    private void update(ArrayList<Vehicle> state, Pair moveToMake) {
//        //Gets the vehicle we are moving
//        Vehicle movedVehicle = state.get(moveToMake.getId());
//        int initLoc = movedVehicle.getLocation()[0]; //Gets the start location
//
//        switch (moveToMake.getDirection()) {
//            case 'n':
//                movedVehicle.setSingleLoc(-6);
//                break;
//            case 's':
//                movedVehicle.setSingleLoc(6);
//                break;
//            case 'e':
//                movedVehicle.setSingleLoc(1);
//                break;
//            case 'w':
//                movedVehicle.setSingleLoc(-1);
//                break;
//        }
//
//        state.set(moveToMake.getId(), movedVehicle);
//    }
//
//    /**
//     * Sets an int array based on a vehicle array for hasing
//     * @param vehs Arraylist of vehicles
//     * @return int array for hashing
//     */
//    private int[] setVehicleArray(ArrayList<Vehicle> vehs) {
//        int[] returnArray = new int[vehs.size()];
//
//        for (int i = 0; i < returnArray.length; i++) {
//            returnArray[i] = vehs.get(i).getLocation()[0];
//        }
//
//        return returnArray;
//    }
//
//    /**
//     * Reverse the move
//     * @param moveMade The move that was made
//     * @return The opposite of the move
//     */
//    private Pair reverseTheMove(Pair moveMade) {
//        //Create a pair to return, q is invalid move
//        Pair reversedMove = new Pair(moveMade.getId(), 'q');
//
//        //Switch case to reverse the move
//        switch (moveMade.getDirection()) {
//            case 'n' -> reversedMove.setDirection('s');
//            case 'e' -> reversedMove.setDirection('w');
//            case 's' -> reversedMove.setDirection('n');
//            case 'w' -> reversedMove.setDirection('e');
//        }
//
//        return reversedMove;
//    }
//
//    /**
//     * Returns a deep copy of the vehicle array
//     * @param oldVehs The vehicle array to be copied
//     * @return A arraylist of vehicles with no reference to old vehicles
//     */
//    private ArrayList<Vehicle> deepCopy(ArrayList<Vehicle> oldVehs) {
//        ArrayList<Vehicle> newCopy = new ArrayList<>();
//        for (Vehicle curVeh : oldVehs) {
//            newCopy.add(curVeh.getId(), new Vehicle(curVeh));
//        }
//        return newCopy;
//    }
//
//    public void printGameBoard(ArrayList<Vehicle> vehs) {
//        StringBuilder gb = new StringBuilder();
//        for (int row = 0; row <= 30; row += 6) {
//            for (int col = 1; col < 7; col++) {
//                int val = -1;
//                if (!checkSpace(row+col)) {
//                    for (Vehicle curVeh : vehs) {
//                        for (int a : curVeh.getLocation()) {
//                            if (a == row+col) {
//                                val = curVeh.getId();
//                                break;
//                            }
//                        }
//                    }
//                }
//                if (val == -1) {
//                    gb.append("e ");
//                } else {
//                    gb.append(val).append(" ");
//                }
//            }
//            gb.append("\n");
//        }
//        System.out.println(gb);
//    }
//
//}
//
///**
// * State class to hold the current state of the gameboard
// */
//class State {
//    private ArrayList<Vehicle> vehicles;
//    private int layerLevel;
//    private Pair moveMade;
//
//    /**
//     * Creates a new state based on an array of vehicles
//     * @param vehicles An array of VALID vehicles
//     */
//    public State(ArrayList<Vehicle> vehicles) {
//        //Initialize the vehicle arraylist
//        this.vehicles = new ArrayList<>();
//
//        for (Vehicle curVeh : vehicles) {
//            //Gets the current vehicle
//            //Adds the vehicle to the vehicle array
//            this.vehicles.add(curVeh);
//        }
//
//        moveMade = new Pair(-1, 'q'); //Setting the move made to nothing
//    }
//
//    /**
//     * Creates a blank state
//     */
//    public State() {
//        vehicles = new ArrayList<>();
//        moveMade = null;
//    }
//
//    /**
//     * Returns the arraylist of vehicles
//     * @return Arraylist of type vehicles
//     */
//    public ArrayList<Vehicle> getVehicles() {
//        return vehicles;
//    }
//
//    /**
//     * Sets the layer level for the state
//     * @param layerLevel level as int
//     */
//    public void setLayerLevel(int layerLevel) {
//        this.layerLevel = layerLevel;
//    }
//
//    /**
//     * Gets the layer level for the state
//     * @return level as an int
//     */
//    public int getLayerLevel() {
//        return layerLevel;
//    }
//}
//
///**
// * Class that holds the vehicle locations
// */
//class Vehicle {
//    private int id;
//    private int[] location; //2 Or 3 long
//    private char direction; //v for vertical, h for horizontal
//
//    /**
//     * Gets the ID of the vehicle
//     * @return ID as int
//     */
//    public int getId() {
//        return id;
//    }
//
//    /**
//     * Gets the location data of the vehicle
//     * @return int array of locations
//     */
//    public int[] getLocation() {
//        return location;
//    }
//
//    /**
//     * Sets the location of the current vehicle
//     * @param newLocations int array of new locations. Keep in mind direction
//     */
//    public void setLocation(int[] newLocations) {
//        location = newLocations.clone();
//    }
//
//    /**
//     * Updates a vehicle based on a move amount
//     * @param a move amount
//     */
//    public void setSingleLoc(int a) {
//        for (int i = 0; i < location.length; i++) {
//            location[i] += a;
//        }
//    }
//
//    /**
//     * Returns the direction of the car, either horizontal (h) or vertical (v)
//     * @return Char of direction
//     */
//    public char getDirection() {
//        return direction;
//    }
//
//    /**
//     * Public constructor for making a vehicle that's 3 long
//     * @param id id of the vehicle
//     * @param a first loc
//     * @param b second loc
//     * @param c third loc
//     */
//    public Vehicle(int id, int a, int b, int c) {
//        this.id = id;
//
//        location = new int[3];
//
//        location[0] = a;
//        location[1] = b;
//        location[2] = c;
//
//        direction = setDirectionBasedOnLoc();
//    }
//
//    /**
//     * Public constructor for making a vehicle that's 2 long
//     * @param id id of the vehicle
//     * @param a first loc
//     * @param b second loc
//     */
//    public Vehicle(int id, int a, int b) {
//        this.id = id;
//
//        location = new int[2];
//
//        location[0] = a;
//        location[1] = b;
//
//        direction = setDirectionBasedOnLoc();
//    }
//
//    /**
//     * Sets the location of a vehicle based on its location data
//     * @return char of location
//     */
//    private char setDirectionBasedOnLoc() {
//        if (location[1] - location[0] == 1) {
//            return 'h';
//        } else {
//            return 'v';
//        }
//    }
//
//    /**
//     * Creates a new vehicle and copies it from an old vehicle (DEEP COPY)
//     * @param oldVehicle the vehicle to copy
//     */
//    public Vehicle(Vehicle oldVehicle) {
//        this.id = oldVehicle.getId();
//        this.location = oldVehicle.getLocation().clone();
//        this.direction = oldVehicle.getDirection();
//    }
//
//    public int getEndLocation() {
//        return location[location.length - 1];
//    }
//
//    public int getStartingLocation() {
//        return location[0];
//    }
//}
//
///**
// * Class that holds movement data
// */
//class Pair {
//    private int id;
//    private char direction; //{'e', 'w', 'n', 's'}
//
//    public Pair(int i, char d) {
//        id = i;
//        direction = d;
//    }
//
//    char getDirection() {
//        return direction;
//    }
//
//    int getId() {
//        return id;
//    }
//
//    void setDirection(char d) {
//        direction = d;
//    }
//
//    void setId(int i) {
//        id = i;
//    }
//
//    void printPair() {
//        System.out.println(getId() + " " + getDirection());
//    }
//}
//
//class HashKey {
//    int[] c; // attribute
//    public HashKey(int[] inputc) {
//        c = new int[inputc.length];
//        c = inputc;
//    }
//    public boolean equals(Object o) {
//        boolean flag = true;
//        if (this == o) return true; // same object
//        if ((o instanceof HashKey)) {
//            HashKey h = (HashKey)o;
//            int[] locs1 = h.c;
//            int[] locs = c;
//            if (locs1.length == locs.length) {
//                for (int i=0; i<locs1.length; i++) { // mismatch
//                    if (locs1[i] != locs[i]) {
//                        flag = false;
//                        break;
//                    }
//                }
//            }
//            else // different size
//
//                flag = false;
//        }
//        else // not an instance of HashKey
//            flag = false;
//        return flag;
//    }
//    /*
//     * (non-Javadoc)
//     * @see java.lang.Object#hashCode()
//     */
//    public int hashCode() {
//        return Arrays.hashCode(c); // using default hashing of arrays
//    }
//}