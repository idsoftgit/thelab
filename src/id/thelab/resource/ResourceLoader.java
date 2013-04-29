package id.thelab.resource;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.w3c.dom.Element;

public class ResourceLoader {

	public Resource loadResource(final Element el) throws SlickException {
		String type = el.getAttribute("type");
		Resource res = null;
		if (type.equals("image")) {
			System.out.println("Found Image. resource");
			String path = el.getAttribute("path");
			res = loadImage(path);
		} else if (type.equals("spritesheet")) {
			System.out.println("Found SpriteSheet resource");
			String path = el.getAttribute("path");
			int tw = Integer.parseInt(el.getAttribute("tw"));
			int th = Integer.parseInt(el.getAttribute("th"));
			res = loadSpriteSheet(path, tw, th);
		} else if (type.equals("animation")) {
			System.out.println("Found Animation resource");
			String source = el.getAttribute("source");
			String path = el.getAttribute("path");
			int tw = Integer.parseInt(el.getAttribute("tw"));
			int th = Integer.parseInt(el.getAttribute("th"));
			int duration = Integer.parseInt(el.getAttribute("duration"));
			if (source.equals("resource")) {
				Image img = (Image) ResourceManager.getInstance()
						.getResource(path).getResourceData();
				assert (img != null);
				res = loadAnimation(img, tw, th, duration);
			} else if (source.equals("file")) {
				res = loadAnimation(path, tw, th, duration);
			}
		} else if (type.equals("sound")) {
			System.out.println("Found Sound resource");
			String path = el.getAttribute("path");
			res = loadSound(path);
		} else if (type.equals("music")) {
			System.out.println("Found Music resource");
			String path = el.getAttribute("path");
			res = loadMusic(path);
		} else if (type.equals("string")) {
			System.out.println("Found String resource.");
			String val = el.getTextContent();
			res = loadString(val);
		}
		return res;
	}

	public ImageResource loadImage(final String path) throws SlickException {
		return new ImageResource(path);
	}

	public SpriteSheetResource loadSpriteSheet(final String path, final int tw,
			final int th) throws SlickException {
		return new SpriteSheetResource(path, tw, th);
	}

	public AnimationResource loadAnimation(final String path, final int tw,
			final int th, final int duration) throws SlickException {
		return new AnimationResource(path, tw, th, duration);
	}

	public AnimationResource loadAnimation(final Image img, final int tw,
			final int th, final int duration) {
		return new AnimationResource(img, tw, th, duration);
	}

	public SoundResource loadSound(final String path) throws SlickException {
		return new SoundResource(path);
	}

	public MusicResource loadMusic(final String path) throws SlickException {
		return new MusicResource(path);
	}

	public StringResource loadString(final String val) {
		return new StringResource(val);
	}
}
