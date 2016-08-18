
package ohha.puzzlecrafter.auxiliary;

import ohha.puzzlecrafter.grid.Coordinate;
import ohha.puzzlecrafter.grid.Grid;

import java.util.List;
import java.util.LinkedList;


/**
 * Provides a slew of methods to quickly access the neighbouring cells of a
 * given cell.
 * The methods are mindful of the given grid, and will not return coordinates to
 * cells outside of the grid.
 */
public final class Neighbours {
    /**
     * Returns a list of the coordinates of the cells directly adjacent to the
     * cell indicated by the given coordinate.
     * This method returns the four cells the given cell shares an edge with;
     * namely, the cells above, below, to the right and to the left, unless they
     * lie outside of the grid.
     * 
     * @param g the grid where the cells live
     * @param c the coordinate of the cell whose neighbours to fetch
     * @return  a list of the coordinates of the cardinal neighbours
     */
    public static List<Coordinate> cardinals(Grid g, Coordinate c) {
        List<Coordinate> neighbours = new LinkedList<>();
        
        if (c.getX() > 0) {
            neighbours.add(c.shiftX(-1));
        }
        if (c.getY() > 0) { 
            neighbours.add(c.shiftY(-1));
        }
        if (c.getX() < g.getWidth() - 1) {
            neighbours.add(c.shiftX(1));
        }
        if (c.getY() < g.getHeight() - 1) {
            neighbours.add(c.shiftY(1));
        }
        return neighbours;
    }
    
    
    /**
     * Returns a list of the coordinates of the cells that are diagonal
     * neighbours to the cell indicated by the given coordinate.
     * This method returns the four cells that are diagonally adjacent to the
     * given cell; namely, the cells to the northeast, northwest, southeast and 
     * southwest, unless they lie outside of the grid.
     * 
     * @param grid the grid where the cells live
     * @param c the coordinate of the cell whose neighbours to fetch
     * @return  a list of the coordinates of the diagonal neighbours
     */
    public static List<Coordinate> diagonals(Grid grid, Coordinate c) {
        List<Coordinate> neighbours = new LinkedList<>();
        
        if (c.getX() > 0) {
            if (c.getY() > 0) {
                neighbours.add(c.shiftX(-1).shiftY(-1));
            }
            if (c.getY() < grid.getHeight() - 1) {
                neighbours.add(c.shiftX(-1).shiftY(1));
            }
        }
        if (c.getX() < grid.getWidth() - 1) {
            if (c.getY() > 0) {
                neighbours.add(c.shiftX(1).shiftY(-1));
            }
            if (c.getY() < grid.getHeight() - 1) {
                neighbours.add(c.shiftX(1).shiftY(1));
            }
        }
        return neighbours;
    }
    
    
    /**
     * Returns a list of the coordinates of the cells that touch by corners the
     * cell indicated by the given coordinate.
     * This method returns a list of the eight cells that touch the given cell;
     * namely, the cells to the north, northeast, east, southeast, south,
     * southwest, west and northwest, unless they lie outside of the grid.
     * 
     * @param grid the grid where the cells live
     * @param c the coordinate of the cell whose neighbours to fetch
     * @return  a list of the coordinates of the cells that touch the given cell
     * by corners
     */
    public static List<Coordinate> surrounding(Grid grid, Coordinate c) {
        List<Coordinate> neighbours = new LinkedList<>();
        
        if (c.getX() > 0) {
            if (c.getY() > 0) {
                neighbours.add(c.shiftX(-1).shiftY(-1));
            }
            if (c.getY() < grid.getHeight() - 1) {
                neighbours.add(c.shiftX(-1).shiftY(1));
            }
            neighbours.add(c.shiftX(-1));
        }
        if (c.getX() < grid.getWidth() - 1) {
            if (c.getY() > 0) {
                neighbours.add(c.shiftX(1).shiftY(-1));
            }
            if (c.getY() < grid.getHeight() - 1) {
                neighbours.add(c.shiftX(1).shiftY(1));
            }
            neighbours.add(c.shiftX(1));
        }
        if (c.getY() > 0) {
            neighbours.add(c.shiftY(-1));
        }
        if (c.getY() < grid.getHeight() - 1) {
            neighbours.add(c.shiftY(1));
        }
        
        return neighbours;
    }
}
