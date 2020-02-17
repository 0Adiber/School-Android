package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Digit extends JLabel{

    private int value = 0;
    
    public Digit(int value) {
        this.value = value;
        this.setOpaque(true);
        this.setBackground(Color.BLACK);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        double scaleX = this.getWidth()/11.;
        double scaleY = this.getHeight()/18.;
        AffineTransform atrans = new AffineTransform();
        atrans.scale(scaleX, scaleY);
        g2.transform(atrans);
        
        g2.setBackground(Color.BLACK);
        g2.setStroke(new BasicStroke(0.2f));
        
        if(value == -1) {
            g2.setColor(Color.RED);
            g2.fillRect(5, 5, 1, 1);
            g2.fillRect(5, 10,1,1);
            return;
        }
        
        for(int seg : getSegmentsOfNumber(value)) {
            g2.setColor(Color.RED);
            g2.fillPolygon(getCoordinateOfSegmentX(seg), getCoordinateOfSegmentY(seg), 6);
            g2.setColor(Color.BLACK);
            g2.drawPolygon(getCoordinateOfSegmentX(seg), getCoordinateOfSegmentY(seg), 6);
        }
        
    }
    
    private int[] getSegmentsOfNumber(int val) {
        switch(val) {
            case 0:
                return new int[]{0, 1, 2, 3, 4, 5};
            case 1:
                return new int[]{1,2};
            case 2:
                return new int[]{0,1,6,4,3};
            case 3:
                return new int[]{0,1,6,2,3};
            case 4:
                return new int[]{5,6,1,2};
            case 5:
                return new int[]{0,5,6,2,3};
            case 6:
                return new int[]{0,5,4,3,2,6};
            case 7:
                return new int[]{0,1,2};
            case 8:
                return new int[]{0,1,2,3,4,5,6};
            case 9:
                return new int[]{0,1,6,5,2,3};
        }
        return new int[]{};
    }
    
    private int[] getCoordinateOfSegmentX(int seg) {
        switch(seg) {
            case 0:
            case 3:
            case 6:
                return new int[]{2, 3, 8, 9, 8, 3};
            case 1:
            case 2:
                return new int[]{8, 9, 10, 10, 9, 8};
            case 4:
            case 5:
                return new int[]{1, 2, 3, 3, 2, 1};
            default:
                return null;
        }
    }
    
    private int[] getCoordinateOfSegmentY(int seg) {
        switch(seg) {
            case 0:
                return new int[]{2, 1, 1, 2, 3, 3};
            case 3:
                return new int[]{16, 15, 15, 16, 17, 17};
            case 6:
                return new int[]{9, 8, 8, 9, 10, 10};
            case 1:
            case 5:
                return new int[]{3, 2, 3, 8, 9, 8};
            case 2:
            case 4:
                return new int[]{10, 9, 10, 15, 16, 15};
            default:
                return new int[]{2, 1, 1, 2, 1, 1};
        }
    }
    
    public void setValue(int value) {
        this.value = value;
        repaint();
    }

    public static void main(String[] args) throws InterruptedException {      
        JFrame uhr = new JFrame("Beispiel JFrame");     
        uhr.setSize(10*11,10*18);
        uhr.setLocationRelativeTo(null);
         
        uhr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Digit dg = new Digit(0); 
        
        uhr.getContentPane().add(dg);
        
        uhr.setVisible(true);   
        
        int cc = -1;
        while(cc<=9) {
            dg.setValue(cc++);
            dg.repaint();
            Thread.sleep(1000);
        }
        
    }
    
}
