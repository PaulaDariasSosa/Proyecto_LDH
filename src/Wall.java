import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Wall {
    private final static Image WALL_IMAGE = new Image("res/wall.png");
    private final Rectangle wallRectangle;
    public Wall(Point topLeft) {
        wallRectangle = new Rectangle(topLeft, WALL_IMAGE.getWidth(), WALL_IMAGE.getHeight());
    }

    /**
     * Checks if the potential moved position
     * of the player collides with the wall
     */
    public boolean collidesWith(Player player) {
        return player.getPlayerGo().intersects(this.wallRectangle);
    }

    /**
     * Draws the wall image at the
     * coordinate of the wall Rectangle
     */
    public void draw() {
        WALL_IMAGE.drawFromTopLeft(wallRectangle.left(), wallRectangle.top());
    }
}
