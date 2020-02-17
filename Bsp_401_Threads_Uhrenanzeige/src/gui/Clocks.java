package gui;

import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Clocks extends javax.swing.JFrame {
    
    private Thread[] cThreads = new Thread[3];
    private JCheckBox[] cbs = new JCheckBox[3];
    private JPanel[] pns = new JPanel[3];
    
    public Clocks() {
        initComponents();
        this.setSize(800,600);
        this.setLocationRelativeTo(null);  
        
        cbs[0] = cbLocal;
        cbs[1] = cbFirst;
        cbs[2] = cbSecond;
        pns[0] = pnLocal;
        pns[1] = pnFirst;
        pns[2] = pnSecond;
        
        ((Clock)pnLocal).setCity("L");
        ((Clock)pnFirst).setCity("Australia/Sydney");
        ((Clock)pnSecond).setCity("America/New_York");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        tfLocal = new javax.swing.JTextField();
        cbLocal = new javax.swing.JCheckBox();
        pnLocal = new Clock();
        tfFirst = new javax.swing.JTextField();
        cbFirst = new javax.swing.JCheckBox();
        pnFirst = new Clock();
        tfSecond = new javax.swing.JTextField();
        cbSecond = new javax.swing.JCheckBox();
        pnSecond = new Clock();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Time");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        tfLocal.setEditable(false);
        tfLocal.setBackground(new java.awt.Color(0, 0, 0));
        tfLocal.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tfLocal.setForeground(new java.awt.Color(255, 255, 255));
        tfLocal.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tfLocal.setText("Local Time");
        tfLocal.setBorder(null);
        tfLocal.setName(""); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(tfLocal, gridBagConstraints);

        cbLocal.setBackground(new java.awt.Color(0, 0, 0));
        cbLocal.setActionCommand("0");
        cbLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLocalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(cbLocal, gridBagConstraints);

        pnLocal.setBackground(new java.awt.Color(0, 0, 0));
        pnLocal.setMinimumSize(new java.awt.Dimension(290, 100));
        pnLocal.setPreferredSize(new java.awt.Dimension(290, 100));
        pnLocal.setLayout(new java.awt.GridLayout(1, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(pnLocal, gridBagConstraints);

        tfFirst.setBackground(new java.awt.Color(0, 0, 0));
        tfFirst.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tfFirst.setForeground(new java.awt.Color(255, 255, 255));
        tfFirst.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tfFirst.setText("Australia/Sydney");
        tfFirst.setToolTipText("");
        tfFirst.setBorder(null);
        tfFirst.setName(""); // NOI18N
        tfFirst.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                onCityChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(tfFirst, gridBagConstraints);

        cbFirst.setBackground(new java.awt.Color(0, 0, 0));
        cbFirst.setActionCommand("1");
        cbFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLocalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(cbFirst, gridBagConstraints);

        pnFirst.setBackground(new java.awt.Color(0, 0, 0));
        pnFirst.setMinimumSize(new java.awt.Dimension(290, 100));
        pnFirst.setPreferredSize(new java.awt.Dimension(290, 100));
        pnFirst.setLayout(new java.awt.GridLayout(1, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(pnFirst, gridBagConstraints);

        tfSecond.setBackground(new java.awt.Color(0, 0, 0));
        tfSecond.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tfSecond.setForeground(new java.awt.Color(255, 255, 255));
        tfSecond.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tfSecond.setText("America/New York");
        tfSecond.setBorder(null);
        tfSecond.setName(""); // NOI18N
        tfSecond.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                onCityChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(tfSecond, gridBagConstraints);

        cbSecond.setBackground(new java.awt.Color(0, 0, 0));
        cbSecond.setActionCommand("2");
        cbSecond.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLocalActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(cbSecond, gridBagConstraints);

        pnSecond.setBackground(new java.awt.Color(0, 0, 0));
        pnSecond.setMinimumSize(new java.awt.Dimension(290, 100));
        pnSecond.setPreferredSize(new java.awt.Dimension(290, 100));
        pnSecond.setLayout(new java.awt.GridLayout(1, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(pnSecond, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private static Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() {
    public void uncaughtException(Thread th, Throwable ex) {
        JOptionPane.showMessageDialog(null, "Wrong format or city!", "Error", JOptionPane.ERROR_MESSAGE);
    }
};
    
    private void cbLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLocalActionPerformed
        int pos = Integer.parseInt(evt.getActionCommand());
        if(cbs[pos].isSelected()) {
            if(cThreads[pos] == null || !cThreads[pos].isAlive()) {
                cThreads[pos] = new Thread((Runnable)pns[pos]);
                cThreads[pos].setUncaughtExceptionHandler(h);
                cThreads[pos].start();
            }
        } else {
            if(cThreads[pos] != null) {
                cThreads[pos].interrupt();
            }
        }
    }//GEN-LAST:event_cbLocalActionPerformed

    private void onCityChange(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_onCityChange
        if(evt.getComponent().equals(tfFirst)) {
            cbFirst.setSelected(false);
            if(cThreads[1] != null)
                cThreads[1].interrupt();
            ((Clock)pnFirst).setCity(tfFirst.getText().replace(" ", "_"));
        } else {
            cbSecond.setSelected(false);
            if(cThreads[2] != null)
                cThreads[2].interrupt();
            ((Clock)pnSecond).setCity(tfSecond.getText().replace(" ", "_"));
        }
    }//GEN-LAST:event_onCityChange

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Clocks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbFirst;
    private javax.swing.JCheckBox cbLocal;
    private javax.swing.JCheckBox cbSecond;
    private javax.swing.JPanel pnFirst;
    private javax.swing.JPanel pnLocal;
    private javax.swing.JPanel pnSecond;
    private javax.swing.JTextField tfFirst;
    private javax.swing.JTextField tfLocal;
    private javax.swing.JTextField tfSecond;
    // End of variables declaration//GEN-END:variables
}
