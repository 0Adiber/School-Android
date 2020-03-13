package euler59;

import java.io.ObjectStreamConstants;
import java.util.Arrays;
import java.util.concurrent.Callable;

public class XorDecryptWorker implements Callable<String>{

    private static String[] common = {" the ", " of ", " and ", " a ", " to ", " in ", " is ", " you ", " that ", " it "};
    private char startChar;
    
    public XorDecryptWorker(char startChar) {
        this.startChar = startChar;
    }
    
    @Override
    public String call() throws Exception {
        
        for(char c2 = 'a'; c2<='z'; c2++) {
            for(char c3 = 'a'; c3<='z'; c3++) {
                char[] output = new char[XorDecryptLauncher.input.length];
                String pw = ""+startChar+c2+c3;
                
                int cc = 0;
                for(int b : XorDecryptLauncher.input) {
                    output[cc] = (char) ( b^pw.charAt((cc%3)) );
                    cc++;
                }

                String outStr = Arrays.toString(output).replace(", ", "");
                outStr = outStr.substring(1, outStr.length()-1);

                int same = 0;
                for(String com : common) {
                    if(outStr.contains(com))
                        same++;
                }
                
                if(same >=6) {
                    System.out.println("Key found: " + pw);
                    String res =  Arrays.toString(output).replace(", ", "");
                    return res.substring(1, res.length()-1);
                }
                
            }
        }
        
        throw new Exception("not found");
    }
    
}
