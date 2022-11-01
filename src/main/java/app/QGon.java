package app;

public class QGon extends NGon {
    public QGon(Point2D[] p) throws Exception {
        super(p);
        type = "QGon";
        if (n != 4)
            throw new Exception("QGon: Array size is not correct\n");
    }

    @Override
    public double square() throws Exception {
        return super.square();
    }
}
