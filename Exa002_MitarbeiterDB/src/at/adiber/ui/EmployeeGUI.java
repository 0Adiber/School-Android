package at.adiber.ui;

import at.adiber.beans.Employee;
import at.adiber.bl.AddEmployeeDialog;
import at.adiber.bl.EmployeeTableModel;
import at.adiber.db.DB_Access;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class EmployeeGUI extends javax.swing.JFrame {
    
    private DB_Access db;
    private EmployeeTableModel resultTable;
    private int currentDept = -1;
    
    public EmployeeGUI() {        
        try {
            db = new DB_Access();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmployeeGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        resultTable = new EmployeeTableModel();
        
        initComponents();
        setLocationRelativeTo(null);
        setSize(800, 600);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        popDel = new javax.swing.JPopupMenu();
        miDelete = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        btAverage = new javax.swing.JButton();
        btAddEmployee = new javax.swing.JButton();
        btFindDepartment = new javax.swing.JButton();
        tfDepartment = new javax.swing.JTextField();
        btInsertData = new javax.swing.JButton();
        lbFilter = new javax.swing.JLabel();
        btAddFromRes = new javax.swing.JButton();
        btConnect = new javax.swing.JButton();
        btDisconnect = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbResult = new javax.swing.JTable();

        miDelete.setText("Delete");
        miDelete.setActionCommand("on");
        miDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onDelete(evt);
            }
        });
        popDel.add(miDelete);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        btAverage.setText("Get Average Salary");
        btAverage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onGetAvgSal(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(btAverage, gridBagConstraints);

        btAddEmployee.setText("Add Employee");
        btAddEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onAddEmployee(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(btAddEmployee, gridBagConstraints);

        btFindDepartment.setText("Filter By Department");
        btFindDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onFilterDept(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(btFindDepartment, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(tfDepartment, gridBagConstraints);

        btInsertData.setText("Instert Test Data");
        btInsertData.setActionCommand("");
        btInsertData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onInsertTestData(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(btInsertData, gridBagConstraints);

        lbFilter.setText("Filter Employees by Department");
        lbFilter.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lbFilter, gridBagConstraints);

        btAddFromRes.setText("Add Employees (from res .csv)");
        btAddFromRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFromRes(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        jPanel1.add(btAddFromRes, gridBagConstraints);

        btConnect.setText("Connect");
        btConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onConnect(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(btConnect, gridBagConstraints);

        btDisconnect.setText("Disconnect");
        btDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onDisconnect(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel1.add(btDisconnect, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setLayout(new java.awt.BorderLayout());

        tbResult.setModel(resultTable);
        tbResult.setComponentPopupMenu(popDel);
        jScrollPane1.setViewportView(tbResult);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onInsertTestData(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onInsertTestData
        try {
            db.initEmployees();
            resultTable.changeData(db.getAllEmployees());
            resultTable.lastKey = 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Couldn't add Employee Test Data to DB");
        } catch(NullPointerException e) {
            JOptionPane.showMessageDialog(this, "You have to connect to the DB first!");
        }
    }//GEN-LAST:event_onInsertTestData

    private void onAddEmployee(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onAddEmployee
        AddEmployeeDialog aed = new AddEmployeeDialog(this);
        Employee emp = aed.run();
        if(emp != null) {
            try {
                db.insertEmployee(emp);
                resultTable.changeData(db.getAllEmployees());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Ther was an error inserting the data (Maybe duplicate Key or not connected to DB?)!");
            } catch(NullPointerException e) {
                JOptionPane.showMessageDialog(this, "You have to connect to the DB first!");
            }
        }
    }//GEN-LAST:event_onAddEmployee

    private void onGetAvgSal(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onGetAvgSal
        Object[] options = {"Female", "Male"};
        int selected = JOptionPane.showOptionDialog(this, "Select Gender", "Avg Salary", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, options, options[0]);
        
        try {
            if(currentDept == -1) {
                double avg = db.getAverageSalery(((String)options[selected]).charAt(0));
                JOptionPane.showMessageDialog(this, "The Average Salary of " + options[selected] + "s in all departments is " + NumberFormat.getCurrencyInstance().format(avg));
            } else {
                double avg = db.getAverageSalery(((String)options[selected]).charAt(0), currentDept);
                JOptionPane.showMessageDialog(this, "The Average Salary of " + options[selected] + "s in department " + currentDept + " is " + NumberFormat.getCurrencyInstance().format(avg));
            }
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(this, "There was an error retrieving the data.");
        } catch(NullPointerException e) {
                JOptionPane.showMessageDialog(this, "You have to connect to the DB first!");
            }
        
    }//GEN-LAST:event_onGetAvgSal

    private void onFilterDept(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onFilterDept
        try {
            String filter = tfDepartment.getText().trim();
            if(filter.isEmpty()) {
                currentDept = -1;
                resultTable.changeData(db.getAllEmployees());
                return;
            }
            int dept = Integer.parseInt(filter);
            currentDept = dept;
            resultTable.changeData(db.getEmployeesFromDepartment(dept));
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "The Department Filter has to be a Number!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "There was an error retrieving the data.");
        } catch(NullPointerException e) {
                JOptionPane.showMessageDialog(this, "You have to connect to the DB first!");
            }
    }//GEN-LAST:event_onFilterDept

    private void onDelete(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onDelete
        Arrays.stream(tbResult.getSelectedRows()).forEach(i -> {
            Object[] row = resultTable.getRow(i);
            try {
                db.removeEmployee(new Employee(row));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "There was an error when deleting an employee");
            }
        });
        
        onFilterDept(null);
    }//GEN-LAST:event_onDelete

    private void addFromRes(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFromRes
        Path path = Paths.get(System.getProperty("user.dir"), "src", "res", "eingabe.csv");
    
        try {
            FileReader fr = new FileReader(path.toFile());
            BufferedReader br = new BufferedReader(fr);
            
            List<Employee> tEmps = br.lines()
                                     .skip(1)
                                     .map(Employee::new)
                                     .collect(Collectors.toList());
            
            for(Employee e : tEmps) {
                e.setPers_nr(++resultTable.lastKey);
                db.insertEmployee(e);
            }
            
            resultTable.changeData(db.getAllEmployees());
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Could not load 'eingabe.csv'");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not load 'eingabe.csv'");
        } catch(SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "There was an error inserting the new data!");
        } catch(NullPointerException e) {
            JOptionPane.showMessageDialog(this, "You have to connect to the DB first!");
        }
        
    }//GEN-LAST:event_addFromRes

    private void onConnect(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onConnect
        try {
            db.init();
            resultTable.changeData(db.getAllEmployees());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Can't connect to DB!");
        }
    }//GEN-LAST:event_onConnect

    private void onDisconnect(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onDisconnect
        try {
            db.disconnect();
            resultTable.changeData(new ArrayList<>());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Can't disconnect from DB!");
        }
    }//GEN-LAST:event_onDisconnect
    
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
            java.util.logging.Logger.getLogger(EmployeeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddEmployee;
    private javax.swing.JButton btAddFromRes;
    private javax.swing.JButton btAverage;
    private javax.swing.JButton btConnect;
    private javax.swing.JButton btDisconnect;
    private javax.swing.JButton btFindDepartment;
    private javax.swing.JButton btInsertData;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbFilter;
    private javax.swing.JMenuItem miDelete;
    private javax.swing.JPopupMenu popDel;
    private javax.swing.JTable tbResult;
    private javax.swing.JTextField tfDepartment;
    // End of variables declaration//GEN-END:variables
}
