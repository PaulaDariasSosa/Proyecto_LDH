import bagel.Image;
import bagel.util.Point;
import java.util.Random;

public class GhostGreen extends Ghost {
    private final static Image GHOST_GREEN_IMAGE = new Image("res/ghostGreen.png");
    private final static double SPEED = 4;

    public GhostGreen(Point topLeft) {
        super(topLeft, SPEED);
        setImage(GHOST_GREEN_IMAGE);
        // randomly generation direction between DOWN and RIGHT
        Random rand = new Random();
        setDirection(rand.nextInt(2));
    }

    public void changeDirection() {
        if (getDirection() == RIGHT) {
            setDirection(LEFT);
        } else if (getDirection() == LEFT) {
            setDirection(RIGHT);
        } else if (getDirection() == DOWN) {
            setDirection(UP);
        } else {
            setDirection(DOWN);
        }
    }
}
