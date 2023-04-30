import bagel.Image;
import bagel.util.Point;

public class Wall extends Entity {
    private final static Image WALL_IMAGE = new Image("res/wall.png");
    public Wall(Point topLeft) {
        super(WALL_IMAGE, topLeft);
    }

    /**
     * Checks if the ghost
     * collides with the wall
     */
    public boolean collidesWith(Ghost ghost) {
        return ghost.getRectangle().intersects(this.getRectangle());
    }

}
