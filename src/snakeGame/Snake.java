package snakeGame;

import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Point;

import java.awt.Color;

/**
 * Creates and manages movement of snake
 */
public class Snake {
    private static final double BODY_WIDTH = 15;

    private Head head;
    private List<GraphicsObject> body;
    private Color color;
    private GraphicsGroup parentGroup;
    private int bodyCollisionRadius;

    /**
     * Creates snake with head and 25 body parts
     * @param color
     * @param parentGroup
     */
    public Snake(Color color, GraphicsGroup parentGroup) {
        head = new Head(color);
        body = new ArrayList<>();
        parentGroup.add(head);

        this.color = color;
        this.parentGroup = parentGroup;
        makeLonger(25);
    }

    public List<GraphicsObject> getBodyGraphics() {
        return body;
    }

    public Head getHead() {
        return head;
    }

    /**
     * Checks if snake has passed canvas bounds
     * @return true when out of bounds
     * @return false when in bounds
     */
    public boolean wallCollision(double canvasWidth, double canvasHeight) {
        if (head.getShape().getX() < 0 || head.getShape().getX() + 30 > canvasWidth 
        || head.getShape().getY() < 0 || head.getShape().getY() + 30 > canvasHeight) {
            return true;
        }
        return false;
    }

    /**
     * Checks if snake head has hit snake body
     * @return true if snake head is in contact with body
     * @return false if no contact between head and body
     */
    public boolean bodyCollision() {
        bodyCollisionRadius = 0;
        double collisionDistance = (BODY_WIDTH/2) + 10;
        for (GraphicsObject part : body) {
            if (bodyCollisionRadius > 25) {
                if (part.getCenter().distance(head.getCenter()) < collisionDistance) {
                    return true;
                }
            }
            bodyCollisionRadius++;
        }
        return false;
    }
    
    /**
     * Adds @param total number of Ellipses to end of snake body,
     * if body is empty adds to snake head.
     */
    public void makeLonger(int total) {
        for (int i = 0; i < total; i++) {
            Ellipse part = new Ellipse(0, 0, BODY_WIDTH, BODY_WIDTH);
            if (!body.isEmpty()) {
                int index = body.size() - 1;
                Point newPosition = body.get(index).getCenter();
                part.setCenter(newPosition);
            }
            else {
                part.setCenter(head.getCenter());
            }
            part.setFillColor(color);
            part.setStrokeColor(color);
            body.add(part);
            parentGroup.add(part);
        } 
    }

    public void moveHead(CanvasWindow canvas) {
        head.moveAround(canvas);
    }

    /**
     * Makes each part in body follow the head, moving each ellipse to the previous
     * position of the ellipse in front of it.
     */
    public void moveBody() {
        Point newPosition = head.getCenter();
        for (GraphicsObject part : body) {
            Point oldPosition = part.getCenter();
            part.setCenter(newPosition);
            newPosition = oldPosition;
        } 
    }

    /**
     * When the snake loses a life, its position will be reset to the center of the screen.
     * @param canvas
     */
    public void resetSnakeOnDeath(CanvasWindow canvas) {
        head.getShape().setCenter(300, 300);
        for (GraphicsObject part : body) {
            part.setCenter(-10, -10);
        }
    }
}

