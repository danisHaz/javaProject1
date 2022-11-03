package ui.drawers;

import app.IShape;

import java.util.ArrayList;

public class DrawerFactory {
    public static Drawer create(IShape shape) throws Exception {
        for (Drawer drawer: drawerDelegates) {
            if (drawer.getClassName() == shape.getClass().getName()) {
                return drawer;
            }
        }

        throw new Exception("Appropriate drawer is not implemented");
    }

    private static ArrayList<Drawer> drawerDelegates = new ArrayList<>() {{
        add(CircleDrawer.createInstance());
        add(NGonDrawer.createInstance());
        add(PolylineDrawer.createInstance());
        add(QGonDrawer.createInstance());
        add(RectangleDrawer.createInstance());
        add(SegmentDrawer.createInstance());
        add(TGonDrawer.createInstance());
        add(TrapezeDrawer.createInstance());
    }};
}
