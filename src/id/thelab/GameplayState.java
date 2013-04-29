package id.thelab;

import java.util.Random;

import id.thelab.collision.Collision;
import id.thelab.collision.LineCollision;
import id.thelab.level.Level;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameplayState extends BasicGameState {
	private final int stateId;
	private Level lv;
	private Point mp = new Point(0, 0);
	private int lnum;

	public GameplayState(int state) {
		this.stateId = state;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {

		lv = new Level("res/map/collision.tmx");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		lv.render(gc, g);
		g.setColor(Color.white);
		g.drawString("mx:" + mp.getX() + " my:" + mp.getY(), 10, 30);

		g.drawString("tile type: " + lv.getTileType(mp.getX(), mp.getY()), 10,
				50);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// update game objects
		Input in = gc.getInput();

		/*
		 * if (in.isKeyPressed(Input.KEY_A)) { Random r = new Random();
		 * CollisionComponent rc = new RectangleCollisionComponent("rc", new
		 * Rectangle(r.nextInt(300), r.nextInt(300), (r.nextInt(2) + 1) * 64,
		 * (r.nextInt(2) + 1) * 32)); lv.addCollisionComponent(rc); }
		 */
		if (in.isKeyPressed(Input.KEY_L)) {
			Random r = new Random();
			Collision lc = new LineCollision("LineCollision"
					+ String.valueOf(lnum), new Line(r.nextInt(lv.getWidth()),
					r.nextInt(lv.getHeight()), r.nextInt(lv.getWidth()),
					r.nextInt(lv.getHeight())));
			lv.addCollision(lc);
			lnum++;
		}
		lv.update(gc, delta);
	}

	@Override
	public int getID() {
		return stateId;
	}

}
