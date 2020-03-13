package euler96;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SudokuLauncher {
    
    public void performSolve() {
        
    }
    
    public static void main(String[] args) {
        
    }
    
    private Sudoku[] loadData() throws FileNotFoundException, IOException {
        Sudoku[] sudokus = new Sudoku[50];
        
        String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "euler96" + File.separator + "sudoku.txt";
        FileReader fr = new FileReader(new File(path));
        BufferedReader br = new BufferedReader(fr);
        
        String line = br.readLine();
        int cc = 0, inner = 0;
        while((line = br.readLine()) != null) {
            if(line.startsWith("G")) {
                cc++;
                inner = 0;
            } else {
                if(sudokus[cc] == null)
                    sudokus[cc] = new Sudoku();
                int y = 0;
                for(char c : line.toCharArray()) {
                    sudokus[cc].setNum(inner, y++, Integer.parseInt(""+c));
                }
                inner++;
            }
        }
        
        return sudokus;
    }
    
}


