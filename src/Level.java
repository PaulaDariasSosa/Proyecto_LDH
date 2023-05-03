import java.util.ArrayList;
import bagel.util.Point;

public class Level {
    private Player player;
    private final ArrayList<Wall> walls = new ArrayList<>();
    private final ArrayList<Ghost> ghosts = new ArrayList<>();
    private final ArrayList<Dot> dots = new ArrayList<>();
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

    public void addGhost(Ghost ghost) {
        ghosts.add(ghost);
    }

    public ArrayList<Ghost> getGhosts() {
        return ghosts;
    }

    public void addDot(Dot dot) {
        dots.add(dot);
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
