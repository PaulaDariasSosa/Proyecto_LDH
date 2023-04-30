import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.ArrayList;

public class GhostBlue extends Ghost {
    private final static Image GHOST_BLUE_IMAGE = new Image("res/ghostBlue.png");
    private final static double SPEED = 2;
    private final static double FRENZY_SPEED = SPEED - 0.5;
    private int direction = DOWN;

    public GhostBlue(Point topLeft) {
        super(GHOST_BLUE_IMAGE, topLeft);
    }
    public void move(ArrayList<Wall> walls, boolean frenzyMode) {
        double curSpeed;
        if (frenzyMode) {
            curSpeed = FRENZY_SPEED;
        } else {
            curSpeed = SPEED;
        }
        if (direction == DOWN) {
            pointGo = new Point(origin.x, origin.y + curSpeed);
        } else {
            pointGo = new Point(origin.x, origin.y - curSpeed);
        }
        this.setRectangle(new Rectangle(pointGo, GHOST_BLUE_IMAGE.getWidth(), GHOST_BLUE_IMAGE.getHeight()));

        boolean colliding = false;
        for (Wall wall : walls) {
            if (wall.collidesWith(this)) {
                this.setRectangle(new Rectangle(origin, GHOST_BLUE_IMAGE.getWidth(), GHOST_BLUE_IMAGE.getHeight()));
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
        if (direction == DOWN) {
            direction = UP;
        } else {
            direction = DOWN;
        }
    }

    public void draw(boolean frenzyMode) {
        if (frenzyMode) {
            GHOST_FRENZY_IMAGE.drawFromTopLeft(this.getRectangle().left(), this.getRectangle().top());
        } else {
            GHOST_BLUE_IMAGE.drawFromTopLeft(this.getRectangle().left(), this.getRectangle().top());
        }
    }
}
