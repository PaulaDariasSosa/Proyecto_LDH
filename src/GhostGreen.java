import bagel.Image;
import bagel.util.Point;

import java.util.ArrayList;
import java.util.Random;

public class GhostGreen extends Ghost {
    private final static Image GHOST_GREEN_IMAGE = new Image("res/ghostGreen.png");
    private final static double SPEED = 4;

    private final Random rand = new Random();
    // randomly generation direction between DOWN and RIGHT
    private int direction = rand.nextInt(2);
    public GhostGreen(Point topLeft) {
        super(topLeft, SPEED);
        setImage(GHOST_GREEN_IMAGE);
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
        if (direction == RIGHT) {
            direction = LEFT;
        } else if (direction == LEFT) {
            direction = RIGHT;
        } else if (direction == DOWN) {
            direction = UP;
        } else {
            direction = DOWN;
        }
    }
}
