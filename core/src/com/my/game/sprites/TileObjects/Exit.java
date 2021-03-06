package com.my.game.sprites.TileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.my.game.BitHeroes;
import com.my.game.screens.PlayScreens.FirstLevel;
import com.my.game.screens.PlayScreens.SecondLevel;
import com.my.game.screens.PlayScreens.ThirdLevel;
import com.my.game.tools.Entity;
import com.my.game.tools.TileObject;

/**
 * Create a solid Exit TileObject to jump to the next level
 */

public class Exit extends TileObject {
    /**
     * Create a Exit TileObject
     * @param world
     * @param rect
     * @param game
     */
    public Exit(World world, Rectangle rect, BitHeroes game) {
        super(world, rect,game);
        setCategoryBits(BitHeroes.EXIT_BIT);
    }

    @Override
    public void update(float delta) {}

    /**
     * When the player touches the exit jumps to the next level
     * @param entity Enemy or Player
     */
    @Override
    public void onHit(Entity entity) {
        try{
            music = game.getManager().get("sounds/passLivello.wav",Music.class);
            music.setLooping(false);
            music.setVolume(1);
            music.play();
        }catch (Exception e){
            Gdx.app.log("Error","audio file not found");
        }
        if(game.getCurrentPlayScreen() instanceof FirstLevel){
            game.changeLevel(new SecondLevel(game,game.getCurrentPlayer(),game.getScore()));
        }
        else if(game.getCurrentPlayScreen() instanceof SecondLevel){
            game.changeLevel(new ThirdLevel(game,game.getCurrentPlayer(),game.getScore()));
        }
    }
}
