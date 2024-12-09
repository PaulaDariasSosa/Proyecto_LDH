package es.ull.app;

import bagel.*;
import utilities.FileUtils;

/**
 * @brief The es.ull.app.ShadowPac class is the main class of the game.
 */
public class ShadowPac extends AbstractGame {
    private static final int WINDOW_WIDTH = 1024;
    private static final int WINDOW_HEIGHT = 768;
    private static final String GAME_TITLE = "SHADOW TUKA";
    private static final String LEVEL_0_FILE = "res/level0.csv";
    private static final String LEVEL_1_FILE = "res/level1.csv";
    private static final String LEVEL_2_FILE = "res/level2.csv";

    private final Image TITLE_IMAGE = new Image("res/title.png");
    private final Image LEVEL0_IMAGE = new Image("res/level0.png");
    private final Image LEVEL1_IMAGE = new Image("res/level1.png");
    private final Image LEVEL2_IMAGE = new Image("res/level2.png");
    private final Image WIN_IMAGE = new Image("res/win.png");
    private final Image LOSE_IMAGE = new Image("res/lose.png");
    private final Image TIMESUP_IMAGE = new Image("res/timesUp.png");
    private final Image EXPLOSION_IMAGE = new Image("res/explosion.png");
    private final Image TIME_FREEZE_IMAGE = new Image("res/zawarudo.png");
    private Image background;

    private static final int TITLE_SCREEN = 0;
    private static final int INSTRUCTION_0_SCREEN = 1;
    private static final int LEVEL_0 = 2;
    private static final int LVL_0_COMPLETE_SCREEN = 3;
    private static final int INSTRUCTION_1_SCREEN = 4;
    private static final int LEVEL_1 = 5;
    private static final int LVL_1_COMPLETE_SCREEN = 6;
    private static final int INSTRUCTION_2_SCREEN = 7;
    private static final int LEVEL_2 = 8;
    private int screenStatus;
    private boolean gameOver;
    private boolean playerWin;
    private boolean timesUp;

    private static final int COMPLETE_MESSAGE_FRAMES = 150;
    private int levelCompleteFrameCount;
    private static final String LVL_1_CODE = "TUKA";
    private static final String LVL_2_CODE = "MANK";

    private static final int TARGET_SCORE_LVL_0 = 1210;
    private static final int TARGET_SCORE_LVL_1 = 1230;
    private static final int TARGET_SCORE_LVL_2 = 1250;
    public static final int MAX_SCORE = TARGET_SCORE_LVL_0 + TARGET_SCORE_LVL_1 + TARGET_SCORE_LVL_2;

    private static final int EXPLOSION_FRAMES = 200;
    private int explosionFrameCount;
    private boolean exploding;

    private static final int FRENZY_MODE_FRAMES = 500;
    private int frenzyFrameCount;
    private boolean frenzyMode;

    private static final int TIME_FROZEN_FRAMES = 300;
    private int timeFrozenFrameCount;
    private boolean timeFrozen;

    private int highScore;
    private User nombreUsuario;

    private Level level0;
    private Level level1;
    private Level level2;

    public ShadowPac() {
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
        background = TITLE_IMAGE;
        screenStatus = TITLE_SCREEN;
        gameOver = false;
        playerWin = false;
        timesUp = false;
        exploding = false;
        frenzyMode = false;
        timeFrozen = false;
        highScore = 0;
    }
    public ShadowPac(User nombre) {
        this();
        nombreUsuario = nombre;
    }

    /**
     * @brief The entry point for the program.
     * @param args The command-line arguments
     */
    public static void main(String[] args) {
        User usuario = new User();
        usuario.askForName();
        ShadowPac game = new ShadowPac(usuario);
        game.run();
    }

    /**
     * @brief Performs a state update. Allows the game to exit when the escape key is pressed.
     * @param input The input object
     */
    @Override
    public void update(Input input) {
        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        } else {

            if (screenStatus == TITLE_SCREEN) {
                if (input.wasPressed(Keys.SPACE)) {
                    resetGame();
                    background = LEVEL0_IMAGE;
                    screenStatus = INSTRUCTION_0_SCREEN;
                } else if (input.isDown(Keys.T) && input.isDown(Keys.U) &&
                        input.isDown(Keys.K) && input.isDown(Keys.A)) {
                    resetGame();
                    background = LEVEL1_IMAGE;
                    screenStatus = INSTRUCTION_1_SCREEN;
                } else if (input.isDown(Keys.M) && input.isDown(Keys.A) &&
                        input.isDown(Keys.N) && input.isDown(Keys.K)) {
                    resetGame();
                    background = LEVEL2_IMAGE;
                    screenStatus = INSTRUCTION_2_SCREEN;
                }

            } else if (screenStatus == INSTRUCTION_0_SCREEN && input.wasPressed(Keys.SPACE)) {
                screenStatus = LEVEL_0;
            } else if (screenStatus == LVL_0_COMPLETE_SCREEN &&
                    levelCompleteFrameCount == COMPLETE_MESSAGE_FRAMES) {
                exploding = false;
                frenzyMode = false;
                timeFrozen = false;
                background = LEVEL1_IMAGE;
                screenStatus = INSTRUCTION_1_SCREEN;
            } else if (screenStatus == INSTRUCTION_1_SCREEN &&
                    input.wasPressed(Keys.SPACE)) {
                screenStatus = LEVEL_1;
            } else if (screenStatus == LVL_1_COMPLETE_SCREEN &&
                    levelCompleteFrameCount == COMPLETE_MESSAGE_FRAMES) {
                exploding = false;
                frenzyMode = false;
                timeFrozen = false;
                background = LEVEL2_IMAGE;
                screenStatus = INSTRUCTION_2_SCREEN;
            } else if (screenStatus == INSTRUCTION_2_SCREEN && input.wasPressed(Keys.SPACE)) {
                screenStatus = LEVEL_2;
            }

            background.draw(Window.getWidth() / 2.0, Window.getHeight() / 2.0);

            if (screenStatus == TITLE_SCREEN) {
                Message.titleScreen(GAME_TITLE, highScore);
            } else if (screenStatus == LVL_0_COMPLETE_SCREEN) {
                Message.levelComplete(0, LVL_1_CODE);
                levelCompleteFrameCount++;
            } else if (screenStatus == LVL_1_COMPLETE_SCREEN) {
                Message.levelComplete(1, LVL_2_CODE);
                levelCompleteFrameCount++;
            } else if (screenStatus == INSTRUCTION_0_SCREEN) {
                Message.instructionLevel0();
            } else if (screenStatus == INSTRUCTION_1_SCREEN) {
                Message.instructionLevel1();
            } else if (screenStatus == INSTRUCTION_2_SCREEN) {
                Message.instructionLevel2();
            } else if (screenStatus == LEVEL_0 &&
                    level0.getPlayer().getPlayerScore() >= TARGET_SCORE_LVL_0) {
                Player.setTotalScore(Player.getTotalScore() + level0.getPlayer().getPlayerScore());
                screenStatus = LVL_0_COMPLETE_SCREEN;
                levelCompleteFrameCount = 0;
            } else if (screenStatus == LEVEL_1 &&
                    level1.getPlayer().getPlayerScore() >= TARGET_SCORE_LVL_1) {
                Player.setTotalScore(Player.getTotalScore() + level1.getPlayer().getPlayerScore());
                screenStatus = LVL_1_COMPLETE_SCREEN;
                levelCompleteFrameCount = 0;
            } else if (timesUp) {
                Message.timesUp();
                if (input.wasPressed(Keys.SPACE)) {
                    background = TITLE_IMAGE;
                    screenStatus = TITLE_SCREEN;
                }
            } else if (gameOver) {
                Message.loseScreen();
                if (input.wasPressed(Keys.SPACE)) {
                    background = TITLE_IMAGE;
                    screenStatus = TITLE_SCREEN;
                }
            } else if (playerWin) {
                Message.winScreen();
                if (input.wasPressed(Keys.SPACE)) {
                    background = TITLE_IMAGE;
                    screenStatus = TITLE_SCREEN;
                }
            } else if (screenStatus == LEVEL_0) {
                playLevel(input, level0, 0, TARGET_SCORE_LVL_0);
            } else if (screenStatus == LEVEL_1) {
                playLevel(input, level1, 1, TARGET_SCORE_LVL_1);
            } else {
                playLevel(input, level2, 2, TARGET_SCORE_LVL_2);
                if (level2.getPlayer().getPlayerScore() >= TARGET_SCORE_LVL_2) {
                    Player.setTotalScore(Player.getTotalScore() + level2.getPlayer().getPlayerScore());
                    if (highScore < Player.getTotalScore()) {
                        highScore = Player.getTotalScore();
                        FileUtils.saveScore(nombreUsuario.getName(), highScore);
                    }
                    playerWin = true;
                    background = WIN_IMAGE;
                }
            }
        }
    }

    /**
     * @brief Method that plays a game level given the input, the level, and if the ghosts move.
     * @param input The input object
     * @param level The level object
     * @param levelNum The level number
     * @param targetScore The target score for the level
     */
    private void playLevel(Input input, Level level, int levelNum, int targetScore) {
        level.playerInput(input, frenzyMode);

        for (Bomb bomb : level.getBombs()) {
            if (bomb.collidesWith(level.getPlayer())) {
                level.getBombs().remove(bomb);
                exploding = true;
                frenzyMode = false;
                explosionFrameCount = 0;
                for (Ghost ghost : level.getGhosts()) {
                    ghost.setActive(false);
                }
                break;
            }
        }
        if (exploding) {
            explosionFrameCount++;
        }

        for (Star star : level.getPellets()) {
            if (star.collidesWith(level.getPlayer())) {
                frenzyMode = true;
                frenzyFrameCount = 0;
                level.getPellets().remove(star);
                break;
            }
        }
        if (frenzyMode) {
            frenzyFrameCount++;
        }

        for (TimeFreeze timeFreeze : level.getTimeFreezes()) {
            if (timeFreeze.collidesWith(level.getPlayer())) {
                timeFrozen = true;
                timeFrozenFrameCount = 0;
                level.getTimeFreezes().remove(timeFreeze);
                break;
            }
        }
        if (timeFrozen) {
            TIME_FREEZE_IMAGE.draw(Window.getWidth() / 2.0, Window.getHeight() / 2.0);
            timeFrozenFrameCount++;
        }

        for (Shield shield : level.getShields()) {
            if (shield.collidesWith(level.getPlayer())) {
                level.getShields().remove(shield);
                level.getPlayer().shieldOn();
                break;
            }
        }

        for (Ghost ghost : level.getGhosts()) {
            if (ghost.isActive()) {
                if (!timeFrozen) {
                    ghost.move(level.getWalls(), frenzyMode);
                }
                if (level.getPlayer().isActive() && ghost.collidesWith(level.getPlayer())) {
                    if (frenzyMode) {
                        level.getPlayer().increaseScore(Ghost.FRENZY_SCORE);
                        ghost.setActive(false);
                    } else {
                        level.getPlayer().collidesGhost();
                        if (!timeFrozen) {
                            ghost.startRespawn();
                        }
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
                FileUtils.saveScore(nombreUsuario.getName(), highScore);
            }
            gameOver = true;
            background = LOSE_IMAGE;
        } else if (level.getTimer().timesUp()) {
            Player.setTotalScore(Player.getTotalScore() + level.getPlayer().getPlayerScore());
            if (highScore < Player.getTotalScore()) {
                highScore = Player.getTotalScore();
                FileUtils.saveScore(nombreUsuario.getName(), highScore);
            }
            timesUp = true;
            background = TIMESUP_IMAGE;
        } else {
            Message.renderLevel(levelNum, targetScore);

            level.getPlayer().update(input);

            level.getTimer().update(timeFrozen);

            for (Wall wall : level.getWalls()) {
                wall.update();
            }

            for (Dot dot : level.getDots()) {
                dot.update();
            }

            for (Shield shield : level.getShields()) {
                shield.update();
            }

            for (Pizza pizza : level.getPizzas()) {
                pizza.update();
            }

            for (Star star : level.getPellets()) {
                star.update();
            }

            for (Bomb bomb : level.getBombs()) {
                bomb.update();
            }

            for (TimeFreeze timeFreezes : level.getTimeFreezes()) {
                timeFreezes.update();
            }

            for (Ghost ghost : level.getGhosts()) {
                if (ghost instanceof GhostTuka) {
                    ghost.update(frenzyMode, timeFrozen);
                } else {
                    ghost.update(frenzyMode);
                }
            }

            if (exploding) {
                EXPLOSION_IMAGE.draw(WINDOW_WIDTH / 2.0, WINDOW_HEIGHT / 2.0);
            }
            if (explosionFrameCount == EXPLOSION_FRAMES) {
                exploding = false;
                explosionFrameCount = 0;
                for (Ghost ghost : level.getGhosts()) {
                    ghost.startRespawn();
                }
            }

            if (frenzyFrameCount == FRENZY_MODE_FRAMES) {
                frenzyMode = false;
                frenzyFrameCount = 0;
                for (Ghost ghost : level.getGhosts()) {
                    if (!ghost.isActive()) {
                        ghost.setActive(true);
                        ghost.resetPosition();
                    }
                }
            }

            if (timeFrozenFrameCount == TIME_FROZEN_FRAMES) {
                timeFrozen = false;
                timeFrozenFrameCount = 0;
            }

        }
    }

    /**
     * @brief Method that resets the game after finishing a game
     */
    public void resetGame() {
        level0 = new Level(LEVEL_0_FILE);
        level1 = new Level(LEVEL_1_FILE);
        level2 = new Level(LEVEL_2_FILE);
        gameOver = false;
        playerWin = false;
        timesUp = false;
        Player.setTotalScore(0);
    }

    public int getPlayerScore() {
        return Player.getTotalScore();
    }
}
