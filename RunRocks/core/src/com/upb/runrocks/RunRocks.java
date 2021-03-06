package com.upb.runrocks;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.upb.runrocks.screens.ScreenManager;

public class RunRocks extends Game {
	// Constantes de vista
	public static int FONDOHEX = 0x65C1E6FF;
	public static String TITLE = "RUNROCKS";
	public static int WIDTH = 640, HEIGHT = 360;
	public static float VOLS = 0.5f, VOLM = 0.4f;

	// Variables de administracion
	public OrthographicCamera cam;
	public AssetManager assets;
	public ScreenManager screens;

	public Sound clicked, start;
	public Music music;
	public boolean musicOn = true, soundOn = true, pause = false, gameOver = false;
	public int nroCoins = 0;

	@Override
	public void create () {
		assets = new AssetManager();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, WIDTH, HEIGHT);

		// Inicializacion de Screen
		screens = new ScreenManager(this);
		screens.set(screens.newSplash());
	}

	@Override
	public void render () { super.render(); }

	@Override
	public void dispose () {
		assets.dispose();
		screens.dispose();
		music.dispose();
		clicked.dispose();
	}
}
