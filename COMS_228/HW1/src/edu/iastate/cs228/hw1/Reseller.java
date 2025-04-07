package edu.iastate.cs228.hw1;

/**
 * @author Camden Fergen
 * Reseller internet user TownCell object
 */
public class Reseller extends TownCell {

    /**
     * Creates a new ResellerCell in a town
     * @param p town that you are adding the cell too
     * @param r row the cell is in
     * @param c column the cell is in
     */
    public Reseller (Town p, int r, int c) {
        super(p, r, c);
    }

    /**
     * Gets the identity of the cell.
     *
     * @return State
     */
    @Override
    public State who() {
        return State.RESELLER;
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

        if (nCensus[CASUAL] <= 3 || nCensus[EMPTY] >= 3) { //Sets the current cell to whatever it should be in the next cycle
            return new Empty(tNew, row, col);
        } else {
            return new Reseller(tNew, row, col);
        }
    }
}
