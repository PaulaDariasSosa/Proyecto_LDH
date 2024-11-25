package es.ull.app;

import bagel.Image;
import bagel.util.Point;

/**
 * @brief Class for the es.ull.app.GhostBlue object
 */
public class GhostBlue extends Ghost {
    private static final Image GHOST_BLUE_IMAGE = new Image("res/ghostBlue.png");
    private static final double SPEED = 4;

    /**
     * @brief Constructor for the es.ull.app.GhostBlue class
     * @param topLeft The top left corner of the ghost
     */
    public GhostBlue(Point topLeft) {
        super(topLeft, SPEED);
        setImage(GHOST_BLUE_IMAGE);
        setDirection(DOWN);
    }

    /**
     * @brief Method that changes the direction of the ghost
     */
    @Override
    public void changeDirection() {
        if (getDirection() == DOWN) {
            setDirection(UP);
        } else {
            setDirection(DOWN);
        }
    }
}
