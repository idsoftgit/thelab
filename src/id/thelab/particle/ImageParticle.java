package id.thelab.particle;

import id.thelab.level.Camera;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class ImageParticle extends Particle {
	private Image img;

	public ImageParticle(Image img, Vector3f position, float width,
			float height, Color color, float lifeTime,
			ParticleBehaviour behaviour) {
		super(position, width, height, color, lifeTime, behaviour);
		this.img = img;
	}

	@Override
	public void render(GameContainer gc, Graphics g, Camera c) {

		// super.render(gc, g, c);
		Shape s = c.mapToScreenConvert(new Rectangle(position.getX(), position
				.getY(), width, height));
		s.setY(s.getY() - position.getZ() / 2);
		if (c.isVisible(s)) {
			g.drawImage(img, s.getX(), s.getY(), s.getX() + width, s.getY()
					+ height, 0, 0, img.getWidth(), img.getHeight(), color);
		}
	}
}
