package euler59;

public class Touple {
    private int same;
    private String pw;
    private int sum;
    
    public Touple(int same, String pw, int sum) {
        this.same = same;
        this.pw = pw;
        this.sum = sum;
    }

    public int getSame() {
        return same;
    }

    public String getPw() {
        return pw;
    } 
    
    public int getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return String.format("%s --> %d", pw, same);
    }

}
