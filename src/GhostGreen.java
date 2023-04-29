import bagel.Image;
import bagel.util.Point;

public class GhostGreen extends Ghost {
    private final static Image GHOST_GREEN_IMAGE = new Image("res/ghostGreen.png");
    public GhostGreen(Point topLeft) {
        super(topLeft);
    }

    @Override
    public void draw() {
        GHOST_GREEN_IMAGE.drawFromTopLeft(ghostRectangle.left(), ghostRectangle.top());
    }
}
