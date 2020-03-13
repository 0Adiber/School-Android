package euler96;

public class Sudoku {
    
    private int[][] field = new int[9][9];
    
    public void setNum(int x, int y, int num) {
        field[x][y] = num;
    }
    
    public int[][] getField() {
        return field;
    }

}
