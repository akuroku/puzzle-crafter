
package ohha.puzzlecrafter.ui.editorwindow;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.SwingUtilities;

import ohha.puzzlecrafter.grid.Coordinate;
import ohha.puzzlecrafter.puzzles.Puzzle;


public class Editor extends MouseAdapter {
    
    private GridPane gridPane;
    private Puzzle puzzle;
    
    private Coordinate cursor;
    
    
    public Editor(GridPane gridPane) {
        this.gridPane = gridPane;
        this.puzzle = gridPane.getDrawer().getPuzzle();
        cursor = new Coordinate(0, 0);
    }
    
    
    public Coordinate getCursor() {
        return cursor;
    }
    public void setCursor(Coordinate c) {
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
        Coordinate newCursor = gridPane.getDrawer().pointToGridCellCoordinate(me.getPoint());
        
        if (newCursor == null) {
            setCursor(null);
            return;
        }
        
        if (SwingUtilities.isLeftMouseButton(me)) {
            if (newCursor.equals(cursor)) {
                cycleCellAtCursor(1);
            }
            setCursor(newCursor);
        } else if (SwingUtilities.isRightMouseButton(me)) {
            setCursor(newCursor);
            toggleGivenStatusAtCursor();
        }
    }
    
    
    /* a terrible hack
    this method gets its mouse wheel event from an anonymous mouse wheel
    listener attached to the frame window
    */
    public void mouseWheelMoved2(MouseWheelEvent mwe) {
        cycleCellAtCursor(mwe.getWheelRotation());
    }
}