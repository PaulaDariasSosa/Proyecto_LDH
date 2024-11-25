package es.ull.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Logger;

import bagel.Input;
import bagel.Keys;
import bagel.util.Point;

/**
 * @brief Class that represents a level
 */
public class Level {
    private static final Logger logger = Logger.getLogger(utilities.FileUtils.class.getName());
    private final Timer timer = new Timer();
    private Player player;
    private final ArrayList<Wall> walls = new ArrayList<>();
    private final ArrayList<Ghost> ghosts = new ArrayList<>();
    private final ArrayList<Dot> dots = new ArrayList<>();
    private final ArrayList<Shield> shields = new ArrayList<>();
    private final ArrayList<Pizza> pizzas = new ArrayList<>();
    private final ArrayList<Star> stars = new ArrayList<>();
    private final ArrayList<Bomb> bombs = new ArrayList<>();
    private final ArrayList<TimeFreeze> timeFreezes = new ArrayList<>();

    public Level(String worldFile) {
        readCSV(worldFile);
    }

    /**
     * @brief Reads the CSV file and creates the objects
     * @param worldFile
     */
    private void readCSV(String worldFile) {
        String text;
        try (BufferedReader br = new BufferedReader(new FileReader(worldFile))) {
            while ((text = br.readLine()) != null) {
                String[] cells = text.split(",");
                Point point = new Point(Integer.parseInt(cells[1]), Integer.parseInt(cells[2]));

                switch (cells[0]) {
                    case "es.ull.app.Player":
                        player = new Player(point);
                        break;
                    case "es.ull.app.Bus":
                        walls.add(new Bus(point));
                        break;
                    case "es.ull.app.Tram":
                        walls.add(new Tram(point));
                        break;
                    case "es.ull.app.Train":
                        walls.add(new Train(point));
                        break;
                    case "es.ull.app.GhostRed":
                        ghosts.add(new GhostRed(point));
                        break;
                    case "es.ull.app.GhostBlue":
                        ghosts.add(new GhostBlue(point));
                        break;
                    case "es.ull.app.GhostGreen":
                        ghosts.add(new GhostGreen(point));
                        break;
                    case "es.ull.app.GhostPink":
                        ghosts.add(new GhostPink(point));
                        break;
                    case "es.ull.app.GhostTuka":
                        ghosts.add(new GhostTuka(point));
                        break;
                    case "es.ull.app.Shield":
                        shields.add(new Shield(point));
                        break;
                    case "es.ull.app.Pizza":
                        pizzas.add(new Pizza(point));
                        break;
                    case "es.ull.app.Star":
                        stars.add(new Star(point));
                        break;
                    case "es.ull.app.Bomb":
                        bombs.add(new Bomb(point));
                        break;
                    case "es.ull.app.TimeFreeze":
                        timeFreezes.add(new TimeFreeze(point));
                        break;
                    default:
                        dots.add(new Dot(point));
                        break;
                }
            }
        } catch (Exception e) {
            logger.severe("Error reading the file: " + e.getMessage());
        }
    }

    /**
     * @brief Method that moves the player given the input and frenzy mode
     * @param input
     * @param frenzyMode
     */
    public void playerInput(Input input, boolean frenzyMode) {
        if (input.isDown(Keys.LEFT)) {
            player.goLeft(walls, frenzyMode);
        } else if (input.isDown(Keys.RIGHT)) {
            player.goRight(walls, frenzyMode);
        } else if (input.isDown(Keys.UP)) {
            player.goUp(walls, frenzyMode);
        } else if (input.isDown(Keys.DOWN)) {
            player.goDown(walls, frenzyMode);
        }
    }

    /**
     * @brief Method that get the timer
     * @return es.ull.app.Timer
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * @brief Method that get the player
     * @return es.ull.app.Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @brief Method that get the walls
     * @return ArrayList<es.ull.app.Wall>
     */
    public ArrayList<Wall> getWalls() {
        return walls;
    }

    /**
     * @brief Method that get the ghosts
     * @return ArrayList<es.ull.app.Ghost>
     */
    public ArrayList<Ghost> getGhosts() {
        return ghosts;
    }

    /**
     * @brief Method that get the shields
     * @return ArrayList<es.ull.app.Shield>
     */
    public ArrayList<Shield> getShields() {
        return shields;
    }

    /**
     * @brief Method that get the dots
     * @return ArrayList<es.ull.app.Dot>
     */
    public ArrayList<Dot> getDots() {
        return dots;
    }

    /**
     * @brief Method that get the pizzas
     * @return ArrayList<es.ull.app.Pizza>
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * @brief Method that get the pellets
     * @return ArrayList<es.ull.app.Star>
     */
    public ArrayList<Star> getPellets() {
        return stars;
    }

    /**
     * @brief Method that get the bombs
     * @return ArrayList<es.ull.app.Bomb>
     */
    public ArrayList<Bomb> getBombs() {
        return bombs;
    }

    /**
     * @brief Method that get the time freezes
     * @return ArrayList<es.ull.app.TimeFreeze>
     */
    public ArrayList<TimeFreeze> getTimeFreezes() {
        return timeFreezes;
    }

}
