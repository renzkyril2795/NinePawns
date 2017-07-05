package com.ninepawns.game.Sprites;

import com.ninepawns.game.States.PlayState;

import java.util.List;

/**
 * Created by renzkyril on 04/07/2017.
 */

public class Player {
    private String name;
    private boolean isWhite;

    public Player(String name,boolean isWhite){
        this.name = name;
        this.isWhite = isWhite;
    }

    public boolean move(Board board,int x,int y,int dx,int dy){
        Pawn pawn = null;
        try {
            pawn = board.getPawn(x,y);

            if (pawn.isWhite() == this.isWhite){
                board.setPawnPosition(x,y,dx,dy);
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            System.out.println(" There is no pawn on "+x+","+y);
            return false;
        }


    }

}
