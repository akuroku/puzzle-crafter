
package ohha.puzzlecrafter.ui;

import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JPanel;


public class PuzzleDropDownListener implements ItemListener {
    
    private JPanel cardPane;
    
    public PuzzleDropDownListener(JPanel cardPane) {
        this.cardPane = cardPane;
    }
    
    
    @Override
    public void itemStateChanged(ItemEvent ie) {
        CardLayout layout = (CardLayout) cardPane.getLayout();
        layout.show(cardPane, (String) ie.getItem());
    }
}
