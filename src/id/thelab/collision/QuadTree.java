package id.thelab.collision;

import id.thelab.level.Camera;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class QuadTree {
	public static final int MAX_COLLISIONS = 10;
	public static final int MAX_DEPTH_LEVEL = 10;
	public static final int MIN_QUAD_SIZE = 64;

	QuadNode rootNode;

	public QuadTree(final Rectangle region) {
		rootNode = new QuadNode(region.getX(), region.getY(),
				region.getWidth(), region.getHeight(), 0, null);
	}

	public void addCollision(final Collision cc) {
		rootNode.addCollision(cc);
	}

	public void removeCollision(final Collision cc) {
		rootNode.remove(cc);
	}

	public void update(final GameContainer gc, final int delta) {
		rootNode.update(gc, delta);
	}

	public void render(final GameContainer gc, final Graphics g, final Camera sc) {
		rootNode.render(gc, g, sc);
	}
}
