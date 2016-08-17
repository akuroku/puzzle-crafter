
package ohha.puzzlecrafter.grid;

import java.util.List;
import java.util.LinkedList;

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
    
    public void setValueAt(Cell c, int v) {
        grid[c.getY()][c.getX()] = v;
    }
    public int getValueAt(Cell c) {
        return grid[c.getY()][c.getX()];
    }
    
    
    public boolean isUndetermined(Cell c) {
        return getValueAt(c) == 0;
    }
    public boolean isEmpty(Cell c) {
        return getValueAt(c) == -1;
    }
    public boolean isFilledIn(Cell c) {
        return getValueAt(c) > 0;
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
        Grid grid = new Grid(getHeight(), getWidth());
        
        for (Cell cell : this.getCells()) {
            grid.setValueAt(cell, this.getValueAt(cell));
        }
        
        return grid;
    }
}
