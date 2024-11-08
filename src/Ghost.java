import bagel.Image;
import bagel.util.Point;

import java.util.ArrayList;

/**
 * @brief Abstract class that represents a ghost
 */
public abstract class Ghost extends MovingEntity {
    private final static Image GHOST_FRENZY_IMAGE = new Image("res/ghostFrenzy.png");
    private final static Image EYES_LEFT_IMAGE = new Image("res/eyesLeft.png");
    private final static Image EYES_RIGHT_IMAGE = new Image("res/eyesRight.png");
    private final static Image EYES_UP_IMAGE = new Image("res/eyesUp.png");
    private final static Image EYES_DOWN_IMAGE = new Image("res/eyesDown.png");
    public final static int FRENZY_SCORE = 40;
    public final static int DOWN = 0;
    public final static int RIGHT = 1;
    public final static int UP = 2;
    public final static int LEFT = 3;
    private int direction;

    /**
     * @brief The speed decrease when the ghost is in frenzy mode
     */
    private final static double FRENZY_SPEED_DECREASE = 0.5;

    /**
     * @brief Constructor for the Ghost class
     * @param topLeft The top left corner of the ghost
     * @param speed The speed of the ghost
     */
    public Ghost(Point topLeft, double speed) {
        super(topLeft, speed, speed - FRENZY_SPEED_DECREASE);
        setPosition(topLeft);
    }

    /**
     * @brief Method that performs state update
     * @param frenzyMode
     */
    public void update(boolean frenzyMode) {
        if (isRespawning()) {
            respawn();
        }
        else {
            if (isActive()) {
                if (frenzyMode) {
                    GHOST_FRENZY_IMAGE.drawFromTopLeft(getPosition().x, getPosition().y);
                } else {
                    update();
                    if (direction == LEFT) {
                        EYES_LEFT_IMAGE.drawFromTopLeft(getPosition().x, getPosition().y);
                    } else if (direction == RIGHT) {
                        EYES_RIGHT_IMAGE.drawFromTopLeft(getPosition().x, getPosition().y);
                    } else if (direction == UP) {
                        EYES_UP_IMAGE.drawFromTopLeft(getPosition().x, getPosition().y);
                    } else {
                        EYES_DOWN_IMAGE.drawFromTopLeft(getPosition().x, getPosition().y);
                    }
                }
            }
        }
    }

    /**
     * @brief Method that moves the ghost given the list of walls and frenzy mode
     * @param frenzyMode
     * @param timeFrozen
     */
    public void update(boolean frenzyMode, boolean timeFrozen) {
        if (isRespawning()) {
            respawn();
        }
        else {
            if (isActive()) {
                if (frenzyMode) {
                    GHOST_FRENZY_IMAGE.drawFromTopLeft(getPosition().x, getPosition().y);
                }
            }
        }
    }

    /**
     * @brief Method that moves the ghost given the list of walls and frenzy mode
     * @param walls
     * @param frenzyMode
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

    /**
     * @brief Method that returns true if the ghost can move, false otherwise
     * @param walls
     * @return True if the ghost can move, false otherwise
     */
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
     * @brief Method that changes the direction of the ghost after colliding with a wall
     */
    public abstract void changeDirection();

    /**
     * @brief Method that gets the direction of the ghost
     * @return
     */
    public int getDirection() {
        return direction;
    }

    /**
     * @brief Method that sets the direction of the ghost
     * @param direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

}

