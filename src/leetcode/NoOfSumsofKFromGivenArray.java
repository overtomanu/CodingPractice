// Mis-interpreted below question
// https://leetcode.com/problems/subarray-sums-divisible-by-k/description/

package leetcode;

import java.util.HashMap;
import java.util.Map;

class NoOfSumsofKFromGivenArray {
    public int subarraysDivByK(int[] a, int k) {
        Map<Integer,Integer> m = new HashMap<>();
        for(int i=0;i<k;i++){
            m.put(i,0);
        }
        for(int i=0;i<a.length;i++)
		{
            int j = a[i]%k;
            if(j<0){
                j+=k;
            }
            //System.out.println("j,a[i]"+j+","+a[i]);
            m.put(j,m.get(j)+1);
		}
        System.out.println("map:"+m);
        int mul = m.get(0);
        int l=(k-1)/2;
        for(int i=1;i<=l;i++)
		{
            mul+=m.get(i)*m.get(k-i);
        }
        if(k%2==0){
            int n = m.get(k/2);
            //nC2
            mul+= n*(n-1)/2;
        }
        //nC1+nC2+...+nCn
        return (int)(Math.pow(2,mul)-1);
    }
}