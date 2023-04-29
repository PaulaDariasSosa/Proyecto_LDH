import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Pellet {
    private final static Image PELLET_IMAGE = new Image("res/pellet.png");
    private final static int SCORE = 20;
    private final Rectangle pelletRectangle;
    private boolean eaten;
    public Pellet(Point topLeft) {
        pelletRectangle = new Rectangle(topLeft, PELLET_IMAGE.getWidth(), PELLET_IMAGE.getHeight());
        eaten = false;
    }
    public void eat() {
        eaten = true;
    }
    public boolean isEaten() {
        return eaten;
    }
    public static int getScore() {
        return SCORE;
    }

    /**
     * Checks if the player after moving
     * collides with the dot
     */
    public boolean collidesWith(Player player) {
        return player.getPlayerGo().intersects(this.pelletRectangle);
    }

    /**
     * If the dot is not eaten, draws the dot image
     * at the coordinate of the dot Rectangle
     */
    public void draw() {
        if (!eaten) {
            PELLET_IMAGE.drawFromTopLeft(pelletRectangle.left(), pelletRectangle.top());
        }
    }
}
