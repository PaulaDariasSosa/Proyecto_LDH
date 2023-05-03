import bagel.Image;
import bagel.util.Point;

import java.util.ArrayList;

public class GhostRed extends Ghost {
    private final static Image GHOST_RED_IMAGE = new Image("res/ghostRed.png");
    private final static double SPEED = 1;

    public GhostRed(Point topLeft) {
        super(topLeft, SPEED);
        setImage(GHOST_RED_IMAGE);
        setDirection(RIGHT);
    }

    public void changeDirection() {
        if (getDirection() == RIGHT) {
            setDirection(LEFT);
        } else {
            setDirection(RIGHT);
        }
    }

}
