import bagel.Font;
import bagel.util.Point;

public class Message {

    private final static int DEFAULT_FONT_SIZE = 80;
    private final static Font DEFAULT_FONT = new Font("res/FSO8BITR.ttf", DEFAULT_FONT_SIZE);
    private final static Point DEFAULT_POINT = new Point(170, 350);

    private final static Font HIGH_SCORE_FONT = new Font("res/FSO8BITR.ttf", 35);
    private final static Point HIGH_SCORE_POINT = new Point(DEFAULT_POINT.x + 130, DEFAULT_POINT.y + 100);

    private final static int TITLE_MESSAGE_SIZE = 24;
    private final static String TITLE_MESSAGE = "PRESS SPACE TO START THE GAME";
    private final static Font TITLE_MESSAGE_FONT = new Font("res/FSO8BITR.ttf", TITLE_MESSAGE_SIZE);
    private final static Point TITLE_MESSAGE_POINT = new Point(DEFAULT_POINT.x + 80, DEFAULT_POINT.y + 190);

    private final static Font COMPLETE_FONT = new Font("res/FSO8BITR.ttf", 60);
    private final static Point COMPLETE_POINT = new Point(140, 350);

    private final static int LEVEL_INS_FONT_SIZE = 60;
    private final static Font LEVEL_INS_FONT = new Font("res/FSO8BITR.ttf", LEVEL_INS_FONT_SIZE);
    private final static Point LEVEL_INS_POINT = new Point(340, 240);
    private final static int INSTRUCTION_FONT_SIZE = 40;
    private final static Font INSTRUCTION_FONT = new Font("res/FSO8BITR.ttf", INSTRUCTION_FONT_SIZE);
    private final static double INSTRUCTION_X = 200;
    private final static double INSTRUCTION_Y = 350;

    private final static String INSTRUCTION_0_MESSAGE = "USE ARROW KEYS TO MOVE\n\nPRESS SPACE TO START";
    private final static String INSTRUCTION_1_MESSAGE = "USE ARROW KEYS TO MOVE\nEAT THE PELLET TO ATTACK\n\nPRESS SPACE TO START";
    private final static String INSTRUCTION_2_MESSAGE = "USE ARROW KEYS TO MOVE\nEAT THE PELLET TO ATTACK\n\nPRESS SPACE TO START";

    private final static Font TARGET_FONT = new Font("res/FSO8BITR.ttf", 20);
    private final static Point TARGET_POINT = new Point(220, 25);

    private final static Font LEVEL_INGAME_FONT = new Font("res/FSO8BITR.ttf", 40);
    private final static Point LEVEL_INGAME_POINT = new Point(450, 40);

    private final static Font FINAL_SCORE_FONT = new Font("res/FSO8BITR.ttf", 40);
    private final static Point FINAL_SCORE_POINT = new Point(250, 450);

    private final static String RETRY_MESSAGE = "PRESS SPACE TO\nRETURN TO TITLE SCREEN";
    private final static int RETRY_FONT_SIZE = 30;
    private final static Font RETRY_FONT = new Font("res/FSO8BITR.ttf", RETRY_FONT_SIZE);
    private final static double RETURN_X = 250;
    private final static double RETURN_Y = 550;

    /**
     * Method used to draw the start screen title and instructions
     */
    public static void titleScreen(String gameTitle, int highScore) {
        DEFAULT_FONT.drawString(gameTitle, DEFAULT_POINT.x, DEFAULT_POINT.y);
        TITLE_MESSAGE_FONT.drawString(TITLE_MESSAGE, TITLE_MESSAGE_POINT.x, TITLE_MESSAGE_POINT.y);
        if (highScore == ShadowPac.MAX_SCORE) {
            HIGH_SCORE_FONT.drawString("HIGH SCORE - congrats", HIGH_SCORE_POINT.x, HIGH_SCORE_POINT.y);
        } else {
            HIGH_SCORE_FONT.drawString("HIGH SCORE - " + highScore, HIGH_SCORE_POINT.x, HIGH_SCORE_POINT.y);
        }
    }

    /**
     * Method used to draw the level complete messages
     */
    public static void levelComplete(int levelNum) {
        COMPLETE_FONT.drawString("LEVEL " + levelNum + " COMPLETED!", COMPLETE_POINT.x, COMPLETE_POINT.y);
    }

    /**
     * Method used to draw the instructions before Level 1
     */
    public static void instructionLevel0() {
        LEVEL_INS_FONT.drawString("LEVEL 0", LEVEL_INS_POINT.x, LEVEL_INS_POINT.y);
        INSTRUCTION_FONT.drawString(INSTRUCTION_0_MESSAGE, INSTRUCTION_X, INSTRUCTION_Y);
    }

    /**
     * Method used to draw the instructions before Level 1
     */
    public static void instructionLevel1() {
        LEVEL_INS_FONT.drawString("LEVEL 1", LEVEL_INS_POINT.x, LEVEL_INS_POINT.y);
        INSTRUCTION_FONT.drawString(INSTRUCTION_1_MESSAGE, INSTRUCTION_X, INSTRUCTION_Y);
    }

    /**
     * Method used to draw the instructions before Level 2
     */
    public static void instructionLevel2() {
        LEVEL_INS_FONT.drawString("LEVEL 2", LEVEL_INS_POINT.x, LEVEL_INS_POINT.y);
        INSTRUCTION_FONT.drawString(INSTRUCTION_2_MESSAGE, INSTRUCTION_X, INSTRUCTION_Y);
    }

    /**
     * Method used to draw the return to title screen instruction
     */
    public static void returnToTitle() {
        RETRY_FONT.drawString(RETRY_MESSAGE, RETURN_X, RETURN_Y);
    }

    /**
     * Method used to draw messages at the centre of the screen
     */
    public static void drawMessage(String message) {
        DEFAULT_FONT.drawString(message, DEFAULT_POINT.x, DEFAULT_POINT.y);
    }

    /**
     * Method used to draw messages at the centre of the screen
     */
    public static void renderLevel(int levelNum, int targetScore) {
        TARGET_FONT.drawString("TARGET " + targetScore, TARGET_POINT.x, TARGET_POINT.y);
        LEVEL_INGAME_FONT.drawString("LEVEL " + levelNum, LEVEL_INGAME_POINT.x, LEVEL_INGAME_POINT.y);
    }

    /**
     * Method used to draw messages at the centre of the screen
     */
    public static void finalScore(int finalScore) {
        if (finalScore == ShadowPac.MAX_SCORE) {
            FINAL_SCORE_FONT.drawString("congrats lol", FINAL_SCORE_POINT.x, FINAL_SCORE_POINT.y);
        } else {
            FINAL_SCORE_FONT.drawString("FINAL SCORE - " + finalScore, FINAL_SCORE_POINT.x, FINAL_SCORE_POINT.y);
        }
    }

}
