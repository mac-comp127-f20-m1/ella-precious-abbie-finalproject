package snakeGame;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.events.Key;

public class Head extends GraphicsGroup{
    private static final double WIDTH = 30;
    private GraphicsGroup head;
    private GraphicsGroup eye;
    private int directionX = 1;
    private int directionY = 0;
    private double x;
    private double y;
    
    public Head(double xPosition, double yPosition, Color color) {
        makeHead(xPosition, yPosition, color);
    }

    public GraphicsGroup makeEye(double x, double y) {
        GraphicsGroup eye = new GraphicsGroup();
        Ellipse iris = new Ellipse(x, y, WIDTH * 0.3, WIDTH * 0.3);
        iris.setFillColor(Color.white);
        iris.setStrokeColor(Color.black);
        Ellipse pupil = new Ellipse(x + WIDTH * 0.18, y + WIDTH * 0.07, WIDTH * 0.1, WIDTH * 0.1);
        pupil.setFillColor(Color.black);
        eye.add(iris);
        eye.add(pupil);
        return eye;
    }

    public void makeHead(double x, double y, Color color) {
        head = new GraphicsGroup();
        Ellipse headShape = new Ellipse(x, y, WIDTH, WIDTH);
        headShape.setFillColor(color);
        headShape.setStrokeColor(color);
        head.add(headShape);
        head.add(makeEye(x + WIDTH * 0.7, y + WIDTH * 0.1));
        head.add(makeEye(x + WIDTH * 0.7, y + WIDTH * 0.6));
        add(head);

    }

    public GraphicsGroup getHead() {
        return this.head;
    }

    public void moveAround(CanvasWindow canvas) {
        moveHeadHelper(canvas);
        head.moveBy(directionX,directionY);
    }

    public void moveHeadHelper(CanvasWindow canvas) {
        canvas.onKeyDown(event -> {
            if (event.getKey() == Key.LEFT_ARROW) {
                if (directionX == 0) {
                    directionX = -1;
                    directionY = 0;
                }
            }
            if (event.getKey() == Key.RIGHT_ARROW) {
                if (directionX == 0) {
                    directionX = 1;
                    directionY = 0;
                }
            }
            if (event.getKey() == Key.DOWN_ARROW) {
                if (directionY == 0) {
                    directionX = 0;
                    directionY = 1;
                }
            }
            if (event.getKey() == Key.UP_ARROW) {
                if (directionY == 0) {
                    directionX = 0;
                    directionY = -1;
                }
            }
        });
        
    }



}
