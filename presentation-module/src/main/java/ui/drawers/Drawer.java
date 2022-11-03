package ui.drawers;

import app.IShape;

import javafx.scene.canvas.GraphicsContext;

public interface Drawer {
    
    static double centerX = 200;
    static double centerY = 200;

    public String getClassName();

    public void draw(IShape shape, GraphicsContext gc) throws Exception;
}
