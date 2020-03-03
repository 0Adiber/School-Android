package bl;

import java.text.NumberFormat;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Account {
    
    private double money;
    private JTextArea debug;
    private JLabel label;
    
    public Account(double money, JTextArea debug, JLabel label) {
        this.money = money;
        this.debug = debug;
        this.label = label;
        
        this.debug.setText("Created Account with " + money + "€\n");
        this.label.setText(NumberFormat.getNumberInstance().format(money) + " Euro");
    }
    
    public double getMoney() {
        return money;
    }
    
    public void performMoneyTransaction(long amount) {
        if(money + amount < 0)
            throw new RuntimeException("Money on Account cannot be negative");
        
        this.money += amount;
        this.label.setText(String.format("%.2f Euro", money));
    }

}
