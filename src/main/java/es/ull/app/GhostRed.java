package es.ull.app;

import bagel.Image;
import bagel.util.Point;

/**
 * @brief es.ull.app.GhostRed class
 */
public class GhostRed extends Ghost {
    private static final Image GHOST_RED_IMAGE = new Image("res/ghostRed.png");
    private static final double SPEED = 2;

    /**
     * @brief Constructor for es.ull.app.GhostRed
     * @param topLeft
     */
    public GhostRed(Point topLeft) {
        super(topLeft, SPEED);
        setImage(GHOST_RED_IMAGE);
        setDirection(RIGHT);
    }

    /**
     * @brief Change direction of es.ull.app.GhostRed
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
