/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ZipUnzip;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.swing.JFileChooser;

/**
 *
 * @author Hicham ELABBASSI - SUPINFO 2014
 */
public class home extends javax.swing.JFrame {

    /**
     * Creates new form home
     */
    public home() {
        fos = null;
        fc = new JFileChooser();
        zipos = null;
        fis = null;
        ze = null;
        file = null;
        zis = null;
        initComponents();
        setSize(new Dimension(446, 125));
        setMinimumSize(new Dimension(446, 125));
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        textFILE = new javax.swing.JTextField();
        Selector = new javax.swing.JButton();
        unzipBUTTON = new javax.swing.JButton();
        zipBUTTON = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HichamZIPPER");

        jPanel1.setLayout(null);
        jPanel1.add(textFILE);
        textFILE.setBounds(10, 10, 350, 30);

        Selector.setText("...");
        Selector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectorActionPerformed(evt);
            }
        });
        jPanel1.add(Selector);
        Selector.setBounds(370, 10, 43, 30);

        unzipBUTTON.setText("UNZIP");
        unzipBUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unzipBUTTONActionPerformed(evt);
            }
        });
        jPanel1.add(unzipBUTTON);
        unzipBUTTON.setBounds(240, 50, 170, 25);

        zipBUTTON.setText("ZIP");
        zipBUTTON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zipBUTTONActionPerformed(evt);
            }
        });
        jPanel1.add(zipBUTTON);
        zipBUTTON.setBounds(10, 50, 220, 25);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>                        

    private void SelectorActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            textFILE.setText(file.getAbsolutePath());
        } else {
            System.out.println("Retour");
        }
    }                                        

    private void zipBUTTONActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        try {
            if ("application/x-zip-compressed".equals(Files.probeContentType(file.toPath()))) {
                textFILE.setText("Votre fichier est une archive !");
            } else {
                if ("".equalsIgnoreCase(textFILE.getText().trim()) || textFILE.getText() == null) {
                    textFILE.setText("Selectionner un fichier");
                } else {
                    fos = new FileOutputStream(file.getParent()+File.separator + file.getName()+".zip");
                    zipos = new ZipOutputStream(fos);

                    byte[] data = new byte[104096];
                    fis = new FileInputStream(textFILE.getText());
                    ze = new ZipEntry(file.getName());
                    zipos.putNextEntry(ze);
                    while (fis.read(data) != -1) {
                        zipos.write(data);
                    }
                    System.out.println();
                    zipos.flush();
                    textFILE.setText("Archive g�n�r� !");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (zipos != null) {
                try {
                    zipos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }                                         

    private void unzipBUTTONActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        try {
            if ("application/x-zip-compressed".equals(Files.probeContentType(file.toPath()))) {
                if ("".equalsIgnoreCase(textFILE.getText().trim()) || textFILE.getText() == null) {
                    textFILE.setText("Selectionner un fichier");
                } else {
                    fis = new FileInputStream(file.getAbsoluteFile());
                    zis = new ZipInputStream(fis);

                    while ((ze = zis.getNextEntry()) != null) {
                        int b;
                        fos = new FileOutputStream(file.getParent()+File.separator + ze.getName());

                        while ((b = zis.read()) != -1) {
                            fos.write(b);
                        }
                        fos.close();
                    }
                }
            } else {
                textFILE.setText("Votre fichier n'est pas une archive !");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (zipos != null) {
                try {
                    zipos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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
            java.util.logging.Logger.getLogger(home.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton Selector;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField textFILE;
    private javax.swing.JButton unzipBUTTON;
    private javax.swing.JButton zipBUTTON;
    // End of variables declaration                   
    final JFileChooser fc;
    FileOutputStream fos;
    ZipOutputStream zipos;
    FileInputStream fis;
    ZipEntry ze;
    File file;
    ZipInputStream zis;
}
