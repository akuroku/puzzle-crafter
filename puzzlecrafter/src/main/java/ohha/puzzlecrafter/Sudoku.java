
package ohha.puzzlecrafter;

import ohha.puzzlecrafter.grid.PartitionGrid;
import ohha.puzzlecrafter.grid.Partition;
import java.util.List;
import java.util.LinkedList;

public class Sudoku implements Puzzle {
    
    private PartitionGrid grid; // grid of values
    private List<Integer> values; // values that a cell in the grid may take
    
    
    // initialise sudoku with given regions
    public Sudoku(int size, Partition partition) {
        this.grid = new PartitionGrid(size, size, partition);
        this.values = new LinkedList<>();
        for (int i = 1; i <= size; i++) {
            values.add(i);
        }
    }
    
    // initialise sudoku with rectangular regions with dimensions height and width
    public Sudoku(int size, int height, int width) {
        this.grid = new PartitionGrid(size, size, height, width);
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
    public boolean isPartialSolution(Cell c) {
        return !(grid.isDuplicatedOnRow(c) || grid.isDuplicatedOnColumn(c) || grid.isDuplicatedOnRegion(c));
    }
}
