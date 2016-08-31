
package ohha.puzzlecrafter.grid;

/**
 * Represents coordinates of a cell in the puzzle grid.
 * They serve as conceptual wrappers for a pair of numbers. In particular, this
 * means that they do not hold the actual values found in the puzzle's cells,
 * see {@link Grid} for this.
 * <p>
 * <code>CellCoordinate</code> objects are immutable, and the "modifier" methods
 * return new <code>CellCoordinate</code> objects instead of modifying the
 * <code>CellCoordinate</code> they were called on.
 */
public class CellCoordinate { 
    
    private final int x;
    private final int y;
    
    
    /**
     * Constructs a new <code>CellCoordinate</code> with the given x- and
     * y-components.
     * Although a {@link Grid} expects non-negative components that are within
     * the bounds of the grid, this demand is not enforced in this class.
     * 
     * @param x the x-component
     * @param y the y-component
     */
    public CellCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    
    
    /**
     * Returns a new <code>CellCoordinate</code> with a shifted x-component.
     * Specifically, the new <code>CellCoordinate</code> shares its y-component
     * with the <code>CellCoordinate</code> the method was called on, and the
     * new <code>CellCoordinate</code>'s x-component is calculated by adding
     * <code>shift</code> to the x-coordinate of the cell the method was called
     * on.
     * 
     * @param   shift   shift in the x-direction
     * @return  a new <code>CellCoordinate</code> with a shifted x-component
     */
    public CellCoordinate shiftX(int shift) {
        return new CellCoordinate(this.x + shift, this.y);
    }
    
    /**
     * Returns a new <code>CellCoordinate</code> with a shifted y-component.
     * Specifically, the new <code>CellCoordinate</code> shares its x-component
     * with the <code>CellCoordinate</code> the method was called on, and the
     * new <code>CellCoordinate</code>'s y-component is calculated by adding
     * <code>shift</code> to the y-component of the cell the method was called
     * on.
     * 
     * @param   shift   shift in the y-direction
     * @return  a new <code>CellCoordinate</code> with a shifted y-component
     */
    public CellCoordinate shiftY(int shift) {
        return new CellCoordinate(this.x, this.y + shift);
    }
    
    
    /**
     * Returns a new <code>CellCoordinate</code> with the specified x-component.
     * Specifically, the new <code>CellCoordinate</code> shares its y-component with
     * the <code>CellCoordinate</code> the method was called on, and the new
     * <code>CellCoordinate</code>'s x-component is <code>newx</code>.
     * 
     * @param   newx   the new x-coordinate
     * @return  a new <code>CellCoordinate</code> with a switched x-component
     */
    public CellCoordinate switchX(int newx) {
        return new CellCoordinate(newx, this.y);
    }
    
    /**
     * Returns a new <code>CellCoordinate</code> with the specified y-component.
     * Specifically, the new <code>CellCoordinate</code> shares its x-component
     * with the <code>CellCoordinate</code> the method was called on, and the
     * new <code>CellCoordinate</code>'s y-component is <code>newy</code>.
     * 
     * @param   newy   the new y-component
     * @return  a new <code>CellCoordinate</code> with a switched y-component
     */
    public CellCoordinate switchY(int newy) {
        return new CellCoordinate(this.x, newy);
    }
    
    
    /**
     * Returns a new <code>CellCoordinate</code> one step away from the given
     * <code>CellCoordinate</code> in the given direction.
     * As this method is grid-agnostic, it lets you step outside of the grid.
     * For a method that doesn't return coordinates outside the given {@link
     * Grid}, see {@link ohha.puzzlecrafter.auxiliary.Neighbours}.
     * 
     * @param side  the direction of the wanted neighbour
     * @return      the coordinate of the neighbour
     */
    public CellCoordinate getNeighbour(Side side) {
        if (side == Side.TOP) {
            return shiftY(-1);
        }
        if (side == Side.LEFT) {
            return shiftX(-1);
        }
        if (side == Side.BOTTOM) {
            return shiftY(1);
        }
        if (side == Side.RIGHT) {
            return shiftX(1);
        }
        return null;
    }
    
    
    /**
     * Returns a new {@link EdgeCoordinate} indicating the edge on the given
     * side of the cell the method is called on.
     * 
     * @param side  the side of the edge that is wanted
     * @return      the coordinate of the edge on the given side
     */
    public EdgeCoordinate getEdgeAt(Side side) {
        if (side == Side.TOP) {
            return new EdgeCoordinate(x, y, EdgeCoordinate.HORIZONTAL_EDGE);
        }
        if (side == Side.LEFT) {
            return new EdgeCoordinate(x, y, EdgeCoordinate.VERTICAL_EDGE);
        }
        if (side == Side.BOTTOM) {
            return new EdgeCoordinate(x, y + 1, EdgeCoordinate.HORIZONTAL_EDGE);
        }
        if (side == Side.RIGHT) {
            return new EdgeCoordinate(x + 1, y, EdgeCoordinate.VERTICAL_EDGE);
        }
        return null;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final CellCoordinate other = (CellCoordinate) obj;
        return this.x == other.x && this.y == other.y;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.x;
        hash = 73 * hash + this.y;
        return hash;
    }
    
    
    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ")";
    }
}