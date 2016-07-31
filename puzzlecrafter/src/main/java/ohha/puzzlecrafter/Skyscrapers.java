
package ohha.puzzlecrafter;

import java.util.List;
import java.util.LinkedList;

public class Skyscrapers implements Puzzle {
    
    private Grid grid; // grid of values
    private List<Integer> values; // list of values that may be placed in cells
    
    
    public Skyscrapers(int size) {
        this.grid = new Grid(size, size);
        this.values = new LinkedList<>();
        for (int i = 1; i <= size; i++) {
            values.add(i);
        }
    }
    
    
    @Override
    public List<Integer> getValues() {
        return values;
    }
    
    
    @Override
    public boolean isValidState(int x, int y) {
        if (grid.duplicatedOnRow(x, y) || grid.duplicatedOnColumn(x, y)) {
            return false;
        }
        return true;
    }
    
    
}
