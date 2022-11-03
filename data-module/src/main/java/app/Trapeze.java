package app;

public class Trapeze extends QGon {
	public Trapeze(Point2D[] p) throws Exception {
		super(p);
		type = "Trapeze";
		
		Point first = Point2D.sub(p[0], p[1]);
		Point second = Point2D.sub(p[2], p[3]);
		Point third = Point2D.sub(p[0], p[3]);
		Point forth = Point2D.sub(p[1], p[2]);
		
		if (Math.abs(first.getX(0) / second.getX(0)) != Math.abs(first.getX(1) / second.getX(1))
		&& Math.abs(third.getX(0) / forth.getX(0)) != Math.abs(third.getX(1) / forth.getX(1))) {
			throw new Exception("Trapeze: Sides of QGon are not parallel");
		}
	}

	@Override
	public double square() throws Exception {
		return super.square();
	}
}
