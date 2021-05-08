package com.upb.runrocks.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.upb.runrocks.RunRocks;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.upb.runrocks.RunRocks.*;

public class SettingClass extends BaseScreen {

    private Image bg, jabali, dialog, title, btnMusic, btnSound, btnInfo, icono, btnClose, btnMusicAxu, btnSoundAxu;

    public SettingClass(RunRocks game) { super(game); }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        clicked = game.assets.get("audio/click.ogg");
        //Inicializar elementos
        loadComponents();
        //Posicionar elementos
        setComponents();
        //Añadir acciones
        addActions();
        //Agregar al stage
        stage.addActor(bg);
        stage.addActor(jabali);
        stage.addActor(dialog);
        stage.addActor(title);
        stage.addActor(btnMusic);
        stage.addActor(btnSound);
        stage.addActor(btnInfo);
        stage.addActor(btnClose);
        stage.addActor(icono);

    }

    private void loadComponents() {
        bg = new Image(game.assets.get("scene/bg_0.png", Texture.class));
        jabali = new Image(game.assets.get("jabali/die.png", Texture.class));
        dialog = new Image(game.assets.get("dialogs/opciones.png", Texture.class));
        title = new Image(game.assets.get("texts/opciones.png", Texture.class));
        icono = new Image(game.assets.get("icons/icono.png", Texture.class));
        btnInfo = new Image(game.assets.get("buttons/btn_info.png", Texture.class));
        btnClose = new Image(game.assets.get("buttons/btn_close.png", Texture.class));

        if(game.music){
            btnMusic = new Image(game.assets.get("buttons/btn_music.png", Texture.class));
            btnMusicAxu = new Image(game.assets.get("buttons/btn_music_off.png", Texture.class));
        }
        else {
            btnMusic = new Image(game.assets.get("buttons/btn_music_off.png", Texture.class));
            btnMusicAxu = new Image(game.assets.get("buttons/btn_music.png", Texture.class));
        }
        if (game.sound){
            btnSound = new Image(game.assets.get("buttons/btn_sound.png", Texture.class));
            btnSoundAxu = new Image(game.assets.get("buttons/btn_sound_off.png", Texture.class));
        }
        else {
            btnSound = new Image(game.assets.get("buttons/btn_sound_off.png", Texture.class));
            btnSoundAxu = new Image(game.assets.get("buttons/btn_sound.png", Texture.class));
        }
    }

    private void setComponents() {
        bg.setSize(stage.getWidth(), stage.getHeight());
        jabali.setPosition(10, 55);
        dialog.setPosition((WIDTH - dialog.getWidth()) / 2, (HEIGHT - dialog.getHeight()) / 2);
        title.setPosition((WIDTH - title.getWidth()) / 2, HEIGHT - dialog.getY() - 30);
        icono.setSize(90, 90);
        icono.setPosition(WIDTH - icono.getWidth() - 5, 5);
        btnMusic.setPosition(dialog.getX() + 20, (HEIGHT - btnMusic.getHeight()) / 2);
        btnSound.setPosition((WIDTH - btnSound.getWidth()) / 2 , (HEIGHT - btnSound.getHeight()) / 2);
        btnInfo.setPosition(dialog.getX() + dialog.getWidth() - btnInfo.getWidth() - 20, (HEIGHT - btnInfo.getHeight()) / 2);
        btnClose.setPosition(WIDTH - dialog.getX() - 30,HEIGHT - dialog.getY() - 30);
    }

    private void addActions() {
        icono.addAction(alpha(0.4f));
        jabali.addAction(sequence(alpha(0f), fadeIn(0.5f, Interpolation.pow2) ));
        dialog.addAction(sequence(alpha(0f), fadeIn(0.5f, Interpolation.pow2) ));
        title.addAction(sequence(alpha(0f), fadeIn(0.5f, Interpolation.pow2) ));
        btnMusic.addAction(sequence(alpha(0f), fadeIn(0.5f, Interpolation.pow2) ));
        btnMusic.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(game.sound) clicked.play(0.5f);
                Drawable tmp = btnMusic.getDrawable();
                if(game.music){
                    btnMusic.setDrawable(btnMusicAxu.getDrawable());
                    btnMusicAxu.setDrawable(tmp);
                    game.music = false;
                }
                else {
                    btnMusic.setDrawable(btnMusicAxu.getDrawable());
                    btnMusicAxu.setDrawable(tmp);
                    game.music = true;
                }

            }
        });
        btnSound.addAction(sequence( alpha(0f), fadeIn(0.5f, Interpolation.pow2) ));
        btnSound.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(game.sound) clicked.play(0.5f);
                Drawable tmp = btnSound.getDrawable();
                if(game.sound){
                    btnSound.setDrawable(btnSoundAxu.getDrawable());
                    btnSoundAxu.setDrawable(tmp);
                    game.sound = false;
                }
                else {
                    btnSound.setDrawable(btnSoundAxu.getDrawable());
                    btnSoundAxu.setDrawable(tmp);
                    game.sound = true;
                }
            }
        });
        btnInfo.addAction(sequence( alpha(0f), fadeIn(0.5f, Interpolation.pow2) ));
        btnInfo.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(game.sound) clicked.play(0.5f);
                game.setScreen(game.setting);
            }
        });
        btnClose.addAction(sequence( alpha(0f), fadeIn(0.5f, Interpolation.pow2) ));
        btnClose.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(game.sound) clicked.play(0.5f);
                game.setScreen(game.menu);
            }
        });
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

    @Override
    public void dispose() {
        System.out.println("DISPOSE MENU");
        stage.dispose();
    }

    public void update(float delta){
        stage.act(delta);
    }

}
