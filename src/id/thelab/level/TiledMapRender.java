package id.thelab.level;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.Layer;
import org.newdawn.slick.tiled.TiledMap;

public class TiledMapRender extends TiledMap {

	public TiledMapRender(final String ref) throws SlickException {
		super(ref);
	}

	@Override
	protected void renderIsometricMap(final int x, final int y, final int sx,
			final int sy, final int width, final int height, final Layer l,
			final boolean lineByLine) {
		super.renderIsometricMap(x, y, sx, sy, width, height, l, lineByLine);
	}

	@Override
	protected void renderedLine(final int visualY, final int mapY, final int layer) {
		/*
		 * System.out.println("visiualY " + visualY + " mapY " + mapY +
		 * " layer " + layer);
		 */
		super.renderedLine(visualY, mapY, layer);
	}

}
