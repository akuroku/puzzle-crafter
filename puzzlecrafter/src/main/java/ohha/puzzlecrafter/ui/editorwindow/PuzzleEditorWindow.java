
package ohha.puzzlecrafter.ui.editorwindow;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JFrame;
import ohha.puzzlecrafter.ui.drawers.Drawer;


/**
 * Acts as the window where puzzles are created and solved in.
 * Not yet finished.
 */
public class PuzzleEditorWindow implements Runnable {
    
    private JFrame frame;
    private GridPane gridPane;
    
    private Drawer drawer;
    
    
    public PuzzleEditorWindow(Drawer drawer) {
        this.drawer = drawer;
    }
    
    
    @Override
    public void run() {
        frame = new JFrame(drawer.getPuzzle().getName());   // set name displayed on window
        // frame.setPreferredSize(new Dimension(400, 300)); // set dimensions of window
        frame.setResizable(false);                          // make window unresizable
        
        
        
        createComponents(frame.getContentPane());
        
        /* a terrible hack
        a mouse wheel listener attached on gridPane will remain dormant as I
        can't get the panel to get focus so I'm attaching the mouse wheel
        listener here and passing the event on
        */
        frame.addMouseWheelListener((MouseWheelEvent mwe) -> {
            mwe.consume();
            gridPane.getEditor().mouseWheelMoved2(mwe);
        });
        
        frame.pack();
        frame.setVisible(true);
    }
    
    
    private void createComponents(Container container) {
        container.setLayout(new GridBagLayout());
        
        
        // create grid pane
        gridPane = new GridPane(drawer);
        
        GridBagConstraints gridPaneConstraints = new GridBagConstraints();
        gridPaneConstraints.gridx = 0;
        gridPaneConstraints.gridy = 0;
        gridPaneConstraints.weightx = 1.0;
        gridPaneConstraints.weighty = 1.0;
        
        container.add(gridPane, gridPaneConstraints);
        
        
        
        // create value dropdown
        // JComboBox valueDropDown = new JComboBox()
        
        
    }
}
