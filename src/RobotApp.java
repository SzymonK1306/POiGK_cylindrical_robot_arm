import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.swing.*;
import java.awt.*;
import com.sun.j3d.utils.universe.SimpleUniverse;
//import javafx.scene.shape.Cylinder;

import javax.media.j3d.Transform3D;
import javax.media.j3d.Node;
import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;



public class RobotApp extends JFrame{

    RobotApp(){

        super("Robot cylindryczny");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        GraphicsConfiguration config =
           SimpleUniverse.getPreferredConfiguration();

        Canvas3D canvas3D = new Canvas3D(config);
        canvas3D.setPreferredSize(new Dimension(1000,800));

        add(canvas3D);
        pack();
        setVisible(true);

        BranchGroup scena = utworzScene();
	    scena.compile();

        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

        Transform3D przesuniecie_obserwatora = new Transform3D();
        przesuniecie_obserwatora.set(new Vector3f(0.0f,0.0f,3.0f));

        simpleU.getViewingPlatform().getViewPlatformTransform().setTransform(przesuniecie_obserwatora);

        simpleU.addBranchGraph(scena);
    }

    BranchGroup utworzScene(){

        BranchGroup wezel_scena = new BranchGroup();

        BoundingSphere Wiezy = new BoundingSphere();

        AmbientLight Swiatlo = new AmbientLight();
        Swiatlo.setInfluencingBounds(Wiezy);
        wezel_scena.addChild(Swiatlo);


        Transform3D  tmp_rot      = new Transform3D();
        tmp_rot.rotZ(Math.PI/2);

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
        Box osR = new Box(0.3f,0.05f,0.1f,wygladRobota);

        Transform3D punktOsiR = new Transform3D();
        punktOsiR.set(new Vector3f(0.3f,-0.78f,0.0f));

        TransformGroup transformacjaOsiR = new TransformGroup(punktOsiR);
        transformacjaOsiR.addChild(osR);
        wezel_scena.addChild(transformacjaOsiR);

         return wezel_scena;

    }

     public static void main(String args[]){
      new RobotApp();

   }

}
