package leetcode;

import java.util.ArrayList;
import java.util.List;

public class ReachingPoints
{
	public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        if(sx==tx && sy==ty){
            return true;
        }
        else if(sx>tx || sy>ty){
            return false;
        }else{
            return reachingPoints(sx+sy,sy,tx,ty)||reachingPoints(sx,sx+sy,tx,ty);
        }
    }
	
	 public boolean reachingPoints2(int sx, int sy, int tx, int ty) {
	        List<Integer> stack = new ArrayList<>();
	        stack.add(sx);
	        stack.add(sy);
	        while(stack.size()>0){
	            Integer y = stack.remove(stack.size()-1);
	            Integer x = stack.remove(stack.size()-1);
	            System.out.println("Checking "+x+","+y);
	            if(x==tx && y==ty){
	                return true;
	            }else if(x>tx || y>ty){
	                
	            }else{
	                stack.add(x+y);
	                stack.add(y);
	                stack.add(x);
	                stack.add(x+y);
	                System.out.println(stack);
	            }
	        }
	        return false;
	    }
	 
	 public boolean reachingPoints3(int sx, int sy, int tx, int ty) {
		 System.out.println("Checking "+tx+","+ty);
	        if(sx==tx && sy==ty){
	            return true;
	        }else if(tx<sx || ty<sy){
	            return false;
	        }else{
	            if(tx>=ty){
	                return reachingPoints3(sx,sy,tx-ty,ty);
	            }else{
	                return reachingPoints3(sx,sy,tx,ty-tx);
	            }
	        }
	    }
	 
	 public boolean reachingPoints4(int sx, int sy, int tx, int ty) {
	        while(tx>=sx && ty>=sy){
	            if(sx==tx && sy==ty){
		            return true;
		        }else {
	                if(tx>=ty){
		                tx=tx-ty;
		            }else{
		                ty=ty-tx;
		            }
	            }
	        }
	        return false;
	    }
	 
	 public boolean reachingPoints5(int sx, int sy, int tx, int ty) {
		 int px=-1,py=-1;
	        while(tx>=sx && ty>=sy){
	        	System.out.println("Checking:"+tx+","+ty);
	        	System.out.println("Previous:"+px+","+py);
	            if(sx==tx && sy==ty){
		            return true;
		        }else if(tx==px && ty==py) {
		        	return false;
	        	}else {
	        		px=tx;
	        		py=ty;
	                if(tx>=ty){
		                /*int x=tx%ty;
		                if(x!=0) {
		                	tx=x;
		                }else*/ 
		                {
		                	tx-=(ty*((tx/ty)-(sx/ty)));
		                }
		            }else{
		                /*int y=ty%tx;
		                if(y!=0) {
		                	ty=y;
		                }else */
		                {
		                	ty-=(tx*((ty/tx)-(sy/tx)));
		                }
		            }
	            }
	        }
	        return false;
	    }
	 
	 public static void main(String[] args)
	{
		System.out.println(new ReachingPoints().reachingPoints5(1, 1, 1000000000, 1));
		//System.out.println(new ReachingPoints().reachingPoints5(35, 13, 455955547, 420098884));
		//System.out.println(new ReachingPoints().reachingPoints5(1, 4, 999999997, 4));
		//System.out.println(new ReachingPoints().reachingPoints5(9, 10, 9,19));
		//System.out.println(new ReachingPoints().reachingPoints5(9, 5, 12, 8));
		
		 
	}
}
