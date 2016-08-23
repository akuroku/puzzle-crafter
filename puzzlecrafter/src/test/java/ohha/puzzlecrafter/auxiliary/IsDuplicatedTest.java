
package ohha.puzzlecrafter.auxiliary;

import ohha.puzzlecrafter.grid.Coordinate;
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
        
        grid.setValueOfCellAt(new Coordinate(0, 0), 1);
        grid.setValueOfCellAt(new Coordinate(2, 0), 2);
        grid.setValueOfCellAt(new Coordinate(3, 0), 3);
        
        grid.setValueOfCellAt(new Coordinate(0, 1), 1);
        grid.setValueOfCellAt(new Coordinate(1, 1), 2);
        grid.setValueOfCellAt(new Coordinate(4, 1), 2);
        
        grid.setValueOfCellAt(new Coordinate(2, 2), 3);
        grid.setValueOfCellAt(new Coordinate(4, 2), -1);
        grid.setValueOfCellAt(new Coordinate(5, 2), 3);
        
        grid.setValueOfCellAt(new Coordinate(0, 3), -1);
        grid.setValueOfCellAt(new Coordinate(4, 3), -1);
        
        grid.setValueOfCellAt(new Coordinate(1, 4), 2);
        
        
        partition = new Partition();
        
        Region topLeft = new Region();
        topLeft.addCellAt(new Coordinate(0, 0));
        topLeft.addCellAt(new Coordinate(1, 0));
        topLeft.addCellAt(new Coordinate(2, 0));
        topLeft.addCellAt(new Coordinate(0, 1));
        topLeft.addCellAt(new Coordinate(1, 1));
        topLeft.addCellAt(new Coordinate(2, 1));
        partition.addRegion(topLeft);

        Region topRight = new Region();
        topRight.addCellAt(new Coordinate(3, 0));
        topRight.addCellAt(new Coordinate(4, 0));
        topRight.addCellAt(new Coordinate(5, 0));
        topRight.addCellAt(new Coordinate(3, 1));
        topRight.addCellAt(new Coordinate(4, 1));
        topRight.addCellAt(new Coordinate(5, 1));
        partition.addRegion(topRight);

        Region midLeft = new Region();
        midLeft.addCellAt(new Coordinate(0, 2));
        midLeft.addCellAt(new Coordinate(1, 2));
        midLeft.addCellAt(new Coordinate(2, 2));
        midLeft.addCellAt(new Coordinate(0, 3));
        midLeft.addCellAt(new Coordinate(1, 3));
        midLeft.addCellAt(new Coordinate(2, 3));
        partition.addRegion(midLeft);

        Region midRight = new Region();
        midRight.addCellAt(new Coordinate(3, 2));
        midRight.addCellAt(new Coordinate(4, 2));
        midRight.addCellAt(new Coordinate(5, 2));
        midRight.addCellAt(new Coordinate(3, 3));
        midRight.addCellAt(new Coordinate(4, 3));
        midRight.addCellAt(new Coordinate(5, 3));
        partition.addRegion(midRight);

        Region bottomLeft = new Region();
        bottomLeft.addCellAt(new Coordinate(0, 4));
        bottomLeft.addCellAt(new Coordinate(1, 4));
        bottomLeft.addCellAt(new Coordinate(2, 4));
        bottomLeft.addCellAt(new Coordinate(0, 5));
        bottomLeft.addCellAt(new Coordinate(1, 5));
        bottomLeft.addCellAt(new Coordinate(2, 5));
        partition.addRegion(bottomLeft);

        Region bottomRight = new Region();
        bottomRight.addCellAt(new Coordinate(3, 4));
        bottomRight.addCellAt(new Coordinate(4, 4));
        bottomRight.addCellAt(new Coordinate(5, 4));
        bottomRight.addCellAt(new Coordinate(3, 5));
        bottomRight.addCellAt(new Coordinate(4, 5));
        bottomRight.addCellAt(new Coordinate(5, 5));
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
        assertTrue(IsDuplicated.onRow(grid, new Coordinate(1, 1)));
    }
    @Test
    public void uniqueNumberOnRowIsNotReportedAsDuplicated() {
        assertFalse(IsDuplicated.onRow(grid, new Coordinate(0, 0)));
    }
    @Test
    public void undeterminedCellOnRowIsNotReportedAsDuplicated() {
        assertFalse(IsDuplicated.onRow(grid, new Coordinate(1, 0)));
    }
    @Test
    public void emptyCellOnRowIsNotReportedAsDuplicated() {
        assertFalse(IsDuplicated.onRow(grid, new Coordinate(0, 3)));
    }
    
    
    @Test
    public void duplicatedNumberOnColumnIsReportedAsDuplicated() {
        assertTrue(IsDuplicated.onColumn(grid, new Coordinate(1, 1)));
    }
    @Test
    public void uniqueNumberOnColumnIsNotReportedAsDuplicated() {
        assertFalse(IsDuplicated.onColumn(grid, new Coordinate(2, 2)));
    }
    @Test
    public void undeterminedCellOnColumnIsNotReportedAsDuplicated() {
        assertFalse(IsDuplicated.onColumn(grid, new Coordinate(1, 2)));
    }
    @Test
    public void emptyCellOnColumnIsNotReportedAsDuplicated() {
        assertFalse(IsDuplicated.onColumn(grid, new Coordinate(4, 3)));
    }
    
    
    @Test
    public void duplicatedNumberOnRegionIsReportedAsDuplicated() {
        assertTrue(IsDuplicated.onRegion(grid, partition, new Coordinate(1, 1)));
    }
    @Test
    public void uniqueNumberOnRegionIsNotReportedAsDuplicated() {
        assertFalse(IsDuplicated.onRegion(grid, partition, new Coordinate(3, 0)));
    }
    @Test
    public void undeterminedCellOnRegionIsNotReportedAsDuplicated() {
        assertFalse(IsDuplicated.onRegion(grid, partition, new Coordinate(1, 2)));
    }
    @Test
    public void emptyCellOnRegionIsNotReportedAsDuplicated() {
        assertFalse(IsDuplicated.onRegion(grid, partition, new Coordinate(4, 3)));
    }
}
