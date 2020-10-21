package snakeGame;

import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Point;

import java.awt.Color;

public class Snake {
    private static final double BODY_WIDTH = 15;

    private Head head;
    private List<GraphicsObject> body;
   
    private Color color;
    private CanvasWindow canvas;

    public Snake(Color color, CanvasWindow canvas) {
        head = new Head(color);
        body = new ArrayList<>();

        this.color = color;
        this.canvas = canvas;
        makeLonger(50);
    }

    public List<GraphicsObject> getBodyGraphics() {
        return body;
    }

    public Head getHead() {
        return head;
    }

    public boolean wallCollision(double canvasWidth, double canvasHeight) {
        if (head.getHead().getX() < 0 || head.getHead().getX() + 30 > canvasWidth 
        || head.getHead().getY() < 0 || head.getHead().getY() + 30 > canvasHeight) {
            return true;
        }
        return false;
    }

    public boolean bodyCollision() {
        for (GraphicsObject part : body) {
            if (part.getCenter() == head.getCenter()) {
                return true;
            }
        }
        return false;
    }
   
    public void makeLonger(int total) {
        for (int i = 0; i < total; i++) {
            Ellipse part = new Ellipse(0, 0, BODY_WIDTH, BODY_WIDTH);
            part.setFillColor(color);
            part.setStrokeColor(color);
            body.add(part);
        } 
    }

    public void moveHead() {
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

}

