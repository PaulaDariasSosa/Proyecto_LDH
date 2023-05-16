import bagel.util.Point;
import java.util.ArrayList;

public abstract class MovingEntity extends GameEntity {
    private final static int RESPAWN_FRAMES = 100;
    private int respawnFrameCount;
    private boolean respawning;

    private final double speed;
    private final double frenzySpeed;
    private final Point startingPosition;
    private Point pointGo;
    private boolean active;


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
     * Method that moves the entity to the left given the walls and frenzy mode
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
     * Method that moves the entity to the right given the walls and frenzy mode
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
     * Method that moves the entity up given the walls and frenzy mode
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
     * Method that moves the entity down given the walls and frenzy mode
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
     * Method that checks if the entity can move given the walls
     */
    public abstract boolean canMove(ArrayList<Wall> walls);

    /**
     * Method that resets the entity's position to the starting location
     */
    public void startRespawn() {
        active = false;
        respawning = true;
        respawnFrameCount = RESPAWN_FRAMES;
    }

    public Point getPointGo() {
        return pointGo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void respawn() {
        respawnFrameCount--;
        if (respawnFrameCount == 0) {
            respawning = false;
            active = true;
            resetPosition();
        }
    }

    public void resetPosition() {
        setPosition(startingPosition);
        pointGo = startingPosition;
    }

    public boolean isRespawning() {
        return respawning;
    }
}
