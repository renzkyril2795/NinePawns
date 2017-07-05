package com.ninepawns.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.ninepawns.game.Sprites.Board;
import com.ninepawns.game.Sprites.Pawn;
import com.ninepawns.game.Sprites.Player;
import com.ninepawns.game.ninePawns;

/**
 * Created by renzkyril on 02/07/2017.
 */

public class PlayState extends State implements InputProcessor{

    private boolean isWhiteTurn;
    private boolean isPlayer1White;
    private Player player1 , player2;
    private Board board;
    private Pawn pawn;
    private int x,y;
    private int dragx,dragy;
    private Rectangle whiteObjective;
    private Rectangle blackObjective;

    private int homeScore , visitorScore;
    private Texture bg;
    private BitmapFont scores;
    private String textScore;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        Gdx.input.setInputProcessor(this);
        this.isPlayer1White = true;
        this.player1 = new Player("player1",this.isPlayer1White);
        this.player2 = new Player("player2",!this.isPlayer1White);
        this.board = new Board();
        this.isWhiteTurn = true;
        this.whiteObjective = new Rectangle(board.SIDE*4,board.SIDE*4,ninePawns.WIDTH-board.SIDE*4,ninePawns.WIDTH-board.SIDE*4);
        this.blackObjective = new Rectangle(board.SIDE*0,board.SIDE*0,ninePawns.WIDTH-board.SIDE*5,ninePawns.WIDTH-board.SIDE*5);

        this.homeScore = 0;
        this.visitorScore = 0;
        this.bg = new Texture("background.jpg");
        this.scores = new BitmapFont();
        this.textScore = "home "+homeScore+"   "+visitorScore+" visitor";
    }

    public boolean whiteWins(){
        for (Pawn pawn:board.getWhitePawns()) {
            if(!this.whiteObjective.contains(pawn.getScreenX(),pawn.getScreenY())){
                return false;
            }
        }
        return true;
    }

    public boolean blackWins(){
        for (Pawn pawn:board.getBlackPawns()) {
            if(!this.blackObjective.contains(pawn.getScreenX(),pawn.getScreenY())){
                return false;
            }
        }
        return true;
    }

    public boolean validMove(int x,int y,int dx,int dy){
        int tmpx,tmpy;
        if (x-dx>0){
            tmpx = x-1;
        }else if(x-dx<0){
            tmpx = x+1;
        }else{
            tmpx = x;
        }

        if (y-dy>0){
            tmpy = y-1;
        }else if(y-dy<0){
            tmpy = y+1;
        }else{
            tmpy = y;
        }

        try {
            board.getPawn(tmpx,tmpy);
        } catch (Exception e) {
            System.out.println("Not a valid move "+dx+","+dy);
            return false;
        }
        System.out.println((x-dx)+","+(y-dy));
        return (((x-dx)==-2 || (x-dx)==2) && ((y-dy)==-2 || (y-dy)==2)) ||  //diagonal
                 ((x-dx)==0 && ((y-dy)==-2 || (y-dy)==2)) || //vertical
                ((y-dy)==0 && ((x-dx)==-2 || (x-dx)==2)); //horizontal
    }

    public void reset(){
        this.isPlayer1White = !this.isPlayer1White;
        this.player1 = new Player("player1",this.isPlayer1White);
        this.player2 = new Player("player2",!this.isPlayer1White);
        this.board = new Board();
        this.isWhiteTurn = true;
    }

    @Override
    protected void handleInput() {
        if (whiteWins() || blackWins()){
            this.reset();
        }

        if (this.whiteWins()){
            System.out.println("white won");
            homeScore++;
        }
        if(this.blackWins()){
            System.out.println("black won");
            visitorScore++;
        }
        this.textScore = "home "+homeScore+"   "+visitorScore+" visitor";
    }

    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.begin();
        // ****** SCORE ******
        sb.draw(bg,0,ninePawns.WIDTH,ninePawns.WIDTH,ninePawns.HEIGHT-ninePawns.WIDTH);
        scores.setColor(Color.BLACK);
        scores.getData().setScale(2,2);
        scores.draw(sb,textScore,ninePawns.WIDTH/2-scores.getRegion().getTexture().getWidth()/2,
                ninePawns.WIDTH+(ninePawns.HEIGHT-ninePawns.WIDTH)/2-scores.getRegion().getTexture().getHeight()/2);
        // *******************

        // ******* BOARD *****
        sb.draw(board.getBoard(),0,0, ninePawns.WIDTH,ninePawns.WIDTH);
        if (this.pawn !=null) {
            sb.draw(this.pawn.getPawn(), this.dragx-this.pawn.getPawn().getWidth()/2,ninePawns.HEIGHT-this.dragy-board.SIDE-this.pawn.getPawn().getHeight()/2);
        }
        for (Pawn pawn:board.getWhitePawns()) {
            if (!(this.pawn != null && this.pawn.getX()==pawn.getX() && this.pawn.getY() == pawn.getY())) {
                sb.draw(pawn.getPawn(), pawn.getScreenX(), pawn.getScreenY());
            }
        }
        for (Pawn pawn:board.getBlackPawns()) {
            if (!(this.pawn != null && this.pawn.getX()==pawn.getX() && this.pawn.getY() == pawn.getY())) {
                sb.draw(pawn.getPawn(), pawn.getScreenX(), pawn.getScreenY());
            }
        }
        // *****************
        sb.end();
    }

    @Override
    protected void dispose() {

    }

    /**
     * Called when a key was pressed
     *
     * @param keycode one of the constants in {@link Input.Keys}
     * @return whether the input was processed
     */
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    /**
     * Called when a key was released
     *
     * @param keycode one of the constants in {@link Input.Keys}
     * @return whether the input was processed
     */
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    /**
     * Called when a key was typed
     *
     * @param character The character
     * @return whether the input was processed
     */
    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * Called when the screen was touched or a mouse button was pressed. The button parameter will be {@link Buttons#LEFT} on iOS.
     *
     * @param screenX The x coordinate, origin is in the upper left corner
     * @param screenY The y coordinate, origin is in the upper left corner
     * @param pointer the pointer for the event.
     * @param button  the button
     * @return whether the input was processed
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        try {
            this.x = screenX/board.SIDE;
            this.y = (ninePawns.HEIGHT-screenY-Board.SIDE)/board.SIDE;
            this.pawn = this.board.getPawn(x,y);
            this.dragx = screenX;
            this.dragy = screenY;
        } catch (Exception e) {
            System.out.println("Pawn does'nt exists!");
        }

        return false;
    }

    /**
     * Called when a finger was lifted or a mouse button was released. The button parameter will be {@link Buttons#LEFT} on iOS.
     *
     * @param screenX
     * @param screenY
     * @param pointer the pointer for the event.
     * @param button  the button   @return whether the input was processed
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        int dx = screenX/board.SIDE;
        int dy = (ninePawns.HEIGHT - screenY - Board.SIDE)/board.SIDE;
        this.pawn = null;
        if (this.validMove(this.x,this.y,dx,dy)) {
            if (isWhiteTurn) {
                if (player1.move(board, this.x, this.y, dx, dy)) {
                    isWhiteTurn = false;
                }
            } else {
                if (player2.move(board, x, y, dx, dy)) {
                    isWhiteTurn = true;
                }
            }
        }
        return false;
    }

    /**
     * Called when a finger or the mouse was dragged.
     *
     * @param screenX
     * @param screenY
     * @param pointer the pointer for the event.  @return whether the input was processed
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        this.dragx = screenX;
        this.dragy = screenY;
        return false;
    }

    /**
     * Called when the mouse was moved without any buttons being pressed. Will not be called on iOS.
     *
     * @param screenX
     * @param screenY
     * @return whether the input was processed
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    /**
     * Called when the mouse wheel was scrolled. Will not be called on iOS.
     *
     * @param amount the scroll amount, -1 or 1 depending on the direction the wheel was scrolled.
     * @return whether the input was processed.
     */
    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
