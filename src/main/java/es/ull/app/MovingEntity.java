package es.ull.app;

import bagel.util.Point;

import java.util.ArrayList;

/**
 * @brief Abstract class that represents a moving entity
 */
public abstract class MovingEntity extends GameEntity {
    private static final int RESPAWN_FRAMES = 100;
    private int respawnFrameCount;
    private boolean respawning;

    private final double speed;
    private final double frenzySpeed;
    private final Point startingPosition;
    private Point pointGo;
    private boolean active;


    /**
     * @brief Constructor for the es.ull.app.MovingEntity class
     * @param topLeft
     * @param speed
     * @param frenzySpeed
     */
    public MovingEntity(Point topLeft, double speed, double frenzySpeed) {
        super(topLeft);
        startingPosition = topLeft;
        pointGo = topLeft;
        this.speed = speed;
        this.frenzySpeed = frenzySpeed;
        active = true;
        respawning = false;
    }

    /**
     * @brief Method that moves the entity to the left given the walls and frenzy mode
     * @param walls
     * @param frenzyMode
     */
    public void goLeft(ArrayList<Wall> walls, boolean frenzyMode) {
        if (!respawning) {
            if (frenzyMode) {
                pointGo = new Point(getPosition().x - frenzySpeed, getPosition().y);
            } else {
                pointGo = new Point(getPosition().x - speed, getPosition().y);
            }
            if (canMove(walls)) {
                setPosition(pointGo);
            } else {
                pointGo = getPosition();
            }
        }
    }

    /**
     * @brief Method that moves the entity to the right given the walls and frenzy mode
     * @param walls
     * @param frenzyMode
     */
    public void goRight(ArrayList<Wall> walls, boolean frenzyMode) {
        if (!respawning) {
            if (frenzyMode) {
                pointGo = new Point(getPosition().x + frenzySpeed, getPosition().y);
            } else {
                pointGo = new Point(getPosition().x + speed, getPosition().y);
            }
            if (canMove(walls)) {
                setPosition(pointGo);
            } else {
                pointGo = getPosition();
            }
        }
    }

    /**
     * @brief Method that moves the entity up given the walls and frenzy mode
     * @param walls
     * @param frenzyMode
     */
    public void goUp(ArrayList<Wall> walls, boolean frenzyMode) {
        if (!respawning) {
            if (frenzyMode) {
                pointGo = new Point(getPosition().x, getPosition().y - frenzySpeed);
            } else {
                pointGo = new Point(getPosition().x, getPosition().y - speed);
            }
            if (canMove(walls)) {
                setPosition(pointGo);
            } else {
                pointGo = getPosition();
            }
        }
    }

    /**
     * @brief Method that moves the entity down given the walls and frenzy mode
     * @param walls
     * @param frenzyMode
     */
    public void goDown(ArrayList<Wall> walls, boolean frenzyMode) {
        if (!respawning) {
            if (frenzyMode) {
                pointGo = new Point(getPosition().x, getPosition().y + frenzySpeed);
            } else {
                pointGo = new Point(getPosition().x, getPosition().y + speed);
            }
            if (canMove(walls)) {
                setPosition(pointGo);
            } else {
                pointGo = getPosition();
            }
        }
    }

    /**
     * @brief Method that checks if the entity can move given the walls
     * @param walls
     * @return boolean value indicating if the entity can move
     */
    public abstract boolean canMove(ArrayList<Wall> walls);

    /**
     * @brief Method that resets the entity's position to the starting location
     */
    public void startRespawn() {
        active = false;
        respawning = true;
        respawnFrameCount = RESPAWN_FRAMES;
    }

    /**
     * @brief Method that returns the next point oh the moving entity
     * @return Point object indicating the point the entity is going to move
     */
    public Point getPointGo() {
        return pointGo;
    }

    /**
     * @brief Method that returns if the entity is active
     * @return boolean value indicating if the entity is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @brief Method that sets the entity to active or inactive
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @brief Method that respawns the entity
     */
    public void respawn() {
        respawnFrameCount--;
        if (respawnFrameCount == 0) { ///respawnFrameCount must be 0
            respawning = false;
            active = true;
            resetPosition();
        }
    }

    /**
     * @brief Method that resets the entity's position to the starting location
     */
    public void resetPosition() {
        setPosition(startingPosition);
        pointGo = startingPosition;
    }

    /**
     * @brief Method that returns if the entity is respawning
     * @return boolean value indicating if the entity is respawning
     */
    public boolean isRespawning() {
        return respawning;
    }
}
