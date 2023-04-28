import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Dot {
    private final static Image DOT_IMAGE = new Image("res/dot.png");
    private final static int SCORE = 10;
    private final Rectangle dotRectangle;
    private boolean eaten;
    public Dot(Point topLeft) {
        dotRectangle = new Rectangle(topLeft, DOT_IMAGE.getWidth(), DOT_IMAGE.getHeight());
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
        return player.getPlayerGo().intersects(this.dotRectangle);
    }

    /**
     * If the dot is not eaten, draws the dot image
     * at the coordinate of the dot Rectangle
     */
    public void draw() {
        if (!eaten) {
            DOT_IMAGE.drawFromTopLeft(dotRectangle.left(), dotRectangle.top());
        }
    }
}
