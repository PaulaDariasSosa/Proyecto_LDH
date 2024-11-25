package es.ull.app;

import bagel.Image;
import bagel.util.Point;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @brief A class representing a dot in the game
 */
public class Dot extends GameEntity {
    private static final Image DOT_RED_IMAGE = new Image("res/dotRed.png");
    private static final Image DOT_BLUE_IMAGE = new Image("res/dotBlue.png");
    private static final Image DOT_GREEN_IMAGE = new Image("res/dotGreen.png");
    private static final Image DOT_PINK_IMAGE = new Image("res/dotPink.png");
    private static final Image DOT_ORANGE_IMAGE = new Image("res/dotOrange.png");
    private static final Image DOT_PURPLE_IMAGE = new Image("res/dotPurple.png");
    private static final Image DOT_WHITE_IMAGE = new Image("res/dotWhite.png");
    private static final Image DOT_YELLOW_IMAGE = new Image("res/dotYellow.png");

    /**
     * @brief The number of points the player gets when they collect a dot
     */
    public static final int POINTS = 10;

    /**
     * @brief Constructor for the es.ull.app.Dot class
     * @param topLeft The top left corner of the dot
     */
    public Dot(Point topLeft) {
        super(topLeft);
        Image[] images = {DOT_RED_IMAGE, DOT_BLUE_IMAGE, DOT_GREEN_IMAGE, DOT_PINK_IMAGE, DOT_ORANGE_IMAGE,
                DOT_PURPLE_IMAGE, DOT_WHITE_IMAGE, DOT_YELLOW_IMAGE};
        Random rand = new SecureRandom();
        setImage(images[rand.nextInt(images.length)]);
    }
}
