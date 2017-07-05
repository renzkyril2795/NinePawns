package com.ninepawns.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.ninepawns.game.ninePawns;

/**
 * Created by renzkyril on 02/07/2017.
 */

public class Board {

    public static final int  NUMSIDES = 7;
    public static final int SIDE = ninePawns.WIDTH/NUMSIDES;
    public Pawn[] whitePawns = new Pawn[9];
    public Pawn[] blackPawns = new Pawn[9];


    private Texture board;

    public Board(){
        this.board = new Texture("chess_board7.png");
        int x = 0;
        int y = 0;
        for (int i = 0 ; i <whitePawns.length ; i++){
            whitePawns[i] = new Pawn(x,y,true);
            x++;
            if (x>2){
                y++;
                x=0;
            }
        }
        x = NUMSIDES-3;
        y = NUMSIDES-3;
        for (int i = 0 ; i <blackPawns.length ; i++){
            blackPawns[i] = new Pawn(x,y,false);
            x++;
            if (x>(NUMSIDES-1)){
                y++;
                x=NUMSIDES-3;
            }
        }
    }


    public void setPawnPosition(int x,int y, int dx,int dy) throws Exception {


        try {
            getPawn(dx,dy);
        } catch (Exception e) {
            if (validPosition(dx,dy)) {
                getPawn(x, y).setPosition(dx, dy);
            }
        }
    }

    public boolean validPosition(int x,int y){
        return !(x<0 || x>= NUMSIDES || y<0 || y>=NUMSIDES);
    }

    public Pawn getPawn(int x,int y) throws Exception{

        for (int i = 0 ; i <whitePawns.length ; i++){
            if (whitePawns[i].getX() == x && whitePawns[i].getY() == y){
                return whitePawns[i];
            }
        }

        for (int i = 0 ; i <blackPawns.length ; i++){
            if (blackPawns[i].getX() == x && blackPawns[i].getY() == y){
                return blackPawns[i];
            }
        }
        throw new Exception();
    }

    public Pawn[] getWhitePawns() {
        return whitePawns;
    }

    public Pawn[] getBlackPawns() {
        return blackPawns;
    }

    public Texture getBoard() {
        return board;
    }
}
