/* 
 * Checkstyle valittaa luokan koosta, mutta suurin osa luokasta on JavaDoc-
 * dokumentaatiota, joten mielestäni valitus on aiheeton.
 */

package ohha.puzzlecrafter.puzzles;

import ohha.puzzlecrafter.grid.CellCoordinate;
import ohha.puzzlecrafter.grid.Grid;

import java.util.List;
import java.util.LinkedList;
import ohha.puzzlecrafter.Clue;
import ohha.puzzlecrafter.grid.Partition;


/**
 * Represents a general puzzle to be extended by concrete puzzle styles.
 * It is an abstract class instead of an interface so that it can host the
 * fields common to all puzzles, and offer default implementations where
 * possible.
 */
public abstract class Puzzle {
    
    public static final String[] STYLES = new String[] {
        "Fillomino", "Sudoku"
    };
    
    protected String name;
    
    private Grid grid;
    private Partition partition;
    
    private List<Clue> clues;
    
    private List<Integer> values;
    private List<CellCoordinate> givens;
    
    
    /**
     * Constructs a new puzzle whose grid is of the given height and width.
     * 
     * @param height    the height of the grid in cells
     * @param width     the width of the grid in cells
     */
    public Puzzle(int height, int width) {
        grid = new Grid(height, width);
        clues = new LinkedList<>();
        values = new LinkedList<>();
        givens = new LinkedList<>();

    }
    
    
    public String getName() {
        return name;
    }
    
    
    public void setGrid(Grid grid) {
        this.grid = grid;
    }
    public Grid getGrid() {
        return grid;
    }
    
    public void setPartition(Partition partition) {
        this.partition = partition;
    }
    public Partition getPartition() {
        return this.partition;
    }
    
    public void setClues(List<Clue> clues) {
        this.clues = clues;
    }
    public List<Clue> getClues() {
        return clues;
    }
    
    /**
     * Sets the full selection of possible values the puzzle's cells may take in
     * the solved puzzle.
     * All values but {@Link ohha.puzzlecrafter.grid.Grid#CELL_UNDETERMINED} are
     * permitted here. This restriction isn't enforced here, however.
     * <p>
     * Compare to {@link #getCandidates(Coordinate)}, whose purpose is to supply a possibly filtered
     * selection of possible values for a certain cell.
     * 
     * @param values    the list of values the cells of the puzzle may take
     */
    public void setValues(List<Integer> values) {
        this.values = values;
    }
    
    /**
     * Returns the full selection of possible values the puzzle's cells may take
     * in the solved puzzle.
     * 
     * @return  the full list of values the puzzle's cells may take
     */
    public List<Integer> getValues() {
        return values;
    }
    
    /**
     * Returns a possibly pruned selection of values the cell at the given
     * coordinate may take, considering the puzzle's state.
     * This method is provided so that concrete puzzle implementations have a
     * way of weeding out clearly unsuitable values by overriding it. No pruning
     * is required, but the implementations mustn't filter out genuine
     * candidates.
     * <p>
     * Compare to {@link #getValues()}, whose purpose is to supply the full
     * selection of values that are permitted in the puzzle.
     * 
     * @param c the coordinate of the cell whose possible values are requested
     * @return  a list of feasible values that may go into the given cell
     */
    public List<Integer> getCandidates(CellCoordinate c) {
        return getValues();
    }
    
    /**
     * Sets as values the integers from 1 to the given integer.
     * 
     * @param amount    the upper limit of integers to include, from 1 up to
     * this
     */
    public void initialiseDefaultValues(int amount) {
        for (int i = 1; i <= amount; i++) {
            values.add(i);
        }
    }
    
    
    /**
     * Fills the given value into the cell at the given coordinate.
     * This is the intended method for the solver to fill the grid with. Compare
     * to {@link #setGiven(Coordinate, int)}, whose purpose is to insert pre-
     * filled values as hints to the solver.
     * <p>
     * Givens may not hold the value
     * {@Link ohha.puzzlecrafter.grid.Grid#CELL_UNDETERMINED}. If this method is
     * asked to set that value, it won't.
     * 
     * @param c     the coordinate of the cell to fill
     * @param value the value to fill in
     */
    public void setCell(CellCoordinate c, int value) {
        if (value == Grid.UNDETERMINED_CELL) {
            return;
        }
        grid.setValueOfCellAt(c, value);
    }
    
    
    /**
     * Cycles the value in the cell at the given coordinate by the given amount.
     * This method isn't intended to be used by the automated solver, as it also
     * assigns to cells the value
     * {@Link ohha.puzzlecrafter.grid.Grid#CELL_UNDETERMINED}.
     * 
     * @param c         the coordinate of the cell whose value to cycle
     * @param amount    the amount of steps to cycle
     */
    public void cycleValueOfCell(CellCoordinate c, int amount) {
        int value = grid.getValueOfCellAt(c);
        
        int oldIndex = getValues().indexOf(value);
        int modulus = getValues().size() + 1;
        int newIndex = ((oldIndex + amount) % modulus + modulus) % modulus;
        
        if (newIndex == getValues().size()) {
            setCellUndetermined(c);
        } else {
            setCell(c, getValues().get(newIndex));
        }
    }
    
    /**
     * Sets a cell's value to
     * {@Link ohha.puzzlecrafter.grid.Grid#CELL_UNDETERMINED}.
     * This method is intended to be used when the solver resets a cell's
     * contents, instead of {@Link #setCell(Coordinate, int)} and
     * {@Link #setGiven{Coordinate, int)}, which will fail if trying to assign
     * an undecided.
     * 
     * @param c the cell whose value to reset.
     */
    public void setCellUndetermined(CellCoordinate c) {
        givens.remove(c);
        grid.setValueOfCellAt(c, Grid.UNDETERMINED_CELL);
    }
    
    
    /**
     * Sets the given value into the cell at the given coordinate as pre-filled
     * information to the solver, like the already filled-in numbers in Sudoku.
     * This is the intended method for the puzzle writer to set pre-filled
     * values with. Compare to {@link #setCell}, which is the method the solver
     * will use to fill the grid with.
     * <p>
     * Givens may not hold the value
     * {@Link ohha.puzzlecrafter.grid.Grid#CELL_UNDETERMINED}, and if this
     * method is used to set a given cell undetermined, nothing is done.
     * 
     * @param c     the coordinate of the cell to set as a given
     * @param value the value to set in
     */
    public void setGiven(CellCoordinate c, int value) {
        if (value == Grid.UNDETERMINED_CELL) {
            return;
        }
        givens.add(c);
        setCell(c, value);
    }
    
    /**
     * Sets the cell indicated by the coordinate as a given, unless it is
     * undetermined.
     * This method only changes the 'given' status of a cell, but not its
     * contents. For the latter, see {@link #setGiven}.
     * 
     * @param c the coordinate of the cell to set as a given 
     */
    public void setAsGiven(CellCoordinate c) {
        if (grid.isCellUndetermined(c)) {
            return;
        }
        givens.add(c);
    }
    
    /**
     * Removes the cell's status as a given.
     * 
     * @param c the coordinate of the cell whose status to change
     */
    public void setAsNotGiven(CellCoordinate c) {
        givens.remove(c);
    }
    
    /**
     * Returns whether the cell indicated by the coordinate is a given or not.
     * 
     * @param c the coordinate to test
     * @return  true if the cell is a given, false otherwise
     */
    public boolean isGiven(CellCoordinate c) {
        return givens.contains(c);
    }
    
    /**
     * Sets the given list of coordinates as givens for the puzzle.
     * This method is only intended for the use of {@Link #deepCopy()}, and
     * does not check whether the cells are undetermined or not.
     * 
     * @param givens    the list of coordinates to set as givens
     */
    public void setGivens(List<CellCoordinate> givens) {
        this.givens = givens;
    }
    
    /**
     * Returns the coordinates of givens (pre-filled values) in the grid.
     * This enables the solver not to overwrite these cells. Givens must not be
     * the value {ohha.puzzlegrafter.grid.Grid#UNDETERMINED_CELL}.
     * 
     * @return  a list of coordinates of cells with givens
     */
    public List<CellCoordinate> getGivens() {
        return givens;
    }
    
    
    
    
    /**
     * Returns the next undetermined cell for the solver to try, or null if
     * there is none.
     * The default implementation simply moves row by row from top to bottom.
     * Concrete puzzle implementations may override this method to provide a
     * more efficient sequence of cells that results in detecting futile efforts
     * earlier and thus triggering backtracking earlier.
     * <p>
     * The passed parameter is the coordinate of the cell where the
     * solver last filled a value.
     * <p>
     * It is important that any implementation makes sure all the cells of the
     * grid are covered.
     * 
     * @param c the cell last modified by the solver
     * @return  the next cell intended for the solver to modify
     */
    public CellCoordinate getNextCell(CellCoordinate c) {
        CellCoordinate next = c.shiftX(1);
        
        if (next.getX() >= grid.getWidth()) {
            next = new CellCoordinate(0, c.getY() + 1);
            
            if (next.getY() >= grid.getHeight()) {
                return null;
            }
        }
        
        if (getGivens().contains(next)) {
            return getNextCell(next);
        }
        
        return next;
    }
    
    
    /**
     * Returns true if the current state of the puzzle is possible to be
     * completed into a full solution, that is the state of the puzzle is not
     * in contradiction with the puzzle's rules.
     * This method must be implemented case by case in each puzzle.
     * <p>
     * The passed parameter is the coordinate of the cell where the
     * solver last filled a value, as it is most often sufficient to check that
     * the rules of the puzzle still hold only around the cell that changed.
     * 
     * @param c the cell last modified by the solver
     * @return  true if the puzzle may be completed into a valid solution, false
     * otherwise
     */
    public abstract boolean isPartialSolution(CellCoordinate c);
    
    
    /**
     * Returns a deep copy of the puzzle.
     * This method allows the solving algorithm to copy the solved puzzle into
     * a list of solutions, and then further mutate the puzzle object it is
     * solving.
     * <p>
     * It must be implemented by each concrete puzzle implementation so that it
     * captures all the additional structures each puzzle might have, and that
     * the puzzle object has the correct subclass. Regrettably this seems to
     * result in a lot of copied code.
     * 
     * @return  a deep copy of the puzzle
     */
    public abstract Puzzle deepCopy();
    
    
    @Override
    public String toString() {
        String s = "";
        
        for (int y = 0; y < getGrid().getHeight(); y++) {
            for (int x = 0; x < getGrid().getWidth(); x++) {
                s += getGrid().getValueOfCellAt(new CellCoordinate(x, y)) + " ";
            }
            s += "\n";
        }
        
        return s;
    }
}