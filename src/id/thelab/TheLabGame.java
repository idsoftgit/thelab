package id.thelab;

import id.thelab.resource.ResourceManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public final class TheLabGame extends StateBasedGame {
	public final static int STATE_INTRO = 0;
	public final static int STATE_GAMEPLAY = 1;

	public final static int WIDTH = 640;
	public final static int HEIGHT = 480;

	public TheLabGame(String title) {
		super(title);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.addState(new IntroGameState(STATE_INTRO));
		this.addState(new GameplayState(STATE_GAMEPLAY));

		try {
			ResourceManager.getInstance().loadResources(
					new FileInputStream(new File("res/resources.xml")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (SlickException e) {
			e.printStackTrace();
			System.exit(0);
		}

		gc.setAlwaysRender(false);
		gc.setTargetFrameRate(100);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("This is a \"The Lab\" game.");
		try {
			new AppGameContainer(new TheLabGame("The Lab"), 640, 480, false)
					.start();
		} catch (SlickException r) {
			r.printStackTrace();
			System.exit(0);
		}

	}

}
