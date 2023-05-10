import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import bagel.Input;
import bagel.Keys;
import bagel.util.Point;

public class Level {
    private Player player;
    private final ArrayList<Wall> walls = new ArrayList<>();
    private final ArrayList<Ghost> ghosts = new ArrayList<>();
    private final ArrayList<Dot> dots = new ArrayList<>();
    private final ArrayList<Cherry> cherries = new ArrayList<>();
    private final ArrayList<Pellet> pellets = new ArrayList<>();

    public Level(String worldFile) {
        readCSV(worldFile);
    }

    /**
     * Method used to read file and create objects
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
                    case "Wall":
                        walls.add(new Wall(point));
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
                    case "Cherry":
                        cherries.add(new Cherry(point));
                        break;
                    case "Pellet":
                        pellets.add(new Pellet(point));
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
     * Method that moves the player given the input and frenzy mode
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

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public ArrayList<Ghost> getGhosts() {
        return ghosts;
    }

    public ArrayList<Dot> getDots() {
        return dots;
    }

    public ArrayList<Cherry> getCherries() {
        return cherries;
    }

    public ArrayList<Pellet> getPellets() {
        return pellets;
    }
}
