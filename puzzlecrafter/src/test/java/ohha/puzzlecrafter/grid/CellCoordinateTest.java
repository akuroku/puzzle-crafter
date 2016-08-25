
package ohha.puzzlecrafter.grid;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class CellCoordinateTest {
    
    private CellCoordinate a;
    private CellCoordinate b1;
    private CellCoordinate b2;
    private CellCoordinate b3;
    private CellCoordinate c;
    
    @Before
    public void setUp() {
        a = new CellCoordinate(0, 2);
        b1 = new CellCoordinate(1, 3);
        b2 = new CellCoordinate(1, 3);
        b3 = new CellCoordinate(1, 3);
        c = new CellCoordinate(-1, -1);
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
    public void getNeighbourReturnsNullWhenNullSide() {
        assertTrue(b1.getNeighbour(null) == null);
    }
    
    @Test
    public void getNeighbourReturnsTopCorrectly() {
        assertEquals(new CellCoordinate(1, 2), b1.getNeighbour(Side.TOP));
    }
    @Test
    public void getNeighbourReturnsTopCorrectlyWithNegativeComponents() {
        assertEquals(new CellCoordinate(-1, -2), c.getNeighbour(Side.TOP));
    }
    
    @Test
    public void getNeighbourReturnsLeftCorrectly() {
        assertEquals(new CellCoordinate(0, 3), b1.getNeighbour(Side.LEFT));
    }
    @Test
    public void getNeighbourReturnsLeftCorrectlyWithNegativeComponents() {
        assertEquals(new CellCoordinate(-2, -1), c.getNeighbour(Side.LEFT));
    }
    
    @Test
    public void getNeighbourReturnsBottomCorrectly() {
        assertEquals(new CellCoordinate(1, 4), b1.getNeighbour(Side.BOTTOM));
    }
    @Test
    public void getNeighbourReturnsBottomCorrectlyWithNegativeComponents() {
        assertEquals(new CellCoordinate(-1, 0), c.getNeighbour(Side.BOTTOM));
    }
    
    @Test
    public void getNeighbourReturnsRightCorrectly() {
        assertEquals(new CellCoordinate(2, 3), b1.getNeighbour(Side.RIGHT));
    }
    @Test
    public void getNeighbourReturnsRightCorrectlyWithNegativeComponents() {
        assertEquals(new CellCoordinate(0, -1), c.getNeighbour(Side.RIGHT));
    }
    
    
    @Test
    public void getEdgeAtTopReturnsCorrectEdge() {
        assertEquals(new EdgeCoordinate(1, 3, EdgeCoordinate.HORIZONTAL_EDGE), b1.getEdgeAt(Side.TOP));
    }
    @Test
    public void getEdgeAtLeftReturnsCorrectEdge() {
        assertEquals(new EdgeCoordinate(1, 3, EdgeCoordinate.VERTICAL_EDGE), b1.getEdgeAt(Side.LEFT));
    }
    @Test
    public void getEdgeAtBottomReturnsCorrectEdge() {
        assertEquals(new EdgeCoordinate(1, 4, EdgeCoordinate.HORIZONTAL_EDGE), b1.getEdgeAt(Side.BOTTOM));
    }
    @Test
    public void getEdgeAtRightReturnsCorrectEdge() {
        assertEquals(new EdgeCoordinate(2, 3, EdgeCoordinate.VERTICAL_EDGE), b1.getEdgeAt(Side.RIGHT));
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
