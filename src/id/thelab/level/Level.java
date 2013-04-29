package id.thelab.level;

import id.thelab.TheLabGame;
import id.thelab.collision.Collision;
import id.thelab.collision.CollisionManager;
import id.thelab.collision.RectangleCollision;
import id.thelab.entity.Entity;
import id.thelab.entity.Player;
import id.thelab.particle.ParticleEmitter;
import id.thelab.particle.ParticleSystem;
import id.thelab.particle.Range;
import id.thelab.particle.SnowParticleEmitter;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

public class Level {
	protected TiledMapRender map;
	protected List<Entity> entities = new ArrayList<Entity>();
	protected CollisionManager collisionManager;
	protected Player player;
	protected boolean renderCollisions;
	private Camera cam;
	private int width;
	private int height;

	ParticleSystem psystem;
	ParticleEmitter ppe;

	public Level(final String ref) throws SlickException {
		cam = new Camera(TheLabGame.WIDTH, TheLabGame.HEIGHT, 32, 0);
		try {
			this.map = new TiledMapRender(ref);
		} catch (Exception e) {
			e.printStackTrace();
		}
		width = map.getWidth() * map.getTileWidth() / 2;
		height = map.getHeight() * map.getTileHeight();
		collisionManager = new CollisionManager(new Rectangle(0, 0, width,
				height));

		int objGroupCount = map.getObjectGroupCount();
		int objCount;
		for (int i = 0; i < objGroupCount; i++) {
			objCount = map.getObjectCount(i);
			for (int j = 0; j < objCount; j++) {
				System.out.println(map.getObjectName(i, j));
				System.out.println(map.getObjectType(i, j));
				Rectangle rect = new Rectangle(map.getObjectX(i, j),
						map.getObjectY(i, j), map.getObjectWidth(i, j),
						map.getObjectHeight(i, j));
				RectangleCollision rc = new RectangleCollision(
						map.getObjectName(i, j), rect);
				collisionManager.add(rc);
			}
		}

		player = new Player(this, 50, 50);
		entities.add(player);
		cam.setCenterOnMapPosition(new Point(
				player.getPosition().getCenterX() + 32, player.getPosition()
						.getCenterY()));
		player.registerCollision(collisionManager);

		psystem = new ParticleSystem();
		ParticleEmitter pe = new SnowParticleEmitter(new Rectangle(0, 0, 1024,
				1024), 400, psystem, new Range(50, 60));
		psystem.addEmitter(pe);
		pe.setEnabled(true);
		ppe = new SnowParticleEmitter(new Rectangle(0, 0, 10, 10), 400,
				psystem, new Range(50, 60));
		psystem.addEmitter(ppe);
		ppe.setEnabled(true);
	}

	public void addEntity(final Entity entity) {
		entities.add(entity);
	}

	public void removeEntity(final Entity entity) {
		entities.remove(entity);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getTileType(final float x, final float y) {
		if ((x > 0 && y > 0)
				&& (x < map.getWidth() * map.getTileWidth() / 2 && y < map
						.getHeight() * map.getTileHeight())) {

			int tid = map.getTileId((int) x * 2 / map.getTileWidth(), (int) y
					/ map.getTileHeight(), 0);
			System.out.println(tid);
			return map.getTileProperty(tid, "type", "def");
		}
		return "";
	}

	public void update(final GameContainer gc, final int delta) {
		collisionManager.update(gc, delta);
		for (Entity entity : entities) {
			entity.update(gc, delta);
		}
		Input in = gc.getInput();
		if (in.isKeyPressed(Input.KEY_C)) {
			renderCollisions = !renderCollisions;
		}
		Point x = cam.screenToMap(new Point(in.getMouseX(), in.getMouseY()));
		ppe.setPosition(x, 400);
		psystem.update(gc, delta);
	}

	public void render(final GameContainer gc, final Graphics g) {
		// render map
		map.renderIsometricMap(-cam.getX(), -cam.getY(), 0, 0, cam.getWidth(),
				cam.getHeight(), null, true);

		for (Entity entity : entities) {
			entity.render(gc, g, cam);
		}

		// render collision
		if (renderCollisions) {
			collisionManager.render(gc, g, cam);
		}

		psystem.render(gc, g, cam);

		g.setColor(Color.white);
		g.drawString("cam x: " + cam.getX() + " y: " + cam.getY(), 10, 70);
	}
	
	// check this self!
	public void addCollision(final Collision cc) {
		collisionManager.add(cc);
	}

	public void setCameraPosition(final Point position) {
		cam.setCenterOnMapPosition(position);

	}

}
