import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public class GhostRed extends Ghost {
    private final static Image GHOST_RED_IMAGE = new Image("res/ghostRed.png");
    private double speed = 1;
    public GhostRed(Point topLeft) {
        super(topLeft);
    }

    public void move() {
        origin = new Point(origin.x + speed, origin.y);
        ghostRectangle = new Rectangle(origin, GHOST_RED_IMAGE.getWidth(), GHOST_RED_IMAGE.getHeight());
    }

    public void changeDirection() {
        speed *= -1;
    }

    /**
     * Draws the ghost image at the
     * coordinate of the ghost Rectangle
     */
    public void draw() {
        GHOST_RED_IMAGE.drawFromTopLeft(ghostRectangle.left(), ghostRectangle.top());
    }
}
