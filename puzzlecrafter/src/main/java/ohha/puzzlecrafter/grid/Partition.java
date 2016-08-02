
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
    
    
    public void createRectangularPartition(int region_height, int region_width, int partition_height, int partition_width) {
        regions = new HashSet<>();
        
        for (int yp = 0; yp < partition_height; yp++) {
            for (int xp = 0; xp < partition_width; xp++) {
                Region region = new Region();
                
                for (int yr = 0; yr < region_height; yr++) {
                    for (int xr = 0; xr < region_width; xr++) {
                        region.addCell(new Cell(xp * region_width + xr, yp * region_height + yr));
                    }
                }
                addRegion(region);
            }
        }
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
