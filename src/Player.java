import bagel.*;
import bagel.util.Point;
import bagel.util.Rectangle;

import java.util.ArrayList;

public class Player extends MovingEntity {
    private final static Image PAC_IMAGE = new Image("res/pac.png");
    private final static Image PAC_OPEN_IMAGE = new Image("res/pacOpen.png");
    private final static Image HEART_IMAGE = new Image("res/heart.png");
    private final static int MAX_LIFE = 3;
    private final static int HEART_GAP = 30;
    private final static Point FIRST_HEART_POINT = new Point(900, 10);
    private final static Font SCORE_FONT = new Font("res/FSO8BITR.ttf", 20);
    private final static Point SCORE_POINT = new Point(25, 25);


    private final static double SPEED = 3;
    private final static double FRENZY_SPEED = 4;

    private final DrawOptions rotation;

    // Rectangle object for potential Go positions

    private static int lifeCount;
    private int playerScore;

    public Player(Point topLeft) {
        super(PAC_IMAGE, topLeft, SPEED, FRENZY_SPEED);
        rotation = new DrawOptions();

        lifeCount = MAX_LIFE;
        playerScore = 0;
    }

    public void goLeft(ArrayList<Wall> walls, boolean frenzyMode) {
        super.goLeft(walls, frenzyMode);
        rotation.setRotation(Math.PI);
    }

    public void goRight(ArrayList<Wall> walls, boolean frenzyMode) {
        super.goRight(walls, frenzyMode);
        rotation.setRotation(0);
    }
    public void goUp(ArrayList<Wall> walls, boolean frenzyMode) {
        super.goUp(walls, frenzyMode);
        rotation.setRotation(1.5 * Math.PI);
    }
    public void goDown(ArrayList<Wall> walls, boolean frenzyMode) {
        super.goDown(walls, frenzyMode);
        rotation.setRotation(0.5 * Math.PI);
    }

    /**
     * The player collides with a ghost.
     * Loses 1 life and resets to starting position.
     * Rotation is not reset.
     */
    public void loseLife() {
        lifeCount--;
        origin = startPoint;
        this.setRectangle(new Rectangle(origin, PAC_IMAGE.getWidth(), PAC_IMAGE.getHeight()));
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void increaseScore(int dotScore) {
        playerScore += dotScore;
    }

    public void drawScore() {
        SCORE_FONT.drawString("SCORE " + playerScore, SCORE_POINT.x, SCORE_POINT.y);
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
    public void drawLives() {
        for (int i = 0; i < lifeCount; i++) {
            HEART_IMAGE.drawFromTopLeft(FIRST_HEART_POINT.x + HEART_GAP * i, FIRST_HEART_POINT.y);
        }
    }
}
