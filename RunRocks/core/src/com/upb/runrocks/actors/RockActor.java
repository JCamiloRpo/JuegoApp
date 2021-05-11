package com.upb.runrocks.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.upb.runrocks.RunRocks.HEIGHT;
import static com.upb.runrocks.RunRocks.WIDTH;

public class RockActor extends Actor {

    public static String TAG = "ROCK";
    public static float GAP = 300, SPACE = 80;
    public float W, H;
    private Texture rock, coin;
    private Vector2 pos;
    private Rectangle bounds;

    public RockActor(Texture rock, Texture coin, float x, float y) {
        this.rock = rock;
        this.coin = coin;

        pos = new Vector2(x, y);
        W = rock.getWidth();
        H = rock.getHeight();

        setSize(W, H);
        setPosition(x, y);
        bounds = new Rectangle(x, y, W, H);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(rock, getX(), getY(), W, H);
        batch.draw(coin, getX(), getY() + SPACE);
    }

    public void rePos(float x){
        pos.set(x, 55);
        setPosition(x, 55);
    }

    public void detach(){
        rock.dispose();
        coin.dispose();
    }

}
