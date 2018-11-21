/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.client;

import controller.client.ClientChat;
import controller.client.UsersOnline;
import java.io.IOException;
import static java.lang.System.exit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class DeThi extends javax.swing.JFrame {

    public Connection conn;
    public PreparedStatement pst;
    public static ResultSet rs = null;
    public Statement st;
    public ClientChat chatToServer;
    public double diem;
    public int i = 0;
    HashMap<String[], String> questions;
    ArrayList<String> answers = new ArrayList<>();
    ArrayList<JRadioButton[]> listRadioButton = new ArrayList<>();

    public DeThi(ClientChat chat) {
        initComponents();
        this.chatToServer = chat;
        Thread thoigian = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Date thoiGian = new Date();
                    //Khai bao dinh dang ngay thang
                    SimpleDateFormat dinhDangThoiGian = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy ");
                    //parse ngay thang sang dinh dang va chuyen thanh string.
                    String showTime = dinhDangThoiGian.format(thoiGian.getTime());
                    //
                    //jTextFieldThoiGianNow.setText(showTime);
                    lbThoigiannow.setText(showTime);
                }
            }
        });
        thoigian.start();
    }

    public void setAnswers() {
        JRadioButton[] c1 = {jRadioButton1A, jRadioButton1B, jRadioButton1C, jRadioButton1D};
        JRadioButton[] c2 = {jRadioButton2A, jRadioButton2B, jRadioButton2C, jRadioButton2D};
        JRadioButton[] c3 = {jRadioButton3A, jRadioButton3B, jRadioButton3C, jRadioButton3D};
        listRadioButton.add(c1);
        listRadioButton.add(c2);
        listRadioButton.add(c3);
        for (JRadioButton[] b : listRadioButton) {
            for (int i = 0; i < b.length; i++) {
                if (b[i].isEnabled()) {
                    answers.add(b[i].getText());
                    break;
                }
            }
        }
        // add cau tra loi vao list -> kiem tra so voi hashmap

    }

    public void newTest() {
        try {
            chatToServer.sendMessage("/getListQuestions");
        } catch (IOException ex) {
            Logger.getLogger(DeThi.class.getName()).log(Level.SEVERE, null, ex);
        }
        questions = UsersOnline.questions;
        String[] aar = {"Question", "A", "B", "C", "D"};
        DefaultTableModel model = new DefaultTableModel(aar, 0);
        questions.forEach((key, value) -> {
            model.addRow(key);
        });
        jTableQuestion.setModel(model);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1A = new javax.swing.JRadioButton();
        jRadioButton1B = new javax.swing.JRadioButton();
        jRadioButton1C = new javax.swing.JRadioButton();
        jRadioButton1D = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jRadioButton2A = new javax.swing.JRadioButton();
        jRadioButton3A = new javax.swing.JRadioButton();
        jRadioButton2B = new javax.swing.JRadioButton();
        jRadioButton3B = new javax.swing.JRadioButton();
        jRadioButton3C = new javax.swing.JRadioButton();
        jRadioButton2C = new javax.swing.JRadioButton();
        jRadioButton3D = new javax.swing.JRadioButton();
        jRadioButton2D = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableQuestion = new javax.swing.JTable();
        lbThoigiannow = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jRadioButton1A.setText("A");
        jRadioButton1A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1AActionPerformed(evt);
            }
        });

        jRadioButton1B.setText("B");

        jRadioButton1C.setText("C");

        jRadioButton1D.setText("D");

        jLabel1.setText("Câu 1:");

        jLabel2.setText("Câu 2:");

        jLabel3.setText("Câu 3:");

        jRadioButton2A.setText("A");

        jRadioButton3A.setText("A");

        jRadioButton2B.setText("B");

        jRadioButton3B.setText("B");

        jRadioButton3C.setText("C");

        jRadioButton2C.setText("C");

        jRadioButton3D.setText("D");

        jRadioButton2D.setText("D");

        jLabel6.setText("Thời gian:");

        jButton1.setText("Thoát");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Submit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("New Game");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTableQuestion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Question", "A", "B", "C", "D"
            }
        ));
        jScrollPane2.setViewportView(jTableQuestion);

        lbThoigiannow.setText("time");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jRadioButton1A))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jRadioButton2A))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jRadioButton3A)))
                                .addGap(110, 110, 110)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton1B)
                                    .addComponent(jRadioButton3B)
                                    .addComponent(jRadioButton2B))
                                .addGap(100, 100, 100)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jRadioButton2C)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jRadioButton2D))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jRadioButton3C)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jRadioButton3D))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jRadioButton1C)
                                        .addGap(98, 98, 98)
                                        .addComponent(jRadioButton1D))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(44, 44, 44)
                                .addComponent(lbThoigiannow)
                                .addGap(102, 102, 102)
                                .addComponent(jButton2)
                                .addGap(64, 64, 64)
                                .addComponent(jButton1)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(36, 36, 36))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(293, 293, 293)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 91, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addGap(21, 21, 21)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jRadioButton1A)
                    .addComponent(jRadioButton1B)
                    .addComponent(jRadioButton1C)
                    .addComponent(jRadioButton1D))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jRadioButton2A)
                    .addComponent(jRadioButton2B)
                    .addComponent(jRadioButton2C)
                    .addComponent(jRadioButton2D))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton3B)
                        .addComponent(jRadioButton3A))
                    .addComponent(jRadioButton3C)
                    .addComponent(jRadioButton3D))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(lbThoigiannow)
                    .addComponent(jLabel6)
                    .addComponent(jButton1))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int confirmLogut = JOptionPane.showConfirmDialog(this, "Bạn muốn thoát game ?", "Logout", JOptionPane.YES_NO_OPTION);
        if (confirmLogut == JOptionPane.YES_OPTION) {
            exit(0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        newTest();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setAnswers();
        i = 0;
        questions.forEach((key, value) -> {
            if (value.equals(answers.get(i))) {
                diem += 1;
            }
            i++;
        });
        JOptionPane.showMessageDialog(rootPane, "Diem: " + diem);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButton1AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1AActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1AActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButton jRadioButton1A;
    private javax.swing.JRadioButton jRadioButton1B;
    private javax.swing.JRadioButton jRadioButton1C;
    private javax.swing.JRadioButton jRadioButton1D;
    private javax.swing.JRadioButton jRadioButton2A;
    private javax.swing.JRadioButton jRadioButton2B;
    private javax.swing.JRadioButton jRadioButton2C;
    private javax.swing.JRadioButton jRadioButton2D;
    private javax.swing.JRadioButton jRadioButton3A;
    private javax.swing.JRadioButton jRadioButton3B;
    private javax.swing.JRadioButton jRadioButton3C;
    private javax.swing.JRadioButton jRadioButton3D;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableQuestion;
    private javax.swing.JLabel lbThoigiannow;
    // End of variables declaration//GEN-END:variables
}
