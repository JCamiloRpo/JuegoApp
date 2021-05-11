package com.upb.runrocks.actors;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.upb.runrocks.RunRocks;

import static com.upb.runrocks.RunRocks.WIDTH;

public class ActorManager {

    public static float Y = 55;
    private RunRocks game;

    public ActorManager(RunRocks game) {
        this.game = game;
    }

    public PlayerActor createPlayer(float x){
        return  new PlayerActor(game.assets.get("jabali/jabali.atlas", TextureAtlas.class), x, Y, game);
    }

    public FloorActor createFloor(int i, int type){
        if (type > -1 && type < 3)
            return new FloorActor(game.assets.get("scene/bg_1.png", Texture.class),
                    game.assets.get("scene/floor_"+type+".png", Texture.class),i * WIDTH);
        else
            return new FloorActor(game.assets.get("scene/bg_0.png", Texture.class), null,i * WIDTH);

    }

    public RockActor createRock(int i, int type){
        return new RockActor(game.assets.get("scene/rock_"+type+".png", Texture.class),
                game.assets.get("icons/coin.png", Texture.class), (i+1)*RockActor.GAP, Y);
    }
}
