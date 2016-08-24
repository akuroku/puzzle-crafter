
package ohha.puzzlecrafter.puzzles;

import ohha.puzzlecrafter.grid.Coordinate;
import ohha.puzzlecrafter.grid.Side;
import ohha.puzzlecrafter.auxiliary.SingleTimeEntryQueue;
import ohha.puzzlecrafter.auxiliary.Neighbours;

import java.util.List;
import java.util.LinkedList;


/**
 * Implements the
 * <a href="https://tspuzzles.wordpress.com/rules/fillomino/">Fillomino</a>
 * puzzle style.
 * Solver performance is currently slow.
 */
public class Fillomino extends Puzzle {
    
    public static final int THIN_DASHED_EDGE = 0;
    public static final int THICK_SOLID_EDGE = 1;
    public static final int NO_EDGE = -1;
    
    
    public Fillomino(int height, int width) {
        super(height, width);
        super.initialiseDefaultValues(height * width);
        name = "Fillomino";
    }
    
    
    @Override
    public void setCell(Coordinate c, int value) {
        super.setCell(c, value);
        
        for (Side side : Side.values()) {
            Coordinate neighbour = c.getNeighbour(side);
            
            if (getGrid().contains(neighbour)) {
                if (getGrid().isUndetermined(neighbour)) {
                    getGrid().setValueOfEdgeAt(c, side, THIN_DASHED_EDGE);
                } else if (getGrid().getValueOfCellAt(neighbour) == getGrid().getValueOfCellAt(c)) {
                    getGrid().setValueOfEdgeAt(c, side, NO_EDGE);
                } else {
                    getGrid().setValueOfEdgeAt(c, side, THICK_SOLID_EDGE);
                }
            }
        }
    }
    
    @Override
    public void setCellUndetermined(Coordinate c) {
        super.setCellUndetermined(c);
        
        for (Side side : Side.values()) {
            getGrid().setValueOfEdgeAt(c, side, Fillomino.THIN_DASHED_EDGE);
        }
    }
    
    
    /*
    @Override
    public Coordinate getNextCell(Coordinate c) {
        // override with a better heuristic if you can
    }
    */
    
    
    
    @Override
    public List<Integer> getCandidates(Coordinate cell) {
        // look for the largest possible region that fits in the remaining space
        int max = 0;
        
        for (Coordinate c : getGrid().getListOfCoordinates()) {
            if (getGrid().getValueOfCellAt(c) > max) {
                max = getGrid().getValueOfCellAt(c);
            }
        }
        
        SingleTimeEntryQueue<Coordinate> queue = new SingleTimeEntryQueue<>();
        queue.addIfUnadded(cell);
        
        int space = 0;
        
        while (!queue.isEmpty()) {
            Coordinate current = queue.dequeue();
            
            space++;
            
            for (Coordinate neighbour : Neighbours.cardinals(getGrid(), current)) {
                if (getGrid().isUndetermined(neighbour) || getGrid().getValueOfCellAt(neighbour) == max) {
                    queue.addIfUnadded(neighbour);
                }
            }
        }
        
        if (space > max) {
            max = space;
        }
        
        List<Integer> values = new LinkedList<>();
        for (int i = 1; i <= max; i++) {
            values.add(i);
        }
        return values;
    }
    
    
    @Override
    public boolean isPartialSolution(Coordinate cell) {
        /*
        checks if the region of the current cell is valid,
        and checks of the regions of the four neighbouring cells are valid
        (newly placed cells might box in neighbouring regions, not allowing them
        to get as big as they need to)
        */
        if (isRegionTooLarge(cell) || isRegionConstricted(cell)) {
            return false;
        }
        
        for (Coordinate neighbour : Neighbours.cardinals(getGrid(), cell)) {
            if (getGrid().getValueOfCellAt(neighbour) != getGrid().getValueOfCellAt(cell)
                    && getGrid().isFilledIn(neighbour)
                    && isRegionConstricted(neighbour)) {
                return false;
            }
        }
        return true;
    }
    
    
    /**
     * Returns whether the region contains too many cells for its value. For
     * example, three adjacent 2s form a region of size three, but its size 
     * should be two in accordance to its value.
     * 
     * @param c the coordinate of the cell to test
     * @return  true if the region contains more cells than its value allows,
     * false otherwise
     */
    private boolean isRegionTooLarge(Coordinate c) {
        SingleTimeEntryQueue<Coordinate> queue = new SingleTimeEntryQueue<>();
        queue.addIfUnadded(c);
        
        int value = getGrid().getValueOfCellAt(c);
        
        for (int size = 1; !queue.isEmpty(); size++) {
            if (size > value) {
                return true;
            }
            
            Coordinate current = queue.dequeue();
            
            for (Coordinate neighbour : Neighbours.cardinals(getGrid(), current)) {
                if (getGrid().getValueOfCellAt(neighbour) == value) {
                    queue.addIfUnadded(neighbour);
                }
            }
        }
        return false;
    }
    
    
    /**
     * Returns whethee the region has enough space to expand to satisfy its
     * value. For example, a lone cell of 13 with nine undetermined cells left can't
     * expand to its required size of 13 cells.
     * 
     * @param c the coordinate of the cell to test
     * @return  true if the region is able to expand to its required value,
     * false otherwise
     */
    private boolean isRegionConstricted(Coordinate c) {
        SingleTimeEntryQueue<Coordinate> queue = new SingleTimeEntryQueue<>();
        queue.addIfUnadded(c);
        
        int value = getGrid().getValueOfCellAt(c);
        
        for (int size = 1; !queue.isEmpty(); size++) {
            if (size >= value) {
                return false;
            }
            
            Coordinate current = queue.dequeue();
            
            for (Coordinate neighbour : Neighbours.cardinals(getGrid(), current)) {
                if (getGrid().getValueOfCellAt(neighbour) == value || getGrid().isUndetermined(neighbour)) {
                    queue.addIfUnadded(neighbour);
                }
            }
        }
        return true;
    }
    
    
    @Override
    public Puzzle deepCopy() {
        Fillomino copy = new Fillomino(getGrid().getHeight(), getGrid().getWidth());
        copy.setGrid(getGrid().deepCopy());
        // clues not used
        copy.setValues(new LinkedList<>(getValues()));
        copy.setGivens(new LinkedList<>(getGivens()));
        
        return copy;
    }
}
