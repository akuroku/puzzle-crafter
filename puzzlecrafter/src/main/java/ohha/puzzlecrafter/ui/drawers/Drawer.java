
package ohha.puzzlecrafter.ui.drawers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import ohha.puzzlecrafter.auxiliary.FontSize;
import ohha.puzzlecrafter.grid.Coordinate;
import ohha.puzzlecrafter.grid.Side;

import ohha.puzzlecrafter.puzzles.Puzzle;
import ohha.puzzlecrafter.ui.editorwindow.Editor;


public abstract class Drawer {
    
    public static final int MAX_CELL_SIZE = 100;
    public static final int DEFAULT_CELL_SIZE = 40;
    public static final int MIN_CELL_SIZE = 25;
    
    public static final int THIN_EDGE_THICKNESS = 1;
    public static final int THICK_EDGE_THICKNESS = 3;
    
    public static final Font DEFAULT_FONT = new Font("Arial", Font.TRUETYPE_FONT, 16);
    
    
    private Puzzle puzzle;
    
    private int topMargin;      // margin at the top in cells
    private int leftMargin;     // margin at the left in cells
    private int bottomMargin;   // margin at the bottom in cells
    private int rightMargin;    // margin at the right in cells
    
    private int cellSize;       // height and width of cell in pixels
    
    
    public Drawer(Puzzle puzzle,
            int topMargin, int leftMargin, int bottomMargin, int rightMargin,
            int cellSize) {
        this.puzzle = puzzle;
        
        this.topMargin = topMargin;
        this.leftMargin = leftMargin;
        this.bottomMargin = bottomMargin;
        this.rightMargin = rightMargin;
        
        this.cellSize = cellSize;
    }
    
    
    public Puzzle getPuzzle() {
        return puzzle;
    }
    
    public int getTopMargin() {
        return topMargin;
    }
    public int getLeftMargin() {
        return leftMargin;
    }
    public int getBottomMargin() {
        return bottomMargin;
    }
    public int getRightMargin() {
        return rightMargin;
    }
    
    public int getCellSize() {
        return cellSize;
    }
    public void setCellSize(int size) {
        cellSize = size;
    }
    
    
    public int getHeight() {
        return (topMargin + puzzle.getGrid().getHeight() + bottomMargin) * (cellSize + THIN_EDGE_THICKNESS) - THIN_EDGE_THICKNESS;
    }
    public int getWidth() {
        return (leftMargin + puzzle.getGrid().getWidth() + rightMargin) * (cellSize + THIN_EDGE_THICKNESS) - THIN_EDGE_THICKNESS;
    }
    
    
    public float getFontSizeInPoints() {
        return FontSize.fromPixelsToPoints(cellSize);
    }
    
    
    public Point getTopLeftPixelCoordinateOfGridCell(Coordinate c) {
        return new Point(
                (leftMargin + c.getX()) * (cellSize + THIN_EDGE_THICKNESS),
                (topMargin + c.getY()) * (cellSize + THIN_EDGE_THICKNESS)
        );
    }
    public Point getBottomLeftPixelCoordinateOfGridCell(Coordinate c) {
        return new Point(
                (leftMargin + c.getX()) * (cellSize + THIN_EDGE_THICKNESS),
                (topMargin + c.getY()) * (cellSize + THIN_EDGE_THICKNESS) + cellSize
        );
    }
    
    public Point getTopLeftPixelCoordinateOfVertex(Coordinate c) {
        return new Point(
                (leftMargin + c.getX()) * (cellSize + THIN_EDGE_THICKNESS) - (THIN_EDGE_THICKNESS + 1) / 2,
                (topMargin + c.getY()) * (cellSize + THIN_EDGE_THICKNESS) - (THIN_EDGE_THICKNESS + 1) / 2
        );
    }
    
    
    public Coordinate pointToGridCellCoordinate(Point point) {
        int period = cellSize + THIN_EDGE_THICKNESS;
        
        int x = point.x - leftMargin * period;
        int y = point.y - topMargin * period;
        
        if ( x < 0 || y < 0 || (x + 1) % period == 0 || (y + 1) % period == 0) {
            return null;
        }
        
        Coordinate c = new Coordinate(x / period, y / period);
        
        if (!getPuzzle().getGrid().contains(c)) {
            return null;
        }
        
        return c;
    }
    
    
    public void drawCursor(Graphics2D g2d, Coordinate c) {
        g2d.setColor(new Color(200, 200, 200, 127));
        g2d.fill(new Rectangle(getTopLeftPixelCoordinateOfGridCell(c), new Dimension(cellSize, cellSize)));
    }
    
    public abstract void drawCell(Graphics2D g2d, Coordinate c);
    
    public abstract void drawInternalEdge(Graphics2D g2d, Coordinate c, Side side);
    
    public abstract void drawFramingEdges(Graphics2D g2d);
    
    public abstract void drawVertex(Graphics2D g2d, Coordinate c);
}
