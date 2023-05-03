import bagel.Image;
import bagel.util.Point;

import java.util.ArrayList;
import java.util.Random;

public class GhostPink extends Ghost {
    private final static Image GHOST_PINK_IMAGE = new Image("res/ghostPink.png");
    private final static double SPEED = 3;
    private final Random rand = new Random();

    public GhostPink(Point topLeft) {
        super(topLeft, SPEED);
        setImage(GHOST_PINK_IMAGE);
        // randomly generation direction between 4 directions
        setDirection(rand.nextInt(4));
    }
    public void move(ArrayList<Wall> walls, boolean frenzyMode) {
        if (getDirection() == LEFT) {
            goLeft(walls, frenzyMode);
        } else if (getDirection() == RIGHT) {
            goRight(walls, frenzyMode);
        } else if (getDirection() == UP) {
            goUp(walls, frenzyMode);
        } else {
            goDown(walls, frenzyMode);
        }
    }

    public void changeDirection() {
        setDirection(rand.nextInt(4));
    }
}
