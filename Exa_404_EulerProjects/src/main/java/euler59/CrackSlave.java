package euler59;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class CrackSlave implements Callable<Touple>{

    private static String[] common = {"the", "of", "and", "a", "to", "in", "is", "you", "that", "it"};
    private String pw;
    private String[] input;
    
    public CrackSlave(String pw, String[] input) {
        this.pw = pw;
        this.input = input;
    }
    
    @Override
    public Touple call() throws Exception {
        char[] output = new char[input.length];
        
        int cc = 0;
        for(String b : input) {
            output[cc] = (char)(Integer.parseInt(b)^pw.charAt((cc%3)));
            cc++;
        }

        String outStr = Arrays.toString(output).replace(", ", "");
        outStr = outStr.substring(1, outStr.length()-1);
        
        int same = 0;
        for(String com : common) {
            if(outStr.contains(com))
                same++;
        }
        
        int sum = 0;
        for(char c : outStr.toCharArray())
            sum+=c;
                
        return new Touple(same, pw, sum);
    }
    
}
