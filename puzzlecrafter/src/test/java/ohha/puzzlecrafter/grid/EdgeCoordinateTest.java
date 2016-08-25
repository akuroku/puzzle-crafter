/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohha.puzzlecrafter.grid;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tapio
 */
public class EdgeCoordinateTest {
    
    private EdgeCoordinate a;
    private EdgeCoordinate a1;
    private EdgeCoordinate b1;
    private EdgeCoordinate b2;
    private EdgeCoordinate b3;
    private EdgeCoordinate c;
    
    @Before
    public void setUp() {
        a = new EdgeCoordinate(0, 2, EdgeCoordinate.HORIZONTAL_EDGE);
        a1 = new EdgeCoordinate(0, 2, EdgeCoordinate.VERTICAL_EDGE);
        b1 = new EdgeCoordinate(1, 3, EdgeCoordinate.HORIZONTAL_EDGE);
        b2 = new EdgeCoordinate(1, 3, EdgeCoordinate.HORIZONTAL_EDGE);
        b3 = new EdgeCoordinate(1, 3, EdgeCoordinate.HORIZONTAL_EDGE);
    }
    
    
    public void getCellAtLeftReturnsCellForVerticalEdge() {
        assertEquals(new CellCoordinate(0, 2), new EdgeCoordinate(1, 2, EdgeCoordinate.VERTICAL_EDGE).getCellAt(Side.LEFT));
    }
    public void getCellAtRightReturnsCellForVerticalEdge() {
        assertEquals(new CellCoordinate(1, 2), new EdgeCoordinate(1, 2, EdgeCoordinate.VERTICAL_EDGE).getCellAt(Side.RIGHT));
    }
    public void getCellAtTopReturnsNullForVerticalEdge() {
        assertTrue(null == new EdgeCoordinate(1, 2, EdgeCoordinate.VERTICAL_EDGE).getCellAt(Side.TOP));
    }
    public void getCellAtBottomReturnsNullForVerticalEdge() {
        assertTrue(null == new EdgeCoordinate(1, 2, EdgeCoordinate.VERTICAL_EDGE).getCellAt(Side.BOTTOM));
    }
    
    public void getCellAtTopReturnsCellForHorizontalEdge() {
        assertEquals(new CellCoordinate(0, 2), new EdgeCoordinate(1, 2, EdgeCoordinate.HORIZONTAL_EDGE).getCellAt(Side.TOP));
    }
    public void getCellAtBottomReturnsCellForHorizontalEdge() {
        assertEquals(new CellCoordinate(1, 2), new EdgeCoordinate(1, 2, EdgeCoordinate.HORIZONTAL_EDGE).getCellAt(Side.BOTTOM));
    }
    public void getCellAtLeftReturnsNullForHorizontalEdge() {
        assertTrue(null == new EdgeCoordinate(1, 2, EdgeCoordinate.HORIZONTAL_EDGE).getCellAt(Side.LEFT));
    }
    public void getCellAtRightReturnsNullForHorizontalEdge() {
        assertTrue(null == new EdgeCoordinate(1, 2, EdgeCoordinate.HORIZONTAL_EDGE).getCellAt(Side.RIGHT));
    }
    
    // here 500 is meant to be any number that isn't used by a constant that
    // stands for the orientation of an edge
    public void getCellAtTopReturnsNullForNonsensicalEdge() {
        assertTrue(null == new EdgeCoordinate(1, 2, 500).getCellAt(Side.TOP));
    }
    public void getCellAtLeftReturnsNullForNonsensicalEdge() {
        assertTrue(null == new EdgeCoordinate(1, 2, 500).getCellAt(Side.LEFT));
    }
    public void getCellAtBottomReturnsNullForNonsensicalEdge() {
        assertTrue(null == new EdgeCoordinate(1, 2, 500).getCellAt(Side.BOTTOM));
    }
    public void getCellAtRightReturnsNullForNonsensicalEdge() {
        assertTrue(null == new EdgeCoordinate(1, 2, 500).getCellAt(Side.RIGHT));
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
    public void equalsDoesntEquateEdgesOfDifferentOrientation() {
        assertFalse(a.equals(a1));
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
