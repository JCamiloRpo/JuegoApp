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

    /**
     * @param rock Textura de la roca
     * @param coin Textura de la moneda
     * @param x Posicion horizontal de la roca
     * @param y Posicion vertical de la roca
     */
    public RockActor(Texture rock, Texture coin, float x, float y) {
        this.rock = rock;
        this.coin = coin;

        pos = new Vector2(x, y);    // Guardar posicion
        W = rock.getWidth();        // Ancho de la roca
        H = rock.getHeight();       // Alto de la roca

        setSize(W, H);              // Tamaño del actor
        setPosition(x, y);          // Posicion del actor
        boundsRock = new Rectangle(x + 15, y,               // Posicion
                W - 15, H - 15);                 // Tamaño de los limites de la roca
        boundsCoin = new Rectangle(x + 5, y + SPACE,    // Posicion
                coin.getWidth() - 10, coin.getHeight() - 10);   // Limites de la moneda
    }
    // Getters and Setters necesarios
    public Rectangle getBoundsRock() { return boundsRock; }

    public Rectangle getBoundsCoin() { return boundsCoin; }

    public Texture getRock() { return rock; }

    public Texture getCoin() { return coin; }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(rock, getX(), getY(), W, H);                     // Dibujar la roca
        if (coinOn) batch.draw(coin, getX(), getY() + SPACE);   // Dibujar la moneda si esta activa
    }

    /**
     * Reposicionar el actor: la roca y moneda
     * @param x posicion nueva horizontal
     */
    public void rePos(float x){
        coinOn = true;
        rockOn = true;
        pos.set(x, pos.y);
        setPosition(x, pos.y);
        boundsRock.setPosition(pos.x + 15, pos.y);
        boundsCoin.setPosition(x + 5, pos.y + SPACE);
    }

    /**
     * Eliminar elementos de la memoria
     */
    public void detach(){
        rock.dispose();
        coin.dispose();
    }

}
