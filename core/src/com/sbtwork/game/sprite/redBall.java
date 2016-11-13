package com.sbtwork.game.sprite;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by sbtrwt on 11-06-2016.
 */
public class redBall extends enemy {
    public static final int MAX_RADIUS = 10;
    private static final int FLUCTUATION = 230;
    private static final int OFFSET = 100;
    public static final int MOVEMENT = 10;

    private Random rand;

    public redBall(Vector2 pos){
        super(pos);
        rand = new Random();
        velocity = new Vector2(0,0);
        System.out.println(pos.x + " "+  pos.y );
        position.set(pos.x + rand.nextInt(FLUCTUATION), pos.y + OFFSET);
        bound = new Rectangle(position.x,position.y,MAX_RADIUS*2,MAX_RADIUS*2);

    }
    @Override
    public void dispose() {

    }

    @Override
    public void update(float dt) {
        velocity.scl(dt);
        position.add(velocity.x + MOVEMENT * dt , velocity.y);

        bound.setPosition(position.x - MAX_RADIUS, position.y - MAX_RADIUS);
        bound.setWidth(2 * MAX_RADIUS);
        bound.setHeight(2 * MAX_RADIUS);
        velocity.scl(1/dt);
    }

    @Override
    public boolean collides(Rectangle player) {
           return player.overlaps(bound);
    }

    @Override
    public void relocation(Vector2 pos) {
        position.set(-rand.nextInt(FLUCTUATION),pos.y + OFFSET);
        bound.setPosition(position.x, position.y);
     }
}
