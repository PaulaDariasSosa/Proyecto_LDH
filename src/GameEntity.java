import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public abstract class GameEntity {
    private Image image;
    private Point position;

    public GameEntity(Point topLeft) {
        position = new Point(topLeft.x, topLeft.y);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Rectangle getRectangle() {
        return new Rectangle(position, image.getWidth(), image.getHeight());
    }


    /**
     * Checks if the object
     * collides with the Moving entity
     */
    public boolean collidesWith(MovingEntity entity) {
        Rectangle rectangleGo = new Rectangle(entity.getPointGo(), entity.getImage().getWidth(), entity.getImage().getHeight());
        return rectangleGo.intersects(this.getRectangle());
    }

    /**
     * Method that performs state update
     */
    public void update() {
        image.drawFromTopLeft(position.x, position.y);
    }

}
