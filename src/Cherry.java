import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Cherry {
    private final static Image CHERRY_IMAGE = new Image("res/cherry.png");
    private final static int SCORE = 20;
    private final Rectangle cherryRectangle;
    private boolean eaten;
    public Cherry(Point topLeft) {
        cherryRectangle = new Rectangle(topLeft, CHERRY_IMAGE.getWidth(), CHERRY_IMAGE.getHeight());
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
        return player.getPlayerGo().intersects(this.cherryRectangle);
    }

    /**
     * If the dot is not eaten, draws the dot image
     * at the coordinate of the dot Rectangle
     */
    public void draw() {
        if (!eaten) {
            CHERRY_IMAGE.drawFromTopLeft(cherryRectangle.left(), cherryRectangle.top());
        }
    }
}
