/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.simulation.police_car_simulation.visuals.Auxiliar;

import com.jhw.simulation.police_car_simulation.main.SimulationMain;
import com.jhw.simulation.police_car_simulation.inner.AgentType_Enum;
import com.jhw.simulation.police_car_simulation.utils.Utility_Class;
import com.jhw.simulation.police_car_simulation.visuals.Configuration.Configuration_UI;
import javax.swing.ImageIcon;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Jesús Hernández Barrios
 */
public class General_Panel extends javax.swing.JPanel {

    /**
     * Creates new form PatrolsPanel_Class
     */
    public General_Panel() {
        initComponents();
        jButtonReset.setIcon(new ImageIcon("icons/otros.png"));
        jSpinnerGeneralScale.setValue(SimulationMain.cfg.getPatrolsPointsScale());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBoxTipeNewInserted = new javax.swing.JComboBox();
        jButtonReset = new javax.swing.JButton();
        jSpinnerGeneralScale = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jComboBoxTipeNewInserted.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Policía", "Emergencia" }));

        jButtonReset.setBackground(new java.awt.Color(255, 255, 255));
        jButtonReset.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonReset.setText("Reiniciar Simulación");
        jButtonReset.setContentAreaFilled(false);
        jButtonReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonReset.setFocusPainted(false);
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });

        jSpinnerGeneralScale.setModel(new javax.swing.SpinnerNumberModel(1.0d, -100.0d, 100.0d, 1.0d));
        jSpinnerGeneralScale.setToolTipText("");
        jSpinnerGeneralScale.setBorder(javax.swing.BorderFactory.createTitledBorder("Escala"));
        jSpinnerGeneralScale.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerGeneralScaleStateChanged(evt);
            }
        });

        jButton1.setText("Generar");
        jButton1.setBorder(javax.swing.BorderFactory.createTitledBorder("Emergencia Aleatoria"));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(265, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSpinnerGeneralScale, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jComboBoxTipeNewInserted, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxTipeNewInserted, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSpinnerGeneralScale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addComponent(jButtonReset)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        resetSimulation();
    }//GEN-LAST:event_jButtonResetActionPerformed

    private void jSpinnerGeneralScaleStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerGeneralScaleStateChanged
        adjustScale();
    }//GEN-LAST:event_jSpinnerGeneralScaleStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        generateRandomsEmergencies();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JComboBox jComboBoxTipeNewInserted;
    private javax.swing.JSpinner jSpinnerGeneralScale;
    // End of variables declaration//GEN-END:variables

    public AgentType_Enum getTypeNewInserted() {
        switch (jComboBoxTipeNewInserted.getSelectedIndex()) {
            case 0:
                return AgentType_Enum.PATROL;
            case 1:
                return AgentType_Enum.EMERGENCY;
        }
        return null;
    }

    private void resetSimulation() {
        if (Utility_Class.jopContinue("Desea reiniciar TODA la simulación?")) {
            SimulationMain.closeSimulation();//close the entire simulation
            SimulationMain.showConfiguration();//star the configuration UI
        }
    }

    private void adjustScale() {
        double scale = (double) jSpinnerGeneralScale.getValue();
        SimulationMain.cfg.setPatrolsPointsScale(Math.pow(2, scale));
        SimulationMain.adjustScale();
    }

    private void generateRandomsEmergencies() {
        SimulationMain.generateRandomEmergency();
    }

}
