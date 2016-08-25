
package ohha.puzzlecrafter.grid;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class GridTest {
    
    private Grid grid;
    
    
    @Before
    public void setUp() {
        grid = new Grid(4, 3);
        
        grid.setValueOfCellAt(new CellCoordinate(0, 0), 1);
        grid.setValueOfCellAt(new CellCoordinate(2, 0), 2);
        
        grid.setValueOfCellAt(new CellCoordinate(0, 1), 1);
        grid.setValueOfCellAt(new CellCoordinate(1, 1), 2);
        
        grid.setValueOfCellAt(new CellCoordinate(0, 2), -1);
        grid.setValueOfCellAt(new CellCoordinate(2, 2), 3);
        
        grid.setValueOfCellAt(new CellCoordinate(0, 3), -1);
        grid.setValueOfCellAt(new CellCoordinate(1, 3), -1);
        /*
        1 0 2
        1 2 0
       -1 0 3
       -1-1 0
        */
    }
    
    
    @Test
    public void constructorInitialisesCorrectHeight() {
        assertEquals(4, grid.getHeight());
    }
    @Test
    public void constructorInitialisesCorrectWidth() {
        assertEquals(3, grid.getWidth());
    }
    
    
    @Test
    public void setValueAtCellAndGetValueAtCellMatch() {
        CellCoordinate c = new CellCoordinate(1, 1);
        grid.setValueOfCellAt(c, 10);
        
        assertEquals(10, grid.getValueOfCellAt(c));
    }
    @Test
    public void setValueAtEdgeAndGetValueAtEdgeMatchWithHorizontalEdge() {
        EdgeCoordinate e = new EdgeCoordinate(1, 1, EdgeCoordinate.HORIZONTAL_EDGE);
        grid.setValueOfEdgeAt(e, 10);
        
        assertEquals(10, grid.getValueOfEdgeAt(e));
    }
    @Test
    public void setValueAtEdgeAndGetValueAtEdgeMatchWithVerticalEdge() {
        EdgeCoordinate e = new EdgeCoordinate(1, 1, EdgeCoordinate.VERTICAL_EDGE);
        grid.setValueOfEdgeAt(e, 10);
        
        assertEquals(10, grid.getValueOfEdgeAt(e));
    }
    
    
    @Test
    public void undeterminedCellIsReportedAsUndetermined() {
        assertTrue(grid.isCellUndetermined(new CellCoordinate(1, 0)));
    }
    @Test
    public void filledCellIsNotReportedAsUndetermined() {
        assertFalse(grid.isCellUndetermined(new CellCoordinate(0, 0)));
    }
    @Test
    public void emptyCellIsNotReportedAsUndetermined() {
        assertFalse(grid.isCellUndetermined(new CellCoordinate(0, 2)));
    }
    
    
    @Test
    public void emptyCellIsReportedAsEmpty() {
        assertTrue(grid.isCellEmpty(new CellCoordinate(0, 2)));
    }
    @Test
    public void undeterminedCellIsNotReportedAsEmpty() {
        assertFalse(grid.isCellEmpty(new CellCoordinate(1, 0)));
    }
    @Test
    public void filledCellisNotReportedAsEmpty() {
        assertFalse(grid.isCellEmpty(new CellCoordinate(0, 0)));
    }
    
    
    @Test
    public void filledCellIsReportedAsFilled() {
        assertTrue(grid.isCellFilledIn(new CellCoordinate(0, 0)));
    }
    @Test
    public void undeterminedCellIsNotReportedAsFilled() {
        assertFalse(grid.isCellFilledIn(new CellCoordinate(1, 0)));
    }
    @Test
    public void emptyCellIsNotReportedAsFilled() {
        assertFalse(grid.isCellFilledIn(new CellCoordinate(0, 2)));
    }
    
    
    @Test
    public void nullCellIsNotReportedAsContained() {
        assertFalse(grid.containsCell(null));
    }
    @Test
    public void containedCellIsReportedAsContained() {
        assertTrue(grid.containsCell(new CellCoordinate(0, 0)));
        assertTrue(grid.containsCell(new CellCoordinate(2, 0)));
        assertTrue(grid.containsCell(new CellCoordinate(0, 3)));
        assertTrue(grid.containsCell(new CellCoordinate(2, 3)));
        assertTrue(grid.containsCell(new CellCoordinate(1, 1)));
    }
    @Test
    public void uncontainedCellWithPositiveCoordinatesIsNotReportedAsContained() {
        assertFalse(grid.containsCell(new CellCoordinate(3, 4)));
        assertFalse(grid.containsCell(new CellCoordinate(2, 5)));
        assertFalse(grid.containsCell(new CellCoordinate(3, 5)));
    }
    @Test
    public void uncontainedCellWithNegativeCoordinatesIsNotReportedAsContained() {
        assertFalse(grid.containsCell(new CellCoordinate(0, -1)));
        assertFalse(grid.containsCell(new CellCoordinate(-1, 0)));
        assertFalse(grid.containsCell(new CellCoordinate(-1, -1)));
    }
    
    
    @Test
    public void nullEdgeIsNotReportedAsContained() {
        assertFalse(grid.containsEdge(null));
    }
    @Test
    public void containedHorizontalEdgeIsReportedAsContained() {
        assertTrue(grid.containsEdge(new EdgeCoordinate(0, 0, EdgeCoordinate.HORIZONTAL_EDGE)));
        assertTrue(grid.containsEdge(new EdgeCoordinate(2, 0, EdgeCoordinate.HORIZONTAL_EDGE)));
        assertTrue(grid.containsEdge(new EdgeCoordinate(0, 4, EdgeCoordinate.HORIZONTAL_EDGE)));
        assertTrue(grid.containsEdge(new EdgeCoordinate(2, 4, EdgeCoordinate.HORIZONTAL_EDGE)));
        assertTrue(grid.containsEdge(new EdgeCoordinate(1, 1, EdgeCoordinate.HORIZONTAL_EDGE)));
    }
    @Test
    public void uncontainedHorizontalEdgeWithPositiveCoordinatesIsNotReportedAsContained() {
        assertFalse(grid.containsEdge(new EdgeCoordinate(3, 4, EdgeCoordinate.HORIZONTAL_EDGE)));
        assertFalse(grid.containsEdge(new EdgeCoordinate(2, 5, EdgeCoordinate.HORIZONTAL_EDGE)));
        assertFalse(grid.containsEdge(new EdgeCoordinate(3, 5, EdgeCoordinate.HORIZONTAL_EDGE)));
    }
    @Test
    public void uncontainedHorizontalEdgeWithNegativeCoordinatesIsNotReportedAsContained() {
        assertFalse(grid.containsEdge(new EdgeCoordinate(0, -1, EdgeCoordinate.HORIZONTAL_EDGE)));
        assertFalse(grid.containsEdge(new EdgeCoordinate(-1, 0, EdgeCoordinate.HORIZONTAL_EDGE)));
        assertFalse(grid.containsEdge(new EdgeCoordinate(-1, -1, EdgeCoordinate.HORIZONTAL_EDGE)));
    }
    
    @Test
    public void internalHorizontalEdgeIsReportedAsInternal() {
        assertTrue(grid.isInternalEdge(new EdgeCoordinate(0, 1, EdgeCoordinate.HORIZONTAL_EDGE)));
        assertTrue(grid.isInternalEdge(new EdgeCoordinate(2, 1, EdgeCoordinate.HORIZONTAL_EDGE)));
        assertTrue(grid.isInternalEdge(new EdgeCoordinate(0, 3, EdgeCoordinate.HORIZONTAL_EDGE)));
        assertTrue(grid.isInternalEdge(new EdgeCoordinate(2, 3, EdgeCoordinate.HORIZONTAL_EDGE)));
        assertTrue(grid.isInternalEdge(new EdgeCoordinate(1, 1, EdgeCoordinate.HORIZONTAL_EDGE)));
    }
    @Test
    public void horizontalEdgeOnTheFrameIsNotReportedAsInternal() {
        assertFalse(grid.isInternalEdge(new EdgeCoordinate(0, 0, EdgeCoordinate.HORIZONTAL_EDGE)));
        assertFalse(grid.isInternalEdge(new EdgeCoordinate(2, 0, EdgeCoordinate.HORIZONTAL_EDGE)));
        assertFalse(grid.isInternalEdge(new EdgeCoordinate(0, 4, EdgeCoordinate.HORIZONTAL_EDGE)));
        assertFalse(grid.isInternalEdge(new EdgeCoordinate(2, 4, EdgeCoordinate.HORIZONTAL_EDGE)));
    }
    @Test
    public void uncontainedHorizontalEdgeWithPositiveCoordinatesIsNotReportedAsInternal() {
        assertFalse(grid.containsEdge(new EdgeCoordinate(3, 4, EdgeCoordinate.HORIZONTAL_EDGE)));
        assertFalse(grid.containsEdge(new EdgeCoordinate(2, 5, EdgeCoordinate.HORIZONTAL_EDGE)));
        assertFalse(grid.containsEdge(new EdgeCoordinate(3, 5, EdgeCoordinate.HORIZONTAL_EDGE)));
    }
    @Test
    public void uncontainedHorizontalEdgeWithNegativeCoordinatesIsNotReportedAsInternal() {
        assertFalse(grid.containsEdge(new EdgeCoordinate(0, -1, EdgeCoordinate.HORIZONTAL_EDGE)));
        assertFalse(grid.containsEdge(new EdgeCoordinate(-1, 0, EdgeCoordinate.HORIZONTAL_EDGE)));
        assertFalse(grid.containsEdge(new EdgeCoordinate(-1, -1, EdgeCoordinate.HORIZONTAL_EDGE)));
    }
    
    @Test
    public void containedVerticalEdgeIsReportedAsContained() {
        assertTrue(grid.containsEdge(new EdgeCoordinate(0, 0, EdgeCoordinate.VERTICAL_EDGE)));
        assertTrue(grid.containsEdge(new EdgeCoordinate(3, 0, EdgeCoordinate.VERTICAL_EDGE)));
        assertTrue(grid.containsEdge(new EdgeCoordinate(0, 3, EdgeCoordinate.VERTICAL_EDGE)));
        assertTrue(grid.containsEdge(new EdgeCoordinate(3, 3, EdgeCoordinate.VERTICAL_EDGE)));
        assertTrue(grid.containsEdge(new EdgeCoordinate(1, 1, EdgeCoordinate.VERTICAL_EDGE)));
    }
    @Test
    public void uncontainedVerticalEdgeWithPositiveCoordinatesIsNotReportedAsContained() {
        assertFalse(grid.containsEdge(new EdgeCoordinate(4, 3, EdgeCoordinate.VERTICAL_EDGE)));
        assertFalse(grid.containsEdge(new EdgeCoordinate(3, 4, EdgeCoordinate.VERTICAL_EDGE)));
        assertFalse(grid.containsEdge(new EdgeCoordinate(4, 4, EdgeCoordinate.VERTICAL_EDGE)));
    }
    @Test
    public void uncontainedVerticalEdgeWithNegativeCoordinatesIsNotReportedAsContained() {
        assertFalse(grid.containsEdge(new EdgeCoordinate(0, -1, EdgeCoordinate.VERTICAL_EDGE)));
        assertFalse(grid.containsEdge(new EdgeCoordinate(-1, 0, EdgeCoordinate.VERTICAL_EDGE)));
        assertFalse(grid.containsEdge(new EdgeCoordinate(-1, -1, EdgeCoordinate.VERTICAL_EDGE)));
    }
    
    @Test
    public void internalVerticalEdgeIsReportedAsInternal() {
        assertTrue(grid.isInternalEdge(new EdgeCoordinate(1, 0, EdgeCoordinate.VERTICAL_EDGE)));
        assertTrue(grid.isInternalEdge(new EdgeCoordinate(2, 0, EdgeCoordinate.VERTICAL_EDGE)));
        assertTrue(grid.isInternalEdge(new EdgeCoordinate(1, 3, EdgeCoordinate.VERTICAL_EDGE)));
        assertTrue(grid.isInternalEdge(new EdgeCoordinate(2, 3, EdgeCoordinate.VERTICAL_EDGE)));
        assertTrue(grid.isInternalEdge(new EdgeCoordinate(1, 1, EdgeCoordinate.VERTICAL_EDGE)));
    }
    @Test
    public void verticalEdgeOnTheFrameIsNotReportedAsInternal() {
        assertFalse(grid.isInternalEdge(new EdgeCoordinate(0, 0, EdgeCoordinate.VERTICAL_EDGE)));
        assertFalse(grid.isInternalEdge(new EdgeCoordinate(3, 0, EdgeCoordinate.VERTICAL_EDGE)));
        assertFalse(grid.isInternalEdge(new EdgeCoordinate(0, 3, EdgeCoordinate.VERTICAL_EDGE)));
        assertFalse(grid.isInternalEdge(new EdgeCoordinate(3, 3, EdgeCoordinate.VERTICAL_EDGE)));
    }
    @Test
    public void uncontainedVerticalEdgeWithPositiveCoordinatesIsNotReportedAsInternal() {
        assertFalse(grid.containsEdge(new EdgeCoordinate(4, 3, EdgeCoordinate.VERTICAL_EDGE)));
        assertFalse(grid.containsEdge(new EdgeCoordinate(3, 4, EdgeCoordinate.VERTICAL_EDGE)));
        assertFalse(grid.containsEdge(new EdgeCoordinate(4, 4, EdgeCoordinate.VERTICAL_EDGE)));
    }
    @Test
    public void uncontainedVerticalEdgeWithNegativeCoordinatesIsNotReportedAsInternal() {
        assertFalse(grid.containsEdge(new EdgeCoordinate(0, -1, EdgeCoordinate.VERTICAL_EDGE)));
        assertFalse(grid.containsEdge(new EdgeCoordinate(-1, 0, EdgeCoordinate.VERTICAL_EDGE)));
        assertFalse(grid.containsEdge(new EdgeCoordinate(-1, -1, EdgeCoordinate.VERTICAL_EDGE)));
    }
    
    
    @Test
    public void getListOfCellCoordinatesReturnsCorrectAmountOfCells() {
        assertEquals(12, grid.getListOfCellCoordinates().size());
    }
    @Test
    public void getListOfCellCoordinatesReturnsCorrectCells() {
        Grid smallGrid = new Grid(2, 3);
        
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 2; y++) {
                assertTrue(smallGrid.getListOfCellCoordinates().contains(new CellCoordinate(x, y)));
            }
        }
    }
    
    @Test
    public void getListOfEdgeCoordinatesReturnsCorrectAmountOfEdges() {
        assertEquals(31, grid.getListOfEdgeCoordinates().size());
    }
    @Test
    public void getListOfEdgeCoordinatesReturnsCorrectEdges() {
        Grid smallGrid = new Grid(2, 3);
        
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                assertTrue(smallGrid.getListOfEdgeCoordinates().contains(new EdgeCoordinate(x, y, EdgeCoordinate.HORIZONTAL_EDGE)));
            }
        }
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 2; y++) {
                assertTrue(smallGrid.getListOfEdgeCoordinates().contains(new EdgeCoordinate(x, y, EdgeCoordinate.VERTICAL_EDGE)));
            }
        }
    }
    
    @Test
    public void getListOfInternalEdgeCoordinatesReturnsCorrectAmountOfEdges() {
        assertEquals(17, grid.getListOfInternalEdgeCoordinates().size());
    }
    @Test
    public void getListOfInternalEdgeCoordinatesReturnsCorrectEdges() {
        Grid smallGrid = new Grid(2, 3);
        
        for (int x = 0; x < 3; x++) {
            for (int y = 1; y < 2; y++) {
                assertTrue(smallGrid.getListOfEdgeCoordinates().contains(new EdgeCoordinate(x, y, EdgeCoordinate.HORIZONTAL_EDGE)));
            }
        }
        for (int x = 1; x < 3; x++) {
            for (int y = 0; y < 2; y++) {
                assertTrue(smallGrid.getListOfEdgeCoordinates().contains(new EdgeCoordinate(x, y, EdgeCoordinate.VERTICAL_EDGE)));
            }
        }
    }
    
    
    @Test
    public void deepCopyCopiesCorrectly() {
        Grid copy = grid.deepCopy();
        
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                CellCoordinate cell = new CellCoordinate(x, y);
                assertEquals(grid.getValueOfCellAt(cell), copy.getValueOfCellAt(cell));
            }
        }
    }
    @Test
    public void deepCopyCopiesDeep() {
        Grid copy = grid.deepCopy();
        grid.setValueOfCellAt(new CellCoordinate(0, 0), 20);
        assertEquals(1, copy.getValueOfCellAt(new CellCoordinate(0, 0)));
    }
}