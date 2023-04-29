import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Ghost {
    private final static Image GHOST_IMAGE = new Image("res/ghostRed.png");
    final Rectangle ghostRectangle;
    public Ghost(Point topLeft) {
        ghostRectangle = new Rectangle(topLeft, GHOST_IMAGE.getWidth(), GHOST_IMAGE.getHeight());
    }

    /**
     * Checks if the potential moved position
     * of the player collides with the ghost
     */
    public boolean collidesWith(Player player) {
        return player.getPlayerGo().intersects(this.ghostRectangle);
    }

    /**
     * Draws the ghost image at the
     * coordinate of the ghost Rectangle
     */
    public void draw() {
        GHOST_IMAGE.drawFromTopLeft(ghostRectangle.left(), ghostRectangle.top());
    }
}

