package hasher;

import beans.Person;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BruteForceManager {
    
    public static char[] signs = new char[26+10];
    
    private void crackPasswords(int threads) throws IOException, InterruptedException {
        
        //add all possible chars to "signs" array for bruteforcing 
        for(char c = 'a'; c<='z'; c++)
            signs[c-'a'] = c;
        for(char c = '0'; c<='9'; c++)
            signs[c-'0'+26] = c;
        
        //create pool and service
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        CompletionService<String> service = new ExecutorCompletionService<>(pool);
        
        List<Person> people = loadData(); //load data
                
        //submit a worker for each person
        for(Person p : people) {
            service.submit(new BruteForceWorker(p));
        }
        
        pool.shutdown();
        
        while(!pool.isTerminated()) {
            Future<String> f = service.take();
            try {
                System.out.println(f.get()); //print password result to console (firstname lastname's password: <pw> (Xs Yms)
            } catch (ExecutionException ex) {
                Logger.getLogger(BruteForceManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private List<Person> loadData() throws FileNotFoundException, IOException {
        String PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator + "res" + File.separator + "passwd_file.csv";
                
        try {
            return new BufferedReader(new FileReader(PATH))
                    .lines()
                    .skip(1)
                    .map(Person::new)
                    .collect(Collectors.toList());
        }catch(FileNotFoundException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    public static void main(String[] args) {
        try {
            new BruteForceManager().crackPasswords(12);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(BruteForceManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
