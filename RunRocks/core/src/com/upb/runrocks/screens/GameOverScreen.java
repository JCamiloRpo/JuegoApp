package com.upb.runrocks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.upb.runrocks.RunRocks;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.upb.runrocks.RunRocks.FONDOHEX;
import static com.upb.runrocks.RunRocks.HEIGHT;
import static com.upb.runrocks.RunRocks.WIDTH;

public class GameOverScreen extends BaseScreen {

    private Image bg, jabali, rock, coin, dialog, title, btnRetry, btnLeave, btnSetting, btnClose, lifes, coins, icono;
    private Label nroCoins;
    private Skin skin;

    public GameOverScreen(RunRocks game) {
        super(game);
    }

    @Override
    public void show() {
        game.gameOver = true;
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
        stage.addActor(jabali);
        stage.addActor(rock);
        stage.addActor(coin);
        stage.addActor(dialog);
        stage.addActor(title);
        stage.addActor(coins);
        stage.addActor(nroCoins);
        stage.addActor(lifes);
        stage.addActor(btnRetry);
        stage.addActor(btnLeave);
        stage.addActor(btnSetting);
        stage.addActor(btnClose);
        stage.addActor(icono);

    }

    private void loadComponents() {
        skin = new Skin(Gdx.files.internal("skin/app.json"));

        bg = new Image(game.assets.get("scene/bg_0.png", Texture.class));
        jabali = new Image(game.assets.get("jabali/die.png", Texture.class));
        coin = new Image(game.assets.get("icons/coin.png", Texture.class));
        rock = new Image(game.assets.get("scene/rock_0.png", Texture.class));
        dialog = new Image(game.assets.get("dialogs/gameover.png", Texture.class));
        title = new Image(game.assets.get("texts/gameover.png", Texture.class));
        btnRetry = new Image(game.assets.get("buttons/btn_restart.png", Texture.class));
        btnLeave = new Image(game.assets.get("buttons/btn_leave.png", Texture.class));
        btnSetting = new Image(game.assets.get("buttons/btn_setting.png", Texture.class));
        btnClose = new Image(game.assets.get("buttons/btn_close.png", Texture.class));
        lifes = new Image(game.assets.get("icons/heart.png", Texture.class));
        coins = new Image(game.assets.get("icons/coins.png", Texture.class));
        nroCoins = new Label("012345", skin, "default");
        icono = new Image(game.assets.get("icons/icono.png", Texture.class));
    }

    private void setComponents() {
        bg.setSize(stage.getWidth(), stage.getHeight());
        jabali.setPosition(10, 60);
        rock.setPosition(500, 60);
        coin.setPosition(500, rock.getY() + rock.getHeight() + 20);
        dialog.setPosition((WIDTH - dialog.getWidth()) / 2, (HEIGHT - dialog.getHeight()) / 2);
        title.setPosition((WIDTH - title.getWidth()) / 2, HEIGHT - dialog.getY() - 30);
        coins.setPosition(((WIDTH - coins.getWidth() - nroCoins.getWidth() + 10) / 2) - 5, title.getY() - 50);
        nroCoins.setPosition(5 + coins.getWidth() + (WIDTH - coins.getWidth() - nroCoins.getWidth() + 10) / 2, title.getY() - 50);
        lifes.setPosition((WIDTH - lifes.getWidth()) / 2, coins.getY() - 50);
        icono.setSize(90, 90);
        icono.setPosition(WIDTH - icono.getWidth() - 5, 5);

        btnRetry.setPosition((WIDTH - btnRetry.getWidth()) / 2, dialog.getY() - 40);
        btnLeave.setPosition(btnRetry.getX() - btnLeave.getWidth() - 10, dialog.getY() - 20);
        btnSetting.setPosition(btnRetry.getX() + btnRetry.getWidth() + 10, dialog.getY() - 20);
        btnClose.setPosition(WIDTH - dialog.getX() - 30,HEIGHT - dialog.getY() - 30);

    }

    private void addActions() {
        icono.addAction(alpha(0.4f));
        dialog.addAction(sequence(alpha(0f), fadeIn(0.5f, Interpolation.pow2) ));
        title.addAction(sequence(alpha(0f), fadeIn(0.5f, Interpolation.pow2) ));
        coins.addAction(sequence(alpha(0f), fadeIn(0.5f, Interpolation.pow2) ));
        nroCoins.addAction(sequence(alpha(0f), fadeIn(0.5f, Interpolation.pow2) ));
        lifes.addAction(sequence(alpha(0f), fadeIn(0.5f, Interpolation.pow2) ));
        btnClose.addAction(sequence(alpha(0f), fadeIn(0.5f, Interpolation.pow2) ));
        btnClose.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(game.soundOn) game.clicked.play(0.5f);
                game.screens.set(game.screens.newMenu());
            }
        });
        btnRetry.addAction(sequence(alpha(0f), fadeIn(0.5f, Interpolation.pow2) ));
        btnRetry.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(game.soundOn) game.clicked.play(0.5f);
                game.gameOver = false;
                game.screens.set(game.screens.newGame());
            }
        });
        btnLeave.addAction(sequence(alpha(0f), fadeIn(0.5f, Interpolation.pow2) ));
        btnLeave.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(game.soundOn) game.clicked.play(0.5f);
                game.screens.set(game.screens.newMenu());
            }
        });
        btnSetting.addAction(sequence(alpha(0f), fadeIn(0.5f, Interpolation.pow2) ));
        btnSetting.addCaptureListener(new ClickListener() {
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

    public void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
        System.out.println("DISPOSE MENU");
    }

}