
package ohha.puzzlecrafter;

import ohha.puzzlecrafter.grid.Cell;
import java.util.List;


public interface Puzzle {
    
    // values represent whatever the cells may be filled with, must be > 0
    // 0 always means cell with undetermined contents, -1 always means empty cell
    public List<Integer> getValues();
    
    // returns true if and only if current state is a partial solution
    // usually tests need only be done depending on the cell that changed
    public boolean isPartialSolution(Cell c);
    
}
