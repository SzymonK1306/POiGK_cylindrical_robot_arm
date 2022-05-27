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

    RotationInterpolator obrotFi;

    private TransformGroup fiTransform;

    public BoundingSphere bounds;

    public GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();

    public Canvas3D canvas3D = new Canvas3D(config);
    public SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

    private float fi=1.67f;

    class Task extends TimerTask {

        @Override
        public void run() {
            obrotFi.setMaximumAngle(fi);
            obrotFi.setMinimumAngle(fi);
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
        ViewingPlatform viewingPlatform = simpleU.getViewingPlatform();

        PlatformGeometry pg = new PlatformGeometry();

        viewingPlatform.setPlatformGeometry(pg);

        viewingPlatform.setNominalViewingTransform();


        OrbitBehavior orbit = new OrbitBehavior(canvas3D, OrbitBehavior.REVERSE_ROTATE | OrbitBehavior.STOP_ZOOM);
        orbit.setReverseTranslate(true);
        orbit.setMinRadius(1.0);
        orbit.setRotationCenter(new Point3d(0.0f, 0.0f, 0.0f));
        orbit.setRotFactors(0.5, 0.5);
        orbit.setTransFactors(0.25, 0.25);
        orbit.setZoomFactor(0.25);
        orbit.setSchedulingBounds(bounds);
        viewingPlatform.setViewPlatformBehavior(orbit);


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

        // Światło
        AmbientLight Swiatlo = new AmbientLight();
        Swiatlo.setInfluencingBounds(bounds);
        wezel_scena.addChild(Swiatlo);


        // os pionowa robota
        Appearance  wygladRobota = new Appearance();
        wygladRobota.setColoringAttributes(new ColoringAttributes(0.6f,0.5f,0.9f,ColoringAttributes.NICEST));

        Cylinder osZ = new Cylinder(0.05f,1.3f, wygladRobota);

        Transform3D punktOsiZ = new Transform3D();
        punktOsiZ.set(new Vector3f(0f,-0.2f,0.0f));

        TransformGroup transformacjaOsiZ = new TransformGroup(punktOsiZ);
        transformacjaOsiZ.addChild(osZ);
        wezel_scena.addChild(transformacjaOsiZ);

        // os pozioma robota
        animationFi = new Alpha(-1,5000);

        TransformGroup transformacjaFi = new TransformGroup();
        transformacjaFi.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        wezel_scena.addChild(transformacjaFi);

        Transform3D tmp = new Transform3D();
        tmp.set(new Vector3f(0f,1f,0f));
        obrotFi = new RotationInterpolator(animationFi, transformacjaFi, tmp, 0, 0);
        BoundingSphere Wiezy = new BoundingSphere(new Point3d(0.0, -0.2, 0.0), 1.0);
        obrotFi.setSchedulingBounds(Wiezy);
        transformacjaFi.addChild(obrotFi);

        Box arm = new Box(0.3f,0.05f,0.1f,wygladRobota);

        Transform3D armPoint = new Transform3D();
        armPoint.set(new Vector3f(0.3f,-0.78f,0.0f));

        TransformGroup armTransform = new TransformGroup(armPoint);
        armTransform.addChild(arm);
        transformacjaFi.addChild(armTransform);
        //wezel_scena.addChild(armTransform);

        // obrot fi



         return wezel_scena;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println(fi);
            fi-=0.02;
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
