package leetcode;

public class OnesAndZeroes
{
	//Get max no of strings that can be formed from 
	//fixed 'm' no of zeroes and 'n' no of ones
	public int findMaxForm(String[] strs, int m, int n) {
        int[][] maxArr = new int[m+1][n+1];
        int ns = strs.length;
        for(int k=0;k<ns;k++){
            int n1 = getNoOfOnes(strs[k]);
            int m0 = strs[k].length()-n1;
            for(int i=m;i>=m0;i--){
                for(int j=n;j>=n1;j--){
                    maxArr[i][j]=max(maxArr[i][j],1+maxArr[i-m0][j-n1]);
                }
            }
            String printArr="";
            for(int i=0;i<m+1;i++){
                for(int j=0;j<n+1;j++){
                	printArr+=maxArr[i][j]+",";
                }
                printArr+="\n";
            }
            System.out.println("After "+strs[k]+":\n"+printArr+"\n");
        }
        return maxArr[m][n];
    }
                                     
    int getNoOfOnes(String s){
        char[] c = s.toCharArray();
        int res=0;
        for(int i=0;i<c.length;i++) {
        	if(c[i]=='1') {
        		res++;
        	}
        }
        return res;
    }
    
    int max(int a,int b){
        if(a>b)
            return a;
        else
            return b;
    }
    
    public static void main(String[] args)
	{
    	String strs[] = new String[] {"10","0001","111001","1","0"};
    	int m=5,n=3;
		System.out.println("Solution:"+new OnesAndZeroes().findMaxForm(strs, m, n));
	}
}
