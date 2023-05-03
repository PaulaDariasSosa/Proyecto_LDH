import java.util.ArrayList;
import bagel.util.Point;

public class Level {
    private Player player;
    private final ArrayList<Wall> walls = new ArrayList<>();
    public final ArrayList<Ghost> ghosts = new ArrayList<>();
    public final ArrayList<Dot> dots = new ArrayList<>();
    public final ArrayList<Cherry> cherries = new ArrayList<>();
    public final ArrayList<Pellet> pellets = new ArrayList<>();

    public void createPlayer(Point point) {
        player = new Player(point);
    }

    public Player getPlayer() {
        return player;
    }

    public void addWall(Wall wall) {
        walls.add(wall);
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
