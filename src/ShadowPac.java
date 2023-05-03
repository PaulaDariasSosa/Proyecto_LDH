import bagel.*;

/**
 * SWEN20003 Project 2B, Semester 1, 2023
 * Tung Khanh Ho
 */
public class ShadowPac extends AbstractGame  {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "SHADOW PAC";
    private final static String LEVEL_0_FILE = "res/level0.csv";
    private final static String LEVEL_1_FILE = "res/level1.csv";
    private final Image BACKGROUND_IMAGE = new Image("res/background0.png");

    private final static String LEVEL_COMPLETE = "LEVEL COMPLETE!";
    private final static String WIN_MESSAGE = "WELL DONE!";
    private final static String LOSE_MESSAGE = "GAME OVER!";

    private final static int TITLE_SCREEN = 0;
    private final static int LEVEL_0 = 1;
    private final static int LEVEL_COMPLETE_SCREEN = 2;
    private final static int INSTRUCTION_1_SCREEN = 3;
    private final static int LEVEL_1 = 4;
    private int screenStatus = TITLE_SCREEN;


    // count the frame number to switch between open and closed images
    private final static int SWITCH_FRAMES = 15;
    private int switchFrameCount = 0;

    private final static int COMPLETE_MESSAGE_FRAMES = 300;
    private int levelCompleteFrameCount = 0;

    private final static int MAX_SCORE_LVL_1 = 800;

    // Frenzy mode attributes
    private final static int FRENZY_MODE_FRAMES = 1000;
    private boolean frenzyMode = false;
    private int frenzyFrameCount;

    private final Level level0 = new Level(LEVEL_0_FILE);
    private final int numDotLevel0 = level0.getNumDots();
    private final Level level1 = new Level(LEVEL_1_FILE);
    public ShadowPac(){
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowPac game = new ShadowPac();
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

            if (screenStatus == TITLE_SCREEN) {
                if (input.wasPressed(Keys.SPACE)) {
                    screenStatus = LEVEL_0;
                }
                if (input.wasPressed(Keys.W)) {
                    screenStatus = LEVEL_1;
                }
            }

            else if (screenStatus == INSTRUCTION_1_SCREEN && input.wasPressed(Keys.SPACE)) {
                screenStatus = LEVEL_1;
            }

            else if (screenStatus == LEVEL_COMPLETE_SCREEN && levelCompleteFrameCount == COMPLETE_MESSAGE_FRAMES) {
                screenStatus = INSTRUCTION_1_SCREEN;
            }

            if (screenStatus == TITLE_SCREEN) {
                Message.titleScreen(GAME_TITLE);
            }

            else if (screenStatus == LEVEL_COMPLETE_SCREEN) {
                Message.drawMessage(LEVEL_COMPLETE);
                levelCompleteFrameCount++;
            }

            else if (screenStatus == INSTRUCTION_1_SCREEN) {
                Message.instructionLevel1();
            }

            else if (screenStatus == LEVEL_0 && level0.getPlayer().wonLevel0(numDotLevel0)) {
                screenStatus = LEVEL_COMPLETE_SCREEN;
            }
            else if (level0.getPlayer().hasLost()) {
                Message.drawMessage(LOSE_MESSAGE);
            }
            else if (screenStatus == LEVEL_1 && level1.getPlayer().getPlayerScore() >= MAX_SCORE_LVL_1) {
                // player has won
                Message.drawMessage(WIN_MESSAGE);
            }

            else if (screenStatus == LEVEL_0) {
                // Playing level 0
                playerInput(input, level0);

                for (Ghost ghost : level0.getGhosts()) {
                    if (ghost.collidesWith(level0.getPlayer())) {
                        level0.getPlayer().loseLife();
                        break;
                    }
                }

                for (Dot dot : level0.getDots()) {
                    if (dot.collidesWith(level0.getPlayer())) {
                        level0.getDots().remove(dot);
                        level0.getPlayer().increaseScore(Dot.POINTS);
                        break;
                    }
                }

                if (!level0.getPlayer().hasLost()) {
                    // Player still has more than 0 life:
                    // draw player, switch between opening and closing mouth every 15 frames
                    level0.getPlayer().draw(switchFrameCount, SWITCH_FRAMES);

                    // draw stationary objects on screen
                    for (Wall wall : level0.getWalls()) {
                        wall.draw();
                    }
                    for (Ghost ghost : level0.getGhosts()) {
                        ghost.draw(frenzyMode);
                    }
                    for (Dot dot : level0.getDots()) {
                        dot.draw();
                    }

                    // draw remaining lives and score
                    level0.getPlayer().drawLives();
                    level0.getPlayer().drawScore();

                    switchFrameCount++;
                    if (switchFrameCount == SWITCH_FRAMES * 2) {
                        switchFrameCount = 0;
                    }
                }
            }

            else {
                // Playing level 1
                playerInput(input, level1);

                for (Pellet pellet : level1.getPellets()) {
                    if (pellet.collidesWith(level1.getPlayer())) {
                        frenzyMode = true;
                        frenzyFrameCount = 0;
                        level1.getPellets().remove(pellet);
                        break;
                    }
                }
                if (frenzyMode) {
                    frenzyFrameCount++;
                }

                for (Ghost ghost : level1.getGhosts()) {
                    if (!ghost.isEaten()) {
                        ghost.move(level1.getWalls(), frenzyMode);
                        if (ghost.collidesWith(level1.getPlayer())) {
                            if (frenzyMode) {
                                level1.getPlayer().increaseScore(Ghost.getScore());
                                ghost.setEaten(true);
                            } else {
                                level1.getPlayer().loseLife();
                                ghost.resetPosition();
                            }
                        }
                    }
                }

                for (Dot dot : level1.getDots()) {
                    if (dot.collidesWith(level1.getPlayer())) {
                        level1.getPlayer().increaseScore(Dot.POINTS);
                        level1.getDots().remove(dot);
                        break;
                    }
                }
                for (Cherry cherry : level1.getCherries()) {
                    if (cherry.collidesWith(level1.getPlayer())) {
                        level1.getPlayer().increaseScore(Cherry.POINTS);
                        level1.getCherries().remove(cherry);
                        break;
                    }
                }

                if (!level1.getPlayer().hasLost()) {
                    // Player still has more than 0 life:
                    // draw player, switch between opening and closing mouth every 15 frames
                    level1.getPlayer().draw(switchFrameCount, SWITCH_FRAMES);

                    // draw stationary objects on screen
                    for (Wall wall : level1.getWalls()) {
                        wall.draw();
                    }
                    for (Dot dot : level1.getDots()) {
                        dot.draw();
                    }
                    for (Cherry cherry : level1.getCherries()) {
                        cherry.draw();
                    }
                    for (Pellet pellet : level1.getPellets()) {
                        pellet.draw();
                    }
                    for (Ghost ghost : level1.getGhosts()) {
                        if (!ghost.isEaten()) {
                            ghost.draw(frenzyMode);
                        }
                    }

                    // draw remaining lives and score
                    level1.getPlayer().drawLives();
                    level1.getPlayer().drawScore();

                    switchFrameCount++;
                    if (switchFrameCount == SWITCH_FRAMES * 2) {
                        switchFrameCount = 0;
                    }
                    if (frenzyFrameCount == FRENZY_MODE_FRAMES) {
                        frenzyMode = false;
                        frenzyFrameCount = 0;
                        for (Ghost ghost : level1.getGhosts()) {
                            if (ghost.isEaten()) {
                                ghost.resetPosition();
                                ghost.setEaten(false);
                            }
                        }
                    }
                }
            }

        }
    }

    private void playerInput(Input input, Level level) {
        if (input.isDown(Keys.LEFT)) {
            level.getPlayer().goLeft(level.getWalls(), frenzyMode);
        }
        else if (input.isDown(Keys.RIGHT)) {
            level.getPlayer().goRight(level.getWalls(), frenzyMode);
        }
        else if (input.isDown(Keys.UP)) {
            level.getPlayer().goUp(level.getWalls(), frenzyMode);
        }
        else if (input.isDown(Keys.DOWN)) {
            level.getPlayer().goDown(level.getWalls(), frenzyMode);
        }
    }
}
