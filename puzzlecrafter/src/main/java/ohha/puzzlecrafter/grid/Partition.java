
package ohha.puzzlecrafter.grid;

import java.util.Set;
import java.util.HashSet;


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
