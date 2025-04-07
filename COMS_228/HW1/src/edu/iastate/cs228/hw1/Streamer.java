package edu.iastate.cs228.hw1;

/**
 * @author Camden Fergen
 * Streamer internet user TownCell object
 */
public class Streamer extends TownCell {

    /**
     * Creates a new EmptyCell in a town
     * @param p town that you are adding the cell too
     * @param r row the cell is in
     * @param c column the cell is in
     */
    public Streamer (Town p, int r, int c) {
        super(p, r, c);
    }

    /**
     * Gets the identity of the cell.
     *
     * @return State
     */
    @Override
    public State who() {
        return State.STREAMER;
    }

    /**
     * Determines the cell type in the next cycle.
     *
     * @param tNew : town of the next cycle
     * @return TownCell
     */
    @Override
    public TownCell next(Town tNew) {

        super.census(nCensus); //Gets the census of all the cells neighboring the current cell

        if (nCensus[EMPTY] + nCensus[OUTAGE] <= 1) {
            return new Reseller(tNew, row, col);
        } else if (nCensus[RESELLER] >= 1) { //Sets the current cell to whatever it should be in the next cycle
            return new Outage(tNew, row, col);
        } else if (nCensus[OUTAGE] >= 1) {
            return new Empty(tNew, row, col);
        } else if (nCensus[CASUAL] >= 5) {
            return new Streamer(tNew, row, col);
        } else {
            return new Streamer(tNew, row, col);
        }

    }
}
