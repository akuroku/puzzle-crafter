
package ohha.puzzlecrafter.grid;

import java.util.Set;
import java.util.HashSet;


/**
 * Represents a partition of the puzzle grid, such as the nine 3x3 boxes of a
 * regular Sudoku.
 * As a <code>Partition</code> is a set of regions, it may not contain duplicate
 * regions.
 * <p>
 * Despite the name, no demands or requirements are made that the constituent
 * regions either cover the grid nor lack overlaps. However, the class offers
 * methods for testing this.
 */
public class Partition {
    
    private Set<Region> regions;
    
    
    /**
     * A constructor for making an empty partition.
     */
    public Partition() {
        regions = new HashSet<>();
    }
    /**
     * A constructor for making a partition consisting of a grid of rectangular regions, all the same shape.
     * The top-left corner of the top-left region will be at (0, 0).
     * @param regionHeight  the height of each region in cells
     * @param regionWidth   the width of each region in cells
     * @param partitionHeight   the height of the partition in regions
     * @param partitionWidth    the width of the partition in regions
     */
    public Partition(int regionHeight, int regionWidth, int partitionHeight, int partitionWidth) {
        regions = new HashSet<>();
        
        for (int y = 0; y < partitionHeight; y++) {
            for (int x = 0; x < partitionWidth; x++) {
                regions.add(new Region(regionHeight, regionWidth, new CellCoordinate(x * regionWidth, y * regionHeight)));
            }
        }
    }
    
    
    /**
     * Adds given region into partition.
     * @param r region to be added
     */
    public void addRegion(Region r) {
        regions.add(r);
    }
    /**
     * Returns the constituent regions of the partition.
     * @return  the set of regions of the partition
     */
    public Set<Region> getRegions() {
        return regions;
    }
    
    
    /**
     * Returns whether the partition contains the given region.
     * @param r the region to test
     * @return  true if the partition contains the region, false otherwise
     */
    public boolean contains(Region r) {
        return regions.contains(r);
    }
    
    
    /**
     * Returns the region that the cell at the given coordinate belongs to, or
     * null.
     * @param c the coordinate of the cell whose region to get
     * @return  returns the region containing the given cell, or null if no
     * containing region is found
     */
    public Region getRegionOf(CellCoordinate c) {
        for (Region region : regions) {
            if (region.contains(c)) {
                return region;
            }
        }
        return null; // this should never be returned with a valid partition
    }
    
    
    /**
     * Not implemented yet, always returns false.
     * Returns whether the given <code>Partition</code> is a valid partition
     * covering the given grid.
     * A valid partition must satisfy the following criteria:
     * <ul>
     * <li>No regions may overlap. That is, each cell may belong to only one
     * region.
     * <li>The whole grid must be covered. That is, each cell must belong to one
     * region.
     * <li>No region may contain cells that aren't in the grid.
     * </ul>
     * @param grid  the grid to test validness on
     * @return  true if the <code>Partition</code> is a valid partition, false
     * otherwise.
     */
    public boolean isValidPartition(Grid grid) {
        return false;
    }
    
    
    /**
     * Returns a deep copy of the <code>Partition</code> it was called on, deep
     * copying the consitutent <code>{@Link Region}</code>s.
     * 
     * @return a deep copy of the <code>Partition</code> it was called on
     */
    public Partition deepCopy() {
        Partition copy = new Partition();
        
        for (Region region : getRegions()) {
            copy.addRegion(region.deepCopy());
        }
        return copy;
    }
    
    
    @Override
    public String toString() {
        String s = "";
        for (Region region : regions) {
            s += region + "\n";
        }
        return s;
    }
}
