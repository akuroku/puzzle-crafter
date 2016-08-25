
package ohha.puzzlecrafter;

import ohha.puzzlecrafter.puzzles.Puzzle;
import ohha.puzzlecrafter.grid.CellCoordinate;

import java.util.List;
import java.util.LinkedList;


/**
 * Solves the given puzzle and retains all found solutions.
 * The algorithm used is a simple backtracking algorithm: on each cell the
 * solver systematically tries placing each possible value one by one,
 * backtracking once it knows it made a mistake and the puzzle can't be solved.
 * <p>
 * To stay versatile, the <code>Solver</code> delegates validity checks,
 * possible value pruning, etc. to its puzzle.
 * <p>
 * Future refactoring may allow for multiple implementations of a solver to
 * allow custom solvers tailored to fit specific puzzle styles.
 */
public class Solver {
    
    private Puzzle puzzle;
    private List<Puzzle> solutions;
    
    
    /**
     * Constructs a new solver for solving the given puzzle.
     * 
     * @param puzzle    the puzzle to be solved
     */
    public Solver(Puzzle puzzle) {
        this.puzzle = puzzle;
    }
    
    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }
    
    
    /**
     * Searches for all the solutions of the puzzle and stores them in the list
     * solutions.
     * The printing commands are for debugging purposes and will be removed.
     */
    public void solvePuzzle() {
        solutions = new LinkedList<>();
        
        System.out.println("Starting!");
        iterate(new CellCoordinate(0, 0));
        
        if (solutions.isEmpty()) {
            System.out.println("No solutions found");
            return;
        }
        
        System.out.println(solutions.size() + " solutions found:");
        for (Puzzle p : solutions) {
            System.out.print(p);
            System.out.println("------");
        }
    }
    
    
    /**
     * Forms the recursive backbone of the solution search algorithm.
     * When called on a cell, it tries placing all possible candidate values on
     * the cell, calling tiself with the next cell in sequence in each case.
     * If there is no next cell, the whole grid has been filled in a valid
     * fashion, meaning a solution has been found.
     * <p>
     * Once all possible values have been tried and an iterate finds itself on
     * top of the call stack again, it finally sets its cell undetermined again
     * and returns to give way for the previous iterate on the callstack.
     * <p>
     * The <code>{@Link Puzzle}</code> class provides the methods for:
     * <ul>
     * <li>supplying the possible values to try
     * <li>testing whether the puzzle is not in contradiction, and
     * <li>supplying the next cell to test.
     * </ul>
     * <p>
     * The printing methods are for debugging purposes and will be removed.
     * 
     * @param c  coordinates of the cell to try placing values in
     */
    private void iterate(CellCoordinate c) {
        if (c == null) {
            // the puzzle has been successfully filled
            solutions.add(puzzle.deepCopy());
            return;
        }
        
        // try all candidates on current cell
        for (int value : puzzle.getCandidates(c)) {
            puzzle.setCell(c, value);
            
            if (!puzzle.isPartialSolution(c)) {
                continue;
            }
            iterate(puzzle.getNextCell(c));
        }
        
        // set cell back to undetermined
        puzzle.setCellUndetermined(c);
    }
}
