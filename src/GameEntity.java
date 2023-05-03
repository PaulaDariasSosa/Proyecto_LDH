import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;
public abstract class GameEntity {
    private final Image image;
    private Point position;
    private Rectangle rectangle;

    public GameEntity(Image image, Point topLeft) {
        this.image = image;
        position = new Point(topLeft.x, topLeft.y);
        this.rectangle = new Rectangle(topLeft, image.getWidth(), image.getHeight());
    }

    public Image getImage() {
        return image;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * Checks if the player
     * collides with the object
     */
    public boolean collidesWith(Player player) {
        return player.getRectangle() .intersects(this.rectangle);
    }

    /**
     * Draw the image at the coordinate of the rectangle
     */
    public void draw() {
        image.drawFromTopLeft(rectangle.left(), rectangle.top());
    }

}
