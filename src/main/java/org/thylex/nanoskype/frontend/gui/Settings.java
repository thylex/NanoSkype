/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thylex.nanoskype.frontend.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import jssc.SerialPortList;
import org.thylex.nanoskype.frontend.app.NanoSkypeApp;

/**
 *
 * @author Henrik
 */
public class Settings extends javax.swing.JFrame {

    private NanoSkypeApp nanoApp;
    private Properties appProps;

    /**
     * Creates new form GuiClient
     */
    public Settings() {
        initComponents();
    }

    public Settings(NanoSkypeApp app) {
        initComponents();
        nanoApp = app;
        setLabels();
        this.setVisible(false);
        this.setLocationRelativeTo(null);
//        this.pack();

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                saveSettings();
                JOptionPane.showMessageDialog(Settings.this,
                        "Properties were saved successfully!");
            }
        });
        loadSettings();
        setValues();
    }

    public String getPort() {
        return appProps.getProperty("port");
    }

    public String getSpeed() {
        return appProps.getProperty("speed");
    }

    private void loadSettings() {
        Properties defaultProps = new Properties();
        defaultProps.setProperty("port", "COM3");
        defaultProps.setProperty("speed", "57600");

        appProps = new Properties(defaultProps);

        try {
            InputStream instream = new FileInputStream(nanoApp.getConfigFilePath());
            appProps.loadFromXML(instream);
            instream.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "The " + nanoApp.getConfigFilePath() + " file does not exist, default properties loaded.");
        }
    }

    public void saveSettings() {
        appProps.setProperty("port", jComboBox1.getItemAt(jComboBox1.getSelectedIndex()));
        appProps.setProperty("speed", jComboBox2.getItemAt(jComboBox2.getSelectedIndex()));

        try {
            OutputStream outstream = new FileOutputStream(new File(nanoApp.getConfigFilePath()));
            System.out.println(appProps.toString());
            appProps.storeToXML(outstream, "NanoSkypeApp Properties");
            outstream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setValues() {
        //Set port
        boolean portMatchFail = true;
        int portcount = jComboBox1.getItemCount();
        for (int i = 0; i < portcount; i++) {
            if (jComboBox1.getItemAt(i).equals(appProps.getProperty("port"))) {
                jComboBox1.setSelectedIndex(i);
                portMatchFail = false;
            }
        }
        if (portMatchFail) {
            setVisible(true);
            JOptionPane.showMessageDialog(this, "The saved port setting didn't match any detected port. Please check the settings and save when done.");
        }

        //Set speed
        boolean speedMatchFail = true;
        int speedcount = jComboBox2.getItemCount();
        for (int i = 0; i < speedcount; i++) {
            if (jComboBox2.getItemAt(i).equals(appProps.getProperty("speed"))) {
                jComboBox2.setSelectedIndex(i);
                speedMatchFail = false;
            }
        }
        if (speedMatchFail) {
            setVisible(true);
            JOptionPane.showMessageDialog(this, "The saved speed setting didn't match any detected port. Please check the settings and save when done.");
        }
    }

    private void setLabels() {
        String[] speeds = {"9600", "19200", "38400", "57600", "115200"};

        String[] ports;
        ports = SerialPortList.getPortNames();
        if (ports.length < 1) {
            //Ports should be polled from jssc, not a set list
            ports = new String[]{"COM1", "COM2", "COM3"};
            System.out.println("No COM ports detected, faking a list");
        }

        Locale currentLoc = Locale.getDefault();
        ResourceBundle res = ResourceBundle.getBundle("guiSettings", currentLoc);

        this.setTitle(res.getString("frameTitle"));

        jLabel1.setText(res.getString("labelPort"));
        jLabel2.setText(res.getString("labelSpeed"));
        jButton1.setText(res.getString("labelSave"));

        jComboBox1.setModel(new DefaultComboBoxModel<>(ports));
        jComboBox2.setModel(new DefaultComboBoxModel<>(speeds));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 5, 0};
        layout.rowHeights = new int[] {0, 5, 0, 5, 0};
        getContentPane().setLayout(layout);

        jLabel1.setText("portnamn");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel2.setText("speed");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        getContentPane().add(jLabel2, gridBagConstraints);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        getContentPane().add(jComboBox1, gridBagConstraints);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        getContentPane().add(jComboBox2, gridBagConstraints);

        jButton1.setText("Save");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        getContentPane().add(jButton1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Settings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
