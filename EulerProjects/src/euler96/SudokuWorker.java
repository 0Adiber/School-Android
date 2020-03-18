package euler96;

import java.util.concurrent.Callable;

public class SudokuWorker implements Callable<Integer>{
    
    String grid = "";

    public SudokuWorker(String grid) {
        this.grid = grid;
    }
    

    @Override
    public Integer call() throws Exception {
        //returns top left three numbers
        
        throw new Exception();
    }
    
}
