import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.ArrayList;

public class GhostBlue extends Ghost {
    private final static Image GHOST_BLUE_IMAGE = new Image("res/ghostBlue.png");
    private double speed = 2;
    public GhostBlue(Point topLeft) {
        super(topLeft);
    }
    public void move(ArrayList<Wall> walls) {
        origin = new Point(origin.x, origin.y+ speed);
        ghostRectangle = new Rectangle(origin, GHOST_BLUE_IMAGE.getWidth(), GHOST_BLUE_IMAGE.getHeight());
    }

    public void changeDirection() {
        speed *= -1;
    }

    public void draw() {
        GHOST_BLUE_IMAGE.drawFromTopLeft(ghostRectangle.left(), ghostRectangle.top());
    }
}
