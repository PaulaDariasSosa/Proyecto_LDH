import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class GhostGreen extends Ghost {
    private final static Image GHOST_GREEN_IMAGE = new Image("res/ghostGreen.png");
    private double speed = 4;
    private Random rand = new Random();
    private int direction = rand.nextInt(2);
    public GhostGreen(Point topLeft) {
        super(topLeft);
    }

    public void move(ArrayList<Wall> walls) {
        Point pointGo;
        if (direction == 0) {
            // vertical
            pointGo = new Point(origin.x + speed, origin.y);
        }
        else {
            // horizontal
            pointGo = new Point(origin.x, origin.y + speed);
        }
        ghostRectangle = new Rectangle(pointGo, GHOST_GREEN_IMAGE.getWidth(), GHOST_GREEN_IMAGE.getHeight());

        boolean colliding = false;
        for (Wall wall : walls) {
            if (ghostRectangle.intersects(wall.getWallRectangle())) {
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
        speed *= -1;
    }

    public void resetPosition() {
        origin = ghostStartPoint;
        ghostRectangle = new Rectangle(origin, GHOST_GREEN_IMAGE.getWidth(), GHOST_GREEN_IMAGE.getHeight());
        direction = rand.nextInt(2);
    }

    public void draw() {
        GHOST_GREEN_IMAGE.drawFromTopLeft(ghostRectangle.left(), ghostRectangle.top());
    }
}
