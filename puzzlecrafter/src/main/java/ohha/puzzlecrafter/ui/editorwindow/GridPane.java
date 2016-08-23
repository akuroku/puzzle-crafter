
package ohha.puzzlecrafter.ui.editorwindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import ohha.puzzlecrafter.grid.Coordinate;
import ohha.puzzlecrafter.grid.Side;

import ohha.puzzlecrafter.ui.drawers.Drawer;


/**
 * Acts as the panel where the actual puzzle is drawn into.
 * Not yet finished.
 */
public class GridPane extends JPanel {
    
    private Drawer drawer;
    private Editor editor;
    
    public GridPane(Drawer drawer) {
        super();
        
        this.drawer = drawer;
        this.editor = new Editor(this);
        this.addMouseListener(editor);
        
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        this.setSize(new Dimension(drawer.getWidth(), drawer.getHeight()));
        
        this.setBackground(Color.white);
        this.setOpaque(true);
    }
    
    
    public Drawer getDrawer() {
        return drawer;
    }
    
    
    public void setEditor(Editor editor) {
        this.editor = editor;
    }
    public Editor getEditor() {
        return editor;
    }
    
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(drawer.getWidth(), drawer.getHeight());
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        
        g2d.setRenderingHints(
                new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB)
        );
        
        
        // first draw cell content
        for (Coordinate c : drawer.getPuzzle().getGrid().getListOfCoordinates()) {
            drawer.drawCell(g2d, c);
        }
        
        
        // then draw cursor
        if (editor.getCursor() != null) {
            drawer.drawCursor(g2d, editor.getCursor());
        }
        
        
        // then draw internal edges
        for (Coordinate c : drawer.getPuzzle().getGrid().getListOfCoordinates()) {
            if (c.getX() != 0) {
                drawer.drawInternalEdge(g2d, c, Side.LEFT);
            }
            if (c.getY() != 0) {
                drawer.drawInternalEdge(g2d, c, Side.TOP);
            }
        }
        
        
        // then draw the framing edges
        drawer.drawFramingEdges(g2d);
        
        
        // then draw the vertices
        for (Coordinate c : drawer.getPuzzle().getGrid().getListOfCoordinates()) {
            drawer.drawVertex(g2d, c);
        }
    }  
}
