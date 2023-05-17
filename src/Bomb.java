import bagel.Image;
import bagel.util.Point;

public class Bomb extends GameEntity {
    private final static Image BOMB_IMAGE = new Image("res/bomb.png");
    public Bomb(Point topLeft) {
        super(topLeft);
        setImage(BOMB_IMAGE);
    }
}
