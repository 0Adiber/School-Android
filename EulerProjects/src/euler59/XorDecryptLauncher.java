package euler59;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XorDecryptLauncher {
    
    //this cracks your PASSWORDS
    
    public static int[] input;
    
    public void runCalculation() throws InterruptedException, ExecutionException {       
        ExecutorService pool = Executors.newFixedThreadPool(4);
        List<Callable<String>> workers = new ArrayList<>();
        
        for(char c = 'a'; c<='z'; c++) {
            workers.add(new XorDecryptWorker(c));
        }
        
        String result = pool.invokeAny(workers);
        pool.shutdown();
        
        System.out.println(result);
        System.out.println(CharBuffer.wrap(result.toCharArray()).chars().sum());
    }
    
    public static void main(String[] args) {        
        try {
            input = loadData();
            new XorDecryptLauncher().runCalculation();
        } catch (IOException | InterruptedException | ExecutionException ex) {
            Logger.getLogger(XorDecryptLauncher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static int[] loadData() throws FileNotFoundException, IOException {
        int[] chars;

        FileReader fr = new FileReader(System.getProperty("user.dir") + File.separator + "src" + File.separator + "euler59" + File.separator + "input.csv");
        BufferedReader br = new BufferedReader(fr);

        String[] shit = br.readLine().split(","); 
        
        int cc = 0;
        chars = new int[shit.length];
        for(String c : shit) {
            chars[cc++] = Integer.parseInt(c);
        }

        return chars;        
    }
    
}
