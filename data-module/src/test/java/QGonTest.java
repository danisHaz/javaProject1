import static org.junit.Assert.assertTrue;

import org.junit.Test;

import app.Point2D;
import app.QGon;

public class QGonTest {
    
    private Point2D[] points;
    private QGon qgon;

    public QGonTest() {
        try {
            points = new Point2D[] {
                new Point2D(),
                new Point2D(1, 1),
                new Point2D(5, 1),
                new Point2D(5, -1)
            };
            qgon = new QGon(points);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void checkLength() {
        String testName = "qgon length";

        try {
            assertTrue(testName, String.format("%.2f", qgon.length()).equals("12.51"));
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(testName, false);
        }
    }

    @Test
    public void checkSquare() {
        String testName = "qgon square";

        try {
            assertTrue(testName, String.format("%.2f", qgon.square()).equals("7.00"));
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(testName, false);
        }
    }
}
