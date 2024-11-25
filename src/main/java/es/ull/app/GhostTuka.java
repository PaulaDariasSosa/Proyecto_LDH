package es.ull.app;

import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Point;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @brief es.ull.app.GhostTuka class, a subclass
 */
public class GhostTuka extends Ghost {
    private static final Image GHOST_TUKA_IMAGE = new Image("res/ghostTuka.png");
    private static final Image GHOST_TUKA_OPEN_IMAGE = new Image("res/ghostTukaOpen.png");

    private static final int SWITCH_FRAMES = 7;
    private final DrawOptions rotation;
    private int switchFrameCount;
    private boolean isOpen = false;

    private static final double SPEED = 7;
    private final SecureRandom rand = new SecureRandom();

    /**
     * @brief Constructor for es.ull.app.GhostTuka
     * @param topLeft
     */
    public GhostTuka(Point topLeft) {
        super(topLeft, SPEED);
        setImage(GHOST_TUKA_IMAGE);
        switchFrameCount = SWITCH_FRAMES;
        rotation = new DrawOptions();
        // randomly generate direction between 4 directions
        setDirection(rand.nextInt(4));
    }

    /**
     * @brief Update the ghost
     * @param frenzyMode
     * @param timeFrozen
     */
    public void update(boolean frenzyMode, boolean timeFrozen) {
        if (isRespawning()) {
            respawn();
        } else {
            if (isActive()) {
                if (frenzyMode) {
                    super.update(true);
                } else {
                    // switching the image being rendered
                    if (!timeFrozen) {
                        switchFrameCount--;
                    }
                    if (switchFrameCount == 0) {
                        if (isOpen) {
                            setImage(GHOST_TUKA_OPEN_IMAGE);
                            isOpen = false;
                        } else {
                            setImage(GHOST_TUKA_IMAGE);
                            isOpen = true;
                        }
                        switchFrameCount = SWITCH_FRAMES;
                    }
                    if (getDirection() == LEFT) {
                        rotation.setRotation(0.5 * Math.PI);
                    } else if (getDirection() == RIGHT) {
                        rotation.setRotation(1.5 * Math.PI);
                    } else if (getDirection() == UP) {
                        rotation.setRotation(Math.PI);
                    } else {
                        rotation.setRotation(0);
                    }
                    getImage().drawFromTopLeft(getPosition().x, getPosition().y, rotation);
                }
            }
        }
    }

    /**
     * @brief Change the direction of the ghost
     */
    @Override
    public void changeDirection() {
        setDirection(rand.nextInt(4));
    }
}
