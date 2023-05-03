import bagel.Image;
import bagel.util.Point;

import java.util.ArrayList;
import java.util.Random;

public class GhostPink extends Ghost {
    private final static Image GHOST_PINK_IMAGE = new Image("res/ghostPink.png");
    private final static double SPEED = 3;
    private final Random rand = new Random();
    private int direction = rand.nextInt(4);

    public GhostPink(Point topLeft) {
        super(topLeft, SPEED);
        setImage(GHOST_PINK_IMAGE);
    }
    public void move(ArrayList<Wall> walls, boolean frenzyMode) {
        if (direction == LEFT) {
            goLeft(walls, frenzyMode);
        } else if (direction == RIGHT) {
            goRight(walls, frenzyMode);
        } else if (direction == UP) {
            goUp(walls, frenzyMode);
        } else {
            goDown(walls, frenzyMode);
        }
    }

    public void changeDirection() {
        direction = rand.nextInt(4);
    }
}
