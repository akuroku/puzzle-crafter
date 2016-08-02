
package ohha.puzzlecrafter.grid;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class GridTest {
    
    private Grid grid;
    
    
    @Before
    public void setUp() {
        grid = new Grid(4, 5);
        
        grid.setCell(new Cell(0, 0), 1);
        grid.setCell(new Cell(2, 0), 2);
        grid.setCell(new Cell(3, 0), 3);
        
        grid.setCell(new Cell(0, 1), 1);
        grid.setCell(new Cell(1, 1), 2);
        grid.setCell(new Cell(4, 1), 2);
        
        grid.setCell(new Cell(2, 2), 3);
        grid.setCell(new Cell(4, 2), -1);
        
        grid.setCell(new Cell(3, 3), -1);
        grid.setCell(new Cell(4, 3), -1);
        /*
        1 0 2 3 0
        1 2 0 0 2
        0 0 3 0-1
        0 0 0-1-1
        */
    }
    
    
    @Test
    public void constructorInitialisesCorrectHeight() {
        assertEquals(4, grid.getHeight());
    }
    @Test
    public void constructorInitialisesCorrectWidth() {
        assertEquals(5, grid.getWidth());
    }
    
    
    @Test
    public void undeterminedCellIsReportedAsUndetermined() {
        assertTrue(grid.isUndetermined(new Cell(1, 0)));
    }
    @Test
    public void filledCellIsNotReportedAsUndetermined() {
        assertFalse(grid.isUndetermined(new Cell(0, 0)));
    }
    @Test
    public void emptyCellIsNotReportedAsUndetermined() {
        assertFalse(grid.isUndetermined(new Cell(3, 3)));
    }
    
    @Test
    public void emptyCellIsReportedAsEmpty() {
        assertTrue(grid.isEmpty(new Cell(3, 3)));
    }
    @Test
    public void undeterminedCellIsNotReportedAsEmpty() {
        assertFalse(grid.isEmpty(new Cell(1, 0)));
    }
    @Test
    public void filledCellisNotReportedAsEmpty() {
        assertFalse(grid.isEmpty(new Cell(0, 0)));
    }
    
    @Test
    public void filledCellIsReportedAsFilled() {
        assertTrue(grid.isFilledIn(new Cell(0, 0)));
    }
    @Test
    public void undeterminedCellIsNotReportedAsFilled() {
        assertFalse(grid.isFilledIn(new Cell(1, 0)));
    }
    @Test
    public void emptyCellIsNotReportedAsFilled() {
        assertFalse(grid.isFilledIn(new Cell(4, 4)));
    }
    
    
    @Test
    public void duplicatedNumberOnRowIsReportedAsDuplicated() {
        assertTrue(grid.isDuplicatedOnRow(new Cell(1, 1)));
    }
    @Test
    public void uniqueNumberOnRowIsNotReportedAsDuplicated() {
        assertFalse(grid.isDuplicatedOnRow(new Cell(0, 0)));
    }
    @Test
    public void undeterminedCellOnRowIsNotReportedAsDuplicated() {
        assertFalse(grid.isDuplicatedOnRow(new Cell(1, 0)));
    }
    @Test
    public void emptyCellOnRowIsNotReportedAsDuplicated() {
        assertFalse(grid.isDuplicatedOnRow(new Cell(3, 3)));
    }
    
    
    @Test
    public void duplicatedNumberOnColumnIsReportedAsDuplicated() {
        assertTrue(grid.isDuplicatedOnColumn(new Cell(0, 1)));
    }
    @Test
    public void uniqueNumberOnColumnIsNotReportedAsDuplicated() {
        assertFalse(grid.isDuplicatedOnColumn(new Cell(1, 1)));
    }
    @Test
    public void undeterminedCellOnColumnIsNotReportedAsDuplicated() {
        assertFalse(grid.isDuplicatedOnColumn(new Cell(1, 2)));
    }
    @Test
    public void emptyCellOnColumnIsNotReportedAsDuplicated() {
        assertFalse(grid.isDuplicatedOnColumn(new Cell(4, 3)));
    }
}