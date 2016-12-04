package io.github.gusg21.frog;

import java.awt.geom.Point2D;

public class Point {

	public int x, y;

	public Point(int nx, int ny) {
		x = nx;
		y = ny;
	}

	public double distanceTo(Point other) {
		return Point2D.distance(x, y, other.x, other.y);
	}

}
