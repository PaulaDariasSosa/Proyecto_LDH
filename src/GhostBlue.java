import bagel.Image;
import bagel.util.Point;

import java.util.ArrayList;

public class GhostBlue extends Ghost {
    private final static Image GHOST_BLUE_IMAGE = new Image("res/ghostBlue.png");
    private final static double SPEED = 2;
    private int direction = DOWN;

    public GhostBlue(Point topLeft) {
        super(topLeft, SPEED);
        setImage(GHOST_BLUE_IMAGE);
    }
    public void move(ArrayList<Wall> walls, boolean frenzyMode) {
        if (direction == DOWN) {
            goDown(walls, frenzyMode);
        } else {
            goUp(walls, frenzyMode);
        }
    }

    public void changeDirection() {
        if (direction == DOWN) {
            direction = UP;
        } else {
            direction = DOWN;
        }
    }
}
