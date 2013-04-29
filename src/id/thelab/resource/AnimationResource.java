package id.thelab.resource;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class AnimationResource extends Resource {
	protected int _tw, _th, _duration;
	protected boolean _autoUpdate;
	Animation res;

	public AnimationResource(final String path, final int tw, final int th,
			final int duration) throws SlickException {
		super("animation");
		resource = new SpriteSheet(path, tw, th);
		_duration = duration;
		_autoUpdate = true;
		res = new Animation((SpriteSheet) resource, _duration);
		res.setAutoUpdate(_autoUpdate);
	}

	public AnimationResource(final Image img, final int tw, final int th,
			final int duration) {
		super("animation");
		resource = new SpriteSheet(img, tw, th);
		_duration = duration;
		_autoUpdate = true;
		res = new Animation((SpriteSheet) resource, _duration);
		res.setAutoUpdate(_autoUpdate);
	}

	public Animation getResourceData() {
		return res;
	}

	public int get_tw() {
		return _tw;
	}

	public int get_th() {
		return _th;
	}

	public int get_duration() {
		return _duration;
	}
}
