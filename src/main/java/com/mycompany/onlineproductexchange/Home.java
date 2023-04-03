/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.onlineproductexchange;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import static javax.swing.SwingConstants.LEFT;
import javax.swing.border.EtchedBorder;
import static javax.swing.text.StyleConstants.Italic;

 
/**
 *
 * @author Admin
 */
public class Home extends javax.swing.JFrame {
     Profile_page p=new Profile_page();
     
String n;
static int uid;
 
    public void SET(String name,int u){
                        n=name;
                        uid=u;
//                        System.out.println("setting"+uid);
                    }


    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        
        
        
        
        
        Connection dbconn =DBconnection.connectDB();
        if(dbconn!=null){
        try {
            byte[] imageBytes;
            Image image;
            PreparedStatement st=(PreparedStatement)
                    dbconn.prepareStatement("Select * from product");
                    
            
        
            ResultSet res=st.executeQuery();
            jPanel4.setLayout(new GridLayout(10,2,10,10));
            while(res.next()){
                String status="Status:"+res.getString(8);
                imageBytes=res.getBytes(7);
                image=getToolkit().createImage(imageBytes);
               
//                ImageIcon icon=new ImageIcon(image);
                JPanel p=new JPanel();
                
                 
//                 String text="Product name: "+res.getString(2)+"\nDescription: "+res.getString(4)+"\nBase-Price: "+res.getInt(6)+"\nYears used: "+res.getInt(3);
                String name="Product name: "+res.getString(2);
                String desc="Description: "+res.getString(4);
                int bp=res.getInt(6);
                String baseprice="Base-Price: "+res.getInt(6);
                String yrs="Years used: "+res.getInt(3);
                String pid=res.getString(1);
                
                
                JLabel namelabel=new JLabel(name);
                JTextArea desclabel=new JTextArea(desc,3,6);
                
                desclabel.setEditable(false);
                desclabel.setLineWrap(true);
                desclabel.setWrapStyleWord(true);
              
                desclabel.setBorder(null);
                desclabel.setBackground(Color.white);
                JLabel plabel=new JLabel(baseprice);
                JLabel yrslabel=new JLabel(yrs);
                
                p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
                p.setPreferredSize(new Dimension(360, 360));
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(360, 200, Image.SCALE_SMOOTH));
                 desclabel.setMinimumSize(new Dimension(p.getWidth(), HEIGHT));
                 JLabel label=new JLabel(imageIcon);
                namelabel.setSize(p.getWidth(), HEIGHT);
                namelabel.setHorizontalAlignment(LEFT);
                 p.add(label);
                 p.add(namelabel);
                 
                 p.add(plabel);
                 p.add(yrslabel);
                 p.add(desclabel);
                 EtchedBorder border=new EtchedBorder(EtchedBorder.RAISED);
                 p.setBorder(border);
                 p.setBackground(Color.white);
                 Font f1, f2;
                f1 = new Font("Lucida Bright",Font.PLAIN,18);
                 f2 = new Font("Lucida Bright",Font.BOLD,18);
                 namelabel.setFont(f1);
                 desclabel.setFont(f1);
                 yrslabel.setFont(f1);
                 plabel.setFont(f1);
                 JButton btn=new JButton("Bid now!");
                 btn.setBackground(Color.black);
                 btn.setForeground(Color.white);
                 btn.setFont(f2);
                 btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//                 btn.setAlignmentX(CENTER_ALIGNMENT);
//                 System.out.println(status);
                 JLabel statuslabel=new JLabel(status);
                 statuslabel.setFont(f2);
                 statuslabel.setForeground(Color.red);
                 String s=res.getString(8);
//                 System.out.println(s);
                 p.add(statuslabel);
                 
                 p.add(btn);
                 
                  
                 btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    bidbtnclicked(evt);
                } catch (SQLException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

                    private void bidbtnclicked(MouseEvent evt) throws SQLException {
                        int value=Integer.valueOf(JOptionPane.showInputDialog("Enter the bid-price here!",""));
       
                    if(value<bp){
                        JOptionPane.showMessageDialog(btn,"Bid price must be greater than or equal to the base-price mentioned!","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                 
                                JOptionPane.showMessageDialog(btn,"Your bid is taken into consideration!","Success",JOptionPane.INFORMATION_MESSAGE);
//                                System.out.println(value);
                                enter_pur_record(value,pid);
                       
                    }
                    
                    
                
                       
                    }
        });
                 if(s!=null){
                 p.remove(btn);
                 }
                 else{
                 p.remove(statuslabel);
                 }
                 jPanel4.add(p);   
            }
            
            
        
    }
    catch (SQLException ex){
         Logger.getLogger(OnlineProductExchange.class.getName()).log(Level.SEVERE, null, ex);
//         System.out.println("Error here");
    }
        }
    else{
            System.out.println("Connection not available!");
        }
     
        
//
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    void username(String user){
        jLabel3.setText( "Welcome, "+user +" !");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1444, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 718, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel4);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 820, 730));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 10, 30, -1));

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Lucida Bright", 2, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 30, 340, 30));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon("D:\\netbeans\\OnlineProductExchange\\src\\main\\java\\Pics\\icons8_Account_50px (1).png")); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 255, 255), new java.awt.Color(255, 255, 255), new java.awt.Color(255, 255, 255), new java.awt.Color(255, 255, 255)));
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 20, 85, 67));

        jButton1.setFont(new java.awt.Font("Lucida Bright", 0, 18)); // NOI18N
        jButton1.setText("Go back!");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 720, -1, 43));

        jLabel6.setIcon(new javax.swing.ImageIcon("D:\\netbeans\\OnlineProductExchange\\src\\main\\java\\Pics\\resiizedd.jpg")); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 770));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
      System.exit(0);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
            // TODO add your handling code here:
           
            p.setVisible(true);
            System.out.println("Sending uid"+uid);
            p.uid(uid,n);
            p.initializepanel2(uid);
            p.initializepanel3(uid);
           
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        new OnlineProductExchange().setVisible(true);
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    public void enter_pur_record(int value,String pid) throws SQLException{
        Connection dbconn2 =DBconnection.connectDB();
    PreparedStatement st2=(PreparedStatement)
                                        dbconn2.prepareStatement("insert into bidding(bid_price,u_id,p_id) values(?,?,?)");
                                
                                
                                st2.setInt(1,value);
                                st2.setInt(2,uid);
//                                System.out.println(uid+"printing");
                                st2.setString(3,pid);
                                
                                
                                int res2=st2.executeUpdate();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
                 
                 
                
                
            }

           
                    
                }



                );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    

       

}


