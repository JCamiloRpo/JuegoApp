package com.upb.runrocks.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import com.upb.runrocks.RunRocks;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.upb.runrocks.RunRocks.*;

public class SplashScreen extends BaseScreen {

    private float progress;
    private Image logo, bgProgress, clProgress;

    public SplashScreen(RunRocks game) {
        super(game);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        logo = new Image(new Texture("icons/runrocks.png"));
        logo.setSize(220, 220);
        logo.setPosition((WIDTH - logo.getWidth()) / 2, HEIGHT );
        logo.addAction( sequence(
                alpha(0f), scaleTo( 0.2f, 0.2f), parallel(
                        moveTo((WIDTH - logo.getWidth()) / 2, ((HEIGHT - logo.getHeight()) / 2) + 40, 2f, Interpolation.swing) ,
                        fadeIn(2f, Interpolation.pow2), scaleTo(1f, 1f, 2f, Interpolation.pow2))
        ));
        stage.addActor(logo);

        bgProgress = new Image(new Texture("buttons/progress-bar-empty.png"));
        bgProgress.setPosition((WIDTH - bgProgress.getWidth()) / 2, 30 );
        stage.addActor(bgProgress);
        clProgress = new Image(new Texture("buttons/progress-bar-knof.png"));
        clProgress.setSize(0,clProgress.getHeight());
        clProgress.setPosition(bgProgress.getX()+5, 35 );
        stage.addActor(clProgress);

        progress = 0;
        queuAssets();
    }

    private void queuAssets() {
        // Cargar audios
        game.assets.load("audio/music.mp3", Music.class);
        game.assets.load("audio/click.ogg", Sound.class);
        game.assets.load("audio/coin.ogg", Sound.class);
        game.assets.load("audio/hitvoice1.ogg", Sound.class);
        game.assets.load("audio/jump.ogg", Sound.class);
        game.assets.load("audio/pause.ogg", Sound.class);
        game.assets.load("audio/start.ogg", Sound.class);
        // Cargar buttons
        game.assets.load("buttons/btn_check.png", Texture.class);
        game.assets.load("buttons/btn_close.png", Texture.class);
        game.assets.load("buttons/btn_info.png", Texture.class);
        game.assets.load("buttons/btn_leave.png", Texture.class);
        game.assets.load("buttons/btn_music.png", Texture.class);
        game.assets.load("buttons/btn_music_off.png", Texture.class);
        game.assets.load("buttons/btn_pause.png", Texture.class);
        game.assets.load("buttons/btn_play.png", Texture.class);
        game.assets.load("buttons/btn_restart.png", Texture.class);
        game.assets.load("buttons/btn_setting.png", Texture.class);
        game.assets.load("buttons/btn_sound.png", Texture.class);
        game.assets.load("buttons/btn_sound_off.png", Texture.class);
        // Cargar dialogs
        game.assets.load("dialogs/acercade.png", Texture.class);
        game.assets.load("dialogs/gameover.png", Texture.class);
        game.assets.load("dialogs/menu.png", Texture.class);
        game.assets.load("dialogs/opciones.png", Texture.class);
        game.assets.load("dialogs/pausa.png", Texture.class);
        // Cargar jabalis
        game.assets.load("jabali/still.png", Texture.class);
        game.assets.load("jabali/die.png", Texture.class);
        game.assets.load("jabali/jabali.atlas", TextureAtlas.class);
        game.assets.load("icons/icono.png", Texture.class);
        // Cargar elementos de scenes
        game.assets.load("scene/bg_0.png", Texture.class);
        game.assets.load("scene/bg_1.png", Texture.class);
        game.assets.load("scene/floor_0.png", Texture.class);
        game.assets.load("scene/floor_1.png", Texture.class);
        game.assets.load("scene/floor_2.png", Texture.class);
        game.assets.load("scene/planet_0.png", Texture.class);
        game.assets.load("scene/planet_1.png", Texture.class);
        game.assets.load("scene/planet_2.png", Texture.class);
        game.assets.load("scene/planet_3.png", Texture.class);
        game.assets.load("scene/planet_4.png", Texture.class);
        game.assets.load("scene/planet_5.png", Texture.class);
        game.assets.load("scene/planet_6.png", Texture.class);
        game.assets.load("scene/planet_7.png", Texture.class);
        game.assets.load("scene/planet_8.png", Texture.class);
        game.assets.load("scene/rock_0.png", Texture.class);
        game.assets.load("scene/rock_1.png", Texture.class);
        game.assets.load("scene/rock_2.png", Texture.class);
        game.assets.load("scene/rock_3.png", Texture.class);
        game.assets.load("scene/rock_4.png", Texture.class);
        game.assets.load("scene/rock_5.png", Texture.class);
        // Cargar texts
        game.assets.load("texts/acercade.png", Texture.class);
        game.assets.load("texts/autores.png", Texture.class);
        game.assets.load("texts/descripcion.png", Texture.class);
        game.assets.load("texts/gameover.png", Texture.class);
        game.assets.load("texts/opciones.png", Texture.class);
        game.assets.load("texts/pausa.png", Texture.class);
        game.assets.load("texts/runrocks.png", Texture.class);

    }

    @Override
    public void render(float delta) {
        // Limpiar la pantalla
        ScreenUtils.clear(new Color(FONDOHEX));
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Actualizar
        update(delta);
        // Dibujar
        stage.draw();

    }

    private void update(float delta) {
        stage.act(delta);
        progress = MathUtils.lerp(progress, game.assets.getProgress(), 0.1f);
        clProgress.setSize(progress * (bgProgress.getWidth()-10),clProgress.getHeight());

        if (game.assets.update() && progress >= game.assets.getProgress() - 0.001f){
            game.clicked = game.assets.get("audio/click.ogg");
            game.music = game.assets.get("audio/music.mp3");
            game.music.setVolume(0.5f);
            if(game.musicOn) game.music.play();

            game.screens.set(game.screens.newMenu());
        }


    }

    @Override
    public void dispose() {
        super.dispose();
        System.out.println("DISPOSE SPLASH");
    }

}
