
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
 * The cells, edges and vertices can all hold values to be interpreted by each
 * concrete puzzle style as needed. For example, Fillomino stores in edges
 * whether to use a thick, dashed or invisible line between cells.
 * <p>
 * Cells, horizontal edges, vertical edges and vertices all have their own
 * coordinate systems, where the top-left-most instance is at (0, 0), and
 * the first component increases rightward and the second component increases
 * downward. These are interweaved in the actual two-dimensional integer array
 * that stores the values.
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
    
    public final static int MIN_SIZE = 3;
    public final static int DEFAULT_SIZE = 9;
    public final static int MAX_SIZE = 100;
    
    public static final int UNDETERMINED_CELL = 0;
    public static final int EMPTY_CELL = -1;
    
    public static final int UNDETERMINED_EDGE = 0;
    public static final int EMPTY_EDGE = -1;
    
    
    private int[][] grid;
    private int height;
    private int width;
    
    
    /**
     * Constructs a new empty <code>Grid</code> of the given height and width.
     * 
     * @param height    the height of the grid in cells
     * @param width     the width of the grid in cells 
     */
    public Grid(int height, int width) {
        grid = new int[2 * height + 1][2 * width + 1];
        this.height = height;
        this.width = width;
    }
    
    
    private void setGrid(int[][] grid) {
        this.grid = grid;
    }
    
    
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    
    
    /**
     * Sets the given value into the cell indicated by the given coordinate.
     * 
     * @param c     the coordinate of the cell
     * @param value the value to set in
     */
    public void setValueOfCellAt(CellCoordinate c, int value) {
        grid[2 * c.getY() + 1][2 * c.getX() + 1] = value;
    }
    
    /**
     * Returns the value in the cell indicated by the coordinate.
     * 
     * @param c the coordinate of the cell whose value to get
     * @return  the value in the given cell
     */
    public int getValueOfCellAt(CellCoordinate c) {
        return grid[2 * c.getY() + 1][2 * c.getX() + 1];
    }
    
    
    /**
     * Sets the given value into the edge indicated by the given coordinate.
     * 
     * @param e     the coordinate of the edge
     * @param value the value to set in
     */
    public void setValueOfEdgeAt(EdgeCoordinate e, int value) {
        if (e.getOrientation() == EdgeCoordinate.HORIZONTAL_EDGE) {
            grid[2 * e.getY()][2 * e.getX() + 1] = value;
        } else if (e.getOrientation() == EdgeCoordinate.VERTICAL_EDGE) {
            grid[2 * e.getY() + 1][2 * e.getX()] = value;
        }
    }
    
    /**
     * Returns the value in the edge indicated by the given coordinate.
     * 
     * @param e the coordinate of the edge whose value to get
     * @return  the value in the given edge
     */
    public int getValueOfEdgeAt(EdgeCoordinate e) {
        if (e.getOrientation() == EdgeCoordinate.HORIZONTAL_EDGE) {
            return grid[2 * e.getY()][2 * e.getX() + 1];
        }
        if (e.getOrientation() == EdgeCoordinate.VERTICAL_EDGE) {
            return grid[2 * e.getY() + 1][2 * e.getX()];
        }
        return UNDETERMINED_EDGE;
    }
    
    
    /**
     * Returns whether the coordinate given represents a cell contained in the
     * grid.
     * 
     * @param c the coordinate of the cell to be tested
     * @return  true if the grid contains the indicated cell, false otherwise
     */
    public boolean containsCell(CellCoordinate c) {
        if (c == null) {
            return false;
        }
        return 0 <= c.getX() && c.getX() < getWidth() && 0 <= c.getY() && c.getY() < getHeight();
    }
    
    /**
     * Returns whether the coordinate given represents an edge contained in the
     * grid, including the outer frame.
     * 
     * @param e the coordinate of the edge to be tested
     * @return  true if the grid contains the indicated edge, false otherwise
     */
    public boolean containsEdge(EdgeCoordinate e) {
        if (e == null) {
            return false;
        }
        if (e.getOrientation() == EdgeCoordinate.HORIZONTAL_EDGE) {
            return 0 <= e.getX() && e.getX() < getWidth() && 0 <= e.getY() && e.getY() <= getHeight();
        }
        if (e.getOrientation() == EdgeCoordinate.VERTICAL_EDGE) {
            return 0 <= e.getX() && e.getX() <= getWidth() && 0 <= e.getY() && e.getY() < getHeight();
        }
        return false;
    }
    
    /**
     * Returns whether the coordinate given represents an internal edge of the
     * grid, that is an edge not on the outer frame.
     * In other words, an edge is internal if there is a cell on both sides of
     * it.
     * 
     * @param e the coordinate of the edge to be tested
     * @return  true if the edge is an internal edge, false otherwise
     */
    public boolean isInternalEdge(EdgeCoordinate e) {
        if (!containsEdge(e)) {
            return false;
        }
        if (e.getOrientation() == EdgeCoordinate.HORIZONTAL_EDGE) {
            return !(0 == e.getY() || e.getY() == getHeight());
        }
        if (e.getOrientation() == EdgeCoordinate.VERTICAL_EDGE) {
            return !(0 == e.getX() || e.getX() == getWidth());
        }
        return false;
    }
    
    
    /**
     * Returns whether the value at the coordinate is undetermined or not.
     * The value 'undetermined' represents a cell whose contents have not yet
     * been determined by the solver.
     * <p>
     * The value is named in the constant <code>UNDETERMINED_CELL</code>.
     * 
     * @param c the coordinate in the puzzle grid to probe
     * @return  true if the cell is undetermined, false otherwise
     */
    public boolean isCellUndetermined(CellCoordinate c) {
        return getValueOfCellAt(c) == UNDETERMINED_CELL;
    }
    
    /**
     * Returns whether the value at the coordinate is empty or not.
     * The value 'empty' represents a cell which has been determined empty by
     * the solver.
     * <p>
     * The value is named in the constant <code>EMPTY_CELL</code>.
     * 
     * @param c the location in the puzzle grid to probe
     * @return  true if the cell is empty, false otherwise
     */
    public boolean isCellEmpty(CellCoordinate c) {
        return getValueOfCellAt(c) == EMPTY_CELL;
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
    public boolean isCellFilledIn(CellCoordinate c) {
        return !(isCellEmpty(c) || isCellUndetermined(c));
    }
    
    
    /**
     * Returns a list of coordinates to use when looping over every cell of the
     * grid.
     * 
     * @return a list containing coordinates for each cell in the grid
     */
    public List<CellCoordinate> getListOfCellCoordinates() {
        List<CellCoordinate> coordinates = new LinkedList<>();
        
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                coordinates.add(new CellCoordinate(x, y));
            }
        }
        return coordinates;
    }
    
    /**
     * Returns a list of coordinates to use when looping over every edge of the
     * grid.
     * 
     * @return  a list containing coordinates for each edge in the grid
     */
    public List<EdgeCoordinate> getListOfEdgeCoordinates() {
        List<EdgeCoordinate> coordinates = new LinkedList<>();
        
        for (int y = 0; y <= getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                coordinates.add(new EdgeCoordinate(x, y, EdgeCoordinate.HORIZONTAL_EDGE));
            }
        }
        
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x <= getWidth(); x++) {
                coordinates.add(new EdgeCoordinate(x, y, EdgeCoordinate.VERTICAL_EDGE));
            }
        }
        return coordinates;
    }
    
    /**
     * Returns a list of coordinates to use when looping over every internal
     * edge of the grid.
     * See also {@Link #isInternalEdge(EdgeCoordinate)}.
     * 
     * @return  a list containing coordinates for each internal edge in the grid
     */
    public List<EdgeCoordinate> getListOfInternalEdgeCoordinates() {
        List<EdgeCoordinate> coordinates = new LinkedList<>();
        
        for (int y = 1; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                coordinates.add(new EdgeCoordinate(x, y, EdgeCoordinate.HORIZONTAL_EDGE));
            }
        }
        
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 1; x < getWidth(); x++) {
                coordinates.add(new EdgeCoordinate(x, y, EdgeCoordinate.VERTICAL_EDGE));
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
        int[][] newGrid = new int[2 * height + 1][2 * width + 1];
        
        for (int y = 0; y < 2 * height + 1; y++) {
            for (int x = 0; x < 2 * width + 1; x++) {
                newGrid[y][x] = grid[y][x];
            }
        }
        copy.setGrid(newGrid);
        
        return copy;
    }
}
