package RobotApp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class RobotPanel extends javax.swing.JPanel{
    public RobotPanel() {
        fiText = new javax.swing.JLabel();
        zControlText = new javax.swing.JLabel();
        rText = new javax.swing.JLabel();
        resetCameraText = new javax.swing.JLabel();
        resetRobotText = new javax.swing.JLabel();
        inverseCinematicsText = new javax.swing.JLabel();
        xText = new javax.swing.JLabel();
        yText = new javax.swing.JLabel();
        zText = new javax.swing.JLabel();
        recordingText = new javax.swing.JLabel();
        musicText = new javax.swing.JLabel();
        CatchText = new javax.swing.JLabel();
        trajectoryText = new javax.swing.JLabel();
        Q = new javax.swing.JButton();
        W = new javax.swing.JButton();
        A = new javax.swing.JButton();
        S = new javax.swing.JButton();
        D = new javax.swing.JButton();
        E = new javax.swing.JButton();
        R = new javax.swing.JButton();
        F = new javax.swing.JButton();
        K = new javax.swing.JButton();
        N = new javax.swing.JButton();
        M = new javax.swing.JButton();
        B = new javax.swing.JButton();
        P = new javax.swing.JButton();
        O = new javax.swing.JButton();
        xTextField = new javax.swing.JTextField();
        yTextField = new javax.swing.JTextField();
        zTextField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(51, 255, 255));

        fiText.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        fiText.setText("fi rotation");

        zControlText.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        zControlText.setText("z transform");

        rText.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        rText.setText("r transform");

        resetCameraText.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        resetCameraText.setText("Reset camera");

        resetRobotText.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        resetRobotText.setText("Reset robot");

        inverseCinematicsText.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        inverseCinematicsText.setText("Inverse kinematics");

        xText.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        xText.setText("x:");

        yText.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        yText.setText("y:");

        zText.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        zText.setText("z:");

        recordingText.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        recordingText.setText("start/stop recording");

        musicText.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        musicText.setText("music play");

        CatchText.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        CatchText.setText("Catch object");

        trajectoryText.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        trajectoryText.setText("playing recorded trajectory");

        Q.setBackground(new java.awt.Color(102, 255, 102));
        Q.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        Q.setText("Q");
        Q.setEnabled(false);

        W.setBackground(new java.awt.Color(102, 255, 102));
        W.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        W.setText("W");
        W.setEnabled(false);

        A.setBackground(new java.awt.Color(102, 255, 102));
        A.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        A.setText("A");
        A.setEnabled(false);

        S.setBackground(new java.awt.Color(102, 255, 102));
        S.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        S.setText("S");
        S.setEnabled(false);

        D.setBackground(new java.awt.Color(102, 255, 102));
        D.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        D.setText("M");
        D.setEnabled(false);

        E.setBackground(new java.awt.Color(102, 255, 102));
        E.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        E.setText("E");
        E.setEnabled(false);

        R.setBackground(new java.awt.Color(102, 255, 102));
        R.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        R.setText("K");
        R.setEnabled(false);

        F.setBackground(new java.awt.Color(102, 255, 102));
        F.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        F.setText("F");
        F.setEnabled(false);

        K.setBackground(new java.awt.Color(102, 255, 102));
        K.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        K.setText("R");
        K.setEnabled(false);

        N.setBackground(new java.awt.Color(102, 255, 102));
        N.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        N.setText("N");
        N.setEnabled(false);

        M.setBackground(new java.awt.Color(102, 255, 102));
        M.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        M.setText("D");
        M.setEnabled(false);

        B.setBackground(new java.awt.Color(102, 255, 102));
        B.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        B.setText("B");
        B.setEnabled(false);

        P.setBackground(new java.awt.Color(102, 255, 102));
        P.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        P.setText("P");
        P.setEnabled(false);

        O.setBackground(new java.awt.Color(102, 255, 102));
        O.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        O.setText("O");
        O.setEnabled(false);

        xTextField.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        xTextField.setText("0.00");

        yTextField.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        yTextField.setText("-0.4");

        zTextField.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        zTextField.setText("0.20");

        try {
            myPicture = ImageIO.read(new File("images/xyz.jpg"));
        }
        catch (IOException ex){
            System.err.println(ex);
        }
        imageLabel = new JLabel((new ImageIcon(myPicture)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(fiText)
                                                .addGap(91, 91, 91)
                                                .addComponent(zControlText))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(Q, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(E, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(72, 72, 72)
                                                .addComponent(W, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(S, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(A, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(M, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(rText))
                                                .addGap(6, 6, 6)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(57, 57, 57)
                                                                .addComponent(recordingText))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(80, 80, 80)
                                                                .addComponent(N, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(D, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(trajectoryText)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(O, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(resetCameraText)
                                                        .addGap(29, 29, 29)
                                                        .addComponent(K, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(resetRobotText)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(F, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(inverseCinematicsText))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(42, 42, 42)
                                                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(44, 44, 44)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(xText)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(xTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(yText)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(yTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(zText)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(zTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(CatchText)
                                                        .addComponent(musicText)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(R, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(B, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(P, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(167, 167, 167))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(33, 33, 33)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(xText)
                                                                        .addComponent(xTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(R, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(inverseCinematicsText)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(yText)
                                                                        .addComponent(yTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(CatchText)
                                                                        .addComponent(P, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(zText)
                                                                        .addComponent(zTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(musicText)
                                                                        .addComponent(B, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(trajectoryText)
                                                                        .addComponent(O, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(15, 15, 15))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(fiText)
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(zControlText)
                                                                                .addComponent(resetCameraText)))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(Q, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(E, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(W, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(S, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(K, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(resetRobotText)
                                                                        .addComponent(F, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(rText)
                                                        .addComponent(recordingText))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(D, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(A, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(N, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(M, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap())
        );

            // </editor-fold>
        }

    // get value from text fields
    public float getXvalue(){
        float x = Float.parseFloat(xTextField.getText());
        return x;
    }

    public float getYvalue(){
        float y = Float.parseFloat(yTextField.getText());
        return y;
    }

    public float getZvalue(){
        float z = Float.parseFloat(zTextField.getText());
        return z;
    }

    // set color of the P button red
    public void setPRed(){
        P.setBackground(new java.awt.Color(255, 0, 0));
    }
    // set color of the P button green
    public void setPGreen(){
        P.setBackground(new java.awt.Color(102, 255, 102));
    }


    private javax.swing.JButton A;
    private javax.swing.JButton B;
    private javax.swing.JLabel CatchText;
    private javax.swing.JButton D;
    private javax.swing.JButton E;
    private javax.swing.JButton F;
    private javax.swing.JButton K;
    private javax.swing.JButton M;
    private javax.swing.JButton N;
    private javax.swing.JButton O;
    private javax.swing.JButton P;
    private javax.swing.JButton Q;
    private javax.swing.JButton R;
    private javax.swing.JButton S;
    private javax.swing.JButton W;
    private javax.swing.JLabel fiText;
    private javax.swing.JLabel inverseCinematicsText;
    private javax.swing.JLabel musicText;
    private javax.swing.JLabel rText;
    private javax.swing.JLabel recordingText;
    private javax.swing.JLabel resetCameraText;
    private javax.swing.JLabel resetRobotText;
    private javax.swing.JLabel trajectoryText;
    private javax.swing.JLabel xText;
    private javax.swing.JTextField xTextField;
    private javax.swing.JLabel yText;
    private javax.swing.JTextField yTextField;
    private javax.swing.JLabel zControlText;
    private javax.swing.JLabel zText;
    private javax.swing.JTextField zTextField;
    private javax.swing.JLabel imageLabel;
    private BufferedImage myPicture;
        // End of variables declaration
    }

