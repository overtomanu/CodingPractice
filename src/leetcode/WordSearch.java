package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
//efficient recursive implementation below
//https://leetcode.com/problems/word-search/submissions/
public class WordSearch
{
	public boolean exist(char[][] board, String word) {
        Map<int[],Set<String>> paths = new HashMap<>();
        int[][] adj = {{0,1},{0,-1},{1,0},{-1,0}};
        for(int i=0;i<board.length;i++){
			for(int j=0;j<board[i].length;j++){
                if(board[i][j]==word.charAt(0)){
                    Set<String> path = new HashSet<>();
                    path.add(i+","+j);
                    paths.put(new int[]{i,j},path);
                }
			}
		}
        if(paths.size()==0){
            return false;
        }
        for(int i=1;i<word.length();i++){
            if(paths.size()>0){
                Map<int[],Set<String>> newPaths = new HashMap<>();
                boolean isNextPathFound = false;
                for(Map.Entry<int[],Set<String>> me:paths.entrySet()){
                    int[] pos = (int[])me.getKey();
                    for(int j=0;j<adj.length;j++){
                        int x=pos[0]+adj[j][0];
                        int y=pos[1]+adj[j][1];
                        if(x>=0 && x<board.length 
                           && y>=0 && y<board[x].length 
                           && !me.getValue().contains(x+","+y) && word.charAt(i)==board[x][y]){
                            Set<String> path = new HashSet<>(me.getValue());
                            path.add(x+","+y);
                            newPaths.put(new int[]{x,y},path);
                            isNextPathFound=true;
                        }
                    }
                }
                if(!isNextPathFound){
                    return false;
                }
                paths=newPaths;
            }else{
                return false;
            }
        }
        return true;
    }
}