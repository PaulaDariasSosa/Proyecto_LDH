package es.ull.app;

import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

/**
 * @brief Abstract class that represents a game entity
 */
public abstract class GameEntity {
    private Image image;
    private Point position;

    /**
     * @brief Constructor for the es.ull.app.GameEntity class
     * @param topLeft
     */
    public GameEntity(Point topLeft) {
        position = new Point(topLeft.x, topLeft.y);
    }

    /**
     * @brief Getter for the image field
     * @return
     */
    public Image getImage() {
        return image;
    }

    /**
     * @brief Method that performs state update
     */
    public void update() {
        image.drawFromTopLeft(position.x, position.y);
    }

    /**
     * @brief Checks if the object collides with the Moving entity
     */
    public boolean collidesWith(MovingEntity entity) {
        Rectangle rectangleGo = new Rectangle(entity.getPointGo(), entity.getImage().getWidth(),
                entity.getImage().getHeight());
        return rectangleGo.intersects(this.getRectangle());
    }

    /**
     * @brief Setter for the image field
     * @param image
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * @brief Getter for the position field
     * @return The position of the es.ull.app.GameEntity
     */
    public Point getPosition() {
        return position;
    }

    /**
     * @brief Setter for the position field
     * @param position
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * @brief Getter for the rectangle of the es.ull.app.GameEntity
     * @return The rectangle of the es.ull.app.GameEntity
     */
    public Rectangle getRectangle() {
        return new Rectangle(position, image.getWidth(), image.getHeight());
    }

}
