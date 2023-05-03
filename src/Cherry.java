import bagel.Image;
import bagel.util.Point;

public class Cherry extends GameEntity {
    private final static Image CHERRY_IMAGE = new Image("res/cherry.png");
    public final static int POINTS = 20;
    public Cherry(Point topLeft) {
        super(topLeft);
        setImage(CHERRY_IMAGE);
    }
}
