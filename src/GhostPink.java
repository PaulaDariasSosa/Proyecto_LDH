import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class GhostPink extends Ghost {
    private final static Image GHOST_PINK_IMAGE = new Image("res/ghostPink.png");

    private double speed = 3;
    private Random rand = new Random();
    private int direction = rand.nextInt(4);

    public GhostPink(Point topLeft) {
        super(topLeft);
    }
    public void move(ArrayList<Wall> walls) {
        Point pointGo;
        if (direction == 0) {
            // left
            pointGo = new Point(origin.x - speed, origin.y);
        } else if (direction == 1) {
            // right
            pointGo = new Point(origin.x + speed, origin.y);
        } else if (direction == 2) {
            // up
            pointGo = new Point(origin.x, origin.y - speed);
        } else {
            // down
            pointGo = new Point(origin.x, origin.y + speed);
        }
        ghostRectangle = new Rectangle(pointGo, GHOST_PINK_IMAGE.getWidth(), GHOST_PINK_IMAGE.getHeight());

        boolean colliding = false;
        for (Wall wall : walls) {
            if (ghostRectangle.intersects(wall.getWallRectangle())) {
                ghostRectangle = new Rectangle(origin, GHOST_PINK_IMAGE.getWidth(), GHOST_PINK_IMAGE.getHeight());
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
        direction = rand.nextInt(4);
    }

    public void resetPosition() {
        origin = ghostStartPoint;
        ghostRectangle = new Rectangle(origin, GHOST_PINK_IMAGE.getWidth(), GHOST_PINK_IMAGE.getHeight());
        direction = rand.nextInt(4);
    }

    public void draw() {
        GHOST_PINK_IMAGE.drawFromTopLeft(ghostRectangle.left(), ghostRectangle.top());
    }
}
