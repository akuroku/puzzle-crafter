
package ohha.puzzlecrafter;

import java.util.List;

public interface Puzzle {
    
    // values represent whatever the cells may be filled with, must be > 0
    // 0 always means cell with undetermined contents, -1 always means empty cell
    public List<Integer> getValues();
    
    public boolean isValidState(int x, int y);
    
}
