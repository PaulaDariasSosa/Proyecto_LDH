package es.ull.app;

import bagel.util.Point;

/**
 * @brief Abstract class for es.ull.app.Wall
 */
public abstract class Wall extends GameEntity {

    /**
     * @brief Constructor for es.ull.app.Wall
     * @param topLeft
     */
    public Wall(Point topLeft) {
        super(topLeft);
    }

}
