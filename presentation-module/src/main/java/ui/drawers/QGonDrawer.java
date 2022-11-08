package ui.drawers;

import app.IShape;
import app.QGon;

import javafx.scene.canvas.GraphicsContext;

public final class QGonDrawer implements Drawer {
    
    private static QGonDrawer drawer = null;
    private final String className = QGon.class.getName();

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void draw(IShape shape, GraphicsContext gc) throws Exception {
        QGon qgon = (QGon) shape;
        int n = qgon.getN();
        double[] xcoords = new double[n];
		double[] ycoords = new double[n];
		for (int i = 0; i < n; i++) {
			xcoords[i] = qgon.getP()[i].getX(0) + centerX;
			ycoords[i] = -qgon.getP()[i].getX(1) + centerY;
		}

		gc.strokePolygon(xcoords, ycoords, n);
    }

    public static QGonDrawer createInstance() {
        if (drawer == null)
            drawer = new QGonDrawer();
        
        return drawer;
    }
}
