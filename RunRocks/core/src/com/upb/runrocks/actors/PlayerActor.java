package com.upb.runrocks.actors;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.upb.runrocks.RunRocks;

import static com.upb.runrocks.RunRocks.VOLS;

public class PlayerActor extends Actor {

    public static String TAG = "PLAYER";
    public static float JUMP = 550, SPEED = 250, GRAVITY = -15;
    public static float W = 108, H = 90, Y;
    private RunRocks game;
    // Animacion: Run - Jump - Die
    private Animation<TextureRegion> run, jump, die;
    private float timeFrame;
    // Scene2D
    private Vector2 pos, speed;
    private Rectangle bounds;
    private Sound sndJump, sndHit, sndDie, sndCoin;
    // Variables del jugador
    private int nroCoins = 0, nroLifes = 3;
    private boolean alive = true, jumping = false, mustJump = false;

    /**
     * @param atlas con todas las animaciones
     * @param x posicion horizontal
     * @param y posicion vertical
     * @param game para acceder al AssetManager
     */
    public PlayerActor(TextureAtlas atlas, float x, float y, RunRocks game){
        this.game = game;
        sndJump = game.assets.get("audio/jump.mp3");    // Sonido cuando salta
        sndHit = game.assets.get("audio/hitvoice.ogg"); // Sonido cuando se golpea
        sndDie = game.assets.get("audio/end.mp3");      // Sonido cuando pierde
        sndCoin = game.assets.get("audio/coin.ogg");    // Sonido de la moneda

        Array<TextureAtlas.AtlasRegion> run = atlas.findRegions("run");     // Imgs de correr
        this.run = new Animation<TextureRegion>(5f, run, Animation.PlayMode.LOOP);
        timeFrame = 0;

        Array<TextureAtlas.AtlasRegion> jump = atlas.findRegions("jump");   // Imgs de saltar
        this.jump = new Animation<TextureRegion>(10f, jump, Animation.PlayMode.NORMAL);

        Array<TextureAtlas.AtlasRegion> die = atlas.findRegions("die");     // Imgs de morir
        this.die = new Animation<TextureRegion>(20f, die, Animation.PlayMode.NORMAL);

        pos = new Vector2(x, y);            // Posicion
        speed = new Vector2(0, 0);    // Velocidad
        Y = y;                              // Guardar la posicion inicial en y
        setPosition(x, y);                  // Poner la posicion del actor
        setSize(W, H);                      // El tamaño del actor
        // Rectangulo para determinar colisiones
        bounds = new Rectangle(x + 5 , y, getWidth() - 5, getHeight() - 12);
    }
    //Getters and Setters necesarios
    public int getNroCoins() { return nroCoins; }

    public int getNroLifes() { return nroLifes; }

    public boolean isAlive() { return alive; }

    public boolean isJumping() { return jumping; }

    public void setMustJump(boolean mustJump) { this.mustJump = mustJump; }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        timeFrame += parentAlpha;   // Actualizar el tiempo de la animación
        // Dibujar la animación del jabali correspondiente
        if (jumping) batch.draw(jump.getKeyFrame(timeFrame), getX(), getY(), getWidth(), getHeight());
        else if(alive) batch.draw(run.getKeyFrame(timeFrame), getX(), getY(), getWidth(), getHeight());
        else batch.draw(die.getKeyFrame(timeFrame), getX(), getY(), getWidth(), getHeight());

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        // Saltar
        if (mustJump){
            mustJump = false;
            jump();
        }
        // Aplicar gravedad si se esta saltando
        if (jumping)
            speed.add(0, GRAVITY);

        // Velocidad para avanzar si esta vivo
        if (alive){
            speed.scl(delta);
            pos.add(SPEED*delta, speed.y);
        }
        // Cuando esta cayendo no dejar que baje más de la posicion inicial
        if (pos.y < Y){
            pos.y = Y;
            jumping = false;
        }
        speed.scl(1/delta);
        setPosition(pos.x, pos.y);          // Actualizar posicion del actor
        bounds.setPosition(pos.x, pos.y);   // Actualizar posicion
    }

    public void jump(){
        // Saltar solo una vez
        if(!jumping && alive) {
            if(game.soundOn) sndJump.play(VOLS);
            jumping = true;
            speed.y = JUMP;
        }
    }

    /**
     * Metodo para determinar si se cogió una moneda
     * @param coin los limites de la moneda
     * @return boolean si se cogió la moneda o no
     */
    public boolean coin(Rectangle coin){
        if (coin.overlaps(bounds)){
            if(game.soundOn) sndCoin.play(VOLS);
            nroCoins ++;
            return true;
        }
        return false;
    }

    /**
     * Determinar si hubo una colision con una roca
     * @param rock los limites de la roca
     * @return true o false
     */
    public boolean collision(Rectangle rock){
        if(rock.overlaps(bounds)){
            if(game.soundOn) sndHit.play(VOLS);
            nroLifes--;
            if(nroLifes < 0){
                if(game.soundOn) sndDie.play(VOLS);
                alive = false;
                jumping = false;
            }
            return true;
        }
        return false;
    }

    /**
     * Eliminar los elementos de la memoria
     */
    public void detach(){
        sndDie.dispose();
        sndHit.dispose();
        sndCoin.dispose();
        sndJump.dispose();
    }
}
