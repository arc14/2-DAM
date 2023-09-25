/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package conversortemp;

/**
 *
 * @author aromcon1407_iesmarti
 */
public class ConvTemp extends javax.swing.JFrame {

    /**
     * Creates new form ConvTemp
     */
    public ConvTemp() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sliderC = new javax.swing.JSlider();
        LabelC = new javax.swing.JLabel();
        textFieldC = new javax.swing.JTextField();
        LabelF = new javax.swing.JLabel();
        textFieldF = new javax.swing.JTextField();
        sliderF = new javax.swing.JSlider();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sliderC.setMajorTickSpacing(10);
        sliderC.setMaximum(60);
        sliderC.setMinorTickSpacing(5);
        sliderC.setOrientation(javax.swing.JSlider.VERTICAL);
        sliderC.setPaintLabels(true);
        sliderC.setPaintTicks(true);
        sliderC.setValue(0);
        sliderC.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sliderC.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sliderC.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderCStateChanged(evt);
            }
        });

        LabelC.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LabelC.setText("Cº");

        textFieldC.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        textFieldC.setText("0");
        textFieldC.setEnabled(false);
        textFieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldCActionPerformed(evt);
            }
        });
        textFieldC.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                textFieldCPropertyChange(evt);
            }
        });

        LabelF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LabelF.setText("Fº");

        textFieldF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        textFieldF.setText("32");
        textFieldF.setEnabled(false);
        textFieldF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldFActionPerformed(evt);
            }
        });

        sliderF.setMajorTickSpacing(20);
        sliderF.setMaximum(140);
        sliderF.setMinimum(32);
        sliderF.setMinorTickSpacing(10);
        sliderF.setOrientation(javax.swing.JSlider.VERTICAL);
        sliderF.setPaintLabels(true);
        sliderF.setPaintTicks(true);
        sliderF.setToolTipText("");
        sliderF.setValue(32);
        sliderF.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sliderF.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderFStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(LabelF, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 45, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(textFieldF, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(sliderF, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(LabelC, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(textFieldC, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sliderC, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelC, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LabelF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sliderC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sliderF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldCActionPerformed

    }//GEN-LAST:event_textFieldCActionPerformed

    private void textFieldFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldFActionPerformed

    private void sliderCStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderCStateChanged
        double C = (double) sliderC.getValue();
        textFieldC.setText("" + C);
        double resC = (double) (C * 1.8 + 32);
        textFieldF.setText("" + Math.round(resC));
        sliderF.setValue(Integer.parseInt(textFieldF.getText()));
    }//GEN-LAST:event_sliderCStateChanged

    private void sliderFStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderFStateChanged
        double F = (double) sliderF.getValue();
        textFieldF.setText("" + F);
        double resF = (double)((F -32)/1.8);
        textFieldC.setText("" + Math.round(resF));
        sliderC.setValue(Integer.parseInt(textFieldC.getText()));
    }//GEN-LAST:event_sliderFStateChanged

    private void textFieldCPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_textFieldCPropertyChange
        
    }//GEN-LAST:event_textFieldCPropertyChange

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
            java.util.logging.Logger.getLogger(ConvTemp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConvTemp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConvTemp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConvTemp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConvTemp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelC;
    private javax.swing.JLabel LabelF;
    private javax.swing.JSlider sliderC;
    private javax.swing.JSlider sliderF;
    private javax.swing.JTextField textFieldC;
    private javax.swing.JTextField textFieldF;
    // End of variables declaration//GEN-END:variables
}
