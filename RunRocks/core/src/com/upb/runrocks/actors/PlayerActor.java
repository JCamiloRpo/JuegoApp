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
    private RunRocks game;
    // Elementos de Scene2D - Animacion
    // Run
    private Animation<TextureRegion> run;
    private float timeFrame;
    // Jump
    private Animation<TextureRegion> jump;
    // Die
    private Animation<TextureRegion> die;
    private Sound sndJump, sndHit, sndDie, sndCoin;
    // Elementos de Box2D
    private World world;
    private Body body;
    private Fixture fix;
    // Variables del jugador
    private int coins, lifes = 3;
    private boolean alive = true, jumping = false, mustJump = false;

    public PlayerActor(World world, TextureAtlas atlas, float x, float y, RunRocks game){
        this.game = game;
        sndJump = game.assets.get("audio/jump.ogg");
        sndHit = game.assets.get("audio/hitvoice1.ogg");
        sndDie = game.assets.get("audio/gameover.ogg");
        sndCoin = game.assets.get("audio/coin.ogg");

        this.world = world;
        Array<TextureAtlas.AtlasRegion> run = atlas.findRegions("run");
        this.run = new Animation<TextureRegion>(5f, run, Animation.PlayMode.LOOP);
        timeFrame = 0;

        Array<TextureAtlas.AtlasRegion> jump = atlas.findRegions("jump");
        this.jump = new Animation<TextureRegion>(10f, jump, Animation.PlayMode.NORMAL);

        Array<TextureAtlas.AtlasRegion> die = atlas.findRegions("die");
        this.die = new Animation<TextureRegion>(5f, die, Animation.PlayMode.NORMAL);


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

    public int getCoins() { return coins; }

    public void setCoins(int coins) { this.coins = coins; }

    public int getLifes() { return lifes; }

    public void setLifes(int lifes) { this.lifes = lifes; }

    public boolean isAlive() { return alive; }

    public void setAlive(boolean alive) { this.alive = alive; }

    public boolean isJumping() { return jumping; }

    public void setJumping(boolean jumping) { this.jumping = jumping; }

    public boolean isMustJump() { return mustJump; }

    public void setMustJump(boolean mustJump) { this.mustJump = mustJump; }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        timeFrame += parentAlpha;
        setPosition((body.getPosition().x - (W/2)) * PIXEL_METERS,
                (body.getPosition().y - (H/2)) * PIXEL_METERS);
        if (jumping) batch.draw(jump.getKeyFrame(timeFrame), getX(), getY(), getWidth(), getHeight());
        else batch.draw(run.getKeyFrame(timeFrame), getX(), getY(), getWidth(), getHeight());

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
            body.applyForceToCenter(0, -IMPULSE_JUMP * 1.15f, true);
        }

        // Avanzar
        if (alive){
            float velY = body.getLinearVelocity().y;
            body.setLinearVelocity(SPEED, velY);
        }
    }

    public void jump(){
        if(!jumping && alive) {
            if(game.soundOn) sndJump.play(0.5f);
            jumping = true;
            Vector2 pos = body.getPosition();
            body.applyLinearImpulse(0, IMPULSE_JUMP, pos.x, pos.y, true);
        }
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
