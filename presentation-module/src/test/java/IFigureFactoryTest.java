import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ui.IfigureFactory;

public class IFigureFactoryTest {
    private String str = "NGon(n=3, p=(Point(dims=2, x=(0, 1)), Point(dims=2, x=(0, 1)), Point(dims=2, x=(0, 1))))";

    @Test
    public void checkFactory() {
        assertTrue("kek", IfigureFactory.create(str) != null);
    }
}
