package ui.drawers;

import app.IShape;
import app.Circle;

import javafx.scene.canvas.GraphicsContext;

public final class CircleDrawer implements Drawer {
    
    private static CircleDrawer drawer = null;
    private final String className = Circle.class.getName();

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void draw(IShape shape, GraphicsContext gc) throws Exception {
        Circle circle = (Circle) shape;
		gc.strokeOval(
            circle.getP().getX(0) - circle.getR() + centerX,
            circle.getP().getX(1) - circle.getR() + centerY,
            circle.getR() * 2,
            circle.getR() * 2
        );
    }

    public static CircleDrawer createInstance() {
        if (drawer == null)
            drawer = new CircleDrawer();
        
        return drawer;
    }
}
