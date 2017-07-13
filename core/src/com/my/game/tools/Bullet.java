package com.my.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BooleanArray;
import com.my.game.MyGame;

/**
 * Created by lorib on 02/06/2017.
 */

public abstract class Bullet extends Sprite {
    protected Body body;
    protected World world;
    protected float forceAttack;
    protected boolean oppositeDirection;
    protected Animation flyBullet;
    protected TextureAtlas atl;
    protected float stateTimer;
    protected float minSpeed=0.1f;
    protected boolean isPlayer;
    public int damage=0;
    /// [0-1): fall; 1: still; 1+: ryse
    protected float forceDrag;
    protected Fixture fixture;

    public Bullet(Vector2 position,World world,boolean oppositeDirection,boolean isPlayer){
        super();
        this.isPlayer=isPlayer;
        this.oppositeDirection=oppositeDirection;
        this.world=world;
        defineBullet(position);
        forceAttack=1.5f;
        forceDrag=0.98f;
        if(oppositeDirection) forceAttack=-forceAttack;
        stateTimer = 0;
    }

    /**
     * Set filter bits to trigger collisions in the current Fixture.
            * @param filterBits
     */
    public void setCategoryBits(short filterBits){
        Filter filter = new Filter();
        filter.categoryBits = filterBits;
        filter.groupIndex = MyGame.GROUP_SCENERY;
        fixture.setFilterData(filter);
    }

    /**
     * Set and return filter used in collisions.
     * @return
     */
    public Filter getFilter() {
        Filter f = new Filter();
        if (isPlayer) {
            f.categoryBits = MyGame.PLAYER_BULLET_BIT;
            f.maskBits = MyGame.ENEMY_BIT | MyGame.WALL_BIT | MyGame.BRICK_BIT | MyGame.DEFAULT_BIT;
        } else {
            f.categoryBits = MyGame.ENEMY_BULLET_BIT;
            f.maskBits = MyGame.PLAYER_BIT | MyGame.WALL_BIT | MyGame.BRICK_BIT | MyGame.DEFAULT_BIT;
        }
        return f;
    }

    /**
     * Create bulelt body and shape in the given position in the current world.
     * @param position
     */
    public void defineBullet(Vector2 position){
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();

        bdef.position.set(position.x+(1/MyGame.PPM) ,position.y+(1/MyGame.PPM));
        bdef.type = BodyDef.BodyType.DynamicBody;

        body=world.createBody(bdef);

        fdef.filter.groupIndex = getFilter().groupIndex;
        fdef.filter.maskBits = getFilter().maskBits;
        fdef.filter.categoryBits = getFilter().categoryBits;

        shape.setRadius(3/MyGame.PPM);
        fdef.shape=shape;
        fixture=body.createFixture(fdef);
        fixture.setUserData(this);
    }

    /**
     * Update position of the bullet.
     * @param delta
     */
    public abstract void update(float delta);

    /**
     * Remove the bullet from the simulation.
     */
    public void dispose(){
        PlayScreen.current.objectsToRemove.add(this);
        body.setUserData(true);
    }
}
