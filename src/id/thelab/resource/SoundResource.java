package id.thelab.resource;


import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundResource extends Resource {
	public SoundResource(final String path) throws SlickException {
		super("sound");
		resource = new Sound(path);
	}
}
