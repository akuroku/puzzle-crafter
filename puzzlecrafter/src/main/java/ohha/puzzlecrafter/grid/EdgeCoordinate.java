
package ohha.puzzlecrafter.grid;

/**
 * Represents coordinates of an edge in the grid.
 * As {@link CellCoordinate}s, they serve mostly as conceptual wrappers for
 * pairs of numbers, and do not hold the actual values the grid's edges may
 * have, see {@link Grid}.
 * <p>
 * An edge's orientation is meant to be specified with the constants found in
 * this class, and never as a bare number.
 */
public class EdgeCoordinate {
    
    public static final int HORIZONTAL_EDGE = 0;
    public static final int VERTICAL_EDGE = 1;
    
    private final int x;
    private final int y;
    private final int orientation;
    
    
    /**
     * Constructs a new EdgeCoordinate representing the edge with the given
     * orientation at the given coordinates.
     * The orientation should always be specified with a constant in
     * <code>EdgeCoordinate</code>, never with a bare number.
     * 
     * @param x             the x-component of the edge
     * @param y             the y-component of the edge
     * @param orientation   the orientation of the edge
     */
    public EdgeCoordinate(int x, int y, int orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }
    
    
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getOrientation() {
        return orientation;
    }
    
    
    // En tiedä miksi testit eivät tapa yhtään mutaatiota tästä metodista
    /**
     * Returns the cell on the specified side of the edge.
     * Asking for the cell above or below a vertical edge, or to the left or
     * right of a horizontal edge, results in a returned <code>null</code>.
     * 
     * @param side  the side of the edge where the cell is wanted
     * @return      the coordinate of the cell on the specified side of the
     * edge, or null if there is no such cell
     */
    public CellCoordinate getCellAt(Side side) {
        if (orientation == VERTICAL_EDGE) {
            if (side == Side.LEFT) {
                return new CellCoordinate(x - 1, y);
            } else if (side == Side.RIGHT) {
                return new CellCoordinate(x, y);
            }
        } else if (orientation == HORIZONTAL_EDGE) {
            if (side == Side.TOP) {
                return new CellCoordinate(x, y - 1);
            } else if (side == Side.BOTTOM) {
                return new CellCoordinate(x, y);
            }
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
        
        final EdgeCoordinate other = (EdgeCoordinate) obj;
        return this.x == other.x && this.y == other.y
                && this.orientation == other.orientation;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        hash = 97 * hash + this.orientation;
        return hash;
    }
}
