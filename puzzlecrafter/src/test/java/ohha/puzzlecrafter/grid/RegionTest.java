
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
    private CellCoordinate s;
    
    
    @Before
    public void setUp() {
        s = new CellCoordinate(0, 0);
        CellCoordinate t = new CellCoordinate(1, 0);
        CellCoordinate u = new CellCoordinate(2, 0);
        CellCoordinate v = new CellCoordinate(3, 0);
        CellCoordinate w = new CellCoordinate(0, 1);
        CellCoordinate x = new CellCoordinate(1, 1);
        CellCoordinate y = new CellCoordinate(2, 1);
        CellCoordinate z = new CellCoordinate(3, 1);
        
        a = new Region();
        a.addCellAt(s);
        a.addCellAt(t);
        
        b1 = new Region();
        b1.addCellAt(u);
        b1.addCellAt(v);
        b1.addCellAt(z);
        
        b2 = new Region();
        b2.addCellAt(v);
        b2.addCellAt(z);
        b2.addCellAt(u);
        
        b3 = new Region();
        b3.addCellAt(z);
        b3.addCellAt(v);
        b3.addCellAt(u);
        
        c = new Region();
        c.addCellAt(s);
        c.addCellAt(t);
        c.addCellAt(u);
        c.addCellAt(w);
        c.addCellAt(x);
        c.addCellAt(y);
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
        Region r = new Region(2, 3, new CellCoordinate(1, 1));
        assertEquals(6, r.getCells().size());
    }
    @Test
    public void rectangularRegionConstructorMakesCorrectCells() {
        Region r = new Region(2, 3, new CellCoordinate(1, 1));
        
        for (int y = 1; y <= 2; y++) {
            for (int x = 1; x <= 3; x++) {
                assertTrue(r.contains(new CellCoordinate(x, y)));
            }
        }
    }
    
    
    @Test
    public void deepCopyCopiesCorrectly() {
        Region copy = a.deepCopy();
        
        assertEquals(a.getCells().size(), copy.getCells().size());
        
        for (CellCoordinate c : a.getCells()) {
            assertTrue(copy.contains(c));
        }
    }
    @Test
    public void deepCopyCopiesDeep() {
        Region copy = a.deepCopy();
        a.addCellAt(new CellCoordinate(10, 10));
        
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
