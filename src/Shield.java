import bagel.Image;
import bagel.util.Point;

public class Shield extends GameEntity{
    private final static Image SHIELD_IMAGE = new Image("res/shield.png");

    public Shield(Point topLeft) {
        super(topLeft);
        setImage(SHIELD_IMAGE);
    }
}
