package id.thelab;

import id.thelab.resource.ResourceManager;

import java.awt.Font;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class IntroGameState extends BasicGameState {
	TrueTypeFont ttf = new TrueTypeFont(new Font("Verdana", Font.BOLD, 48),
			true);
	TrueTypeFont ttf2 = new TrueTypeFont(new Font("Verdana", Font.ITALIC, 24),
			true);

	private final int stateId;

	private float pressY;
	private float pressX;
	private float labX;
	private float labY;
	private String msg = "The Lab";
	private String msg2 = "Press mouse button";
	private static final int labEndY = 180;
	private static final int pressEndY = 350;
	private float acc = 0.3f;
	private Music m;
	private Random r = new Random();
	private Line l = new Line(0, 0, 0, 480);

	public IntroGameState(int state) {
		this.stateId = state;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		labX = gc.getWidth() / 2 - ttf.getWidth(msg) / 2;
		labY = gc.getHeight() / 2 - ttf.getHeight(msg) / 2 + 100;
		pressX = gc.getWidth() / 2 - ttf2.getWidth(msg2) / 2;
		pressY = gc.getHeight() / 2 - ttf2.getHeight(msg2);
		m = (Music) ResourceManager.getInstance().getResource("introMusic")
				.getResourceData();
		m.play();
		m.setVolume(0);
		m.fade(2000, 1, false);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {

		ttf.drawString(labX + r.nextInt(4) - 2, labY + r.nextInt(4) - 2, msg,
				Color.red);
		ttf2.drawString(pressX + r.nextInt(4) - 2, pressY + r.nextInt(4) - 2,
				msg2, Color.gray);
		g.drawString(String.valueOf(acc), 0, 0);
		g.setColor(Color.darkGray);
		if (r.nextInt(100) < 10) {
			g.draw(l);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input i = gc.getInput();
		if (i.isMouseButtonDown(0)) {
			sbg.enterState(TheLabGame.STATE_GAMEPLAY, new FadeOutTransition(
					Color.white, 500), new FadeInTransition(Color.white, 500));
			m.fade(1000, 0, true);
		}
		if (pressY < pressEndY) {
			pressY += acc * delta;
		}
		if (labY > labEndY) {
			labY -= acc * delta;
		}
		if (acc > 0) {
			acc -= 0.0001 * delta;
		}
		l.setX(r.nextInt(200) + 100);
	}

	@Override
	public int getID() {
		return stateId;
	}

}
