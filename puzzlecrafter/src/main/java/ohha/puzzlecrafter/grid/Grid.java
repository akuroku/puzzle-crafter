
package ohha.puzzlecrafter.grid;

import java.util.List;
import java.util.LinkedList;

/**
 * This class serves as a conceptual wrapper for a two-dimensional integer array containing the puzzle grid cells' values.
 * The actual state of the puzzle is completely contained in this class, as {@Link <code>Cell</code>} objects only act as coordinates, completely agnostic to the value the puzzle may have at its coordinate.
 * <p>
 * The co-ordinates of the grid vary such that the top-left corner is always at (0, 0), and the components increase down- and rightward.
 * <p>
 * Two special integer values have been reserved:
 * <ul>
 * <li> 0 always means a cell whose contents have not been determined by the solver. Solved puzzles will not contain the value 0.
 * <li> -1 always means a cell that has been determined empty.
 * </ul>
 * The rest of the values may be interpreted freely by each puzzle. The <code>Grid</code> class does not enforce what values may be written into it, this is done by each puzzle.
 */
public class Grid {
    
    public final static int MIN_SIZE = 1;
    public final static int DEFAULT_SIZE = 9;
    public final static int MAX_SIZE = 100;
    
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
    
    public void setValueAt(Cell cell, int value) {
        grid[cell.getY()][cell.getX()] = value;
    }
    public int getValueAt(Cell cell) {
        return grid[cell.getY()][cell.getX()];
    }
    
    
    public boolean isUndetermined(Cell cell) {
        return getValueAt(cell) == 0;
    }
    public boolean isEmpty(Cell cell) {
        return getValueAt(cell) == -1;
    }
    public boolean isFilledIn(Cell cell) {
        return getValueAt(cell) > 0;
    }
    
    
    public List<Cell> getCells() {
        List<Cell> cells = new LinkedList<>();
        
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                cells.add(new Cell(x, y));
            }
        }
        return cells;
    }
    
    
    public Grid deepCopy() {
        Grid copy = new Grid(getHeight(), getWidth());
        
        for (Cell cell : this.getCells()) {
            copy.setValueAt(cell, this.getValueAt(cell));
        }
        
        return copy;
    }
}
