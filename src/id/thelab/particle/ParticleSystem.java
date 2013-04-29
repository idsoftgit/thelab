package id.thelab.particle;

import id.thelab.level.Camera;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class ParticleSystem {
	private List<Particle> particles;
	private List<ParticleEmitter> emitters;
	private boolean alive;

	public ParticleSystem() {
		particles = new ArrayList<Particle>();
		emitters = new ArrayList<ParticleEmitter>();
		alive = true;
	}

	public boolean isAlive() {
		return alive;
	}

	public void update(final GameContainer gc, final int delta) {
		for (ParticleEmitter e : emitters) {
			e.update(gc, delta);
		}
		List<Particle> copy = new ArrayList<Particle>();
		/*
		 * if (!alive) { return; }
		 */
		copy.addAll(particles);
		for (Particle part : copy) {
			part.update(gc, delta);
			if (!part.isAlive()) {
				particles.remove(part);
			}
		}
		if (particles.size() == 0) {
			alive = false;
		}
	}

	public void render(final GameContainer gc, final Graphics g, final Camera c) {
		for (ParticleEmitter e : emitters) {
			e.render(gc, g, c);
		}
		for (Particle part : particles) {
			part.render(gc, g, c);
		}
	}

	public void addParticle(Particle p) {
		particles.add(p);
	}

	public void addEmitter(ParticleEmitter pe) {
		emitters.add(pe);
	}
}
