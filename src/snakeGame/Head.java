package snakeGame;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.Key;

public class Head extends GraphicsGroup {
    private static final double WIDTH = 30;
    private GraphicsGroup head;
    private GraphicsGroup eye;
    private int directionX = 1;
    private int directionY = 0;
    private Ellipse iris;
    private Ellipse pupil;


    public Head(Color color) {
        makeHead(0, 0, color);
    }

    public GraphicsGroup makeEye(double x, double y) {
        eye = new GraphicsGroup();
        iris = new Ellipse(x, y, WIDTH * 0.3, WIDTH * 0.3);
        iris.setFillColor(Color.white);
        iris.setStrokeColor(Color.black);
        pupil = new Ellipse(x + WIDTH * 0.18, y + WIDTH * 0.07, WIDTH * 0.1, WIDTH * 0.1);
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

    public GraphicsGroup getShape() {
        return this.head;
    }

    public void moveAround(CanvasWindow canvas) {
        moveAroundHelper(canvas);
        head.moveBy(directionX, directionY);
    }

    public void moveAroundHelper(CanvasWindow canvas) {
        canvas.onKeyDown(event -> {
            if (event.getKey() == Key.LEFT_ARROW) {
                if (directionX == 0 && directionY == 1) {
                    directionX = -1;
                    directionY = 0;
                    head.rotateBy(90);
                }
                else if (directionX == 0 && directionY == -1) {
                    directionX = -1;
                    directionY = 0;
                    head.rotateBy(270);
                }  
            }
            if (event.getKey() == Key.RIGHT_ARROW) {
                if (directionX == 0 && directionY == 1) {
                    directionX = 1;
                    directionY = 0;
                    head.rotateBy(270);
                }
                else if (directionX == 0 && directionY == -1) {
                    directionX = 1;
                    directionY = 0;
                    head.rotateBy(90);
                }
            }
            if (event.getKey() == Key.DOWN_ARROW) {
                if (directionY == 0 && directionX == 1) {
                    directionX = 0;
                    directionY = 1;
                    head.rotateBy(90);
                }
                else if (directionY == 0 && directionX == -1) {
                    directionX = 0;
                    directionY = 1;
                    head.rotateBy(270);
                }
            }
            if (event.getKey() == Key.UP_ARROW) {
                if (directionY == 0 && directionX == 1) {
                    directionX = 0;
                    directionY = -1;
                    head.rotateBy(270);
                }
                else if (directionY == 0 && directionX == -1) {
                    directionX = 0;
                    directionY = -1;
                    head.rotateBy(90);
                }

                
            }
        });

    }

    

    // public Mushroom checkHeadAndMushroomCollision(CanvasWindow canvas) {
    //     System.out.println("Head x                               " + head.getX());
    //     GraphicsObject leftCollisionElement = canvas.getElementAt(head.getX() - .0001, head.getY() + (WIDTH/2));
    //     GraphicsObject rightCollisionElement = canvas.getElementAt(head.getX() + WIDTH + .0001, head.getY() + (WIDTH/2));
    //     GraphicsObject topCollisionElement = canvas.getElementAt(head.getX() + (WIDTH/2), head.getY() - .0001);
    //     GraphicsObject bottomCollisionElement = canvas.getElementAt(head.getX() + (WIDTH/2), head.getY() + WIDTH + .0001);
    //     System.out.println("left                               " + leftCollisionElement);
    //     System.out.println("right                              " + rightCollisionElement);
    //     System.out.println("top                                " + topCollisionElement);
    //     System.out.println("bottom                             " + bottomCollisionElement);
    //     if (leftCollisionElement instanceof GraphicsGroup) {
    //         return (Mushroom) leftCollisionElement;
    //     }
    //     else if (rightCollisionElement instanceof GraphicsGroup) {
    //         return (Mushroom) rightCollisionElement;
    //     }
    //     else if (topCollisionElement instanceof GraphicsGroup) {
    //         return (Mushroom) topCollisionElement;
    //     }
    //     else if (bottomCollisionElement instanceof GraphicsGroup) {
    //         return (Mushroom) bottomCollisionElement;
    //     }
    //     return null;
    // }

}
