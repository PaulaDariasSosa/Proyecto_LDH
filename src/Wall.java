import bagel.Image;
import bagel.util.Point;

public class Wall extends GameEntity {
    private final static Image WALL_IMAGE = new Image("res/wall.png");
    public Wall(Point topLeft) {
        super(WALL_IMAGE, topLeft);
    }

    /**
     * Checks if the ghost
     * collides with the wall
     */

    public boolean collidesWith(MovingEntity entity) {
        return entity.getRectangle().intersects(this.getRectangle());
    }

}
