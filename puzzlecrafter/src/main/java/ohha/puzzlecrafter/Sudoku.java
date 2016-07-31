
package ohha.puzzlecrafter;

import java.util.List;
import java.util.LinkedList;

public class Sudoku implements Puzzle {
    
    private RegionGrid grid; // grid of values
    private List<Integer> values; // values that a cell in the grid may take
    
    
    // initialise sudoku with given regions
    public Sudoku(int size, List<List<Integer[]>> regions) {
        this.grid = new RegionGrid(size, size, regions);
        this.values = new LinkedList<>();
        for (int i = 1; i <= size; i++) {
            values.add(i);
        }
    }
    
    // initialise sudoku with rectangular regions with dimensions height and width
    public Sudoku(int size, int height, int width) {
        this.grid = new RegionGrid(size, size, height, width);
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
        return !(grid.duplicatedOnRow(x, y) || grid.duplicatedOnColumn(x, y) || grid.duplicatedOnRegion(x, y));
    }
}
