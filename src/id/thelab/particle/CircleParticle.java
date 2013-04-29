package id.thelab.particle;

import id.thelab.level.Camera;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;

public class CircleParticle extends Particle {
	private Ellipse circle;

	public CircleParticle(Vector3f position, Color color, int size,
			float lifeTime) {
		super(position, size, size, color, lifeTime,
				new SimpleParticleBehaviour());
		circle = new Ellipse(position.getX(), position.getY(), width, height);
	}

	@Override
	public void render(GameContainer gc, Graphics g, Camera c) {
		super.render(gc, g, c);
		Shape s = c.mapToScreenConvert(circle);
		s.setY(s.getY() - position.getZ() / 2);
		if (c.isVisible(s)) {
			g.setColor(color);
			g.draw(s);
		}
	}

	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		circle.setX(position.getX());
		circle.setY(position.getY());
	}
}
