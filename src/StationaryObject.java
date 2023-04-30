import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;
public abstract class StationaryObject {
    private final Image image;
    private final Rectangle rectangle;

    public StationaryObject(Image image, Point topLeft) {
        this.image = image;
        this.rectangle = new Rectangle(topLeft, image.getWidth(), image.getHeight());
    }

    /**
     * Checks if the player
     * collides with the object
     */
    public boolean collidesWith(Player player) {
        return player.getPlayerGo().intersects(this.rectangle);
    }

    /**
     * Checks if the ghost
     * collides with the object
     */
    public boolean collidesWith(Ghost ghost) {
        return ghost.getGhostRectangle().intersects(this.rectangle);
    }

    /**
     * Draw the image at the coordinate of the rectangle
     */
    public void draw() {
        image.drawFromTopLeft(rectangle.left(), rectangle.top());
    }

}
