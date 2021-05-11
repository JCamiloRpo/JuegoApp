package com.upb.runrocks.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
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
    // Elementos para pausa
    private Stage stageP;
    private Image bgP, jabaliP, rockP, coinP, dialogP, titleP, btnPlayP, btnLeaveP, btnSettingP, btnCloseP, lifesP, coinsP;
    private Label nroCoinsP;

    public GameScreen(RunRocks game) { super(game); }

    @Override
    public void show() {
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
        //Pausa
        stageP = new Stage(new StretchViewport(WIDTH, HEIGHT, game.cam));
        stageP.addActor(bgP);
        stageP.addActor(jabaliP);
        stageP.addActor(rockP);
        stageP.addActor(coinP);
        stageP.addActor(dialogP);
        stageP.addActor(titleP);
        stageP.addActor(coinsP);
        stageP.addActor(nroCoinsP);
        stageP.addActor(lifesP);
        stageP.addActor(btnPlayP);
        stageP.addActor(btnLeaveP);
        stageP.addActor(btnSettingP);
        stageP.addActor(btnCloseP);

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

        // Componentes cuando esta en pausa
        bgP = new Image(game.assets.get("scene/bg_0.png", Texture.class));
        jabaliP = new Image(game.assets.get("jabali/still.png", Texture.class));
        coinP = new Image(game.assets.get("icons/coin.png", Texture.class));
        rockP = new Image(game.assets.get("scene/rock_0.png", Texture.class));
        dialogP = new Image(game.assets.get("dialogs/pausa.png", Texture.class));
        titleP = new Image(game.assets.get("texts/pausa.png", Texture.class));
        btnPlayP = new Image(game.assets.get("buttons/btn_play.png", Texture.class));
        btnLeaveP = new Image(game.assets.get("buttons/btn_leave.png", Texture.class));
        btnSettingP = new Image(game.assets.get("buttons/btn_setting.png", Texture.class));
        btnCloseP = new Image(game.assets.get("buttons/btn_close.png", Texture.class));
        lifesP = new Image(game.assets.get("icons/heart.png", Texture.class));
        coinsP = new Image(game.assets.get("icons/coins.png", Texture.class));
        nroCoinsP = new Label("012345", skin, "default");
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
        // Pausa
        bgP.setSize(stage.getWidth(), stage.getHeight());
        jabaliP.setPosition(10, 60);
        rockP.setPosition(500, 60);
        coinP.setPosition(500, rockP.getY() + rockP.getHeight() + 20);
        dialogP.setPosition((WIDTH - dialogP.getWidth()) / 2, (HEIGHT - dialogP.getHeight()) / 2);
        titleP.setPosition((WIDTH - titleP.getWidth()) / 2, HEIGHT - dialogP.getY() - 30);
        coinsP.setPosition(titleP.getX(), titleP.getY() - 50);
        nroCoinsP.setPosition(coinsP.getX() + coinsP.getWidth() + 10, titleP.getY() - 50);
        lifesP.setPosition((WIDTH - lifes.getWidth()) / 2, coinsP.getY() - 50);

        btnLeaveP.setPosition(dialogP.getX() + 10, dialogP.getY() - 20);
        btnSettingP.setPosition(dialogP.getX() + dialogP.getWidth() - btnSettingP.getWidth() - 10, dialogP.getY() - 20);
        btnPlayP.setPosition((WIDTH - btnPlayP.getWidth()) / 2, dialogP.getY() - 40);
        btnCloseP.setPosition(WIDTH - dialogP.getX() - 30,HEIGHT - dialogP.getY() - 30);
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
        if(game.pause){
            stageP.draw();
        }
        else {
            stage.draw();
        }
    }

    public void update(float delta) {
        if (game.pause){
            Gdx.input.setInputProcessor(stageP);
            stageP.act(delta);
        }
        else {
            Gdx.input.setInputProcessor(stage);
            stage.act(delta);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        System.out.println("DISPOSE MENU");
    }

}
