package com.upb.runrocks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.upb.runrocks.Screen.LoandingScreen;

public class RunRocks extends Game {
	// Constantes de vista
	public static int FONDOHEX = 0x65C1E6FF;
	public static String TITLE = "RUNROCKS";
	public static int WIDTH = 640, HEIGHT = 360;

	// Variables de administracion
	public OrthographicCamera cam;
	public AssetManager assets;

	// Screens
	public LoandingScreen loading;
	
	@Override
	public void create () {
		assets = new AssetManager();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, WIDTH, HEIGHT);

		loading = new LoandingScreen(this);

		setScreen(loading);
	}

	@Override
	public void render () { super.render(); }
	
	@Override
	public void dispose () {
		//Eliminar los recursos de la memoria
		assets.dispose();

	}
}
