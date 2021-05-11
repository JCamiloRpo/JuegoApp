package com.upb.runrocks.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.upb.runrocks.RunRocks.HEIGHT;
import static com.upb.runrocks.RunRocks.WIDTH;

public class FloorActor extends Actor {

    public static String TAG = "FLOOR";

    private Texture bg, floor;
    private Vector2 pos;

    public FloorActor(Texture bg, Texture floor, float x) {
        this.bg = bg;
        this.floor = floor;

        pos = new Vector2(x, 0);

        setSize(WIDTH, HEIGHT);
        setPosition(x, 0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(bg, getX(), getY(), WIDTH + 1, HEIGHT);
        batch.draw(floor, getX(), getY(), WIDTH + 1, floor.getHeight());
    }

    public void rePos(float x){
        pos.set(x, 0);
        setPosition(x, 0);
    }

    public void detach(){
        bg.dispose();
        floor.dispose();
    }
}
