
package ohha.puzzlecrafter.grid;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class PartitionGridTest {
    
    private PartitionGrid grid;
    private Region topLeft;
    private Region topRight;
    private Region midLeft;
    private Region midRight;
    private Region bottomLeft;
    private Region bottomRight;
    
    
    @Before
    public void setUp() {
        grid = new PartitionGrid(6, 6, 2, 3);
        
        topLeft = new Region();
        topLeft.addCell(new Cell(0, 0));
        topLeft.addCell(new Cell(1, 0));
        topLeft.addCell(new Cell(2, 0));
        topLeft.addCell(new Cell(0, 1));
        topLeft.addCell(new Cell(1, 1));
        topLeft.addCell(new Cell(2, 1));

        topRight = new Region();
        topRight.addCell(new Cell(3, 0));
        topRight.addCell(new Cell(4, 0));
        topRight.addCell(new Cell(5, 0));
        topRight.addCell(new Cell(3, 1));
        topRight.addCell(new Cell(4, 1));
        topRight.addCell(new Cell(5, 1));

        midLeft = new Region();
        midLeft.addCell(new Cell(0, 2));
        midLeft.addCell(new Cell(1, 2));
        midLeft.addCell(new Cell(2, 2));
        midLeft.addCell(new Cell(0, 3));
        midLeft.addCell(new Cell(1, 3));
        midLeft.addCell(new Cell(2, 3));

        midRight = new Region();
        midRight.addCell(new Cell(3, 2));
        midRight.addCell(new Cell(4, 2));
        midRight.addCell(new Cell(5, 2));
        midRight.addCell(new Cell(3, 3));
        midRight.addCell(new Cell(4, 3));
        midRight.addCell(new Cell(5, 3));

        bottomLeft = new Region();
        bottomLeft.addCell(new Cell(0, 4));
        bottomLeft.addCell(new Cell(1, 4));
        bottomLeft.addCell(new Cell(2, 4));
        bottomLeft.addCell(new Cell(0, 5));
        bottomLeft.addCell(new Cell(1, 5));
        bottomLeft.addCell(new Cell(2, 5));

        bottomRight = new Region();
        bottomRight.addCell(new Cell(3, 4));
        bottomRight.addCell(new Cell(4, 4));
        bottomRight.addCell(new Cell(5, 4));
        bottomRight.addCell(new Cell(3, 5));
        bottomRight.addCell(new Cell(4, 5));
        bottomRight.addCell(new Cell(5, 5));
        
        grid.setCell(new Cell(0, 0), 1);
        grid.setCell(new Cell(2, 0), 2);
        grid.setCell(new Cell(3, 0), 3);
        
        grid.setCell(new Cell(0, 1), 1);
        grid.setCell(new Cell(1, 1), 2);
        grid.setCell(new Cell(4, 1), 2);
        
        grid.setCell(new Cell(2, 2), 3);
        grid.setCell(new Cell(4, 2), -1);
        grid.setCell(new Cell(5, 2), 3);
        
        grid.setCell(new Cell(0, 3), -1);
        grid.setCell(new Cell(4, 3), -1);
        
        grid.setCell(new Cell(1, 4), 2);
        /*
        1 0 2 | 3 0 0
        1 2 0 | 0 2 0
        ------+------
        0 0 3 | 0-1 3
       -1 0 0 | 0-1 0
        ------+------
        0 2 0 | 0 0 0
        0 0 0 | 0 0 0
        */
    }
    
    
    @Test
    public void correctContainingRegionOfCellIsReturned() {
        assertEquals(topLeft, grid.getPartition().getRegionOf(new Cell(1, 1)));
    }
    
    @Test
    public void nullRegionIsReturnedWhenCellIsInNoRegion() {
        assertEquals(null, grid.getPartition().getRegionOf(new Cell(20, 20)));
    }
    
    
    @Test
    public void duplicatedNumberOnRegionIsReportedAsDuplicated() {
        assertTrue(grid.isDuplicatedOnRegion(new Cell(1, 1)));
    }
    @Test
    public void uniqueNumberOnRegionIsNotReportedAsDuplicated() {
        assertFalse(grid.isDuplicatedOnRegion(new Cell(3, 0)));
    }
    @Test
    public void undeterminedCellOnRegionIsNotReportedAsDuplicated() {
        assertFalse(grid.isDuplicatedOnRegion(new Cell(1, 2)));
    }
    @Test
    public void emptyCellOnRegionIsNotReportedAsDuplicated() {
        assertFalse(grid.isDuplicatedOnRegion(new Cell(4, 3)));
    }
    
    
    @Test
    public void createRectangularPartitionMakesCorrectAmountOfRegions() {
        assertEquals(6, grid.getPartition().getRegions().size());
    }
    
    @Test
    public void createRectangularPartitionMakesTopLeftRegion() {
        assertTrue(grid.getPartition().contains(topLeft));
    }
    @Test
    public void createRectangularPartitionMakesTopRightRegion() {
        assertTrue(grid.getPartition().contains(topRight));
    }
    @Test
    public void createRectangularPartitionMakesMidLeftRegion() {
        assertTrue(grid.getPartition().contains(midLeft));
    }
    @Test
    public void createRectangularPartitionMakesMidRightRegion() {
        assertTrue(grid.getPartition().contains(midRight));
    }
    @Test
    public void createRectangularPartitionMakesBottomLeftRegion() {
        assertTrue(grid.getPartition().contains(bottomLeft));
    }
    @Test
    public void createRectangularPartitionMakesBottomRightRegion() {
        assertTrue(grid.getPartition().contains(bottomRight));
    }
}
