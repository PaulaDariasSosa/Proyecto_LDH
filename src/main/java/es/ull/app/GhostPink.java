package es.ull.app;

import bagel.Image;
import bagel.util.Point;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @brief es.ull.app.GhostPink class, a subclass of es.ull.app.Ghost
 */
public class GhostPink extends Ghost {
    private static final Image GHOST_PINK_IMAGE = new Image("res/ghostPink.png");
    private static final double SPEED = 5;
    private final SecureRandom rand = new SecureRandom();

    /**
     * Constructor for es.ull.app.GhostPink
     * @param topLeft the top left point of the ghost
     */
    public GhostPink(Point topLeft) {
        super(topLeft, SPEED);
        setImage(GHOST_PINK_IMAGE);
        // randomly generate direction between 4 directions
        setDirection(rand.nextInt(4));
    }

    /**
     * @brief change the direction of the ghost randomly
     */
    @Override
    public void changeDirection() {
        setDirection(rand.nextInt(4));
    }
}
