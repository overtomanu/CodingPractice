package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LargestRectangleHistogram
{
	public int largestRectangleArea(int[] heights) {
        int size = heights.length;
        if(size==0){
            return 0;
        }
        List<Integer> stack = new ArrayList<>();
        int maxA = heights[0];
        stack.add(0);
        int runPos = 0;
        for(int i=1;i<size;i++){
            if(heights[i]>=heights[stack.get(runPos)]){
                stack.add(i);
                runPos++;
            }else{
                
                while(runPos>-1 && heights[stack.get(runPos)]>heights[i]){
                    int posSTop = stack.remove(runPos);
                    int maxSTop = heights[posSTop]*(i-1-(runPos==0?-1:stack.get(runPos-1)));
                    if(maxSTop>maxA){
                    	System.out.println("poSTOP,i,area:"+posSTop+","+i+","+maxSTop);
                        maxA=maxSTop;
                    }
                    runPos--;
                }
                stack.add(i);
                runPos++;
            }
        }
        while(runPos>0){
            int posSTop = stack.remove(runPos);
            int maxSTop = heights[posSTop]*(size-1-stack.get(runPos-1));
            if(maxSTop>maxA){
            	System.out.println("poSTOP,SIZE(END),area:"+posSTop+","+size+","+maxSTop);
                maxA=maxSTop;
            }
            runPos--;
        }
        int posSTop = stack.remove(runPos);
        int maxSTop = heights[posSTop]*size;
        if(maxSTop>maxA){
        	System.out.println("poSTOP,Minimum,area:"+posSTop+","+","+maxSTop);
        	maxA=maxSTop;
        }
        return maxA;
    }
	
	public static void main(String[] args)
	{
		//int[] inp = new int[] {2,1,5,6,2,3};
		//int[] inp = {2,1,2};
		//int[] inp = new int[] {1};
		int[] inp = {5,4,1,2};
		System.out.println(new LargestRectangleHistogram().largestRectangleArea(inp));
	}
}
