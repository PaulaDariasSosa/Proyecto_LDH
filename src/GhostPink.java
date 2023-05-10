import bagel.Image;
import bagel.util.Point;
import java.util.Random;

public class GhostPink extends Ghost {
    private final static Image GHOST_PINK_IMAGE = new Image("res/ghostPink.png");
    private final static double SPEED = 6;
    private final Random rand = new Random();

    public GhostPink(Point topLeft) {
        super(topLeft, SPEED);
        setImage(GHOST_PINK_IMAGE);
        // randomly generate direction between 4 directions
        setDirection(rand.nextInt(4));
    }

    @Override
    public void changeDirection() {
        setDirection(rand.nextInt(4));
    }
}
