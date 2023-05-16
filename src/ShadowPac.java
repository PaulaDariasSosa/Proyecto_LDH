import bagel.*;

/**
 * SWEN20003 Project 2B, Semester 1, 2023
 * Tung Khanh Ho
 */
public class ShadowPac extends AbstractGame {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "SHADOW TUKA";
    private final static String LEVEL_0_FILE = "res/level0.csv";
    private final static String LEVEL_1_FILE = "res/level1.csv";
    private final static String LEVEL_2_FILE = "res/level2.csv";

    private final Image BACKGROUND_IMAGE_0 = new Image("res/background0.png");
    private final Image BACKGROUND_IMAGE_1 = new Image("res/zawarudo.png");
    private final Image BACKGROUND_IMAGE_2 = new Image("res/explosion.png");
    private Image background;

    private final static String WIN_MESSAGE = "WELL DONE!";
    private final static String LOSE_MESSAGE = "GAME OVER!";

    private final static int TITLE_SCREEN = 0;
    private final static int INSTRUCTION_0_SCREEN = 1;
    private final static int LEVEL_0 = 2;
    private final static int LVL_0_COMPLETE_SCREEN = 3;
    private final static int INSTRUCTION_1_SCREEN = 4;
    private final static int LEVEL_1 = 5;
    private final static int LVL_1_COMPLETE_SCREEN = 6;
    private final static int INSTRUCTION_2_SCREEN = 7;
    private final static int LEVEL_2 = 8;
    private int screenStatus;
    private boolean gameOver;
    private boolean playerWin;

    private final static int COMPLETE_MESSAGE_FRAMES = 150;
    private int levelCompleteFrameCount;

    private final static int TARGET_SCORE_LVL_0 = 1200;
    private final static int TARGET_SCORE_LVL_1 = 1200;
    private final static int TARGET_SCORE_LVL_2 = 1200;
    public final static int MAX_SCORE = TARGET_SCORE_LVL_0 + TARGET_SCORE_LVL_1 + TARGET_SCORE_LVL_2;

    // Frenzy mode attributes
    private final static int FRENZY_MODE_FRAMES = 500;
    private boolean frenzyMode;
    private int frenzyFrameCount;

    private int highScore;

    private Level level0;
    private Level level1;
    private Level level2;

    public ShadowPac() {
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
        background = BACKGROUND_IMAGE_0;
        screenStatus = TITLE_SCREEN;
        gameOver = false;
        playerWin = false;
        frenzyMode = false;
        highScore = 0;
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
        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        } else {

            if (screenStatus == TITLE_SCREEN) {
                if (input.wasPressed(Keys.SPACE)) {
                    resetGame();
                    background = BACKGROUND_IMAGE_0;
                    screenStatus = INSTRUCTION_0_SCREEN;
                }
                else if (input.isDown(Keys.O) && input.isDown(Keys.N) && input.isDown(Keys.E)) {
                    resetGame();
                    background = BACKGROUND_IMAGE_1;
                    screenStatus = INSTRUCTION_1_SCREEN;
                }
                else if (input.isDown(Keys.T) && input.isDown(Keys.W) && input.isDown(Keys.O)) {
                    resetGame();
                    background = BACKGROUND_IMAGE_2;
                    screenStatus = INSTRUCTION_2_SCREEN;
                }

            } else if (screenStatus == INSTRUCTION_0_SCREEN && input.wasPressed(Keys.SPACE)) {
                screenStatus = LEVEL_0;
            } else if (screenStatus == INSTRUCTION_1_SCREEN && input.wasPressed(Keys.SPACE)) {
                screenStatus = LEVEL_1;
            } else if (screenStatus == LVL_0_COMPLETE_SCREEN && levelCompleteFrameCount == COMPLETE_MESSAGE_FRAMES) {
                screenStatus = INSTRUCTION_1_SCREEN;
            } else if (screenStatus == INSTRUCTION_2_SCREEN && input.wasPressed(Keys.SPACE)) {
                screenStatus = LEVEL_2;
            } else if (screenStatus == LVL_1_COMPLETE_SCREEN && levelCompleteFrameCount == COMPLETE_MESSAGE_FRAMES) {
                screenStatus = INSTRUCTION_2_SCREEN;
            }

            background.draw(Window.getWidth() / 2.0, Window.getHeight() / 2.0);

            if (screenStatus == TITLE_SCREEN) {
                Message.titleScreen(GAME_TITLE, highScore);
            } else if (screenStatus == LVL_0_COMPLETE_SCREEN) {
                Message.levelComplete(0);
                levelCompleteFrameCount++;
            } else if (screenStatus == LVL_1_COMPLETE_SCREEN) {
                Message.levelComplete(1);
                levelCompleteFrameCount++;
            } else if (screenStatus == INSTRUCTION_0_SCREEN) {
                Message.instructionLevel0();
            } else if (screenStatus == INSTRUCTION_1_SCREEN) {
                Message.instructionLevel1();
            } else if (screenStatus == INSTRUCTION_2_SCREEN) {
                Message.instructionLevel2();
            } else if (screenStatus == LEVEL_0 && level0.getPlayer().getPlayerScore() >= TARGET_SCORE_LVL_0) {
                Player.setTotalScore(Player.getTotalScore() + level0.getPlayer().getPlayerScore());
                screenStatus = LVL_0_COMPLETE_SCREEN;
                levelCompleteFrameCount = 0;
            } else if (screenStatus == LEVEL_1 && level1.getPlayer().getPlayerScore() >= TARGET_SCORE_LVL_1) {
                Player.setTotalScore(Player.getTotalScore() + level1.getPlayer().getPlayerScore());
                screenStatus = LVL_1_COMPLETE_SCREEN;
                levelCompleteFrameCount = 0;
            } else if (gameOver) {
                Message.drawMessage(LOSE_MESSAGE);
                Message.finalScore(Player.getTotalScore());
                Message.returnToTitle();
                if (input.wasPressed(Keys.SPACE)) {
                    background = BACKGROUND_IMAGE_0;
                    screenStatus = TITLE_SCREEN;
                }

            } else if (playerWin) {
                Message.drawMessage(WIN_MESSAGE);
                Message.finalScore(Player.getTotalScore());
                Message.returnToTitle();
                if (input.wasPressed(Keys.SPACE)) {
                    background = BACKGROUND_IMAGE_0;
                    screenStatus = TITLE_SCREEN;
                }
            } else if (screenStatus == LEVEL_0) {
                playLevel(input, level0, 0, TARGET_SCORE_LVL_0);
            } else if (screenStatus == LEVEL_1) {
                playLevel(input, level1, 1, TARGET_SCORE_LVL_1);
            } else {
                playLevel(input, level2, 2, TARGET_SCORE_LVL_2);
                if (level2.getPlayer().getPlayerScore() == TARGET_SCORE_LVL_2) {
                    Player.setTotalScore(Player.getTotalScore() + level2.getPlayer().getPlayerScore());
                    if (highScore < Player.getTotalScore()) {
                        highScore = Player.getTotalScore();
                    }
                    playerWin = true;
                }
            }
        }
    }

    /**
     * Method that plays a game level
     * given the input, the level, and if the ghosts move.
     */
    private void playLevel(Input input, Level level, int levelNum, int targetScore) {
        level.playerInput(input, frenzyMode);

        for (Pellet pellet : level.getPellets()) {
            if (pellet.collidesWith(level.getPlayer())) {
                frenzyMode = true;
                frenzyFrameCount = 0;
                level.getPellets().remove(pellet);
                break;
            }
        }
        if (frenzyMode) {
            frenzyFrameCount++;
        }

        for (Ghost ghost : level.getGhosts()) {
            if (ghost.isActive()) {
                ghost.move(level.getWalls(), frenzyMode);
                if (ghost.collidesWith(level.getPlayer())) {
                    if (frenzyMode) {
                        level.getPlayer().increaseScore(Ghost.FRENZY_SCORE);
                        ghost.setActive(false);
                    } else {
                        level.getPlayer().loseLife();
                        ghost.resetPosition();
                    }
                }
            }
        }

        for (Dot dot : level.getDots()) {
            if (dot.collidesWith(level.getPlayer())) {
                level.getPlayer().increaseScore(Dot.POINTS);
                level.getDots().remove(dot);
                break;
            }
        }
        for (Pizza pizza : level.getPizzas()) {
            if (pizza.collidesWith(level.getPlayer())) {
                level.getPlayer().increaseScore(Pizza.POINTS);
                Player.extraLife();
                level.getPizzas().remove(pizza);
                break;
            }
        }

        if (Player.hasLost()) {
            Player.setTotalScore(Player.getTotalScore() + level.getPlayer().getPlayerScore());
            if (highScore < Player.getTotalScore()) {
                highScore = Player.getTotalScore();
            }
            gameOver = true;
        } else {
            Message.renderLevel(levelNum, targetScore);
            level.getPlayer().update(input);

            for (Wall wall : level.getWalls()) {
                wall.update();
            }

            for (Dot dot : level.getDots()) {
                dot.update();
            }

            for (Pizza pizza : level.getPizzas()) {
                pizza.update();
            }

            for (Pellet pellet : level.getPellets()) {
                pellet.update();
            }

            for (Ghost ghost : level.getGhosts()) {
                if (ghost.isActive()) {
                    ghost.update(frenzyMode);
                }
            }

            if (frenzyFrameCount == FRENZY_MODE_FRAMES) {
                frenzyMode = false;
                frenzyFrameCount = 0;
                for (Ghost ghost : level.getGhosts()) {
                    if (!ghost.isActive()) {
                        ghost.resetPosition();
                    }
                    ghost.setActive(true);
                }
            }

        }
    }

    /**
     * Method that resets the game
     * after winning/losing
     */
    private void resetGame() {
        level0 = new Level(LEVEL_0_FILE);
        level1 = new Level(LEVEL_1_FILE);
        level2 = new Level(LEVEL_2_FILE);
        gameOver = false;
        playerWin = false;
        Player.setTotalScore(0);
    }
}
