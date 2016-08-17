
package ohha.puzzlecrafter.auxiliary;

import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;


public class PositiveIntFormatter extends NumberFormatter {
    public PositiveIntFormatter() {
        super(NumberFormat.getInstance());
        super.setValueClass(Integer.class);
        super.setMinimum(1);
        super.setMaximum(Integer.MAX_VALUE);
        super.setAllowsInvalid(false);
    }
}
