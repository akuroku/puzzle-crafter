
package ohha.puzzlecrafter.ui.editorwindow;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.SwingUtilities;

import ohha.puzzlecrafter.grid.Coordinate;


public class Editor extends MouseAdapter {
    
    private GridPane gridPane;
    
    private Coordinate cursor;
    
    
    public Editor(GridPane gridPane) {
        this.gridPane = gridPane;
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
        
        gridPane.getDrawer().getPuzzle().cycleValueOfCell(cursor, amount);
        gridPane.repaint();
        gridPane.revalidate();
    }
    
    
    @Override
    public void mouseClicked(MouseEvent me) {
        Coordinate newCursor = gridPane.getDrawer().pointToGridCellCoordinate(me.getPoint());
        
        System.out.println("nwn");
        
        if (!(newCursor.equals(cursor))) {
            setCursor(newCursor);
            return;
        }
        
        if (SwingUtilities.isLeftMouseButton(me)) {
            cycleCellAtCursor(1);
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
