import bagel.Image;
import bagel.util.Point;
import java.util.Random;

/**
 * @brief GhostPink class, a subclass of Ghost
 */
public class GhostPink extends Ghost {
    private final static Image GHOST_PINK_IMAGE = new Image("res/ghostPink.png");
    private final static double SPEED = 5;
    private final Random rand = new Random();

    /**
     * Constructor for GhostPink
     * @param topLeft the top left point of the ghost
     */
    public GhostPink(Point topLeft) {
        super(topLeft, SPEED);
        setImage(GHOST_PINK_IMAGE);
        // randomly generate direction between 4 directions
        setDirection(rand.nextInt(4));
    }

    /**
     * @brief change the direction of the ghost randomly
     */
    @Override
    public void changeDirection() {
        setDirection(rand.nextInt(4));
    }
}
