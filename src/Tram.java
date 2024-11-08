import bagel.Image;
import bagel.util.Point;

/**
 * @brief Tram class
 */
public class Tram extends Wall {
    private final static Image TRAM_IMAGE = new Image("res/tram.png");

    /**
     * @brief Constructor for Tram
     * @param topLeft
     */
    public Tram(Point topLeft) {
        super(topLeft);
        setImage(TRAM_IMAGE);
    }
}

