
package ohha.puzzlecrafter.auxiliary;

import ohha.puzzlecrafter.grid.Cell;
import ohha.puzzlecrafter.grid.Grid;

import java.util.List;
import java.util.LinkedList;


public final class Neighbours {
    public static List<Cell> cardinals(Grid g, Cell c) {
        List<Cell> neighbours = new LinkedList<>();
        
        if (c.getX() > 0) {
            neighbours.add(c.shiftX(-1));
        }
        if (c.getY() > 0) { 
            neighbours.add(c.shiftY(-1));
        }
        if (c.getX() < g.getWidth() - 1) {
            neighbours.add(c.shiftX(1));
        }
        if (c.getY() < g.getHeight() - 1) {
            neighbours.add(c.shiftY(1));
        }
        return neighbours;
    }
    
    
    public static List<Cell> diagonals(Grid g, Cell c) {
        List<Cell> neighbours = new LinkedList<>();
        
        if (c.getX() > 0) {
            if (c.getY() > 0) {
                neighbours.add(c.shiftX(-1).shiftY(-1));
            }
            if (c.getY() < g.getHeight() - 1) {
                neighbours.add(c.shiftX(-1).shiftY(1));
            }
        }
        if (c.getX() < g.getWidth() - 1) {
            if (c.getY() > 0) {
                neighbours.add(c.shiftX(1).shiftY(-1));
            }
            if (c.getY() < g.getHeight() - 1) {
                neighbours.add(c.shiftX(1).shiftY(1));
            }
        }
        
        return neighbours;
    }
    
    
    public static List<Cell> surrounding(Grid g, Cell c) {
        List<Cell> neighbours = new LinkedList<>();
        
        if (c.getX() > 0) {
            if (c.getY() > 0) {
                neighbours.add(c.shiftX(-1).shiftY(-1));
            }
            if (c.getY() < g.getHeight() - 1) {
                neighbours.add(c.shiftX(-1).shiftY(1));
            }
            neighbours.add(c.shiftX(-1));
        }
        if (c.getX() < g.getWidth() - 1) {
            if (c.getY() > 0) {
                neighbours.add(c.shiftX(1).shiftY(-1));
            }
            if (c.getY() < g.getHeight() - 1) {
                neighbours.add(c.shiftX(1).shiftY(1));
            }
            neighbours.add(c.shiftX(1));
        }
        if (c.getY() > 0) {
            neighbours.add(c.shiftY(-1));
        }
        if (c.getY() < g.getHeight() - 1) {
            neighbours.add(c.shiftY(1));
        }
        
        return neighbours;
    }
    
}
