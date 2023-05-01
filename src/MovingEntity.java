import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;
import java.util.ArrayList;

public abstract class MovingEntity extends GameEntity {
    final double speed;
    final double frenzySpeed;
    Point startPoint;
    Point pointGo;
    public MovingEntity(Image image, Point topLeft, double speed, double frenzySpeed) {
        super(image, topLeft);
        this.startPoint = topLeft;
        pointGo = new Point(topLeft.x, topLeft.y);
        this.speed = speed;
        this.frenzySpeed = frenzySpeed;
    }
    public void goLeft(ArrayList<Wall> walls, boolean frenzyMode) {
        if (frenzyMode) {
            pointGo = new Point(origin.x - frenzySpeed, origin.y);
        } else {
            pointGo = new Point(origin.x - speed, origin.y);
        }
        this.setRectangle(new Rectangle(pointGo, getImage().getWidth(), getImage().getHeight()));

        checkCollision(walls);
    }

    public void goRight(ArrayList<Wall> walls, boolean frenzyMode) {
        if (frenzyMode) {
            pointGo = new Point(origin.x + frenzySpeed, origin.y);
        } else {
            pointGo = new Point(origin.x + speed, origin.y);
        }
        this.setRectangle(new Rectangle(pointGo, getImage().getWidth(), getImage().getHeight()));

        checkCollision(walls);
    }

    public void goUp(ArrayList<Wall> walls, boolean frenzyMode) {
        if (frenzyMode) {
            pointGo = new Point(origin.x, origin.y - frenzySpeed);
        } else {
            pointGo = new Point(origin.x, origin.y - speed);
        }
        this.setRectangle(new Rectangle(pointGo, getImage().getWidth(), getImage().getHeight()));

        checkCollision(walls);
    }

    public void goDown(ArrayList<Wall> walls, boolean frenzyMode) {
        if (frenzyMode) {
            pointGo = new Point(origin.x, origin.y + frenzySpeed);
        } else {
            pointGo = new Point(origin.x, origin.y + speed);
        }
        this.setRectangle(new Rectangle(pointGo, getImage().getWidth(), getImage().getHeight()));

        checkCollision(walls);
    }

    private void checkCollision(ArrayList<Wall> walls) {
        boolean colliding = false;
        for (Wall wall : walls) {
            if (wall.collidesWith(this)) {
                this.setRectangle(new Rectangle(origin, getImage().getWidth(), getImage().getHeight()));
                colliding = true;
                break;
            }
        }
        if (!colliding) {
            origin = pointGo;
        }
    }
}
