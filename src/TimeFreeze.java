import bagel.Image;
import bagel.util.Point;

public class TimeFreeze extends GameEntity {
    private final static Image TIME_FREEZE_IMAGE = new Image("res/timeFreeze.png");
    public TimeFreeze(Point topLeft) {
        super(topLeft);
        setImage(TIME_FREEZE_IMAGE);
    }
}
