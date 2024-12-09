package es.ull.app;

import bagel.*;
import bagel.util.Point;

import java.util.ArrayList;

/**
 * @brief Class for the player
 */
public class Player extends MovingEntity {
    private static final Image PAC_IMAGE = new Image("res/pac.png");
    private static final Image PAC_OPEN_IMAGE = new Image("res/pacOpen.png");
    private static final Image HEART_IMAGE = new Image("res/heart.png");
    private static final Image SHIELD_ON_IMAGE = new Image("res/shieldOn.png");
    private static final int STARTING_LIVES = 3;
    private static final int MAX_LIVES = 5;
    private static final int SWITCH_FRAMES = 8;
    private static final int HEART_GAP = 30;
    private static final Point FIRST_HEART_POINT = new Point(850, 10);
    private static final Font SCORE_FONT = new Font("res/FSO8BITR.ttf", 20);
    private static final Point SCORE_POINT = new Point(25, 25);
    private static final int SHIELD_OFFSET = 7;

    private static final double SPEED = 6;
    private static final double FRENZY_SPEED = 10;

    private final DrawOptions rotation;

    public static int lifeCount;
    private int switchFrameCount;
    private boolean isOpen = false;
    private int playerScore;
    private static int totalScore;
    public static boolean shieldOn;

    /**
     * @brief Constructor for es.ull.app.Player
     * @param topLeft
     */
    public Player(Point topLeft) {
        super(topLeft, SPEED, FRENZY_SPEED);
        setImage(PAC_IMAGE);
        rotation = new DrawOptions();

        lifeCount = STARTING_LIVES;
        switchFrameCount = SWITCH_FRAMES;
        playerScore = 0;
        shieldOn = false;
    }

    /**
     * @brief Update the player
     * @param input
     */
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
                if (shieldOn) {
                    SHIELD_ON_IMAGE.drawFromTopLeft(getPosition().x - SHIELD_OFFSET, getPosition().y - SHIELD_OFFSET);
                }
            }
        }
        renderLives();
        renderScore();
    }

    /**
     * @brief Method that moves the player left
     * @param walls
     * @param frenzyMode
     */
    @Override
    public void goLeft(ArrayList<Wall> walls, boolean frenzyMode) {
        super.goLeft(walls, frenzyMode);
        rotation.setRotation(0.5 * Math.PI);
    }

    /**
     * @brief Method that moves the player right
     * @param walls
     * @param frenzyMode
     */
    @Override
    public void goRight(ArrayList<Wall> walls, boolean frenzyMode) {
        super.goRight(walls, frenzyMode);
        rotation.setRotation(1.5 * Math.PI);
    }

    /**
     * @brief Method that moves the player up
     * @param walls
     * @param frenzyMode
     */
    @Override
    public void goUp(ArrayList<Wall> walls, boolean frenzyMode) {
        super.goUp(walls, frenzyMode);
        rotation.setRotation(Math.PI);
    }

    /**
     * @brief Method that moves the player down
     * @param walls
     * @param frenzyMode
     */
    @Override
    public void goDown(ArrayList<Wall> walls, boolean frenzyMode) {
        super.goDown(walls, frenzyMode);
        rotation.setRotation(0);
    }

    /**
     * @brief Method that checks if the player can move
     * @param walls
     * @return boolean if the player can move or not
     */
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
     * @brief Method that renders the player's score
     */
    public void renderScore() {
        SCORE_FONT.drawString("SCORE " + playerScore, SCORE_POINT.x, SCORE_POINT.y);
    }

    /**
     * @brief Method that renders the player's lives
     */
    public static void renderLives() {
        for (int i = 0; i < lifeCount; i++) {
            HEART_IMAGE.drawFromTopLeft(FIRST_HEART_POINT.x + HEART_GAP * i, FIRST_HEART_POINT.y);
        }
    }

    /**
     * @brief Method that gives the player an extra life
     */
    public static void extraLife() {
        /// The player eats a cherry and gains an extra life
        if (lifeCount < MAX_LIVES) {
            lifeCount++;
        }
    }

    /**
     * @brief Method that checks if the player has collided with a ghost
     */
    public void collidesGhost() {
        /// The player collides with a ghost
        if (shieldOn) { /// If the player has a shield
            shieldOn = false;/// The shield is removed
        } else { /// If the player does not have a shield
            lifeCount--; /// The player loses a life
            startRespawn();
        }
        /// Rotation is not reset
    }

    /**
     * @brief Method that checks if the player has 0 lives
     * @return boolean if the player has lost or not
     */
    public static boolean hasLost() {
        return lifeCount == 0;
    }

    /**
     * @brief Method that gets the player's score
     * @return
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     * @brief Method that increases the player's score
     * @param score
     */
    public void increaseScore(int score) {
        playerScore += score;
    }

    /**
     * @brief Method that gets the total score
     * @return
     */
    public static int getTotalScore() {
        return totalScore;
    }

    /**
     * @brief Method that sets the total score
     * @param totalScore
     */
    public static void setTotalScore(int totalScore) {
        Player.totalScore = totalScore;
    }

    /**
     * @brief Method that turns the shield on
     */
    public void shieldOn() {
        shieldOn = true;
    }
}
