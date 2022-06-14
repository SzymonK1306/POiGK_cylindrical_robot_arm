package RobotApp;

import javax.swing.*;

public class RobotPanel extends javax.swing.JPanel{
    public RobotPanel() {

        controlText = new javax.swing.JLabel();
        resetCameraText = new javax.swing.JLabel();
        resetRobotText = new javax.swing.JLabel();
        inverseCinematicsText = new javax.swing.JLabel();
        xText = new javax.swing.JLabel();
        yText = new javax.swing.JLabel();
        zText = new javax.swing.JLabel();
        Q = new javax.swing.JButton();
        W = new javax.swing.JButton();
        A = new javax.swing.JButton();
        S = new javax.swing.JButton();
        D = new javax.swing.JButton();
        E = new javax.swing.JButton();
        R = new javax.swing.JButton();
        F = new javax.swing.JButton();
        K = new javax.swing.JButton();
        xTextField = new javax.swing.JTextField();
        yTextField = new javax.swing.JTextField();
        zTextField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(51, 255, 255));

        controlText.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        controlText.setText("Control:");

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
        D.setText("D");
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

        xTextField.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        xTextField.setText("0.0");

        yTextField.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        yTextField.setText("0.3");

        zTextField.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        zTextField.setText("0.2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(A, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(S, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(D, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(52, 52, 52)
                                                                .addComponent(Q, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(W, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(E, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(135, 135, 135)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(resetRobotText)
                                                        .addComponent(resetCameraText))
                                                .addGap(27, 27, 27)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(K, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(F, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 229, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(xText)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(xTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(31, 31, 31))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(yText)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(yTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(zText)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(zTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(125, 125, 125)
                                                .addComponent(controlText)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(inverseCinematicsText)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(R, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(313, 313, 313))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(controlText)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(Q, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(W, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(E, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(8, 8, 8)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(D, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(S, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(A, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(resetRobotText))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(resetCameraText)
                                                        .addComponent(K, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(F, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(inverseCinematicsText)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(xText)
                                                                        .addComponent(xTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(R, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(yText)
                                                        .addComponent(yTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(zText)
                                                        .addComponent(zTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

            // </editor-fold>
        }

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


    private javax.swing.JButton A;
    private javax.swing.JButton D;
    private javax.swing.JButton E;
    private javax.swing.JButton F;
    private javax.swing.JButton Q;
    private javax.swing.JButton R;
    private javax.swing.JButton K;
    private javax.swing.JButton S;
    private javax.swing.JButton W;
    private javax.swing.JLabel controlText;
    private javax.swing.JLabel inverseCinematicsText;
    private javax.swing.JTextField xTextField;
    private javax.swing.JTextField yTextField;
    private javax.swing.JTextField zTextField;
    private javax.swing.JLabel resetCameraText;
    private javax.swing.JLabel resetRobotText;
    private javax.swing.JLabel xText;
    private javax.swing.JLabel yText;
    private javax.swing.JLabel zText;
        // End of variables declaration
    }

