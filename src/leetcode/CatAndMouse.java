package leetcode;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class CatAndMouse
{
	class PathComparator implements Comparator<int[]>
	{

		@Override
		public int compare(int[] arg0, int[] arg1)
		{
			return arg0[0]-arg1[0];
		}
		
	}
	
	public Writer wr = null;
	public static void main(String[] args) throws IOException
	{
		int[][] g;
		//g = new int[][]{{2,5},{3},{0,4,5},{1,4,5},{2,3},{0,2,3}};
		g = new int[][]{{2,9,14},{2,5,7},{0,1,3,8,9,11,14},{2,12},{5,11,18},{1,4,18},{9,17,19},{1,11,12,13,14,17,19},{2,16,17},{0,2,6,12,14,18},{14},{2,4,7},{3,7,9,13},{7,12,14},{0,2,7,9,10,13,17},{18},{8,19},{6,7,8,14,19},{4,5,9,15},{6,7,16,17}};
		CatAndMouse sol = new CatAndMouse();
		System.out.println("Solution:"+sol.catMouseGame(g));
	}
	
	public int catMouseGame(int[][] graph) throws IOException {
        int v = graph.length;
        int rp = 1;
        int cp = 2;
        int[][] pl = new int[v][v];
        int[][][] memo = new int[v][v][2];
        for(int i=0;i<v;i++) {
        	for(int j=0;j<v;j++) {
        		pl[i][j]=Integer.MAX_VALUE;
        	}
        }
        for(int i=0;i<v;i++) {
        	pl[i][i]=0;
        }
        Set<String> moves = new LinkedHashSet<>();
        List<Set<Integer>> gs = new ArrayList<>();
        for(int i=0;i<v;i++){
            Set<Integer> is = new HashSet<>();
            for(int j=0;j<graph[i].length;j++){
                is.add(graph[i][j]);
                pl[i][graph[i][j]]=1;
                pl[graph[i][j]][i]=1;
            }
            gs.add(is);
        }
        
        //Floyds algorithm for all pair shortest path
        for(int k=0;k<v;k++) {
        	for(int i=0;i<v;i++) {
        		for(int j=i+1;j<v;j++) {
        			if(i!=k &&j!=k) {
        				int dViaK = pl[i][k]+pl[k][j];
        	        			if(dViaK>0 && pl[i][j]>dViaK) {
        	        				pl[i][j]=dViaK;
        	        				pl[j][i]=dViaK;
        	        			}
        			}
        		}
        	}
        }
        
        for(int i=0;i<v;i++) {
    		for(int j=0;j<v;j++) {
    			memo[i][j][0]=-1;
    			memo[i][j][1]=-1;
    		}
    	}
        
        PathComparator pc = new PathComparator();
        System.out.println("Graph List:"+gs);
        wr = new FileWriter("CatMouseOutput");
        int result = catMouseGame(gs,pl,pc,memo,rp,cp,moves,true);
        //System.out.println("Final Moves:"+moves);
        return result;
    
    }
    public int catMouseGame(List<Set<Integer>> graph,int[][] pl,PathComparator pc,int[][][] memo,int rp,int cp,Set<String> moves,boolean ratMove) throws IOException{
    	if(memo[rp][cp][ratMove?0:1]!=-1)
    	{
    		return memo[rp][cp][ratMove?0:1];
    	}
        if(ratMove) {
            if(graph.get(rp).contains(0)){
            	memo[rp][cp][ratMove?0:1]=1;
                return 1;
            }
            boolean canBeDrawn = false;
            PriorityQueue<int[]> pq = new PriorityQueue<>(graph.get(rp).size(), pc);
            for(int i:graph.get(rp)) {
            	if(!graph.get(i).contains(cp)&& i!=cp) {
            		pq.add(new int[] {pl[i][0],i});
            	}
            }
            while(!pq.isEmpty()) {
        		int[] element = pq.remove();
        		int i = element[1];
        		String moveState = rp+"|"+cp+"|"+ratMove;
        		if(moves.contains(moveState)) {
        			canBeDrawn=true;
        			continue;
        		}
        		moves.add(moveState);
        		
        		wr.write(moves.toString()+"\n");
        		wr.write("rat "+rp+" -> "+i+" MoveState: "+moveState+"\n");
        		wr.flush();
        		int result = catMouseGame(graph,pl,pc,memo,i,cp,moves,false);
        		moves.remove(moveState);
        		
        		if(result==1) {
        			memo[rp][cp][ratMove?0:1]=1;
        			return 1;
        		}
        		if(result==0) {
        			canBeDrawn=true;
        		}
        	}
            if(canBeDrawn) {
            	memo[rp][cp][ratMove?0:1]=0;
            	return 0;
            }else {
            	memo[rp][cp][ratMove?0:1]=2;
            	return 2;
            }
        }else {
        	//cat Move
        	if(graph.get(cp).contains(rp)){
        		memo[rp][cp][ratMove?0:1]=2;
                return 2;
            }
        	boolean canBeDrawn = false;
        	PriorityQueue<int[]> pq = new PriorityQueue<>(graph.get(cp).size(), pc);
            for(int i:graph.get(cp)) {
            	if(i!=0) {
            		pq.add(new int[] {pl[i][rp],i});
            	}	
            }
            while(!pq.isEmpty()) {
        		int[] element = pq.remove();
        		int i = element[1];
        		String moveState = rp+"|"+cp+"|"+ratMove;
        		if(moves.contains(moveState)) {
        			canBeDrawn=true;
        			continue;
        		}
        		moves.add(moveState);
        		wr.write(moves.toString()+"\n");
        		wr.write("cat "+cp+" -> "+i+" MoveState: "+moveState+"\n");
        		wr.flush();
        		int result = catMouseGame(graph,pl,pc,memo,rp,i,moves,true);
        		moves.remove(moveState);
        		if(result==2) {
        			memo[rp][cp][ratMove?0:1]=2;
        			return 2;
        		}
        		if(result==0) {
        			canBeDrawn=true;
        		}
            }
            if(canBeDrawn) {
            	memo[rp][cp][ratMove?0:1]=0;
            	return 0;
            }else {
            	memo[rp][cp][ratMove?0:1]=1;
            	return 1;
            }
        }
    }
}

