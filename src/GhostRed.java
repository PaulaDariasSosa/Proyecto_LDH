import bagel.Image;
import bagel.util.Point;

/**
 * @brief GhostRed class
 */
public class GhostRed extends Ghost {
    private final static Image GHOST_RED_IMAGE = new Image("res/ghostRed.png");
    private final static double SPEED = 2;

    /**
     * @brief Constructor for GhostRed
     * @param topLeft
     */
    public GhostRed(Point topLeft) {
        super(topLeft, SPEED);
        setImage(GHOST_RED_IMAGE);
        setDirection(RIGHT);
    }

    /**
     * @brief Change direction of GhostRed
     */
    @Override
    public void changeDirection() {
        if (getDirection() == RIGHT) {
            setDirection(LEFT);
        } else {
            setDirection(RIGHT);
        }
    }

}
