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
import java.lang.Math;
import javax.swing.*;

import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.universe.SimpleUniverse;
//import javafx.scene.shape.Cylinder;

import javax.media.j3d.Transform3D;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.ViewingPlatform;

import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;



public class RobotApp extends Applet implements KeyListener {
    BranchGroup scene;
    private java.util.Timer clock;

    Alpha animationFi;

    private Transform3D camera = new Transform3D();
    Transform3D rAxisPoint;
    Transform3D armPoint;

    RotationInterpolator fiRotation;

    Vector3f cameraPosition = new Vector3f(0f, 0f, 5f);

    private TransformGroup fiTransform;
    TransformGroup armTransform;
    TransformGroup rAxisGroup;

    BoundingSphere bounds;

    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();

    Canvas3D canvas3D = new Canvas3D(config);
    SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

    RobotPanel panel = new RobotPanel();

    private float fi=0f;
    private float r = 0.3f;
    private float z = -0.7f;

    private float new_fi=0f;
    private float new_r = 0.3f;
    private float new_z = -0.7f;

    private int dr = 0;
    private int dz = 0;
    private int dfi = 0;

    private boolean inverse = false;

//    private int i=0;
//    private int j=0;
//    private int k=0;

    // task which is done in every 17 milliseconds
    class Task extends TimerTask {

        @Override
        public void run() {
            fiRotation.setMaximumAngle(fi);
            fiRotation.setMinimumAngle(fi);
            rAxisPoint.setTranslation(new Vector3f(r, 0, 0));
            rAxisGroup.setTransform(rAxisPoint);
            armPoint.setTranslation(new Vector3f(0.3f, z, 0));
            armTransform.setTransform(armPoint);
            if (inverse) {
                dr = (int)((new_r-r)*100);
                dz=(int)((new_z-z)*100);
                dfi=(int)((new_fi-fi)*100);
                if (0!=dfi){
                    fi=fi+Math.signum(dfi)*0.01f;
                }
                else if (0!=dz){
                    z=z+Math.signum(dz)*0.01f;
                }
                else if (0!=dr){
                    r=r+Math.signum(dr)*0.01f;
                }
                else {
                    inverse=false;
                }

            }
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

        // camera initial position
        camera.set(cameraPosition);
        simpleU.getViewingPlatform().getViewPlatformTransform().setTransform(camera);
        simpleU.addBranchGraph(scene);

        add("North", panel);

        // Timer
        clock = new java.util.Timer();
        clock.scheduleAtFixedRate(new Task(), 0, 30);

    }

    BranchGroup utworzScene(){

        BranchGroup wezel_scena = new BranchGroup();

        // Lights
        AmbientLight Swiatlo = new AmbientLight();
        Swiatlo.setInfluencingBounds(bounds);
        wezel_scena.addChild(Swiatlo);

        // floor
        Appearance floorApperance = new Appearance();
        Texture floorTexture = new TextureLoader("images/floor.jpg", null, new Container()).getTexture();
        floorApperance.setTexture(floorTexture);
        Box floor = new Box(6.0f,0.01f,6.0f,Box.GENERATE_TEXTURE_COORDS,floorApperance);
        Transform3D floorPoint = new Transform3D();
        floorPoint.set(new Vector3f(0f,-0.86f,0.0f));
        TransformGroup floorTransform = new TransformGroup(floorPoint);
        floorTransform.addChild(floor);
        wezel_scena.addChild(floorTransform);

        // ceiling
        Appearance ceilingApperance = new Appearance();
        Texture ceilingTexture = new TextureLoader("images/ceiling.jpg", null, new Container()).getTexture();
        ceilingApperance.setTexture(ceilingTexture);
        Box ceiling = new Box(6.0f,0.01f,6.0f,Box.GENERATE_TEXTURE_COORDS,ceilingApperance);
        Transform3D ceilingPoint = new Transform3D();
        ceilingPoint.set(new Vector3f(0f,5f,0.0f));
        TransformGroup ceilingTransform = new TransformGroup(ceilingPoint);
        ceilingTransform.addChild(ceiling);
        wezel_scena.addChild(ceilingTransform);

        //walls
        Appearance wallsApperance = new Appearance();
        Texture wallsTexture = new TextureLoader("images/walls.jpg", null, new Container()).getTexture();
        wallsApperance.setTexture(wallsTexture);

        // north wall
        Box wallN = new Box(6.0f,3f,0.01f,Box.GENERATE_TEXTURE_COORDS,wallsApperance);
        Transform3D wallsPointN = new Transform3D();
        wallsPointN.set(new Vector3f(0f,2f,-6.0f));
        TransformGroup wallNTransform = new TransformGroup(wallsPointN);
        wallNTransform.addChild(wallN);
        wezel_scena.addChild(wallNTransform);

        // south wall
        Box wallS = new Box(6.0f,3f,0.01f,Box.GENERATE_TEXTURE_COORDS,wallsApperance);
        Transform3D wallsPointS = new Transform3D();
        wallsPointS.set(new Vector3f(0f,2f,6.0f));
        TransformGroup wallSTransform = new TransformGroup(wallsPointS);
        wallSTransform.addChild(wallS);
        wezel_scena.addChild(wallSTransform);

        // west wall
        Box wallW = new Box(0.01f,3f,6f,Box.GENERATE_TEXTURE_COORDS,wallsApperance);
        Transform3D wallsPointW = new Transform3D();
        wallsPointW.set(new Vector3f(-6f,2f,0));
        TransformGroup wallWTransform = new TransformGroup(wallsPointW);
        wallWTransform.addChild(wallW);
        wezel_scena.addChild(wallWTransform);

        // east wall
        Box wallE = new Box(0.01f,3f,6f,Box.GENERATE_TEXTURE_COORDS,wallsApperance);
        Transform3D wallsPointE = new Transform3D();
        wallsPointE.set(new Vector3f(6f,2f,0));
        TransformGroup wallETransform = new TransformGroup(wallsPointE);
        wallETransform.addChild(wallE);
        wezel_scena.addChild(wallETransform);




        // vertical robot axis
        Appearance  robotApperance1 = new Appearance();
        Texture steelTexture = new TextureLoader("images/steel.jpg", this).getTexture();
        robotApperance1.setTexture(steelTexture);
        Appearance  robotApperance2 = new Appearance();
        Texture lightSteelTexture = new TextureLoader("images/lightSteel.jpg", this).getTexture();
        robotApperance2.setTexture(lightSteelTexture);
        //robotApperance1.setColoringAttributes(new ColoringAttributes(0.6f,0.5f,0.9f,ColoringAttributes.NICEST));
        Box stand = new Box(0.15f,0.02f,0.15f,Box.GENERATE_TEXTURE_COORDS, robotApperance1);
        Transform3D standPoint = new Transform3D();
        standPoint.set(new Vector3f(0f,-0.83f,0f));
        TransformGroup standTransform = new TransformGroup(standPoint);
        standTransform.addChild(stand);
        wezel_scena.addChild(standTransform);

        Cylinder zAxis = new Cylinder(0.05f,1.3f,Cylinder.GENERATE_TEXTURE_COORDS, robotApperance2);

        Transform3D zAxisPoint = new Transform3D();
        zAxisPoint.set(new Vector3f(0f,-0.2f,0.0f));

        TransformGroup zAxisTransform = new TransformGroup(zAxisPoint);
        zAxisTransform.addChild(zAxis);
        wezel_scena.addChild(zAxisTransform);

        // horizontal robot axis
        animationFi = new Alpha(-1,5000);

        fiTransform = new TransformGroup();
        fiTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        wezel_scena.addChild(fiTransform);

        // rotation fi
        Transform3D tmp = new Transform3D();
        tmp.set(new Vector3f(0f,1f,0f));
        fiRotation = new RotationInterpolator(animationFi, fiTransform, tmp, 0, 0);
        BoundingSphere boundsFi = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 1.0);
        fiRotation.setSchedulingBounds(boundsFi);
        fiTransform.addChild(fiRotation);

        // robotic arm
        Box arm = new Box(0.3f,0.05f,0.1f,Box.GENERATE_TEXTURE_COORDS, robotApperance1);

        armPoint = new Transform3D();
        armPoint.set(new Vector3f(0.3f,-0.7f,0.0f));

        armTransform = new TransformGroup();
        armTransform.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        armTransform.setTransform(armPoint);
        armTransform.addChild(arm);
        fiTransform.addChild(armTransform);

        // r axis
        Cylinder rAxis = new Cylinder(0.02f, 0.6f,Cylinder.GENERATE_TEXTURE_COORDS, robotApperance2);

        rAxisPoint = new Transform3D();
        rAxisPoint.set(new Vector3f(r,0.0f,0.0f));
        Transform3D rotationAngle = new Transform3D();
        rotationAngle.rotZ(-Math.PI/2);
        rAxisPoint.mul(rotationAngle);

        rAxisGroup = new TransformGroup();
        rAxisGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        rAxisGroup.setTransform(rAxisPoint);
        rAxisGroup.addChild(rAxis);
        armTransform.addChild(rAxisGroup);

        // manipulator
        TransformGroup manipulatorGroup = new TransformGroup();
        rAxisGroup.addChild(manipulatorGroup);

        // manipulator stand
        Box manipulatorStand = new Box(0.02f,0.01f,0.1f,Box.GENERATE_TEXTURE_COORDS, robotApperance1);
        Transform3D manipulatorStandPoint = new Transform3D();
        manipulatorStandPoint.set(new Vector3f(0,r,0));
        TransformGroup manipulatorStandGroup = new TransformGroup(manipulatorStandPoint);
        manipulatorStandGroup.addChild(manipulatorStand);
        manipulatorGroup.addChild(manipulatorStandGroup);

        Box manipulatorRight = new Box(0.005f,0.1f,0.02f,Box.GENERATE_TEXTURE_COORDS, robotApperance1);
        Transform3D manipulatorRightPoint = new Transform3D();
        manipulatorRightPoint.set(new Vector3f(0.015f,r+0.09f,0.07f));
        TransformGroup manipulatorRightGroup = new TransformGroup(manipulatorRightPoint);
        manipulatorRightGroup.addChild(manipulatorRight);
        manipulatorGroup.addChild(manipulatorRightGroup);

        Box manipulatorLeft = new Box(0.005f,0.1f,0.02f,Box.GENERATE_TEXTURE_COORDS, robotApperance1);
        Transform3D manipulatorLeftPoint = new Transform3D();
        manipulatorLeftPoint.set(new Vector3f(0.015f,r+0.09f,-0.07f));
        TransformGroup manipulatorLeftGroup = new TransformGroup(manipulatorLeftPoint);
        manipulatorLeftGroup.addChild(manipulatorLeft);
        manipulatorGroup.addChild(manipulatorLeftGroup);

        // movable blocks
        Appearance  block_appearance = new Appearance();
        Texture woodTexture = new TextureLoader("images/wood.jpg", this).getTexture();
        block_appearance.setTexture(woodTexture);
        TransformGroup blocks_transgroup = new TransformGroup();

        //bigger block
        Box block1 = new Box(0.13f,0.13f,0.13f,Box.GENERATE_TEXTURE_COORDS, block_appearance);
        Transform3D block1_transform = new Transform3D();
        block1_transform.set(new Vector3f(-0.12f,0.45f,r-0.3f));

        TransformGroup block1_group = new TransformGroup();
        block1_group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        block1_group.setTransform(block1_transform);

        block1_group.addChild(block1);
        manipulatorGroup.addChild(block1_group);
        wezel_scena.addChild(blocks_transgroup);



         return wezel_scena;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // function to control robot using keyboard
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            fi-=0.02;       // angle (radians)
        }
        if (e.getKeyCode()==KeyEvent.VK_E){
            fi+=0.02;
        }
        if (e.getKeyCode()==KeyEvent.VK_A){
            if (r >0.05)
                r-=0.02;
        }
        if (e.getKeyCode()==KeyEvent.VK_D){
            if (r<0.6)
                r+=0.02;
        }
        if (e.getKeyCode()==KeyEvent.VK_W){
            if (z<0.3)
                z+=0.02;
        }
        if (e.getKeyCode()==KeyEvent.VK_S){
            if (z>-0.7)
                z-=0.02;
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            camera.set(cameraPosition);
            simpleU.getViewingPlatform().getViewPlatformTransform().setTransform(camera);
        }
        if (e.getKeyCode() == KeyEvent.VK_F) {
            fi=0;
            r=0.3f;
            z=-0.7f;
        }
        if (e.getKeyCode() == KeyEvent.VK_K) {
            inverseKinematic();
            inverse=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void inverseKinematic(){
        float x = panel.getXvalue();
        float y = panel.getYvalue();
        float z1 = panel.getZvalue()-0.7f;
        float r1 = (float)Math.sqrt(x*x+y*y)-0.3f;
        if ((r1<=0.6f) && (r1>=0f) && (z1<=0.3f) && (z1>=-0.7f)){
            new_r=r1;
            new_z=z1;
            new_fi=(float)Math.atan2(y,x);
        }
        else {
            JOptionPane.showMessageDialog(null,
                    "Robot can't reach this position, change the value of x,y,z",
                    "Wrong coordinates",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String args[]){
        Frame Robot = new MainFrame(new RobotApp(), 1280, 960);
        Robot.setTitle("Cylindrical arm");
        Robot.setVisible(true);

    }
}
