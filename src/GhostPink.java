import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class GhostPink extends Ghost {
    private final static Image GHOST_PINK_IMAGE = new Image("res/ghostPink.png");
    private final static double SPEED = 3;
    private final Random rand = new Random();
    private int direction = rand.nextInt(4);

    public GhostPink(Point topLeft) {
        super(GHOST_PINK_IMAGE, topLeft, SPEED);
    }
    public void move(ArrayList<Wall> walls, boolean frenzyMode) {
        double curSpeed;
        if (frenzyMode) {
            curSpeed = getFrenzySpeed();
        } else {
            curSpeed = getSpeed();
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
        this.setRectangle(new Rectangle(pointGo, GHOST_PINK_IMAGE.getWidth(), GHOST_PINK_IMAGE.getHeight()));

        boolean colliding = false;
        for (Wall wall : walls) {
            if (wall.collidesWith(this)) {
                setRectangle(new Rectangle(origin, GHOST_PINK_IMAGE.getWidth(), GHOST_PINK_IMAGE.getHeight()));
                changeDirection();
                colliding = true;
                break;
            }
        }
        if (!colliding) {
            origin = pointGo;
        }
    }

    public void changeDirection() {
        direction = rand.nextInt(4);
    }
}
