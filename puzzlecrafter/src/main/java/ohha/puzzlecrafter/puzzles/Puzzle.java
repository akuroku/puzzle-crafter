
package ohha.puzzlecrafter.puzzles;

import ohha.puzzlecrafter.grid.Cell;
import ohha.puzzlecrafter.grid.Grid;
import java.util.List;
import java.util.LinkedList;


public abstract class Puzzle {
    
    private Grid grid;
    private List<Integer> values;
    private List<Cell> givens;
    
    
    public Puzzle(int height, int width) {
        grid = new Grid(height, width);
        values = new LinkedList<>();
        givens = new LinkedList<>();
    }
    
    
    public void setGrid(Grid grid) {
        this.grid = grid;
    }
    public Grid getGrid() {
        return grid;
    }
    
    
    // values represent whatever the cells may be filled with, must be -1 or positive
    // 0 always means cell with undetermined contents, -1 always means empty cell
    public void setValues(List<Integer> values) {
        this.values = values;
    }
    // gives all possible values that may be placed in cells
    public List<Integer> getValues() {
        return values;
    }
    // gives a list of possible values that may be placed in the given cell
    // it may be overridden to weed out obviously unfit values, but it must always return actual potential values
    public List<Integer> getCandidates(Cell cell) {
        return getValues();
    }
    public void initialiseDefaultValues(int amount) {
        for (int i = 1; i <= amount; i++) {
            values.add(i);
        }
    }
    
    
    public void setCell(Cell cell, int value) {
        grid.setValueAt(cell, value);
    }
    
    
    // a given is a pre-filled in value that is set in the puzzle, not to be changed by the solver
    public void setGiven(Cell cell, int value) {
        setCell(cell, value);
        givens.add(cell);
    }
    // returns the locations of givens
    public List<Cell> getGivens() {
        return givens;
    }
    
    
    // returns the next undetermined cell for the solver to try, or null if there is none
    // it may be overridden to implement a better order for the solver to try to fill the puzzle in
    public Cell getNextCell(Cell cell) {
        Cell next = cell.switchX(cell.getX() + 1);
        
        if (next.getX() >= grid.getWidth()) {
            next = new Cell(0, cell.getY() + 1);
            
            if (next.getY() >= grid.getHeight()) {
                return null;
            }
        }
        
        if (getGivens().contains(next)) {
            return getNextCell(next);
        }
        
        return next;
    }
    
    
    // returns true if and only if current state is a partial solution
    // usually tests need only be done depending on the cell that changed
    public abstract boolean isPartialSolution(Cell cell);
    
    
    public abstract Puzzle deepCopy();
    
    
    @Override
    public String toString() {
        String s = "";
        
        for (int y = 0; y < getGrid().getHeight(); y++) {
            for (int x = 0; x < getGrid().getWidth(); x++) {
                s += getGrid().getValueAt(new Cell(x, y)) + " ";
            }
            s += "\n";
        }
        
        return s;
    }
}