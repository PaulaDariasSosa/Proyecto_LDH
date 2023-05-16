import bagel.Image;
import bagel.util.Point;

public class Tram extends Wall {
    private final static Image TRAM_IMAGE = new Image("res/tram.png");

    public Tram(Point topLeft) {
        super(topLeft);
        setImage(TRAM_IMAGE);
    }
}

