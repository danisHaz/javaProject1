package ui.drawers;

import app.IShape;
import app.NGon;

import javafx.scene.canvas.GraphicsContext;

public final class NGonDrawer implements Drawer {
    
    private static NGonDrawer drawer = null;
    private final String className = NGon.class.getName();

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void draw(IShape shape, GraphicsContext gc) throws Exception {
        NGon ngon = (NGon) shape;
        int n = ngon.getN();
        double[] xcoords = new double[n];
		double[] ycoords = new double[n];
		for (int i = 0; i < n; i++) {
			xcoords[i] = ngon.getP()[i].getX(0) + centerX;
			ycoords[i] = -ngon.getP()[i].getX(1) + centerY;
		}

		gc.strokePolygon(xcoords, ycoords, n);
    }

    public static NGonDrawer createInstance() {
        if (drawer == null)
            drawer = new NGonDrawer();
        
        return drawer;
    }
}
