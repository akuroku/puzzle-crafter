
package ohha.puzzlecrafter.grid;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class CoordinateTest {
    
    private Coordinate a;
    private Coordinate b1;
    private Coordinate b2;
    private Coordinate b3;
    
    
    @Before
    public void setUp() {
        a = new Coordinate(0, 2);
        b1 = new Coordinate(1, 3);
        b2 = new Coordinate(1, 3);
        b3 = new Coordinate(1, 3);
    }
    
    
    @Test
    public void shiftXChangesXCoordinate() {
        assertEquals(4, b1.shiftX(3).getX());
    }
    @Test
    public void shiftXDoesntChangeYCoordinate() {
        assertEquals(3, b1.shiftX(3).getY());
    }
    @Test
    public void shiftYChangesYCoordinate() {
        assertEquals(8, b1.shiftY(5).getY());
    }
    @Test
    public void shiftYDoesntChangeXCoordinate() {
        assertEquals(1, b1.shiftY(5).getX());
    }
    
    
    @Test
    public void switchXChangesXCoordinate() {
        assertEquals(3, a.switchX(3).getX());
    }
    @Test
    public void switchXDoesntChangeYCoordinate() {
        assertEquals(2, a.switchX(3).getY());
    }
    @Test
    public void switchYChangesYCoordinate() {
        assertEquals(5, a.switchY(5).getY());
    }
    @Test
    public void switchYDoesntChangeXCoordinate() {
        assertEquals(0, a.switchY(5).getX());
    }
    
    
    
    @Test
    public void equalsDoesntEquateWithNull() {
        assertFalse(a.equals(null));
    }
    @Test
    public void equalsDoesntEquateWithObjectOfDifferentType() {
        assertFalse(a.equals(new int[] {1}));
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
