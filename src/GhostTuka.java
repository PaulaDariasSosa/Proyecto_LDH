import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Point;
import java.util.Random;

public class GhostTuka extends Ghost {
    private final static Image GHOST_TUKA_IMAGE = new Image("res/ghostTuka.png");
    private final static Image GHOST_TUKA_OPEN_IMAGE = new Image("res/ghostTukaOpen.png");

    private final static int SWITCH_FRAMES = 7;
    private final DrawOptions rotation;
    private int switchFrameCount;
    private boolean isOpen = false;

    private final static double SPEED = 7;
    private final Random rand = new Random();

    public GhostTuka(Point topLeft) {
        super(topLeft, SPEED);
        setImage(GHOST_TUKA_IMAGE);
        switchFrameCount = SWITCH_FRAMES;
        rotation = new DrawOptions();
        // randomly generate direction between 4 directions
        setDirection(rand.nextInt(4));
    }

    public void update(boolean frenzyMode) {
        if (isRespawning()) {
            respawn();
        } else {
            if (isActive()) {
                if (frenzyMode) {
                    super.update(true);
                } else {
                    // switching the image being rendered
                    switchFrameCount--;
                    if (switchFrameCount == 0) {
                        if (isOpen) {
                            setImage(GHOST_TUKA_OPEN_IMAGE);
                            isOpen = false;
                        } else {
                            setImage(GHOST_TUKA_IMAGE);
                            isOpen = true;
                        }
                        switchFrameCount = SWITCH_FRAMES;
                    }
                    if (getDirection() == LEFT) {
                        rotation.setRotation(0.5 * Math.PI);
                    } else if (getDirection() == RIGHT) {
                        rotation.setRotation(1.5 * Math.PI);
                    } else if (getDirection() == UP) {
                        rotation.setRotation(Math.PI);
                    } else {
                        rotation.setRotation(0);
                    }
                    getImage().drawFromTopLeft(getPosition().x, getPosition().y, rotation);
                }
            }
        }
    }

    @Override
    public void changeDirection() {
        setDirection(rand.nextInt(4));
    }
}
