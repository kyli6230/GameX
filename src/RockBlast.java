
import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class RockBlast extends BasicGame {

    ArrayList<Astroid> rocks;
    int timer;
    int timer2;
    Color darkGreen;

    public RockBlast(String title) {
        super(title);
    }

    public void init(GameContainer gc) throws SlickException {
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
        darkGreen = new Color (196, 0, 151);
    }

    public void update(GameContainer gc, int i) throws SlickException {
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
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
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

    public static void main(String args[]) throws SlickException {
        RockBlast game = new RockBlast("Testing Game");
        AppGameContainer app = new AppGameContainer(game);
        app.setDisplayMode(800, 600, false);
        app.setShowFPS(false);
        app.setTargetFrameRate(100);
        app.start();
    }

}
