import bagel.Image;
import bagel.util.Point;

/**
 * @brief Star class
 */
public class Star extends GameEntity {
    private final static Image STAR_IMAGE = new Image("res/star.png");
    private final static Image STAR_BRIGHT_IMAGE = new Image("res/starBright.png");
    private final static int SWITCH_FRAMES = 6;

    private int switchFrameCount;
    private boolean isBright = false;

    /**
     * @brief Constructor for Star
     * @param topLeft
     */
    public Star(Point topLeft) {
        super(topLeft);
        setImage(STAR_IMAGE);
        switchFrameCount = SWITCH_FRAMES;
    }

    /**
     * @brief Update the star
     */
    public void update() {
        switchFrameCount--;///decrement the switch frame count
        if (switchFrameCount == 0) {
            if (isBright) {
                setImage(STAR_IMAGE);
                isBright = false;
            } else {
                setImage(STAR_BRIGHT_IMAGE);
                isBright = true;
            }
            switchFrameCount = SWITCH_FRAMES;
        }
        getImage().drawFromTopLeft(getPosition().x, getPosition().y);
    }
}
