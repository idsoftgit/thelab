package id.thelab.collision;

import org.newdawn.slick.geom.Rectangle;

public class RectangleCollision extends Collision {
	public RectangleCollision(final String id, final Rectangle rect) {
		super(id);
		shape = new Rectangle(rect.getX(), rect.getY(), rect.getWidth(),
				rect.getHeight());
	}

	public RectangleCollision(final String id, final float x, final float y,
			final float width, float height) {
		super(id);
		shape = new Rectangle(x, y, width, height);
	}
}
