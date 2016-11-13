package com.sbtwork.game.state;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.sbtwork.game.scene.Hud;
import com.sbtwork.game.sprite.Tube;
import com.sbtwork.game.sprite.ball;
import com.sbtwork.game.sprite.redBall;
import com.sbtwork.game.xball;

import java.util.ArrayList;

/**
 * Created by sbtrwt on 03-06-2016.
 */
public class PlayState extends State {
    private Label score ;
    private ball gameball;
    private Array<ball> balls;
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int BALL_SPEED = 4;
    private Array<Tube> tubes;
    private  Array<redBall> redballs;
    float accelx = 0, accely = 0,accelxp = 0, accelyp = 0;
   // private ShapeRenderer shapeRenderer;
    public PlayState(GameStateManager gsmFound) {
        super(gsmFound);
       // shapeRenderer = new ShapeRenderer();
        statecam.setToOrtho(false, xball.WIDTH /2 , xball.HEIGHT /2 );
        gameball = new ball(xball.WIDTH/4 , 100,Color.SALMON);
        Hud.addScore(true, 0);
        ball.isDead =   false;
        balls = new Array<ball>();
        tubes = new Array<Tube>();
        redballs  = new Array<redBall>();
        //tubes.add(new Tube(1 * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        for(int i = 1; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
            redballs.add(new redBall(new Vector2(tubes.get(i-1).getPosTopTube().x,tubes.get(i-1).getPosTopTube().y)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            /*Vector3 touchpoint = new Vector3();
            statecam.unproject(touchpoint.set(Gdx.input.getX(),Gdx.input.getY(),0));
            if(gameball.isBallTouched(touchpoint.x, touchpoint.y)) {
                gameball.jump();
                balls.add(new ball(gameball.getPosition().x + 300, gameball.getPosition().y ));
            }*/
            gameball.moveX();
        }
        gameball.isKeyPrs = false;
        gameball.keyPressed = 0x00;
        Application.ApplicationType appType = Gdx.app.getType();

        // should work also with Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer)
        if (appType == Application.ApplicationType.Android || appType == Application.ApplicationType.iOS) {
           accelx=  Gdx.input.getAccelerometerX();
            accely = Gdx.input.getAccelerometerY();
            //System.out.print("\naccelartion x= "+ accelx);
            //System.out.print("\naccelartion y= "+ accely);
        } else {

          /*  if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT))  update(accelx -= BALL_SPEED);
            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT))  update(accelx += BALL_SPEED);
            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP))  update(accely -= BALL_SPEED);
            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN))  update(accely += BALL_SPEED);*/

            }
        if(Gdx.input.isKeyPressed(Input.Keys.UP) || accely<0){
            //gameball.keyPressed = 0x00;
            gameball.isKeyPrs = true;
            gameball.keyPressed |= gameball.UP;
            //accelyp = accely;
            //update( BALL_SPEED);
            gameball.moveUp();
        }
         if(Gdx.input.isKeyPressed(Input.Keys.DOWN) ||  accely > 0){
            //gameball.keyPressed = 0x00;
            gameball.isKeyPrs = true;
            gameball.keyPressed |= gameball.DOWN;
           // accelyp = accely;
            //gameball.moveDown();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || accelx > 0){
            //gameball.keyPressed = 0x00;
            gameball.isKeyPrs = true;
            gameball.keyPressed |= gameball.LEFT;
           // accelxp = accelx;
            gameball.moveLeft();
        }
       if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || accelx < 0){
           //gameball.keyPressed = 0x00;
            gameball.isKeyPrs = true;
            gameball.keyPressed |= gameball.RIGHT;
           // accelxp = accelx;
            gameball.moveRight();
        }


           /*  for(ball ballfound :  balls)
        {
            if(gameball.collides(ballfound.getBounds())){
                gameball.dispose();
                gsm.set(new MenuState(gsm));
            }
        }*/
    }

    @Override
    public void update(float dt) {
        handleInput();
        gameball.update(dt);
        //statecam.position.x = gameball.getPosition().x ;
        statecam.position.y = gameball.getPosition().y + 50;
        for(int i = 0; i < tubes.size; i++){
            Tube tube = tubes.get(i);
            redBall redbl = redballs.get(i);
            if(statecam.position.y - (statecam.viewportHeight /2) > tube.getPosTopTube().y + tube.getTopTube().getHeight()){
                tube.reposition(tube.getPosTopTube().y  + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
                redbl.relocation(new Vector2(tube.getPosTopTube().x, tube.getPosTopTube().y));
            }
            if(gameball.getPosition().y  > tube.getPosTopTube().y + tube.getTopTube().getHeight()){
                Hud.addScore(false,1);
            }
            if(tube.collides(gameball.getBounds()) || gameball.outOfArea(gameball.getBounds())){
                System.out.println("In collide");
                ball.isDead = true;
               gsm.set(new GameOverState(gsm));
                dispose();
            }
            redbl.update(dt);
        }
        statecam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(statecam.combined);
        sb.begin();

        for(Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }


        sb.end();
    }

    @Override
    public void dispose() {

        balls.clear();
        gameball.dispose();
        tubes.clear();
    }

    @Override
    public void render(ShapeRenderer sr) {
        //statecam.update();
        sr.setProjectionMatrix(statecam.combined);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        //Matrix4 mat = statecam.combined.cpy();
        //mat.setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //sr.setProjectionMatrix(mat);
        sr.circle(gameball.getPosition().x, gameball.getPosition().y, gameball.getRadius());
        for( ball ballfound :  balls ) {
            sr.circle(ballfound.getPosition().x, ballfound.getPosition().y, ballfound.getRadius());
        }
        for( redBall ballfound :  redballs ) {
            sr.circle(ballfound.getPosition().x, ballfound.getPosition().y, ballfound.MAX_RADIUS);
        }
        //sr.circle(20,200,40);
        sr.setColor(gameball.getBallcolor());
        sr.end();
    }
}
