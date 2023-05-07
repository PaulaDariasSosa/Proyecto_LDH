import bagel.util.Point;
import java.util.ArrayList;

public abstract class MovingEntity extends GameEntity {
    private final double speed;
    private final double frenzySpeed;
    private final Point startPoint;
    private Point pointGo;

    public MovingEntity(Point topLeft, double speed, double frenzySpeed) {
        super(topLeft);
        startPoint = topLeft;
        pointGo = topLeft;
        this.speed = speed;
        this.frenzySpeed = frenzySpeed;
    }

    public Point getPointGo() {
        return pointGo;
    }

    public void goLeft(ArrayList<Wall> walls, boolean frenzyMode) {
        if (frenzyMode) {
            pointGo = new Point(getPosition().x - frenzySpeed, getPosition().y);
        } else {
            pointGo = new Point(getPosition().x - speed, getPosition().y);
        }
        if (canMove(walls)) {
            setPosition(pointGo);
        }
        else {
            pointGo = getPosition();
        }
    }

    public void goRight(ArrayList<Wall> walls, boolean frenzyMode) {
        if (frenzyMode) {
            pointGo = new Point(getPosition().x + frenzySpeed, getPosition().y);
        } else {
            pointGo = new Point(getPosition().x + speed, getPosition().y);
        }
        if (canMove(walls)) {
            setPosition(pointGo);
        }
        else {
            pointGo = getPosition();
        }
    }

    public void goUp(ArrayList<Wall> walls, boolean frenzyMode) {
        if (frenzyMode) {
            pointGo = new Point(getPosition().x, getPosition().y - frenzySpeed);
        } else {
            pointGo = new Point(getPosition().x, getPosition().y - speed);
        }
        if (canMove(walls)) {
            setPosition(pointGo);
        }
        else {
            pointGo = getPosition();
        }
    }

    public void goDown(ArrayList<Wall> walls, boolean frenzyMode) {
        if (frenzyMode) {
            pointGo = new Point(getPosition().x, getPosition().y + frenzySpeed);
        } else {
            pointGo = new Point(getPosition().x, getPosition().y + speed);
        }
        if (canMove(walls)) {
            setPosition(pointGo);
        }
        else {
            pointGo = getPosition();
        }
    }

    public abstract boolean canMove(ArrayList<Wall> walls);

    public void resetPosition() {
        setPosition(startPoint);
        pointGo = startPoint;
    }
}
