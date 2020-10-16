package snakeGame;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BodyManager {
    private Map<Point, Boolean> turnPoints;
    private List<BodyPart> segments;

    private CanvasWindow canvas;

    public BodyManager(CanvasWindow canvas) { 
        turnPoints = new HashMap<>();
        segments = new ArrayList<>();
        this.canvas = canvas;
    }

    public void go() {
        canvas.animate(() -> {
            for (BodyPart part : segments) {
                if (turnPoints.containsKey(part.getPosition())) {
                    if(turnPoints.get(part.getPosition())) {
                        part.turnRight();
                    }
                    part.turnLeft();
                }
                part.moveForward();
            }
        });   
    }

    public void addPart() {

    }
    

}
