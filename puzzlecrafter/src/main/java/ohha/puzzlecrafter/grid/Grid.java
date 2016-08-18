
package ohha.puzzlecrafter.grid;

import java.util.List;
import java.util.LinkedList;

/**
 * This class serves as a conceptual wrapper for a two-dimensional integer array
 * containing the puzzle grid cells' values.
 * The actual state of the puzzle is completely contained in this class, as
 * {@Link <code>Cell</code>} objects only act as coordinates, completely
 * agnostic to the value the puzzle may have at its coordinate.
 * <p>
 * The co-ordinates of the grid vary such that the top-left corner is always at
 * (0, 0), and the components increase down- and rightward.
 * <p>
 * Two special integers have been reserved (see <code>{@Link Cell}</code>):
 * <ul>
 * <li> 0 always means a cell whose contents have not been determined by the
 * solver. Solved puzzles will not contain the value 0.
 * <li> -1 always means a cell that has been determined empty.
 * </ul>
 * The rest of the values may be interpreted freely by each puzzle. The
 * <code>Grid</code> class does not enforce what values may be written into it,
 * this is done by each puzzle.
 */
public class Grid {
    
    public final static int MIN_SIZE = 1;
    public final static int DEFAULT_SIZE = 9;
    public final static int MAX_SIZE = 100;
    
    public static final int CELL_UNDETERMINED = 0;
    public static final int CELL_EMPTY = -1;
    
    private int[][] grid;
    
    
    public Grid(int height, int width) {
        grid = new int[height][width];
    }
    
    
    public int getHeight() {
        return grid.length;
    }
    public int getWidth() {
        return grid[0].length;
    }
    
    public void setValueAt(Coordinate c, int value) {
        grid[c.getY()][c.getX()] = value;
    }
    public int getValueAt(Coordinate c) {
        return grid[c.getY()][c.getX()];
    }
    
    
    /**
     * Returns whether the value at the coordinate is undetermined or not.
     * The value 'undetermined' represents a cell whose contents have not yet
     * been determined by the solver.
     * <p>
     * The value is named in the constant <code>CELL_UNDETERMINED</code>.
     * 
     * @param c the coordinate in the puzzle grid to probe
     * @return  true if the cell is undetermined, false otherwise
     */
    public boolean isUndetermined(Coordinate c) {
        return getValueAt(c) == CELL_UNDETERMINED;
    }
    
    /**
     * Returns whether the value at the coordinate is empty or not.
     * The value 'empty' represents a cell which has been determined empty by
     * the solver.
     * <p>
     * The value is named in the constant <code>CELL_EMPTY</code>.
     * 
     * @param c the location in the puzzle grid to probe
     * @return  true if the cell is empty, false otherwise
     */
    public boolean isEmpty(Coordinate c) {
        return getValueAt(c) == CELL_EMPTY;
    }
    
    /**
     * Returns whether the value at the coordinate is filled in or not.
     * 'Filled in' means the cell is not undetermined nor empty, i.e. its value
     * represents something concrete. The interpretation of the concrete values
     * is left for each puzzle style to specify.
     * <p>
     * As 'filled in' is not a single specific value, it is not represented by a
     * constant.
     * 
     * @param c  the location in the puzzle grid to probe
     * @return  false if the cell is undetermined or empty, true otherwise
     */
    public boolean isFilledIn(Coordinate c) {
        return !(isEmpty(c) || isUndetermined(c));
    }
    
    
    /**
     * Returns a list of coordinates to use when looping over the entire grid.
     * 
     * @return a list containing coordinates for each cell in the grid
     */
    public List<Coordinate> getListOfCoordinates() {
        List<Coordinate> coordinates = new LinkedList<>();
        
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                coordinates.add(new Coordinate(x, y));
            }
        }
        return coordinates;
    }
    
    
    /**
     * Returns a deep copy of the <code>Grid</code> object it was called on.
     * 
     * @return a deep copy of the <code>Grid</code> it was called on
     */
    public Grid deepCopy() {
        Grid copy = new Grid(getHeight(), getWidth());
        
        for (Coordinate coordinate : this.getListOfCoordinates()) {
            copy.setValueAt(coordinate, this.getValueAt(coordinate));
        }
        
        return copy;
    }
}
