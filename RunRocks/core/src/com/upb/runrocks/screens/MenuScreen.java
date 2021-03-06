package com.upb.runrocks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.upb.runrocks.RunRocks;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.upb.runrocks.RunRocks.*;

public class MenuScreen extends BaseScreen {

    private Image bg, jabali, dialog, title, btnPlay, btnLeave, btnSetting, icono;

    public MenuScreen(RunRocks game) { super(game); }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        game.start = game.assets.get("audio/start.mp3");
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
        stage.addActor(btnPlay);
        stage.addActor(btnLeave);
        stage.addActor(btnSetting);
        stage.addActor(icono);

    }

    /**
     * Obtener los componentes
     */
    private void loadComponents() {
        bg = new Image(game.assets.get("scene/bg_0.png", Texture.class));
        jabali = new Image(game.assets.get("jabali/still.png", Texture.class));
        dialog = new Image(game.assets.get("dialogs/menu.png", Texture.class));
        title = new Image(game.assets.get("texts/runrocks.png", Texture.class));
        icono = new Image(game.assets.get("icons/icono.png", Texture.class));
        btnPlay = new Image(game.assets.get("buttons/btn_play.png", Texture.class));
        btnLeave = new Image(game.assets.get("buttons/btn_leave.png", Texture.class));
        btnSetting = new Image(game.assets.get("buttons/btn_setting.png", Texture.class));
    }

    /**
     * Configurar los componentes: tamaño y posicion
     */
    private void setComponents() {
        bg.setSize(WIDTH, HEIGHT);

        jabali.setPosition(10, 60);
        dialog.setPosition((WIDTH - dialog.getWidth()) / 2, (HEIGHT - dialog.getHeight()) / 2);
        title.setPosition((WIDTH - title.getWidth()) / 2, HEIGHT - dialog.getY() - 30);
        icono.setSize(90, 90);
        icono.setPosition(WIDTH - icono.getWidth() - 5, 5);
        btnLeave.setPosition(dialog.getX() + 10, (HEIGHT - btnLeave.getHeight()) / 2);
        btnSetting.setPosition(dialog.getX() + dialog.getWidth() - btnSetting.getWidth() - 10, (HEIGHT - btnSetting.getHeight()) / 2);
        btnPlay.setPosition((WIDTH - btnPlay.getWidth()) / 2, (HEIGHT - btnPlay.getHeight()) / 2);
    }

    /**
     * Agregar acciones y animaciones
     */
    private void addActions() {
        icono.addAction(alpha(0.4f));
        //jabali.addAction(sequence(alpha(0f), fadeIn(0.5f, Interpolation.pow2)));
        dialog.addAction(sequence(alpha(0f), fadeIn(0.5f, Interpolation.pow2)));
        title.addAction(sequence(alpha(0f), fadeIn(0.5f, Interpolation.pow2)));
        btnLeave.addAction(sequence(alpha(0f), fadeIn(0.5f, Interpolation.pow2)));
        btnLeave.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (game.soundOn) game.clicked.play(0.5f);
                delay(1f);
                Gdx.app.exit();
            }
        });
        btnSetting.addAction(sequence(alpha(0f), fadeIn(0.5f, Interpolation.pow2)));
        btnSetting.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (game.soundOn) game.clicked.play(0.5f);
                game.screens.set(game.screens.newSetting());
            }
        });
        btnPlay.addAction(sequence(alpha(0f), fadeIn(0.5f, Interpolation.pow2)));
        btnPlay.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (game.soundOn){
                    game.clicked.play(0.5f);
                    game.start.play(0.5f);
                }
                game.screens.set(game.screens.newGame());
            }
        });
    }

    @Override
    public void render(float delta) {
        // Limpiar la pantalla
        ScreenUtils.clear(new Color(FONDOHEX));
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)){
            System.out.println("MENU");
            if(game.soundOn) game.clicked.play(0.5f);
            Gdx.app.exit();
        }
        // Actualizar
        update(delta);
        // Dibujar
        stage.draw();
    }

    public void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
        //System.out.println("DISPOSE MENU");
    }

}
