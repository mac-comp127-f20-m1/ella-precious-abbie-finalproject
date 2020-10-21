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

    // public boolean bodyCollision() {
        // eatingDistance = (snakeHeadWidth + mushroom.getHeight()) / 2
        // if (mushroom.getCenter().distance(point) < eatingDistance) {

        // for (GraphicsObject part : body) {
        //     if (part > 5) {
        //         if (part.getCenter() == head.getCenter()) {
        //             return true;
        //         }
        //     }
        // }
        // return false;
    //}
   
    public void makeLonger(int total) {
        for (int i = 0; i < total; i++) {
            Ellipse part = new Ellipse(head.getCenter().getX(), head.getCenter().getY(), BODY_WIDTH, BODY_WIDTH);
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

    public void resetSnakeOnDeath() {
        head.setCenter(300, 300);
        for (GraphicsObject part : body) {
            part.setCenter(300, 300);
        }
        //moveBody();
    }

}

