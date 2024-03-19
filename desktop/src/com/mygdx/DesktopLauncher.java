package com.mygdx;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.utils.Commons;

import java.io.IOException;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setIdleFPS(30);
		config.setTitle("AstralMayhem");
		config.setWindowIcon("window-logo.jpg");
		config.setWindowedMode(Commons.WINDOW_WIDTH, Commons.WINDOW_HEIGHT);
		config.setResizable(false);
		new Lwjgl3Application(new AstralMayhem(), config);
	}
}
