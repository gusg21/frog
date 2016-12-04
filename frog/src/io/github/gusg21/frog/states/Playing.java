package io.github.gusg21.frog.states;

import io.github.gusg21.frog.TheoreticalFigure;
import io.github.gusg21.frog.TileGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Playing {

	/*
	 * Player Figure
	 */
	static TheoreticalFigure player = new TheoreticalFigure(20, 20);

	/*
	 * Wall Figures
	 */
	static TheoreticalFigure wall1 = new TheoreticalFigure(80, 80, 70, 260);
	static TheoreticalFigure wall2 = new TheoreticalFigure(280, 80, 70, 260);

	/*
	 * Item / Gameplay Figures
	 */
	static TheoreticalFigure coin1 = new TheoreticalFigure(180, 180, 20, 20);

	static int speed = 5;
	static int score = 0;
	static boolean gotCoin1 = false;
	static int Calpha = 255;
	static double prevx, prevy;

	static boolean mPressed = false;
	static private Random r = new Random();

	public static void render(Graphics2D g) {
		/*
		 * Figure Rendering
		 */
		player.render(g, Color.MAGENTA);

		wall1.render(g, Color.BLUE);
		wall2.render(g, Color.BLUE);

		coin1.render(g, Color.YELLOW);

		/*
		 * Extra Rendering (GUI, etc.)
		 */
		TileGame.canvas.setBackground(new Color(34, 139, 34));
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", Font.PLAIN, 36));
		g.drawString(String.valueOf(score), 10, 40);
	}

	public static void update(int deltaTime) {

		/*
		 * COLLISIONS
		 */
		boolean colliding = ( // Wall Collision Register
		player.isColliding(wall1.getCollider()) || player.isColliding(wall2
				.getCollider()));

		/*
		 * Wall
		 */
		if (colliding) {
			revertXY(player);
		} else {
			setPrevPos();
		}

		/*
		 * Coin
		 */
		if (player.isColliding(coin1.getCollider()) && !gotCoin1) {
			score += 50;
			gotCoin1 = true;
		}
		if (gotCoin1) {
			coin1.y -= 1;
			coin1.editRenderAlpha(Calpha);
			Calpha -= 8;
		}
	}

	private static void revertXY(TheoreticalFigure fig) {
		fig.x = prevx;
		fig.y = prevy;
	}

	private static void setPrevPos() {
		prevx = player.x;
		prevy = player.y;
	}

	public static void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (player.y > 0) {
				player.y -= speed;
			}
			break;
		case KeyEvent.VK_DOWN:
			if (player.y < TileGame.HEIGHT) {
				player.y += speed;
			}
			break;
		case KeyEvent.VK_LEFT:
			if (player.x > 0) {
				player.x -= speed;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (player.x < TileGame.WIDTH) {
				player.x += speed;
			}
			break;
		case KeyEvent.VK_C:
			coin1.revive();
			break;
		case KeyEvent.VK_R:
			restart();
			break;
		}
		
			
	}

	private static void restart() {
		wall1.x = r.nextInt(TileGame.canvas.getWidth());
		wall1.y = r.nextInt(TileGame.canvas.getHeight());
		wall2.x = r.nextInt(TileGame.canvas.getWidth());
		wall2.y = r.nextInt(TileGame.canvas.getHeight());
		coin1.x = r.nextInt(TileGame.canvas.getWidth());
		coin1.y = r.nextInt(TileGame.canvas.getHeight());
		coin1.revive();
		coin1.editRenderAlpha(255);
		gotCoin1 = false;
		player.x = 20;
		player.y = 20;
	}

	public static void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		}
	}

	public static void mouseReleased(MouseEvent e) {
		mPressed = false;
	}

	public static void mousePressed(MouseEvent e) {
		mPressed = true;
	}

}
