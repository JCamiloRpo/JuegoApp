package com.upb.runrocks.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.upb.runrocks.RunRocks.HEIGHT;
import static com.upb.runrocks.RunRocks.PIXEL_METERS;
import static com.upb.runrocks.RunRocks.WIDTH;

public class FloorActor extends Actor {

    public static float W=812, H=100;
    public static String TAG = "FLOOR";

    private Texture bg, floor;

    private World world;
    private Body body;
    private Fixture fix;

    /**
     *
     * @param world
     * @param floor
     * @param x dónde esta el borde izquierdo del suelo (Metros)
     * @param y dónde esta el borde superior del suelo (Metros)
     */
    public FloorActor(World world, Texture bg, Texture floor, float x, float y) {
        this.world = world;
        this.bg = bg;
        this.floor = floor;
        float w = W / PIXEL_METERS, h = H / PIXEL_METERS;

        // Colocar suelo
        BodyDef def = new BodyDef();
        def.position.set(x + w / 2, y - h / 2f);
        body = world.createBody(def);

        // Dar forma
        PolygonShape box = new PolygonShape();
        box.setAsBox(w / 2, h / 2f);
        fix = body.createFixture(box, 1);
        fix.setUserData(TAG);
        box.dispose();

        setSize(w * PIXEL_METERS, h * PIXEL_METERS);
        setPosition(x * PIXEL_METERS, (y - h + 0.3f) * PIXEL_METERS);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(bg, getX(), getY(), getWidth() + 10, HEIGHT + 20);
        batch.draw(floor, getX(), getY(), getWidth(), getHeight());
    }

    public void detach(){
        body.destroyFixture(fix);
        world.destroyBody(body);
        bg.dispose();
        floor.dispose();
    }
}
