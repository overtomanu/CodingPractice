package leetcode;

import java.util.HashMap;
import java.util.Map;

public class ProfitableSchemes
{
	Integer MOD = 1000_000_007;
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        int jobs = group.length;
        Map<String,Integer> memo = new HashMap<>();
        return noOfSchemes(jobs-1,G,P,group,profit,memo)%MOD;
    }
    
    int noOfSchemes(int jno,int g, int p, int[] group, int[] profit,Map<String,Integer> memo){
        if(jno<0){
            return 0;
        }
        Integer nmemo = memo.get(jno+"|"+g+"|"+p);
        if(nmemo!=null){
        	System.out.println("memo hit:"+jno+"|"+g+"|"+p);
            return nmemo;
        }
        
        int n = 0;
        int ng = g-group[jno];
        int np = p-profit[jno];
        //include
        if(ng>=0){
            if(np<=0){
                n++;
            }
            if(ng>0){
                n=(n+noOfSchemes(jno-1,ng,np,group,profit,memo))%MOD;
            }
        }
        //exclude
        n=(n+noOfSchemes(jno-1,g,p,group,profit,memo))%MOD;
        memo.put(jno+"|"+g+"|"+p,n);
        return n;
    }
    
    public static void main(String[] args)
	{
		
		int[] g = new int[]{18,58,88,52,54,13,50,66,83,61,100,54,60,80,1,19,78,54,67,20,57,46,12,6,14,43,64,81,30,60,48,53,86,71,51,23,71,87,95,69,11,12,41,36,69,89,91,10,98,31,67,85,16,83,83,14,14,71,33,5,40,61,22,19,34,70,50,21,91,77,4,36,16,38,56,23,68,51,71,38,63,52,14,47,25,57,95,35,58,32,1,39,48,33,89,9,1,95,90,78};
		int[] p = new int[]{96,77,37,98,66,44,18,37,47,9,38,82,74,12,71,31,80,64,15,45,85,52,70,53,94,90,90,14,98,22,33,39,18,22,10,46,6,19,25,50,33,15,63,93,35,0,76,44,37,68,35,80,70,66,4,88,66,93,49,19,25,90,21,59,17,40,46,79,5,41,2,37,27,92,0,53,57,91,75,0,42,100,16,97,83,75,57,61,73,21,63,97,75,95,84,14,98,47,0,13};
		int G=100;
		int P=100;
		System.out.println("Solution: "+new ProfitableSchemes().profitableSchemes(G, P, g, p));
	}
}
