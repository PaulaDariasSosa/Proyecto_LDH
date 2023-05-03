import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Wall extends GameEntity {
    private final static Image WALL_IMAGE = new Image("res/wall.png");
    public Wall(Point topLeft) {
        super(topLeft);
        setImage(WALL_IMAGE);
    }

    /**
     * Checks if the ghost
     * collides with the wall
     */

    public boolean collidesWith(MovingEntity entity) {
        Rectangle rectangleGo = new Rectangle(entity.pointGo, entity.getImage().getWidth(), entity.getImage().getHeight());
        return rectangleGo.intersects(this.getRectangle());
    }

}
