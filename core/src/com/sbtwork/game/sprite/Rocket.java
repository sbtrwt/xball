package com.sbtwork.game.sprite;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by sbtrwt on 12-06-2016.
 */
public class Rocket {

    public World world;
    public Body b2body;

    public  Rocket(World world){
        this.world = world;
        defineRocket();
    }

    public void defineRocket(){
        BodyDef bdf = new BodyDef();
        bdf.position.set(32,32);
        bdf.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdf);

        FixtureDef fdf = new FixtureDef();
        CircleShape crclR = new CircleShape();
        crclR.setRadius(20);
        fdf.shape    = crclR;
        b2body.createFixture(fdf);
    }
}
