package leetcode;

import java.util.HashSet;
import java.util.Set;

public class ShortestPathinBinaryMatrix1091_DFSTimeLimitExceeded
{
	int[][] coordinates = new int[][]{{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        int R=grid.length;
        int C=grid[0].length;
        Set<String> path = new HashSet<>();
        
        int result = doDFS(grid,R,C,0,0,path,new int[]{Integer.MAX_VALUE});
        return result;
    }
    
    int doDFS(int[][] grid, int R, int C,int x,int y,Set<String> path,int[] curMinPath){
        if(grid[x][y]==1){
            return -1;
        }
        
        if((path.size()+1)>curMinPath[0]){
            return curMinPath[0];
        }
        if(x==R-1 && y==C-1){
            return path.size()+1;
        }
        path.add(x+","+y);
        int result=curMinPath[0];
        for(int[] pair:coordinates){
            int dx=pair[0]+x;
            int dy=pair[1]+y;
            if(dx>-1 && dx<R && dy>-1 && dy<C && grid[dx][dy]==0 && !path.contains(dx+","+dy)){
                int dr = doDFS(grid,R,C,dx,dy,path,curMinPath);
                if(dr!=-1 && dr<result){
                    result=dr;
                }
            }
        }
        path.remove(x+","+y);
        curMinPath[0]=result;
        return result==Integer.MAX_VALUE?-1:result;
    }
}
