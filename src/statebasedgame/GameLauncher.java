package statebasedgame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class GameLauncher extends StateBasedGame {

    public GameLauncher(String title) {
        super(title);
    }
    
    public void initStatesList(GameContainer gc) throws SlickException {
       //list other screens in here - first one is opening screen
       this.addState(new startScreen());
       this.addState(new mainGame());
       this.addState(new endScreen());
    }

    public static void main(String args[]) throws SlickException {
        GameLauncher game = new GameLauncher("Astroid Clicker");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(100);
        app.start();
    }

}