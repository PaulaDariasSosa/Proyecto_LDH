import bagel.Image;
import bagel.util.Point;

import java.util.Random;

public class Dot extends GameEntity {
    private final static Image DOT_RED_IMAGE = new Image("res/dotRed.png");
    private final static Image DOT_BLUE_IMAGE = new Image("res/dotBlue.png");
    private final static Image DOT_GREEN_IMAGE = new Image("res/dotGreen.png");
    private final static Image DOT_PINK_IMAGE = new Image("res/dotPink.png");

    public final static int POINTS = 10;

    public Dot(Point topLeft) {
        super(topLeft);
        Image[] images = {DOT_RED_IMAGE, DOT_BLUE_IMAGE, DOT_GREEN_IMAGE, DOT_PINK_IMAGE};
        Random rand = new Random();
        setImage(images[rand.nextInt(4)]);
    }
}
