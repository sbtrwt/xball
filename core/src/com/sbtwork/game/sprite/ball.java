package com.sbtwork.game.sprite;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
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
        public static final int UP = 0x01;
        public static final int DOWN = 0x02;
        public static final int LEFT = 0x04;
        public static final int RIGHT = 0x08;
        public static final int GRAVITY = -10;
        public static final int MOVEMENT = 100;
        public static final int MAX_R = 100;
        public static final int MIN_R = 20;
        public static boolean isDead = false;
        private Vector3 position;
        private Vector3 velocity;
        private float radius = MIN_R;
        private Rectangle bounds, ballArea;
        private Circle gameball;

        private int sign = -1,directionX = 0,directionY = 0;
        public boolean isKeyPrs = false;
        public int keyPressed = 0x00;

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
            ballArea = new Rectangle(0,0, Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
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
            position.add(velocity.x + ((isKeyPrs && ((LEFT | RIGHT) & keyPressed) > 0)? directionX: 0),velocity.y + ((isKeyPrs && ((UP | DOWN) & keyPressed) > 0)? directionY: 0),  0);
           /* if(position.y < radius)
                position.y = radius;
            if( position.y > (nCheckH)){
                position.y = nCheckH;
            }*/
            //ballArea.setPosition(0, position.y );
            bounds.setPosition(position.x - radius, position.y - radius);
            bounds.setWidth(2*radius);
            bounds.setHeight(2 *radius);
           velocity.scl(1/dt);
        }
        public void moveball(){
            position.add(velocity.x + directionX, MOVEMENT, 0);
           /* if(position.y < radius)
                position.y = radius;
            if( position.y > (nCheckH)){
                position.y = nCheckH;
            }*/
            //ballArea.setPosition(0, position.y );
            bounds.setPosition(position.x - radius, position.y - radius);
            bounds.setWidth(2 * radius);
            bounds.setHeight(2 * radius);
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
            directionX *= -sign;
            // music.play();
            //moveball();
        }
        public void moveRight(){
            directionX = -sign;
           // music.play();
            //moveball();
        }
        public void moveLeft(){
            directionX = sign;
           // music.play();
           // moveball();
        }
        public void moveUp() {
            directionY = -sign ;
        }

        public void moveDown() {
            directionY = sign ;
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
            System.out.println("In collide");
            Boolean bChk = outOfArea(objChk);
            return (bounds.overlaps(objChk) | bChk   ) ;
        }
    public boolean outOfArea(Rectangle objChk){
       /* System.out.println("\n Area x = " + ballArea.x +" y = "  + ballArea.x+" width = "+ballArea.width+" height = "+ballArea.height );
        System.out.println("\n Area x = " + objChk.x +" y = "  + objChk.x+" width = "+objChk.width+" height = "+objChk.height );
        boolean bChk = false;
        objChk.setPosition(position.x - 2*radius, position.y - 2*radius);
        objChk.setWidth(2*radius);
        objChk.setHeight(2 *radius);
        System.out.println("\n Area x = " + objChk.x +" y = "  + objChk.x+" width = "+objChk.width+" height = "+objChk.height );
        bChk = ballArea.overlaps(objChk);
        objChk.setPosition(position.x + 2*radius, position.y + 2*radius);
        objChk.setWidth(2*radius);
        objChk.setHeight(2 *radius);
        System.out.println("\n Area x = " + objChk.x +" y = "  + objChk.x+" width = "+objChk.width+" height = "+objChk.height+ " " +bChk + " ");
        bChk |= ballArea.overlaps(objChk);
        System.out.println(bChk);*/
        //System.out.println( (objChk.x + 2*radius) + " > "+ ballArea.width);
        return (objChk.x < 0 || (objChk.x + 2*radius) > ballArea.width);
    }
    public Color getBallcolor() {
        return ballcolor;
    }

    public void setBallcolor(Color ballcolor) {
        this.ballcolor = ballcolor;
    }


}
