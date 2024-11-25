package es.ull.app;

import bagel.Image;
import bagel.util.Point;

/**
 * @brief es.ull.app.TimeFreeze class that represents a es.ull.app.TimeFreeze powerup
 */
public class TimeFreeze extends GameEntity {
    private static final Image TIME_FREEZE_IMAGE = new Image("res/timeFreeze.png");

    /**
     * @brief Constructor for es.ull.app.TimeFreeze
     * @param topLeft
     */
    public TimeFreeze(Point topLeft) {
        super(topLeft);
        setImage(TIME_FREEZE_IMAGE);
    }
}
