package com.my.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.my.game.MyGame;
import com.my.game.tools.TileObject;

/**
 * Created by lorib on 13/05/2017.
 */

public class Coin extends TileObject {
    public Coin(World world, TiledMap map, Rectangle rect) {
        super(world, map, rect);
        setCategoryBits(MyGame.COIN_BIT);

    }

    @Override
    public void onHit() {
        Gdx.app.log("Head","Coin");

    }

}
