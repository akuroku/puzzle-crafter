
package ohha.puzzlecrafter;

import ohha.puzzlecrafter.grid.Grid;
import ohha.puzzlecrafter.grid.Cell;
import ohha.puzzlecrafter.grid.Partition;


public final class IsDuplicated {
    public static boolean onRow(Grid g, Cell c) {
        if (!g.isFilledIn(c)) {
            return false;
        }
        
        for (int i = 0; i < g.getWidth(); i++) {
            if (i == c.getX()) {
                continue;
            }
            if (g.getValueAt(c) == g.getValueAt(c.switchX(i))) {
                return true;
            }
        }
        return false;
    }
    
    
    public static boolean onColumn(Grid g, Cell c) {
        if (!g.isFilledIn(c)) {
            return false;
        }
        
        for (int i = 0; i < g.getHeight(); i++) {
            if (i == c.getY()) {
                continue;
            }
            if (g.getValueAt(c) == g.getValueAt(c.switchY(i))) {
                return true;
            }
        }
        return false;
    }
    
    
    public static boolean onRegion(Grid g, Partition p, Cell c) {
        if (!g.isFilledIn(c)) {
            return false;
        }
        
        for (Cell cell : p.getRegionOf(c).getCells()) {
            if (cell.equals(c)) {
                continue;
            }
            if (g.getValueAt(c) == g.getValueAt(cell)) {
                return true;
            }
        }
        return false;
    }
}
