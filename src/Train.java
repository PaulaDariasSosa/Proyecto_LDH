import bagel.Image;
import bagel.util.Point;

/**
 * @brief Train class
 */
public class Train extends Wall {
    private final static Image TRAIN_IMAGE = new Image("res/train.png");

    /**
     * @brief Constructor for Train
     * @param topLeft
     */
    public Train(Point topLeft) {
        super(topLeft);
        setImage(TRAIN_IMAGE);
    }
}
