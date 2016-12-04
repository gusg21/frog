package io.github.gusg21.frog;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class TheoreticalFigure {

	public double x = 0, y = 0; // Position
	private double px, py;
	public int width = 20, height = 20; // Dimensions
	private int pwidth, pheight;
	private int alpha = 255;
	public boolean destroyed = false;

	private static Random r = new Random();

	/**
	 * Creates a new rectangle object in the game, with width, height, x and y.
	 * Has easy rendering capabilites (render()) and collision detection
	 * (isColliding()).
	 * 
	 * @param nx
	 *            X position of object by default.
	 * @param ny
	 *            Y position of object by default.
	 */
	public TheoreticalFigure(double nx, double ny) {
		x = nx;
		y = ny;
	}

	/**
	 * Creates a new rectangle object in the game, with width, height, x and y.
	 * Has easy rendering capabilites (render()) and collision detection
	 * (isColliding()).
	 * 
	 * @param nx
	 *            X position of object by default.
	 * @param ny
	 *            Y position of object by default.
	 * @param nwidth
	 *            Width of the object by default.
	 * @param nheight
	 *            Height of the object by default.
	 */
	public TheoreticalFigure(double nx, double ny, int nwidth, int nheight) {
		x = nx;
		y = ny;
		width = nwidth;
		height = nheight;
	}

	/**
	 * Used in conjunction with isColliding, returns a Collider object
	 * 
	 * @return Collider
	 */
	public Collider getCollider() {
		return new Collider((int) x, (int) y, width, height);
	}

	/**
	 * Returns the coordinates of the center of the Theoretical as a Point
	 * object
	 * 
	 * @return Point
	 */
	public Point center() {
		return new Point((int) x + (width / 2), (int) y + (height / 2));
	}

	/**
	 * Returns the coordinates of the top-left of the Theoretical as a Point
	 * object
	 * 
	 * @return Point
	 */
	public Point topLeft() {
		return new Point((int) x, (int) y);
	}

	/**
	 * Returns the coordinates of the top-right of the Theoretical as a Point
	 * object
	 * 
	 * @return Point
	 */
	public Point topRight() {
		return new Point((int) x + width, (int) y);
	}

	/**
	 * Returns the coordinates of the bottom-left of the Theoretical as a Point
	 * object
	 * 
	 * @return Point
	 */
	public Point bottomLeft() {
		return new Point((int) x, (int) y + height);
	}

	/**
	 * Returns the coordinates of the bottom-right of the Theoretical as a Point
	 * object
	 * 
	 * @return Point
	 */
	public Point bottomRight() {
		return new Point((int) x + width, (int) y + height);
	}

	/**
	 * Destroys the object, makes it irrenderable.
	 */
	public void destroy() {
		if (!destroyed) {
			pwidth = width;
			pheight = height;
			width = 0;
			height = 0;
			px = x;
			py = y;
			x = -(r.nextInt(30000));
			y = -(r.nextInt(30000));
			destroyed = true;
		}
	}

	/**
	 * Reverts the object back to its state pre-destroy
	 */
	public void revive() {
		if (destroyed) {
			width = pwidth;
			height = pheight;
			x = px;
			y = py;
			destroyed = false;
		}
	}

	/**
	 * Checks if the collider of the current object is overlapping with the
	 * collider of a seperate object
	 * 
	 * @param other
	 *            The Collider of the other object (otherObject.getCollider())
	 * @return If you're colliding, true, else false
	 */
	public boolean isColliding(Collider other) {
		return x < other.x + other.width && x + width > other.x
				&& y < other.y + other.height && y + height > other.y;
	}

	/**
	 * Renders the Theoretical as a rectangle
	 * 
	 * @param g
	 *            The Graphics2D object you're using
	 * @param color
	 *            The color to render as
	 */
	public void render(Graphics2D g, Color color) {
		g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(),
				alpha));
		g.fillRect((int) x, (int) y, width, height);
	}

	/**
	 * Sets the rendering alpha. (0-255)
	 * 
	 * @param newAlpha
	 */
	public void editRenderAlpha(int newAlpha) {
		if (newAlpha > 255) {
			newAlpha = 255;
		}
		if (newAlpha < 0) {
			newAlpha = 0;
		}
		alpha = newAlpha;
	}

	/**
	 * Returns the current render alpha.
	 * 
	 * @return alpha
	 */
	public int getRenderAlpha() {
		return alpha;
	}

}