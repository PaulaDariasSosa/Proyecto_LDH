import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;
import java.util.ArrayList;

public abstract class MovingEntity extends GameEntity {
    private final double speed;
    private final double frenzySpeed;
    Point startPoint;
    Point pointGo;
    public MovingEntity(Image image, Point topLeft, double speed, double frenzySpeed) {
        super(image, topLeft);
        this.startPoint = topLeft;
        pointGo = new Point(topLeft.x, topLeft.y);
        this.speed = speed;
        this.frenzySpeed = frenzySpeed;
    }

    public double getSpeed() {
        return speed;
    }

    public double getFrenzySpeed() {
        return frenzySpeed;
    }

    public void goLeft(ArrayList<Wall> walls, boolean frenzyMode) {
        if (frenzyMode) {
            pointGo = new Point(getPosition().x - frenzySpeed, getPosition().y);
        } else {
            pointGo = new Point(getPosition().x - speed, getPosition().y);
        }
        this.setRectangle(new Rectangle(pointGo, getImage().getWidth(), getImage().getHeight()));

        checkCollision(walls);
    }

    public void goRight(ArrayList<Wall> walls, boolean frenzyMode) {
        if (frenzyMode) {
            pointGo = new Point(getPosition().x + frenzySpeed, getPosition().y);
        } else {
            pointGo = new Point(getPosition().x + speed, getPosition().y);
        }
        this.setRectangle(new Rectangle(pointGo, getImage().getWidth(), getImage().getHeight()));

        checkCollision(walls);
    }

    public void goUp(ArrayList<Wall> walls, boolean frenzyMode) {
        if (frenzyMode) {
            pointGo = new Point(getPosition().x, getPosition().y - frenzySpeed);
        } else {
            pointGo = new Point(getPosition().x, getPosition().y - speed);
        }
        this.setRectangle(new Rectangle(pointGo, getImage().getWidth(), getImage().getHeight()));

        checkCollision(walls);
    }

    public void goDown(ArrayList<Wall> walls, boolean frenzyMode) {
        if (frenzyMode) {
            pointGo = new Point(getPosition().x, getPosition().y + frenzySpeed);
        } else {
            pointGo = new Point(getPosition().x, getPosition().y + speed);
        }
        this.setRectangle(new Rectangle(pointGo, getImage().getWidth(), getImage().getHeight()));

        checkCollision(walls);
    }

    private void checkCollision(ArrayList<Wall> walls) {
        boolean colliding = false;
        for (Wall wall : walls) {
            if (wall.collidesWith(this)) {
                setRectangle(new Rectangle(getPosition(), getImage().getWidth(), getImage().getHeight()));
                changeDirection();
                colliding = true;
                break;
            }
        }
        if (!colliding) {
            setPosition(pointGo);
        }
    }

    public abstract void changeDirection();
}
