import static org.junit.Assert.assertTrue;

import org.junit.Test;

import app.Point;

public class PointTest {
    
    private Point point;

    public PointTest() {
        try {
            point = new Point(5, new double[] {1, 2, 3, 4, 5});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkLength() {
        String testName = "point length";
        assertTrue(testName, String.format("%.4f", point.abs()).equals("7.4162"));
    }

    @Test
    public void checkAdd() {
        String testName = "point add";
        try {
            assertTrue(testName, String.format("%.4f", Point.add(point, point).abs()).equals("14.8324"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkSub() {
        String testName = "point sub";
        try {
            assertTrue(testName, String.format("%.2f", Point.sub(point, point).abs()).equals("0.00"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkMulti() {
        String testName = "point multi";
        try {
            assertTrue(testName, String.format("%.4f", Point.mult(point, 3).abs()).equals("22.2486"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
