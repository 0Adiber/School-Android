package gui;

import beans.Department;
import bl.EmployeeModel;
import database.DBAccess;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class EmployeesGUI extends javax.swing.JFrame {

    private EmployeeModel etm;
    private List<Department> depts;
    
    public EmployeesGUI() {
        initComponents();
        
        try {
            DBAccess.getInstance().connect();
            
            depts = DBAccess.getInstance().getAllDepartments();
            depts.forEach(d -> {
                cbDept.addItem(d);
            });
            
            etm = new EmployeeModel();
            taEmps.setModel(etm);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Please add the Postgres Library");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error");
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnleft = new javax.swing.JPanel();
        pnFilter = new javax.swing.JPanel();
        lbDept = new javax.swing.JLabel();
        cbDept = new javax.swing.JComboBox<>();
        cbBirth = new javax.swing.JCheckBox();
        tfBirth = new javax.swing.JTextField();
        cbMale = new javax.swing.JCheckBox();
        cbFemale = new javax.swing.JCheckBox();
        lbMan = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taEmps = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnleft.setLayout(new java.awt.GridLayout(2, 1));

        pnFilter.setBorder(javax.swing.BorderFactory.createTitledBorder("Filter"));
        pnFilter.setLayout(new java.awt.GridLayout(3, 2));

        lbDept.setText("Department:");
        pnFilter.add(lbDept);

        cbDept.setMaximumRowCount(1000);
        pnFilter.add(cbDept);

        cbBirth.setText("Birthdate before");
        pnFilter.add(cbBirth);

        tfBirth.setText("todo");
        pnFilter.add(tfBirth);

        cbMale.setText("Male");
        pnFilter.add(cbMale);

        cbFemale.setText("Female");
        pnFilter.add(cbFemale);

        pnleft.add(pnFilter);

        lbMan.setBorder(javax.swing.BorderFactory.createTitledBorder("Management"));
        pnleft.add(lbMan);

        getContentPane().add(pnleft, java.awt.BorderLayout.WEST);

        taEmps.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(taEmps);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(EmployeesGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeesGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeesGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeesGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeesGUI().setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbBirth;
    private javax.swing.JComboBox<Department> cbDept;
    private javax.swing.JCheckBox cbFemale;
    private javax.swing.JCheckBox cbMale;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDept;
    private javax.swing.JLabel lbMan;
    private javax.swing.JPanel pnFilter;
    private javax.swing.JPanel pnleft;
    private javax.swing.JTable taEmps;
    private javax.swing.JTextField tfBirth;
    // End of variables declaration//GEN-END:variables
}
