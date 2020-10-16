package snakeGame;

import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.Line;
import java.awt.Color;

public class BodyPart extends Line {
    private static final double WIDTH = 10;

    public BodyPart(double x1, double y1, double x2, double y2, Color color) {
        super(x1, y1, x2, y2);
        setStrokeWidth(WIDTH);
        setStrokeColor(color);
    }

}

// public class BodyPart extends Rectangle {
//     private static final double SIDE_LENGTH = 10;


//     public BodyPart(double xPosition, double yPosition, Color color) {
//        super(xPosition, yPosition, SIDE_LENGTH, SIDE_LENGTH);
//        setColor(color);
//     }

//     public void setColor(Color color) {
//         setFillColor(color);
//         setStrokeColor(color);
//     } 


// }


