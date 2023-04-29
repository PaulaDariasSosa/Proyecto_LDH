import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public class GhostGreen extends Ghost {
    private final static Image GHOST_GREEN_IMAGE = new Image("res/ghostGreen.png");
    public GhostGreen(Point topLeft) {
        super(topLeft);
    }

    public void move() {
    }

    public void changeDirection() {
    }

    public void draw() {
        GHOST_GREEN_IMAGE.drawFromTopLeft(ghostRectangle.left(), ghostRectangle.top());
    }
}
