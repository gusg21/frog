package io.github.gusg21.frog.launcher;

import io.github.gusg21.frog.TileGame;

public class Launcher {

	public static void main(String[] args) {
		TileGame ex = new TileGame();
		new Thread(ex).start();
	}

}
