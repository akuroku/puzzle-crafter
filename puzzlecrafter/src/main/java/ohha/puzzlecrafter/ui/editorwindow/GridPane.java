
package ohha.puzzlecrafter.ui.editorwindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import ohha.puzzlecrafter.grid.CellCoordinate;
import ohha.puzzlecrafter.grid.Side;

import ohha.puzzlecrafter.ui.drawers.Drawer;


/**
 * Acts as the panel where the actual puzzle is drawn into.
 * Not yet finished.
 */
public class GridPane extends JPanel {
    
    private String code;
    private int copies;
    
    private Drawer drawer;
    private Editor editor;
    
    public GridPane(Drawer drawer, String code) {
        super();
        
        this.code = code;
        this.copies = 0;
        
        this.drawer = drawer;
        this.editor = new Editor(this);
        this.addMouseListener(editor);
        
        //this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        this.setSize(new Dimension(drawer.getWidth(), drawer.getHeight()));
        
        this.setBackground(Color.white);
        this.setOpaque(true);
    }
    
    
    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
    
    public int getCopies() {
        return copies;
    }
    public int incrementAndGetCopies() {
        return ++copies;
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
        for (CellCoordinate c : drawer.getPuzzle().getGrid().getListOfCellCoordinates()) {
            drawer.drawCell(g2d, c);
        }
        
        
        // then draw cursor
        if (editor.getCursor() != null) {
            drawer.drawCursor(g2d, editor.getCursor());
        }
        
        
        // then draw internal edges
        for (CellCoordinate c : drawer.getPuzzle().getGrid().getListOfCellCoordinates()) {
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
        for (CellCoordinate c : drawer.getPuzzle().getGrid().getListOfCellCoordinates()) {
            drawer.drawVertex(g2d, c);
        }
    }
    
    
    public GridPane makeCopy() {
        return new GridPane(drawer.deepCopy(), getCode() + "." + incrementAndGetCopies());
    }
}
