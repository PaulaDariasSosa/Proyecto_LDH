package es.ull.app;

import bagel.Image;
import bagel.util.Point;

/**
 * @brief es.ull.app.Tram class
 */
public class Tram extends Wall {
    private static final Image TRAM_IMAGE = new Image("res/tram.png");

    /**
     * @brief Constructor for es.ull.app.Tram
     * @param topLeft
     */
    public Tram(Point topLeft) {
        super(topLeft);
        setImage(TRAM_IMAGE);
    }
}

