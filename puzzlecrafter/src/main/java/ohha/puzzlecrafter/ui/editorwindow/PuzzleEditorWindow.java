
package ohha.puzzlecrafter.ui.editorwindow;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import ohha.puzzlecrafter.ui.drawers.Drawer;


/**
 * Acts as the window where puzzles are created and solved in.
 * Not yet finished.
 */
public class PuzzleEditorWindow implements Runnable {
    
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JMenuBar menuBar;
    
    private Drawer drawer;
    
    
    public PuzzleEditorWindow(Drawer drawer) {
        this.drawer = drawer;
    }
    
    
    private GridPane getActiveGridPane() {
        return (GridPane) tabbedPane.getSelectedComponent();
    }
    
    
    @Override
    public void run() {
        frame = new JFrame(drawer.getPuzzle().getName());   // set name displayed on window
        // frame.setPreferredSize(new Dimension(400, 300)); // set dimensions of window
        frame.setResizable(false);                          // make window unresizable
        
        createComponents(frame.getContentPane());
        
        drawer = null;  // don't keep the drawer references without need
        
        /* a terrible hack
        a mouse wheel listener attached on gridPane will remain dormant as I
        can't get the panel to get focus so I'm attaching the mouse wheel
        listener here and passing the event on
        */
        frame.addMouseWheelListener((MouseWheelEvent mwe) -> {
            mwe.consume();
            ((GridPane) tabbedPane.getSelectedComponent()).getEditor().mouseWheelMoved2(mwe);
        });
        
        frame.pack();
        frame.setVisible(true);
    }
    
    
    private void createComponents(Container container) {
        container.setLayout(new BorderLayout());
        
        
        // create tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setTabPlacement(JTabbedPane.TOP);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        tabbedPane.add("1", new GridPane(drawer, "1"));
        
        container.add(tabbedPane);
        
        
        // create menu bar
        menuBar = new JMenuBar();
        
        
        JMenu copyMenu = new JMenu("Tab");
        copyMenu.setMnemonic(KeyEvent.VK_T);
        
        
        JMenuItem closeButton = new JMenuItem("Close");
        closeButton.setMnemonic(KeyEvent.VK_C);
        closeButton.addActionListener((ActionEvent ae) -> {
            tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
            
            if (tabbedPane.getTabCount() == 0) {
                frame.dispose();
            }
        });
        copyMenu.add(closeButton);
        
        
        JMenuItem renameButton = new JMenuItem("Rename");
        renameButton.setMnemonic(KeyEvent.VK_R);
        renameButton.addActionListener((ActionEvent ae) -> {
            String code = (String) JOptionPane.showInputDialog(
                    frame,
                    "Enter a name for the tab:",
                    "Rename",
                    JOptionPane.PLAIN_MESSAGE);
            
            if (code == null || code.equals("")) {
                return;
            }
            
            tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), code);
            getActiveGridPane().setCode(code);
        });
        copyMenu.add(renameButton);
        
        
        JMenuItem copyButton = new JMenuItem("Copy");
        copyButton.addActionListener((ActionEvent ae) -> {
            copy();
        });
        copyMenu.add(copyButton);
        
        menuBar.add(copyMenu);
        
        container.add(menuBar, BorderLayout.NORTH);
    }
    
    
    private void copy() {
        GridPane copy = getActiveGridPane().makeCopy();
        tabbedPane.add(copy.getCode(), copy);
    }
}
