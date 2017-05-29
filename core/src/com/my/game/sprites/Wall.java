package com.my.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.my.game.MyGame;
import com.my.game.tools.TileObject;

/**
 * Created by lorib on 24/05/2017.
 */

public class Wall extends TileObject {
    public Wall(World world, TiledMap map, Rectangle rect) {
        super(world, map, rect);
        setCategoryBits(MyGame.WALL_BIT);

    }

    @Override
    public void onHit() {
        Gdx.app.log("Hit","Wall");
    }
}