package es.ull.app;

import bagel.Image;
import bagel.util.Point;

/**
 * @brief es.ull.app.Pizza class
 */
public class Pizza extends GameEntity {
    private static final Image PIZZA_IMAGE = new Image("res/pizza.png");
    public static final int POINTS = 30; /// Points for collecting pizza

    /**
     * @brief Constructor for es.ull.app.Pizza
     * @param topLeft
     */
    public Pizza(Point topLeft) {
        super(topLeft);
        setImage(PIZZA_IMAGE);
    }
}
