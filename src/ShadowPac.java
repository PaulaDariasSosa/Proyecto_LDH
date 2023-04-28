import java.io.*;
import bagel.*;
import bagel.util.Point;

/**
 * SWEN20003 Project 1, Semester 1, 2023
 * Tung Khanh Ho
 */
public class ShadowPac extends AbstractGame  {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "SHADOW PAC";
    private final static int DEFAULT_FONT_SIZE = 64;
    private final Font defaultFont = new Font("res/FSO8BITR.ttf", DEFAULT_FONT_SIZE);
    private final static double TITLE_POINT_X = 260;
    private final static double TITLE_POINT_Y = 250;

    private final static String[] INSTRUCTION_MESSAGE = {"PRESS SPACE TO START", "USE ARROW KEYS TO MOVE"};
    private final Font instructionFont = new Font("res/FSO8BITR.ttf", 24);
    private final static double INSTRUCTION_POINT_X = TITLE_POINT_X + 60;
    private final static double INSTRUCTION_POINT_Y1 = TITLE_POINT_Y + 190;
    private final static double INSTRUCTION_POINT_Y2 = INSTRUCTION_POINT_Y1 + 40;

    private final Image BACKGROUND_IMAGE = new Image("res/background0.png");
    private boolean titleScreen = true;

    private final static String WIN_MESSAGE = "WELL DONE!";
    private final static String LOSE_MESSAGE = "GAME OVER!";
    private final Point WIN_MESSAGE_POINT = new Point((double)WINDOW_WIDTH/2 - defaultFont.getWidth(WIN_MESSAGE)/2,
            (double)WINDOW_HEIGHT/2 + (double)DEFAULT_FONT_SIZE/2);
    private final Point LOSE_MESSAGE_POINT = new Point((double)WINDOW_WIDTH/2 - defaultFont.getWidth(LOSE_MESSAGE)/2,
            (double)WINDOW_HEIGHT/2 + (double)DEFAULT_FONT_SIZE/2);

    private final static Point FIRST_HEART_POINT = new Point(900, 10);
    private final Font scoreFont = new Font("res/FSO8BITR.ttf", 20);
    private final static Point SCORE_POINT = new Point(25, 25);

    // count the frame number to switch between open and closed images
    private int frameCount = 0;
    private final static int SWITCH_FRAME = 15;

    private final static int MAX_WALLS = 145;
    private final static int MAX_GHOSTS = 4;
    private final static int MAX_DOTS = 121;
    private final static int MAX_SCORE = 1210;

    private Player player;
    private final Wall[] walls = new Wall[MAX_WALLS];
    private final Ghost[] ghosts = new Ghost[MAX_GHOSTS];
    private final Dot[] dots = new Dot[MAX_DOTS];

    public ShadowPac(){
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
    }

    /**
     * Method used to read file and create objects (you can change
     * this method as you wish).
     */
    private void readCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("res/level0.csv"))) {
            String text;
            int wallCount = 0, ghostCount = 0, dotCount = 0;
            while ((text = br.readLine()) != null) {
                String[] cells = text.split(",");
                Point point = new Point(Integer.parseInt(cells[1]), Integer.parseInt(cells[2]));

                switch (cells[0]) {
                    case "Player":
                        player = new Player(point);
                        break;
                    case "Wall":
                        walls[wallCount] = new Wall(point);
                        wallCount++;
                        break;
                    case "Ghost":
                        ghosts[ghostCount] = new Ghost(point);
                        ghostCount++;
                        break;
                    default:
                        dots[dotCount] = new Dot(point);
                        dotCount++;
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowPac game = new ShadowPac();
        game.readCSV();
        game.run();
    }

    /**
     * Performs a state update.
     * Allows the game to exit when the escape key is pressed.
     */
    @Override
    protected void update(Input input) {
        if (input.wasPressed(Keys.ESCAPE)){
            Window.close();
        }
        else {
            BACKGROUND_IMAGE.draw(Window.getWidth() / 2.0, Window.getHeight() / 2.0);
            if (input.wasPressed(Keys.SPACE)) {
                titleScreen = false;
            }

            if (titleScreen) {
                defaultFont.drawString(GAME_TITLE, TITLE_POINT_X, TITLE_POINT_Y);
                instructionFont.drawString(INSTRUCTION_MESSAGE[0], INSTRUCTION_POINT_X, INSTRUCTION_POINT_Y1);
                instructionFont.drawString(INSTRUCTION_MESSAGE[1], INSTRUCTION_POINT_X, INSTRUCTION_POINT_Y2);
            }
            else if (player.getPlayerScore() == MAX_SCORE) {
                // player has won
                defaultFont.drawString(WIN_MESSAGE, WIN_MESSAGE_POINT.x, WIN_MESSAGE_POINT.y);
            } else if (player.hasLost()) {
                defaultFont.drawString(LOSE_MESSAGE, LOSE_MESSAGE_POINT.x, LOSE_MESSAGE_POINT.y);
            }
            else {
                // Playing the game
                if (input.isDown(Keys.LEFT)) {
                    player.goLeft();
                }
                else if (input.isDown(Keys.RIGHT)) {
                    player.goRight();
                }
                else if (input.isDown(Keys.UP)) {
                    player.goUp();
                }
                else if (input.isDown(Keys.DOWN)) {
                    player.goDown();
                }

                boolean colliding = false;
                for (Wall wall : walls) {
                    if (wall.collidesWith(player)) {
                        colliding = true;
                        break;
                    }
                }
                for (Ghost ghost : ghosts) {
                    if (ghost.collidesWith(player)) {
                        player.loseLife();
                        colliding = true;
                        break;
                    }
                }

                if (!colliding) {
                    // player does not collide with any wall or ghost
                    player.goCommit();
                    for (Dot dot : dots) {
                        if (!dot.isEaten()) {
                            if (dot.collidesWith(player)) {
                                dot.eat();
                                player.increaseScore(Dot.getScore());
                                break;
                            }
                        }
                    }
                }

                if (!player.hasLost()) {
                    // Player still has more than 0 life:
                    // draw player, switch between opening and closing mouth every 15 frames
                    player.draw(frameCount, SWITCH_FRAME);

                    // draw stationary objects on screen
                    for (Wall wall : walls) {
                        wall.draw();
                    }
                    for (Ghost ghost : ghosts) {
                        ghost.draw();
                    }
                    for (Dot dot : dots) {
                        dot.draw();
                    }

                    // draw remaining lives and score
                    player.drawLives(FIRST_HEART_POINT);
                    scoreFont.drawString("SCORE " + player.getPlayerScore(), SCORE_POINT.x, SCORE_POINT.y);

                    frameCount++;
                    if (frameCount == SWITCH_FRAME * 2) {
                        frameCount = 0;
                    }
                }
            }
        }
    }
}
