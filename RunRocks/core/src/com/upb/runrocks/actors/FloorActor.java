package com.upb.runrocks.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.upb.runrocks.RunRocks.HEIGHT;
import static com.upb.runrocks.RunRocks.WIDTH;

public class FloorActor extends Actor {

    public static String TAG = "FLOOR";
    public static float W = WIDTH+1, H = 75;
    private Texture bg, floor;
    private Vector2 pos;

    /**
     * @param bg Textura de fondo
     * @param floor Textura del piso
     * @param x Ubicacion horizontal
     */
    public FloorActor(Texture bg, Texture floor, float x) {
        this.bg = bg;
        if (floor != null) this.floor = floor;

        pos = new Vector2(x, 0);     // Posicion
        setSize(WIDTH, HEIGHT);         // Tamaño del actor
        setPosition(x, 0);          // Posicion del actor
    }
    // Getter del fondo
    public Texture getBg() { return bg; }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(bg, getX(), getY(), W, HEIGHT);                  // Dibujar el fondo
        if (floor != null) batch.draw(floor, getX(), getY(), W, H); // Dibujar el piso
    }

    /**
     * Reposicionar el fondo
     * @param x Nueva posicion horizontal
     */
    public void rePos(float x){
        pos.set(x, 0);
        setPosition(x, 0);
    }

    /**
     * Eliminar elementos de la memoria
     */
    public void detach(){
        bg.dispose();
        if (floor != null) floor.dispose();
    }
}
