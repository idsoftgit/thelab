package id.thelab.collision;

import org.newdawn.slick.geom.Line;

public class LineCollision extends Collision {

	public LineCollision(final String id, final float x1, final float y1,
			final float x2, final float y2) {
		super(id);
		shape = new Line(x1, y1, x2, y2);
	}

	public LineCollision(final String id, final Line l) {
		super(id);
		shape = new Line(l.getX1(), l.getY1(), l.getX2(), l.getY2());
	}

}
