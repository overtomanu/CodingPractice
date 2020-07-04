package interviewBit;

import java.util.ArrayList;

public class ConcentricRectangular
{
	public static void main(String[] args)
	{
		ConcentricRectangular sol = new ConcentricRectangular();
		System.out.println(sol.prettyPrint(4));
	}
	
	public ArrayList<ArrayList<Integer>> prettyPrint(int A) {
        if(A<=0){
            return null;
        }
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int i=A;
        boolean decrease = true;
        while(decrease || (!decrease && i<=A)){
            ArrayList<Integer> row = new ArrayList<>();
            res.add(row);
            int j=A;
            
            boolean decreaseJ = true;
            while(decreaseJ || (!decreaseJ && j<=A)){
                Integer value = i>j ? i: j;
                row.add(value);
                
                if(j==1){
                	j++;
                    decreaseJ=false;
                    continue;
                }
                
                if(decreaseJ){
                    j--;
                }else{
                    j++;
                }
            }
            
            if(i==1){
            	i++;
                decrease=false;
                continue;
            }
            
            if(decrease){
                i--;
            }else{
                i++;
            }
        }
        return res;
    }

}
