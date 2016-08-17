
package ohha.puzzlecrafter;

import javax.swing.SwingUtilities;
import ohha.puzzlecrafter.puzzles.Fillomino;
import ohha.puzzlecrafter.grid.Cell;
import ohha.puzzlecrafter.ui.mainwindow.MainWindow;


public class Main {

    public static void main(String [] args) {
        // Sudoku puzzle = new Sudoku(6, 2, 3);
        Fillomino puzzle = new Fillomino(6, 6);
        
        
        /*
        puzzle.setGiven(new Cell(2, 0), 5);
        puzzle.setGiven(new Cell(4, 0), 6);
        
        puzzle.setGiven(new Cell(3, 1), 4);
        
        puzzle.setGiven(new Cell(0, 2), 4);
        puzzle.setGiven(new Cell(4, 2), 2);
        
        puzzle.setGiven(new Cell(1, 3), 6);
        puzzle.setGiven(new Cell(5, 3), 5);
        
        puzzle.setGiven(new Cell(2, 4), 3);
        
        puzzle.setGiven(new Cell(1, 5), 1);
        puzzle.setGiven(new Cell(3, 5), 6);
        /*
        _ _ 5 | _ 6 _
        _ _ _ | 4 _ _
        ------+------
        4 _ _ | _ 2 _
        _ 6 _ | _ _ 5
        ------+------
        _ _ 3 | _ _ _
        _ 1 _ | 6 _ _
        */
        
        
        puzzle.setGiven(new Cell(1, 0), 1); // unnecessary for sudoku
        puzzle.setGiven(new Cell(2, 0), 6);
        puzzle.setGiven(new Cell(1, 1), 3);
        
        puzzle.setGiven(new Cell(4, 1), 2);
        puzzle.setGiven(new Cell(5, 1), 6);
        puzzle.setGiven(new Cell(5, 2), 5);
        
        puzzle.setGiven(new Cell(0, 3), 6);
        puzzle.setGiven(new Cell(0, 4), 4);
        puzzle.setGiven(new Cell(1, 4), 2);
        
        puzzle.setGiven(new Cell(4, 4), 3);
        puzzle.setGiven(new Cell(3, 5), 2);
        puzzle.setGiven(new Cell(4, 5), 5); // unnecessary for sudoku
        /*
        _(1)6 | _ _ _
        _ 3 _ | _ 2 6
        ------+------
        _ _ _ | _ _ 5
        6 _ _ | _ _ _
        ------+------
        4 2 _ | _ 3 _
        _ _ _ | 2(5) _
        */
        
        // both puzzles solve as both sudokus and fillominos
        
        System.out.println("Puzzle:");
        System.out.println(puzzle);
        
        System.out.println("Values: ");
        for (Integer v : puzzle.getValues()) {
            System.out.print(v + ", ");
        }
        System.out.println("\n");
        
        
        Solver solver = new Solver(puzzle);
        // solver.solvePuzzle();
        
        SwingUtilities.invokeLater(new MainWindow());
    }
}
