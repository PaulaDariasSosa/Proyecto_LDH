package es.ull.app;

import bagel.Image;
import bagel.util.Point;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @brief es.ull.app.GhostGreen class, a subclass
 */
public class GhostGreen extends Ghost {
    private static final Image GHOST_GREEN_IMAGE = new Image("res/ghostGreen.png");
    private static final double SPEED = 8;

    /**
     * Constructor for es.ull.app.GhostGreen
     * @param topLeft
     */
    public GhostGreen(Point topLeft) {
        super(topLeft, SPEED);
        setImage(GHOST_GREEN_IMAGE);
        // randomly generation direction between DOWN and RIGHT
        Random rand = new SecureRandom();
        setDirection(rand.nextInt(2));
    }

    /**
     * @brief Change direction of the ghost
     */
    @Override
    public void changeDirection() {
        if (getDirection() == RIGHT) {
            setDirection(LEFT);
        } else if (getDirection() == LEFT) {
            setDirection(RIGHT);
        } else if (getDirection() == DOWN) {
            setDirection(UP);
        } else {
            setDirection(DOWN);
        }
    }
}
