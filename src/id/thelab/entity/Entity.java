package id.thelab.entity;

import id.thelab.collision.Collision;
import id.thelab.level.Camera;
import id.thelab.level.Level;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

public abstract class Entity {
	private String id;
	protected Point position;
	protected float direction;
	protected Collision collision;
	protected Level level;
	protected boolean moving;

	public Entity(final String id, final Level level, final float x, final float y) {
		position = new Point(x, y);
		this.id = id;
		this.level = level;
		moving = false;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(final Point p) {
		position.setX(p.getX());
		position.setY(p.getY());
	}

	public void move(final float dx, final float dy) {
		position.setX(position.getX() + dx);
		position.setY(position.getY() + dy);
		collision.move(dx, dy);
	}

	public boolean moveInDirection(final float distance, final float direction) {
		boolean moved = false;
		Point np = new Point(position.getX() + (float) Math.cos(direction)
				* distance, position.getY() + (float) Math.sin(direction)
				* distance);

		collision.moveInDirection(distance, direction);
		if (!collision.isCollided()) {
			setPosition(np);
			moved = true;
		} else {
			collision.setPosition(position);
			moved = false;
		}

		return moved;
	}

	public String getId() {
		return id;
	}

	public abstract void update(final GameContainer gc, final int delta);

	public abstract void render(final GameContainer gc, final Graphics g, final Camera sc);
}
