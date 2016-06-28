package com.sbtwork.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sbtwork.game.xball;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = xball.HEIGHT;
		config.width = xball.WIDTH;
		new LwjglApplication(new xball(), config);
	}
}
