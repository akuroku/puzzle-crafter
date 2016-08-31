
package ohha.puzzlecrafter.grid;

import java.util.Set;
import java.util.HashSet;


/**
 * Represents a set of cells forming a region, for example the 3x3 boxes in
 * Sudoku or the regions in Star Battle.
 * A <code>Region</code> doesn't contain any values filled into the puzzle. This
 * task is done by {@link Grid}.
 * <p>
 * As a <code>Region</code> is a set of <code>CellCoordinate</code>s, it may not
 * contain duplicate coordinates. No demands or requirements are made on the
 * components of the constituent coordinates; they may be negative as well,
 * although the  <code>Grid</code> class only expects non-negative components.
 * <p>
 * When constructing a region, no demands or requirements are made that the
 * region is connected, either in the sense of moving cardinally, or moving both
 * cardinally and diagonally. However, the class offers methods for testing
 * this.
 */
public class Region {
    
    private Set<CellCoordinate> cells;
    
    
    /**
     * A constructor for making an empty region.
     */
    public Region() {
        cells = new HashSet<>();
    }
    /**
     * A constructor for making a rectangular region.
     * @param regionHeight  height of region in cells
     * @param regionWidth   width of region in cells
     * @param topLeft   coordinate of top-left cell of region
     */
    public Region(int regionHeight, int regionWidth, CellCoordinate topLeft) {
        cells = new HashSet<>();
        
        for (int y = 0; y < regionHeight; y++) {
            for (int x = 0; x < regionWidth; x++) {
                cells.add(topLeft.shiftX(x).shiftY(y));
            }
        }
    }
    
    
    /**
     * Adds cell at given coordinate into region.
     * @param c coordinate to be added
     */
    public void addCellAt(CellCoordinate c) {
        cells.add(c);
    }
    /**
     * Returns the set of coordinates of the region's cells.
     * @return  the set of coordinates of the region's cells
     */
    public Set<CellCoordinate> getCells() {
        return cells;
    }
    
    
    /**
     * Returns true if region contains cell at given coordinate.
     * @param c coordinate of cell to test
     * @return  true if the region contains the coordinate, false otherwise
     */
    public boolean contains(CellCoordinate c) {
        return cells.contains(c);
    }
    
    
    /**
     * Not implemented yet, always returns false.
     * <p>
     * Returns true if the region is connected cardinally.
     * A region is connected cardinally if you can go from any cell in the
     * region to any other, without having to exit the region, by stepping from
     * cell to one of its neighbours in the four cardinal directions (up, down,
     * left, right).
     * @return  true if the region is connected cardinally, false otherwise
     */
    public boolean isConnectedCardinally() {
        return false;
    }
    
    /**
     * Not implemented yet, always returns false.
     * <p>
     * Returns true if the region is connected in the sense of
     * touching-by-corners.
     * A region is connected in this sense if you can go from any cell in the
     * region. to any other, without having to exit the region, by stepping from
     * cell to one of its surrounding neighbours (a cell that touches the
     * current cell at least by corners).
     * @return  true if the region is connected in the sense of
     * toucing-by-corners, false otherwise
     */
    public boolean isConnectedDiagonally() {
        return false;
    }
    
    
    /**
     * Returns a deep copy of the <code>Region</code> it was called on.
     * 
     * @return a deep copy of the <code>Region</code> it was called on
     */
    public Region deepCopy() {
        Region copy = new Region();
        
        for (CellCoordinate c : getCells()) {
            copy.addCellAt(new CellCoordinate(c.getX(), c.getY()));
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
        for (CellCoordinate c : getCells()) {
            s += c.toString() + ", ";
        }
        return s;
    }
}
