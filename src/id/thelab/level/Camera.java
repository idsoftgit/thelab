package id.thelab.level;

import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Camera {
	protected int x;
	protected int y;
	protected Rectangle bound;
	protected int xOffset;
	protected int yOffset;

	public Camera(final int width, final int height, final int xOffset,
			final int yOffset) {
		this.bound = new Rectangle(0, 0, width, height);
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.x = 0;
		this.y = 0;
	}

	public void setPosition(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	public void move(final float dx, final float dy) {
		x += dx;
		y += dy;
	}

	public void moveInDirection(final float distance, final float direction) {
		x += distance * Math.cos(direction + Math.toRadians(45));
		y += distance * Math.sin(direction + Math.toRadians(45));
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return (int) this.bound.getWidth();
	}

	public int getHeight() {
		return (int) this.bound.getWidth();
	}

	public boolean isVisible(final Shape shape) {
		return this.bound.contains(shape) | this.bound.intersects(shape);
	}

	public Rectangle getBound() {
		return this.bound;
	}

	public Point orthoToIso(final Point p) {
		return new Point(p.getX() / 2 + p.getY(), p.getY() - p.getX() / 2);
	}

	private float orthoToIsoX(final float x, final float y) {
		return x / 2 + y;
	}

	private float orthoToIsoY(final float x, final float y) {
		return y - x / 2;
	}

	public Point isoToOrtho(final Point p) {
		return new Point(p.getX() - p.getY(), p.getX() / 2 - p.getY() / 2);
	}

	private float isoToOrthoX(final float x, final float y) {
		return x - y;
	}

	private float isoToOrthoY(final float x, final float y) {
		return x / 2 + y / 2;
	}

	public Point screenToMap(final Point p) {
		float nx = orthoToIsoX(p.getX(), p.getY()) - xOffset / 2 + x / 2 + y;
		float ny = orthoToIsoY(p.getX(), p.getY()) + xOffset / 2 - x / 2 + y;
		return new Point(nx, ny);
	}

	public Shape mapToScreenConvert(final Shape s) {
		float p[] = s.getPoints().clone();
		float px, py;
		for (int i = 0; i < s.getPointCount(); i++) {
			px = isoToOrthoX(p[i * 2], p[i * 2 + 1]) - x + xOffset;
			py = isoToOrthoY(p[i * 2], p[i * 2 + 1]) - y + yOffset;
			p[i * 2] = px;
			p[i * 2 + 1] = py;
		}
		return new Polygon(p);
	}

	public Point mapToScreenConvert(final Point p) {
		return new Point(isoToOrthoX(p.getX(), p.getY()) - x + xOffset,
				isoToOrthoY(p.getX(), p.getY()) - y + yOffset);
	}

	public void setCenterOnMapPosition(final Point position) {
		x = (int) (isoToOrthoX(position.getX(), position.getY()) - bound
				.getWidth() / 2);
		y = (int) (isoToOrthoY(position.getX(), position.getY()) - bound
				.getHeight() / 2);
	}

	public void setPosition(final Point position) {
		x = (int) position.getX();
		y = (int) position.getY();
	}
}
