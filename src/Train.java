import bagel.Image;
import bagel.util.Point;

public class Train extends Wall {
    private final static Image TRAIN_IMAGE = new Image("res/train.png");

    public Train(Point topLeft) {
        super(topLeft);
        setImage(TRAIN_IMAGE);
    }
}
