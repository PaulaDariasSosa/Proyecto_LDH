import bagel.util.Point;

/**
 * @brief Abstract class for Wall
 */
public abstract class Wall extends GameEntity {

    /**
     * @brief Constructor for Wall
     * @param topLeft
     */
    public Wall(Point topLeft) {
        super(topLeft);
    }

}
