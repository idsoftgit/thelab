package id.thelab.collision;

import id.thelab.level.Camera;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class QuadNode implements MoveListener {
	private List<Collision> collisions = new LinkedList<Collision>();
	private QuadNode quadNodes[];

	private int depthLevel;
	private boolean split;
	private QuadNode parent = this;
	private Rectangle rectangle;

	public QuadNode(final float x, final float y, final float width,
			final float height, final int depthLevel, final QuadNode parent) {
		this.rectangle = new Rectangle(x, y, width, height);
		this.depthLevel = depthLevel;
		this.parent = parent;
		split = false;
	}

	public float getX() {
		return rectangle.getX();
	}

	public float getY() {
		return rectangle.getY();
	}

	public float getWidth() {
		return rectangle.getWidth();
	}

	public float getHeight() {
		return rectangle.getHeight();
	}

	public List<Collision> getCollisions() {
		return collisions;
	}

	public boolean isSplit() {
		return split;
	}

	public int size() {
		return collisions.size();
	}

	public int getDepthLevel() {
		return depthLevel;
	}

	public QuadNode[] getQuadNodes() {
		return quadNodes;
	}

	public QuadNode getParent() {
		return parent;
	}

	public void addCollision(final Collision cc) {
		if (isSplit()) {
			// this is a branch node
			for (QuadNode quadNode : getQuadNodes()) {
				if (quadNode.touches(cc)) {
					// add collision to this leaf
					quadNode.addCollision(cc);
				}
			}
			return;
		}
		if (collisions.contains(cc)) {
			// already contain this collision
			return;
		}
		if (collisions.size() < QuadTree.MAX_COLLISIONS) {
			// can add collision here
			collisions.add(cc);
			cc.addMoveListener(this);
			checkCollision(cc);
			return;
		} else if (depthLevel < QuadTree.MAX_DEPTH_LEVEL) {
			if (createSplitNodes(depthLevel + 1)) {
				addCollision(cc);
				return;
			}
		}
		collisions.add(cc);
		cc.addMoveListener(this);
		checkCollision(cc);
	}

	private boolean createSplitNodes(final int depthLevel) {
		if (rectangle.getWidth() < QuadTree.MIN_QUAD_SIZE
				|| rectangle.getHeight() < QuadTree.MIN_QUAD_SIZE) {
			return false;
		}
		float newWidth = rectangle.getWidth() / 2;
		float newHeight = rectangle.getHeight() / 2;

		quadNodes = new QuadNode[4];
		quadNodes[0] = new QuadNode(rectangle.getX(), rectangle.getY(),
				newWidth, newHeight, depthLevel, this);
		quadNodes[1] = new QuadNode(rectangle.getX(), rectangle.getY()
				+ newHeight, newWidth, newHeight, depthLevel, this);
		quadNodes[2] = new QuadNode(rectangle.getX() + newWidth,
				rectangle.getY(), newWidth, newHeight, depthLevel, this);
		quadNodes[3] = new QuadNode(rectangle.getX() + newWidth,
				rectangle.getY() + newHeight, newWidth, newHeight, depthLevel, this);
		this.split = true;

		for (Collision cc : collisions) {
			for (int j = 0; j < 4; j++) {
				if (quadNodes[j].touches(cc)) {
					quadNodes[j].addCollision(cc);
				}
			}
		}

		for (Collision col : collisions) {
			col.removeMoveListener(this);
		}
		collisions.clear();
		return true;
	}

	public boolean touches(final Collision cc) {
		return touches(cc.getShape());
	}

	private boolean touches(final Shape s) {
		return rectangle.contains(s) | rectangle.intersects(s);
	}

	public void remove(final Collision cc) {
		cc.removeMoveListener(this);
		collisions.remove(cc);
	}

	public void moving(final Collision cc) {
		// check previous collisions
		cc.checkCollisions();
		if (rectangle.contains(cc.getShape())) {
			// still inside node
			// check newCollisions
			checkCollision(cc);
			return;
		}
		if (parent != null) {
			if (!rectangle.contains(cc.getShape())
					&& !rectangle.intersects(cc.getShape())) {
				// absolutely leave node
				collisions.remove(cc);
				cc.removeMoveListener(this);
			}
			parent.collisionLeaveNode(this, cc);
		}
	}

	public void collisionLeaveNode(final QuadNode node, final Collision cc) {
		if (quadNodes != null) {
			for (int j = 0; j < 4; j++) {
				if (quadNodes[j] == node) {
					continue;
				}
				if (quadNodes[j].touches(cc)) {
					quadNodes[j].addCollision(cc);
					quadNodes[j].checkCollision(cc);
				}
			}
		}
		int size = 0;
		int splits = 0;
		if (quadNodes != null) {
			for (int j = 0; j < 4; j++) {
				size += quadNodes[j].size();
				splits += quadNodes[j].isSplit() ? 1 : 0;
			}
			if (size < QuadTree.MAX_COLLISIONS && splits == 0) {
				this.split = false;
				for (int j = 0; j < 4; j++) {
					for (Collision upCC : quadNodes[j].getCollisions()) {
						if (!collisions.contains(upCC)) {
							upCC.removeMoveListener(quadNodes[j]);
							upCC.addMoveListener(this);
							collisions.add(upCC);
							checkCollision(upCC);
						}
					}
					quadNodes[j] = null;
				}
				quadNodes = null;
			}
		}
		if (!rectangle.contains(cc.getShape())) {
			if (parent != null) {
				parent.collisionLeaveNode(this, cc);
			}
		}
	}

	public void update(final GameContainer gc, final int delta) {
		/*if (isSplit()) {
			for (int i = 0; i < 4; i++) {
				quadNodes[i].update(gc, delta);
			}
		}*/
	}

	public void render(final GameContainer gc, final Graphics g, final Camera sc) {
		if (isSplit()) {
			for (int i = 0; i < 4; i++) {
				quadNodes[i].render(gc, g, sc);
			}
			return;
		}
		Shape s = sc.mapToScreenConvert(rectangle);
		if (sc.isVisible(s)) {
			g.setLineWidth(3);
			g.setColor(new Color(0, 1, 0, 0.3f));
			g.draw(s);
			g.setLineWidth(1);
		}

		// render collisions
		for (Collision col : collisions) {
			s = sc.mapToScreenConvert(col.getShape());
			if (!sc.isVisible(s)) {
				continue;
			}
			g.setLineWidth(3);
			if (col.isCollided()) {
				g.setColor(new Color(1, 0, 0, 0.3f));
			} else {
				g.setColor(new Color(0, 0, 1, 0.3f));
			}
			g.draw(s);
			g.setLineWidth(1);
		}

	}

	public void setCollided(final Collision c1, final Collision c2) {
		c1.setCollided(c2, true);
		c2.setCollided(c1, true);
	}

	private boolean checkCollision(final Collision c) {
		boolean isCol = false;
		if (isSplit() && quadNodes != null) {
			for (QuadNode quadNode : getQuadNodes()) {
				if (quadNode.touches(c)) {
					if (quadNode.checkCollision(c)) {
						isCol = true;
					}
				}
			}
			return isCol;
		}

		for (Collision col : collisions) {
			if (col == c) {
				continue;
			}
			if (col.touches(c)) {
				setCollided(col, c);
				isCol = true;
			}
		}
		return isCol;
	}
}
