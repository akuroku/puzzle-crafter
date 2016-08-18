
package ohha.puzzlecrafter.grid;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


// Miten nimetään jos on useita testiluokkia??

public class PartitionTest {
    
    private Partition p;
    private Region topLeft;
    private Region topRight;
    private Region midLeft;
    private Region midRight;
    private Region bottomLeft;
    private Region bottomRight;
    
    @Before
    public void setUp() {
        p = new Partition(2, 3, 3, 2);
        
        topLeft = new Region();
        topLeft.addCellAt(new Coordinate(0, 0));
        topLeft.addCellAt(new Coordinate(1, 0));
        topLeft.addCellAt(new Coordinate(2, 0));
        topLeft.addCellAt(new Coordinate(0, 1));
        topLeft.addCellAt(new Coordinate(1, 1));
        topLeft.addCellAt(new Coordinate(2, 1));

        topRight = new Region();
        topRight.addCellAt(new Coordinate(3, 0));
        topRight.addCellAt(new Coordinate(4, 0));
        topRight.addCellAt(new Coordinate(5, 0));
        topRight.addCellAt(new Coordinate(3, 1));
        topRight.addCellAt(new Coordinate(4, 1));
        topRight.addCellAt(new Coordinate(5, 1));

        midLeft = new Region();
        midLeft.addCellAt(new Coordinate(0, 2));
        midLeft.addCellAt(new Coordinate(1, 2));
        midLeft.addCellAt(new Coordinate(2, 2));
        midLeft.addCellAt(new Coordinate(0, 3));
        midLeft.addCellAt(new Coordinate(1, 3));
        midLeft.addCellAt(new Coordinate(2, 3));

        midRight = new Region();
        midRight.addCellAt(new Coordinate(3, 2));
        midRight.addCellAt(new Coordinate(4, 2));
        midRight.addCellAt(new Coordinate(5, 2));
        midRight.addCellAt(new Coordinate(3, 3));
        midRight.addCellAt(new Coordinate(4, 3));
        midRight.addCellAt(new Coordinate(5, 3));

        bottomLeft = new Region();
        bottomLeft.addCellAt(new Coordinate(0, 4));
        bottomLeft.addCellAt(new Coordinate(1, 4));
        bottomLeft.addCellAt(new Coordinate(2, 4));
        bottomLeft.addCellAt(new Coordinate(0, 5));
        bottomLeft.addCellAt(new Coordinate(1, 5));
        bottomLeft.addCellAt(new Coordinate(2, 5));

        bottomRight = new Region();
        bottomRight.addCellAt(new Coordinate(3, 4));
        bottomRight.addCellAt(new Coordinate(4, 4));
        bottomRight.addCellAt(new Coordinate(5, 4));
        bottomRight.addCellAt(new Coordinate(3, 5));
        bottomRight.addCellAt(new Coordinate(4, 5));
        bottomRight.addCellAt(new Coordinate(5, 5));
    }
    
    
    @Test
    public void containedRegionIsReportedAsContained() {
        Region r = new Region();
        r.addCellAt(new Coordinate(0, 20));
        r.addCellAt(new Coordinate(0, 21));
        p.addRegion(r);
        assertTrue(p.contains(r));
    }
    @Test
    public void uncontainedRegionIsNotReportedAsContained() {
        Region r = new Region();
        r.addCellAt(new Coordinate(0, 20));
        r.addCellAt(new Coordinate(0, 21));
        assertFalse(p.contains(r));
    }
    
    
    @Test
    public void correctContainingRegionOfCellIsReturned() {
        assertEquals(topLeft, p.getRegionOf(new Coordinate(1, 1)));
    }
    @Test
    public void nullRegionIsReturnedWhenCellIsInNoRegion() {
        assertEquals(null, p.getRegionOf(new Coordinate(20, 20)));
    }
    
    
    @Test
    public void rectangularPartitionConstructorMakesCorrectAmountOfRegions() {
        assertEquals(6, p.getRegions().size());
    }
    
    
    @Test
    public void rectangularPartitionConstructorMakesTopLeftRegion() {
        assertTrue(p.contains(topLeft));
    }
    @Test
    public void rectangularPartitionConstructorMakesTopRightRegion() {
        assertTrue(p.contains(topRight));
    }
    @Test
    public void rectangularPartitionConstructorMakesMidLeftRegion() {
        assertTrue(p.contains(midLeft));
    }
    @Test
    public void rectangularPartitionConstructorMakesMidRightRegion() {
        assertTrue(p.contains(midRight));
    }
    @Test
    public void rectangularPartitionConstructorMakesBottomLeftRegion() {
        assertTrue(p.contains(bottomLeft));
    }
    @Test
    public void rectangularPartitionConstructorMakesBottomRightRegion() {
        assertTrue(p.contains(bottomRight));
    }
    
    
    @Test
    public void deepCopyCopiesCorrectly() {
        Partition copy = p.deepCopy();
        
        assertEquals(p.getRegions().size(), copy.getRegions().size());
        
        for (Region r : p.getRegions()) {
            assertTrue(copy.contains(r));
        }
    }
    @Test
    public void deepCopyCopiesDeep() {
        Partition copy = p.deepCopy();
        p.getRegionOf(new Coordinate(0, 0)).addCellAt(new Coordinate(10, 10));
        
        assertFalse(copy.getRegionOf(new Coordinate(0, 0)).contains(new Coordinate(10, 10)));
    }
}
