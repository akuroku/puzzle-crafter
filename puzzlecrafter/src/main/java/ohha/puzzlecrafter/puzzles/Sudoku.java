
package ohha.puzzlecrafter.puzzles;

import ohha.puzzlecrafter.auxiliary.IsDuplicated;
import ohha.puzzlecrafter.grid.Partition;
import ohha.puzzlecrafter.grid.Cell;

import java.util.LinkedList;


public class Sudoku extends Puzzle {
    
    private Partition partition;
    
    
    // initialise sudoku with given regions
    public Sudoku(int size, Partition partition) {
        super(size, size);
        super.initialiseDefaultValues(size);
        this.partition = partition;
    }
    
    // initialise sudoku with rectangular regions with dimensions height and width
    public Sudoku(int size, int regionHeight, int regionWidth) {
        this(size, new Partition(regionHeight, regionWidth, size / regionHeight, size / regionWidth));
    }
    
    
    /*
    @Override
    public Cell getNextCell(Cell c) {
        // override with a better heuristic if you can
    }
    */
    
    
    @Override
    public boolean isPartialSolution(Cell cell) {
        if (IsDuplicated.onRow(getGrid(), cell)) {
            return false;
        }
        if (IsDuplicated.onColumn(getGrid(), cell)) {
            return false;
        }
        if (IsDuplicated.onRegion(getGrid(), partition, cell)) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public Sudoku deepCopy() {
        Sudoku copy = new Sudoku(getGrid().getHeight(), partition.deepCopy());
        copy.setValues(new LinkedList<>(getValues()));
        copy.setGrid(getGrid().deepCopy());
        return copy;
    }
}