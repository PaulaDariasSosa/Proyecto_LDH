import bagel.*;
import bagel.util.Point;

import java.util.ArrayList;

public class Player extends MovingEntity {
    private final static Image PAC_IMAGE = new Image("res/pac.png");
    private final static Image PAC_OPEN_IMAGE = new Image("res/pacOpen.png");
    private final static Image HEART_IMAGE = new Image("res/heart.png");
    private final static int MAX_LIFE = 3;
    private final static int SWITCH_FRAMES = 15;
    private final static int HEART_GAP = 30;
    private final static Point FIRST_HEART_POINT = new Point(900, 10);
    private final static Font SCORE_FONT = new Font("res/FSO8BITR.ttf", 20);
    private final static Point SCORE_POINT = new Point(25, 25);


    private final static double SPEED = 3;
    private final static double FRENZY_SPEED = 4;

    private final DrawOptions rotation;


    private static int lifeCount;
    private int switchFrameCount;
    private boolean isOpen = false;
    private int playerScore;

    public Player(Point topLeft) {
        super(topLeft, SPEED, FRENZY_SPEED);
        setImage(PAC_IMAGE);
        rotation = new DrawOptions();

        lifeCount = MAX_LIFE;
        switchFrameCount = SWITCH_FRAMES;
        playerScore = 0;
    }

    public void playerInput(Input input, Level level, boolean frenzyMode) {
        if (input.isDown(Keys.LEFT)) {
            level.getPlayer().goLeft(level.getWalls(), frenzyMode);
        } else if (input.isDown(Keys.RIGHT)) {
            level.getPlayer().goRight(level.getWalls(), frenzyMode);
        } else if (input.isDown(Keys.UP)) {
            level.getPlayer().goUp(level.getWalls(), frenzyMode);
        } else if (input.isDown(Keys.DOWN)) {
            level.getPlayer().goDown(level.getWalls(), frenzyMode);
        }
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

    public boolean checkCollision(ArrayList<Wall> walls) {
        boolean colliding = false;
        for (Wall wall : walls) {
            if (wall.collidesWith(this)) {
                colliding = true;
                break;
            }
        }
        return colliding;
    }

    /**
     * The player collides with a ghost.
     * Loses 1 life and resets to starting position.
     * Rotation is not reset.
     */
    public void loseLife() {
        lifeCount--;
        resetPosition();
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

    /**
     * Method that checks if the player has reached the target score
     */
    public boolean wonLevel0(int target) {
        return playerScore == target * Dot.POINTS;
    }

    public boolean hasLost() {
        return lifeCount == 0;
    }

    /**
     * Draws the player, switching between
     * opening and closing mouths.
     */
    public void draw() {
        switchFrameCount--;
        if (switchFrameCount == 0) {
            // switching the image being rendered
            if (isOpen) {
                setImage(PAC_IMAGE);
                isOpen = false;
            } else {
                setImage(PAC_OPEN_IMAGE);
                isOpen = true;
            }
            switchFrameCount = SWITCH_FRAMES;
        }
        getImage().drawFromTopLeft(getPosition().x, getPosition().y, rotation);
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
