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
        body.add(head);
        
        this.color = color;
        this.canvas = canvas;
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
   
    public void moveHead() {
        head.moveAround(canvas);
    }

}

