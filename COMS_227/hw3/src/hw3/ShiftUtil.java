package hw3;

import java.util.ArrayList;
import api.Shift;

/**
 * Utility class containing the basic logic for performing moves in the
 * Powers game.  All methods operate on a one-dimensional array
 * of integers representing the tiles.  A cell with zero is referred to
 * as "empty" and a nonzero cell is "nonempty".  Tiles are only shifted
 * to the left; that is, tiles that are moved or merged can only move to the 
 * left.  The Powers class can use these methods to shift a row or column
 * in any direction by copying that row or column, forward or backward,
 * into a temporary one-dimensional array.
 * 
 * @author Camden Fergen
 */
public class ShiftUtil
{
  /**
   * Returns the index of the first nonempty cell that is on or after the 
   * given index <code>start</code>, or -1 if there is none.
   * @param arr
   *   given array
   * @param start
   *   index at which to start looking 
   * @return
   *   index of the first nonempty cell, or -1 if none is found
   */
  public int findNextNonempty(int[] arr, int start)
  {
	
    for (int i = start; i < arr.length; i++) { //for loop that starts at Start
    	if (arr[i] > 0) {
    		return i;
    	}
    }
    
    return -1;
    
  }
  
  /**
   *  Given an array and a starting index, finds a shift that
   *  would merge or move a tile to that index, if such a shift 
   *  exists. This method does not modify the array. If there is no shift
   *  to the given index, returns null.  This method is not required to 
   *  examine cells to the left of <code>index</code>.  Note that in 
   *  case of a merge, the value of the Shift object is the <em>current</em>
   *  value on the tile or tiles, not the new value that it would
   *  have after the merge takes place.
   *  
   *  The logic of this method can be described as follows:
   *  <pre>
   *  if cell at index is occupied (nonzero)
   *      find next occupied cell c to the right of 'index'
   *      if there is one and it is the same value
   *            create a shift to merge c with cell at 'index'
   *  else
   *      find next occupied cell c to the right of 'index'
   *      if there is one
   *           find next occupied cell c2 to the right of c
   *           if there is one, and if they are the same value
   *                create a shift to merge c and c2 into cell at index
   *           else
   *                create a shift that just moves c to 'index'
   *  return the shift object
   *  </pre>
   *            
   * @param arr
   *   array in which to search for possible shift
   * @param index
   *   index for destination of shift
   * @return  
   *   Shift object describing the shift, or null if there is no shift possible
   */
  public Shift findNextPotentialShift(int[] arr, int index)
  {
	int i = index;
	
	if (arr[i] > 0) { //if cell at i is occupied
		int c = findNextNonempty(arr, i + 1);
		if (c > -1 && arr[i] == arr[c]) { //checks to see if there is a cell that is occupied and is same value
			Shift shift = new Shift(i, c, i, arr[i]);
			return shift;
		}
	} else { //if the cell at i is not occupied
		int c = findNextNonempty(arr, i + 1);
		if (c > -1) { //checks to see if there is a next cell that is occupied
			int c2 = findNextNonempty(arr, c + 1);
			if (c2 > -1 && arr[c] == arr[c2]) { //checks to see if there is a next cell that is occupied, and is the same as c
				Shift shift = new Shift(c, c2, i, arr[c]);
				return shift;
			} else { //else returns moving the next occupied cell over to the left
				Shift shift = new Shift(c, i, arr[c]);
				return shift;
			}
		}
	}
	
    return null;
  }

  
  /**
   * Updates the array according to the given Shift.  This
   * method is not required to check whether the given Shift describes
   * a move or merge that is actually correct in the given array.
   * @param arr
   *   given array to be modified
   * @param shift
   *   the shift to be applied to the array
   */
  public void applyOneShift(int[] arr, Shift shift)
  {
	int old1 = shift.getOldIndex();
	int old2 = shift.getOldIndex2();
	int newI = shift.getNewIndex();
	  
    if (shift.isMerge()) {
    	arr[newI] = shift.getValue() + arr[old1]; //Adds old arr[i] value so it can be merged
    	if (newI == old1) { //Doesnt replace old index with 0 if it didnt move
    		arr[old2] = 0;
    	} else {
    		arr[old1] = 0;
    		arr[old2] = 0;
    	}	
    } else {
    	arr[newI] = shift.getValue();
    	if (newI == old1) { //Doesnt replace old index with 0 if it didnt move
    		arr[old2] = 0;
    	} else {
    		arr[old1] = 0;
    	}
    }
    
  }
  

  /**
   * Collapses the array to the left by performing a sequence of shifts, 
   * and returns a list of shifts that were performed.  
   * <p>
   * The idea is to iterate over the array indices from left to right, 
   * finding a shift to that index and (if one exists) applying it to the array.
   * Note that according to this logic, shifts do not "cascade": once a cell is merged 
   * with another cell, the resulting cell is not merged again during this operation.  
   * For example, when this method is applied to the array [2, 2, 4], the end result 
   * is [4, 4, 0], not [8, 0, 0].
   * @param arr
   *   array to be collapsed
   * @return
   *   list of all shifts performed in the collapse
   */
  public ArrayList<Shift> shiftAll(int[] arr)
  {
	ArrayList<Shift> shifts = new ArrayList<Shift>();
	
    for (int i = 0; i < arr.length; i++) {
    	Shift shift = findNextPotentialShift(arr, i); //generates shift if there is one
    	if (shift != null) {
    		applyOneShift(arr, shift); //applys shift
    		shifts.add(shift); //adds shift instructions to shifts arrayList
    	}
    }
    return shifts;
  }
  


  
  
}
