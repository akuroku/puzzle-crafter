
package ohha.puzzlecrafter.grid;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class GridTest {
    
    private Grid grid;
    
    
    @Before
    public void setUp() {
        grid = new Grid(4, 5);
        
        grid.setValueAt(new Cell(0, 0), 1);
        grid.setValueAt(new Cell(2, 0), 2);
        grid.setValueAt(new Cell(3, 0), 3);
        
        grid.setValueAt(new Cell(0, 1), 1);
        grid.setValueAt(new Cell(1, 1), 2);
        grid.setValueAt(new Cell(4, 1), 2);
        
        grid.setValueAt(new Cell(2, 2), 3);
        grid.setValueAt(new Cell(4, 2), -1);
        
        grid.setValueAt(new Cell(3, 3), -1);
        grid.setValueAt(new Cell(4, 3), -1);
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
        assertFalse(grid.isFilledIn(new Cell(3, 3)));
    }
    
    
    @Test
    public void deepCopyCopiesCorrectly() {
        Grid copy = grid.deepCopy();
        
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 5; x++) {
                Cell cell = new Cell(x, y);
                assertEquals(grid.getValueAt(cell), copy.getValueAt(cell));
            }
        }
    }
    @Test
    public void deepCopyCopiesDeep() {
        Grid copy = grid.deepCopy();
        grid.setValueAt(new Cell(0, 0), 20);
        assertEquals(1, copy.getValueAt(new Cell(0, 0)));
    }
}