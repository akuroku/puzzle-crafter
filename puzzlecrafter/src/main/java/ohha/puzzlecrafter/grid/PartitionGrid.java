
package ohha.puzzlecrafter.grid;


public class PartitionGrid extends Grid {
    
    private Partition partition;
    
    
    // initialise grid with given regions
    public PartitionGrid(int height, int width, Partition partition) {
        super(height, width);
        this.partition = partition;
    }
    
    // initialise grid with rectangular regions with dimensions height and width
    public PartitionGrid(int grid_height, int grid_width, int region_height, int region_width) {
        super(grid_height, grid_width);
        this.partition = new Partition();
        partition.createRectangularPartition(region_height, region_width, getHeight() / region_height, getWidth() / region_width);
    }
    
    public Partition getPartition() {
        return partition;
    }
    
    
    public Region getRegionOf(Cell c) {
        return partition.getRegionOf(c);
    }
    
    
    public boolean isDuplicatedOnRegion(Cell c) {
        if (!isFilledIn(c)) {
            return false;
        }
        
        for (Cell cell : getRegionOf(c).getCells()) {
            if (cell.equals(c)) {
                continue;
            }
            if (getValueAt(c) == getValueAt(cell)) {
                return true;
            }
        }
        return false;
    }
}