package euler39;

import java.util.Set;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TriangleLauncher {

    public void performTriangleCalculation() {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        CompletionService<Set<Triple>> service = new ExecutorCompletionService<>(pool);
        
        for(int p = 10; p<=1000; p++) {
            service.submit(new TriangleWorker((p)));
        }
        pool.shutdown();
        
        Set<Triple> maxTriple = null;
        
        while(!pool.isTerminated()) {
            try {
                Future<Set<Triple>> future = service.take();
                Set<Triple> result = future.get();
                
                if(maxTriple == null || result.size() > maxTriple.size())
                    maxTriple = result;
                
                System.out.println("-->" + result.size());                
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            } catch (ExecutionException ex) {
                System.out.println(ex.toString());
            }
        }
        
        for(Triple t : maxTriple) {
            System.out.println(t);
        }
        System.out.println(maxTriple.size());
        
    }
    
    public static void main(String[] args) {
        new TriangleLauncher().performTriangleCalculation();
    }
    
}
