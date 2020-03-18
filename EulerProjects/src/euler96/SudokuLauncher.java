package euler96;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SudokuLauncher {
    
    public void runSudokuCalculation() throws IOException, InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        List<Callable<Integer>> workers = new ArrayList<>();
        
        String[] sudokus = loadData();
        
        for(String s : sudokus) {
            workers.add(new SudokuWorker(s));
        }

//        workers.add(new SudokuWorker(sudokus[1]));
        
        List<Future<Integer>> res = pool.invokeAll(workers);
        pool.shutdown();
        
        int sum = 0;
        for(Future<Integer> f : res) {
            int num = f.get();
            sum+=num;
        }
        
        System.out.println(sum);
    }
    
    public static void main(String[] args) {
        try {
            new SudokuLauncher().runSudokuCalculation();
        } catch (IOException | InterruptedException | ExecutionException ex) {
            Logger.getLogger(SudokuLauncher.class.getName()).log(Level.SEVERE, null, ex);
        }

        

    }
    
    private String[] loadData() throws FileNotFoundException, IOException {
        String[] sudokus = new String[50];
        
        String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "euler96" + File.separator + "sudoku.txt";
        FileReader fr = new FileReader(new File(path));
        BufferedReader br = new BufferedReader(fr);
        
        String line;
        int cc = -1, inner = 0;
        String sudo = "";
        while((line = br.readLine()) != null){
            if(line.startsWith("G")) {
                cc++;
                sudokus[cc] = sudo;
                sudo = "";
            } else {
                sudo+=line;
                sudokus[cc] = sudo;
            }
        };
        
        return sudokus;
    }
    
}


