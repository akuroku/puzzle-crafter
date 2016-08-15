
package ohha.puzzlecrafter.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.text.NumberFormatter;


public class MainGUI implements Runnable {
    
    private JFrame frame;
    
    private final String[] puzzles;
    
    
    public MainGUI() {
        puzzles = new String[] {"Fillomino", "Sudoku"};
    }
    
    
    @Override
    public void run() {
        frame = new JFrame("Puzzlecrafter");                // set name displayed on window
        // frame.setPreferredSize(new Dimension(400, 300));    // set dimensions of window
        frame.setResizable(false);                          // make window unresizable
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        createComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    
    private void createComponents(Container container) {
        
        
        // Create puzzle selection bar on top
        
        JComboBox puzzleDropDown = new JComboBox(puzzles);
        puzzleDropDown.setSelectedIndex(1);
        
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
        
        
        
        // Create puzzle detail pane on middle
        
        // create formatter that only accepts positive ints
        NumberFormatter positiveIntFormatter = new NumberFormatter(NumberFormat.getInstance());
        positiveIntFormatter.setValueClass(Integer.class);
        positiveIntFormatter.setMinimum(1);
        positiveIntFormatter.setMaximum(Integer.MAX_VALUE);
        positiveIntFormatter.setAllowsInvalid(false);
        
        // Create sudoku settings pane
        JPanel sudokuSettingsPane;
        {
            // create entry boxes
            JFormattedTextField gridSizeField = new JFormattedTextField(positiveIntFormatter);
            JLabel gridSizeLabel = new JLabel("Grid size:", JLabel.RIGHT);
            gridSizeLabel.setToolTipText("Input a positive integer to specify the height and width of the grid in cells.");

            JFormattedTextField regionHeightField = new JFormattedTextField(positiveIntFormatter);
            JLabel regionHeightLabel = new JLabel("Region height:", JLabel.RIGHT);
            regionHeightLabel.setToolTipText("Input a positive integer to specify the height of a region in cells.");

            JFormattedTextField regionWidthField = new JFormattedTextField(positiveIntFormatter);
            JLabel regionWidthLabel = new JLabel("Region width:", JLabel.RIGHT);
            regionWidthLabel.setToolTipText("Input a positive integer to specify the width of a region in cells.");

            JFormattedTextField cellSizeField = new JFormattedTextField(positiveIntFormatter);
            JLabel cellSizeLabel = new JLabel("Cell size:", JLabel.RIGHT);
            cellSizeLabel.setToolTipText("Input a positive integer to specify the height and width of a cell in pixels.");

            sudokuSettingsPane = new JPanel(new GridLayout(4, 2, 5, 5)); // rows, columns, horizontal gap, vertical gap

            sudokuSettingsPane.add(gridSizeLabel);
            sudokuSettingsPane.add(gridSizeField);

            sudokuSettingsPane.add(regionHeightLabel);
            sudokuSettingsPane.add(regionHeightField);

            sudokuSettingsPane.add(regionWidthLabel);
            sudokuSettingsPane.add(regionWidthField);

            sudokuSettingsPane.add(cellSizeLabel);
            sudokuSettingsPane.add(cellSizeField);
        }
        
        // Create fillomino settings pane
        JPanel fillominoSettingsPane;
        {
            // create entry boxes
            JFormattedTextField gridHeightField = new JFormattedTextField(positiveIntFormatter);
            JLabel gridHeightLabel = new JLabel("Region height:", JLabel.RIGHT);
            gridHeightLabel.setToolTipText("Input a positive integer to specify the height of the grid in cells.");

            JFormattedTextField gridWidthField = new JFormattedTextField(positiveIntFormatter);
            JLabel gridWidthLabel = new JLabel("Region width:", JLabel.RIGHT);
            gridWidthLabel.setToolTipText("Input a positive integer to specify the width of the grid in cells.");

            JFormattedTextField cellSizeField = new JFormattedTextField(positiveIntFormatter);
            JLabel cellSizeLabel = new JLabel("Cell size:", JLabel.RIGHT);
            cellSizeLabel.setToolTipText("Input a positive integer to specify the height and width of a cell in pixels.");

            fillominoSettingsPane = new JPanel(new GridLayout(3, 2, 5, 5)); // rows, columns, horizontal gap, vertical gap

            fillominoSettingsPane.add(gridHeightLabel);
            fillominoSettingsPane.add(gridHeightField);

            fillominoSettingsPane.add(gridWidthLabel);
            fillominoSettingsPane.add(gridWidthField);

            fillominoSettingsPane.add(cellSizeLabel);
            fillominoSettingsPane.add(cellSizeField);
        }
        
        
        // Create container for different puzzle setting panes
        
        JPanel puzzleSettingsPane = new JPanel(new CardLayout());
        
        puzzleSettingsPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 10, 0, 10),              // outer empty buffer // top, left, bottom, right
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY),   // outline
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)         // inner empty buffer
        )));
        
        puzzleSettingsPane.add(fillominoSettingsPane, "Fillomino");
        puzzleSettingsPane.add(sudokuSettingsPane, "Sudoku");
        
        
        
        // Add panes to main window
        container.add(puzzleSelectionPane, BorderLayout.NORTH);
        container.add(puzzleSettingsPane);
        container.add(puzzleCreationPane, BorderLayout.SOUTH);
        
        
        
        // Create event listeners
        
        puzzleDropDown.addItemListener(new PuzzleDropDownListener(puzzleSettingsPane));
        
        
    }
    
    
    public JFrame getFrame() {
        return frame;
    }
}
