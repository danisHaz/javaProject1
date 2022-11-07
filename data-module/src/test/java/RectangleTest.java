import static org.junit.Assert.assertTrue;

import org.junit.Test;

import app.Point2D;
import app.Rectangle;

public class RectangleTest {
    
    private Rectangle rectangle;
    private Point2D[] points;

    public RectangleTest() {
        try {
            points = new Point2D[] {
                new Point2D(),
                new Point2D(0, 10),
                new Point2D(10, 10),
                new Point2D(10, 0)
            };
            rectangle = new Rectangle(points);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkLength() {
        String testName = "rectangle length";
        try {
            assertTrue(testName, String.format("%.2f", rectangle.length()).equals("40.00"));
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(testName, false);
        }
    }

    @Test
    public void checkSquare() {
        String testName = "rectangle square";
        try {
            assertTrue(testName, String.format("%.2f", rectangle.square()).equals("100.00"));
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(testName, false);
        }
    }
}
