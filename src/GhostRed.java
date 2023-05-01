import bagel.Image;
import bagel.util.Point;

import java.util.ArrayList;

public class GhostRed extends Ghost {
    private final static Image GHOST_RED_IMAGE = new Image("res/ghostRed.png");
    private final static double SPEED = 1;
    private int direction = RIGHT;

    public GhostRed(Point topLeft) {
        super(GHOST_RED_IMAGE, topLeft, SPEED);
    }

    public void move(ArrayList<Wall> walls, boolean frenzyMode) {
        if (direction == RIGHT) {
            goRight(walls, frenzyMode);
        } else {
            goLeft(walls, frenzyMode);
        }
    }

    public void changeDirection() {
        if (direction == RIGHT) {
            direction = LEFT;
        } else {
            direction = RIGHT;
        }
    }

    /**
     * Draws the ghost image at the
     * coordinate of the ghost Rectangle
     */
    public void draw(boolean frenzyMode) {
        if (frenzyMode) {
            GHOST_FRENZY_IMAGE.drawFromTopLeft(this.getRectangle().left(), this.getRectangle().top());
        } else {
            GHOST_RED_IMAGE.drawFromTopLeft(this.getRectangle().left(), this.getRectangle().top());
        }
    }
}
