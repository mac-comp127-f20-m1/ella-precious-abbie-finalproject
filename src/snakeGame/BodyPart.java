package snakeGame;

import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class BodyPart extends Rectangle {
    private static final double SIDE_LENGTH = 10;

    // public BodyPart(double xPosition, double yPosition) {
    //     this.xPosition = xPosition;
    //     this.yPosition = yPosition;
    //     bodyPart = new Rectangle(xPosition, yPosition, SIDE_LENGTH, SIDE_LENGTH);
    // }

    public BodyPart(double xPosition, double yPosition, Color color) {
       super(xPosition, yPosition, SIDE_LENGTH, SIDE_LENGTH);
       setColor(color);
    }

    public void setColor(Color color) {
        setFillColor(color);
        setStrokeColor(color);
    } 
}


