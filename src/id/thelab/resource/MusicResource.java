package id.thelab.resource;


import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class MusicResource extends Resource {
	public MusicResource(final String path) throws SlickException {
		super("music");
		resource = new Music(path);
	}
}
