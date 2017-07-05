package com.ninepawns.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ninepawns.game.ninePawns;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = ninePawns.HEIGHT;
		config.width = ninePawns.WIDTH;
		config.title = ninePawns.TITLE;
		new LwjglApplication(new ninePawns(), config);
	}
}
