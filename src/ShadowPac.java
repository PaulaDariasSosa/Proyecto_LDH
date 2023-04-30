import java.io.*;
import bagel.*;
import bagel.util.Point;

/**
 * SWEN20003 Project 2B, Semester 1, 2023
 * Tung Khanh Ho
 */
public class ShadowPac extends AbstractGame  {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "SHADOW PAC";

    private final Image BACKGROUND_IMAGE = new Image("res/background0.png");

    private final static int TITLE_SCREEN = 0;
    private final static int LEVEL_0 = 1;
    private final static int LEVEL_COMPLETE_SCREEN = 2;
    private final static int INSTRUCTION_1_SCREEN = 3;
    private final static int LEVEL_1 = 4;
    private int screenStatus = TITLE_SCREEN;


    private final static Point FIRST_HEART_POINT = new Point(900, 10);
    private final Font scoreFont = new Font("res/FSO8BITR.ttf", 20);
    private final static Point SCORE_POINT = new Point(25, 25);

    // count the frame number to switch between open and closed images
    private int switchFrameCount = 0;
    private final static int SWITCH_FRAME = 15;

    private final static int COMPLETE_MESSAGE_FRAMES = 300;
    private int levelCompleteFrameCount = 0;

    private final static int MAX_SCORE_LVL_0 = 1210;
    private final static int MAX_SCORE_LVL_1 = 800;

    private final Level level0 = new Level();
    private final Level level1 = new Level();

    // Frenzy mode attributes
    private final static int FRENZY_MODE_FRAMES = 1000;
    private boolean frenzyMode = false;
    private int frenzyFrameCount;

    public ShadowPac(){
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
    }

    /**
     * Method used to read file and create objects (you can change
     * this method as you wish).
     */
    private void readCSV() {
        String text;
        try (BufferedReader br = new BufferedReader(new FileReader("res/level0.csv"))) {
            while ((text = br.readLine()) != null) {
                String[] cells = text.split(",");
                Point point = new Point(Integer.parseInt(cells[1]), Integer.parseInt(cells[2]));

                switch (cells[0]) {
                    case "Player":
                        level0.player = new Player(point);
                        break;
                    case "Wall":
                        level0.walls.add(new Wall(point));
                        break;
                    case "Ghost":
                        level0.ghosts.add(new GhostRed(point));
                        break;
                    default:
                        level0.dots.add(new Dot(point));
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader("res/level1.csv"))) {
            while ((text = br.readLine()) != null) {
                String[] cells = text.split(",");
                Point point = new Point(Integer.parseInt(cells[1]), Integer.parseInt(cells[2]));

                switch (cells[0]) {
                    case "Player":
                        level1.player = new Player(point);
                        break;
                    case "Wall":
                        level1.walls.add(new Wall(point));
                        break;
                    case "GhostRed":
                        level1.ghosts.add(new GhostRed(point));
                        break;
                    case "GhostBlue":
                        level1.ghosts.add(new GhostBlue(point));
                        break;
                    case "GhostGreen":
                        level1.ghosts.add(new GhostGreen(point));
                        break;
                    case "GhostPink":
                        level1.ghosts.add(new GhostPink(point));
                        break;
                    case "Cherry":
                        level1.cherries.add(new Cherry(point));
                        break;
                    case "Pellet":
                        level1.pellets.add(new Pellet(point));
                        break;
                    default:
                        level1.dots.add(new Dot(point));
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
                Message.levelCompleteScreen();
                levelCompleteFrameCount++;
            }

            else if (screenStatus == INSTRUCTION_1_SCREEN) {
                Message.instructionLevel1();
            }

            else if (screenStatus == LEVEL_0 && level0.player.getPlayerScore() >= MAX_SCORE_LVL_0) {
                screenStatus = LEVEL_COMPLETE_SCREEN;
            }
            else if (level0.player.hasLost()) {
                Message.lose();
            }
            else if (screenStatus == LEVEL_1 && level1.player.getPlayerScore() >= MAX_SCORE_LVL_1) {
                // player has won
                Message.win();
            }

            else if (screenStatus == LEVEL_0) {
                // Playing level 0
                playerInput(input, level0);

                for (Ghost ghost : level0.ghosts) {
                    if (ghost.collidesWith(level0.player)) {
                        level0.player.loseLife();
                        break;
                    }
                }

                for (Dot dot : level0.dots) {
                    if (dot.collidesWith(level0.player)) {
                        level0.dots.remove(dot);
                        level0.player.increaseScore(Dot.getScore());
                        break;
                    }
                }

                if (!level0.player.hasLost()) {
                    // Player still has more than 0 life:
                    // draw player, switch between opening and closing mouth every 15 frames
                    level0.player.draw(switchFrameCount, SWITCH_FRAME);

                    // draw stationary objects on screen
                    for (Wall wall : level0.walls) {
                        wall.draw();
                    }
                    for (Ghost ghost : level0.ghosts) {
                        ghost.draw(frenzyMode);
                    }
                    for (Dot dot : level0.dots) {
                        dot.draw();
                    }

                    // draw remaining lives and score
                    level0.player.drawLives(FIRST_HEART_POINT);
                    scoreFont.drawString("SCORE " + level0.player.getPlayerScore(), SCORE_POINT.x, SCORE_POINT.y);

                    switchFrameCount++;
                    if (switchFrameCount == SWITCH_FRAME * 2) {
                        switchFrameCount = 0;
                    }
                }
            }

            else {
                // Playing level 1
                playerInput(input, level1);

                for (Pellet pellet : level1.pellets) {
                    if (pellet.collidesWith(level1.player)) {
                        frenzyMode = true;
                        frenzyFrameCount = 0;
                        level1.pellets.remove(pellet);
                        break;
                    }
                }
                if (frenzyMode) {
                    frenzyFrameCount++;
                }

                for (Ghost ghost : level1.ghosts) {
                    if (!ghost.isEaten()) {
                        ghost.move(level1.walls, frenzyMode);
                        if (ghost.collidesWith(level1.player)) {
                            if (frenzyMode) {
                                level1.player.increaseScore(Ghost.getScore());
                                ghost.setEaten(true);
                            } else {
                                level1.player.loseLife();
                                ghost.resetPosition();
                            }
                        }
                    }
                }

                for (Dot dot : level1.dots) {
                    if (dot.collidesWith(level1.player)) {
                        level1.player.increaseScore(Dot.getScore());
                        level1.dots.remove(dot);
                        break;
                    }
                }
                for (Cherry cherry : level1.cherries) {
                    if (cherry.collidesWith(level1.player)) {
                        level1.player.increaseScore(Cherry.getScore());
                        level1.cherries.remove(cherry);
                        break;
                    }
                }

                if (!level1.player.hasLost()) {
                    // Player still has more than 0 life:
                    // draw player, switch between opening and closing mouth every 15 frames
                    level1.player.draw(switchFrameCount, SWITCH_FRAME);

                    // draw stationary objects on screen
                    for (Wall wall : level1.walls) {
                        wall.draw();
                    }
                    for (Dot dot : level1.dots) {
                        dot.draw();
                    }
                    for (Cherry cherry : level1.cherries) {
                        cherry.draw();
                    }
                    for (Pellet pellet : level1.pellets) {
                        pellet.draw();
                    }
                    for (Ghost ghost : level1.ghosts) {
                        if (!ghost.isEaten()) {
                            ghost.draw(frenzyMode);
                        }
                    }

                    // draw remaining lives and score
                    level1.player.drawLives(FIRST_HEART_POINT);
                    scoreFont.drawString("SCORE " + level1.player.getPlayerScore(), SCORE_POINT.x, SCORE_POINT.y);

                    switchFrameCount++;
                    if (switchFrameCount == SWITCH_FRAME * 2) {
                        switchFrameCount = 0;
                    }
                    if (frenzyFrameCount == FRENZY_MODE_FRAMES) {
                        frenzyMode = false;
                        frenzyFrameCount = 0;
                        for (Ghost ghost : level1.ghosts) {
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
            level.player.goLeft(level.walls, frenzyMode);
        }
        else if (input.isDown(Keys.RIGHT)) {
            level.player.goRight(level.walls, frenzyMode);
        }
        else if (input.isDown(Keys.UP)) {
            level.player.goUp(level.walls, frenzyMode);
        }
        else if (input.isDown(Keys.DOWN)) {
            level.player.goDown(level.walls, frenzyMode);
        }
    }
}
