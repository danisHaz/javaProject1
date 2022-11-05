import static org.junit.Assert.assertTrue;

import org.junit.Test;

import app.NGon;
import app.Point2D;

public class NGonTest {
    private int n = 5;
    private Point2D[] points;
    private NGon ngon;

    public NGonTest() {
        try {
            points = new Point2D[] {
                new Point2D(1, 1),
                new Point2D(2, 1),
                new Point2D(3, 0),
                new Point2D(4, -2),
                new Point2D(0, 0)
            };
            ngon = new NGon(points);
        } catch (Exception e) {
            // pass
        }
    }

    @Test
    public void checkLength() {
        String testName = "ngon length";
        try {
            assertTrue(testName, String.format("%.4f", ngon.length()) == "10.5366");
        } catch (Exception e) {
            assertTrue(testName, false);
        }
    }
}
