package es.ull.app;

import bagel.Image;
import bagel.util.Point;

/**
 * @brief es.ull.app.Train class
 */
public class Train extends Wall {
    private static final Image TRAIN_IMAGE = new Image("res/train.png");

    /**
     * @brief Constructor for es.ull.app.Train
     * @param topLeft
     */
    public Train(Point topLeft) {
        super(topLeft);
        setImage(TRAIN_IMAGE);
    }
}
