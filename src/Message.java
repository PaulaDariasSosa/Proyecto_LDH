import bagel.Font;
import bagel.util.Point;

public class Message {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static int DEFAULT_FONT_SIZE = 64;
    private final static Font defaultFont = new Font("res/FSO8BITR.ttf", DEFAULT_FONT_SIZE);
    private final static double TITLE_POINT_X = 260;
    private final static double TITLE_POINT_Y = 250;

    private final static String[] INSTRUCTION0_MESSAGE = {"PRESS SPACE TO START", "USE ARROW KEYS TO MOVE"};
    private static final Font instruction0Font = new Font("res/FSO8BITR.ttf", 24);
    private final static double INSTRUCTION0_POINT_X = TITLE_POINT_X + 60;
    private final static double INSTRUCTION0_POINT_Y1 = TITLE_POINT_Y + 190;
    private final static double INSTRUCTION0_POINT_Y2 = INSTRUCTION0_POINT_Y1 + 40;

    private final static String[] INSTRUCTION1_MESSAGE = {"PRESS SPACE TO START", "USE ARROW KEYS TO MOVE", "EAT THE PELLET TO ATTACK"};
    private static final Font instruction1Font = new Font("res/FSO8BITR.ttf", 40);
    private final static double INSTRUCTION1_POINT_X = 200;
    private final static double INSTRUCTION1_POINT_Y1 = 350;
    private final static double INSTRUCTION1_POINT_Y2 = INSTRUCTION1_POINT_Y1 + 40;
    private final static double INSTRUCTION1_POINT_Y3 = INSTRUCTION1_POINT_Y2 + 40;


    private final static String LEVEL_COMPLETE = "LEVEL COMPLETE!";
    private final static String WIN_MESSAGE = "WELL DONE!";
    private final static String LOSE_MESSAGE = "GAME OVER!";

    private static final Point LEVEL_COMPLETE_POINT = new Point((double)WINDOW_WIDTH/2 - defaultFont.getWidth(LEVEL_COMPLETE)/2,
            (double)WINDOW_HEIGHT/2 + (double)DEFAULT_FONT_SIZE/2);
    private static final Point WIN_MESSAGE_POINT = new Point((double)WINDOW_WIDTH/2 - defaultFont.getWidth(WIN_MESSAGE)/2,
            (double)WINDOW_HEIGHT/2 + (double)DEFAULT_FONT_SIZE/2);
    private static final Point LOSE_MESSAGE_POINT = new Point((double)WINDOW_WIDTH/2 - defaultFont.getWidth(LOSE_MESSAGE)/2,
            (double)WINDOW_HEIGHT/2 + (double)DEFAULT_FONT_SIZE/2);

    public static void writeTitleScreen(String gameTitle) {
        defaultFont.drawString(gameTitle, TITLE_POINT_X, TITLE_POINT_Y);
        instruction0Font.drawString(INSTRUCTION0_MESSAGE[0], INSTRUCTION0_POINT_X, INSTRUCTION0_POINT_Y1);
        instruction0Font.drawString(INSTRUCTION0_MESSAGE[1], INSTRUCTION0_POINT_X, INSTRUCTION0_POINT_Y2);
    }

    public static void writeLevelCompleteScreen() {
        defaultFont.drawString(LEVEL_COMPLETE, LEVEL_COMPLETE_POINT.x, LEVEL_COMPLETE_POINT.y);
    }

    public static void writeInstruction1() {
        instruction1Font.drawString(INSTRUCTION1_MESSAGE[0], INSTRUCTION1_POINT_X, INSTRUCTION1_POINT_Y1);
        instruction1Font.drawString(INSTRUCTION1_MESSAGE[1], INSTRUCTION1_POINT_X, INSTRUCTION1_POINT_Y2);
        instruction1Font.drawString(INSTRUCTION1_MESSAGE[2], INSTRUCTION1_POINT_X, INSTRUCTION1_POINT_Y3);
    }

    public static void writeLoseMessage() {
        defaultFont.drawString(LOSE_MESSAGE, LOSE_MESSAGE_POINT.x, LOSE_MESSAGE_POINT.y);
    }

    public static void writeWinMessage() {
        defaultFont.drawString(WIN_MESSAGE, WIN_MESSAGE_POINT.x, WIN_MESSAGE_POINT.y);
    }

}
