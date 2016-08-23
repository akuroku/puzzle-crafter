
package ohha.puzzlecrafter.puzzles;

import ohha.puzzlecrafter.auxiliary.IsDuplicated;
import ohha.puzzlecrafter.grid.Partition;
import ohha.puzzlecrafter.grid.Coordinate;

import java.util.LinkedList;


/**
 * Implements the
 * <a href ="https://tspuzzles.wordpress.com/rules/sudoku/">Sudoku</a> puzzle
 * style.
 * Solver performance is satisfactory at least on 6 by 6 sudokus.
 */
public class Sudoku extends Puzzle {
    
    private Partition partition;
    
    
    // initialise sudoku with given regions
    public Sudoku(int size, Partition partition) {
        super(size, size);
        super.initialiseDefaultValues(size);
        this.partition = partition;
        name = "Sudoku";
    }
    
    // initialise sudoku with rectangular regions with dimensions height and width
    public Sudoku(int size, int regionHeight, int regionWidth) {
        this(size, new Partition(regionHeight, regionWidth, size / regionHeight, size / regionWidth));
    }
    
    
    public Partition getPartition() {
        return partition;
    }
    
    
    /*
    @Override
    public Coordinate getNextCell(Coordinate c) {
        // override with a better heuristic if you can
    }
    */
    
    
    @Override
    public boolean isPartialSolution(Coordinate cell) {
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