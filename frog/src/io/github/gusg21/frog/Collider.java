package io.github.gusg21.frog;

public class Collider {

	public double x = 0, y = 0; // Position
	public int width = 20, height = 20; // Dimensions
	public double radius;
	TheoreticalFigure.ColliderTypes type;

	public Collider(int nx, int ny, int nwidth, int nheight, TheoreticalFigure.ColliderTypes type) {
		x = nx;
		y = ny;
		width = nwidth;
		height = nheight;
		this.type = type;
		radius = width / 2;
	}

}
