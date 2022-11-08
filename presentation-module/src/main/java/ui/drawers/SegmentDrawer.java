package ui.drawers;

import app.IShape;
import app.Segment;

import javafx.scene.canvas.GraphicsContext;

public final class SegmentDrawer implements Drawer {
    
    private static SegmentDrawer drawer = null;
    private final String className = Segment.class.getName();

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void draw(IShape shape, GraphicsContext gc) throws Exception {
        Segment segment = (Segment) shape;
        gc.strokeLine(segment.getStart().getX(0) + centerX, -segment.getStart().getX(1) + centerY,
			segment.getFinish().getX(0) + centerX, -segment.getFinish().getX(1) + centerY);
    }

    public static SegmentDrawer createInstance() {
        if (drawer == null)
            drawer = new SegmentDrawer();
        
        return drawer;
    }
}
