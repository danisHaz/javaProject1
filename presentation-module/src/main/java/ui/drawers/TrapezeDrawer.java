package ui.drawers;

import app.IShape;
import app.Trapeze;

import javafx.scene.canvas.GraphicsContext;

public final class TrapezeDrawer implements Drawer {
    
    private static TrapezeDrawer drawer = null;
    private final String className = Trapeze.class.getName();

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void draw(IShape shape, GraphicsContext gc) throws Exception {
        Trapeze trapeze = (Trapeze) shape;
        int n = trapeze.getN();
        double[] xcoords = new double[n];
		double[] ycoords = new double[n];
		for (int i = 0; i < n; i++) {
			xcoords[i] = trapeze.getP()[i].getX(0) + centerX;
			ycoords[i] = trapeze.getP()[i].getX(1) + centerY;
		}

		gc.strokePolygon(xcoords, ycoords, n);
    }

    public static TrapezeDrawer createInstance() {
        if (drawer == null)
            drawer = new TrapezeDrawer();
        
        return drawer;
    }
}
