package euler39;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

public class TriangleWorker implements Callable<Set<Triple>>{
    private int p;
    
    public TriangleWorker(int p) {
        this.p = p;
    }

    @Override
    public Set<Triple> call() throws Exception {
        Set<Triple> tripleSet = new HashSet<>();
        
        for (int i = 1; i < p/2; i++) {
            for (int j = 0; j < p/2; j++) {
                for (int k = 0; k < p/2; k++) {
                    if((i+j+k == p) && (i*i+j*j == k*k)) {
                        tripleSet.add(new Triple(p, i, j, k));
                    }
                }
            }
        }
        
        return tripleSet;
    }
    
}
