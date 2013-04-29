package id.thelab.particle;

import id.thelab.level.Camera;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public abstract class ParticleEmitter {
	private Rectangle bound;
	private float z;
	private boolean enabled;
	protected Range particleSize;
	protected Range emitInterval;
	protected float remainingToEmit;
	protected Particle particleSample;
	protected ParticleSystem system;

	public ParticleEmitter(Rectangle bound, float z, ParticleSystem ps,
			Range emitInterval) {
		this.bound = bound;
		this.z = z;
		enabled = false;
		this.emitInterval = emitInterval;
		remainingToEmit = emitInterval.getRandom();
		system = ps;
	}

	public void setParticleSize(Range size) {
		particleSize = size;
	}

	public void setEmitInterval(Range interval) {
		emitInterval = interval;
		remainingToEmit = interval.getRandom();
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public abstract Particle getNewParticle();

	public abstract void emit();

	public void update(GameContainer gc, int delta) {
		if (enabled) {
			if (remainingToEmit > 0) {
				remainingToEmit -= 1 * delta;
			} else {
				emit();
				remainingToEmit = emitInterval.getRandom();
			}
		}
	}

	public void setPosition(Point p, float z) {
		bound.setLocation(p.getX(), p.getY());
		this.z = z;
	}

	public float getX() {
		return bound.getX();
	}

	public float getY() {
		return bound.getY();
	}

	public float getZ() {
		return z;
	}

	public float getWidth() {
		return bound.getWidth();
	}

	public float getHeight() {
		return bound.getHeight();
	}

	/*
	 * вывод контура эмиттера
	 */
	public void render(GameContainer gc, Graphics g, Camera c) {
		Shape s = c.mapToScreenConvert(bound);
		s.setY(s.getY() - z / 2);
		if (c.isVisible(s)) {
			if (enabled) {
				g.setColor(Color.cyan);
			} else {
				g.setColor(Color.darkGray);
			}
			g.draw(s);
		}
	}
}
