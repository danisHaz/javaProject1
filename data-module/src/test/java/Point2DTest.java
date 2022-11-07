import static org.junit.Assert.assertTrue;

import org.junit.Test;

import app.Point2D;

public class Point2DTest {
    private Point2D point;

    public Point2DTest() {
        try {
            point = new Point2D(new double[] {1, 3});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkRot() {
        String testName = "point2D length";
        assertTrue(testName, String.format("%.4f", point.abs()).equals("3.1623"));

        try {
            point = Point2D.rot(point, Math.PI);
            assertTrue(testName, String.format("%.4f", Point2D.add(point, point).abs()).equals("6.3246"));
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(testName, false);
        }
    }
}
