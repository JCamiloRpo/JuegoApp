package com.upb.runrocks.actors;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.upb.runrocks.RunRocks;

import static com.upb.runrocks.RunRocks.IMPULSE_JUMP;
import static com.upb.runrocks.RunRocks.PIXEL_METERS;
import static com.upb.runrocks.RunRocks.SPEED;

public class PlayerActor extends Actor {

    public static String TAG = "PLAYER";
    public static float W = 108 / PIXEL_METERS, H = 90 / PIXEL_METERS;
    // Elementos de Scene2D - Animacion
    // Run
    private Animation<TextureRegion> run;
    private float timeRun;
    // Jump
    private Animation<TextureRegion> jump;
    private float timeJump;
    // Die
    private Animation<TextureRegion> die;
    private float timeDie;
    private Sound sndJump, sndHit, sndDie, sndCoin;
    // Elementos de Box2D
    private World world;
    private Body body;
    private Fixture fix;
    // Variables del jugador
    private int coins, lifes = 3;
    private boolean alive = true, jumping = false, mustJump = false;

    public PlayerActor(World world, TextureAtlas atlas, float x, float y, RunRocks game){
        sndJump = game.assets.get("audio/jump.ogg");
        sndHit = game.assets.get("audio/hitvoice1.ogg");
        sndDie = game.assets.get("audio/gameover.ogg");
        sndCoin = game.assets.get("audio/coin.ogg");

        this.world = world;
        Array<TextureAtlas.AtlasRegion> run = atlas.findRegions("run");
        this.run = new Animation<TextureRegion>(5f, run, Animation.PlayMode.LOOP);
        timeRun = 0;

        Array<TextureAtlas.AtlasRegion> jump = atlas.findRegions("jump");
        this.jump = new Animation<TextureRegion>(5f, jump, Animation.PlayMode.LOOP);
        timeJump = 0;

        Array<TextureAtlas.AtlasRegion> die = atlas.findRegions("die");
        this.die = new Animation<TextureRegion>(5f, die, Animation.PlayMode.LOOP);
        timeDie = 0;


        // Create the player body.
        BodyDef def = new BodyDef();
        def.position.set(x + (W/2),y + (H/2));
        def.fixedRotation = true;
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);

        // Give it some shape.
        PolygonShape box = new PolygonShape();
        box.setAsBox(W/2 - 0.1f, H/2 - 0.1f);
        fix = body.createFixture(box, 1);
        fix.setUserData(TAG);

        box.dispose();

        setSize(W * PIXEL_METERS, H * PIXEL_METERS);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        timeRun += parentAlpha;
        setPosition((body.getPosition().x - (W/2)) * PIXEL_METERS,
                (body.getPosition().y - (H/2)) * PIXEL_METERS);
        batch.draw(run.getKeyFrame(timeRun), getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
    }

    public void detach(){
        body.destroyFixture(fix);
        world.destroyBody(body);
        sndJump.dispose();
        sndHit.dispose();
        sndDie.dispose();
        sndCoin.dispose();
    }
}
