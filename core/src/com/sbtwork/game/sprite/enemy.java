package com.sbtwork.game.sprite;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by sbtrwt on 11-06-2016.
 */
public abstract class enemy  {
    protected Vector2 position;
    protected Rectangle bound;
    protected Float width,height;
    protected Vector2 velocity;


    public  enemy(Vector2 pos){
     this.position =  pos;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Rectangle getBound() {
        return bound;
    }

    public void setBound(Rectangle bound) {
        this.bound = bound;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public abstract  void dispose ();
    public abstract void update(float dt);
    public abstract boolean collides(Rectangle player);
    public abstract void relocation(Vector2 pos);

}
