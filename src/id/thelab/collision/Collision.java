package id.thelab.collision;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import id.thelab.entity.Entity;
import id.thelab.level.Camera;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Shape;

public abstract class Collision {
	private String id;
	private List<MoveListener> moveListeners = new ArrayList<MoveListener>();
	private List<Collision> collided = new LinkedList<Collision>();
	protected Shape shape;
	private Entity owner;

	public Collision(final String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setOwner(final Entity e) {
		owner = e;
	}

	public Entity getOwner() {
		return owner;
	}

	public void setCollided(final Collision c, final boolean b) {
		if (b) {
			if (!collided.contains(c)) {
				collided.add(c);
			}
		} else {
			if (collided.contains(c)) {
				collided.remove(c);
			}
		}
	}

	public void checkCollisions() {
		List<Collision> copy = new LinkedList<Collision>();
		copy.addAll(collided);
		for (Collision col : copy) {
			if (!touches(col)) {
				setCollided(col, false);
				col.setCollided(this, false);
			}
		}
	}

	public boolean isCollided() {
		return collided.size() > 0;
	}

	public Shape getShape() {
		return shape;
	}

	public void setPosition(final Point p) {
		shape.setX(p.getX());
		shape.setY(p.getY());
	}

	public void setPosition(final float x, final float y) {
		shape.setX(x);
		shape.setY(y);
	}

	public float getX() {
		return shape.getX();
	}

	public float getY() {
		return shape.getY();
	}

	public Point getPosition() {
		return new Point(shape.getX(), shape.getY());
	}

	public void move(final float dx, final float dy) {
		setPosition(getX() + dx, getY() + dy);
		moved();
	}

	public void moveInDirection(final float distance, final float direction) {
		setPosition(getX() + (float) Math.cos(direction) * distance, getY()
				+ (float) Math.sin(direction) * distance);
		moved();
	}

	public boolean touches(final Collision other) {
		return touches(other.getShape());
	}

	public boolean touches(final Shape s) {
		return shape.contains(s) | shape.intersects(s);
	}

	public void render(final GameContainer gc, final Graphics g, final Camera sc) {
		Shape s = sc.mapToScreenConvert(shape);
		if (sc.isVisible(s)) {
			if (isCollided()) {
				g.setColor(Color.red);
			} else {
				g.setColor(Color.blue);
			}
			g.draw(s);
		}
	}

	public void moved() {
		ArrayList<MoveListener> copy = new ArrayList<MoveListener>();
		copy.addAll(moveListeners);
		for (MoveListener ml : copy) {
			ml.moving(this);
		}
	}

	public void addMoveListener(final MoveListener ml) {
		moveListeners.add(ml);
	}

	public void removeMoveListener(final MoveListener ml) {
		moveListeners.remove(ml);
	}
}
