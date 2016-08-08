
package ohha.puzzlecrafter;

import ohha.puzzlecrafter.grid.Cell;

import java.util.List;
import java.util.LinkedList;

public class Solver {
    
    private Puzzle puzzle;
    private List<Puzzle> solutions;
    
    
    public Solver(Puzzle puzzle) {
        this.puzzle = puzzle;
    }
    
    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }
    
    
    public void solvePuzzle() {
        solutions = new LinkedList<>();
        
        iterate(new Cell(0, 0));
        
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
    
    public void iterate(Cell cell) {
        if (cell == null) {
            // the puzzle has been successfully filled
            solutions.add(puzzle.deepCopy());
            return;
        }
        
        for (int value : puzzle.getValues()) {
            puzzle.setCell(cell, value);
            
            if (!puzzle.isPartialSolution(cell)) {
                continue;
            }
            iterate(puzzle.getNextCell(cell));
        }
        puzzle.setCell(cell, 0);
    }
}
