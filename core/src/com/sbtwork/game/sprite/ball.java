package com.sbtwork.game.sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sbtwork.game.xball;

import com.badlogic.gdx.math.Circle;

/**
 * Created by sbtrwt on 03-06-2016.
 */
public class ball {

        public static final int GRAVITY = -10;
        public static final int MOVEMENT = 100;
        public static final int MAX_R = 100;
         public static final int MIN_R = 20;
    public static boolean isDead = false;
        private Vector3 position;
        private Vector3 velocity;
        private float radius = MIN_R;
        private Rectangle bounds;
        private Circle gameball;
        private int sign = -1,direction = 1;



    Color ballcolor;
        //private Texture bird;
        //private Animation birdAnimation;
        float nCheckH;
        private Music music;
        public ball (float x , float y,Color clrBall)
        {
            position = new Vector3(x, y , 0);
            velocity = new Vector3(0,0,0);
           // bird = new Texture("bird.png");
            gameball = new Circle();
            bounds = new Rectangle(x , y , MIN_R , MIN_R);
            ballcolor = new Color(clrBall);

            //nCheckH = (xball.HEIGHT / 2) - MAX_R;
            music = Gdx.audio.newMusic(Gdx.files.internal("sfx_wing.ogg"));

        }

        public void update (float dt){
            //radiusIncr();
            //nCheckH = (xball.HEIGHT / 2) - radius;
            /*if(position.y > 0 ){
                velocity.add(0,getDirection() * GRAVITY,0);
            }*/
            velocity.scl(dt);
            position.add(velocity.x + direction, MOVEMENT * dt,  0);
           /* if(position.y < radius)
                position.y = radius;
            if( position.y > (nCheckH)){
                position.y = nCheckH;
            }*/
            bounds.setPosition(position.x - radius, position.y - radius);
            bounds.setWidth(2*radius);
            bounds.setHeight(2*radius);
           velocity.scl(1/dt);
        }

        public void radiusIncr(){
            if(radius >= 20  && radius< 100)
                radius += 0.1;
            else
                radius = MAX_R;
        }
        public void jump()
        {
            velocity.x = getDirection() * 250;
            music.play();
        }
        public void moveX(){
            direction *= sign;
            music.play();
        }
        private int getDirection(){

            return (position.y >= 0 && position.y < nCheckH/2)? 1 : -1;
        }
        public void dispose()
        {
            //bird.dispose();
            music.dispose();
        }
        public Vector3 getPosition() {
            return position;
        }
        public float getRadius(){
                return radius;
        }
        //public Texture getBird() {
            //return bird;
        //}
        public Circle getBall(){
            return gameball;
        }
        public Rectangle getBounds(){return bounds;}

    public boolean isBallTouched(float X, float Y)
    {
       //System.out.println( X + " " + Y + " " +bounds.getX() + " " +bounds.getY()); //&& (position.y < Y  && position.y + radius > Y)
        return bounds.contains(X,Y) ;
    }

        public boolean collides(Rectangle objChk)
        {
            return bounds.overlaps(objChk) ;
        }
    public Color getBallcolor() {
        return ballcolor;
    }

    public void setBallcolor(Color ballcolor) {
        this.ballcolor = ballcolor;
    }
}
