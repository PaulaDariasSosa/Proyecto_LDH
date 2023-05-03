import bagel.Image;
import bagel.util.Point;

public class Pellet extends GameEntity {
    private final static Image PELLET_IMAGE = new Image("res/pellet.png");
    public Pellet(Point topLeft) {
        super(topLeft);
        setImage(PELLET_IMAGE);
    }
}
