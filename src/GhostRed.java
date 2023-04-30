import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.ArrayList;

public class GhostRed extends Ghost {
    private final static Image GHOST_RED_IMAGE = new Image("res/ghostRed.png");
    private double speed = 1;
    public GhostRed(Point topLeft) {
        super(topLeft);
    }

    public void move(ArrayList<Wall> walls) {
        Point pointGo = new Point(origin.x + speed, origin.y);
        ghostRectangle = new Rectangle(pointGo, GHOST_RED_IMAGE.getWidth(), GHOST_RED_IMAGE.getHeight());

        boolean colliding = false;
        for (Wall wall : walls) {
            if (this.collidesWith(wall)) {
                ghostRectangle = new Rectangle(origin, GHOST_RED_IMAGE.getWidth(), GHOST_RED_IMAGE.getHeight());
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
        speed *= -1;
    }

    /**
     * Draws the ghost image at the
     * coordinate of the ghost Rectangle
     */
    public void draw(boolean frenzyMode) {
        if (frenzyMode) {
            GHOST_FRENZY_IMAGE.drawFromTopLeft(ghostRectangle.left(), ghostRectangle.top());
        } else {
            GHOST_RED_IMAGE.drawFromTopLeft(ghostRectangle.left(), ghostRectangle.top());
        }
    }
}
