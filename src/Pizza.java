import bagel.Image;
import bagel.util.Point;

public class Pizza extends GameEntity {
    private final static Image PIZZA_IMAGE = new Image("res/pizza.png");
    public final static int POINTS = 30;
    public Pizza(Point topLeft) {
        super(topLeft);
        setImage(PIZZA_IMAGE);
    }
}
