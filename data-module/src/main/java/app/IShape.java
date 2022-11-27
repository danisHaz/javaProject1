package app;

import com.mongodb.DBObject;

public interface IShape {
	double square() throws Exception;
	double length() throws Exception;
	IShape shift(Point2D a) throws Exception;
	IShape rot(double phi) throws Exception;
	IShape symAxis(int i) throws Exception;
	<T extends IShape> boolean cross(T s) throws Exception;
	DBObject toBson();
	String getType();
	double centerX = 200.0;
	double centerY = 200.0;
}
