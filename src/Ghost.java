import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.ArrayList;

public abstract class Ghost {
    final static Image GHOST_FRENZY_IMAGE = new Image("res/ghostFrenzy.png");
    private final Image image;

    final static int DOWN = 0;
    final static int RIGHT = 1;
    final static int UP = 2;
    final static int LEFT = 3;

    private final static int FRENZY_SCORE = 30;
    private boolean eaten = false;

    final Point ghostStartPoint;
    Point origin;
    Point pointGo;
    Rectangle ghostRectangle;


    public Ghost(Image image, Point topLeft) {
        this.image = image;
        ghostStartPoint = topLeft;
        origin = topLeft;

        this.ghostRectangle = new Rectangle(topLeft, image.getWidth(), image.getHeight());

    }

    /**
     * Checks if the potential moved position
     * of the player collides with the ghost
     */
    public boolean collidesWith(Player player) {
        return player.getPlayerGo().intersects(this.ghostRectangle);
    }

    public Rectangle getGhostRectangle() {
        return ghostRectangle;
    }

    public abstract void move(ArrayList<Wall> walls, boolean frenzyMode);
    public abstract void changeDirection();
    public void resetPosition() {
        origin = ghostStartPoint;
        ghostRectangle = new Rectangle(origin, image.getWidth(), image.getHeight());
    }

    /**
     * Draws the ghost image at the
     * coordinate of the ghost Rectangle
     */
    public abstract void draw(boolean frenzyMode);

    public static int getScore() {
        return FRENZY_SCORE;
    }
    public boolean isEaten() {
        return eaten;
    }
    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

}

