
package ohha.puzzlecrafter.ui.mainwindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import ohha.puzzlecrafter.grid.Grid;
import ohha.puzzlecrafter.puzzles.Fillomino;
import ohha.puzzlecrafter.puzzles.Puzzle;
import ohha.puzzlecrafter.puzzles.Sudoku;
import ohha.puzzlecrafter.ui.drawers.Drawer;
import ohha.puzzlecrafter.ui.drawers.FillominoDrawer;
import ohha.puzzlecrafter.ui.drawers.SudokuDrawer;
import ohha.puzzlecrafter.ui.editorwindow.PuzzleEditorWindow;


/**
 * Acts as the main window of the program, from which individual puzzle styles
 * are created.
 * The layout design is completed, but as the puzzle editor itself is
 * unfinished, the puzzle creation functionality is yet lacking.
 * <p>
 * Not yet finished.
 */
public class MainWindow implements Runnable {
    
    private JFrame frame;
    
    private JComboBox puzzleDropDown;
    private JSpinner gridHeightSpinner;
    private JSpinner gridWidthSpinner;
    private JSpinner cellSizeSpinner;
    
    
    @Override
    public void run() {
        frame = new JFrame("Puzzlecrafter");                // set name displayed on window
        // frame.setPreferredSize(new Dimension(400, 300)); // set dimensions of window
        frame.setResizable(false);                          // make window unresizable
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        createComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    
    private void createComponents(Container container) {
        
        UIManager.put("Spinner.editorAlignment", JTextField.LEFT);  // set spinner values on the left
        
        
        // Create puzzle selection bar on top
        
        puzzleDropDown = new JComboBox(Puzzle.STYLES);
        puzzleDropDown.setSelectedIndex(0);
        
        JLabel selectPuzzleLabel = new JLabel("Select puzzle style:", JLabel.CENTER);
        selectPuzzleLabel.setLabelFor(puzzleDropDown);
        
        JPanel puzzleSelectionPane = new JPanel(new GridLayout(1, 2, 50, 0));   // rows, columns, horizontal gap, vertical gap
        
        puzzleSelectionPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // top, left, bottom, right

        puzzleSelectionPane.add(selectPuzzleLabel);     // text
        puzzleSelectionPane.add(puzzleDropDown);        // dropdown menu
        
        
        
        // Create puzzle creation button on bottom
        
        JButton createPuzzleButton = new JButton("Create");
        
        JPanel puzzleCreationPane = new JPanel(new FlowLayout());
        
        puzzleCreationPane.setBorder(BorderFactory.createEmptyBorder(7, 20, 10, 20)); // top, left, bottom, right
        
        puzzleCreationPane.add(createPuzzleButton);
        
        

        // Create puzzle settings pane
        // todo: make one grid dimension spinner inactive and copy the other when puzzle style requires square grid
        
        JPanel puzzleSettingsPane = new JPanel(new GridLayout(3, 2, 5, 5));
        
        puzzleSettingsPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 10, 0, 10),              // outer empty buffer // top, left, bottom, right
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY),   // outline
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)         // inner empty buffer
        )));
        
        gridHeightSpinner = new JSpinner(new SpinnerNumberModel(Grid.DEFAULT_SIZE, Grid.MIN_SIZE, Grid.MAX_SIZE, 1));   // default, min, max, step
        JLabel gridHeightLabel = new JLabel("Grid height:", JLabel.RIGHT);
        gridHeightLabel.setToolTipText("Input an integer between " + Grid.MIN_SIZE + " and " + Grid.MAX_SIZE + " to specify the height of the grid in cells.");
        
        gridWidthSpinner = new JSpinner(new SpinnerNumberModel(Grid.DEFAULT_SIZE, Grid.MIN_SIZE, Grid.MAX_SIZE, 1));   // default, min, max, step
        JLabel gridWidthLabel = new JLabel("Grid width:", JLabel.RIGHT);
        gridWidthLabel.setToolTipText("Input an integer between " + Grid.MIN_SIZE + " and " + Grid.MAX_SIZE + " to specify the width of the grid in cells.");
        
        cellSizeSpinner = new JSpinner(new SpinnerNumberModel(Drawer.DEFAULT_CELL_SIZE, Drawer.MIN_CELL_SIZE, Drawer.MAX_CELL_SIZE, 1));   // default, min, max, step
        JLabel cellSizeLabel = new JLabel("Cell size:", JLabel.RIGHT);
        cellSizeLabel.setToolTipText("Input an integer between " + Drawer.MIN_CELL_SIZE + " and " + Drawer.MAX_CELL_SIZE + " to specify the height and width of a cell in pixels.");
        
        puzzleSettingsPane.add(gridHeightLabel);
        puzzleSettingsPane.add(gridHeightSpinner);
        
        puzzleSettingsPane.add(gridWidthLabel);
        puzzleSettingsPane.add(gridWidthSpinner);
        
        puzzleSettingsPane.add(cellSizeLabel);
        puzzleSettingsPane.add(cellSizeSpinner);
        
        
        
        // Add panes to main window
        
        container.add(puzzleSelectionPane, BorderLayout.NORTH);
        container.add(puzzleSettingsPane, BorderLayout.CENTER);
        container.add(puzzleCreationPane, BorderLayout.SOUTH);
        
        
        
        // Create event listeners
        
        puzzleDropDown.addItemListener((ItemEvent ie) -> {
            switch ((String) ie.getItem()) {
                case "Sudoku": {
                    gridHeightLabel.setText("Grid size:");
                    gridWidthLabel.setText("Region width:");
                    break;
                }
                case "Fillomino": {
                    gridHeightLabel.setText("Grid height:");
                    gridWidthLabel.setText("Grid width:");
                    break;
                }
                default:
                    break;
            }
        });
        
        createPuzzleButton.addActionListener((ActionEvent ae) -> {
            switch((String) puzzleDropDown.getSelectedItem()) {
                case "Fillomino": {
                    makeFillomino();
                    break;
                }
                case "Sudoku": {
                    makeSudoku();
                    break;
                }
                default: {
                    JOptionPane.showMessageDialog(frame, "there is an error?", "error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    private void makeFillomino() {
        SwingUtilities.invokeLater(new PuzzleEditorWindow(
                new FillominoDrawer(
                        new Fillomino((int) gridHeightSpinner.getValue(), (int) gridWidthSpinner.getValue()), 
                        (int) cellSizeSpinner.getValue()
                )
        ));
    }
    
    private void makeSudoku() {
        int gridSize = (int) gridHeightSpinner.getValue();
        int regionWidth = (int) gridWidthSpinner.getValue();
        int regionHeight = gridSize / regionWidth;
        
        if (gridSize != regionHeight * regionWidth) {
            JOptionPane.showMessageDialog(frame, "Region width doesn't divide grid size evenly!");
            return;
        }
        
        SwingUtilities.invokeLater(new PuzzleEditorWindow(
                new SudokuDrawer(
                        new Sudoku(gridSize, regionHeight, regionWidth), 
                        (int) cellSizeSpinner.getValue()
                )
        ));
    }
    
    public JFrame getFrame() {
        return frame;
    }
}
