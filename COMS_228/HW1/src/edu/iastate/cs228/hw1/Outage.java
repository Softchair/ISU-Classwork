package edu.iastate.cs228.hw1;

/**
 * @author Camden Fergen
 * Outage TownCell object
 */
public class Outage extends TownCell {

    /**
     * Creates a new OutageCell in a town
     * @param p town that you are adding the cell too
     * @param r row the cell is in
     * @param c column the cell is in
     */
    public Outage (Town p, int r, int c) {
        super(p, r, c);
    }

    /**
     * Gets the identity of the cell.
     *
     * @return State
     */
    @Override
    public State who() {
        return State.OUTAGE;
    }

    /**
     * Determines the cell type in the next cycle.
     * @param tNew : town of the next cycle
     * @return TownCell
     */
    @Override
    public TownCell next(Town tNew) {

        return new Empty(tNew, row, col); //Returns Empty as outage always changes to empty

    }
}
