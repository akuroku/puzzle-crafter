
package ohha.puzzlecrafter;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class GridTest {
    
    private Grid grid;
    
    @Before
    public void setUp() {
        grid = new Grid(4, 5);
        
        grid.setCell(0, 0, 1);
        grid.setCell(2, 0, 2);
        grid.setCell(3, 0, 3);
        
        grid.setCell(0, 1, 1);
        grid.setCell(1, 1, 2);
        grid.setCell(4, 1, 2);
        
        grid.setCell(2, 2, 3);
        grid.setCell(4, 2, -1);
        
        grid.setCell(3, 3, -1);
        grid.setCell(4, 3, -1);
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
    public void duplicatedNumberOnRowIsReportedAsDuplicated() {
        assertTrue(grid.duplicatedOnRow(1, 1));
    }
    @Test
    public void uniqueNumberOnRowIsNotReportedAsDuplicated() {
        assertFalse(grid.duplicatedOnRow(0, 0));
    }
    @Test
    public void undeterminedCellOnRowIsNotReportedAsDuplicated() {
        assertFalse(grid.duplicatedOnRow(1, 0));
    }
    @Test
    public void emptyCellOnRowIsNotReportedAsDuplicated() {
        assertFalse(grid.duplicatedOnRow(3, 3));
    }
    
    
    @Test
    public void duplicatedNumberOnColumnIsReportedAsDuplicated() {
        assertTrue(grid.duplicatedOnColumn(0, 1));
    }
    @Test
    public void uniqueNumberOnColumnIsNotReportedAsDuplicated() {
        assertFalse(grid.duplicatedOnColumn(1, 1));
    }
    @Test
    public void undeterminedCellOnColumnIsNotReportedAsDuplicated() {
        assertFalse(grid.duplicatedOnColumn(1, 2));
    }
    @Test
    public void emptyCellOnColumnIsNotReportedAsDuplicated() {
        assertFalse(grid.duplicatedOnColumn(4, 3));
    }
}