package id.thelab.particle;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

public final class SimpleParticleEmitter extends ParticleEmitter {

	public SimpleParticleEmitter(Rectangle bound, float z, ParticleSystem ps,
			Range emitInterval) {
		super(bound, z, ps, emitInterval);
	}

	public Particle getNewParticle() {
		float newx = (float) (getX() + Math.random() * getWidth());
		float newy = (float) (getY() + Math.random() * getHeight());
		return new CircleParticle(new Vector3f(newx, newy, getZ()), new Color(
				1f, 1f, 1f), 3, (float) (Math.random() * 300 + 50));
	}

	@Override
	public void emit() {
		system.addParticle(getNewParticle());
	}

}
