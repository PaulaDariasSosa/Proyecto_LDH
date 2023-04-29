import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.ArrayList;

public abstract class Ghost {
    private final static Image GHOST_IMAGE = new Image("res/ghostRed.png");
    final Point ghostStartPoint;
    Point origin;
    Rectangle ghostRectangle;


    public Ghost(Point topLeft) {
        origin = topLeft;
        ghostStartPoint = topLeft;
        ghostRectangle = new Rectangle(origin, GHOST_IMAGE.getWidth(), GHOST_IMAGE.getHeight());
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

    public abstract void move(ArrayList<Wall> walls);
    public abstract void changeDirection();
    public void resetPosition() {
        origin = ghostStartPoint;
        ghostRectangle = new Rectangle(origin, GHOST_IMAGE.getWidth(), GHOST_IMAGE.getHeight());
    }

    /**
     * Draws the ghost image at the
     * coordinate of the ghost Rectangle
     */
    public abstract void draw();
}

