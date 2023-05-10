import bagel.Font;
import bagel.Window;
import bagel.util.Point;

public class Message {
    private final static int WINDOW_WIDTH = Window.getWidth();
    private final static int WINDOW_HEIGHT = Window.getHeight();

    private final static int DEFAULT_FONT_SIZE = 64;
    private final static int INSTRUCTION_0_FONT_SIZE = 24;
    private final static int INSTRUCTION_1_FONT_SIZE = 40;
    private final static Font DEFAULT_FONT = new Font("res/FSO8BITR.ttf", DEFAULT_FONT_SIZE);
    private final static double TITLE_X = 260;
    private final static double TITLE_Y = 350;

    private final static String INSTRUCTION_0_MESSAGE = "PRESS SPACE TO START\nUSE ARROW KEYS TO MOVE";
    private final static Font INSTRUCTION_0_FONT = new Font("res/FSO8BITR.ttf", INSTRUCTION_0_FONT_SIZE);
    private final static double INSTRUCTION_0_X = TITLE_X + 60;
    private final static double INSTRUCTION_0_Y = TITLE_Y + 190;

    private final static Font HIGH_SCORE_FONT = new Font("res/FSO8BITR.ttf", 30);
    private final static Point HIGH_SCORE_POINT = new Point(340, 450);

    private final static String INSTRUCTION_1_MESSAGE = "PRESS SPACE TO START\nUSE ARROW KEYS TO MOVE\nEAT THE PELLET TO ATTACK";
    private final static Font INSTRUCTION_1_FONT = new Font("res/FSO8BITR.ttf", INSTRUCTION_1_FONT_SIZE);
    private final static double INSTRUCTION_1_X = 200;
    private final static double INSTRUCTION_1_Y = 350;

    private final static String RETRY_MESSAGE = "PRESS SPACE TO\nRETURN TO TITLE SCREEN";
    private final static int RETRY_FONT_SIZE = 30;
    private final static Font RETRY_FONT = new Font("res/FSO8BITR.ttf", RETRY_FONT_SIZE);
    private final static double RETURN_X = 300;
    private final static double RETURN_Y = 500;

    private final static Font TARGET_FONT = new Font("res/FSO8BITR.ttf", 20);
    private final static Point TARGET_POINT = new Point(220, 25);

    private final static Font LEVEL_FONT = new Font("res/FSO8BITR.ttf", 30);
    private final static Point LEVEL_POINT = new Point(WINDOW_WIDTH/2.0, 30);


    /**
     * Method used to draw the start screen title and instructions
     */
    public static void titleScreen(String gameTitle, int highScore) {
        DEFAULT_FONT.drawString(gameTitle, TITLE_X, TITLE_Y);
        INSTRUCTION_0_FONT.drawString(INSTRUCTION_0_MESSAGE, INSTRUCTION_0_X, INSTRUCTION_0_Y);
        if (highScore == ShadowPac.MAX_SCORE) {
            HIGH_SCORE_FONT.drawString("HIGH SCORE - congrats", HIGH_SCORE_POINT.x, HIGH_SCORE_POINT.y);
        } else {
            HIGH_SCORE_FONT.drawString("HIGH SCORE - " + highScore, HIGH_SCORE_POINT.x, HIGH_SCORE_POINT.y);
        }
    }

    /**
     * Method used to draw the instructions before Level 1
     */
    public static void instructionLevel1() {
        INSTRUCTION_1_FONT.drawString(INSTRUCTION_1_MESSAGE, INSTRUCTION_1_X, INSTRUCTION_1_Y);
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
        DEFAULT_FONT.drawString(message, WINDOW_WIDTH/2.0 - DEFAULT_FONT.getWidth(message)/2,
                WINDOW_HEIGHT/2.0 + DEFAULT_FONT_SIZE/2.0);
    }

    /**
     * Method used to draw messages at the centre of the screen
     */
    public static void renderLevel(int levelNum, int targetScore) {
        TARGET_FONT.drawString("TARGET " + targetScore, TARGET_POINT.x, TARGET_POINT.y);
        LEVEL_FONT.drawString("LEVEL " + levelNum, LEVEL_POINT.x, LEVEL_POINT.y);
    }

}
