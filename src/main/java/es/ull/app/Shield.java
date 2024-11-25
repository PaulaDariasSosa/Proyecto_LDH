package es.ull.app;

import bagel.Image;
import bagel.util.Point;

/**
 * @brief es.ull.app.Shield class
 */
public class Shield extends GameEntity{
    private static final Image SHIELD_IMAGE = new Image("res/shield.png");

    /**
     * Constructor for es.ull.app.Shield
     * @param topLeft
     */
    public Shield(Point topLeft) {
        super(topLeft);
        setImage(SHIELD_IMAGE);
    }
}
