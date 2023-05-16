import bagel.Image;
import bagel.util.Point;

public class Bus extends Wall {
    private final static Image BUS_IMAGE = new Image("res/bus.png");

    public Bus(Point topLeft) {
        super(topLeft);
        setImage(BUS_IMAGE);
    }
}
