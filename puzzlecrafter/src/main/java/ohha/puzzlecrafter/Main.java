
package ohha.puzzlecrafter;

import ohha.puzzlecrafter.grid.Cell;

public class Main {

    public static void main(String [] args) {
        Sudoku sudoku = new Sudoku(6, 2, 3);
        /*
        sudoku.setGiven(new Cell(2, 0), 5);
        sudoku.setGiven(new Cell(4, 0), 6);
        
        sudoku.setGiven(new Cell(3, 1), 4);
        
        sudoku.setGiven(new Cell(0, 2), 4);
        sudoku.setGiven(new Cell(4, 2), 2);
        
        sudoku.setGiven(new Cell(1, 3), 6);
        sudoku.setGiven(new Cell(5, 3), 5);
        
        sudoku.setGiven(new Cell(2, 4), 3);
        
        sudoku.setGiven(new Cell(1, 5), 1);
        sudoku.setGiven(new Cell(3, 5), 6);
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
        
        // sudoku.setGiven(new Cell(1, 0), 1);
        sudoku.setGiven(new Cell(2, 0), 6);
        sudoku.setGiven(new Cell(1, 1), 3);
        
        sudoku.setGiven(new Cell(4, 1), 2);
        sudoku.setGiven(new Cell(5, 1), 6);
        sudoku.setGiven(new Cell(5, 2), 5);
        
        sudoku.setGiven(new Cell(0, 3), 6);
        sudoku.setGiven(new Cell(0, 4), 4);
        sudoku.setGiven(new Cell(1, 4), 2);
        
        // sudoku.setGiven(new Cell(4, 4), 3);
        sudoku.setGiven(new Cell(3, 5), 2);
        sudoku.setGiven(new Cell(4, 5), 5);
        /*
        _ _ 6 | _ _ _
        _ 3 _ | _ 2 6
        ------+------
        _ _ _ | _ _ 5
        6 _ _ | _ _ _
        ------+------
        4 2 _ | _ 3 _
        _ _ _ | 2 _ _
        */
        
        
        System.out.println("Puzzle:");
        System.out.println(sudoku);
        
        System.out.println("Values: ");
        for (Integer v : sudoku.getValues()) {
            System.out.print(v + ", ");
        }
        System.out.println("\n");
        
        
        Solver solver = new Solver(sudoku);
        solver.solvePuzzle();
    }
}
