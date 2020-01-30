package at.htlkaindorf.bsp_105_pocketcalculator.bl;

public class Stack {

    private double[] values = new double[10];
    private int tos = 0;

    public void push(double value) {
        if(isFull()) {
            throw new RuntimeException("Stack overflow");
        }
        values[tos++] = value;
    }

    public double pop() {
        if(isEmpty()) {
            throw new RuntimeException("Stack empty");
        }
        return values[--tos];
    }

    public boolean isEmpty(){
        return tos==0;
    }

    public boolean isFull(){
        return tos==values.length;
    }

    public void clear() {
        tos=0;
    }

}
