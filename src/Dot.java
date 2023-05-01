import bagel.Image;
import bagel.util.Point;

public class Dot extends GameEntity {
    private final static Image DOT_IMAGE = new Image("res/dot.png");
    private final static int SCORE = 10;

    public Dot(Point topLeft) {
        super(DOT_IMAGE, topLeft);
    }

    public static int getScore() {
        return SCORE;
    }
}
