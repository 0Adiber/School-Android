package euler96;

import java.util.concurrent.Callable;

public class SudokuWorker implements Callable<Integer>{
    
    String grid = "";
    int arr[][] = new int[9][9];
    
    boolean finished = false;
   

    public SudokuWorker(String grid) {
        this.grid = grid;
    }
    

    @Override
    public Integer call() throws Exception {
        //returns top left three numbers
        
        int cc = 0;
        for(int i = 0; i<grid.length(); i++) {
            arr[cc][i%9] = grid.toCharArray()[i]-'0';
            if((i+1)%9 == 0)
                cc++;
        }
        
        bruting(0, 0);
        
        return Integer.parseInt(String.format("%d%d%d", arr[0][0], arr[0][1], arr[0][2]));
    }
    
    public void bruting(int y, int x) {
        if(x > 8) {
            y++;
            x=0;
        }
        
        if(y > 8) {
            finished = true;
            return;
        }
        
        if(arr[y][x] != 0) {
            bruting(y, x+1);
            
        } else {
            for(int num = 1; num<=9;num++) {
                
                if(isPossible(y, x, num)) {
                    arr[y][x] = num;
                    if(x == 8 && y == 8)
                        finished = true;
                    
                    bruting(y, x+1);
                    
                    if(finished) return;
                    arr[y][x] = 0;
                    
                } 
                
            }
        }
    }
    
    public boolean isPossible(int y, int x, int num) {
        
        //ROW
        for (int val : arr[y]) {
            if (val == num)
                return false;
        }
        
        //COLUMN
        for (int yMov = 0; yMov < arr.length; yMov++) {
            if (arr[yMov][x] == num)
                return false;
        }
        
        //BOX
        int startY = Math.floorDiv(y, 3) * 3;
        int startX = Math.floorDiv(x, 3) * 3;
        for (int y1 = 0; y1 < 3; y1++) {
            for (int x1 = 0; x1 < 3; x1++) {
                if (arr[startY + y1][startX + x1] == num)
                    return false;
            }
        }
        return true;
    }
    
    public boolean checkSudoku(){
        //check row
        for(int i = 0; i<9; i++) {
            for(int j = 0; j<9; j++) {
                for(int k = j+1; k<9; k++) {
                    if(arr[i][j] == arr[i][k] && arr[i][j] != 0)
                        return false;
                }
            }
        }
        
        //check column
        for(int i = 0; i<9; i++) {
            for(int j = 0; j<9; j++) {
                for(int k = j+1; k<9; k++) {
                    if(arr[j][i] == arr[k][i] && arr[j][i] != 0)
                        return false;
                }
            }
        }
        
//        //check boxes
        int brv = 0;
        int bcv = 0;
        
        for(int x = 1; x<=9; x++) {
            
            for(int i = brv+0; i<3; i++) {
                for(int j = bcv+0; j<3; j++) {
                
                    for(int k = brv+0; k<3; k++) {
                        for(int l = bcv+0; l<3; l++) {
                            
                            if(i == k && j == l)
                                continue;
                            
                            if(arr[i][j] == arr[k][l] && arr[i][j] != 0)
                                return false;
                            
                        }
                    }
                    
                }
            }
            bcv+=3;
            
            if(x%3==0) {
                brv+=3;
                bcv=0;
            }
                   
        }
        
        return true;
    }
    
}
