import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Cherry {
    private final static Image CHERRY_IMAGE = new Image("res/cherry.png");
    private final static int SCORE = 20;
    private final Rectangle cherryRectangle;
    public Cherry(Point topLeft) {
        cherryRectangle = new Rectangle(topLeft, CHERRY_IMAGE.getWidth(), CHERRY_IMAGE.getHeight());
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
        CHERRY_IMAGE.drawFromTopLeft(cherryRectangle.left(), cherryRectangle.top());
    }
}
