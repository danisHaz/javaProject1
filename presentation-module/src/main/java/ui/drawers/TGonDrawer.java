package ui.drawers;

import app.IShape;
import app.TGon;

import javafx.scene.canvas.GraphicsContext;

public final class TGonDrawer implements Drawer {
    
    private static TGonDrawer drawer = null;
    private final String className = TGon.class.getName();

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void draw(IShape shape, GraphicsContext gc) throws Exception {
        TGon tgon = (TGon) shape;
        int n = tgon.getN();
        double[] xcoords = new double[n];
		double[] ycoords = new double[n];
		for (int i = 0; i < n; i++) {
			xcoords[i] = tgon.getP()[i].getX(0) + centerX;
			ycoords[i] = tgon.getP()[i].getX(1) + centerY;
		}

		gc.strokePolygon(xcoords, ycoords, n);
    }

    public static TGonDrawer createInstance() {
        if (drawer == null)
            drawer = new TGonDrawer();
        
        return drawer;
    }
}
