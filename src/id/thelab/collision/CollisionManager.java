package id.thelab.collision;

import id.thelab.event.EventService;
import id.thelab.level.Camera;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class CollisionManager {
	private EventService eventService;
	private QuadTree quadTree;

	// protected Map<String, Tuple<Collision, Collision>> collisions;

	public CollisionManager(final Rectangle region) {
		quadTree = new QuadTree(region);
		// collisions = new HashMap<String, Tuple<Collision, Collision>>();
	}

	public EventService setEventService(final EventService newService) {
		EventService oldService = this.eventService;
		this.eventService = newService;
		return oldService;
	}

	public void render(final GameContainer gc, final Graphics g, final Camera sc) {
		quadTree.render(gc, g, sc);
	}

	public void update(final GameContainer gc, final int delta) {
		quadTree.update(gc, delta);
	}

	public void add(final Collision cc) {
		quadTree.addCollision(cc);
	}

	public void remove(Collision cc) {
		quadTree.removeCollision(cc);
	}
}
