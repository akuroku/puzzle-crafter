
package ohha.puzzlecrafter.grid;


public class PartitionGrid extends Grid {
    
    private Partition partition;
    
    
    // initialise grid with given regions
    public PartitionGrid(int height, int width, Partition partition) {
        super(height, width);
        this.partition = partition;
    }
    
    // initialise grid with rectangular regions with dimensions height and width
    public PartitionGrid(int gridHeight, int gridWidth, int regionHeight, int regionWidth) {
        super(gridHeight, gridWidth);
        this.partition = new Partition();
        partition.createRectangularPartition(regionHeight, regionWidth, getHeight() / regionHeight, getWidth() / regionWidth);
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