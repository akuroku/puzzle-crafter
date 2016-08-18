
package ohha.puzzlecrafter.auxiliary;

import ohha.puzzlecrafter.grid.Coordinate;
import ohha.puzzlecrafter.grid.Grid;

import java.util.List;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class NeighboursTest {
    
    private Grid grid;
    private Coordinate topleft;
    private Coordinate top;
    private Coordinate topright;
    private Coordinate left;
    private Coordinate center;
    private Coordinate right;
    private Coordinate bottomleft;
    private Coordinate bottom;
    private Coordinate bottomright;
    
    @Before
    public void setUp() {
        grid = new Grid(3, 3);
        topleft = new Coordinate(0, 0);
        top = new Coordinate(1, 0);
        topright = new Coordinate(2, 0);
        left = new Coordinate(0, 1);
        center = new Coordinate(1, 1);
        right = new Coordinate(2, 1);
        bottomleft = new Coordinate(0, 2);
        bottom = new Coordinate(1, 2);
        bottomright = new Coordinate(2, 2);
    }
    
    
    @Test
    public void cardinalsReturnsCorrectAmountOfCells() {
        assertEquals(2, Neighbours.cardinals(grid, topleft).size());
        assertEquals(3, Neighbours.cardinals(grid, top).size());
        assertEquals(2, Neighbours.cardinals(grid, topright).size());
        assertEquals(3, Neighbours.cardinals(grid, left).size());
        assertEquals(4, Neighbours.cardinals(grid, center).size());
        assertEquals(3, Neighbours.cardinals(grid, right).size());
        assertEquals(2, Neighbours.cardinals(grid, bottomleft).size());
        assertEquals(3, Neighbours.cardinals(grid, bottom).size());
        assertEquals(2, Neighbours.cardinals(grid, bottomright).size());
    }
    
    @Test
    public void cardinalsReturnsCorrectCellsInCenter() {
        List<Coordinate> neighbours = new LinkedList<>();
        neighbours.add(new Coordinate(1, 0));
        neighbours.add(new Coordinate(1, 2));
        neighbours.add(new Coordinate(0, 1));
        neighbours.add(new Coordinate(2, 1));
        
        for (Coordinate cell : neighbours) {
            assertTrue(Neighbours.cardinals(grid, center).contains(cell));
        }
    }
    @Test
    public void cardinalsReturnsCorrectCellsOnEdge() {
        List<Coordinate> neighbours = new LinkedList<>();
        neighbours.add(new Coordinate(1, 1));
        neighbours.add(new Coordinate(0, 2));
        neighbours.add(new Coordinate(2, 2));
        
        for (Coordinate cell : neighbours) {
            assertTrue(Neighbours.cardinals(grid, bottom).contains(cell));
        }
    }
    @Test
    public void cardinalsReturnsCorrectCellsInCorner() {
        List<Coordinate> neighbours = new LinkedList<>();
        neighbours.add(new Coordinate(0, 1));
        neighbours.add(new Coordinate(1, 2));
        
        for (Coordinate cell : neighbours) {
            assertTrue(Neighbours.cardinals(grid, bottomleft).contains(cell));
        }
    }
    
    
    @Test
    public void diagonalsReturnsCorrectAmountOfCells() {
        assertEquals(1, Neighbours.diagonals(grid, topleft).size());
        assertEquals(2, Neighbours.diagonals(grid, top).size());
        assertEquals(1, Neighbours.diagonals(grid, topright).size());
        assertEquals(2, Neighbours.diagonals(grid, left).size());
        assertEquals(4, Neighbours.diagonals(grid, center).size());
        assertEquals(2, Neighbours.diagonals(grid, right).size());
        assertEquals(1, Neighbours.diagonals(grid, bottomleft).size());
        assertEquals(2, Neighbours.diagonals(grid, bottom).size());
        assertEquals(1, Neighbours.diagonals(grid, bottomright).size());
    }
    
    @Test
    public void diagonalsReturnsCorrectCellsInCenter() {
        List<Coordinate> neighbours = new LinkedList<>();
        neighbours.add(new Coordinate(0, 0));
        neighbours.add(new Coordinate(0, 2));
        neighbours.add(new Coordinate(2, 2));
        neighbours.add(new Coordinate(2, 0));
        
        for (Coordinate cell : neighbours) {
            assertTrue(Neighbours.diagonals(grid, center).contains(cell));
        }
    }
    @Test
    public void diagonalsReturnsCorrectCellsOnEdge() {
        List<Coordinate> neighbours = new LinkedList<>();
        neighbours.add(new Coordinate(0, 1));
        neighbours.add(new Coordinate(2, 1));
        
        for (Coordinate cell : neighbours) {
            assertTrue(Neighbours.diagonals(grid, bottom).contains(cell));
        }
    }
    @Test
    public void diagonalsReturnsCorrectCellsInCorner() {
        assertTrue(Neighbours.diagonals(grid, bottomleft).contains(center));
    }
    
    
    @Test
    public void surroundingReturnsCorrectAmountOfCellsInCenter() {
        assertEquals(3, Neighbours.surrounding(grid, topleft).size());
        assertEquals(5, Neighbours.surrounding(grid, top).size());
        assertEquals(3, Neighbours.surrounding(grid, topright).size());
        assertEquals(5, Neighbours.surrounding(grid, left).size());
        assertEquals(8, Neighbours.surrounding(grid, center).size());
        assertEquals(5, Neighbours.surrounding(grid, right).size());
        assertEquals(3, Neighbours.surrounding(grid, bottomleft).size());
        assertEquals(5, Neighbours.surrounding(grid, bottom).size());
        assertEquals(3, Neighbours.surrounding(grid, bottomright).size());
    }
    
    @Test
    public void surroundingReturnsCorrectCellsInCenter() {
        List<Coordinate> neighbours = new LinkedList<>();
        neighbours.add(new Coordinate(0, 0));
        neighbours.add(new Coordinate(1, 0));
        neighbours.add(new Coordinate(2, 0));
        neighbours.add(new Coordinate(0, 1));
        neighbours.add(new Coordinate(2, 1));
        neighbours.add(new Coordinate(0, 2));
        neighbours.add(new Coordinate(1, 2));
        neighbours.add(new Coordinate(2, 2));
        
        for (Coordinate cell : neighbours) {
            assertTrue(Neighbours.surrounding(grid, center).contains(cell));
        }
    }
    @Test
    public void surroundingReturnsCorrectCellsOnEdge() {
        List<Coordinate> neighbours = new LinkedList<>();
        neighbours.add(new Coordinate(0, 1));
        neighbours.add(new Coordinate(1, 1));
        neighbours.add(new Coordinate(2, 1));
        neighbours.add(new Coordinate(0, 2));
        neighbours.add(new Coordinate(2, 2));
        
        for (Coordinate cell : neighbours) {
            assertTrue(Neighbours.surrounding(grid, bottom).contains(cell));
        }
    }
    @Test
    public void surroundingReturnsCorrectCellsInCorner() {
        List<Coordinate> neighbours = new LinkedList<>();
        neighbours.add(new Coordinate(0, 1));
        neighbours.add(new Coordinate(1, 1));
        neighbours.add(new Coordinate(1, 2));
        
        for (Coordinate cell : neighbours) {
            assertTrue(Neighbours.surrounding(grid, bottomleft).contains(cell));
        }
    }
    
}
