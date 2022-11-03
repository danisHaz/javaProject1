package ui.drawers;

import app.IShape;
import app.Rectangle;

import javafx.scene.canvas.GraphicsContext;

public final class RectangleDrawer implements Drawer {
    
    private static RectangleDrawer drawer = null;
    private final String className = Rectangle.class.getName();

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void draw(IShape shape, GraphicsContext gc) throws Exception {
        Rectangle rectangle = (Rectangle) shape;
        int n = rectangle.getN();
        double[] xcoords = new double[n];
		double[] ycoords = new double[n];
		for (int i = 0; i < n; i++) {
			xcoords[i] = rectangle.getP()[i].getX(0) + centerX;
			ycoords[i] = rectangle.getP()[i].getX(1) + centerY;
		}

		gc.strokePolygon(xcoords, ycoords, n);
    }

    public static RectangleDrawer createInstance() {
        if (drawer == null)
            drawer = new RectangleDrawer();
        
        return drawer;
    }
}
