import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public abstract class Ghost {

    final static int LEFT = 0;
    final static int RIGHT = 1;
    final static int UP = 2;
    final static int DOWN = 3;

    private final static Image GHOST_IMAGE = new Image("res/ghostRed.png");
    Point origin;
    Rectangle ghostRectangle;


    public Ghost(Point topLeft) {
        origin = topLeft;
        ghostRectangle = new Rectangle(topLeft, GHOST_IMAGE.getWidth(), GHOST_IMAGE.getHeight());
    }

    /**
     * Checks if the potential moved position
     * of the player collides with the ghost
     */
    public boolean collidesWith(Player player) {
        return player.getPlayerGo().intersects(this.ghostRectangle);
    }

    public boolean collidesWith(Wall wall) {
        return wall.getWallRectangle().intersects(this.ghostRectangle);
    }

    public abstract void move();
    public abstract void changeDirection();

    /**
     * Draws the ghost image at the
     * coordinate of the ghost Rectangle
     */
    public abstract void draw();
}

