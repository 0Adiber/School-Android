package gui;

import bl.Account;
import bl.AccountUser;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class AccountGui extends javax.swing.JFrame {

    private UserListModel userModel = new UserListModel();
    private Account account = null;
    
    private List<Thread> threads = new ArrayList<>();
    
    public AccountGui() {
        initComponents();
        setSize(400, 400);
        setLocationRelativeTo(null);    
        
        liUsers.setModel(userModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        muUser = new javax.swing.JPopupMenu();
        miAdd = new javax.swing.JMenuItem();
        miPerform = new javax.swing.JMenuItem();
        muAccount = new javax.swing.JPopupMenu();
        miCreate = new javax.swing.JMenuItem();
        pane = new javax.swing.JScrollPane();
        taDebug = new javax.swing.JTextArea();
        pane2 = new javax.swing.JScrollPane();
        liUsers = new javax.swing.JList<>();
        lbAccount = new javax.swing.JLabel();

        miAdd.setText("add User");
        miAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onAddUser(evt);
            }
        });
        muUser.add(miAdd);

        miPerform.setText("perform account test");
        miPerform.setActionCommand("perform Account test");
        miPerform.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onPerformTests(evt);
            }
        });
        muUser.add(miPerform);

        miCreate.setText("create Account");
        miCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onCreateAccount(evt);
            }
        });
        muAccount.add(miCreate);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pane.setPreferredSize(new java.awt.Dimension(300, 114));

        taDebug.setEditable(false);
        taDebug.setColumns(20);
        taDebug.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        taDebug.setRows(5);
        taDebug.setBorder(javax.swing.BorderFactory.createTitledBorder("Log-output"));
        taDebug.setComponentPopupMenu(muAccount);
        pane.setViewportView(taDebug);

        getContentPane().add(pane, java.awt.BorderLayout.CENTER);

        pane2.setPreferredSize(new java.awt.Dimension(120, 130));

        liUsers.setBorder(javax.swing.BorderFactory.createTitledBorder("User"));
        liUsers.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        liUsers.setComponentPopupMenu(muUser);
        liUsers.setMaximumSize(new java.awt.Dimension(100, 0));
        liUsers.setMinimumSize(new java.awt.Dimension(100, 0));
        liUsers.setPreferredSize(new java.awt.Dimension(100, 0));
        pane2.setViewportView(liUsers);

        getContentPane().add(pane2, java.awt.BorderLayout.WEST);

        lbAccount.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        lbAccount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbAccount.setText("50,00 Euro");
        lbAccount.setBorder(javax.swing.BorderFactory.createTitledBorder("Account"));
        getContentPane().add(lbAccount, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onAddUser(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onAddUser
        String name = JOptionPane.showInputDialog(this, "Enter name");
        if(name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name darf nicht leer sein!");
            return;
        }
        
        AccountUser usr = new AccountUser(name.trim(), account, 10, taDebug);
        
        if(userModel.exists(usr)){
            JOptionPane.showMessageDialog(this, "User already exists!");
            return;
        }
        
        userModel.add(usr);            
    }//GEN-LAST:event_onAddUser

    private void onPerformTests(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onPerformTests

        if(account == null) {
            JOptionPane.showMessageDialog(this, "You have to create an account first!!");
            return;
        }
       
        for(Thread t : threads) {
            if(t.isAlive()) {
                JOptionPane.showMessageDialog(this, "Test still in progress!");
                return;
            }
        }
        
        threads.clear();
        List<AccountUser> selectedUsers = liUsers.getSelectedValuesList();
        
        selectedUsers.forEach(user -> {
            user.setAccount(account);
            
            Thread t = new Thread(user, user.getName());
            t.setPriority(Thread.NORM_PRIORITY+1);
            threads.add(t);
            t.start();
        });
        
        
    }//GEN-LAST:event_onPerformTests

    private void onCreateAccount(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onCreateAccount
        account = new Account(50, taDebug, lbAccount);
    }//GEN-LAST:event_onCreateAccount

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
            java.util.logging.Logger.getLogger(AccountGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccountGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccountGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccountGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccountGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbAccount;
    private javax.swing.JList<AccountUser> liUsers;
    private javax.swing.JMenuItem miAdd;
    private javax.swing.JMenuItem miCreate;
    private javax.swing.JMenuItem miPerform;
    private javax.swing.JPopupMenu muAccount;
    private javax.swing.JPopupMenu muUser;
    private javax.swing.JScrollPane pane;
    private javax.swing.JScrollPane pane2;
    private javax.swing.JTextArea taDebug;
    // End of variables declaration//GEN-END:variables
}
