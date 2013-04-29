package id.thelab.particle;

import java.util.Random;

import org.lwjgl.util.vector.Vector3f;

public class SimpleParticleBehaviour implements ParticleBehaviour {
	private float f, d;
	private Random r;

	public SimpleParticleBehaviour() {
		r = new Random();
		f = (float) (r.nextFloat() * Math.PI * 2);
		d = (float) ((r.nextFloat() - 0.5) * 0.5);
	}

	@Override
	public void update(Particle p, int delta) {
		Vector3f pos = new Vector3f(p.getPosition());
		float v = (float) (Math.sin(f) * delta * 0.1 + pos.x);
		float b = (float) (Math.cos(f) * delta * 0.1 + pos.y);

		if (pos.z > 0) {
			pos.z -= 0.15f * delta;
			pos.x = v;
			pos.y = b;
		} else {
			//p.die();
		}

		p.setPosition(pos);
		f += d;
		p.setTransparency(p.getRemainingTime() / p.getLifeTime());
		p.getColor().b -= 0.00005*delta;
		/*p.getColor().g -= 0.0001*delta;
		p.getColor().b -= 0.0001*delta;*/
	}
}
