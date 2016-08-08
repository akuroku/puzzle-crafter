
package ohha.puzzlecrafter;

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
        super(size, size);
        super.initialiseDefaultValues(size);
        partition = new Partition(regionHeight, regionWidth, size / regionHeight, size / regionWidth);
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
        Sudoku sudoku = new Sudoku(getGrid().getHeight(), partition.deepCopy());
        sudoku.setValues(new LinkedList<>(getValues()));
        sudoku.setGrid(getGrid().deepCopy());
        return sudoku;
    }
}
