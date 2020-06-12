package gui;

import beans.Department;
import bl.EmployeeModel;
import database.DBAccess;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class EmployeesGUI extends javax.swing.JFrame {

    private EmployeeModel etm;
    private List<Department> depts;
    
    private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public EmployeesGUI() {
        initComponents();
        
        try {            
            depts = DBAccess.getInstance().getAllDepartments();
            depts.forEach(d -> {
                cbDept.addItem(d);
            });
            
            etm = new EmployeeModel();
            taEmps.setModel(etm);
            etm.setEmps(DBAccess.getInstance().getAllEmployeesBy(depts.get(0).getDeptno(), DTF.format(LocalDate.now()), "M", "F"));
            
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Please add the Postgres Library");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error");
        }
        
        taEmps.getTableHeader().addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent evt) {
                int col = taEmps.columnAtPoint(evt.getPoint());
                String name = taEmps.getColumnName(col).toLowerCase();
                etm.sort(name);
            }
            
        });
        
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
        cbDept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onDeptChange(evt);
            }
        });
        pnFilter.add(cbDept);

        cbBirth.setText("Birthdate before");
        cbBirth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onBirthBeforeSelect(evt);
            }
        });
        pnFilter.add(cbBirth);

        tfBirth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onBirthBefore(evt);
            }
        });
        pnFilter.add(tfBirth);

        cbMale.setSelected(true);
        cbMale.setText("Male");
        cbMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onGenderChange(evt);
            }
        });
        pnFilter.add(cbMale);

        cbFemale.setSelected(true);
        cbFemale.setText("Female");
        cbFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onGenderChange(evt);
            }
        });
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

    private void onGenderChange(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onGenderChange
        try {
            Department d = (Department)cbDept.getSelectedItem();
            
            if(cbMale.isSelected() && cbFemale.isSelected())
                etm.setEmps(DBAccess.getInstance().getAllEmployeesBy(d.getDeptno(), DTF.format(LocalDate.now()), "M", "F"));
            else if(cbMale.isSelected())
                etm.setEmps(DBAccess.getInstance().getAllEmployeesBy(d.getDeptno(), DTF.format(LocalDate.now()), "M", "M"));
            else if(cbFemale.isSelected())
                etm.setEmps(DBAccess.getInstance().getAllEmployeesBy(d.getDeptno(), DTF.format(LocalDate.now()), "F", "F"));
            else
                etm.setEmps(DBAccess.getInstance().getAllEmployeesBy(d.getDeptno(), DTF.format(LocalDate.now()), "M", "F"));
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Missing the Postgres Library!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "There was an error retrieving the data from the database");
        }       
        
    }//GEN-LAST:event_onGenderChange

    private void onDeptChange(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onDeptChange
        if(etm == null)
            return;
        try {
            Department d = (Department)cbDept.getSelectedItem();
            
            if(cbMale.isSelected() && cbFemale.isSelected())
                etm.setEmps(DBAccess.getInstance().getAllEmployeesBy(d.getDeptno(), DTF.format(LocalDate.now()), new String[]{"M","F"}));
            else if(cbMale.isSelected())
                etm.setEmps(DBAccess.getInstance().getAllEmployeesBy(d.getDeptno(), DTF.format(LocalDate.now()), new String[]{"M","M"}));
            else if(cbFemale.isSelected())
                etm.setEmps(DBAccess.getInstance().getAllEmployeesBy(d.getDeptno(), DTF.format(LocalDate.now()), new String[]{"F","F"}));
            else
                etm.setEmps(DBAccess.getInstance().getAllEmployeesBy(d.getDeptno(), DTF.format(LocalDate.now()), "M", "F"));
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Missing the Postgres Library!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "There was an error retrieving the data from the database");
        }
    }//GEN-LAST:event_onDeptChange

    private void onBirthBefore(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onBirthBefore
        if(etm == null)
            return;
        try {
            Department d = (Department)cbDept.getSelectedItem();
            
            LocalDate date;
            if(!tfBirth.getText().trim().isEmpty()) {
                date = LocalDate.parse(tfBirth.getText(), DTF);
            } else {
                date = LocalDate.now();
            }
            
            if(cbMale.isSelected() && cbFemale.isSelected())
                etm.setEmps(DBAccess.getInstance().getAllEmployeesBy(d.getDeptno(), DTF.format(date), new String[]{"M","F"}));
            else if(cbMale.isSelected())
                etm.setEmps(DBAccess.getInstance().getAllEmployeesBy(d.getDeptno(), DTF.format(date), new String[]{"M","M"}));
            else if(cbFemale.isSelected())
                etm.setEmps(DBAccess.getInstance().getAllEmployeesBy(d.getDeptno(), DTF.format(date), new String[]{"F","F"}));
            else
                etm.setEmps(DBAccess.getInstance().getAllEmployeesBy(d.getDeptno(), DTF.format(date), "M", "F"));
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Missing the Postgres Library!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "There was an error retrieving the data from the database");
        } catch(DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Date-Format: \"yyyy-MM-dd\"");
        }
    }//GEN-LAST:event_onBirthBefore

    private void onBirthBeforeSelect(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onBirthBeforeSelect
        if(etm == null)
            return;
        try {
            Department d = (Department)cbDept.getSelectedItem();
            
            if(cbMale.isSelected() && cbFemale.isSelected())
                etm.setEmps(DBAccess.getInstance().getAllEmployeesBy(d.getDeptno(), DTF.format(LocalDate.now()), new String[]{"M","F"}));
            else if(cbMale.isSelected())
                etm.setEmps(DBAccess.getInstance().getAllEmployeesBy(d.getDeptno(), DTF.format(LocalDate.now()), new String[]{"M","M"}));
            else if(cbFemale.isSelected())
                etm.setEmps(DBAccess.getInstance().getAllEmployeesBy(d.getDeptno(), DTF.format(LocalDate.now()), new String[]{"F","F"}));
            else
                etm.setEmps(DBAccess.getInstance().getAllEmployeesBy(d.getDeptno(), DTF.format(LocalDate.now()), "M", "F"));
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Missing the Postgres Library!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "There was an error retrieving the data from the database");
        }
    }//GEN-LAST:event_onBirthBeforeSelect

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
