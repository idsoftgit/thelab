package id.thelab.particle;

import id.thelab.resource.ResourceManager;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class SnowParticleEmitter extends ParticleEmitter {

	public SnowParticleEmitter(Rectangle bound, float z, ParticleSystem ps,
			Range emitInterval) {
		super(bound, z, ps, emitInterval);
	}

	public Particle getNewParticle() {
		float newx = (float) (getX() + Math.random() * getWidth());
		float newy = (float) (getY() + Math.random() * getHeight());
		float w = (float) (Math.random() * 5 + 5);
		Image img = (Image) ResourceManager.getInstance()
				.getResource("particle").getResourceData();
		return new ImageParticle(img, new Vector3f(newx, newy, getZ()), w, w,
				new Color(1f, 1f, 1f, 1f), (float) (Math.random() * 400 + 100),
				new SimpleParticleBehaviour());
	}

	@Override
	public void emit() {
		system.addParticle(getNewParticle());
	}
}
