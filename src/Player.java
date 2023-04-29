import bagel.*;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Player {
    private final static Image PAC_IMAGE = new Image("res/pac.png");
    private final static Image PAC_OPEN_IMAGE = new Image("res/pacOpen.png");
    private final static Image HEART_IMAGE = new Image("res/heart.png");
    private final static int MAX_LIFE = 3;
    private final static int HEART_GAP = 30;
    private static double speed = 3;

    private final Point playerStartPoint;
    private Point origin;
    private DrawOptions rotation;

    // Rectangle object for potential Go positions
    private Rectangle playerGo;
    private Point pointGo;
    private final DrawOptions rotationGo;

    private static int lifeCount;
    private int playerScore;

    public Player(Point topLeft) {
        playerStartPoint = topLeft;
        origin = playerStartPoint;
        rotation = new DrawOptions();

        pointGo = origin;
        playerGo = new Rectangle(pointGo, PAC_IMAGE.getWidth(), PAC_IMAGE.getHeight());
        rotationGo = rotation;

        lifeCount = MAX_LIFE;
        playerScore = 0;
    }

    /**
     * go methods to set the Go attributes to move in the
     * direction of keyboard inputs, with distance of STEP_SIZE
     */
    public void goLeft() {
        pointGo = new Point(origin.x - speed, origin.y);
        playerGo = new Rectangle(pointGo, PAC_IMAGE.getWidth(), PAC_IMAGE.getHeight());
        rotationGo.setRotation(Math.PI);
    }
    public void goRight() {
        pointGo = new Point(origin.x + speed, origin.y);
        playerGo = new Rectangle(pointGo, PAC_IMAGE.getWidth(), PAC_IMAGE.getHeight());
        rotationGo.setRotation(0);
    }
    public void goUp() {
        pointGo = new Point(origin.x, origin.y - speed);
        playerGo = new Rectangle(pointGo, PAC_IMAGE.getWidth(), PAC_IMAGE.getHeight());
        rotationGo.setRotation(1.5 * Math.PI);
    }
    public void goDown() {
        pointGo = new Point(origin.x, origin.y + speed);
        playerGo = new Rectangle(pointGo, PAC_IMAGE.getWidth(), PAC_IMAGE.getHeight());
        rotationGo.setRotation(0.5 * Math.PI);
    }

    /**
     * The player does not collide with any walls or ghosts.
     * Set the state of the player to the Go attributes
     */
    public void goCommit() {
        origin = pointGo;
        rotation = rotationGo;
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

        pointGo = origin;
        playerGo = new Rectangle(pointGo, PAC_IMAGE.getWidth(), PAC_IMAGE.getHeight());
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
