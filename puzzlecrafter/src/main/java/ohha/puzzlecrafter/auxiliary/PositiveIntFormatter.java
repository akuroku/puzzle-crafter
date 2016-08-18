
package ohha.puzzlecrafter.auxiliary;

import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;


/**
 * A formatter class to be used with a <code>JFormattedTextBox</code> to make it
 * only accept positive integer values. Currently not used anywhere.
 */
public class PositiveIntFormatter extends NumberFormatter {
    public PositiveIntFormatter() {
        super(NumberFormat.getInstance());
        super.setValueClass(Integer.class);
        super.setMinimum(1);
        super.setMaximum(Integer.MAX_VALUE);
        super.setAllowsInvalid(false);
    }
}
