
package ohha.puzzlecrafter.grid;

import java.util.Set;
import java.util.HashSet;


public class Partition {
    
    private Set<Region> regions;
    
    
    public Partition() {
        regions = new HashSet<>();
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
    
    
    public void createRectangularPartition(int regionHeight, int regionWidth, int partitionHeight, int partitionWidth) {
        regions = new HashSet<>();
        
        for (int y = 0; y < partitionHeight; y++) {
            for (int x = 0; x < partitionWidth; x++) {
                addRegion(createRectangularRegion(regionHeight, regionWidth, x * regionWidth, y * regionHeight));
            }
        }
    }
    
    private Region createRectangularRegion(int regionHeight, int regionWidth, int topLeftX, int topLeftY) {
        Region region = new Region();
        
        for (int y = 0; y < regionHeight; y++) {
            for (int x = 0; x < regionWidth; x++) {
                region.addCell(new Cell(topLeftX + x, topLeftY + y));
            }
        }
        return region;
    }
    
    
    public Region getRegionOf(Cell c) {
        for (Region region : regions) {
            if (region.contains(c)) {
                return region;
            }
        }
        return null; // this should never be returned with a valid partition
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
