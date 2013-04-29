package id.thelab.resource;


import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class SpriteSheetResource extends Resource {
	private final int _tw, _th;

	public int getTileWidth() {
		return _tw;
	}

	public int getTileHeight() {
		return _th;
	}

	public SpriteSheetResource(final String path, final int tw, final int th)
			throws SlickException {
		super("spritesheet");
		try {
			resource = new SpriteSheet(path, tw, th);
		} catch (SlickException e) {
			throw e;
		}
		_tw = tw;
		_th = th;
	}
}
