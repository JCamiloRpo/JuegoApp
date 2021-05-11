package com.upb.runrocks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.upb.runrocks.RunRocks;
import com.upb.runrocks.actors.FloorActor;
import com.upb.runrocks.actors.PlayerActor;
import com.upb.runrocks.actors.RockActor;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.upb.runrocks.RunRocks.FONDOHEX;
import static com.upb.runrocks.RunRocks.HEIGHT;
import static com.upb.runrocks.RunRocks.SPEED;
import static com.upb.runrocks.RunRocks.WIDTH;

public class GameScreen extends BaseScreen{

    private float camX = 0;
    private Image lifes, coins, btnPause;
    private Skin skin;
    private Label nroCoins;
    private Sound pause;
    // Elementos para pausa
    private Stage stageP;
    private Image bgP, jabaliP, rockP, coinP, dialogP, titleP, btnPlayP, btnLeaveP, btnSettingP, btnCloseP, lifesP, coinsP, iconoP;
    private Label nroCoinsP;

    // Jugador
    private PlayerActor player;
    private List<FloorActor> floors = new ArrayList<>();
    private List<RockActor> rocks = new ArrayList<>();

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
        for (FloorActor f : floors) stage.addActor(f);
        for (RockActor r : rocks) stage.addActor(r);
        stage.addActor(player);
        stage.addActor(lifes);
        stage.addActor(coins);
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
        stageP.addActor(iconoP);

    }

    private void loadComponents() {

        player = new PlayerActor(game.assets.get("jabali/jabali.atlas", TextureAtlas.class), 10, 55, game);
        for (int i=0; i < 3;i++){
            floors.add(new FloorActor(game.assets.get("scene/bg_0.png", Texture.class),
                    game.assets.get("scene/floor_0.png", Texture.class),i * WIDTH));
        }
        for (int i=1; i <= 3; i++){
            rocks.add(new RockActor(game.assets.get("scene/rock_0.png", Texture.class),
                    game.assets.get("icons/coin.png", Texture.class), i*RockActor.GAP, 55));
        }

        lifes = new Image(game.assets.get("icons/heart.png", Texture.class));
        coins = new Image(game.assets.get("icons/coins.png", Texture.class));
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
        nroCoinsP = new Label("012345", skin, "black");
        iconoP = new Image(game.assets.get("icons/icono.png", Texture.class));
    }

    private void setComponents() {
        lifes.setPosition(camX + WIDTH - lifes.getWidth() - btnPause.getWidth() - 20, HEIGHT - btnPause.getHeight() - 5);
        coins.setPosition(camX + 10, HEIGHT - coins.getHeight() - 10);
        btnPause.setPosition(camX + WIDTH - btnPause.getWidth() - 10, HEIGHT - btnPause.getHeight() - 10);
        nroCoins.setPosition(  10 + coins.getX() + coins.getWidth(), HEIGHT - coins.getHeight() - 10);
        // Pausa
        bgP.setSize(stage.getWidth(), stage.getHeight());
        bgP.setPosition(camX, 0);
        jabaliP.setPosition(camX + 10, 60);
        rockP.setPosition(camX + 500, 60);
        coinP.setPosition(camX + 500, rockP.getY() + rockP.getHeight() + 20);
        dialogP.setPosition(camX + (WIDTH - dialogP.getWidth()) / 2, (HEIGHT - dialogP.getHeight()) / 2);
        titleP.setPosition(camX + (WIDTH - titleP.getWidth()) / 2, HEIGHT - dialogP.getY() - 30);
        coinsP.setPosition(camX + ((WIDTH - coinsP.getWidth() - nroCoinsP.getWidth() + 10) / 2) - 5, titleP.getY() - 50);
        nroCoinsP.setPosition(camX + 5 + coinsP.getWidth() + ((WIDTH - coinsP.getWidth() - nroCoinsP.getWidth() + 10) / 2), titleP.getY() - 50);
        lifesP.setPosition(camX + (WIDTH - lifesP.getWidth()) / 2, coinsP.getY() - 50);
        iconoP.setSize(90, 90);
        iconoP.setPosition(camX + WIDTH - iconoP.getWidth() - 5, 5);

        btnLeaveP.setPosition( dialogP.getX() + 10, dialogP.getY() - 20);
        btnSettingP.setPosition( dialogP.getX() + dialogP.getWidth() - btnSettingP.getWidth() - 10, dialogP.getY() - 20);
        btnPlayP.setPosition(camX + (WIDTH - btnPlayP.getWidth()) / 2, dialogP.getY() - 40);
        btnCloseP.setPosition( dialogP.getX() + dialogP.getWidth() - 30,HEIGHT - dialogP.getY() - 30);

    }

    private void addActions() {
        iconoP.addAction(alpha(0.4f));
        btnPause.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (game.soundOn) pause.play(0.5f);
                game.pause = true;
            }
        });

        btnCloseP.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(game.soundOn) pause.play(0.5f);
                game.pause = false;
            }
        });
        btnPlayP.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(game.soundOn) pause.play(0.5f);
                game.pause = false;
            }
        });
        btnLeaveP.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(game.soundOn) pause.play(0.5f);
                game.pause = false;
                game.screens.set(game.screens.newMenu());
            }
        });
        btnSettingP.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(game.soundOn) pause.play(0.5f);
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

            if (player.isAlive()){
                if (player.getX() > 100) {
                    stage.getCamera().translate(SPEED * delta, 0, 0);
                    stage.getCamera().update();

                    camX = stage.getCamera().position.x - (stage.getCamera().viewportWidth/2);
                    setComponents();

                    // Reposicion pisos
                    for (FloorActor f : floors)
                        if (camX > f.getX() + f.getWidth())
                            f.rePos(f.getX() + ((floors.size()-1) * f.getWidth()));
                    // Reposiconar rocas
                    for (RockActor r : rocks)
                        if (camX > r.getX() + r.getWidth())
                            r.rePos(r.getX() + (rocks.size() * RockActor.GAP));
                }

                if(Gdx.input.justTouched() && !player.isJumping()) {
                    player.jump();
                }

                if (Gdx.input.isTouched()) {
                    player.setMustJump(true);
                }
            }

            stage.act(delta);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        player.detach();

        for (FloorActor f : floors) f.detach();
        floors.clear();

        stage.getCamera().translate(0, 0, 0);
        stage.getCamera().update();

        System.out.println("DISPOSE GAME");
    }

    private class GameContactListener implements ContactListener {
        private boolean areCollided(Contact c, Object A, Object B){
            return (c.getFixtureA().getUserData().equals(A) && c.getFixtureB().getUserData().equals(B)) ||
                    (c.getFixtureA().getUserData().equals(B) && c.getFixtureB().getUserData().equals(B));
        }

        @Override
        public void beginContact(Contact contact) {
            if (areCollided(contact, PlayerActor.TAG, FloorActor.TAG)) {
                player.setJumping(false);
                if (Gdx.input.isTouched() && player.isAlive()) {
                    player.setMustJump(true);
                }
            }
        }

        @Override
        public void endContact(Contact contact) {  }

        @Override
        public void preSolve(Contact contact, Manifold oldManifold) { }

        @Override
        public void postSolve(Contact contact, ContactImpulse impulse) { }
    }
}
