package com.sbtwork.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.sbtwork.game.scene.Hud;
import com.sbtwork.game.sprite.Tube;
import com.sbtwork.game.sprite.ball;
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
    private Array<Tube> tubes;
   // private ShapeRenderer shapeRenderer;
    public PlayState(GameStateManager gsmFound) {
        super(gsmFound);
       // shapeRenderer = new ShapeRenderer();
        statecam.setToOrtho(false, xball.WIDTH /2 , xball.HEIGHT /2 );
        gameball = new ball(100,0,Color.SALMON);
        Hud.addScore(true, 0);
        ball.isDead =   false;
        balls = new Array<ball>();
        tubes = new Array<Tube>();
        //tubes.add(new Tube(1 * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        for(int i = 1; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
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

            if(statecam.position.y - (statecam.viewportHeight /2) > tube.getPosTopTube().y + tube.getTopTube().getHeight()){
                tube.reposition(tube.getPosTopTube().y  + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if(gameball.getPosition().y  > tube.getPosTopTube().y + tube.getTopTube().getHeight()){
                Hud.addScore(false,1);
            }
            if(tube.collides(gameball.getBounds())){
                ball.isDead = true;
               gsm.set(new GameOverState(gsm));
                dispose();
            }
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
        //sr.circle(20,200,40);
        sr.setColor(gameball.getBallcolor());
        sr.end();
    }
}
