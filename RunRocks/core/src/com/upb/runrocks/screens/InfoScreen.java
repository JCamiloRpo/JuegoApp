package com.upb.runrocks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.upb.runrocks.RunRocks;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.upb.runrocks.RunRocks.FONDOHEX;
import static com.upb.runrocks.RunRocks.HEIGHT;
import static com.upb.runrocks.RunRocks.WIDTH;

public class InfoScreen extends BaseScreen {

    private Image bg, dialog, title, descripcion, autores, btnClose, icono;

    public InfoScreen(RunRocks game) { super(game); }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        //Inicializar elementos
        loadComponents();
        //Posicionar elementos
        setComponents();
        //Añadir acciones
        addActions();
        //Agregar al stage
        stage.addActor(bg);
        stage.addActor(dialog);
        stage.addActor(title);
        stage.addActor(descripcion);
        stage.addActor(autores);
        stage.addActor(btnClose);
        stage.addActor(icono);

    }

    private void loadComponents() {
        bg = new Image(game.assets.get("scene/bg_0.png", Texture.class));
        dialog = new Image(game.assets.get("dialogs/acercade.png", Texture.class));
        title = new Image(game.assets.get("texts/acercade.png", Texture.class));
        icono = new Image(game.assets.get("icons/icono.png", Texture.class));
        descripcion = new Image(game.assets.get("texts/descripcion.png", Texture.class));
        autores = new Image(game.assets.get("texts/autores.png", Texture.class));
        btnClose = new Image(game.assets.get("buttons/btn_close.png", Texture.class));
    }

    private void setComponents() {
        bg.setSize(stage.getWidth(), stage.getHeight());
        dialog.setPosition((WIDTH - dialog.getWidth()) / 2, (HEIGHT - dialog.getHeight()) / 2);
        title.setPosition((WIDTH - title.getWidth()) / 2, HEIGHT - dialog.getY() - 30);
        icono.setSize(90, 90);
        icono.setPosition(WIDTH - icono.getWidth() - 5, 5);
        descripcion.setPosition((WIDTH - descripcion.getWidth()) / 2 , title.getY() - descripcion.getHeight());
        autores.setPosition((WIDTH - autores.getWidth()) / 2, descripcion.getY() - autores.getHeight());
        btnClose.setPosition(WIDTH - dialog.getX() - 30,HEIGHT - dialog.getY() - 30);
    }

    private void addActions() {
        icono.addAction(alpha(0.4f));
        dialog.addAction(sequence(alpha(0f), fadeIn(0.5f, Interpolation.pow2) ));
        title.addAction(sequence(alpha(0f), fadeIn(0.5f, Interpolation.pow2) ));
        descripcion.addAction(sequence(alpha(0f), fadeIn(0.5f, Interpolation.pow2) ));
        autores.addAction(sequence( alpha(0f), fadeIn(0.5f, Interpolation.pow2) ));
        btnClose.addAction(sequence( alpha(0f), fadeIn(0.5f, Interpolation.pow2) ));
        btnClose.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(game.soundOn) game.clicked.play(0.5f);
                game.screens.set(game.screens.newSetting());
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

    public void update(float delta){
        stage.act(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
        System.out.println("DISPOSE INFO");
    }

}
