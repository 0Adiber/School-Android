package gui;

import beans.Department;
import beans.DepartmentManager;
import beans.Employee;
import beans.GehaltHistory;
import bl.EmployeeModel;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import database.DBAccess;
import java.awt.event.ActionEvent;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

public class EmployeesGUI extends javax.swing.JFrame {

    private EmployeeModel etm;
    private List<Department> depts;
    
    private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public static EmployeesGUI gui;
    
    public EmployeesGUI() {
        initComponents();
        gui = this;
                
        // styling of the html in the JEditorPane
        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet styles = kit.getStyleSheet();
        styles.addRule(".red {color: red;}");
        styles.addRule(".green {color: green;}");
        styles.addRule(".gray {color: gray;}");
        kit.setStyleSheet(styles);
        tpMan.setEditorKit(kit);
        tpGehalt.setEditorKit(kit);
        
        setLocationRelativeTo(null);
        
        dpBirth.addDateChangeListener(new DateChangeListener() {
            @Override
            public void dateChanged(DateChangeEvent arg0) {
                onBirthBeforeSelect(new ActionEvent(dpBirth, ActionEvent.ACTION_FIRST, ""));
            }
        });
                
        taEmps.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                int row = taEmps.getSelectedRow();

                if(row == -1)
                    return;
                
                Employee clicked = etm.getEmp(row);

                if(clicked != null) {
                    try {
                        String hisRes = "";
                        for(GehaltHistory h : DBAccess.getInstance().getHistoryForEmp(clicked.getEmpno())) {
                            hisRes+=h.toString();
                        }
                        tpGehalt.setText(hisRes);
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(EmployeesGUI.gui, "Missing the Postgres Library!");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(EmployeesGUI.gui, "There was an error retrieving the data from the database");
                    }
                }
            }
        });
        
        try {            
            depts = DBAccess.getInstance().getAllDepartments();
            depts.forEach(d -> {
                cbDept.addItem(d);
            });
            
            etm = new EmployeeModel();
            taEmps.setModel(etm);
            etm.setEmps(DBAccess.getInstance().getAllEmployeesBy(depts.get(0).getDeptno(), DTF.format(LocalDate.now()), "M", "F"));
            
            String manRes = "";
            for(DepartmentManager m : DBAccess.getInstance().getDeptMan(depts.get(0).getDeptno())) {
                manRes+=m.toString();
            }
            tpMan.setText(manRes);
            
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
        dpBirth = new com.github.lgooddatepicker.components.DatePicker();
        cbMale = new javax.swing.JCheckBox();
        cbFemale = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tpMan = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tpGehalt = new javax.swing.JTextPane();
        taEmpsScroll = new javax.swing.JScrollPane();
        taEmps = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnleft.setLayout(new java.awt.GridLayout(3, 1));

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
        pnFilter.add(dpBirth);

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

        tpMan.setBorder(javax.swing.BorderFactory.createTitledBorder("Management"));
        tpMan.setContentType("text/html"); // NOI18N
        tpMan.setEditable(false);
        tpMan.setText("");
        jScrollPane2.setViewportView(tpMan);

        pnleft.add(jScrollPane2);

        tpGehalt.setBorder(javax.swing.BorderFactory.createTitledBorder("Gehaltshistorie"));
        tpGehalt.setContentType("text/html"); // NOI18N
        jScrollPane1.setViewportView(tpGehalt);

        pnleft.add(jScrollPane1);

        getContentPane().add(pnleft, java.awt.BorderLayout.WEST);

        taEmpsScroll.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                onWheelMove(evt);
            }
        });

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
        taEmps.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        taEmpsScroll.setViewportView(taEmps);

        getContentPane().add(taEmpsScroll, java.awt.BorderLayout.CENTER);

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
            
            String manRes = "";
            for(DepartmentManager m : DBAccess.getInstance().getDeptMan(d.getDeptno())) {
                manRes+=m.toString();
            }
            tpMan.setText(manRes);
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Missing the Postgres Library!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "There was an error retrieving the data from the database");
        }
    }//GEN-LAST:event_onDeptChange

    private void onBirthBeforeSelect(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onBirthBeforeSelect
        if(etm == null)
            return;
        
        try {
            Department d = (Department)cbDept.getSelectedItem();
            LocalDate selected = LocalDate.now();
            if(cbBirth.isSelected()) {
                selected = dpBirth.getDate() == null ? LocalDate.now() : dpBirth.getDate();
            }
            
            if(cbMale.isSelected() && cbFemale.isSelected())
                etm.setEmps(DBAccess.getInstance().getAllEmployeesBy(d.getDeptno(), DTF.format(selected), new String[]{"M","F"}));
            else if(cbMale.isSelected())
                etm.setEmps(DBAccess.getInstance().getAllEmployeesBy(d.getDeptno(), DTF.format(selected), new String[]{"M","M"}));
            else if(cbFemale.isSelected())
                etm.setEmps(DBAccess.getInstance().getAllEmployeesBy(d.getDeptno(), DTF.format(selected), new String[]{"F","F"}));
            else
                etm.setEmps(DBAccess.getInstance().getAllEmployeesBy(d.getDeptno(), DTF.format(selected), "M", "F"));
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Missing the Postgres Library!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "There was an error retrieving the data from the database");
        }
    }//GEN-LAST:event_onBirthBeforeSelect

    private void onWheelMove(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_onWheelMove
        if(evt.getWheelRotation() > -1) {
            try {
                Employee toAdd = DBAccess.getInstance().getScrollEmployee();
                if(toAdd == null) {
                    JOptionPane.showMessageDialog(this, "Aus einem unbekannten Grund wurde das ResultSet geschlossen");
                    return;
                }
                etm.addEmp(toAdd);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Missing the Postgres Library!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "There was an error retrieving the data from the database");
            }
        }
    }//GEN-LAST:event_onWheelMove

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
    private com.github.lgooddatepicker.components.DatePicker dpBirth;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbDept;
    private javax.swing.JPanel pnFilter;
    private javax.swing.JPanel pnleft;
    private javax.swing.JTable taEmps;
    private javax.swing.JScrollPane taEmpsScroll;
    private javax.swing.JTextPane tpGehalt;
    private javax.swing.JTextPane tpMan;
    // End of variables declaration//GEN-END:variables
}
