package com.upb.runrocks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.upb.runrocks.Screen.MenuScreen;
import com.upb.runrocks.Screen.SettingClass;
import com.upb.runrocks.Screen.SplashScreen;

public class RunRocks extends Game {
	// Constantes de vista
	public static int FONDOHEX = 0x65C1E6FF;
	public static String TITLE = "RUNROCKS";
	public static int WIDTH = 640, HEIGHT = 360;

	// Variables de administracion
	public OrthographicCamera cam;
	public AssetManager assets;
	public boolean music = true, sound = true;

	// Screens
	public SplashScreen splash;
	public MenuScreen menu;
	public SettingClass setting;
	
	@Override
	public void create () {
		assets = new AssetManager();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, WIDTH, HEIGHT);

		// Inicializacion de Screen
		splash = new SplashScreen(this);
		menu = new MenuScreen(this);
		setting = new SettingClass(this);

		setScreen(splash);
	}

	@Override
	public void render () { super.render(); }
	
	@Override
	public void dispose () {
		//Eliminar los recursos de la memoria
		System.out.println("Dispose de las Screen");
		assets.dispose();
		splash.dispose();
		menu.dispose();
	}
}
