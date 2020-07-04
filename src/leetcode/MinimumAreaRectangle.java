package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class MinimumAreaRectangle
{
	public int minAreaRect(int[][] points) {
        Set<String> s = new HashSet<>();
        Map<Integer,Set<Integer>> xm = new HashMap<>();
        Map<Integer,Set<Integer>> ym = new HashMap<>();
        Integer A = Integer.MAX_VALUE;
        
        for(int i=0;i<points.length;i++){
            int x = points[i][0];
            int y = points[i][1];
            Set ys = xm.get(x);
            Set xs = ym.get(y);
            if(ys==null){
            	ys = new TreeSet<Integer>();
                xm.put(x,ys);
            }
            if(xs==null){
            	xs = new TreeSet<Integer>();
                ym.put(y,xs);
            }
            
            xs.add(x);
            ys.add(y);
            s.add(x+"|"+y);
            
            Iterator<Integer> xit = xs.iterator();
            while(xit.hasNext()) {
            	Integer x1 = xit.next();
            	Iterator<Integer> yit = ys.iterator();
            	while(yit.hasNext()) {
            		Integer y1 = yit.next();
                    if(x1!=x && y1!=y && s.contains(x1+"|"+y1)){
                    	System.out.println(x+","+y+"|"+x1+","+y+"|"+x+","+y1+"|"+x1+","+y1);
                        int area = Math.abs((x-x1)*(y-y1));
                        if(area!=0 && area<A){
                            A=area;
                        }
                    }
                }
            }
        }
        
        return A==Integer.MAX_VALUE?0:A;
    }
	
	public static void main(String[] args)
	{
		int points[][] = new int[][]{{1,1},{1,3},{3,1},{3,3},{4,1},{4,3}};
		System.out.println(new MinimumAreaRectangle().minAreaRect(points));
	}
}
