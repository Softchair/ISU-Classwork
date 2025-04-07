package hw3;

import java.util.ArrayList;
import java.util.Random;
import api.Descriptor;
import api.Direction;
import api.MoveResult;
import api.Shift;
import api.TilePosition;


/**
 * The Powers class contains the state and logic for an implementation of a game
 * similar to "2048".  The basic underlying state is an n by n grid of tiles, 
 * represented by integer values.  A zero in a cell is considered to be
 * "empty", and all other cells contain some power of two.  The game is played
 * by calling the method <code>shiftGrid()</code>, selecting one of the four
 * directions (LEFT, RIGHT, UP, DOWN). Each row or column is then collapsed
 * according to the algorithm described in the methods of <code>ShiftUtil</code>.
 * <p>
 * Whenever two cells are <em>merged</em>, the score is increased by the 
 * combined value of the two cells.
 * 
 * @author Camden Fergen
 */
public class Powers
{
  /**
   * The array used for the game
   */
  private int[][] game;
  
  /**
   * version of ShiftUtil used for the game
   */
  private ShiftUtil shift = new ShiftUtil(); 
  
  /**
   * version of random used for the game
   */
  private Random rand = new Random();
  
  /**
   * The score for the game
   */
  private int score; 
	
  /**
   * Constructs a game with a grid of the given size, using a default
   * random number generator.  Initially there should be two
   * nonempty cells in the grid selected by the method <code>generateTile()</code>.
   * @param givenSize
   *   size of the grid for this game
   * @param givenUtil
   *   instance of ShiftUtil to be used in this game
   */
  public Powers(int givenSize, ShiftUtil givenUtil)
  {
    game = new int[givenSize][givenSize];
    shift = givenUtil;
    for (int i = 0; i < 2; i++) {
    	TilePosition temp = generateTile();
    	int row = temp.getRow();
    	int col = temp.getCol();
    	int val = temp.getValue();
    	setValue(row, col, val);
    }
    
  }
  
  /**
   * Constructs a game with a grid of the given size, using the given
   * instance of <code>Random</code> for its random number generator.
   * Initially there should be two nonempty cells in the grid selected 
   * by the method <code>generateTile()</code>.
   * @param givenSize
   *   size of the grid for this game
   * @param givenUtil
   *   instance of ShiftUtil to be used in this game
   * @param givenRandom
   *   given instance of Random
   */
  public Powers(int givenSize, ShiftUtil givenUtil, Random givenRandom)
  {
    game = new int[givenSize][givenSize];
    shift = givenUtil;
    rand = givenRandom;
    for (int i = 0; i < 2; i++) {
    	TilePosition temp = generateTile();
    	int row = temp.getRow();
    	int col = temp.getCol();
    	int val = temp.getValue();
    	setValue(row, col, val);
    }
  }
  
  /**
   * Returns the value in the cell at the given row and column.
   * @param row
   *   given row
   * @param col
   *   given column
   * @return
   *   value in the cell at the given row and column
   */
  public int getTileValue(int row, int col)
  {
    return game[row][col];
  }
  
  /**
   * Sets the value of the cell at the given row and column.
   * <em>NOTE: This method should not normally be used by clients outside
   * of a testing environment.</em>
   * @param row
   *   given row
   * @param col
   *   given col
   * @param value
   *   value to be set
   */
  public void setValue(int row, int col, int value)
  {
    game[row][col] = value;
  }
  
  /**
   * Returns the size of this game's grid.
   * @return
   *   size of the grid
   */
  public int getSize()
  {
    return game.length;
  }
  
  /**
   * Returns the current score.
   * @return
   *   score for this game
   */
  public int getScore()
  {
    return score;
  }
  
  /**
   * Copy a row or column from the grid into a new one-dimensional array.  
   * There are four possible actions depending on the given direction:
   * <ul>
   *   <li>LEFT - the row indicated by the index <code>rowOrColumn</code> is
   *   copied into the new array from left to right
   *   <li>RIGHT - the row indicated by the index <code>rowOrColumn</code> is
   *   copied into the new array in reverse (from right to left)
   *   <li>UP - the column indicated by the index <code>rowOrColumn</code> is
   *   copied into the new array from top to bottom
   *   <li>DOWN - the row indicated by the index <code>rowOrColumn</code> is
   *   copied into the new array in reverse (from bottom to top)
   * </ul>
   * @param rowOrColumn
   *   index of the row or column
   * @param dir
   *   direction from which to begin copying
   * @return
   *   array containing the row or column
   */
  public int[] getRowOrColumn(int rowOrColumn, Direction dir)
  {
	int[] copy = new int[game.length];
	int[] reverse = new int[game.length];
	
    if (dir == Direction.LEFT || dir == Direction.RIGHT) { //if the direction is the row
    	for (int i = 0; i < game.length; i++) {
    		copy[i] = game[rowOrColumn][i];
    	}
    } else if (dir == Direction.UP || dir == Direction.DOWN) { //if the direction is the column
    	for (int i = 0; i < game.length; i++) {
    		copy[i] = game[i][rowOrColumn];
    	}
    }
    
    if (dir == Direction.DOWN || dir == Direction.RIGHT) { //reverse array if needed
    	for (int i = 0; i < copy.length; i++) { //copys array into new array
    		reverse[i] = copy[i];
    	}
    	for (int i = copy.length - 1; i >= 0; i--) { //reverses array here
    		copy[i] = reverse[copy.length - 1 -i];
    	}
    }
    
    return copy;
  }
  
  /**
   * Updates the grid by copying the given one-dimensional array into
   * a row or column of the grid.
   * There are four possible actions depending on the given direction:
   * <ul>
   *   <li>LEFT - the given array is copied into the the row indicated by the 
   *   index <code>rowOrColumn</code> from left to right
   *   <li>RIGHT - the given array is copied into the the row indicated by the 
   *   index <code>rowOrColumn</code> in reverse (from right to left)
   *   <li>UP - the given array is copied into the column indicated by the 
   *   index <code>rowOrColumn</code> from top to bottom
   *   <li>DOWN - the given array is copied into the column indicated by the 
   *   index <code>rowOrColumn</code> in reverse (from bottom to top)
   * </ul>
   * @param arr
   *   the array from which to copy
   * @param rowOrColumn
   *   index of the row or column
   * @param dir
   *   direction from which to begin copying
   */
  public void setRowOrColumn(int[] arr, int rowOrColumn, Direction dir)
  {
	  int[] reverse = new int[arr.length];
	  
	  if (dir == Direction.DOWN || dir == Direction.RIGHT) { //reverse array if needed
		  for (int i = 0; i < arr.length; i++) { //copys array into new array
		      reverse[i] = arr[i];
		  }
		  for (int i = arr.length - 1; i >= 0; i--) { //reverses array here
			  arr[i] = reverse[arr.length - 1 -i];
		  }
	  }
		
	  if (dir == Direction.LEFT || dir == Direction.RIGHT) { //if the direction is the row
	    for (int i = 0; i < arr.length; i++) {
	    	game[rowOrColumn][i] = arr[i];
	    }
	  } else if (dir == Direction.UP || dir == Direction.DOWN) { //if the direction is the column
	    for (int i = 0; i < arr.length; i++) {
	    	game[i][rowOrColumn] = arr[i];
	    }
	  }
  }

  /**
   * Plays one step of the game by shifting the grid in the given direction.
   * Returns a <code>MoveResult</code> object containing a list of Descriptor 
   * objects describing all moves performed, and a <code>TilePosition</code> object describing
   * the position and value of a newly added tile, if any. 
   * If no tiles are actually moved, the returned <code>MoveResult</code> object contains an 
   * empty list and has a null value for the new <code>TilePosition</code>.
   * <p>
   * If any tiles are moved or merged, a new tile is selected according to the 
   * <code>generate()</code> method and is added to the grid.
   * <p>
   * The shifting of each individual row or column must be performed by the 
   * method <code>shiftAll</code> of <code>ShiftUtil</code>. 
   * 
   * @param dir
   *   direction in which to shift the grid
   * @return
   *   MoveResult object containing move descriptors and new tile position
   */
  public MoveResult doMove(Direction dir)
  {
	int[] temp = new int[game.length];
	MoveResult result = new MoveResult();
	ArrayList<Shift> moves = new ArrayList<Shift>();
    
    for (int i = 0; i < game.length; i++) {
		temp = getRowOrColumn(i, dir); //creates temp array of current row
		moves = shift.shiftAll(temp); //gets moves and applys them
		setRowOrColumn(temp, i, dir); //sets the game array to new moved array	
		for (int j = 0; j < moves.size(); j++) {
			if (moves.get(j).isMerge()) {
				score += (moves.get(j).getValue()) * 2;
			}
			Descriptor m = new Descriptor(moves.get(j), i, dir); //gets moves
			result.addMove(m); //adds moves to move list
		}
	}
    
    if (!result.getMoves().isEmpty()) { //generates new tile if there was a move
    	TilePosition tempTile = generateTile();
    	int row = tempTile.getRow();
    	int col = tempTile.getCol();
    	int val = tempTile.getValue();
    	setValue(row, col, val);
    	result.setNewTile(tempTile);
    }
    
    return result;
  }

  /**
   * Use this game's instance of <code>Random</code> to generate
   * a new tile.  The tile's row and column must be an empty cell
   * of the grid, and the tile's value is either 2 or 4. 
   * The tile is selected in such a way that
   * <ul>
   *   <li>All empty cells of the grid are equally likely
   *   <li>The tile's value is a 2 with 90% probability and a 4 with 10% probability
   * </ul>
   * This method does not modify the grid.  If the grid has no
   * empty cells, returns null.
   * @return
   *   a new TilePosition containing the row, column, and value of the
   *   selected new tile, or null if the grid has no empty cells
   */
  public TilePosition generateTile()
  {
	
	int length = game.length - 1;
	  
	int row = rand.nextInt(length);
	int col = rand.nextInt(length);
	
	int score = 0; //block to be added
	
	int temp = rand.nextInt(10);
	if (temp == 1) {
		score = 4;
	} else {
		score = 2;
	}
	
	while (game[row][col] != 0) {
		row = rand.nextInt(game.length - 1);
		col = rand.nextInt(game.length - 1);
	}
    
	TilePosition result = new TilePosition(row, col, score);
	
    return result;
  }


}