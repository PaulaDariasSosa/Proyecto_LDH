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
        double curSpeed;
        if (frenzyMode) {
            curSpeed = getFrenzySpeed();
        } else {
            curSpeed = getSpeed();
        }
        if (direction == LEFT) {
            pointGo = new Point(getPosition().x - curSpeed, getPosition().y);
        } else if (direction == RIGHT) {
            pointGo = new Point(getPosition().x + curSpeed, getPosition().y);
        } else if (direction == UP) {
            pointGo = new Point(getPosition().x, getPosition().y - curSpeed);
        } else {
            // down
            pointGo = new Point(getPosition().x, getPosition().y + curSpeed);
        }

        boolean colliding = false;
        for (Wall wall : walls) {
            if (wall.collidesWith(this)) {
                changeDirection();
                colliding = true;
                break;
            }
        }
        if (!colliding) {
            setPosition(pointGo);
        }
    }

    public void changeDirection() {
        direction = rand.nextInt(4);
    }
}
