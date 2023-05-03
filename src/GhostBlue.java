import bagel.Image;
import bagel.util.Point;

public class GhostBlue extends Ghost {
    private final static Image GHOST_BLUE_IMAGE = new Image("res/ghostBlue.png");
    private final static double SPEED = 2;

    public GhostBlue(Point topLeft) {
        super(topLeft, SPEED);
        setImage(GHOST_BLUE_IMAGE);
        setDirection(DOWN);
    }

    public void changeDirection() {
        if (getDirection() == DOWN) {
            setDirection(UP);
        } else {
            setDirection(DOWN);
        }
    }
}
