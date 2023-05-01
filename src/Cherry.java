import bagel.Image;
import bagel.util.Point;

public class Cherry extends GameEntity {
    private final static Image CHERRY_IMAGE = new Image("res/cherry.png");
    private final static int SCORE = 20;
    public Cherry(Point topLeft) {
        super(CHERRY_IMAGE, topLeft);
    }

    public static int getScore() {
        return SCORE;
    }
}
