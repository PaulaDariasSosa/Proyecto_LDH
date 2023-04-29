import bagel.Image;
import bagel.util.Point;

public class GhostPink extends Ghost {
    private final static Image GHOST_PINK_IMAGE = new Image("res/ghostPink.png");
    public GhostPink(Point topLeft) {
        super(topLeft);
    }
    public void move() {
    }

    public void changeDirection() {
    }

    public void draw() {
        GHOST_PINK_IMAGE.drawFromTopLeft(ghostRectangle.left(), ghostRectangle.top());
    }
}
