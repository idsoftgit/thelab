package id.thelab.particle;

import id.thelab.level.Camera;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

public abstract class Particle {
	protected Vector3f position;
	protected float width, height;

	protected ParticleBehaviour behaviour;

	protected float lifeTime;
	protected float remainingTime;
	protected boolean alive;

	protected Color color;

	public Particle(Vector3f position, float width, float height,
			Color color, float lifeTime, ParticleBehaviour behaviour) {
		this.setPosition(position);
		this.setLifeTime(lifeTime);
		remainingTime = lifeTime;
		this.width = width;
		this.height = height;
		this.behaviour = behaviour;
		this.color = color;
		alive = true;
	}

	public boolean isAlive() {
		return alive;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public void setPosition(float x, float y, float z) {
		position.set(x, y, z);
	}

	public float getLifeTime() {
		return lifeTime;
	}

	public void setLifeTime(float lifeTime) {
		this.lifeTime = lifeTime;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public void setWidth(float w) {
		width = w;
	}

	public void setheight(float h) {
		height = h;
	}

	public void update(GameContainer gc, int delta) {
		if (remainingTime > 0 && alive) {
			remainingTime -= 0.05 * delta;
			behaviour.update(this, delta);
		} else if (alive) {
			alive = false;
		}
	}

	public void render(GameContainer gc, Graphics g, Camera c) {
		Point p = c.mapToScreenConvert(new Point(position.x, position.y));
		if (c.isVisible(p)) {
			g.setColor(Color.magenta);
			g.setLineWidth(3);
			g.drawLine(p.getX(), p.getY(), p.getX() + 2, p.getY() + 2);
		}
	}

	public void setTransparency(float trans) {
		color.a = trans;
	}

	public float getRemainingTime() {
		return remainingTime;
	}

	public void die() {
		remainingTime = 0;
		alive = true;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
