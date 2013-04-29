package id.thelab.entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Shape;

import id.thelab.collision.CollisionManager;
import id.thelab.collision.RectangleCollision;
import id.thelab.level.Camera;
import id.thelab.level.Level;
import id.thelab.particle.ParticleEmitter;
import id.thelab.resource.ResourceManager;

public class Player extends Entity {
	private float xImgOffset, yImgOffset;
	private Animation movingUp, movingDown, movingLeft, movingRight,
			currentAnimation;
	ParticleEmitter e;

	public Player(final Level level, final float x, final float y) {
		super("player", level, x, y);
		xImgOffset = -32;
		yImgOffset = -32;
		collision = new RectangleCollision(getId() + "Collision", x, y, 32, 32);
		// load animations
		movingUp = (Animation) ResourceManager.getInstance()
				.getResource("playerMovingUp").getResourceData();
		movingDown = (Animation) ResourceManager.getInstance()
				.getResource("playerMovingDown").getResourceData();
		movingLeft = (Animation) ResourceManager.getInstance()
				.getResource("playerMovingLeft").getResourceData();
		movingRight = (Animation) ResourceManager.getInstance()
				.getResource("playerMovingRight").getResourceData();
		currentAnimation = movingDown;
		/*e = new ParticleEmitter(new Rectangle(position.getX(), position.getY(),
				10, 10));
		level.addEmitter(e);*/
	}

	public void registerCollision(final CollisionManager cm) {
		cm.add(collision);
	}

	@Override
	public void update(final GameContainer gc, final int delta) {
		float dir = 0;
		boolean move = false;
		Input in = gc.getInput();
		if (in.isKeyDown(Input.KEY_A)) {
			dir = (float) Math.toRadians(135);
			moveInDirection(0.15f * delta, dir);
			move = true;
			currentAnimation = movingLeft;
		}
		if (in.isKeyDown(Input.KEY_D)) {
			dir = (float) Math.toRadians(315);
			moveInDirection(0.15f * delta, dir);
			move = true;
			currentAnimation = movingRight;
		}

		if (in.isKeyDown(Input.KEY_W)) {
			dir = (float) Math.toRadians(225);
			moveInDirection(0.15f * delta, dir);
			move = true;
			currentAnimation = movingUp;
		}
		if (in.isKeyDown(Input.KEY_S)) {
			dir = (float) Math.toRadians(45);
			moveInDirection(0.15f * delta, dir);
			move = true;
			currentAnimation = movingDown;
		}
		moving = move;
//		e.setPosition(position);
		level.setCameraPosition(new Point(position.getCenterX() + 32, position
				.getCenterY()));
	}

	@Override
	public void render(final GameContainer gc, final Graphics g, final Camera sc) {
		Shape s = sc.mapToScreenConvert(position);
		if (moving) {
			currentAnimation.setAutoUpdate(true);
		} else {
			currentAnimation.setAutoUpdate(false);
		}
		g.drawAnimation(currentAnimation, s.getX() + xImgOffset, s.getY()
				+ yImgOffset);
	}
}
