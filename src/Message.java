import bagel.Font;

public class Message {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static int DEFAULT_FONT_SIZE = 64;
    private final static Font DEFAULT_FONT = new Font("res/FSO8BITR.ttf", DEFAULT_FONT_SIZE);
    private final static double TITLE_POINT_X = 260;
    private final static double TITLE_POINT_Y = 250;

    private final static String[] INSTRUCTION_0_MESSAGE = {"PRESS SPACE TO START", "USE ARROW KEYS TO MOVE"};
    private final static Font INSTRUCTION_0_FONT = new Font("res/FSO8BITR.ttf", 24);
    private final static double INSTRUCTION_0_POINT_X = TITLE_POINT_X + 60;
    private final static double INSTRUCTION_0_POINT_Y1 = TITLE_POINT_Y + 190;
    private final static double INSTRUCTION_0_POINT_Y2 = INSTRUCTION_0_POINT_Y1 + 40;

    private final static String[] INSTRUCTION1_MESSAGE = {"PRESS SPACE TO START", "USE ARROW KEYS TO MOVE", "EAT THE PELLET TO ATTACK"};
    private final static Font INSTRUCTION_1_FONT = new Font("res/FSO8BITR.ttf", 40);
    private final static double INSTRUCTION1_POINT_X = 200;
    private final static double INSTRUCTION1_POINT_Y1 = 350;
    private final static double INSTRUCTION1_POINT_Y2 = INSTRUCTION1_POINT_Y1 + 40;
    private final static double INSTRUCTION1_POINT_Y3 = INSTRUCTION1_POINT_Y2 + 40;


    private final static String LEVEL_COMPLETE = "LEVEL COMPLETE!";
    private final static String WIN_MESSAGE = "WELL DONE!";
    private final static String LOSE_MESSAGE = "GAME OVER!";

    public static void titleScreen(String gameTitle) {
        DEFAULT_FONT.drawString(gameTitle, TITLE_POINT_X, TITLE_POINT_Y);
        INSTRUCTION_0_FONT.drawString(INSTRUCTION_0_MESSAGE[0], INSTRUCTION_0_POINT_X, INSTRUCTION_0_POINT_Y1);
        INSTRUCTION_0_FONT.drawString(INSTRUCTION_0_MESSAGE[1], INSTRUCTION_0_POINT_X, INSTRUCTION_0_POINT_Y2);
    }
    public static void levelCompleteScreen() {
        DEFAULT_FONT.drawString(LEVEL_COMPLETE, (double)WINDOW_WIDTH/2 - DEFAULT_FONT.getWidth(LEVEL_COMPLETE)/2, (double)WINDOW_HEIGHT/2 + (double)DEFAULT_FONT_SIZE/2);
    }
    public static void instructionLevel1() {
        INSTRUCTION_1_FONT.drawString(INSTRUCTION1_MESSAGE[0], INSTRUCTION1_POINT_X, INSTRUCTION1_POINT_Y1);
        INSTRUCTION_1_FONT.drawString(INSTRUCTION1_MESSAGE[1], INSTRUCTION1_POINT_X, INSTRUCTION1_POINT_Y2);
        INSTRUCTION_1_FONT.drawString(INSTRUCTION1_MESSAGE[2], INSTRUCTION1_POINT_X, INSTRUCTION1_POINT_Y3);
    }
    public static void win() {
        DEFAULT_FONT.drawString(WIN_MESSAGE, (double)WINDOW_WIDTH/2 - DEFAULT_FONT.getWidth(WIN_MESSAGE)/2, (double)WINDOW_HEIGHT/2 + (double)DEFAULT_FONT_SIZE/2);
    }
    public static void lose() {
        DEFAULT_FONT.drawString(LOSE_MESSAGE, (double)WINDOW_WIDTH/2 - DEFAULT_FONT.getWidth(LOSE_MESSAGE)/2, (double)WINDOW_HEIGHT/2 + (double)DEFAULT_FONT_SIZE/2);
    }

}
