import bagel.*;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.ArrayList;

public class Player {
    private final static Image PAC_IMAGE = new Image("res/pac.png");
    private final static Image PAC_OPEN_IMAGE = new Image("res/pacOpen.png");
    private final static Image HEART_IMAGE = new Image("res/heart.png");
    private final static int MAX_LIFE = 3;
    private final static int HEART_GAP = 30;
    private final static double SPEED = 3;
    private final static double FRENZY_SPEED = 4;

    private final Point playerStartPoint;
    private Point origin;
    private final DrawOptions rotation;

    // Rectangle object for potential Go positions
    private Point pointGo;
    private Rectangle playerGo;
    boolean colliding = false;

    private static int lifeCount;
    private int playerScore;

    public Player(Point topLeft) {
        playerStartPoint = topLeft;
        origin = playerStartPoint;
        rotation = new DrawOptions();

        playerGo = new Rectangle(origin, PAC_IMAGE.getWidth(), PAC_IMAGE.getHeight());

        lifeCount = MAX_LIFE;
        playerScore = 0;
    }

    private void checkCollision(ArrayList<Wall> walls) {
        for (Wall wall : walls) {
            if (wall.collidesWith(this)) {
                playerGo = new Rectangle(origin, PAC_IMAGE.getWidth(), PAC_IMAGE.getHeight());
                colliding = true;
                break;
            }
        }
        if (!colliding) {
            origin = pointGo;
        }
        colliding = false;
    }

    /**
     * go methods to set the Go attributes to move in the
     * direction of keyboard inputs, with distance of STEP_SIZE
     */
    public void goLeft(ArrayList<Wall> walls, boolean frenzyMode) {
        if (frenzyMode) {
            pointGo = new Point(origin.x - FRENZY_SPEED, origin.y);
        } else {
            pointGo = new Point(origin.x - SPEED, origin.y);
        }
        playerGo = new Rectangle(pointGo, PAC_IMAGE.getWidth(), PAC_IMAGE.getHeight());
        rotation.setRotation(Math.PI);

        checkCollision(walls);
    }

    public void goRight(ArrayList<Wall> walls, boolean frenzyMode) {
        if (frenzyMode) {
            pointGo = new Point(origin.x + FRENZY_SPEED, origin.y);
        } else {
            pointGo = new Point(origin.x + SPEED, origin.y);
        }
        playerGo = new Rectangle(pointGo, PAC_IMAGE.getWidth(), PAC_IMAGE.getHeight());
        rotation.setRotation(0);

        checkCollision(walls);
    }
    public void goUp(ArrayList<Wall> walls, boolean frenzyMode) {
        if (frenzyMode) {
            pointGo = new Point(origin.x, origin.y - FRENZY_SPEED);
        } else {
            pointGo = new Point(origin.x, origin.y - SPEED);
        }
        playerGo = new Rectangle(pointGo, PAC_IMAGE.getWidth(), PAC_IMAGE.getHeight());
        rotation.setRotation(1.5 * Math.PI);

        checkCollision(walls);
    }
    public void goDown(ArrayList<Wall> walls, boolean frenzyMode) {
        if (frenzyMode) {
            pointGo = new Point(origin.x, origin.y + FRENZY_SPEED);
        } else {
            pointGo = new Point(origin.x, origin.y + SPEED);
        }
        playerGo = new Rectangle(pointGo, PAC_IMAGE.getWidth(), PAC_IMAGE.getHeight());
        rotation.setRotation(0.5 * Math.PI);

        checkCollision(walls);
    }

    public Rectangle getPlayerGo() {
        return playerGo;
    }

    /**
     * The player collides with a ghost.
     * Loses 1 life and resets to starting position.
     * Rotation is not reset.
     */
    public void loseLife() {
        lifeCount--;
        origin = playerStartPoint;
        playerGo = new Rectangle(origin, PAC_IMAGE.getWidth(), PAC_IMAGE.getHeight());
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void increaseScore(int dotScore) {
        playerScore += dotScore;
    }

    public boolean hasLost() {
        return lifeCount == 0;
    }

    /**
     * Draws the player, switching between
     * opening and closing mouths.
     */
    public void draw(int frameCount, int switchFrame) {
        if (frameCount < switchFrame) {
            PAC_IMAGE.drawFromTopLeft(origin.x, origin.y);
        } else {
            PAC_OPEN_IMAGE.drawFromTopLeft(origin.x, origin.y, rotation);
        }
    }

    /**
     * Draws the hearts, the number of which is
     * the number of lives the player has left.
     */
    public void drawLives(Point point) {
        for (int i = 0; i < lifeCount; i++) {
            HEART_IMAGE.drawFromTopLeft(point.x + HEART_GAP * i, point.y);
        }
    }
}
