package com.ninepawns.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ninepawns.game.ninePawns;

/**
 * Created by renzkyril on 02/07/2017.
 */

public class MenuState extends State{

    private int homeScore , visitorScore;
    private Texture bg;
    private BitmapFont scores;
    private String textScore;
    private PlayState playstate;

    public MenuState(GameStateManager gsm,int homeScore , int visitorScore) {
        super(gsm);
        this.homeScore = homeScore;
        this.visitorScore = visitorScore;
        this.bg = new Texture("background.jpg");
        this.scores = new BitmapFont();
        this.textScore = "home "+homeScore+"   "+visitorScore+" visitor";

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            this.playstate = new PlayState(gsm);
            gsm.push(this.playstate);
            if (this.playstate.whiteWins()){
                System.out.println("white won");
                homeScore++;
            }
            if(this.playstate.blackWins()){
                System.out.println("black won");
                visitorScore++;
            }
            this.textScore = "home "+homeScore+"   "+visitorScore+" visitor";
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg,0,0,ninePawns.WIDTH,ninePawns.HEIGHT);
        scores.setColor(Color.BLACK);
        scores.getData().setScale(3,3);
        scores.draw(sb,textScore,ninePawns.WIDTH/2-scores.getRegion().getTexture().getWidth()/2,ninePawns.HEIGHT/2-scores.getRegion().getTexture().getHeight()/2);
        sb.end();
    }

    @Override
    protected void dispose() {

    }
}
