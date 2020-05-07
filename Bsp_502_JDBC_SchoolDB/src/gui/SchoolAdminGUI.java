package gui;

import beans.Student;
import bl.DataHandler;
import db.DB_Access;
import java.awt.Color;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class SchoolAdminGUI extends javax.swing.JFrame {

    private DB_Access DB;
    
    private List<Student> students = new ArrayList<>();
    
    private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    public SchoolAdminGUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnBt = new javax.swing.JPanel();
        btConn = new javax.swing.JButton();
        btImport = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btQuit = new javax.swing.JButton();
        pnMain = new javax.swing.JPanel();
        pnClass = new javax.swing.JPanel();
        lbClass = new javax.swing.JLabel();
        cbClass = new javax.swing.JComboBox<>();
        pnInput = new javax.swing.JPanel();
        lbKatnr = new javax.swing.JLabel();
        tfKatnr = new javax.swing.JTextField();
        lbKLasse = new javax.swing.JLabel();
        tfKlasse = new javax.swing.JTextField();
        lbVor = new javax.swing.JLabel();
        tfVor = new javax.swing.JTextField();
        lbNach = new javax.swing.JLabel();
        tfNach = new javax.swing.JTextField();
        lbGebDat = new javax.swing.JLabel();
        tfGebDat = new javax.swing.JTextField();
        lbAlt = new javax.swing.JLabel();
        tfAlt = new javax.swing.JTextField();
        spacer = new javax.swing.JLabel();
        pnChoose = new javax.swing.JPanel();
        btNew = new javax.swing.JButton();
        btStart = new javax.swing.JButton();
        btEnd = new javax.swing.JButton();
        btBack = new javax.swing.JButton();
        btForward = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnBt.setBackground(new java.awt.Color(51, 51, 51));
        pnBt.setLayout(new javax.swing.BoxLayout(pnBt, javax.swing.BoxLayout.LINE_AXIS));

        btConn.setBackground(new java.awt.Color(0, 153, 0));
        btConn.setForeground(new java.awt.Color(255, 255, 255));
        btConn.setText("Verbinden");
        btConn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onConn(evt);
            }
        });
        pnBt.add(btConn);

        btImport.setBackground(new java.awt.Color(204, 204, 0));
        btImport.setText("Importieren");
        btImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onImport(evt);
            }
        });
        pnBt.add(btImport);

        jButton1.setBackground(new java.awt.Color(153, 255, 0));
        jButton1.setText("Exportieren");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onExport(evt);
            }
        });
        pnBt.add(jButton1);

        btQuit.setBackground(new java.awt.Color(0, 153, 255));
        btQuit.setForeground(new java.awt.Color(255, 255, 255));
        btQuit.setText("Beenden");
        btQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onQuit(evt);
            }
        });
        pnBt.add(btQuit);

        getContentPane().add(pnBt, java.awt.BorderLayout.NORTH);

        pnMain.setBackground(new java.awt.Color(51, 51, 51));
        pnMain.setLayout(new java.awt.BorderLayout());

        pnClass.setBackground(new java.awt.Color(51, 51, 51));
        pnClass.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        lbClass.setForeground(new java.awt.Color(255, 255, 255));
        lbClass.setText("Klasse:");
        pnClass.add(lbClass);

        cbClass.setBackground(new java.awt.Color(153, 51, 255));
        cbClass.setForeground(new java.awt.Color(255, 255, 255));
        cbClass.setPreferredSize(new java.awt.Dimension(100, 20));
        cbClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onClassSelection(evt);
            }
        });
        pnClass.add(cbClass);

        pnMain.add(pnClass, java.awt.BorderLayout.NORTH);

        pnInput.setBackground(new java.awt.Color(51, 51, 51));
        pnInput.setLayout(new java.awt.GridBagLayout());

        lbKatnr.setForeground(new java.awt.Color(255, 255, 255));
        lbKatnr.setText("Kat-Nr");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        pnInput.add(lbKatnr, gridBagConstraints);

        tfKatnr.setEditable(false);
        tfKatnr.setText("01");
        tfKatnr.setMinimumSize(new java.awt.Dimension(30, 30));
        tfKatnr.setPreferredSize(new java.awt.Dimension(50, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnInput.add(tfKatnr, gridBagConstraints);

        lbKLasse.setForeground(new java.awt.Color(255, 255, 255));
        lbKLasse.setText("Klasse:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnInput.add(lbKLasse, gridBagConstraints);

        tfKlasse.setEditable(false);
        tfKlasse.setText("Best");
        tfKlasse.setMinimumSize(new java.awt.Dimension(100, 30));
        tfKlasse.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 10);
        pnInput.add(tfKlasse, gridBagConstraints);

        lbVor.setForeground(new java.awt.Color(255, 255, 255));
        lbVor.setText("Vorname:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        pnInput.add(lbVor, gridBagConstraints);

        tfVor.setEditable(false);
        tfVor.setText("Tony");
        tfVor.setMinimumSize(new java.awt.Dimension(100, 30));
        tfVor.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnInput.add(tfVor, gridBagConstraints);

        lbNach.setForeground(new java.awt.Color(255, 255, 255));
        lbNach.setText("Nachname:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnInput.add(lbNach, gridBagConstraints);

        tfNach.setEditable(false);
        tfNach.setText("Stark");
        tfNach.setMinimumSize(new java.awt.Dimension(100, 30));
        tfNach.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 10);
        pnInput.add(tfNach, gridBagConstraints);

        lbGebDat.setForeground(new java.awt.Color(255, 255, 255));
        lbGebDat.setText("Geb.dat:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        pnInput.add(lbGebDat, gridBagConstraints);

        tfGebDat.setEditable(false);
        tfGebDat.setText("29.05.1970");
        tfGebDat.setMinimumSize(new java.awt.Dimension(100, 30));
        tfGebDat.setPreferredSize(new java.awt.Dimension(100, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        pnInput.add(tfGebDat, gridBagConstraints);

        lbAlt.setForeground(new java.awt.Color(255, 255, 255));
        lbAlt.setText("Alter:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnInput.add(lbAlt, gridBagConstraints);

        tfAlt.setEditable(false);
        tfAlt.setText("50");
        tfAlt.setMinimumSize(new java.awt.Dimension(30, 30));
        tfAlt.setPreferredSize(new java.awt.Dimension(50, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 10);
        pnInput.add(tfAlt, gridBagConstraints);

        spacer.setText("                      ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnInput.add(spacer, gridBagConstraints);

        pnMain.add(pnInput, java.awt.BorderLayout.CENTER);

        pnChoose.setBackground(new java.awt.Color(51, 51, 51));
        pnChoose.setLayout(new java.awt.GridBagLayout());

        btNew.setBackground(new java.awt.Color(0, 153, 153));
        btNew.setText("Neu");
        btNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onNew(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnChoose.add(btNew, gridBagConstraints);

        btStart.setBackground(new java.awt.Color(102, 102, 102));
        btStart.setForeground(new java.awt.Color(255, 255, 255));
        btStart.setText("|<");
        btStart.setActionCommand("start");
        btStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSwitch(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnChoose.add(btStart, gridBagConstraints);

        btEnd.setBackground(new java.awt.Color(102, 102, 102));
        btEnd.setForeground(new java.awt.Color(255, 255, 255));
        btEnd.setText(">|");
        btEnd.setActionCommand("end");
        btEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSwitch(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        pnChoose.add(btEnd, gridBagConstraints);

        btBack.setBackground(new java.awt.Color(102, 102, 102));
        btBack.setForeground(new java.awt.Color(255, 255, 255));
        btBack.setText("<");
        btBack.setActionCommand("back");
        btBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSwitch(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnChoose.add(btBack, gridBagConstraints);

        btForward.setBackground(new java.awt.Color(102, 102, 102));
        btForward.setForeground(new java.awt.Color(255, 255, 255));
        btForward.setText(">");
        btForward.setActionCommand("forward");
        btForward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSwitch(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnChoose.add(btForward, gridBagConstraints);

        pnMain.add(pnChoose, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(pnMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onConn(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onConn
        try {
            if(!DB_Access.getInstance().isConnected()) {
                DB_Access.getInstance().connect();
                btConn.setBackground(Color.RED);
                btConn.setText("Trennen");
                changeEditMode(false);
                DB_Access.getInstance().deleteAll();
            } else {
                DB_Access.getInstance().disconnect();
                btConn.setBackground(Color.GREEN);
                btConn.setText("Verbinden");
                cbClass.removeAllItems();
                changeEditMode(false);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Es ist ein Datenbankfehler aufgetreten!");
        }
    }//GEN-LAST:event_onConn

    private void changeEditMode(boolean to) {
        tfKatnr.setEditable(to);
        tfKlasse.setEditable(to);
        tfVor.setEditable(to);
        tfNach.setEditable(to);
        tfGebDat.setEditable(to);
        tfAlt.setEditable(to);
    }
    
    private void onQuit(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onQuit
        System.exit(0);
    }//GEN-LAST:event_onQuit
    
    private void updateStudent(Student student) {
        tfKatnr.setText(""+student.getCatalogNo());
        tfKlasse.setText(student.getClassName());
        tfVor.setText(student.getFirstname());
        tfNach.setText(student.getSurname());
        tfGebDat.setText(DTF.format(student.getBirthdate()));
        tfAlt.setText(""+Period.between(student.getBirthdate(), LocalDate.now()).getYears());
    }
    
    private void onImport(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onImport
        try {
            if(!DB_Access.getInstance().isConnected()){
                JOptionPane.showMessageDialog(this, "Du musst dich zuerst mit der Datenbank verbinden!");
                return;
            }
            DB_Access.getInstance().deleteAll();
            cbClass.removeAllItems();
            
            List<Student> ss = DataHandler.loadData();
            ss.sort(Comparator.comparing(Student::getSurname).thenComparing(Student::getFirstname));
            
            HashMap<String,Integer> LastKatNr = new HashMap<>();
            
            for(Student s : ss) {
                if(!LastKatNr.containsKey(s.getClassName())) {
                    DB_Access.getInstance().insertClass(s.getClassName());
                    LastKatNr.put(s.getClassName(), 1);
                } else
                    LastKatNr.put(s.getClassName(), LastKatNr.get(s.getClassName())+1);
                s.setCatalogNo(LastKatNr.get(s.getClassName()));
                DB_Access.getInstance().insertStudent(s);
            }
            for(String s : DB_Access.getInstance().getAllGrades())
                    cbClass.addItem(s);
            
            students = DB_Access.getInstance().getAllStudentsFor((String)cbClass.getSelectedItem());
            updateStudent(students.get(0));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "SQL-Error: Gibt es die Tables: grade & student??");
        }
    }//GEN-LAST:event_onImport

    private void onClassSelection(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onClassSelection
        try {
            students = DB_Access.getInstance().getAllStudentsFor((String)cbClass.getSelectedItem());
            updateStudent(students.get(0));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Es ist ein Datenbankfehler aufgetreten!");
        } catch(RuntimeException e) {
            return;
        }
    }//GEN-LAST:event_onClassSelection

    private void onSwitch(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onSwitch
        try {
            switch(evt.getActionCommand()) {
                case "start":
                    updateStudent(students.get(0));
                    break;
                case "end":
                    updateStudent(students.get(students.size()-1));
                    break;
                case "back":
                    updateStudent(students.get(Integer.parseInt(tfKatnr.getText())-1-1));
                    break;
                case "forward":
                    updateStudent(students.get(Integer.parseInt(tfKatnr.getText())-1+1));
                    break;
                default:
                    break;
            }
        }catch(IndexOutOfBoundsException e) {
            return;
        }
    }//GEN-LAST:event_onSwitch

    private void onNew(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onNew
        try {
            if(!DB_Access.getInstance().isConnected()) {
                JOptionPane.showMessageDialog(this, "Du musst dich zuerst mit der Datenbank verbinden!");
                return;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Es ist ein Datenbankfehler aufgetreten!");
            return;
        }
                
        String first = JOptionPane.showInputDialog("Vorname?");
        String last = JOptionPane.showInputDialog("Nachname?");
        Object[] options = {"f", "m"};
        String gender = (String)options[JOptionPane.showOptionDialog(this, "Geschlecht?", "Geschlecht wählen", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, options, options[0])];
        
        LocalDate geb = null;
        while(geb == null) {
            try {
                geb = LocalDate.parse(JOptionPane.showInputDialog("Geburtsdatum?"),DTF);
            }catch(DateTimeParseException e) {
                JOptionPane.showMessageDialog(this, "Bitte das Datum in folgender Form eingeben: DD.MM.YYYY und auch innerhalb des Logischen bleiben!");
            } catch(NullPointerException e) {
                JOptionPane.showMessageDialog(this, "Bitte gib ein Datum ein :(");
            }
        }
        
        String classname = JOptionPane.showInputDialog("Klasse?");
        
        if(first == null || last == null || gender == null || classname == null) {
            return;
        }
        
        last = last.toUpperCase();
        
        try {
            Student toAdd = new Student(classname, -1, first, last, gender, geb);
            List<Student> ss = DB_Access.getInstance().getAllStudentsFor(classname);
            
            if(ss.size() == 0) {
                toAdd.setCatalogNo(1);
                DB_Access.getInstance().insertClass(classname);
                DB_Access.getInstance().insertStudent(toAdd);
                cbClass.addItem(classname);
                return;
            } 
            /*
            if(DB_Access.getInstance().getGradeId(classname) == -1) {
                DB_Access.getInstance().insertClass(classname);
                cbClass.addItem(classname);
            }*/
            
            ss.add(toAdd);
            ss.sort(Comparator.comparing(Student::getSurname).thenComparing(Student::getFirstname));
            int lastKatNr = 0;
            DB_Access.getInstance().deleteFor(classname);
            for(Student s : ss) {
                s.setCatalogNo(++lastKatNr);
                DB_Access.getInstance().insertStudent(s);
            }
            
            students = DB_Access.getInstance().getAllStudentsFor((String)cbClass.getSelectedItem());
            updateStudent(toAdd);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Es ist ein Datenbankfehler aufgetreten!");
            return;
        }
                
        JOptionPane.showMessageDialog(this, "Ein neuer Schüler wurde zur Klasse " + (String)cbClass.getSelectedItem() + " hinzugefügt!");
    }//GEN-LAST:event_onNew

    private void onExport(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onExport
        
        try {
            List<Student> toExport = DB_Access.getInstance().getAllStudents();
            if(DataHandler.saveData(toExport))
                JOptionPane.showMessageDialog(this, "Exportiert unter \"res/output.csv\"!");
            else
                JOptionPane.showMessageDialog(this, "Es gab einen Fehler beim Speichern!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Es ist ein Datenbankfehler aufgetreten!");
            return;
        } catch(RuntimeException e) {
            JOptionPane.showMessageDialog(this, "Du musst dich zuerst mit der Datenbank verbinden!");
            return;
        }
        
    }//GEN-LAST:event_onExport
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
            java.util.logging.Logger.getLogger(SchoolAdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SchoolAdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SchoolAdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SchoolAdminGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SchoolAdminGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBack;
    private javax.swing.JButton btConn;
    private javax.swing.JButton btEnd;
    private javax.swing.JButton btForward;
    private javax.swing.JButton btImport;
    private javax.swing.JButton btNew;
    private javax.swing.JButton btQuit;
    private javax.swing.JButton btStart;
    private javax.swing.JComboBox<String> cbClass;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lbAlt;
    private javax.swing.JLabel lbClass;
    private javax.swing.JLabel lbGebDat;
    private javax.swing.JLabel lbKLasse;
    private javax.swing.JLabel lbKatnr;
    private javax.swing.JLabel lbNach;
    private javax.swing.JLabel lbVor;
    private javax.swing.JPanel pnBt;
    private javax.swing.JPanel pnChoose;
    private javax.swing.JPanel pnClass;
    private javax.swing.JPanel pnInput;
    private javax.swing.JPanel pnMain;
    private javax.swing.JLabel spacer;
    private javax.swing.JTextField tfAlt;
    private javax.swing.JTextField tfGebDat;
    private javax.swing.JTextField tfKatnr;
    private javax.swing.JTextField tfKlasse;
    private javax.swing.JTextField tfNach;
    private javax.swing.JTextField tfVor;
    // End of variables declaration//GEN-END:variables
}
