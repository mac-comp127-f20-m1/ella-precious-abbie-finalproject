package snakeGame;

import edu.macalester.graphics.Rectangle;

public class BodyPart extends Rectangle {
    private static final double SIDE_LENGTH = 10;

    // public BodyPart(double xPosition, double yPosition) {
    //     this.xPosition = xPosition;
    //     this.yPosition = yPosition;
    //     bodyPart = new Rectangle(xPosition, yPosition, SIDE_LENGTH, SIDE_LENGTH);
    // }

    public BodyPart(double xPosition, double yPosition) {
        this.setPosition(xPosition, yPosition);
        this.setSize(SIDE_LENGTH, SIDE_LENGTH);
    }




    
}
