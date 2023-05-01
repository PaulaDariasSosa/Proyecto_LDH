import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.ArrayList;

public abstract class Ghost extends GameEntity {
    final static Image GHOST_FRENZY_IMAGE = new Image("res/ghostFrenzy.png");
    private final double speed;
    private final double frenzySpeed;

    final static int DOWN = 0;
    final static int RIGHT = 1;
    final static int UP = 2;
    final static int LEFT = 3;

    private final static int FRENZY_SCORE = 30;
    private boolean eaten = false;

    final Point ghostStartPoint;
    Point origin;
    Point pointGo;


    public Ghost(Image image, Point topLeft, double speed) {
        super(image, topLeft);
        this.speed = speed;
        frenzySpeed = this.speed - 0.5;
        ghostStartPoint = topLeft;
        origin = topLeft;

    }

    public abstract void move(ArrayList<Wall> walls, boolean frenzyMode);
    public abstract void changeDirection();
    public void resetPosition() {
        origin = ghostStartPoint;
        this.setRectangle(new Rectangle(origin, this.getImage().getWidth(), this.getImage().getHeight()));
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

    public double getSpeed() {
        return speed;
    }

    public double getFrenzySpeed() {
        return frenzySpeed;
    }
}

