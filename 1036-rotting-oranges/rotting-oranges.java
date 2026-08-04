import java.util.*;

class Solution {

    public int orangesRotting(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> q = new ArrayDeque<>();

        int fresh = 0;
        for(int i = 0 ; i < rows ; i ++ ){
            for ( int j = 0 ; j < cols ; j++ ){
                if (grid[i][j] == 1){
                    fresh ++ ; 
                }
                else if(grid[i][j] == 2){
                    q.offer(new int[]{i,j}) ; 
                }
            }
        }

        int[] dx = {-1 , 1,0, 0} ; 
        int[] dy = {0 ,0 ,-1 ,1} ;

        int min = 0 ; 

        while (!q.isEmpty() && fresh > 0 ){
            int size = q.size() ; 

            for (int i = 0 ; i < size ; i++){
                int[] current = q.poll() ; 
                int x = current[0] ; 
                int y = current[1] ; 

                for (int j = 0 ; j < 4 ; j++){
                    int newx = dx[j] + x ; 
                    int newy = dy[j] + y ;
                    
                    if (newx >= 0 && newx < rows && 
                    newy < cols && newy >= 0 && 
                    grid[newx][newy] == 1){
                        grid[newx][newy] = 2 ; 
                        fresh -- ; 
                        q.offer(new int[]{newx, newy} ) ; 
                    } 
                }
            }
            min++ ; 
        } 

    if (fresh > 0 ){return -1 ; } 

    return min ; 
    }
}