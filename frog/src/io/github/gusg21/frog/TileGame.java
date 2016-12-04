package io.github.gusg21.frog;

import io.github.gusg21.frog.states.Playing;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TileGame implements Runnable, KeyListener, MouseListener {

	public final static int WIDTH = 1000;
	public final static int HEIGHT = 700;
	final String TITLE = "RPG Game"; // Change me
	
	public enum States {
		PLAYING
	}
	
	public static States state = States.PLAYING;

	JFrame frame;
	public static Canvas canvas;
	BufferStrategy bufferStrategy;

	public TileGame() {
		frame = new JFrame(TITLE);

		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.setLayout(null);

		canvas = new Canvas();
		canvas.setBounds(0, 0, WIDTH, HEIGHT);
		canvas.setIgnoreRepaint(true);

		panel.add(canvas);

		canvas.addMouseListener(this);
		canvas.addKeyListener(this);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);

		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();

		canvas.requestFocus();
	}

	long desiredFPS = 60;
	long desiredDeltaLoop = (1000 * 1000 * 1000) / desiredFPS;

	boolean running = true;

	public void run() {

		long beginLoopTime;
		long endLoopTime;
		long currentUpdateTime = System.nanoTime();
		long lastUpdateTime;
		long deltaLoop;

		while (running) {
			beginLoopTime = System.nanoTime();

			render();

			lastUpdateTime = currentUpdateTime;
			currentUpdateTime = System.nanoTime();
			update((int) ((currentUpdateTime - lastUpdateTime) / (1000 * 1000)));
				
			endLoopTime = System.nanoTime();
			deltaLoop = endLoopTime - beginLoopTime;

			if (deltaLoop > desiredDeltaLoop) {
				// Do nothing. We are already late.
			} else {
				try {
					Thread.sleep((desiredDeltaLoop - deltaLoop) / (1000 * 1000));
				} catch (InterruptedException e) {
					// Do nothing
				}
			}
		}
	}
	
	public static void setState(States newState) {
		state = newState;
	}

	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);
		render(g);
		g.dispose();
		bufferStrategy.show();
	}

	/**
	 * Rewrite this method for your game
	 */
	protected void update(int deltaTime) {
		if (state == States.PLAYING) {
			Playing.update(deltaTime);
		}
	}

	/**
	 * Rewrite this method for your game
	 */
	protected void render(Graphics2D g) {
		if (state == States.PLAYING) {
			Playing.render(g);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (state == States.PLAYING) {
			Playing.keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (state == States.PLAYING) {
			Playing.keyReleased(e);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) { } // None

	@Override
	public void mouseClicked(MouseEvent e) { } // None

	@Override
	public void mouseEntered(MouseEvent e) { } // None

	@Override
	public void mouseExited(MouseEvent e) { } // None

	@Override
	public void mousePressed(MouseEvent e) {
		if (state == States.PLAYING) {
			Playing.mousePressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (state == States.PLAYING) {
			Playing.mouseReleased(e);
		}
	}

}