import bagel.*;
import bagel.util.Point;

import java.util.ArrayList;

public class Player extends MovingEntity {
    private final static Image PAC_IMAGE = new Image("res/pac.png");
    private final static Image PAC_OPEN_IMAGE = new Image("res/pacOpen.png");
    private final static Image HEART_IMAGE = new Image("res/heart.png");
    private final static int STARTING_LIVES = 3;
    private static final int MAX_LIVES = 5;
    private final static int SWITCH_FRAMES = 8;
    private final static int HEART_GAP = 30;
    private final static Point FIRST_HEART_POINT = new Point(850, 10);
    private final static Font SCORE_FONT = new Font("res/FSO8BITR.ttf", 20);
    private final static Point SCORE_POINT = new Point(25, 25);

    private final static double SPEED = 6;
    private final static double FRENZY_SPEED = 10;

    private final DrawOptions rotation;

    private static int lifeCount;
    private int switchFrameCount;
    private boolean isOpen = false;
    private int playerScore;
    private static int totalScore;

    public Player(Point topLeft) {
        super(topLeft, SPEED, FRENZY_SPEED);
        setImage(PAC_IMAGE);
        rotation = new DrawOptions();

        lifeCount = STARTING_LIVES;
        switchFrameCount = SWITCH_FRAMES;
        playerScore = 0;
    }

    public void update(Input input) {
        if (isRespawning()) {
            respawn();
        } else {
            if (isActive()) {
                if (input.isDown(Keys.RIGHT) || input.isDown(Keys.DOWN) ||
                        input.isDown(Keys.UP) || input.isDown(Keys.LEFT)) {
                    // switching the image being rendered
                    switchFrameCount--;
                    if (switchFrameCount == 0) {
                        if (isOpen) {
                            setImage(PAC_OPEN_IMAGE);
                            isOpen = false;
                        } else {
                            setImage(PAC_IMAGE);
                            isOpen = true;
                        }
                        switchFrameCount = SWITCH_FRAMES;
                    }
                }
                getImage().drawFromTopLeft(getPosition().x, getPosition().y, rotation);
            }
        }
        renderLives();
        renderScore();
    }

    @Override
    public void goLeft(ArrayList<Wall> walls, boolean frenzyMode) {
        super.goLeft(walls, frenzyMode);
        rotation.setRotation(0.5 * Math.PI);
    }

    @Override
    public void goRight(ArrayList<Wall> walls, boolean frenzyMode) {
        super.goRight(walls, frenzyMode);
        rotation.setRotation(1.5 * Math.PI);
    }

    @Override
    public void goUp(ArrayList<Wall> walls, boolean frenzyMode) {
        super.goUp(walls, frenzyMode);
        rotation.setRotation(Math.PI);
    }

    @Override
    public void goDown(ArrayList<Wall> walls, boolean frenzyMode) {
        super.goDown(walls, frenzyMode);
        rotation.setRotation(0);
    }

    @Override
    public boolean canMove(ArrayList<Wall> walls) {
        boolean canMove = true;
        for (Wall wall : walls) {
            if (wall.collidesWith(this)) {
                canMove = false;
                break;
            }
        }
        return canMove;
    }

    /**
     * Method that renders the player's score
     */
    public void renderScore() {
        SCORE_FONT.drawString("SCORE " + playerScore, SCORE_POINT.x, SCORE_POINT.y);
    }

    /**
     * Method that renders the player's lives
     */
    public static void renderLives() {
        for (int i = 0; i < lifeCount; i++) {
            HEART_IMAGE.drawFromTopLeft(FIRST_HEART_POINT.x + HEART_GAP * i, FIRST_HEART_POINT.y);
        }
    }

    /**
     * The player eats a cherry.
     * Gains 1 extra life
     */
    public static void extraLife() {
        if (lifeCount < MAX_LIVES) {
            lifeCount++;
        }
    }

    /**
     * The player collides with a ghost.
     * Loses 1 life and resets to starting position.
     * Rotation is not reset.
     */
    public void loseLife() {
        lifeCount--;
        startRespawn();
    }

    /**
     * Method that checks if the player has 0 lives
     */
    public static boolean hasLost() {
        return lifeCount == 0;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void increaseScore(int score) {
        playerScore += score;
    }

    public static int getTotalScore() {
        return totalScore;
    }

    public static void setTotalScore(int totalScore) {
        Player.totalScore = totalScore;
    }
}
