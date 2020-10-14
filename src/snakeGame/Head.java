package snakeGame;

import java.awt.Color;

import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;

public class Head extends GraphicsGroup{
    private static final double WIDTH = 20;
    
    public Head(double xPosition, double yPosition, Color color) {
        makeHead(xPosition, yPosition, color);
    }

    public GraphicsGroup makeEye(double x, double y) {
        GraphicsGroup eye = new GraphicsGroup();
        Ellipse iris = new Ellipse(x, y, WIDTH * 0.4, WIDTH * 0.4);
        iris.setFillColor(Color.white);
        iris.setStrokeColor(Color.white);
        Ellipse pupil = new Ellipse(x + WIDTH * 0.18, y + WIDTH * 0.07, WIDTH * 0.1, WIDTH * 0.1);
        pupil.setFillColor(Color.black);
        eye.add(iris);
        eye.add(pupil);
        return eye;
    }

    public void makeHead(double x, double y, Color color) {
        GraphicsGroup head = new GraphicsGroup();
        Ellipse headShape = new Ellipse(x, y, WIDTH, WIDTH);
        headShape.setFillColor(color);
        headShape.setStrokeColor(color);
        head.add(headShape);
        head.add(makeEye(x, y + WIDTH * 0.2));
        head.add(makeEye(x + WIDTH * 0.6, y + WIDTH * 0.2));
        add(head);

    }

}
