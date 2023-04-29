import bagel.Image;
import bagel.util.Point;

public class GhostRed extends Ghost {
    private final static Image GHOST_RED_IMAGE = new Image("res/ghostRed.png");
    public GhostRed(Point topLeft) {
        super(topLeft);
    }

    @Override
    public void draw() {
        GHOST_RED_IMAGE.drawFromTopLeft(ghostRectangle.left(), ghostRectangle.top());
    }
}
