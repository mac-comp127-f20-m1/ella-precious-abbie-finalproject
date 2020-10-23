package snakeGame;

import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Point;

import java.awt.Color;

public class Snake {
    private static final double BODY_WIDTH = 15;

    private Head head;
    private List<GraphicsObject> body;
    private Color color;
    private GraphicsGroup parentGroup;
    private int bodyCollisionRadius;

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

    public boolean wallCollision(double canvasWidth, double canvasHeight) {
        if (head.getShape().getX() < 0 || head.getShape().getX() + 30 > canvasWidth 
        || head.getShape().getY() < 0 || head.getShape().getY() + 30 > canvasHeight) {
            return true;
        }
        return false;
    }

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

