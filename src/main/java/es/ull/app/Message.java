package es.ull.app;

import bagel.Font;
import bagel.Image;
import bagel.util.Point;

/**
 * @brief Class used to render messages on the screen
 */
public class Message {

    private static final int DEFAULT_FONT_SIZE = 140;
    private static final Font DEFAULT_FONT = new Font("res/SourceSansPro-Bold.ttf", DEFAULT_FONT_SIZE);
    private static final Point DEFAULT_POINT = new Point(140, 350);

    private static final Font HIGH_SCORE_FONT = new Font("res/FSO8BITR.ttf", 35);
    private static final Point HIGH_SCORE_POINT = new Point(300, 450);

    private static final Image CROWN_IMAGE = new Image("res/crown.png");
    private static final Point CROWN_POINT = new Point(512, 430);

    private static final int TITLE_MESSAGE_SIZE = 24;
    private static final String TITLE_MESSAGE = "USE ARROW KEYS TO MOVE\nPRESS SPACE TO START THE GAME\n" +
            "COMPLETE BEFORE TIME RUNS OUT";
    private static final Font TITLE_MESSAGE_FONT = new Font("res/FSO8BITR.ttf", TITLE_MESSAGE_SIZE);
    private static final Point TITLE_MESSAGE_POINT = new Point(250, 530);

    private static final Font COMPLETE_FONT = new Font("res/FSO8BITR.ttf", 60);
    private static final Point COMPLETE_POINT = new Point(140, 350);
    private static final int PASSWORD_OFFSET = 150;

    private static final int LEVEL_INS_FONT_SIZE = 60;
    private static final Font LEVEL_INS_FONT = new Font("res/FSO8BITR.ttf", LEVEL_INS_FONT_SIZE);
    private static final Point LEVEL_INS_POINT = new Point(340, 240);
    private static final int INSTRUCTION_FONT_SIZE = 40;
    private static final Font INSTRUCTION_FONT = new Font("res/FSO8BITR.ttf", INSTRUCTION_FONT_SIZE);
    private static final double INSTRUCTION_X = 100;
    private static final double INSTRUCTION_Y = 350;

    private static final String INSTRUCTION_0_MESSAGE = "REACH TARGET SCORE TO WIN\nGET SHIELD FOR PROTECTION\n" +
            "USE BOMB TO KILL ALL GHOSTS\n\n\nPRESS SPACE TO START";
    private static final String INSTRUCTION_1_MESSAGE = "EAT PIZZA TO GET EXTRA LIFE\n" +
            "GET SUPER STAR TO ATTACK\n\n\nPRESS SPACE TO START";
    private static final String INSTRUCTION_2_MESSAGE = "USE 'THE WORLD' TO FREEZE TIME\n\n\n\nPRESS SPACE TO START";

    private static final Font TARGET_FONT = new Font("res/FSO8BITR.ttf", 20);
    private static final Point TARGET_POINT = new Point(220, 25);

    private static final Font LEVEL_INGAME_FONT = new Font("res/FSO8BITR.ttf", 40);
    private static final Point LEVEL_INGAME_POINT = new Point(450, 40);

    private static final Font FINAL_SCORE_FONT = new Font("res/FSO8BITR.ttf", 40);
    private static final Point FINAL_SCORE_POINT = new Point(250, 450);


    private static final String WIN_MESSAGE = "WELL DONE!";
    private static final Point WIN_POINT = new Point(190, 350);
    private static final String LOSE_MESSAGE = "YOU ARE BROKE!";
    private static final Point LOSE_POINT = new Point(100, 350);
    private static final String TIMES_UP_MESSAGE = "TIME'S UP!";
    private static final Point TIME_POINT = new Point(220, 350);


    private static final String RETRY_MESSAGE = "PRESS SPACE TO\nRETURN TO TITLE SCREEN";
    private static final int RETRY_FONT_SIZE = 30;
    private static final Font RETRY_FONT = new Font("res/FSO8BITR.ttf", RETRY_FONT_SIZE);
    private static final double RETURN_X = 250;
    private static final double RETURN_Y = 550;

    /**
     * @brief Method used to draw the start screen title and instructions
     * @param gameTitle the title of the game
     * @param highScore the high score of the game
     */
    public static void titleScreen(String gameTitle, int highScore) {
        DEFAULT_FONT.drawString(gameTitle, DEFAULT_POINT.x, DEFAULT_POINT.y);
        TITLE_MESSAGE_FONT.drawString(TITLE_MESSAGE, TITLE_MESSAGE_POINT.x, TITLE_MESSAGE_POINT.y);
        if (highScore == ShadowPac.MAX_SCORE) {
            CROWN_IMAGE.draw(CROWN_POINT.x, CROWN_POINT.y);
        } else {
            HIGH_SCORE_FONT.drawString("HIGH SCORE - " + highScore, HIGH_SCORE_POINT.x, HIGH_SCORE_POINT.y);
        }
    }

    /**
     * @brief Method used to draw the level complete messages
     * @param levelNum the level number
     * @param password the password for the next level
     */
    public static void levelComplete(int levelNum, String password) {
        COMPLETE_FONT.drawString("LEVEL " + levelNum + " COMPLETED!", COMPLETE_POINT.x, COMPLETE_POINT.y);
        COMPLETE_FONT.drawString("PASSWORD - " + password, COMPLETE_POINT.x,
                COMPLETE_POINT.y + PASSWORD_OFFSET);
    }

    /**
     * @brief Method used to draw the instructions before es.ull.app.Level 1
     */
    public static void instructionLevel0() {
        LEVEL_INS_FONT.drawString("LEVEL 0", LEVEL_INS_POINT.x, LEVEL_INS_POINT.y);
        INSTRUCTION_FONT.drawString(INSTRUCTION_0_MESSAGE, INSTRUCTION_X, INSTRUCTION_Y);
    }

    /**
     * @brief Method used to draw the instructions before es.ull.app.Level 1
     */
    public static void instructionLevel1() {
        LEVEL_INS_FONT.drawString("LEVEL 1", LEVEL_INS_POINT.x, LEVEL_INS_POINT.y);
        INSTRUCTION_FONT.drawString(INSTRUCTION_1_MESSAGE, INSTRUCTION_X, INSTRUCTION_Y);
    }

    /**
     * @brief Method used to draw the instructions before es.ull.app.Level 2
     */
    public static void instructionLevel2() {
        LEVEL_INS_FONT.drawString("LEVEL 2", LEVEL_INS_POINT.x, LEVEL_INS_POINT.y);
        INSTRUCTION_FONT.drawString(INSTRUCTION_2_MESSAGE, INSTRUCTION_X, INSTRUCTION_Y);
    }

    /**
     * @brief Method used to draw the return to title screen instruction
     */
    public static void returnToTitle() {
        RETRY_FONT.drawString(RETRY_MESSAGE, RETURN_X, RETURN_Y);
    }

    /**
     * @brief Method used to draw the win screen message
     */
    public static void winScreen() {
        DEFAULT_FONT.drawString(WIN_MESSAGE, WIN_POINT.x, WIN_POINT.y);
        finalScore(Player.getTotalScore());
        returnToTitle();
    }

    /**
     * @brief Method used to draw the lose screen message
     */
    public static void loseScreen() {
        DEFAULT_FONT.drawString(LOSE_MESSAGE, LOSE_POINT.x, LOSE_POINT.y);
        finalScore(Player.getTotalScore());
        returnToTitle();
    }

    /**
     * @brief Method used to draw the times up screen message
     */
    public static void timesUp() {
        DEFAULT_FONT.drawString(TIMES_UP_MESSAGE, TIME_POINT.x, TIME_POINT.y);
        finalScore(Player.getTotalScore());
        returnToTitle();
    }

    /**
     * @brief  Method used to draw messages at the centre of the screen
     * @param levelNum the level number
     * @param targetScore the target score for the level
     */
    public static void renderLevel(int levelNum, int targetScore) {
        TARGET_FONT.drawString("TARGET " + targetScore, TARGET_POINT.x, TARGET_POINT.y);
        LEVEL_INGAME_FONT.drawString("LEVEL " + levelNum, LEVEL_INGAME_POINT.x, LEVEL_INGAME_POINT.y);
    }

    /**
     * @brief Method used to draw messages at the centre of the screen
     * @param finalScore the final score of the player
     */
    public static void finalScore(int finalScore) {
        if (finalScore == ShadowPac.MAX_SCORE) {
            CROWN_IMAGE.draw(CROWN_POINT.x, CROWN_POINT.y);
        } else {
            FINAL_SCORE_FONT.drawString("FINAL SCORE - " + finalScore, FINAL_SCORE_POINT.x, FINAL_SCORE_POINT.y);
        }
    }

}
