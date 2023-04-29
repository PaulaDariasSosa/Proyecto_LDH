import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Pellet {
    private final static Image PELLET_IMAGE = new Image("res/pellet.png");
    private final Rectangle pelletRectangle;
    public Pellet(Point topLeft) {
        pelletRectangle = new Rectangle(topLeft, PELLET_IMAGE.getWidth(), PELLET_IMAGE.getHeight());
    }

    /**
     * Checks if the potential moved position
     * of the player collides with the pellet
     */
    public boolean collidesWith(Player player) {
        return player.getPlayerGo().intersects(this.pelletRectangle);
    }

    /**
     * Draws the pellet image at the
     * coordinate of the pellet Rectangle
     */
    public void draw() {
        PELLET_IMAGE.drawFromTopLeft(pelletRectangle.left(), pelletRectangle.top());
    }
}
