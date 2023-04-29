import bagel.Image;
import bagel.util.Point;

public class GhostBlue extends Ghost {
    private final static Image GHOST_BLUE_IMAGE = new Image("res/ghostBlue.png");
    public GhostBlue(Point topLeft) {
        super(topLeft);
    }
    public void move() {
    }

    public void changeDirection() {
    }

    public void draw() {
        GHOST_BLUE_IMAGE.drawFromTopLeft(ghostRectangle.left(), ghostRectangle.top());
    }
}
