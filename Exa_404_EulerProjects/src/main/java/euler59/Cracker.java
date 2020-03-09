package euler59;

import euler39.Triple;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cracker {
    
    //this cracks your PASSWORDS
    
    public void performCracking(String[] input) {       
        ExecutorService pool = Executors.newFixedThreadPool(4);
        CompletionService<Touple> service = new ExecutorCompletionService<>(pool);
        
        for(int i = 97; i<=122; i++) {
            for(int j = 97; j<=122; j++) {
                for(int k = 97; k<=122; k++) {
                    String pw = String.format("%c%c%c", i,j,k);
                    service.submit(new CrackSlave(pw, input));
                }
            }
        }
        
        pool.shutdown();
        
        Touple bestTouple = null;
        while(!pool.isTerminated()) {
            try {
                Future<Touple> future = service.take();
                Touple result = future.get();
                
                if(bestTouple == null || result.getSame() > bestTouple.getSame())
                    bestTouple = result;
                
                System.out.println("-->" + result.getSame());                
            } catch (InterruptedException | ExecutionException ex) {
                System.out.println(ex.toString());
            }
        }
        
        System.out.println(bestTouple + " ==> " + bestTouple.getSum());
        
    }
    
    public static void main(String[] args) {        
        try {
            new Cracker().performCracking(readFile());
        } catch (IOException ex) {
            Logger.getLogger(Cracker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private static String[] readFile() throws FileNotFoundException, IOException {
        String[] chars;

        FileReader fr = new FileReader(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "euler59" + File.separator + "input.csv");
        BufferedReader br = new BufferedReader(fr);

        chars = br.readLine().split(",");        

        return chars;        
    }
    
}
