package com.moon.canyonbunny.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.moon.canyonbunny.CanyonBunnyMain;

/**
 * @author Chen
 */
public class DesktopLauncher {

	private static final String GAME_NAME = "Canyon Bunny";
	private static final int WIDTH = 800;
	private static final int HEIGHT = 400;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = GAME_NAME;
		config.width = WIDTH;
		config.height = HEIGHT;
		new LwjglApplication(new CanyonBunnyMain(), config);
	}
}
