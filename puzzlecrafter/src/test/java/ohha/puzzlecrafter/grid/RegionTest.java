
package ohha.puzzlecrafter.grid;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class RegionTest {
    
    private Region a;
    private Region b1;
    private Region b2;
    private Region b3;
    private Region c;
    private Cell s;
    
    
    @Before
    public void setUp() {
        s = new Cell(0, 0);
        Cell t = new Cell(1, 0);
        Cell u = new Cell(2, 0);
        Cell v = new Cell(3, 0);
        Cell w = new Cell(0, 1);
        Cell x = new Cell(1, 1);
        Cell y = new Cell(2, 1);
        Cell z = new Cell(3, 1);
        
        a = new Region();
        a.addCell(s);
        a.addCell(t);
        
        b1 = new Region();
        b1.addCell(u);
        b1.addCell(v);
        b1.addCell(z);
        
        b2 = new Region();
        b2.addCell(v);
        b2.addCell(z);
        b2.addCell(u);
        
        b3 = new Region();
        b3.addCell(z);
        b3.addCell(v);
        b3.addCell(u);
        
        c = new Region();
        c.addCell(s);
        c.addCell(t);
        c.addCell(u);
        c.addCell(w);
        c.addCell(x);
        c.addCell(y);
    }
    
    
    @Test
    public void containedCellIsReportedAsContained() {
        assertTrue(a.contains(s));
    }
    @Test
    public void notContainedCellIsNotReportedAsContained() {
        assertFalse(b1.contains(s));
    }
    
    
    @Test
    public void rectangularRegionConstructorMakesCorrectAmountOfCells() {
        Region r = new Region(2, 3, 1, 1);
        assertEquals(6, r.getCells().size());
    }
    @Test
    public void rectangularRegionConstructorMakesCorrectCells() {
        Region r = new Region(2, 3, 1, 1);
        
        for (int y = 1; y <= 2; y++) {
            for (int x = 1; x <= 3; x++) {
                assertTrue(r.contains(new Cell(x, y)));
            }
        }
    }
    
    
    @Test
    public void deepCopyCopiesCorrectly() {
        Region copy = a.deepCopy();
        
        assertEquals(a.getCells().size(), copy.getCells().size());
        
        for (Cell c : a.getCells()) {
            assertTrue(copy.contains(c));
        }
    }
    @Test
    public void deepCopyCopiesDeep() {
        Region copy = a.deepCopy();
        a.addCell(new Cell(10, 10));
        
        assertEquals(2, copy.getCells().size());
    }
    
    
    @Test
    public void equalsDoesntEquateWithNull() {
        assertFalse(a.equals(null));
    }
    @Test
    public void equalsDoesntEquateWithObjectOfDifferentType() {
        assertFalse(a.equals(s));
    }
    
    @Test
    public void equalsDoesntEquateWithRegionOfDifferentSize() {
        assertFalse(a.equals(c));
    }
    @Test
    public void equalsDoesntEquateWithUnequalRegionOfSameSize() {
        assertFalse(a.equals(b1));
    }
    
    
    @Test
    public void equalsIsConsistentWhenReturningTrue() {
        assertEquals(b1.equals(b2), b1.equals(b2));
    }
    @Test
    public void equalsIsConsistentWhenReturningFalse() {
        assertEquals(a.equals(b1), a.equals(b1));
    }
    
    
    @Test
    public void equalsIsReflexive() {
        assertTrue(a.equals(a));
    }
    
    
    @Test
    public void equalsIsSymmetricWhenReturningTrue() {
        assertEquals(b1.equals(b2), b2.equals(b1));
    }
    @Test
    public void equalsIsSymmetricWhenReturningFalse() {
        assertEquals(a.equals(b1), b1.equals(a));
    }
    
    
    @Test
    public void equalsIsTransitive() {
        assertEquals(b1.equals(b2) && b2.equals(b3), b1.equals(b3));
    }
    
    
    @Test
    public void hashCodeIsConsistent() {
        assertEquals(a.hashCode(), a.hashCode());
    }
    
    @Test
    public void equalObjectsHaveEqualHashCodes() {
        assertEquals(b1.hashCode(), b2.hashCode());
    }
}
