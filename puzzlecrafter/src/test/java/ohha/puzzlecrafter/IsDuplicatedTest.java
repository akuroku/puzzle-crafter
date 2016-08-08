
package ohha.puzzlecrafter;


import ohha.puzzlecrafter.grid.Cell;
import ohha.puzzlecrafter.grid.Grid;
import ohha.puzzlecrafter.grid.Partition;
import ohha.puzzlecrafter.grid.Region;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class IsDuplicatedTest {
    
    private Grid grid;
    private Partition partition;
    
    @Before
    public void setUp() {
        grid = new Grid(6, 6);
        
        grid.setValueAt(new Cell(0, 0), 1);
        grid.setValueAt(new Cell(2, 0), 2);
        grid.setValueAt(new Cell(3, 0), 3);
        
        grid.setValueAt(new Cell(0, 1), 1);
        grid.setValueAt(new Cell(1, 1), 2);
        grid.setValueAt(new Cell(4, 1), 2);
        
        grid.setValueAt(new Cell(2, 2), 3);
        grid.setValueAt(new Cell(4, 2), -1);
        grid.setValueAt(new Cell(5, 2), 3);
        
        grid.setValueAt(new Cell(0, 3), -1);
        grid.setValueAt(new Cell(4, 3), -1);
        
        grid.setValueAt(new Cell(1, 4), 2);
        
        
        partition = new Partition();
        
        Region topLeft = new Region();
        topLeft.addCell(new Cell(0, 0));
        topLeft.addCell(new Cell(1, 0));
        topLeft.addCell(new Cell(2, 0));
        topLeft.addCell(new Cell(0, 1));
        topLeft.addCell(new Cell(1, 1));
        topLeft.addCell(new Cell(2, 1));
        partition.addRegion(topLeft);

        Region topRight = new Region();
        topRight.addCell(new Cell(3, 0));
        topRight.addCell(new Cell(4, 0));
        topRight.addCell(new Cell(5, 0));
        topRight.addCell(new Cell(3, 1));
        topRight.addCell(new Cell(4, 1));
        topRight.addCell(new Cell(5, 1));
        partition.addRegion(topRight);

        Region midLeft = new Region();
        midLeft.addCell(new Cell(0, 2));
        midLeft.addCell(new Cell(1, 2));
        midLeft.addCell(new Cell(2, 2));
        midLeft.addCell(new Cell(0, 3));
        midLeft.addCell(new Cell(1, 3));
        midLeft.addCell(new Cell(2, 3));
        partition.addRegion(midLeft);

        Region midRight = new Region();
        midRight.addCell(new Cell(3, 2));
        midRight.addCell(new Cell(4, 2));
        midRight.addCell(new Cell(5, 2));
        midRight.addCell(new Cell(3, 3));
        midRight.addCell(new Cell(4, 3));
        midRight.addCell(new Cell(5, 3));
        partition.addRegion(midRight);

        Region bottomLeft = new Region();
        bottomLeft.addCell(new Cell(0, 4));
        bottomLeft.addCell(new Cell(1, 4));
        bottomLeft.addCell(new Cell(2, 4));
        bottomLeft.addCell(new Cell(0, 5));
        bottomLeft.addCell(new Cell(1, 5));
        bottomLeft.addCell(new Cell(2, 5));
        partition.addRegion(bottomLeft);

        Region bottomRight = new Region();
        bottomRight.addCell(new Cell(3, 4));
        bottomRight.addCell(new Cell(4, 4));
        bottomRight.addCell(new Cell(5, 4));
        bottomRight.addCell(new Cell(3, 5));
        bottomRight.addCell(new Cell(4, 5));
        bottomRight.addCell(new Cell(5, 5));
        partition.addRegion(bottomRight);
        
        /* alternatively, if it works,
        partition.createRectangularPartition(2, 3, 3, 2);
        */
        
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
    public void duplicatedNumberOnRowIsReportedAsDuplicated() {
        assertTrue(IsDuplicated.onRow(grid, new Cell(1, 1)));
    }
    @Test
    public void uniqueNumberOnRowIsNotReportedAsDuplicated() {
        assertFalse(IsDuplicated.onRow(grid, new Cell(0, 0)));
    }
    @Test
    public void undeterminedCellOnRowIsNotReportedAsDuplicated() {
        assertFalse(IsDuplicated.onRow(grid, new Cell(1, 0)));
    }
    @Test
    public void emptyCellOnRowIsNotReportedAsDuplicated() {
        assertFalse(IsDuplicated.onRow(grid, new Cell(0, 3)));
    }
    
    
    @Test
    public void duplicatedNumberOnColumnIsReportedAsDuplicated() {
        assertTrue(IsDuplicated.onColumn(grid, new Cell(1, 1)));
    }
    @Test
    public void uniqueNumberOnColumnIsNotReportedAsDuplicated() {
        assertFalse(IsDuplicated.onColumn(grid, new Cell(2, 2)));
    }
    @Test
    public void undeterminedCellOnColumnIsNotReportedAsDuplicated() {
        assertFalse(IsDuplicated.onColumn(grid, new Cell(1, 2)));
    }
    @Test
    public void emptyCellOnColumnIsNotReportedAsDuplicated() {
        assertFalse(IsDuplicated.onColumn(grid, new Cell(4, 3)));
    }
    
    
    @Test
    public void duplicatedNumberOnRegionIsReportedAsDuplicated() {
        assertTrue(IsDuplicated.onRegion(grid, partition, new Cell(1, 1)));
    }
    @Test
    public void uniqueNumberOnRegionIsNotReportedAsDuplicated() {
        assertFalse(IsDuplicated.onRegion(grid, partition, new Cell(3, 0)));
    }
    @Test
    public void undeterminedCellOnRegionIsNotReportedAsDuplicated() {
        assertFalse(IsDuplicated.onRegion(grid, partition, new Cell(1, 2)));
    }
    @Test
    public void emptyCellOnRegionIsNotReportedAsDuplicated() {
        assertFalse(IsDuplicated.onRegion(grid, partition, new Cell(4, 3)));
    }
}
