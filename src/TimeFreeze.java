import bagel.Image;
import bagel.util.Point;

/**
 * @brief TimeFreeze class that represents a TimeFreeze powerup
 */
public class TimeFreeze extends GameEntity {
    private final static Image TIME_FREEZE_IMAGE = new Image("res/timeFreeze.png");

    /**
     * @brief Constructor for TimeFreeze
     * @param topLeft
     */
    public TimeFreeze(Point topLeft) {
        super(topLeft);
        setImage(TIME_FREEZE_IMAGE);
    }
}
