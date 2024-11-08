import bagel.Image;
import bagel.util.Point;

import java.util.Random;

/**
 * @brief A class representing a dot in the game
 */
public class Dot extends GameEntity {
    private final static Image DOT_RED_IMAGE = new Image("res/dotRed.png");
    private final static Image DOT_BLUE_IMAGE = new Image("res/dotBlue.png");
    private final static Image DOT_GREEN_IMAGE = new Image("res/dotGreen.png");
    private final static Image DOT_PINK_IMAGE = new Image("res/dotPink.png");
    private final static Image DOT_ORANGE_IMAGE = new Image("res/dotOrange.png");
    private final static Image DOT_PURPLE_IMAGE = new Image("res/dotPurple.png");
    private final static Image DOT_WHITE_IMAGE = new Image("res/dotWhite.png");
    private final static Image DOT_YELLOW_IMAGE = new Image("res/dotYellow.png");

    /**
     * @brief The number of points the player gets when they collect a dot
     */
    public final static int POINTS = 10;

    /**
     * @brief Constructor for the Dot class
     * @param topLeft The top left corner of the dot
     */
    public Dot(Point topLeft) {
        super(topLeft);
        Image[] images = {DOT_RED_IMAGE, DOT_BLUE_IMAGE, DOT_GREEN_IMAGE, DOT_PINK_IMAGE, DOT_ORANGE_IMAGE,
                DOT_PURPLE_IMAGE, DOT_WHITE_IMAGE, DOT_YELLOW_IMAGE};
        Random rand = new Random();
        setImage(images[rand.nextInt(images.length)]);
    }
}
