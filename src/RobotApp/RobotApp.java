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
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.TimerTask;
import java.lang.Math;
import java.util.Vector;
import javax.swing.*;

import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.universe.SimpleUniverse;
//import javafx.scene.shape.Cylinder;

import javax.media.j3d.Transform3D;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.ViewingPlatform;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

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
    TransformGroup manipulatorGroup;

    TransformGroup rack;
    BranchGroup blocks_group;
    BranchGroup block1_branch;
    TransformGroup block1_group;
    Transform3D block1_transform;
    Vector3f block1_position = new Vector3f();


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

    private double block_rotation = Math.PI/2;

    private int dr = 0;
    private int dz = 0;
    private int dfi = 0;

    Vector<Float> r_recorded = new Vector<>();
    Vector<Float> fi_recorded = new Vector<>();
    Vector<Float> z_recorded = new Vector<>();

    private float start_fi;
    private float start_r;
    private float start_z;

    private boolean inverse = false;
    private boolean pick_up = false;
    private boolean zatrzask = false;
    private boolean recording = false;
    private boolean playing = false;
    private boolean musicLatch=true;


    private int i=0;
    private int j=0;
    private int k=0;

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
            if (recording){
                r_recorded.add(r);
                fi_recorded.add(fi);
                z_recorded.add(z);
            }
            if (playing){
                if (i<r_recorded.size()){
                    r=r_recorded.get(i);
                    fi=fi_recorded.get(i);
                    z=z_recorded.get(i);
                    i+=1;
                }
                else{
                    playing=false;
                }
            }

            if (pick_up && zatrzask) {
                blocks_group.removeChild(block1_branch);
                manipulatorGroup.addChild(block1_branch);

                block1_position.set(-0.115f,0.435f,0f);
                block1_transform.set(block1_position);
                Transform3D blockRotation = new Transform3D();
                blockRotation.rotZ(block_rotation);
                block1_transform.mul(blockRotation);
                block1_group.setTransform(block1_transform);

                zatrzask = false;
            }
            else if (!pick_up && zatrzask) {

                manipulatorGroup.removeChild(block1_branch);
                blocks_group.addChild(block1_branch);


                float b1_x = (float)((r+0.735) * Math.cos(fi));
                float b1_y = (float)((-r-0.735) * Math.sin(fi));
                float b1_z = z + 0.115f;

                //block1_position.set(-1.25f,-0.45f,0f);
                block1_position.set(b1_x,b1_z,b1_y);
                block1_transform.set(block1_position);
                block1_group.setTransform(block1_transform);


                zatrzask = false;
            }
        }
    }
    public void play(){
        InputStream music;
        try{
            music = new FileInputStream(new File("sounds/dla-elizy.wav"));
            AudioStream audios=new AudioStream(music);
            AudioPlayer.player.start(audios);

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error");
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
        manipulatorGroup = new TransformGroup();
        manipulatorGroup.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
        manipulatorGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        manipulatorGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);


//        manipulator_branch = new BranchGroup();
//        manipulator_branch.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
//        manipulator_branch.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
//        manipulator_branch.addChild(manipulatorGroup);
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

        // shelf rack
        Texture woodTexture = new TextureLoader("images/wood.jpg", this).getTexture();
        Appearance shelf_appearance = new Appearance();
        shelf_appearance.setTexture(woodTexture);
        rack = new TransformGroup();

        // rack sides
        Box rackSideLeft = new Box(0.15f, 0.65f, 0.03f, Box.GENERATE_TEXTURE_COORDS, shelf_appearance);
        Transform3D rackSideLeft_transform = new Transform3D();
        rackSideLeft_transform.set(new Vector3f(-1.25f,-0.2f,0.5f));
        TransformGroup rackSideLeft_group = new TransformGroup();
        rackSideLeft_group.addChild(rackSideLeft);
        rackSideLeft_group.setTransform(rackSideLeft_transform);
        rack.addChild(rackSideLeft_group);

        Box rackSideRight = new Box(0.15f, 0.65f, 0.03f, Box.GENERATE_TEXTURE_COORDS, shelf_appearance);
        Transform3D rackSideRight_transform = new Transform3D();
        rackSideRight_transform.set(new Vector3f(-1.25f,-0.2f,-0.5f));
        TransformGroup rackSideRight_group = new TransformGroup();
        rackSideRight_group.addChild(rackSideRight);
        rackSideRight_group.setTransform(rackSideRight_transform);
        rack.addChild(rackSideRight_group);

        // shelves
        Box shelfLower = new Box(0.15f, 0.03f, 0.5f, Box.GENERATE_TEXTURE_COORDS, shelf_appearance);
        Transform3D shelfLower_transform = new Transform3D();
        shelfLower_transform.set(new Vector3f(-1.25f,-0.6f,0f));
        TransformGroup shelfLower_group = new TransformGroup();
        shelfLower_group.addChild(shelfLower);
        shelfLower_group.setTransform(shelfLower_transform);
        rack.addChild(shelfLower_group);

        Box shelfUpper = new Box(0.15f, 0.03f, 0.5f, Box.GENERATE_TEXTURE_COORDS, shelf_appearance);
        Transform3D shelfUpper_transform = new Transform3D();
        shelfUpper_transform.set(new Vector3f(-1.25f,-0.1f,0f));
        TransformGroup shelfUpper_group = new TransformGroup();
        shelfUpper_group.addChild(shelfUpper);
        shelfUpper_group.setTransform(shelfUpper_transform);
        rack.addChild(shelfUpper_group);

        wezel_scena.addChild(rack);


        // movable blocks
        Appearance block_appearance = new Appearance();
        Texture boxTexture = new TextureLoader("images/cardboard.jpg", this).getTexture();
        block_appearance.setTexture(boxTexture);
        blocks_group = new BranchGroup();
        blocks_group.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
        blocks_group.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        blocks_group.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);

        //block 1
        Box block1 = new Box(0.12f,0.12f,0.12f,Box.GENERATE_TEXTURE_COORDS, block_appearance);
        block1_transform = new Transform3D();
        block1_position.set(-1.25f,-0.45f,0f);
        block1_transform.set(block1_position);

        block1_group = new TransformGroup();
        block1_group.addChild(block1);
        block1_group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        block1_group.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        block1_group.setTransform(block1_transform);

        block1_branch = new BranchGroup();
        block1_branch.setCapability(BranchGroup.ALLOW_DETACH);
        block1_branch.addChild(block1_group);
        blocks_group.addChild(block1_branch);


        //block 2
        Box block2 = new Box(0.12f,0.12f,0.12f,Box.GENERATE_TEXTURE_COORDS, block_appearance);
        Transform3D block2_transform = new Transform3D();
        block2_transform.set(new Vector3f(-1.25f,0.05f,0f));

        TransformGroup block2_group = new TransformGroup();
        block2_group.addChild(block2);
        block2_group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        block2_group.setTransform(block2_transform);
        //manipulatorGroup.addChild(block2_group);
        blocks_group.addChild(block2_group);

        wezel_scena.addChild(blocks_group);

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
        if (e.getKeyCode() == KeyEvent.VK_E){
            fi+=0.02;
        }
        if (e.getKeyCode() == KeyEvent.VK_A){
            if (r >0.05)
                r-=0.02;
        }
        if (e.getKeyCode() == KeyEvent.VK_D){
            if (r<0.6)
                r+=0.02;
        }
        if (e.getKeyCode() == KeyEvent.VK_W){
            if (z<0.3)
                z+=0.02;
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
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
        if (e.getKeyCode() == KeyEvent.VK_P){
            pick_up = !pick_up;
            zatrzask = true;
            //System.out.println(pick_up);
        }
        if (e.getKeyCode() == KeyEvent.VK_N) {
            recording=true;
            start_fi=fi;
            start_r=r;
            start_z=z;
        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            recording=false;
        }
        if (e.getKeyCode() == KeyEvent.VK_O) {
            playing=true;
        }
        if ((e.getKeyCode() == KeyEvent.VK_B)&&(musicLatch)) {
            musicLatch=false;
            play();
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
