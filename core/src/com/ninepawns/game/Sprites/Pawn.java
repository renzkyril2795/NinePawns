package com.ninepawns.game.Sprites;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by renzkyril on 02/07/2017.
 */

public class Pawn {

    private static final int ADITIONAL = 10;

    private Texture pawn;
    private boolean isWhite;
    private int x,y;

    public Pawn(int x,int y,boolean isWhite){
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
        if(this.isWhite){
            this.pawn = new Texture("white.png");
        }else{
            this.pawn = new Texture("black.png");
        }
    }

    public void setPosition(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getScreenX(){
        return this.x * Board.SIDE;
    }

    public int getScreenY(){
        return this.y * Board.SIDE + ADITIONAL;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public boolean isWhite(){
        return this.isWhite;
    }

    public Texture getPawn() {
        return pawn;
    }
}
