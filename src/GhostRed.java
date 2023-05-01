import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.ArrayList;

public class GhostRed extends Ghost {
    private final static Image GHOST_RED_IMAGE = new Image("res/ghostRed.png");
    private final static double SPEED = 1;
    private int direction = RIGHT;

    public GhostRed(Point topLeft) {
        super(GHOST_RED_IMAGE, topLeft, SPEED);
    }

    public void move(ArrayList<Wall> walls, boolean frenzyMode) {
        double curSpeed;
        if (frenzyMode) {
            curSpeed = getFrenzySpeed();
        } else {
            curSpeed = getSpeed();
        }
        if (direction == RIGHT) {
            pointGo = new Point(origin.x + curSpeed, origin.y);
        } else {
            pointGo = new Point(origin.x - curSpeed, origin.y);
        }

        this.setRectangle(new Rectangle(pointGo, GHOST_RED_IMAGE.getWidth(), GHOST_RED_IMAGE.getHeight()));

        boolean colliding = false;
        for (Wall wall : walls) {
            if (wall.collidesWith(this)) {
                this.setRectangle(new Rectangle(origin, GHOST_RED_IMAGE.getWidth(), GHOST_RED_IMAGE.getHeight()));
                this.changeDirection();
                colliding = true;
                break;
            }
        }
        if (!colliding) {
            origin = pointGo;
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
