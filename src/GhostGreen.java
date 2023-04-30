import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class GhostGreen extends Ghost {
    private final static Image GHOST_GREEN_IMAGE = new Image("res/ghostGreen.png");
    private final static double SPEED = 4;
    private final static double FRENZY_SPEED = SPEED - 0.5;

    private final Random rand = new Random();
    // randomly generation direction between DOWN and RIGHT
    private int direction = rand.nextInt(2);
    public GhostGreen(Point topLeft) {
        super(topLeft);
    }

    public void move(ArrayList<Wall> walls, boolean frenzyMode) {
        double curSpeed;
        if (frenzyMode) {
            curSpeed = FRENZY_SPEED;
        } else {
            curSpeed = SPEED;
        }
        if (direction == LEFT) {
            pointGo = new Point(origin.x - curSpeed, origin.y);
        } else if (direction == RIGHT) {
            pointGo = new Point(origin.x + curSpeed, origin.y);
        } else if (direction == UP) {
            pointGo = new Point(origin.x, origin.y - curSpeed);
        } else {
            // down
            pointGo = new Point(origin.x, origin.y + curSpeed);
        }
        ghostRectangle = new Rectangle(pointGo, GHOST_GREEN_IMAGE.getWidth(), GHOST_GREEN_IMAGE.getHeight());

        boolean colliding = false;
        for (Wall wall : walls) {
            if (this.collidesWith(wall)) {
                ghostRectangle = new Rectangle(origin, GHOST_GREEN_IMAGE.getWidth(), GHOST_GREEN_IMAGE.getHeight());
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
        } else if (direction == LEFT) {
            direction = RIGHT;
        } else if (direction == DOWN) {
            direction = UP;
        } else {
            direction = DOWN;
        }
    }

    public void resetPosition() {
        origin = ghostStartPoint;
        ghostRectangle = new Rectangle(origin, GHOST_GREEN_IMAGE.getWidth(), GHOST_GREEN_IMAGE.getHeight());
        direction = rand.nextInt(2);
    }

    public void draw(boolean frenzyMode) {
        if (frenzyMode) {
            GHOST_FRENZY_IMAGE.drawFromTopLeft(ghostRectangle.left(), ghostRectangle.top());
        } else {
            GHOST_GREEN_IMAGE.drawFromTopLeft(ghostRectangle.left(), ghostRectangle.top());
        }
    }
}
