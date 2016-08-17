
package ohha.puzzlecrafter.grid;

import java.util.Set;
import java.util.HashSet;


/**
 * An object of this class represents a set of cells forming a region, for example the 3x3 boxes in Sudoku or the regions in Star Battle.
 * A <code>Region</code> doesn't contain any values filled into the puzzle. This task is done by {@Link Grid}.
 * <p>
 * As a <code>Region</code> is a set of cells, it may not contain duplicate cells. No demands or requirements are made on the co-ordinates of the constituent cells; they may be negative as well, although the <code>Grid</code> class only expects non-negative coordinates.
 * <p>
 * When constructing a region, no demands or requirements are made that the region is connected, either in the sense of moving cardinally, or touching-by-corners. However, the class offers methods for testing this.
 */
public class Region {
    
    private Set<Cell> cells;
    
    
    public Region() {
        cells = new HashSet<>();
    }
    public Region(int regionHeight, int regionWidth, int topLeftX, int topLeftY) {
        cells = new HashSet<>();
        
        for (int y = 0; y < regionHeight; y++) {
            for (int x = 0; x < regionWidth; x++) {
                cells.add(new Cell(topLeftX + x, topLeftY + y));
            }
        }
    }
    
    
    public void addCell(Cell cell) {
        cells.add(cell);
    }
    public Set<Cell> getCells() {
        return cells;
    }
    
    
    public boolean contains(Cell cell) {
        return cells.contains(cell);
    }
    
    
    // TODO: implement two below methods
    public boolean isConnectedCardinally(Region region) {
        return false;
    }
    public boolean isConnectedCardinalDiagonally(Region region) {
        return false;
    }
    
    
    public Region deepCopy() {
        Region copy = new Region();
        
        for (Cell c : getCells()) {
            copy.addCell(new Cell(c.getX(), c.getY()));
        }
        return copy;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + 73 * cells.stream().mapToInt(c -> c.getX() + 2 * c.getY()).sum();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Region other = (Region) obj;
        
        if (cells.size() != other.getCells().size()) {
            return false;
        }
        return cells.containsAll(other.getCells());
    }
    
    
    @Override
    public String toString() {
        String s = "";
        for (Cell c : getCells()) {
            s += c.toString() + ", ";
        }
        return s;
    }
}
