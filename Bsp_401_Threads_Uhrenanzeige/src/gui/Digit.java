package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Digit extends JLabel{

    private int value = 0;
    
    public Digit(int value) {
        this.value = value;
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
                return new int[]{2, 3, 7, 8, 7, 3};
            case 3:
                return new int[]{2, 3, 7, 8, 7, 3};
            case 6:
                return new int[]{2, 3, 7, 8, 7, 3};
            case 1:
                return new int[]{7, 8, 9, 9, 8, 7};
            case 2:
                return new int[]{7, 8, 9, 9, 8, 7};
            case 4:
                return new int[]{1, 2, 3, 3, 2, 1};
            case 5:
                return new int[]{1, 2, 3, 3, 2, 1};
            default:
                return new int[]{2, 3, 7, 8, 7, 3};
        }
    }
    
    private int[] getCoordinateOfSegmentY(int seg) {
        switch(seg) {
            case -2: //deoppelpunkt unten
                return new int[]{11,11,11,11,11,11};
            case -1: //doppelpunkt oben
                return new int[]{5, 5, 5, 5, 5, 5};
            case 0:
                return new int[]{2, 1, 1, 2, 3, 3};
            case 3:
                return new int[]{16, 15, 15, 16, 17, 17};
            case 6:
                return new int[]{9, 8, 8, 9, 10, 10};
            case 1:
                return new int[]{3, 2, 3, 8, 9, 8};
            case 5:
                return new int[]{3, 2, 3, 8, 9, 8};
            case 2:
                return new int[]{10, 9, 10, 15, 16, 15};
            case 4:
                return new int[]{10, 9, 10, 15, 16, 15};
            default:
                return new int[]{2, 1, 1, 2, 1, 1};
        }
    }
    
    public void setValue(int value) {
        this.value = value;
    }

    public static void main(String[] args) throws InterruptedException {      
        JFrame uhr = new JFrame("Beispiel JFrame");     
        uhr.setSize(10*11,10*18);
        uhr.setLocationRelativeTo(null);
        uhr.setVisible(true);    
        uhr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Digit dg = new Digit(0); 
        
        uhr.getContentPane().add(dg);
        
        int cc = -2;
        while(cc<=9) {
            dg.setValue(cc++);
            dg.repaint();
            Thread.sleep(1000);
        }
        
    }
    
}
