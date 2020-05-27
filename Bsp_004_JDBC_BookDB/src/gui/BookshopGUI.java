package gui;

import beans.Book;
import beans.Genre;
import beans.Publisher;
import database.DB_Access;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

public class BookshopGUI extends javax.swing.JFrame {
    
    private DB_Access DB;
    
    public BookshopGUI() {
        initComponents();
        
        try {
            DB = DB_Access.getInstance();
            DB.connect();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Could not connect to DB!\nDoes \"booksdb\" exist?");
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Please add the PostgreSQL JDBC Library!!");
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        
        try {
            if(DB.isConnected()) {
                addWindowListener(new ClosingListener());
                
                DB.getPublishers().forEach(p -> {
                    cbVerlag.addItem(p);
                });
                cbVerlag.setSelectedIndex(0);
                
                DB.getGenresBy(cbVerlag.getItemAt(0)).forEach(g -> {
                    cbGenre.addItem(g);
                });
                cbGenre.setSelectedIndex(0);
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error in lines ~39-50!");
            ex.printStackTrace();
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        
        // styling of the html in the JEditorPane
        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet styles = kit.getStyleSheet();
        styles.addRule("ul, li {list-style-type: none; margin: 0; padding: 0;}");
        styles.addRule(".fancy {color: orange;}");
        styles.addRule("h1{text-align:center;color: red; font-size:20px;}");
        styles.addRule("h2 {padding: 0;margin:0;font-style:italic;font-weight:200;font-size:15px;text-align:center;}");
        kit.setStyleSheet(styles);
        epDetails.setEditorKit(kit);

        setSize(1000, 700);
        setLocationRelativeTo(null);
    }

    
    //to disconnect db
    private class ClosingListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("Disconnecting from DB..");
            try {
                DB.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(BookshopGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            e.getWindow().dispose();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        groupSearch = new javax.swing.ButtonGroup();
        pnSuchen = new javax.swing.JPanel();
        pnSuchenLeft = new javax.swing.JPanel();
        lbVerlag = new javax.swing.JLabel();
        lbGenre = new javax.swing.JLabel();
        cbVerlag = new javax.swing.JComboBox<>();
        cbGenre = new javax.swing.JComboBox<>();
        pnSuchenRight = new javax.swing.JPanel();
        tfSearch = new javax.swing.JTextField();
        rbBuch = new javax.swing.JRadioButton();
        rbAutor = new javax.swing.JRadioButton();
        pnBucher = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        liBooks = new javax.swing.JList<>();
        pnDetails = new javax.swing.JPanel();
        details = new javax.swing.JScrollPane();
        epDetails = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnSuchen.setBorder(javax.swing.BorderFactory.createTitledBorder("Suchen"));
        pnSuchen.setLayout(new java.awt.GridLayout(1, 2));

        pnSuchenLeft.setLayout(new java.awt.GridBagLayout());

        lbVerlag.setText("Verlag:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnSuchenLeft.add(lbVerlag, gridBagConstraints);

        lbGenre.setText("Genre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        pnSuchenLeft.add(lbGenre, gridBagConstraints);

        cbVerlag.setAutoscrolls(true);
        cbVerlag.setMaximumSize(new java.awt.Dimension(32000, 100));
        cbVerlag.setMinimumSize(new java.awt.Dimension(300, 50));
        cbVerlag.setPreferredSize(new java.awt.Dimension(300, 25));
        cbVerlag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onComboboxChanged(evt);
            }
        });
        pnSuchenLeft.add(cbVerlag, new java.awt.GridBagConstraints());

        cbGenre.setAutoscrolls(true);
        cbGenre.setMaximumSize(new java.awt.Dimension(32000, 100));
        cbGenre.setMinimumSize(new java.awt.Dimension(300, 50));
        cbGenre.setPreferredSize(new java.awt.Dimension(300, 25));
        cbGenre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onComboboxChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        pnSuchenLeft.add(cbGenre, gridBagConstraints);

        pnSuchen.add(pnSuchenLeft);

        pnSuchenRight.setLayout(new java.awt.GridBagLayout());

        tfSearch.setToolTipText("dfdd");
        tfSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                onKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 3.0;
        pnSuchenRight.add(tfSearch, gridBagConstraints);

        groupSearch.add(rbBuch);
        rbBuch.setSelected(true);
        rbBuch.setText("Buch");
        rbBuch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSearchSelect(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        pnSuchenRight.add(rbBuch, gridBagConstraints);

        groupSearch.add(rbAutor);
        rbAutor.setText("Autor");
        rbAutor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onSearchSelect(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        pnSuchenRight.add(rbAutor, gridBagConstraints);

        pnSuchen.add(pnSuchenRight);

        getContentPane().add(pnSuchen, java.awt.BorderLayout.NORTH);

        pnBucher.setBorder(javax.swing.BorderFactory.createTitledBorder("Bücher"));
        pnBucher.setMinimumSize(new java.awt.Dimension(400, 45));
        pnBucher.setPreferredSize(new java.awt.Dimension(400, 152));
        pnBucher.setLayout(new java.awt.BorderLayout());

        liBooks.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        liBooks.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                onCurrentBookChanged(evt);
            }
        });
        jScrollPane2.setViewportView(liBooks);

        pnBucher.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnBucher, java.awt.BorderLayout.WEST);

        pnDetails.setBorder(javax.swing.BorderFactory.createTitledBorder("Buchdetails"));
        pnDetails.setPreferredSize(new java.awt.Dimension(400, 46));
        pnDetails.setLayout(new java.awt.BorderLayout());

        epDetails.setContentType("text/html"); // NOI18N
        details.setViewportView(epDetails);

        pnDetails.add(details, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnDetails, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_onKeyReleased
        
        try {
            Publisher p = (Publisher)cbVerlag.getSelectedItem();
            Genre g = (Genre)cbGenre.getSelectedItem();
            String author = "", title="";
            if(rbBuch.isSelected()) {
                title=tfSearch.getText();
            } else {
                author=tfSearch.getText();
            }
            List<Book> books = DB.getBookBy(g, p, author, title);
            DefaultListModel<Book> model = new DefaultListModel<>();
            books.forEach(b -> {
                model.addElement(b);
            });
            liBooks.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Could not retrieve books");
            ex.printStackTrace();
        }
            
    }//GEN-LAST:event_onKeyReleased

    private void onComboboxChanged(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onComboboxChanged
        try {
            Publisher p = (Publisher)cbVerlag.getSelectedItem();
            Genre current = (Genre)cbGenre.getSelectedItem();
            
            if(current == null)
                return;
            
            cbGenre.removeAllItems();
            Set<Genre> h = DB.getGenresBy(p);
                        
            h.forEach(g -> {
                cbGenre.addItem(g);
            });
            
            if(!h.contains(current)) {
                cbGenre.setSelectedIndex(0);
            } else {
                cbGenre.setSelectedItem(current);
            }
            
            Genre g = (Genre) cbGenre.getSelectedItem();
            
            String author = "", title="";

            if(rbBuch.isSelected()) {
                title=tfSearch.getText();
            } else {
                author=tfSearch.getText();
            }
            
            List<Book> books = DB.getBookBy(g, p, author, title);
            
            DefaultListModel<Book> model = new DefaultListModel<>();
            books.forEach(b -> {
                model.addElement(b);
            });
            liBooks.setModel(model);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Could not retrieve books");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_onComboboxChanged

    private void onCurrentBookChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_onCurrentBookChanged
        if(liBooks.getSelectedValue() == null)
        return;
        epDetails.setText(liBooks.getSelectedValue().toHTML());
    }//GEN-LAST:event_onCurrentBookChanged

    private void onSearchSelect(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onSearchSelect
        try {
            Publisher p = (Publisher)cbVerlag.getSelectedItem();
            Genre g = (Genre)cbGenre.getSelectedItem();
            String author = "", title="";
            if(rbBuch.isSelected()) {
                title=tfSearch.getText();
            } else {
                author=tfSearch.getText();
            }
            List<Book> books = DB.getBookBy(g, p, author, title);
            DefaultListModel<Book> model = new DefaultListModel<>();
            books.forEach(b -> {
                model.addElement(b);
            });
            liBooks.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Could not retrieve books");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_onSearchSelect

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
            java.util.logging.Logger.getLogger(BookshopGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookshopGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookshopGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookshopGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookshopGUI().setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Genre> cbGenre;
    private javax.swing.JComboBox<Publisher> cbVerlag;
    private javax.swing.JScrollPane details;
    private javax.swing.JEditorPane epDetails;
    private javax.swing.ButtonGroup groupSearch;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbGenre;
    private javax.swing.JLabel lbVerlag;
    private javax.swing.JList<Book> liBooks;
    private javax.swing.JPanel pnBucher;
    private javax.swing.JPanel pnDetails;
    private javax.swing.JPanel pnSuchen;
    private javax.swing.JPanel pnSuchenLeft;
    private javax.swing.JPanel pnSuchenRight;
    private javax.swing.JRadioButton rbAutor;
    private javax.swing.JRadioButton rbBuch;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables
}
