package com.upb.runrocks.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class RockActor extends Actor {

    public static String TAG = "ROCK";
    public static float GAP = 350, SPACE = 100;
    public float W, H;
    public boolean coinOn = true, rockOn = true;
    private Texture rock, coin;
    private Vector2 pos;
    private Rectangle boundsRock, boundsCoin;

    public RockActor(Texture rock, Texture coin, float x, float y) {
        this.rock = rock;
        this.coin = coin;

        pos = new Vector2(x, y);
        W = rock.getWidth();
        H = rock.getHeight();

        setSize(W, H);
        setPosition(x, y);
        boundsRock = new Rectangle(x + 10, y, W - 10, H - 12);
        boundsCoin = new Rectangle(x + 5, y + SPACE, coin.getWidth() - 10, coin.getHeight() - 12);
    }

    public Rectangle getBoundsRock() { return boundsRock; }

    public Rectangle getBoundsCoin() { return boundsCoin; }

    public Texture getRock() { return rock; }

    public Texture getCoin() { return coin; }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(rock, getX(), getY(), W, H);
        if (coinOn) batch.draw(coin, getX(), getY() + SPACE);
    }

    public void rePos(float x){
        coinOn = true;
        rockOn = true;
        pos.set(x, pos.y);
        setPosition(x, pos.y);
        boundsRock.setPosition(pos.x + 5, 55);
        boundsCoin.setPosition(x, pos.y + SPACE);
    }

    public void detach(){
    }

}
