package com.ecopopsim.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.ecopopsim.EcosystemPopulationSimGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowSizeLimits(1200, 1200, 1200, 1200);
		new Lwjgl3Application(new EcosystemPopulationSimGame(), config);
	}
}
