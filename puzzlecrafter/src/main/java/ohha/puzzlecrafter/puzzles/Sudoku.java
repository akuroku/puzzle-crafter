
package ohha.puzzlecrafter.puzzles;

import ohha.puzzlecrafter.auxiliary.IsDuplicated;
import ohha.puzzlecrafter.grid.Partition;
import ohha.puzzlecrafter.grid.CellCoordinate;

import java.util.LinkedList;


/**
 * Implements the
 * <a href ="https://tspuzzles.wordpress.com/rules/sudoku/">Sudoku</a> puzzle
 * style.
 * Solver performance is satisfactory at least on 6 by 6 sudokus.
 */
public class Sudoku extends Puzzle {
    
    /**
     * Constructs a new empty Sudoku of the given height and width, with the
     * given partition.
     * The {@link ohha.puzzlecrafter.grid.Partition} supplied must be a valid
     * partition, but this is not enforced by this constructor. See the
     * Partition class for methods for enforcing it.
     * 
     * @param size      the height and width of the grid in cells
     * @param partition the partition to use
     */
    public Sudoku(int size, Partition partition) {
        super(size, size);
        super.initialiseDefaultValues(size);
        setPartition(partition);
        name = "Sudoku";
    }
    
    /**
     * Constructs a new empty Sudoku of the given height and width, with the 
     * partition consisting of equal-sized rectangular regions of the given
     * height and width.
     * The rectangular {@link ohha.puzzlecrafter.grid.Region} parameters must
     * define a valid partition, but this is not enforced by this constructor.
     * 
     * @param size          the height and width of the grid in cells
     * @param regionHeight  the height of a region in cells
     * @param regionWidth   the width of a region in cells
     */
    public Sudoku(int size, int regionHeight, int regionWidth) {
        this(size, new Partition(regionHeight, regionWidth, size / regionHeight, size / regionWidth));
    }
    
    
    /*
    @Override
    public CellCoordinate getNextCell(CellCoordinate c) {
        // override with a better heuristic if you can
    }
    */
    
    
    @Override
    public boolean isPartialSolution(CellCoordinate cell) {
        if (IsDuplicated.onRow(getGrid(), cell)) {
            return false;
        }
        if (IsDuplicated.onColumn(getGrid(), cell)) {
            return false;
        }
        if (IsDuplicated.onRegion(getGrid(), getPartition(), cell)) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public Sudoku deepCopy() {
        Sudoku copy = new Sudoku(getGrid().getHeight(), getPartition().deepCopy());
        copy.setGrid(getGrid().deepCopy());
        // clues not used
        copy.setValues(new LinkedList<>(getValues()));
        copy.setGivens(new LinkedList<>(getGivens()));
        
        return copy;
    }
}