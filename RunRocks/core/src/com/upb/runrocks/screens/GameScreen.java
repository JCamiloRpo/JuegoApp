package com.upb.runrocks.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.upb.runrocks.RunRocks;
import com.upb.runrocks.actors.ActorManager;
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

    private static float camX;
    private Image lifes[], coins, btnPause;
    private Skin skin;
    private Label nroCoins;
    private Sound pause;
    // Elementos para pausa
    private Stage stageP;
    private Image bgP, jabaliP, rockP, coinP, dialogP, titleP, btnPlayP, btnLeaveP, btnSettingP, btnCloseP, lifesP[], lifeOff, coinsP, iconoP;
    private Label nroCoinsP;

    // Actores
    private ActorManager actors;
    private PlayerActor player;
    private List<FloorActor> floors = new ArrayList<>();
    private List<RockActor> rocks = new ArrayList<>();

    public GameScreen(RunRocks game) {
        super(game);
        stage.clear();
        stageP = new Stage(new StretchViewport(WIDTH, HEIGHT, game.cam));
        camX = 0;

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
        stage.addActor(lifes[0]);
        stage.addActor(lifes[1]);
        stage.addActor(lifes[2]);
        stage.addActor(coins);
        stage.addActor(nroCoins);
        stage.addActor(btnPause);

        //Pausa
        stageP.addActor(bgP);
        stageP.addActor(jabaliP);
        stageP.addActor(rockP);
        stageP.addActor(coinP);
        stageP.addActor(dialogP);
        stageP.addActor(titleP);
        stageP.addActor(coinsP);
        stageP.addActor(nroCoinsP);
        stageP.addActor(lifesP[0]);
        stageP.addActor(lifesP[1]);
        stageP.addActor(lifesP[2]);
        stageP.addActor(btnPlayP);
        stageP.addActor(btnLeaveP);
        stageP.addActor(btnSettingP);
        stageP.addActor(btnCloseP);
        stageP.addActor(iconoP);
    }

    @Override
    public void show() {
        //camX = stage.getCamera().position.x - (stage.getCamera().viewportWidth/2);
        System.out.println(camX);
        /*
        if (player != null) player.rePos(camX + 50);
        if (!floors.isEmpty()) {
            for (int i=0; i < 4;i++)
                floors.get(i).rePos(camX + (i * floors.get(i).getWidth()));
        }
        if (!rocks.isEmpty()){
            for (int i=0; i < 6; i++)
                rocks.get(i).rePos(camX + (i+1)*RockActor.GAP);
        }
        */
        if (camX > 0){
            stage.getCamera().translate(camX, 0, 0);
        }
        setComponents();
    }

    private void loadComponents() {
        actors = new ActorManager(game);
        player = actors.createPlayer(10);

        floors.add(actors.createFloor(0, -1));
        for (int i=0; i < 3;i++){
            floors.add(actors.createFloor(i+1, i));
        }
        for (int i=0; i < 6; i++){
            rocks.add(actors.createRock(i, i));
        }


        lifeOff = new Image(game.assets.get("icons/heart_off.png", Texture.class));
        lifes = new Image[]{ new Image(game.assets.get("icons/heart.png", Texture.class)),
                new Image(game.assets.get("icons/heart.png", Texture.class)),
                new Image(game.assets.get("icons/heart.png", Texture.class))};
        coins = new Image(game.assets.get("icons/coins.png", Texture.class));
        btnPause = new Image(game.assets.get("buttons/btn_pause.png", Texture.class));

        skin = new Skin(Gdx.files.internal("skin/app.json"));
        nroCoins = new Label(player.getNroCoins()+"", skin, "default");

        // Componentes cuando esta en pausa
        bgP = new Image(floors.get(0).getBg());
        coinP = new Image(rocks.get(0).getCoin());
        rockP = new Image(rocks.get(0).getRock());
        lifesP = new Image[]{ new Image(game.assets.get("icons/heart.png", Texture.class)),
                new Image(game.assets.get("icons/heart.png", Texture.class)),
                new Image(game.assets.get("icons/heart.png", Texture.class))};

        jabaliP = new Image(game.assets.get("jabali/still.png", Texture.class));
        coinsP = new Image(game.assets.get("icons/coins.png", Texture.class));
        nroCoinsP = new Label(player.getNroCoins()+"", skin, "black");

        dialogP = new Image(game.assets.get("dialogs/pausa.png", Texture.class));
        titleP = new Image(game.assets.get("texts/pausa.png", Texture.class));
        btnPlayP = new Image(game.assets.get("buttons/btn_play.png", Texture.class));
        btnLeaveP = new Image(game.assets.get("buttons/btn_leave.png", Texture.class));
        btnSettingP = new Image(game.assets.get("buttons/btn_setting.png", Texture.class));
        btnCloseP = new Image(game.assets.get("buttons/btn_close.png", Texture.class));
        iconoP = new Image(game.assets.get("icons/icono.png", Texture.class));
    }

    private void setComponents() {
        btnPause.setPosition(camX + WIDTH - btnPause.getWidth() - 10, HEIGHT - btnPause.getHeight() - 10);
        lifes[0].setPosition(btnPause.getX() - lifes[0].getWidth() - 20, HEIGHT - 55);
        lifes[1].setPosition(lifes[0].getX() - lifes[0].getWidth(), HEIGHT - 55);
        lifes[2].setPosition(lifes[1].getX() - lifes[0].getWidth(), HEIGHT - 55);

        coins.setPosition(camX + 10, HEIGHT - coins.getHeight() - 10);
        nroCoins.setPosition(  10 + coins.getX() + coins.getWidth(), HEIGHT - coins.getHeight() - 10);
        //Pausa
        setUpPause();
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
                game.screens.push(game.screens.newSetting());
            }
        });

    }

    private void setUpPause(){
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

        lifesP[2].setPosition(camX - lifesP[0].getWidth() + (WIDTH - lifesP[0].getWidth()) / 2, coinsP.getY() - 50);
        lifesP[1].setPosition(camX + (WIDTH - lifesP[0].getWidth()) / 2, coinsP.getY() - 50);
        lifesP[0].setPosition(camX + lifesP[0].getWidth() + (WIDTH - lifesP[0].getWidth()) / 2, coinsP.getY() - 50);

        iconoP.setSize(90, 90);
        iconoP.setPosition(camX + WIDTH - iconoP.getWidth() - 5, 5);

        btnLeaveP.setPosition( dialogP.getX() + 10, dialogP.getY() - 20);
        btnSettingP.setPosition( dialogP.getX() + dialogP.getWidth() - btnSettingP.getWidth() - 10, dialogP.getY() - 20);
        btnPlayP.setPosition(camX + (WIDTH - btnPlayP.getWidth()) / 2, dialogP.getY() - 40);
        btnCloseP.setPosition( dialogP.getX() + dialogP.getWidth() - 30,HEIGHT - dialogP.getY() - 30);
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
                if (player.getX() > 50) {
                    stage.getCamera().translate(SPEED * delta, 0, 0);

                    camX = stage.getCamera().position.x - (stage.getCamera().viewportWidth/2);
                    setComponents();

                    // Reposicion pisos
                    for (FloorActor f : floors)
                        if (camX > f.getX() + f.getWidth())
                            f.rePos(f.getX() + (floors.size() * f.getWidth()));

                    for (RockActor r : rocks) {
                        // Colisiones
                        if(r.rockOn && player.collision(r.getBoundsRock())){
                            r.rockOn = false;
                            if (player.getNroLifes() >= 0){
                                lifes[player.getNroLifes()].setDrawable(lifeOff.getDrawable());
                                lifesP[player.getNroLifes()].setDrawable(lifeOff.getDrawable());
                            }
                        }
                        if(r.coinOn && player.coin(r.getBoundsCoin())){
                            r.coinOn = false;
                            nroCoins.setText(player.getNroCoins()+"");
                            nroCoinsP.setText(player.getNroCoins()+"");
                        }

                        // Reposiconar rocas
                        if (camX > r.getX() + r.getWidth())
                            r.rePos(r.getX() + (rocks.size() * RockActor.GAP));
                    }
                }

                if(Gdx.input.justTouched() && !player.isJumping()) {
                    player.jump();
                }

                if (Gdx.input.isTouched()) {
                    player.setMustJump(true);
                }
            }
            else {
                Runnable trans = new Runnable() {
                    @Override
                    public void run() {
                        game.nroCoins = player.getNroCoins();
                        game.screens.set(game.screens.newGameOver());
                    }
                };
                player.addAction(sequence(delay(3f), run(trans)));
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

        for (RockActor r : rocks) r.detach();
        rocks.clear();

        stageP.dispose();
        game.cam.translate(0, 0, 0);
        System.out.println("DISPOSE GAME");
    }
}
