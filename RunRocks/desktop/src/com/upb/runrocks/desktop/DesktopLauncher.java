package com.upb.runrocks.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.upb.runrocks.RunRocks;

import static com.upb.runrocks.RunRocks.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = TITLE;
		config.width = WIDTH;
		config.height = HEIGHT;
		config.x = 0;
		config.y = 0;
		new LwjglApplication(new RunRocks(), config);
	}
}
