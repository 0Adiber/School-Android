package at.adiber.bl;

import at.adiber.beans.Employee;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.Format;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class AddEmployeeDialog extends JDialog implements ActionListener{
    
    private Employee data;
    
    private JTextField tfVorname, tfNachname;
    private JFormattedTextField tfGehalt, tfPersNo, tfAbtNo;
    private JComboBox<String> cbGender;
    private DatePicker datePicker;
    
    private JButton btAdd, btCancel;
    
    private int currentY = 0;
    private GridBagConstraints gbc;
    private JPanel panel;
    
    private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd:mm:yyyy");
        
    public AddEmployeeDialog(Frame parent) {
        super(parent, "Enter Emp Data", true);
        Point loc = parent.getLocation();
        setLocation(loc.x+100, loc.y+100);
        panel = new JPanel();
        
        
        setMinimumSize(new Dimension(500, 420));
        panel = new JPanel();
        panel.setMinimumSize(new Dimension(500, 420));
        
        panel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 10, 0, 0);
                
        JLabel lbPersNo = new JLabel("Personal Nr.:");
        tfPersNo = new JFormattedTextField(NumberFormat.getIntegerInstance());
        addRow(lbPersNo, tfPersNo);
        
        JLabel lbVorname = new JLabel("Vorname:");
        tfVorname = new JTextField(20);
        addRow(lbVorname, tfVorname);
        
        JLabel lbNachname = new JLabel("Nachname:");
        tfNachname = new JTextField(20);
        addRow(lbNachname, tfNachname);
        
        JLabel lbGeschlecht = new JLabel("Geschlecht:");
        cbGender = new JComboBox<String>(new String[]{"Female", "Male"});
        addRow(lbGeschlecht, cbGender);
        
        JLabel lbGehalt = new JLabel("Gehalt:");
        tfGehalt = new JFormattedTextField(NumberFormat.getNumberInstance());
        tfGehalt.setColumns(10);
        addRow(lbGehalt, tfGehalt);
        
        JLabel lbAbt = new JLabel("Abteilung:");
        tfAbtNo = new JFormattedTextField(NumberFormat.getIntegerInstance());
        tfAbtNo.setColumns(3);
        addRow(lbAbt, tfAbtNo);
        
        addDatePicker();
        
        btAdd = new JButton("Add Employee");
        btCancel = new JButton("Cancel");
        btAdd.addActionListener(this);
        btCancel.addActionListener(this);
                
        gbc.gridx = 0;
        gbc.gridy = currentY++;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        panel.add(btCancel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(btAdd, gbc);
        
        getContentPane().add(panel);
        pack();
    }
    
    public void addRow(JLabel lb, Component comp) {
        gbc.gridx = 0;
        gbc.gridy = currentY++;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        panel.add(lb, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        panel.add(comp, gbc);
    }
    
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if(source == btAdd) {
            try {
                data = new Employee(Integer.parseInt(tfPersNo.getValue().toString())
                        , tfNachname.getText()
                        , tfVorname.getText()
                        , datePicker.getValue()
                        , Double.parseDouble(tfGehalt.getText())
                        , Integer.parseInt(tfAbtNo.getValue().toString())
                        , (String)cbGender.getSelectedItem());
            } catch(NullPointerException e) {
                return;
            }
        } else {
            data = null;
        }
        
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    
    public Employee run() {
        this.setVisible(true);
        return data;
    }
    
    private void addDatePicker() {
        JFXPanel fx = new JFXPanel();
        // add the input field (datepicker etc)
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = currentY;
        gbc.weightx = 2;
        gbc.insets = new java.awt.Insets(0, 10, 0, 0);
        fx.setMinimumSize(new Dimension(0, 40));
        fx.setOpaque(true);
        Color color = panel.getBackground();
        fx.setBackground(color);

        panel.add(fx, gbc);
        
        Platform.runLater(() -> {
            // this is just weird a lot of times
            fx.setScene(createDatePicker());
            setMinimumSize(new Dimension(300, getHeight()+50));
            pack();
        });
       
        currentY++;
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
    }

    private Scene createDatePicker() {
        GridPane grid = new GridPane();

        Label lbDate = new Label("Geburtsdatum:");
        datePicker = new DatePicker(LocalDate.now());
        grid.getColumnConstraints().add(new ColumnConstraints(125));
//        grid.getColumnConstraints().add(new ColumnConstraints(Double.MAX_VALUE));
        grid.add(lbDate, 0, 0);
        grid.add(datePicker, 1, 0);

        // Vbox and scene
        VBox vbox = new VBox(20);
//        vbox.setPadding(new javafx.geometry.Insets(15, 15, 15, 15));
        vbox.getChildren().addAll(grid);
        Color bg = panel.getBackground();
        javafx.scene.paint.Color color = javafx.scene.paint.Color.rgb(bg.
                getRed(), bg.getGreen(), bg.getBlue());
        vbox.setBackground(new Background(new BackgroundFill(color,
                CornerRadii.EMPTY, Insets.EMPTY)));
        vbox.setPrefHeight(30);

        Scene scene = new Scene(vbox);
        return scene;
    }
    
}
