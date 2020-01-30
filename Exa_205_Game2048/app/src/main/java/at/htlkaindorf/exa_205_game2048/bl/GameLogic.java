package at.htlkaindorf.exa_205_game2048.bl;

import java.util.Arrays;
import java.util.Random;

public class GameLogic {

    private int[][] values = new int[4][4];
    private int points;
    private Random rand = new Random();

    public GameLogic() {
        resetGame();
    }

    public void resetGame() {
        this.points = 0;
        for(int[] row : values) {
            Arrays.fill(row,0);
        }

        setNewValue();
        setNewValue();
    }

    public void setNewValue() {
        do {
            int r = rand.nextInt(4);
            int c = rand.nextInt(4);

            if(values[r][c] == 0) {
                values[r][c] = rand.nextInt(3) < 2 ? 2 : 4;
                break;
            }
        } while(true);
    }

    public void makeMove(String direction) {
        switch (direction) {
            case "right":
                mirror();
                break;
            case "down":
                transpose();
                mirror();
                break;
            case "up":
                transpose();
                break;
        }

        //move the guys
        for(int i = 0; i<values.length; i++) {
            for(int j = 1; j<values.length;j++) {

                if(values[i][j] != 0) {
                    int tmp = j;
                    while(tmp > 0 && values[i][tmp-1]==0) {
                        values[i][tmp-1]=values[i][tmp];
                        values[i][tmp] = 0;
                        tmp--;
                    }
                }

            }
        }

        //compare
        for(int row = 0; row<values.length;row++) {
            for(int col = 0; col<values[0].length-1;col++) {
                if(values[row][col] == values[row][col+1]) {
                    values[row][col]*=2;
                    points+=values[row][col];
                    //moving others closer
                    for(int jcol = col+1; jcol<values[0].length-1;) {
                        values[row][jcol] = values[row][++jcol];
                        values[row][jcol] = 0;
                    }
                    break;
                }
            }
        }

        switch (direction) {
            case "right":
                mirror();
                break;
            case "down":
                mirror();
                transpose();
                break;
            case "up":
                transpose();
                break;
        }

        if(checkForFree())
            setNewValue();

    }

    private boolean checkForFree() {
        boolean free = false;
        for(int[] r : values) {
            for(int c : r) {
                if(c == 0)
                    free = true;
            }
        }
        return free;
    }

    public void mirror() {
        for(int i = 0; i<values.length; i++) {
            for(int j = 0; j<values[i].length/2; j++) {
                int tmp = values[i][j];
                values[i][j] = values[i][values[i].length-1-j];
                values[i][values[i].length-1-j] = tmp;
            }
        }
    }

    public void transpose() {
        int[][] res = new int[4][4];
        for (int i = 0; i < values[0].length; i++) {
            int[] tmp = new int[4];
            for (int j = 0; j < values.length; j++) {
                tmp[j] = values[j][i];
            }
            res[i] = tmp;
        }

        values = res;
    }

    public int[][] getValues() {
        return values;
    }

    public int getPoints() {
        return points;
    }
}
