package com.upb.runrocks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.upb.runrocks.RunRocks;
import static com.upb.runrocks.RunRocks.*;

/**
 * Case base para los Screen, para no tener que implementar todos los metodos
 * sino que solo los necesarios para la respectiva clase
 */
public abstract  class BaseScreen implements Screen {

    protected RunRocks game;    // Para tener acceso al manager
    protected Stage stage;      // Para dibujar en los screen

    public BaseScreen(RunRocks game){
        this.game = game;
        this.stage = new Stage(new StretchViewport(WIDTH, HEIGHT, game.cam));
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) { }

    @Override
    public void resize(int width, int height) { stage.getViewport().update(width, height, false); }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { Gdx.input.setInputProcessor(null); }

    @Override
    public void dispose() { if (stage != null) stage.dispose(); }
}
