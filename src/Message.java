import bagel.Font;
import bagel.Window;

public class Message {
    private final static int WINDOW_WIDTH = Window.getWidth();
    private final static int WINDOW_HEIGHT = Window.getHeight();

    private final static int DEFAULT_FONT_SIZE = 64;
    private final static int INSTRUCTION_0_FONT_SIZE = 24;
    private final static int INSTRUCTION_1_FONT_SIZE = 40;
    private final static Font DEFAULT_FONT = new Font("res/FSO8BITR.ttf", DEFAULT_FONT_SIZE);
    private final static double TITLE_X = 260;
    private final static double TITLE_Y = 250;

    private final static String INSTRUCTION_0_MESSAGE = "PRESS SPACE TO START\nUSE ARROW KEYS TO MOVE";
    private final static Font INSTRUCTION_0_FONT = new Font("res/FSO8BITR.ttf", INSTRUCTION_0_FONT_SIZE);
    private final static double INSTRUCTION_0_X = TITLE_X + 60;
    private final static double INSTRUCTION_0_Y = TITLE_Y + 190;

    private final static String INSTRUCTION_1_MESSAGE = "PRESS SPACE TO START\nUSE ARROW KEYS TO MOVE\nEAT THE PELLET TO ATTACK";
    private final static Font INSTRUCTION_1_FONT = new Font("res/FSO8BITR.ttf", INSTRUCTION_1_FONT_SIZE);
    private final static double INSTRUCTION_1_X = 200;
    private final static double INSTRUCTION_1_Y = 350;

    public static void titleScreen(String gameTitle) {
        DEFAULT_FONT.drawString(gameTitle, TITLE_X, TITLE_Y);
        INSTRUCTION_0_FONT.drawString(INSTRUCTION_0_MESSAGE, INSTRUCTION_0_X, INSTRUCTION_0_Y);
    }
    public static void instructionLevel1() {
        INSTRUCTION_1_FONT.drawString(INSTRUCTION_1_MESSAGE, INSTRUCTION_1_X, INSTRUCTION_1_Y);
    }
    public static void drawMessage(String message) {
        DEFAULT_FONT.drawString(message, WINDOW_WIDTH/2.0 - DEFAULT_FONT.getWidth(message)/2,
                WINDOW_HEIGHT/2.0 + DEFAULT_FONT_SIZE/2.0);
    }

}
