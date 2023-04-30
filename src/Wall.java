import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Wall extends StationaryObject {
    private final static Image WALL_IMAGE = new Image("res/wall.png");
    public Wall(Point topLeft) {
        super(WALL_IMAGE, topLeft);
    }

}
