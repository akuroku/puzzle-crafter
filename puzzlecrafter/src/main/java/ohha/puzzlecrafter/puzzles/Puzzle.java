/* 
 * Checkstyle valittaa luokan koosta, mutta suurin osa luokasta on JavaDoc-
 * dokumentaatiota, joten mielestäni valitus on aiheeton.
 */

package ohha.puzzlecrafter.puzzles;

import ohha.puzzlecrafter.grid.Coordinate;
import ohha.puzzlecrafter.grid.Grid;

import java.util.List;
import java.util.LinkedList;


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
    
    private Grid grid;
    private List<Integer> values;
    private List<Coordinate> givens;
    
    
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
     * @return 
     */
    public List<Integer> getCandidates(Coordinate c) {
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
     * 
     * @param c     the coordinate of the cell to fill
     * @param value the value to fill in
     */
    public void setCell(Coordinate c, int value) {
        grid.setValueAt(c, value);
    }
    
    
    /**
     * Sets the given value into the cell at the given coordinate as pre-filled
     * information to the solver, like the already filled-in numbers in Sudoku.
     * This is the intended method for the puzzle writer to set pre-filled
     * values with. Compare to {@link #setCell}, which is the method the solver
     * will use to fill the grid with.
     * 
     * @param c     the coordinate of the cell to set as a given
     * @param value the value to set in
     */
    public void setGiven(Coordinate c, int value) {
        setCell(c, value);
        givens.add(c);
    }
    
    /**
     * Returns the coordinates of givens (pre-filled values) in the grid.
     * This enables the solver not to overwrite these cells.
     * 
     * @return  a list of coordinates of cells with givens
     */
    public List<Coordinate> getGivens() {
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
     * The passed parameter {@param c} is the coordinate of the cell where the
     * solver last filled a value.
     * <p>
     * It is important that any implementation makes sure all the cells of the
     * grid are covered.
     * 
     * @param c  the cell last modified by the solver
     * @return 
     */
    public Coordinate getNextCell(Coordinate c) {
        Coordinate next = c.shiftX(1);
        
        if (next.getX() >= grid.getWidth()) {
            next = new Coordinate(0, c.getY() + 1);
            
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
     * The passed parameter {@param c} is the coordinate of the cell where the
     * solver last filled a value, as it is most often sufficient to check that
     * the rules of the puzzle still hold only around the cell that changed.
     * 
     * @param c the cell last modified by the solver
     * @return  true if the puzzle may be completed into a valid solution, false
     * otherwise
     */
    public abstract boolean isPartialSolution(Coordinate c);
    
    
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
                s += getGrid().getValueAt(new Coordinate(x, y)) + " ";
            }
            s += "\n";
        }
        
        return s;
    }
}