package statebasedgame;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class mainGame extends BasicGameState {

    ArrayList<Astroid> rocks;
    int timer;
    int timer2;
    Color darkGreen;
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
       rocks = new ArrayList();
        timer = 0;
        timer2 = 0;
        Astroid.setGameSize(800, 600);
        //get 10 asteroids
        for (int i = 0; i < 10; i++) {
            int rx = (int) (Math.random() * 750);
            int ry = (int) (Math.random() * 505 + 45);
            rocks.add(new Astroid(rx, ry));
        }
        rocks.get(0).setChosen();
        darkGreen = new org.newdawn.slick.Color (196, 0, 151);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException { 
        Input in = gc.getInput();
        int mx = gc.getInput().getMouseX();
        int my = gc.getInput().getMouseY();

        for (Astroid a : rocks) {
            a.move();
        }

        for (Astroid a : rocks) {
            if (a.hit(mx, my)
                    && in.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)
                    && a.isChosen()) {
                rocks.remove(a);
                break;
            }
        }
        if (rocks.size() > 0) {
            rocks.get(0).setChosen();
            timer2++;
            timer++;
            if (timer == 500) {
                timer = 0;
                int rx = (int) (Math.random() * 750);
                int ry = (int) (Math.random() * 505 + 45);
                rocks.add(new Astroid(rx, ry));
            }
        }
        else{
            sbg.enterState(2,new FadeOutTransition(), new FadeInTransition());
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor (darkGreen);
        g.fill(new Rectangle (0,0,800,40));
        g.setColor(Color.red);
        for (Astroid a : rocks) {
            if (a.isChosen()) {
                g.fill(a.getHitBox());
            }
            a.draw();
        }
        g.setColor(Color.yellow);
        g.drawString("Time: " + (double)timer2/100, 10, 10);
        g.drawString ("Rocks remaining: " + rocks.size(), 615, 10);
    }
    
    public int getID(){
        return 1;
    }
    
}
