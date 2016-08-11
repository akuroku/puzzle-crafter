
package ohha.puzzlecrafter.puzzles;

import ohha.puzzlecrafter.grid.Cell;

import ohha.puzzlecrafter.auxiliary.SingleTimeEntryQueue;
import ohha.puzzlecrafter.auxiliary.Neighbours;
import java.util.List;
import java.util.LinkedList;

public class Fillomino extends Puzzle {
    
    
    public Fillomino(int height, int width) {
        super(height, width);
        super.initialiseDefaultValues(height * width);
    }
    
    
    /*
    @Override
    public Cell getNextCell(Cell c) {
        // override with a better heuristic if you can
    }
    */
    
    
    
    @Override
    public List<Integer> getCandidates(Cell cell) {
        // look for the largest possible region that fits in the remaining space
        int max = 0;
        
        for (Cell c : getGrid().getCells()) {
            if (getGrid().getValueAt(c) > max) {
                max = getGrid().getValueAt(c);
            }
        }
        
        SingleTimeEntryQueue<Cell> queue = new SingleTimeEntryQueue<>();
        queue.addIfUnadded(cell);
        
        int space = 0;
        
        while (!queue.isEmpty()) {
            Cell current = queue.dequeue();
            
            space++;
            
            for (Cell neighbour : Neighbours.cardinals(getGrid(), current)) {
                if (getGrid().isUndetermined(neighbour) || getGrid().getValueAt(neighbour) == max) {
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
    public boolean isPartialSolution(Cell cell) {
        /*
        checks if the region of the current cell is valid,
        and checks of the regions of the four neighbouring cells are valid
        (newly placed cells might box in neighbouring regions, not allowign them to get as big as they need to)
        */
        if (isRegionTooLarge(cell) || isRegionConstricted(cell)) {
            return false;
        }
        
        for (Cell neighbour : Neighbours.cardinals(getGrid(), cell)) {
            if (getGrid().getValueAt(neighbour) != getGrid().getValueAt(cell)
                    && getGrid().isFilledIn(neighbour)
                    && isRegionConstricted(neighbour)) {
                return false;
            }
        }
        return true;
    }
    
    
    // tests if region contains too many cells for its value
    private boolean isRegionTooLarge(Cell cell) {
        SingleTimeEntryQueue<Cell> queue = new SingleTimeEntryQueue<>();
        queue.addIfUnadded(cell);
        
        int value = getGrid().getValueAt(cell);
        
        for (int size = 1; !queue.isEmpty(); size++) {
            if (size > value) {
                return true;
            }
            
            Cell current = queue.dequeue();
            
            for (Cell neighbour : Neighbours.cardinals(getGrid(), current)) {
                if (getGrid().getValueAt(neighbour) == value) {
                    queue.addIfUnadded(neighbour);
                }
            }
        }
        return false;
    }
    
    
    // tests if region has the potential to be as big as its value
    private boolean isRegionConstricted(Cell cell) {
        SingleTimeEntryQueue<Cell> queue = new SingleTimeEntryQueue<>();
        queue.addIfUnadded(cell);
        
        int value = getGrid().getValueAt(cell);
        
        for (int size = 1; !queue.isEmpty(); size++) {
            if (size >= value) {
                return false;
            }
            
            Cell current = queue.dequeue();
            
            for (Cell neighbour : Neighbours.cardinals(getGrid(), current)) {
                if (getGrid().getValueAt(neighbour) == value || getGrid().isUndetermined(neighbour)) {
                    queue.addIfUnadded(neighbour);
                }
            }
        }
        return true;
    }
    
    
    @Override
    public Puzzle deepCopy() {
        Fillomino copy = new Fillomino(getGrid().getHeight(), getGrid().getWidth());
        copy.setValues(new LinkedList<>(getValues()));
        copy.setGrid(getGrid().deepCopy());
        return copy;
    }
}
