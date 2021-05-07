package com.upb.runrocks.Screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.upb.runrocks.RunRocks;
import static com.upb.runrocks.RunRocks.*;

public abstract  class BaseScreen implements Screen {

    protected RunRocks game; // Para tener acceso al manager
    protected Stage stage; // Para dibujar en los screen

    public BaseScreen(RunRocks game){
        this.game = game;
        this.stage = new Stage(new StretchViewport(WIDTH, HEIGHT, game.cam));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) { stage.getViewport().update(width, height, false); }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.dispose();
        stage.dispose();
    }
}
