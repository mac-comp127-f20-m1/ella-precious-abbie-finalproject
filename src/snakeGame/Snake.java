package snakeGame;

import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;

import java.awt.Color;

public class Snake extends GraphicsGroup {
    private Head head;
    private List<BodyPart> body;
    private static final double SIDE_LENGTH = 10;
    private Color color;

    public Snake(double xPosition, double yPosition, Color color){
        head = new Head(xPosition, yPosition, color);
        body = new ArrayList<>();
        this.color = color;
    }

    public boolean checkWallCollision(double canvasSize) {
        if (getX() < 0 || getX() > canvasSize || getY() < 0 || getY() > canvasSize) {
            return true;
        }
        return false;
    }

    public boolean checkBodyCollision() {
        for (BodyPart part : body) {
            if (part.getCenter() == head.getCenter()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkMushroomCollision(CanvasWindow canvas) {
        if (canvas.getElementAt(head.getCenter()) != null && !checkBodyCollision()) {
            canvas.remove(getElementAt(head.getCenter()));
            makeLonger();
            return true;
        }
        return false;
    }

    public void makeLonger() {
        BodyPart part = new BodyPart(body.get(body.size()).getX() - SIDE_LENGTH, 
            body.get(body.size()).getY(), color);
        body.add(part);
        add(part);
    }



}

