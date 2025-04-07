package edu.iastate.cs228.hw1;

/**
 * @author Camden Fergen
 * Empty internet user TownCell
 */
public class Empty extends TownCell {

    /**
     * Creates a new EmptyCell in a town
     * @param p town that you are adding the cell too
     * @param r row the cell is in
     * @param c column the cell is in
     */
    public Empty(Town p, int r, int c) {
        super(p, r, c);
    }

    /**
     * Gets the identity of the cell.
     *
     * @return State
     */
    @Override
    public State who() {
        return State.EMPTY;
    }

    /**
     * Determines the cell type in the next cycle.
     *
     * @param tNew : town of the next cycle
     * @return TownCell
     */
    @Override
    public TownCell next(Town tNew) {

        super.census(nCensus);

        if (nCensus[EMPTY] + nCensus[OUTAGE] <= 1) {
            return new Reseller(tNew, row, col);
        } else {
            return new Casual(tNew, row, col); //Returns new Casual cell
        }
    }
}
