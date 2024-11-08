import bagel.Image;
import bagel.util.Point;

/**
 * @brief Shield class
 */
public class Shield extends GameEntity{
    private final static Image SHIELD_IMAGE = new Image("res/shield.png");

    /**
     * Constructor for Shield
     * @param topLeft
     */
    public Shield(Point topLeft) {
        super(topLeft);
        setImage(SHIELD_IMAGE);
    }
}
