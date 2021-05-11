package com.upb.runrocks.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.upb.runrocks.RunRocks;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.upb.runrocks.RunRocks.FONDOHEX;
import static com.upb.runrocks.RunRocks.HEIGHT;
import static com.upb.runrocks.RunRocks.WIDTH;

public class GameScreen extends BaseScreen{

    private Image bg, jabali, lifes, coins, rock, coin, btnPause;
    private Skin skin;
    private Label nroCoins;
    private Sound pause;

    public GameScreen(RunRocks game) { super(game); }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.clear();

        pause = game.assets.get("audio/pause.ogg");
        //Inicializar elementos
        loadComponents();
        //Posicionar elementos
        setComponents();
        //Añadir acciones
        addActions();
        //Agregar al stage
        stage.addActor(bg);
        stage.addActor(jabali);
        stage.addActor(lifes);
        stage.addActor(coins);
        stage.addActor(rock);
        stage.addActor(coin);
        stage.addActor(nroCoins);
        stage.addActor(btnPause);

    }

    private void loadComponents() {
        bg = new Image(game.assets.get("scene/bg_0.png", Texture.class));
        jabali = new Image(game.assets.get("jabali/still.png", Texture.class));
        lifes = new Image(game.assets.get("icons/heart.png", Texture.class));
        coins = new Image(game.assets.get("icons/coins.png", Texture.class));
        coin = new Image(game.assets.get("icons/coin.png", Texture.class));
        rock = new Image(game.assets.get("scene/rock_0.png", Texture.class));
        btnPause = new Image(game.assets.get("buttons/btn_pause.png", Texture.class));

        skin = new Skin(Gdx.files.internal("skin/app.json"));
        nroCoins = new Label("012345", skin, "default");
    }

    private void setComponents() {
        bg.setSize(stage.getWidth(), stage.getHeight());

        jabali.setPosition(10, 60);

        lifes.setPosition(WIDTH - lifes.getWidth() - btnPause.getWidth() - 20, HEIGHT - btnPause.getHeight() - 5);

        coins.setPosition(10, HEIGHT - coins.getHeight() - 10);

        rock.setPosition(500, 60);

        coin.setPosition(500, rock.getY() + rock.getHeight() + 20);

        btnPause.setPosition(WIDTH - btnPause.getWidth() - 10, HEIGHT - btnPause.getHeight() - 10);

        nroCoins.setPosition(10 + coins.getX() + coins.getWidth(), HEIGHT - coins.getHeight() - 10);
        nroCoins.setAlignment(Align.left);
    }

    private void addActions() {

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
