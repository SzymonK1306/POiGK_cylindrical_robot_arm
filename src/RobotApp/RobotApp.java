package RobotApp;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;

import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.universe.SimpleUniverse;
//import javafx.scene.shape.Cylinder;

import javax.media.j3d.Transform3D;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.ViewingPlatform;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;



public class RobotApp extends Applet implements KeyListener {
    BranchGroup scene;
    private java.util.Timer clock;

    Alpha animationFi;

    private Transform3D camera = new Transform3D();

    RotationInterpolator fiRotation;

    private TransformGroup fiTransform;

    BoundingSphere bounds;

    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();

    Canvas3D canvas3D = new Canvas3D(config);
    SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

    private float fi=0f;

    class Task extends TimerTask {

        @Override
        public void run() {
            fiRotation.setMaximumAngle(fi);
            fiRotation.setMinimumAngle(fi);
        }
    }


    RobotApp(){
        setLayout(new BorderLayout());
        canvas3D.setPreferredSize(new Dimension(1280, 960));
        canvas3D.addKeyListener(this);
        add(BorderLayout.CENTER, canvas3D);

        setVisible(true);

        scene = utworzScene();
        scene.compile();

        bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);

        // add mouse behaviors to the ViewingPlatform
        ViewingPlatform view = simpleU.getViewingPlatform();

        PlatformGeometry platform = new PlatformGeometry();

        view.setPlatformGeometry(platform);

        view.setNominalViewingTransform();


        OrbitBehavior orbit = new OrbitBehavior(canvas3D, OrbitBehavior.REVERSE_ROTATE | OrbitBehavior.STOP_ZOOM);
        orbit.setReverseTranslate(true);
        orbit.setMinRadius(1.0);
        orbit.setRotationCenter(new Point3d(0.0f, 0.0f, 0.0f));
        orbit.setRotFactors(0.5, 0.5);
        orbit.setTransFactors(0.25, 0.25);
        orbit.setZoomFactor(0.25);
        orbit.setSchedulingBounds(bounds);
        view.setViewPlatformBehavior(orbit);

        // Ensure at least 5 msec per frame (i.e., < 200Hz)
        simpleU.getViewer().getView().setMinimumFrameCycleTime(5);

        camera.set(new Vector3f(0f, 0f, 5f));
        simpleU.getViewingPlatform().getViewPlatformTransform().setTransform(camera);
        simpleU.addBranchGraph(scene);


        clock = new java.util.Timer();
        clock.scheduleAtFixedRate(new Task(), 0, 30);

    }

    BranchGroup utworzScene(){

        BranchGroup wezel_scena = new BranchGroup();

        // Lights
        AmbientLight Swiatlo = new AmbientLight();
        Swiatlo.setInfluencingBounds(bounds);
        wezel_scena.addChild(Swiatlo);


        // vertical robot axis
        Appearance  robotApperance = new Appearance();
        robotApperance.setColoringAttributes(new ColoringAttributes(0.6f,0.5f,0.9f,ColoringAttributes.NICEST));

        Cylinder zAxis = new Cylinder(0.05f,1.3f, robotApperance);

        Transform3D zAxisPoint = new Transform3D();
        zAxisPoint.set(new Vector3f(0f,-0.2f,0.0f));

        TransformGroup transformacjaOsiZ = new TransformGroup(zAxisPoint);
        transformacjaOsiZ.addChild(zAxis);
        wezel_scena.addChild(transformacjaOsiZ);

        // horizontal robot axis
        animationFi = new Alpha(-1,5000);

        TransformGroup transformFi = new TransformGroup();
        transformFi.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        wezel_scena.addChild(transformFi);

        Transform3D tmp = new Transform3D();
        tmp.set(new Vector3f(0f,1f,0f));
        fiRotation = new RotationInterpolator(animationFi, transformFi, tmp, 0, 0);
        BoundingSphere boundsFi = new BoundingSphere(new Point3d(0.0, -0.2, 0.0), 1.0);
        fiRotation.setSchedulingBounds(boundsFi);
        transformFi.addChild(fiRotation);

        Box arm = new Box(0.3f,0.05f,0.1f,robotApperance);

        Transform3D armPoint = new Transform3D();
        armPoint.set(new Vector3f(0.3f,-0.78f,0.0f));

        TransformGroup armTransform = new TransformGroup(armPoint);
        armTransform.addChild(arm);
        transformFi.addChild(armTransform);

         return wezel_scena;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println(fi);
            fi-=0.02;       // angle (radians)
        }
        if (e.getKeyCode()==KeyEvent.VK_D){
            System.out.println(fi);
            fi+=0.02;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String args[]){
        Frame Robot = new MainFrame(new RobotApp(), 1280, 960);
        Robot.setTitle("Cylindrical arm");
        Robot.setVisible(true);

    }
}
