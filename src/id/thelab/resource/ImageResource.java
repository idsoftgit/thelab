package id.thelab.resource;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageResource extends Resource {
	
	public ImageResource(final String path) throws SlickException{
		super("image");
		try {
			resource = new Image(path);
		} catch (SlickException e) {
			throw e;
		}
	}
}
