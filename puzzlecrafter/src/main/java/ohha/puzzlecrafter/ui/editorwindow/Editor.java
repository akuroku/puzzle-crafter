
package ohha.puzzlecrafter.ui.editorwindow;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.SwingUtilities;

import ohha.puzzlecrafter.grid.CellCoordinate;
import ohha.puzzlecrafter.puzzles.Puzzle;


public class Editor extends MouseAdapter {
    
    private GridPane gridPane;
    private Puzzle puzzle;
    
    private CellCoordinate cursor;
    
    
    public Editor(GridPane gridPane) {
        this.gridPane = gridPane;
        this.puzzle = gridPane.getDrawer().getPuzzle();
    }
    
    
    public CellCoordinate getCursor() {
        return cursor;
    }
    public void setCursor(CellCoordinate c) {
        cursor = c;
        
        gridPane.repaint();
        gridPane.revalidate();
    }
    
    
    private void cycleCellAtCursor(int amount) {
        if (cursor == null) {
            return;
        }
        
        puzzle.cycleValueOfCell(cursor, amount);
        gridPane.repaint();
        gridPane.revalidate();
    }
    
    private void toggleGivenStatusAtCursor() {
        if (cursor == null) {
            return;
        }
        
        if (puzzle.isGiven(cursor)) {
            puzzle.setAsNotGiven(cursor);
        } else {
            puzzle.setAsGiven(cursor);
        }
        
        gridPane.repaint();
        gridPane.revalidate();
    }
    
    
    @Override
    public void mouseClicked(MouseEvent me) {
        CellCoordinate newCursor = gridPane.getDrawer().pointToGridCellCoordinate(me.getPoint());
        
        setCursor(newCursor);
        
        if (SwingUtilities.isRightMouseButton(me)) {
            toggleGivenStatusAtCursor();
        }
    }
    
    
    /* a terrible hack
    this method gets its mouse wheel event from an anonymous mouse wheel
    listener attached to the frame window
    */
    public void mouseWheelMoved2(MouseWheelEvent mwe) {
        if (puzzle.getGivens().contains(cursor)) {
            return;
        }
        cycleCellAtCursor(mwe.getWheelRotation());
    }
}