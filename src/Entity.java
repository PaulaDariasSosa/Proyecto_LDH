import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;
public abstract class Entity {
    private final Image image;
    private Rectangle rectangle;

    public Entity(Image image, Point topLeft) {
        this.image = image;
        this.rectangle = new Rectangle(topLeft, image.getWidth(), image.getHeight());
    }

    public Image getImage() {
        return image;
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
        return player.getPlayerGo().intersects(this.rectangle);
    }

    /**
     * Draw the image at the coordinate of the rectangle
     */
    public void draw() {
        image.drawFromTopLeft(rectangle.left(), rectangle.top());
    }

}
