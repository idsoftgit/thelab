package id.thelab.collision;

import org.newdawn.slick.geom.Polygon;

public class PolygonCollision extends Collision {

	public PolygonCollision(final String id, final float points[]) {
		super(id);
		shape = new Polygon(points);
	}

}
