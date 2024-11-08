import bagel.Image;
import bagel.util.Point;

/**
 * @brief A class representing a bus in the game
 */
public class Bus extends Wall {
    /**
     * @brief The image of the bus
     */
    private final static Image BUS_IMAGE = new Image("res/bus.png");

    /**
     * @brief Constructor for the Bus class
     * @param topLeft
     */
    public Bus(Point topLeft) {
        super(topLeft);
        setImage(BUS_IMAGE);
    }
}
