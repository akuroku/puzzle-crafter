
package ohha.puzzlecrafter.grid;

import java.util.Set;
import java.util.HashSet;


/**
 * An object of this class is a set of <code>{@Link Region}</code>s, represents a partition of the puzzle grid, such as the nine 3x3 boxes of a regular Sudoku.
 * As a <code>Partition</code> is a set of regions, it may not contain duplicate regions.
 * <p>
 * Despite the name, no demands or requirements are made that the constituent regionSs either cover the grid nor lack overlaps. However, the class offers methods for testing this.
 */
public class Partition {
    
    private Set<Region> regions;
    
    
    public Partition() {
        regions = new HashSet<>();
    }
    public Partition(int regionHeight, int regionWidth, int partitionHeight, int partitionWidth) {
        regions = new HashSet<>();
        
        for (int y = 0; y < partitionHeight; y++) {
            for (int x = 0; x < partitionWidth; x++) {
                regions.add(new Region(regionHeight, regionWidth, x * regionWidth, y * regionHeight));
            }
        }
    }
    
    
    public void addRegion(Region r) {
        regions.add(r);
    }
    public Set<Region> getRegions() {
        return regions;
    }
    
    
    public boolean contains(Region r) {
        return regions.contains(r);
    }
    
    
    public Region getRegionOf(Cell c) {
        for (Region region : regions) {
            if (region.contains(c)) {
                return region;
            }
        }
        return null; // this should never be returned with a valid partition
    }
    
    
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
