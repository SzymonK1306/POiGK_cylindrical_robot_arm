package RobotApp;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import java.util.Enumeration;

public class CollisionDetector extends Behavior {
    private static final Color3f highlightColor =
            new Color3f(0.0f, 1.0f, 0.0f);
    private static final ColoringAttributes highlight =
            new ColoringAttributes(highlightColor,
                    ColoringAttributes.SHADE_GOURAUD);

    private boolean inCollision = false;
    private Shape3D shape;
    private ColoringAttributes shapeColoring;
    private Appearance shapeAppearance;

    private WakeupOnCollisionEntry wEnter;
    private WakeupOnCollisionExit wExit;


    public CollisionDetector(Shape3D s) {
        shape = s;
        shapeAppearance = shape.getAppearance();
        shapeColoring = shapeAppearance.getColoringAttributes();
        inCollision = false;
    }

    public void initialize() {
        wEnter = new WakeupOnCollisionEntry(shape);
        wExit = new WakeupOnCollisionExit(shape);
        wakeupOn(wEnter);
    }

    public void processStimulus(Enumeration criteria) {
        inCollision = !inCollision;

        if (inCollision) {
            System.out.println("Collision");
            wakeupOn(wExit);
        }
        else {
            System.out.println("Not Collision");
            wakeupOn(wEnter);
        }
    }
    public boolean getInCollision(){
        return inCollision;
    }
}
