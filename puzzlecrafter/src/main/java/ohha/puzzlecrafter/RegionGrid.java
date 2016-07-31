
package ohha.puzzlecrafter;

import java.util.List;
import java.util.LinkedList;

public class RegionGrid extends Grid {

    private List<List<Integer[]>> regions;
    
    
    // initialise grid with given regions
    public RegionGrid(int height, int width, List<List<Integer[]>> regions) {
        super(height, width);
        this.regions = regions;
    }
    
    // initialise grid with rectangular regions with dimensions height and width
    public RegionGrid(int height, int width, int rheight, int rwidth) {
        super(height, width);
        initialiseRectangularRegions(rheight, rwidth);
    }
    
    
    private void initialiseRectangularRegions(int width, int height) {
        for (int yr = 0; yr < getHeight(); yr += height) {
            for (int xr = 0; xr < getWidth(); xr += width) {
                List<Integer[]> region = new LinkedList<>();
                
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        region.add(new Integer[]{xr + x, yr + y});
                    }
                }
                regions.add(region);
            }
        }
    }
    
    
    public List<Integer[]> getRegion(int x, int y) {
        for (List<Integer[]> region : regions) {
            for (Integer[] cell : region) {
                if (cell[0] == x && cell[1] == y) {
                    return region;
                }
            }
        }
        // this should never be returned with valid regions
        return null;
    }
    
    
    public boolean duplicatedOnRegion(int x, int y) {
        for (Integer[] cell : getRegion(x, y)) {
            if (cell[0] == x && cell[1] == y) {
                continue;
            }
            if (getCell(x, y) == getCell(cell[0], cell[1])) {
                return true;
            }
        }
        return false;
    }
}
