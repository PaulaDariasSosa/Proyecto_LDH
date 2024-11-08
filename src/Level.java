import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import bagel.Input;
import bagel.Keys;
import bagel.util.Point;

/**
 * @brief Class that represents a level
 */
public class Level {
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
                    case "Player":
                        player = new Player(point);
                        break;
                    case "Bus":
                        walls.add(new Bus(point));
                        break;
                    case "Tram":
                        walls.add(new Tram(point));
                        break;
                    case "Train":
                        walls.add(new Train(point));
                        break;
                    case "GhostRed":
                        ghosts.add(new GhostRed(point));
                        break;
                    case "GhostBlue":
                        ghosts.add(new GhostBlue(point));
                        break;
                    case "GhostGreen":
                        ghosts.add(new GhostGreen(point));
                        break;
                    case "GhostPink":
                        ghosts.add(new GhostPink(point));
                        break;
                    case "GhostTuka":
                        ghosts.add(new GhostTuka(point));
                        break;
                    case "Shield":
                        shields.add(new Shield(point));
                        break;
                    case "Pizza":
                        pizzas.add(new Pizza(point));
                        break;
                    case "Star":
                        stars.add(new Star(point));
                        break;
                    case "Bomb":
                        bombs.add(new Bomb(point));
                        break;
                    case "TimeFreeze":
                        timeFreezes.add(new TimeFreeze(point));
                        break;
                    default:
                        dots.add(new Dot(point));
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
     * @return Timer
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * @brief Method that get the player
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @brief Method that get the walls
     * @return ArrayList<Wall>
     */
    public ArrayList<Wall> getWalls() {
        return walls;
    }

    /**
     * @brief Method that get the ghosts
     * @return ArrayList<Ghost>
     */
    public ArrayList<Ghost> getGhosts() {
        return ghosts;
    }

    /**
     * @brief Method that get the shields
     * @return ArrayList<Shield>
     */
    public ArrayList<Shield> getShields() {
        return shields;
    }

    /**
     * @brief Method that get the dots
     * @return ArrayList<Dot>
     */
    public ArrayList<Dot> getDots() {
        return dots;
    }

    /**
     * @brief Method that get the pizzas
     * @return ArrayList<Pizza>
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * @brief Method that get the pellets
     * @return ArrayList<Star>
     */
    public ArrayList<Star> getPellets() {
        return stars;
    }

    /**
     * @brief Method that get the bombs
     * @return ArrayList<Bomb>
     */
    public ArrayList<Bomb> getBombs() {
        return bombs;
    }

    /**
     * @brief Method that get the time freezes
     * @return ArrayList<TimeFreeze>
     */
    public ArrayList<TimeFreeze> getTimeFreezes() {
        return timeFreezes;
    }

}
