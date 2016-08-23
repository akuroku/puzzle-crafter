
package ohha.puzzlecrafter.auxiliary;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides methods to convert font sizes from pixels to points and vice versa.
 * Source: <a href="http://reeddesign.co.uk/test/points-pixels.html/"></a>
 */
public class FontSize {
    
    private static Map<Integer, Float> pixelsToPoints;
    
    
    public static float fromPixelsToPoints(int pixels) {
        if (pixelsToPoints == null) {
            initialise();
        }
        
        if (pixels < 8) {
            return pixelsToPoints.get(8);
        }
        if (pixels > 24) {
            return pixelsToPoints.get(24);
        }
        return pixelsToPoints.get(pixels);
    }
    
    private static void initialise() {
        Map<Integer, Float> pxToPt = new HashMap<>();
        pxToPt.put( 8, 12f);
        pxToPt.put( 9, 14f);
        pxToPt.put(10, 15f);
        pxToPt.put(11, 16f);
        pxToPt.put(12, 18f);
        pxToPt.put(13, 20f);
        pxToPt.put(14, 21f);
        pxToPt.put(15, 22f);
        pxToPt.put(16, 24f);
        pxToPt.put(17, 26f);
        pxToPt.put(18, 27f);
        pxToPt.put(19, 28f);
        pxToPt.put(20, 29f);
        pxToPt.put(21, 30f);
        pxToPt.put(22, 32f);
        pxToPt.put(23, 34f);
        pxToPt.put(24, 36f);
        
        pixelsToPoints = Collections.unmodifiableMap(pxToPt);
    }
}
