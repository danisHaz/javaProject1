package ui.drawers;

import app.IShape;
import app.Polyline;

import javafx.scene.canvas.GraphicsContext;

public final class PolylineDrawer implements Drawer {
    
    private static PolylineDrawer drawer = null;
    private final String className = Polyline.class.getName();

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void draw(IShape shape, GraphicsContext gc) throws Exception {
        Polyline polyline = (Polyline) shape;
        int n = polyline.getN();
        for (int i = 1; i < n; i++) {
			gc.strokeLine(polyline.getP()[i - 1].getX(0) + centerX, -polyline.getP()[i - 1].getX(1) + centerY,
                polyline.getP()[i].getX(0) + centerX, -polyline.getP()[i].getX(1) + centerY);
        }
    }

    public static PolylineDrawer createInstance() {
        if (drawer == null)
            drawer = new PolylineDrawer();
        
        return drawer;
    }
}
