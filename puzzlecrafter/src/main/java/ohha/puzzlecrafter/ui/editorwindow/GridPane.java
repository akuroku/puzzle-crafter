
package ohha.puzzlecrafter.ui.editorwindow;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.border.BevelBorder;

import ohha.puzzlecrafter.puzzles.Puzzle;


/**
 * Acts as the panel where the actual puzzle is drawn into.
 * Not yet finished.
 */
public class GridPane extends JLayeredPane {
    
    private Puzzle puzzle;
    
    private int gridHeight; // grid height in cells
    private int gridWidth;  // grid width in cells
    private int cellSize;   // cell width and height in pixels
    
    private int topMargin;      // margin at the top in cells
    private int leftMargin;     // margin at the left in cells
    private int bottomMargin;   // margin at the bottom in cells
    private int rightMargin;    // margin at the right in cells
    
    
    public GridPane(Puzzle puzzle, int topMargin, int leftMargin, int bottomMargin, int rightMargin, int cellSize) {
        super();
        
        this.puzzle = puzzle;
        
        this.gridHeight = puzzle.getGrid().getHeight();
        this.gridWidth = puzzle.getGrid().getWidth();
        this.cellSize = cellSize;
        
        this.topMargin = topMargin;
        this.leftMargin = leftMargin;
        this.bottomMargin = bottomMargin;
        this.rightMargin = rightMargin;
        
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        this.setSize(
                (leftMargin + gridWidth + rightMargin) * (cellSize + 1) - 1,
                (topMargin + gridHeight + bottomMargin) * (cellSize + 1) - 1
        );    //width, height
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       
        
        
        
        
        // Draw Text
        g.drawString("This is my custom Panel!",10,20);
    }  
}
