//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.*;
//
//public class GameBoard {
//
//    private State gameboard;
//    private State initialState; //Initial gameboard state
//    private ArrayList<State> endStates; //Ending state
//    private ArrayList<Vehicle> vehicles; //Arraylist to store all the vehicles before the game is made
//    private Queue<State> curStates; //Hold the states currently visiting - LinkedList
//    private HashMap<StateKey, State> visitedStates; //Holding the visited states
//    private boolean pathsGenerated = false;
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
//            fileReader.close();
//            //Create the hashmap gameboard
//            gameboard = new State(vehicles);
//
//            //Testing copying a state
//            initialState = new State();
//            initialState = copyState(gameboard);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public ArrayList<Pair> getPlan() {
//
//        //ONLY NEW LINES
//        if(!pathsGenerated) {
//            findAllPaths();
//        }
//        //ONLY NEW LINES
//
//        ArrayList<Pair> thePlan = new ArrayList<>();
//        ArrayList<Pair> reversedPlan = new ArrayList<>();
//
//        //Return an empty plan if there is no plan
//        if (endStates.isEmpty()) {
//            return thePlan;
//        }
//
//        State curState = endStates.get(0);
//
//        while(curState != initialState && curState.getParent() != null) {
//            reversedPlan.add(curState.getMoveMade());
//            curState = curState.getParent();
//        }
//
//        for (int i = reversedPlan.size() - 1; i >= 0; i--) {
//            thePlan.add(reversedPlan.get(i));
//        }
//
//        return thePlan;
//    }
//
//    /**
//     * Gets the total number of shortest paths
//     * @return num of shortest paths as an int
//     */
//    public int getNumOfPaths() {
//        return endStates.size();
//    }
//
//    /**
//     * Call findPath within this function to get all paths
//     */
//    public void findAllPaths() {
//        //Initialize variables
//        curStates = new LinkedList<>();
//        visitedStates = new HashMap<>();
//        endStates = new ArrayList<>();
//
//        initialState.level = 1;
//
//        //Add the initial state to the visited states and add a parent
//        curStates.add(initialState);
//
//        //Create a key
//        StateKey curStateKey = new StateKey(initialState.getGameboard());
//
//        visitedStates.put(curStateKey, initialState);
//
//
//        //No possible path
//        if(gameboard.getVehicles().get(0).getDirection() == 'v') {
//            return;
//        }
//
//        //If the game board is already in a winning state, return!
//        if(gameboard.getGameboard().get(18) == 0) {
//            endStates.add(initialState);
//            return;
//        }
//
//        boolean found = false;
//        //Continue the loop until the queue is empty
//        while (curStates.peek() != null) {
//            //Get the top state off of the queue
//            State workingState = curStates.remove();
//            State parentState = copyState(workingState);
//
//            //Create a key
//            StateKey workingStateKey = new StateKey(workingState.getGameboard());
//
//            visitedStates.put(workingStateKey, workingState);
//
//            //Generates all the next possible states
//            ArrayList<State> futureStates = getNextStates(workingState);
//
//
////            for (State printState : futureStates) {
////                printState.printGameboard();
////            }
//
//            //Find all the paths, and stop the while if its false
//            found = helperFindAll(futureStates, parentState);
//            if (found) {
//                break;
//            }
//        }
//
//        finishTheGraph();
//
//    }
//
//    public void finishTheGraph() {
//        while (curStates.peek() != null) {
//            //Get the top state off of the queue
//            State workingState = curStates.remove();
//            State parentState = copyState(workingState);
//
//            if (workingState.level == endStates.get(0).level - 1) {
//
//                //Create a key
//                StateKey workingStateKey = new StateKey(workingState.getGameboard());
//
//                visitedStates.put(workingStateKey, workingState);
//
//                //Generates all the next possible states
//                ArrayList<State> futureStates = getNextStates(workingState);
//
//
////            for (State printState : futureStates) {
////                printState.printGameboard();
////            }
//
//
//                for (State workingFutureState : futureStates) {
//                    workingFutureState.setParent(parentState);
//                    //If the gameboard has the ice cream truck in the exit position
//                    if (workingFutureState.getGameboard().get(18) == 0) {
//                        //workingFutureState.printGameboard(); //Printing for debug
//
//                        //Adding a copy of the state
//                        endStates.add(copyState(workingFutureState));
//                        break;
//                    }
//                }
//            }
//        }
//
//    }
//
//    public boolean helperFindAll(ArrayList<State> futureStates, State parentState) {
//        //For all the future states, check if it's been visited
//        for (State workingFutureState : futureStates) {
//
//            //Create a key
//            StateKey workingFutureStateKey = new StateKey(workingFutureState.getGameboard());
//
//            if (!visitedStates.containsKey(workingFutureStateKey)) {
//                //Add the state if it's not been visited
//                curStates.add(workingFutureState);
//
//                workingFutureState.setParent(parentState);
//
//                //If the gameboard has the ice cream truck in the exit position
//                if (workingFutureState.getGameboard().get(18) == 0) {
//                    //workingFutureState.printGameboard(); //Printing for debug
//
//                    //Adding a copy of the state
//                    endStates.add(copyState(workingFutureState));
//                    pathsGenerated = true;
//                    return true;
//                }
//            }
//
//        }
//        return false;
//    }
//
//    /**
//     * Finds a valid path for the gameboard
//     * @param gb The current gameboard
//     */
//    public void findPath(State gb) {
//        //todo
//    }
//
//    /**
//     * Gets the next possible states
//     * @return List of the next states
//     */
//    public ArrayList<State> getNextStates(State orignalState) {
//        ArrayList<State> nextStates = new ArrayList<>();
//
//        //Hold the original state
//        State prevState = copyState(orignalState);
//        Pair newMove;
//
//        boolean flag;
//        for (Vehicle vehicle : vehicles) {
//            if (vehicle.getDirection() == 'h') {
//                for (char direction : new char[]{'e', 'w'}) {
//                    //Create a new move to try for every vehicle
//                    newMove = new Pair(vehicle.getId(), direction);
//
//                    if(validMove(newMove, prevState)) { //If the move is valid
//                        //Create a deep copy new state
//                        State validState = copyState(prevState);
//                        Vehicle movedVehicle = makeMove(newMove, validState); //Move the vehicle
//                        validState.setMovedVehicle(movedVehicle);
//                        validState.setMoveMade(newMove); //Add the move made
//                        validState.level = orignalState.level + 1;
//
//                        StateKey hashKey = new StateKey(validState.getGameboard());
//
//                        //Check if it doesnt exist
//                        if(!visitedStates.containsKey(hashKey)) {
//                            //add the new state to nextStates
//                            nextStates.add(validState);
//                        }
//                    }
//                }
//            } else {
//                for (char direction : new char[]{'n', 's'}) {
//                    //Create a new move to try for every vehicle
//                    newMove = new Pair(vehicle.getId(), direction);
//
//                    if(validMove(newMove, prevState)) { //If the move is valid
//                        //Create a deep copy new state
//                        State validState = copyState(prevState);
//                        Vehicle movedVehicle = makeMove(newMove, validState); //Move the vehicle
//                        validState.setMovedVehicle(movedVehicle);
//                        validState.setMoveMade(newMove); //Add the move made
//                        validState.level = orignalState.level + 1;
//
//                        StateKey hashKey = new StateKey(validState.getGameboard());
//
//                        //Check if it doesnt exist
//                        if(!visitedStates.containsKey(hashKey)) {
//                            //add the new state to nextStates
//                            nextStates.add(validState);
//                        }
//                    }
//                }
//            }
//        }
//        return nextStates;
//    }
//
//    /* ------------------------------------------ Helper Methods below ------------------------------------------ */
//
//    /**
//     * Finds if there is a collision when trying a move - Helper method for valid move !!DO NOT CALL!!
//     * @param moveToMake The move that wants to be made as a Pair
//     * @param curState Current gameboard state
//     * @return True if the move can be made, false if it cant be made
//     */
//    private boolean tryMove(Pair moveToMake, State curState) {
//        char moveDir = moveToMake.getDirection(); //todo refactor and try to make better
//        int vehicleId = moveToMake.getId();
//        int vehicleLength = curState.getVehicles().get(vehicleId).getLocation().length - 1;
//        //Gets the vehicle that needs to be moved
//        Vehicle vehicleToMove = curState.getVehicles().get(vehicleId);
//
//        //Check movement direction
//        if (moveDir == 'e' || moveDir == 'w') { //Moving left or right (+-1)
//            if (moveDir == 'e') { //If its moving right
//                //Gets the end of the vehicle
//                int vehicleEnd = vehicleToMove.getLocation()[vehicleLength];
//
//                //Gets the value where the vehicle wants to move to
//                int valueAtWhereVehicleWantsToMove = vehicleEnd + 1;
//
//                //For checking out of bounds
//                vehicleEnd = (vehicleEnd + 1) % 6;
//
//                //Check if it's going out of bounds
//                if (vehicleEnd == 1) {
//                    return false;
//                }
//
//                //Returns true if there is an empty spot, false if it's not
//                return curState.getGameboard().get(valueAtWhereVehicleWantsToMove) == -1;
//            } else { //If its moving left
//                //Gets the start of the vehicle since were moving left
//                int vehicleStart = vehicleToMove.getLocation()[0];
//
//                //Gets the value where the vehicle wants to move to
//                int valueAtWhereVehicleWantsToMove = vehicleStart - 1;
//
//                //For checking out of bounds
//                vehicleStart = (vehicleStart - 1) % 6;
//
//                //Make sure it's not going out of bounds
//                if (vehicleStart <= 0) {
//                    return false;
//                }
//
//                //Returns true if there is an empty spot, false if it's not
//                return curState.getGameboard().get(valueAtWhereVehicleWantsToMove) == -1;
//            }
//        } else if (moveDir == 'n' || moveDir == 's') { //Moving up or down (+-6)
//            if (moveDir == 'n') { //If it's moving up
//                //Gets the top of the vehicle
//                int vehicleTop = vehicleToMove.getLocation()[0];
//
//                //Gets the value where the vehicle wants to move to
//                int valueAtWhereVehicleWantsToMove = vehicleTop - 6;
//
//                //Checking out of bounds
//                if (valueAtWhereVehicleWantsToMove < 1) {
//                    return false;
//                }
//
//                //Returns true if there is an empty spot, false if it's not
//                return curState.getGameboard().get(valueAtWhereVehicleWantsToMove) == -1;
//            } else { //If it's moving down
//                //Gets the top of the vehicle
//                int vehicleBottom = vehicleToMove.getLocation()[vehicleLength];
//
//                //Gets the value where the vehicle wants to move to
//                int valueAtWhereVehicleWantsToMove = vehicleBottom + 6;
//
//                //Checking out of bounds
//                if (valueAtWhereVehicleWantsToMove > 36) {
//                    return false;
//                }
//
//                //Returns true if there is an empty spot, false if it's not
//                return curState.getGameboard().get(valueAtWhereVehicleWantsToMove) == -1;
//            }
//        }
//
//        //invalid move
//        return false;
//    }
//
//    /**
//     * Checks to see if the move is valid
//     * @param moveToMake the move that wants to be made
//     * @return True if the move is valid, false if it's not valid
//     */
//    public boolean validMove(Pair moveToMake, State curState) {
//        char moveDir = moveToMake.getDirection();
//        int vehicleId = moveToMake.getId();
//
//        //Gets the vehicle that needs to be moved
//        Vehicle vehicleToMove = vehicles.get(vehicleId);
//        char vehicleDirection = vehicleToMove.getDirection();
//
//        if (moveDir == 'n' || moveDir == 's') {
//            if (vehicleDirection != 'v') {
//                return false;
//            }
//        } else if (moveDir == 'w' || moveDir == 'e') {
//            if (vehicleDirection != 'h') {
//                return false;
//            }
//        }
//
//        return tryMove(moveToMake, curState);
//    }
//
//    /**
//     * Get the current state
//     * @return the current state or gameboard
//     */
//    public State getState() {
//        return gameboard;
//    }
//
//    /**
//     * Deep copies an old state to new state
//     * @param oldState the state to be copied from
//     */
//    public State copyState(State oldState) {
//        State newState = new State();
//
//        //DEEP copying old to new state
//        newState.setGameboard(oldState.getGameboard());
//        newState.setVehicles(oldState.getVehicles());
//        newState.setParent((oldState.getParent()));
//        newState.level = oldState.level;
//        if(oldState.getMoveMade() != null) {
//            newState.setMoveMade(oldState.getMoveMade());
//        }
//
//        return newState;
//    }
//
//    /**
//     * Makes the next move, assumes that the move is valid
//     * @param move Move to be made
//     * @param oldState the old state containing the vehicle
//     * @return Vehicle returns a new moved vehicle
//     */
//    public Vehicle makeMove(Pair move, State oldState) {
//        int vehicleId = move.getId();
//        //Deep copies a vehicle
//        Vehicle movedVehicle = new Vehicle(oldState.getVehicles().get(vehicleId));
//
//        //Amount the vehicle will move, depends on direction
//        int moveAmt;
//        //Sets the moveAmt to how much it needs to, depending on direction
//        if (move.getDirection() == 'n' || move.getDirection() == 's') {
//            moveAmt = 6;
//        } else {
//            moveAmt = 1;
//        }
//
//        //For all parts of the vehicle, move it by x amount
//        int[] newLocations = movedVehicle.getLocation().clone(); //New locations of the vehicle
//        for (int i = 0; i < movedVehicle.getLocation().length; i++) {
//            if (move.getDirection() == 'n' || move.getDirection() == 'w') { //Moving -1 or 6
//                newLocations[i] -= moveAmt;
//            } else { //Moving +1 or 6
//                newLocations[i] += moveAmt;
//            }
//        }
//
//        //Clones the locations over
//        movedVehicle.setLocation(newLocations);
//        //Return the moved vehicle
//        return movedVehicle;
//    }
//}
//
///**
// * Class to hold the game state
// */
//class State {
//    private HashMap<Integer, Integer> gameboard;
//    private ArrayList<Vehicle> vehicles;
//    private Pair moveMade;
//    private State parent;
//    public int level;
//
//    /**
//     * Empty constructor to make new state
//     */
//    public State() {
//        gameboard = new HashMap<>(36);
//    }
//
//    /**
//     * Creates a new state based on an array of vehicles
//     * @param vehicles An array of VALID vehicles
//     */
//    public State(ArrayList<Vehicle> vehicles) {
//
//        //Initialize the gameboard hashmap
//        gameboard = new HashMap<>(36);
//        this.vehicles = new ArrayList<>();
//
//        for (Vehicle curVeh : vehicles) {
//            //Gets the current vehicle
//            //Adds the vehicle to the vehicle array
//            this.vehicles.add(curVeh);
//
//            //Using a hashmap, put location in key, id in value
//            for (int j = 0; j < curVeh.getLocation().length; j++) {
//                //This line is actually terrible, but I think it'll work lmao
//                gameboard.put(curVeh.getLocation()[j], curVeh.getId());
//            }
//        }
//
//        for (int i = 1; i < 37; i++) {
//            gameboard.putIfAbsent(i, -1);
//        }
//
//        moveMade = new Pair(-1, 'q'); //Setting the move made to nothing
//    }
//
//    /**
//     * Regenerates the game board if the vehicles array was updated
//     */
//    public void regenState() {
//        for (int i = 1; i < 37; i++) {
//            gameboard.put(i, -1);
//        }
//
//        for (Vehicle curVeh : vehicles) {
//            //Using a hashmap, put location in key, id in value
//            for (int i = 0; i < curVeh.getLocation().length; i++) {
//                //This line is actually terrible, but I think it'll work lmao
//                gameboard.put(curVeh.getLocation()[i], curVeh.getId());
//            }
//        }
//
//        for (int i = 1; i < 37; i++) {
//            gameboard.putIfAbsent(i, -1);
//        }
//
//    }
//
//    /**
//     * Method to get the new game state
//     * @return ArrayList of the gameboard
//     */
//    public HashMap<Integer, Integer> getGameboard() {
//        return gameboard;
//    }
//
//    /**
//     * Sets the gameboard to the new game board - DEEP COPY
//     * @param newGameboard the new gameboard
//     */
//    public void setGameboard(HashMap<Integer, Integer> newGameboard) {
//        this.gameboard = new HashMap<>(newGameboard); //DEEP copy
//    }
//
//    /**
//     * Returns the list of vehicles in the state
//     * @return Arraylist of vehicles
//     */
//    public ArrayList<Vehicle> getVehicles() {
//        return vehicles;
//    }
//
//    /**
//     * Deep copies a set of vehicles to this class
//     * @param newVehicles the new vehicles for the state
//     */
//    public void setVehicles(ArrayList<Vehicle> newVehicles) {
//        this.vehicles = new ArrayList<>();
//        for (Vehicle vehicle : newVehicles) {
//            this.vehicles.add(new Vehicle(vehicle)); //DEEP copy inside vehicle
//        }
//    }
//
//    /**
//     * Gets the move made to get to this state
//     * @return Pair of move made
//     */
//    public Pair getMoveMade() {
//        return moveMade;
//    }
//
//    /**
//     * Sets the move made
//     * @param newMove new Pair
//     */
//    public void setMoveMade(Pair newMove) {
//        this.moveMade = new Pair(newMove.getId(), newMove.getDirection());
//    }
//
//    /**
//     * Sets the moved vehicle and updates the board
//     * @param movedVehicle the vehicle that has moved
//     */
//    public void setMovedVehicle(Vehicle movedVehicle) {
//        int vehicleId = movedVehicle.getId();
//        vehicles.set(vehicleId, movedVehicle);
//
//        this.regenState();
//    }
//
//    /**
//     * Prints out the game board in the console - DEBUG
//     */
//    public void printGameboard() {
//        StringBuilder gb = new StringBuilder();
//        for (int row = 0; row <= 30; row += 6) {
//            for (int col = 1; col < 7; col++) {
//                int val = gameboard.get(row + col);
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
//    public State getParent() {
//        return parent;
//    }
//
//    public void setParent(State parent) {
//        this.parent = parent;
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
//     * Sets the ID of a vehicle
//     * @param newId new ID as int
//     */
//    public void setId(int newId) {
//        this.id = newId;
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
//     * Returns the direction of the car, either horizontal (h) or vertical (v)
//     * @return Char of direction
//     */
//    public char getDirection() {
//        return direction;
//    }
//
//    /**
//     * Sets the direction of a vehicle, shouldn't need to be used
//     * @param newDir Char of new dir (h or v)
//     */
//    public void setDirection(char newDir) {
//        this.direction = newDir;
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
//}
//
///**
// * Class that holds movement data
// */
//class Pair {
//    int id;
//    char direction; //{'e', 'w', 'n', 's'}
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
//class StateKey {
//    private final HashMap<Integer, Integer> gameboard;
//
//    public StateKey(HashMap<Integer, Integer> gameboard) {
//        this.gameboard = gameboard;
//    }
//
//    public HashMap<Integer, Integer> getGameboard() {
//        return gameboard;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        StateKey stateKey = (StateKey) o;
//        if (gameboard.size() != stateKey.gameboard.size()) {
//            return false;
//        }
//        for (Map.Entry<Integer, Integer> entry : gameboard.entrySet()) {
//            if (!Objects.equals(entry.getValue(), stateKey.gameboard.get(entry.getKey()))) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = Objects.hashCode(gameboard);
//        //System.out.println(hash);
//        return hash;
//    }
//}