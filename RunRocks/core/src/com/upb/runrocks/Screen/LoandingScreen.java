package com.upb.runrocks.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.upb.runrocks.RunRocks;

import static com.upb.runrocks.RunRocks.*;

public class LoandingScreen extends BaseScreen {

    private ShapeRenderer shape;
    private float progress;

    public LoandingScreen(RunRocks game) {
        super(game);
        this.shape = new ShapeRenderer();
    }

    @Override
    public void show() {
        shape.setProjectionMatrix(game.cam.combined);
        progress = 0;
        queuAssets();
    }

    @Override
    public void render(float delta) {
        // Limpiar la pantalla
        ScreenUtils.clear(new Color(FONDOHEX));
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta); // Actualizar

        // Dibujar
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(new Color(0x3F1100FF));
        shape.rect(32, game.cam.viewportHeight/2 - 8, game.cam.viewportWidth - 64, 16);

        shape.setColor(new Color(0xFFC00DFF));
        shape.rect(32, game.cam.viewportHeight/2 - 8, progress * (game.cam.viewportWidth - 64), 16);
        shape.end();

    }

    @Override
    public void dispose() {
        shape.dispose();
    }

    private void update(float delta) {
        progress = MathUtils.lerp(progress, game.assets.getProgress(), 0.1f);

        if (game.assets.update() && progress >= game.assets.getProgress() - 0.001f){
            game.setScreen(this);
        }
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
        game.assets.load("jabali/jabali.atlas", TextureAtlas.class);
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
}
