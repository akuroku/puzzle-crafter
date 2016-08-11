
package ohha.puzzlecrafter;

import ohha.puzzlecrafter.puzzles.Puzzle;
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
        
        System.out.println("Starting!");
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
            System.out.println("A solution has been found!");
            System.out.println(puzzle);
            solutions.add(puzzle.deepCopy());
            System.out.print("");
            return;
        }
        
        for (int value : puzzle.getCandidates(cell)) {
            puzzle.setCell(cell, value);
            System.out.print("Trying " + value + " at " + cell);
            
            if (!puzzle.isPartialSolution(cell)) {
                System.out.println(", contradiction");
                continue;
            } else {
                System.out.println("");
            }
            iterate(puzzle.getNextCell(cell));
        }
        puzzle.setCell(cell, 0);
    }
}
