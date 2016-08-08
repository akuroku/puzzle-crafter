
package ohha.puzzlecrafter;

import ohha.puzzlecrafter.grid.Cell;


public class Skyscrapers extends Puzzle {
    
    public Skyscrapers(int size) {
        super(size, size);
        super.initialiseDefaultValues(size);
    }
    
    
    @Override
    public boolean isPartialSolution(Cell c) {
        if (IsDuplicated.onRow(getGrid(), c) || IsDuplicated.onColumn(getGrid(), c)) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public Skyscrapers deepCopy() {
        return null;
    }
}
