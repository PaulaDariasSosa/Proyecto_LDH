import bagel.Image;
import bagel.util.Point;

import java.util.ArrayList;

public abstract class Ghost extends MovingEntity {
    final static Image GHOST_FRENZY_IMAGE = new Image("res/ghostFrenzy.png");
    public final static int FRENZY_SCORE = 30;
    public final static int DOWN = 0;
    public final static int RIGHT = 1;
    public final static int UP = 2;
    public final static int LEFT = 3;
    private int direction;

    private final static double FRENZY_SPEED_DECREASE = 0.5;
    private boolean isActive;

    public Ghost(Point topLeft, double speed) {
        super(topLeft, speed, speed - FRENZY_SPEED_DECREASE);
        setPosition(topLeft);
        isActive = true;
    }

    /**
     * Method that performs state update
     */
    public void update(boolean frenzyMode) {
        if (frenzyMode) {
            GHOST_FRENZY_IMAGE.drawFromTopLeft(getPosition().x, getPosition().y);
        } else {
            update();
        }
    }

    /**
     * Method that moves the ghost given the list of walls and frenzy mode
     */
    public void move(ArrayList<Wall> walls, boolean frenzyMode) {
        if (direction == LEFT) {
            goLeft(walls, frenzyMode);
        } else if (direction == RIGHT) {
            goRight(walls, frenzyMode);
        } else if (direction == UP) {
            goUp(walls, frenzyMode);
        } else {
            goDown(walls, frenzyMode);
        }
    }

    @Override
    public boolean canMove(ArrayList<Wall> walls) {
        boolean canMove = true;
        for (Wall wall : walls) {
            if (wall.collidesWith(this)) {
                canMove = false;
                changeDirection();
                break;
            }
        }
        return canMove;
    }

    /**
     * Method that changes the direction of the ghost after colliding with a wall
     */
    public abstract void changeDirection();

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

}

