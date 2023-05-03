import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

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
        double curSpeed;
        if (frenzyMode) {
            curSpeed = getFrenzySpeed();
        } else {
            curSpeed = getSpeed();
        }
        if (direction == DOWN) {
            pointGo = new Point(getPosition().x, getPosition().y + curSpeed);
        } else {
            pointGo = new Point(getPosition().x, getPosition().y - curSpeed);
        }
//        this.setRectangle(new Rectangle(pointGo, GHOST_BLUE_IMAGE.getWidth(), GHOST_BLUE_IMAGE.getHeight()));

        boolean colliding = false;
        for (Wall wall : walls) {
            if (wall.collidesWith(this)) {
//                this.setRectangle(new Rectangle(getPosition(), GHOST_BLUE_IMAGE.getWidth(), GHOST_BLUE_IMAGE.getHeight()));
                this.changeDirection();
                colliding = true;
                break;
            }
        }
        if (!colliding) {
            setPosition(pointGo);
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
