
package ohha.puzzlecrafter.grid;


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
    
    public void setCell(Cell c, int v) {
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
    
    
    public boolean isDuplicatedOnRow(Cell c) {
        if (!isFilledIn(c)) {
            return false;
        }
        
        for (int i = 0; i < getWidth(); i++) {
            if (i == c.getX()) {
                continue;
            }
            if (getValueAt(c) == getValueAt(c.switchX(i))) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isDuplicatedOnColumn(Cell c) {
        if (!isFilledIn(c)) {
            return false;
        }
        
        for (int i = 0; i < getHeight(); i++) {
            if (i == c.getY()) {
                continue;
            }
            if (getValueAt(c) == getValueAt(c.switchY(i))) {
                return true;
            }
        }
        return false;
    }
}
