
package ohha.puzzlecrafter.ui.drawers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import ohha.puzzlecrafter.grid.Coordinate;
import ohha.puzzlecrafter.grid.Side;

import ohha.puzzlecrafter.puzzles.Sudoku;


public class SudokuDrawer extends Drawer {
    
    public SudokuDrawer(Sudoku sudoku, int cellSize) {
        super(sudoku,
                1, 1, 1, 1,
                cellSize);
    }
    
    
    @Override
    public void drawCell(Graphics2D g2d, Coordinate c) {
        
        int value = getPuzzle().getGrid().getValueOfCellAt(c);
        
        if (value != 0) {
            Point bottomLeft = getBottomLeftPixelCoordinateOfGridCell(c);
            
            if (getPuzzle().getGivens().contains(c)) {
                g2d.setColor(Color.black);
            } else {
                g2d.setColor(Color.gray);
            }
            g2d.setFont(DEFAULT_FONT.deriveFont(getFontSizeInPoints()));
        
            g2d.drawString(value + "", bottomLeft.x, bottomLeft.y);
        }
    }
    
    
    @Override
    public void drawInternalEdge(Graphics2D g2d, Coordinate c, Side side) {
        Point topLeft = getTopLeftPixelCoordinateOfVertex(c);
        Point topRight = getTopLeftPixelCoordinateOfVertex(c.shiftX(1));
        Point bottomLeft = getTopLeftPixelCoordinateOfVertex(c.shiftY(1));
        Point bottomRight = getTopLeftPixelCoordinateOfVertex(c.shiftX(1).shiftY(1));
        
        if (((Sudoku) getPuzzle()).getPartition().getRegionOf(c).contains(c.getNeighbour(side))) {
            g2d.setStroke(new BasicStroke(THIN_EDGE_THICKNESS));
        } else {
            g2d.setStroke(new BasicStroke(THICK_EDGE_THICKNESS));
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
        Coordinate c = new Coordinate(0, 0);
        Point topLeft = getTopLeftPixelCoordinateOfVertex(c);
        Point topRight = getTopLeftPixelCoordinateOfVertex(c.shiftX(getPuzzle().getGrid().getWidth()));
        Point bottomLeft = getTopLeftPixelCoordinateOfVertex(c.shiftY(getPuzzle().getGrid().getHeight()));
        
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(THICK_EDGE_THICKNESS));
        
        g2d.draw(new Rectangle(topLeft, new Dimension(topRight.x - topLeft.x, bottomLeft.y - topLeft.y)));
    }
    

    @Override
    public void drawVertex(Graphics2D g2d, Coordinate c) {
    }
}
