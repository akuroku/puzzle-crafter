
package ohha.puzzlecrafter.grid;

/**
 * Represents coordinates of a cell in the puzzle grid.
 * They serve as conceptual wrappers for a pair of numbers. In particular, this
 * means that they do not hold the actual values found in the puzzle's cells,
 * see {@Link <code>Grid</code>} for this.
 * <p>
 * <code>Coordinate</code> objects are immutable, and the "modifier" methods
 * return new <code>Coordinate</code> objects instead of modifying the
 * <code>Coordinate</code> they were called on.
 */
public class Coordinate { 
    
    private final int x;
    private final int y;
    
    
    public Coordinate(int x, int y) {
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
     * Returns a new <code>Coordinate</code> with a shifted x-component.
     * Specifically, the new <code>Coordinate</code> shares its y-component with
     * the <code>Coordinate</code> the method was called on, and the new
     * <code>Coordinate</code>'s x-component is calculated by adding
     * {@param shift} to the x-coordinate of the cell the method was called on.
     * 
     * @param   shift   shift in the x-direction
     * @return  a new <code>Coordinate</code> with a shifted x-component
     */
    public Coordinate shiftX(int shift) {
        return new Coordinate(this.x + shift, this.y);
    }
    
    /**
     * Returns a new <code>Coordinate</code> with a shifted y-component.
     * Specifically, the new <code>Coordinate</code> shares its x-component with
     * the <code>Coordinate</code> the method was called on, and the new
     * <code>Coordinate</code>'s y-component is calculated by adding
     * {@param shift} to the y-component of the cell the method was called on.
     * 
     * @param   shift   shift in the y-direction
     * @return  a new <code>Coordinate</code> with a shifted y-component
     */
    public Coordinate shiftY(int shift) {
        return new Coordinate(this.x, this.y + shift);
    }
    
    
    /**
     * Returns a new <code>Coordinate</code> with the specified x-component.
     * Specifically, the new <code>Coordinate</code> shares its y-component with
     * the <code>Coordinate</code> the method was called on, and the new
     * <code>Coordinate</code>'s x-component is {@param newx}.
     * 
     * @param   newx   the new x-coordinate
     * @return  a new <code>Coordinate</code> with a switched x-component
     */
    public Coordinate switchX(int newx) {
        return new Coordinate(newx, this.y);
    }
    
    /**
     * Returns a new <code>Coordinate</code> with the specified y-component.
     * Specifically, the new <code>Coordinate</code> shares its x-component with
     * the <code>Coordinate</code> the method was called on, and the new
     * <code>Coordinate</code>'s y-component is {@param newy}.
     * 
     * @param   newy   the new y-component
     * @return  a new <code>Coordinate</code> with a switched y-component
     */
    public Coordinate switchY(int newy) {
        return new Coordinate(this.x, newy);
    }
    
    
    /**
     * Returns a new <code>Coordinate</code> one step away from the given
     * <code>Coordinate</code> in the given direction.
     * As this method is grid-agnostic, it lets you step outside of the grid.
     * For a method that doesn't return coordinates outside the given {@link
     * Grid}, see {@Link ohha.puzzlecrafter.auxiliary.Neighbours].
     * 
     * @param side  the direction of the wanted neighbour
     * @return      the coordinate of the neighbour
     */
    public Coordinate getNeighbour(Side side) {
        switch (side) {
            case TOP: {
                return shiftY(-1);
            }
            case LEFT: {
                return shiftX(-1);
            }
            case BOTTOM: {
                return shiftY(1);
            }
            case RIGHT: {
                return shiftX(1);
            }
            default: {
                return null;
            }
        }
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Coordinate other = (Coordinate) obj;
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