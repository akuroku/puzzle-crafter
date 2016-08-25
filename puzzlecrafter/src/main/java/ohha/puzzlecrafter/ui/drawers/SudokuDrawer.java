
package ohha.puzzlecrafter.ui.drawers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import ohha.puzzlecrafter.grid.CellCoordinate;
import ohha.puzzlecrafter.grid.Grid;
import ohha.puzzlecrafter.grid.Side;
import ohha.puzzlecrafter.puzzles.Sudoku;


public class SudokuDrawer extends Drawer {
    
    public SudokuDrawer(Sudoku sudoku, int cellSize) {
        super(sudoku,
                1, 1, 1, 1,
                cellSize);
    }
    
    
    @Override
    public void drawCell(Graphics2D g2d, CellCoordinate c) {
        
        int value = getPuzzle().getGrid().getValueOfCellAt(c);
        
        if (value != Grid.UNDETERMINED_CELL) {
            Point bottomLeft = getBottomLeftPixelCoordinateOfGridCell(c);
            
            if (getPuzzle().getGivens().contains(c)) {
                Point topLeft = getTopLeftPixelCoordinateOfGridCell(c);
                
                g2d.setColor(new Color(230, 230, 230));
                g2d.fill(new Ellipse2D.Double(topLeft.x, topLeft.y, getCellSize(), getCellSize()));
            }
                
            g2d.setColor(Color.black);
            g2d.setFont(Drawer.DEFAULT_FONT.deriveFont(getFontSizeInPoints(value)));
        
            g2d.drawString(value + "", bottomLeft.x, bottomLeft.y);
        }
    }
    
    
    @Override
    public void drawInternalEdge(Graphics2D g2d, CellCoordinate c, Side side) {
        Point topLeft = getTopLeftPixelCoordinateOfVertex(c);
        Point topRight = getTopLeftPixelCoordinateOfVertex(c.shiftX(1));
        Point bottomLeft = getTopLeftPixelCoordinateOfVertex(c.shiftY(1));
        Point bottomRight = getTopLeftPixelCoordinateOfVertex(c.shiftX(1).shiftY(1));
        
        if (getPuzzle().getPartition().getRegionOf(c).contains(c.getNeighbour(side))) {
            g2d.setStroke(new BasicStroke(Drawer.THIN_EDGE_THICKNESS));
        } else {
            g2d.setStroke(new BasicStroke(Drawer.THICK_EDGE_THICKNESS));
        }
        
        g2d.setColor(Color.black);
        
        switch(side) {
            case TOP: {
                g2d.draw(new Line2D.Double(topLeft, topRight));
                return;
            }
            case LEFT: {
                g2d.draw(new Line2D.Double(topLeft, bottomLeft));
                return;
            }
            case BOTTOM: {
                g2d.draw(new Line2D.Double(bottomLeft, bottomRight));
                return;
            }
            case RIGHT: {
                g2d.draw(new Line2D.Double(topRight, bottomRight));
                return;
            }
        }
    }
    
    
    @Override
    public void drawFramingEdges(Graphics2D g2d) {
        CellCoordinate c = new CellCoordinate(0, 0);
        Point topLeft = getTopLeftPixelCoordinateOfVertex(c);
        Point topRight = getTopLeftPixelCoordinateOfVertex(c.shiftX(getPuzzle().getGrid().getWidth()));
        Point bottomLeft = getTopLeftPixelCoordinateOfVertex(c.shiftY(getPuzzle().getGrid().getHeight()));
        
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(Drawer.THICK_EDGE_THICKNESS));
        
        g2d.draw(new Rectangle(topLeft, new Dimension(topRight.x - topLeft.x, bottomLeft.y - topLeft.y)));
    }
    

    @Override
    public void drawVertex(Graphics2D g2d, CellCoordinate c) {
    }
    
    
    @Override
    public Drawer deepCopy() {
        return new SudokuDrawer((Sudoku) getPuzzle().deepCopy(), getCellSize());
    }
}
