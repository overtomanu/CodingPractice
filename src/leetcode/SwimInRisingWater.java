package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SwimInRisingWater
{
	//Failed attempt
    public int swimInWaterF(int[][] grid) {
        int n = grid.length;
        int[][] m = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                m[i][j]=-1;
            }
        }
        List<String> path = new ArrayList<>();
        
        return getMinTime(n,0,0,grid,m,path);
    }
    
    public int getMinTime(int n,int x,int y,int[][] grid,int[][] memo,List<String> path)
    {
        int mh = grid[x][y];
        if(x==n-1 && y==n-1){
            return mh;
        }
        if(memo[x][y]!=-1){
            return memo[x][y];
        }
        if(path.contains(x+"|"+y)){
            //dfs cycle
            return Integer.MAX_VALUE;
        }
        path.add(x+"|"+y);
        
        int mChild=Integer.MAX_VALUE;
        
        if(x-1>-1){
            mChild = min(mChild,getMinTime(n,x-1,y,grid,memo,path));
        }
        if(x+1<n){
            mChild = min(mChild,getMinTime(n,x+1,y,grid,memo,path));
        }
        if(y-1>-1){
            mChild = min(mChild,getMinTime(n,x,y-1,grid,memo,path));
        }
        if(y+1<n){
            mChild = min(mChild,getMinTime(n,x,y+1,grid,memo,path));
        }
        
        path.remove(path.size()-1);
        memo[x][y]=max(mh,mChild);
        System.out.println(x+","+y+"->"+memo[x][y]+" path:"+path);
        return (memo[x][y]=max(mh,mChild));
        
    }
    
    int max(int x,int y){
        if(x<y){
            return y;
        }else{
            return x;
        }
    }
    int min(int x,int y){
        if(x<y){
            return x;
        }else{
            return y;
        }
    }
    
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int mint = grid[n-1][n-1];
        int maxt = mint;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]>maxt){
                    maxt = grid[i][j];
                }
            }
        }
        
        int memo[][] = new int[n][n];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                memo[i][j]= -1;
            }
        }
        
        int mid = maxt;
        int low=mint,high=maxt;
        int solution = maxt;
        while(low<=high){
            mid=low+(high-low)/2;
            System.out.println("Elevation:"+mid);
            System.out.println("low,high:"+low+","+high);
            if(checkDFS(memo,grid,n,mid)){
            	if(solution>mid) {
            		solution=mid;
            	}
            	System.out.println("DFS successful for :"+mid);
            	high=mid-1;
            }else{
            	System.out.println("DFS failed for :"+mid);
            	low=mid+1;
                
            }
        }
        
        return solution;
    }
    
    
    private boolean checkDFS(int[][] memo,int[][] grid,int n,int e){
        Set<String> path = new HashSet<String>();
        
        return doDFS(memo,grid,n,e,path,0,0);
    }
    
    private boolean doDFS(int[][] memo,int[][] grid,int n,int e,Set<String> path,int x, int y){
    	//System.out.println("Checking: "+x+"|"+y);
        if(e<grid[x][y]||path.contains(x+"|"+y)){
            return false;
        }
        
        if(memo[x][y]!=-1 && e>=memo[x][y]){
            return true;
        }
        
        if(x==n-1 && y==n-1) {
        	return true;
        }
        
        path.add(x+"|"+y);
        
        if(x+1<n && doDFS(memo,grid,n,e,path,x+1,y)){
            memo[x][y]=e;
            path.remove(x+"|"+y);
            return true;
        }
        
        if(y+1<n && doDFS(memo,grid,n,e,path,x,y+1)){
            memo[x][y]=e;
            path.remove(x+"|"+y);
            return true;
        }
        
        if(x-1>-1 && doDFS(memo,grid,n,e,path,x-1,y)){
            memo[x][y]=e;
            path.remove(x+"|"+y);
            return true;
        }
        
        if(y-1>-1 && doDFS(memo,grid,n,e,path,x,y-1)){
            memo[x][y]=e;
            path.remove(x+"|"+y);
            return true;
        }
        path.remove(x+"|"+y);
        return false;
    }
    
    public static void main(String[] args)
	{
		//int[][] g = new int[][]{{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}};
    	//int[][] g = new int[][]{{11,15,3,2},{6,4,0,13},{5,8,9,10},{1,14,12,7}};
    	int[][] g = new int[][]{
    		{31,28,33,0,8,57,86,99,23,98},
    		{25,90,20,73,34,65,29,9,42,46},
    		{17,84,10,4,40,5,41,21,71,79},
    		{13,70,69,81,63,93,77,1,94,53},
    		{38,87,61,50,92,2,15,95,82,68},
    		{44,72,88,47,27,91,37,48,83,16},
    		{3,30,96,66,7,58,76,54,19,64},
    		{85,45,60,11,51,26,6,22,74,32},
    		{43,12,62,59,89,52,36,97,49,78},
    		{75,24,14,67,56,35,55,39,80,18}};
		System.out.println("Result:"+new SwimInRisingWater().swimInWater(g));
	}
}
