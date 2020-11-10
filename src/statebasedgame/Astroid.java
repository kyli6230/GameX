package statebasedgame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;

public class Astroid {
    private Image image;
    private Rectangle hitbox;
    private int xdir, ydir;
    private boolean isChosen;
    
    private static int GAME_WIDTH;
    private static int GAME_HEIGHT;
    
    public Astroid (int x, int y) throws SlickException{
        image = new Image ("images/astroid.png");
        hitbox = new Rectangle (x,y, image.getWidth(), image.getHeight());
        isChosen=false;
        //randomly assign from -3 to +3 with zero not allowed
        while (true){
            xdir = (int)(Math.random() * 7 - 3); //-3, -2, -1, 0, 1, 2, 3
            ydir = (int)(Math.random() * 7 - 3);
            if (xdir !=0 && ydir !=0)
                break;
        }
    }
    
    public Rectangle getHitBox(){
        return hitbox;
    }
    
    public void setChosen(){
        isChosen=true;
    }
    
    public boolean isChosen(){
        return isChosen;
    }
    
    public static void setGameSize(int x, int y){
        GAME_HEIGHT=y;
        GAME_WIDTH=x;
    }
    
    public boolean hit (int x, int y){
        if (hitbox.contains(x,y)) return true;
        else return false;
    }
    
    public void move(){
        hitbox.setX(hitbox.getX() + xdir);
        hitbox.setY(hitbox.getY() + ydir);
        
        //bounce when we hit a side
        if (hitbox.getX() <= 0 || hitbox.getX() > GAME_WIDTH-image.getWidth()) xdir=-xdir;
        if (hitbox.getY() <= 40 || hitbox.getY() > GAME_HEIGHT-image.getHeight()) ydir=-ydir;
    }
    
    public void draw(){
        image.draw(hitbox.getX(),hitbox.getY());
    }
}
