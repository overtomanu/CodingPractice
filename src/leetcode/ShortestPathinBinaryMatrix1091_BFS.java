package leetcode;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class ShortestPathinBinaryMatrix1091_BFS
{
	int[][] coordinates = new int[][]{{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        int R=grid.length;
        int C=grid[0].length;
        int result = -1;
        if(grid[0][0]==0){
            int l=1;
            Set<String> visited = new HashSet<>();
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[]{0,0});
            q.offer(new int[]{-1,-1});
            visited.add(0+","+0);
            boolean isLastOneCell=true;
            while(!q.isEmpty()){
                int[] p1 = q.poll();
                if(p1[0]==-1){
                    if(!isLastOneCell)
                        return -1;
                    else
                        q.offer(p1);
                    l+=1;
                    isLastOneCell=false;
                    continue;
                }
                int x=p1[0],y=p1[1];
                for(int[] pair:coordinates){
                    int dx=pair[0]+x;
                    int dy=pair[1]+y;
                    if(dx>-1 && dx<R && dy>-1 && dy<C && grid[dx][dy]==0 && !visited.contains(dx+","+dy)){
                        if(dx==R-1 && dy==C-1){
                            return l+1;
                        }
                        q.offer(new int[]{dx,dy});
                        visited.add(dx+","+dy);
                        isLastOneCell=true;
                    }
                }
            }
        }
        return result;
    }
    
    public static void main(String[] args)
	{
		System.out.println(new ShortestPathinBinaryMatrix1091_BFS()
				.shortestPathBinaryMatrix(new int[][]{{0,0,0},{1,1,0},{1,1,0}}));
		System.out.println(new ShortestPathinBinaryMatrix1091_BFS()
				.shortestPathBinaryMatrix(new int[][]{{0}}));
    	
	}
}
