
package ohha.puzzlecrafter.auxiliary;

import ohha.puzzlecrafter.grid.Grid;
import ohha.puzzlecrafter.grid.Coordinate;
import ohha.puzzlecrafter.grid.Partition;


/**
 * Provides a slew of static methods for testing whether a cell's value is
 * unique in its row, column or region.
 */
public final class IsDuplicated {
    /**
     * Returns whether a cell's value is unique on its row.
     * Uniqueness is only tested if the cell's value is filled in (see
     * <code>{@Link Grid}</code>). Undetermined and empty are considered non-
     * values, and the method returns false whether the cell is the only
     * undetermined/empty-valued cell on its row or not.
     * 
     * @param grid  the grid to test on
     * @param c     the coordinate of the cell to test
     * @return      returns true if the cell is filled in and unique on its row,
     * and false otherwise
     */
    public static boolean onRow(Grid grid, Coordinate c) {
        if (!grid.isFilledIn(c)) {
            return false;
        }
        
        for (int i = 0; i < grid.getWidth(); i++) {
            if (i == c.getX()) {
                continue;
            }
            if (grid.getValueOfCellAt(c) == grid.getValueOfCellAt(c.switchX(i))) {
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * Returns whether a cell's value is unique on its column.
     * Uniqueness is only tested if the cell's value is filled in (see
     * <code>{@Link Grid}</code>). Undetermined and empty are considered non-
     * values, and the method returns false whether the cell is the the only
     * undetermined/empty-valued cell on its column or not.
     * 
     * @param grid  the grid to test on
     * @param c     the coordinate of the cell to test
     * @return      returns true if the cell is filled in and unique on its
     * column, and false otherwise
     */
    public static boolean onColumn(Grid grid, Coordinate c) {
        if (!grid.isFilledIn(c)) {
            return false;
        }
        
        for (int i = 0; i < grid.getHeight(); i++) {
            if (i == c.getY()) {
                continue;
            }
            if (grid.getValueOfCellAt(c) == grid.getValueOfCellAt(c.switchY(i))) {
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * Returns whether a cell's value is unique on its region.
     * Uniqueness is only tested if the cell's value is filled in (see
     * <code>{@Link Grid}</code>). Undetermined and empty are considered non-
     * values, and the method returns false whether the cell is the the only
     * undetermined/empty-valued cell on its region or not.
     * <p>
     * The method expects to receive a valid partition (see
     * <code>{@Link Partition}</code>), in particular it expects that the cell
     * is contained in one of the partition's regions.
     * 
     * @param grid      the grid to test on
     * @param partition the partition to test on
     * @param c         the coordinate of the cell to test
     * @return          returns true if the cell is filled in and unique on its
     * column, and false otherwise
     */
    public static boolean onRegion(Grid grid, Partition partition, Coordinate c) {
        if (!grid.isFilledIn(c)) {
            return false;
        }
        
        for (Coordinate coordinate : partition.getRegionOf(c).getCells()) {
            if (coordinate.equals(c)) {
                continue;
            }
            if (grid.getValueOfCellAt(c) == grid.getValueOfCellAt(coordinate)) {
                return true;
            }
        }
        return false;
    }
}
