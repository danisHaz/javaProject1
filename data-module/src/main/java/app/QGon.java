package app;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

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

    @Override
    public DBObject toBson() {
        DBObject qgon = new BasicDBObject("type", type)
            .append("data", toString());

        return qgon;
    }

    @Override
    public String toString() {
        return type + super.toString().substring(4);
    }
}
