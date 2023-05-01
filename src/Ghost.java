import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.ArrayList;

public abstract class Ghost extends MovingEntity {
    final static Image GHOST_FRENZY_IMAGE = new Image("res/ghostFrenzy.png");
    final static int DOWN = 0;
    final static int RIGHT = 1;
    final static int UP = 2;
    final static int LEFT = 3;

    private final static double FRENZY_SPEED_DECREASE = 0.5;
    private final static int FRENZY_SCORE = 30;
    private boolean eaten = false;

    public Ghost(Image image, Point topLeft, double speed) {
        super(image, topLeft, speed, speed - FRENZY_SPEED_DECREASE);
        startPoint = topLeft;
        origin = topLeft;

    }
    public abstract void changeDirection();

    public abstract void move(ArrayList<Wall> walls, boolean frenzyMode);
    public void resetPosition() {
        origin = startPoint;
        this.setRectangle(new Rectangle(origin, this.getImage().getWidth(), this.getImage().getHeight()));
    }


    /**
     * Draws the ghost image at the
     * coordinate of the ghost Rectangle
     */
    public void draw(boolean frenzyMode) {
        if (frenzyMode) {
            GHOST_FRENZY_IMAGE.drawFromTopLeft(getRectangle().left(), getRectangle().top());
        } else {
            draw();
        }
    }

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

