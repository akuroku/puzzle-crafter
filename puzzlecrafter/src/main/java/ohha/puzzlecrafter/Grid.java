
package ohha.puzzlecrafter;

import java.util.List;

public class Grid {
    
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
    
    public void setCell(int x, int y, int v) {
        grid[y][x] = v;
    }
    
    public int getCell(int x, int y) {
        return grid[y][x];
    }
    
    
    public boolean isUndetermined(int x, int y) {
        return getCell(x, y) == 0;
    }
    
    public boolean isEmpty(int x, int y) {
        return getCell(x, y) == -1;
    }
    
    public boolean isFilledIn(int x, int y) {
        return getCell(x, y) > 0;
    }
    
    
    // expects to be called on non-empty cell
    public boolean duplicatedOnRow(int x, int y) {
        if (!isFilledIn(x, y)) {
            return false;
        }
        
        for (int i = 0; i < getWidth(); i++) {
            if (i == x) {
                continue;
            }
            if (getCell(x, y) == getCell(i, y)) {
                return true;
            }
        }
        return false;
    }
    
    // expects to be called on non-empty cell
    public boolean duplicatedOnColumn(int x, int y) {
        if (!isFilledIn(x, y)) {
            return false;
        }
        
        for (int i = 0; i < getHeight(); i++) {
            if (i == y) {
                continue;
            }
            if (getCell(x, y) == getCell(x, i)) {
                return true;
            }
        }
        return false;
    }
}
