package com.upb.runrocks.actors;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.upb.runrocks.RunRocks;

import static com.upb.runrocks.RunRocks.HEIGHT;
import static com.upb.runrocks.RunRocks.SPEED;
import static com.upb.runrocks.RunRocks.WIDTH;

public class PlayerActor extends Actor {

    private static float GRAVITY = -15;
    public static String TAG = "PLAYER";
    public static float W = 108, H = 90;
    private RunRocks game;
    // Animacion
    // Run
    private Animation<TextureRegion> run;
    private float timeFrame;
    // Jump
    private Animation<TextureRegion> jump;
    // Die
    private Animation<TextureRegion> die;
    // Scene2D
    private Vector2 pos, speed;
    private Rectangle bounds;
    private Sound sndJump, sndHit, sndDie, sndCoin;
    // Variables del jugador
    private int nroCoins = 0, nroLifes = 3;
    private boolean alive = true, jumping = false, mustJump = false;

    public PlayerActor(TextureAtlas atlas, float x, float y, RunRocks game){
        this.game = game;
        sndJump = game.assets.get("audio/jump.ogg");
        sndHit = game.assets.get("audio/hitvoice1.ogg");
        sndDie = game.assets.get("audio/gameover.ogg");
        sndCoin = game.assets.get("audio/coin.ogg");

        Array<TextureAtlas.AtlasRegion> run = atlas.findRegions("run");
        this.run = new Animation<TextureRegion>(5f, run, Animation.PlayMode.LOOP);
        timeFrame = 0;

        Array<TextureAtlas.AtlasRegion> jump = atlas.findRegions("jump");
        this.jump = new Animation<TextureRegion>(10f, jump, Animation.PlayMode.NORMAL);

        Array<TextureAtlas.AtlasRegion> die = atlas.findRegions("die");
        this.die = new Animation<TextureRegion>(20f, die, Animation.PlayMode.NORMAL);

        pos = new Vector2(x, y);
        speed = new Vector2(0, 0);

        setPosition(pos.x, pos.y);
        setSize(W, H);
        bounds = new Rectangle(x + 5 , y, getWidth() - 5, getHeight() - 12);
    }

    public int getNroCoins() { return nroCoins; }

    public int getNroLifes() { return nroLifes; }

    public boolean isAlive() { return alive; }

    public boolean isJumping() { return jumping; }

    public void setMustJump(boolean mustJump) { this.mustJump = mustJump; }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        timeFrame += parentAlpha;
        setPosition(pos.x, pos.y);
        if (jumping) batch.draw(jump.getKeyFrame(timeFrame), getX(), getY(), getWidth(), getHeight());
        else if(alive) batch.draw(run.getKeyFrame(timeFrame), getX(), getY(), getWidth(), getHeight());
        else batch.draw(die.getKeyFrame(timeFrame), getX(), getY(), getWidth(), getHeight());

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        // Iniciar un salto
        if (mustJump){
            mustJump = false;
            jump();
        }

        if (jumping){
            speed.add(0, GRAVITY);
        }

        // Avanzar
        if (alive){
            speed.scl(delta);
            pos.add(SPEED*delta, speed.y);
        }

        if (pos.y < 55){
            pos.y = 55;
            jumping = false;
        }
        speed.scl(1/delta);
        bounds.setPosition(pos.x, pos.y);
    }

    public void jump(){
        if(!jumping && alive) {
            if(game.soundOn) sndJump.play(0.5f);
            jumping = true;
            speed.y = 550;
        }
    }

    public boolean coin(Rectangle coin){
        if (coin.overlaps(bounds)){
            if(game.soundOn) sndCoin.play(0.5f);
            nroCoins ++;
            return true;
        }
        return false;
    }

    public boolean collision(Rectangle rock){
        if(rock.overlaps(bounds)){
            if(game.soundOn) sndHit.play(0.5f);
            nroLifes--;
            if(nroLifes < 0){
                if(game.soundOn) sndDie.play(0.5f);
                alive = false;
                jumping = false;
                return true;
            }
            return true;
        }
        return false;
    }

    public void rePos(float x){
        pos.set(x, pos.y);
        setPosition(x, pos.y);
    }

    public void detach(){
    }
}
