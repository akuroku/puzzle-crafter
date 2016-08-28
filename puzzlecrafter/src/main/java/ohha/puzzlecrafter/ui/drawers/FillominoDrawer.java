
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
import ohha.puzzlecrafter.puzzles.Fillomino;
import ohha.puzzlecrafter.puzzles.Puzzle;
import ohha.puzzlecrafter.puzzles.Sudoku;


public class FillominoDrawer extends Drawer {
    
    public FillominoDrawer(Fillomino fillomino, int cellSize) {
        super(fillomino,
                1, 1, 1, 1,
                cellSize);
    }

    @Override
    public void drawCell(Graphics2D g2d, CellCoordinate c) {
        super.drawCell(g2d, c);
        
        if (!getPuzzle().getGrid().isCellUndetermined(c)) {
            drawNumber(g2d, c, getPuzzle().getGrid().getValueOfCellAt(c));
        }
    }

    @Override
    public void drawInternalEdge(Graphics2D g2d, CellCoordinate c, Side side) {
        if (getPuzzle().getGrid().getValueOfEdgeAt(c.getEdgeAt(side)) == Fillomino.NO_EDGE) {
            return;
        }
        
        Point topLeft = getTopLeftPixelCoordinateOfVertex(c);
        Point topRight = getTopLeftPixelCoordinateOfVertex(c.shiftX(1));
        Point bottomLeft = getTopLeftPixelCoordinateOfVertex(c.shiftY(1));
        Point bottomRight = getTopLeftPixelCoordinateOfVertex(c.shiftX(1).shiftY(1));

        
        if (getPuzzle().getGrid().getValueOfEdgeAt(c.getEdgeAt(side)) == Fillomino.THIN_DASHED_EDGE) {
            g2d.setStroke(new BasicStroke(Drawer.THIN_EDGE_THICKNESS));
            g2d.setColor(new Color(254, 254, 254));
            
            switch(side) {
                case TOP:
                    g2d.draw(new Line2D.Double(topLeft, topRight));
                    break;

                case LEFT:
                    g2d.draw(new Line2D.Double(topLeft, bottomLeft));
                    break;

                case BOTTOM:
                    g2d.draw(new Line2D.Double(bottomLeft, bottomRight));
                    break;

                case RIGHT:
                    g2d.draw(new Line2D.Double(topRight, bottomRight));
                    break;
            }
        }
        
        switch(getPuzzle().getGrid().getValueOfEdgeAt(c.getEdgeAt(side))) {
            case Fillomino.THICK_SOLID_EDGE: {
                g2d.setStroke(new BasicStroke(Drawer.THICK_EDGE_THICKNESS));
                break;
            }
            case Fillomino.THIN_DASHED_EDGE: {
                g2d.setStroke(new BasicStroke(Drawer.THIN_EDGE_THICKNESS,
                BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10f,
                new float[]{4f, 4f}, 0));
                break;
            }
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
    public Drawer duplicate(Puzzle puzzle) {
        return new FillominoDrawer((Fillomino) puzzle, getCellSize());
    }
    
    @Override
    public Drawer deepCopy() {
        return new FillominoDrawer((Fillomino) getPuzzle().deepCopy(), getCellSize());
    }
}
