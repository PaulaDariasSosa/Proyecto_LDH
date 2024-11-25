package es.ull.app;

import bagel.Image;
import bagel.util.Point;

/**
 * @brief The bomb class
 */
public class Bomb extends GameEntity {
    /**
     * @brief The bomb's image
     * The image of the bomb
     */
    private static final Image BOMB_IMAGE = new Image("res/bomb.png");

    /**
     * @brief Constructor
     * @param topLeft
     */
    public Bomb(Point topLeft) {
        super(topLeft);
        setImage(BOMB_IMAGE);
    }
}
