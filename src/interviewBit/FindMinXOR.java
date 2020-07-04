package interviewBit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindMinXOR {
	class TrieNode{
        TrieNode l;
        TrieNode r;
    }
    
    public int findMinXor(ArrayList<Integer> A) {
        TrieNode root = new TrieNode();
        int res = Integer.MAX_VALUE;
        //System.out.println("First");
        if(A!=null && A.size()>1){
        	System.out.println("inserting "+A.get(0));
            insertToTrie(root,A.get(0));
            for(int i=1;i<A.size();i++){
                int iminxor = findMinT(root,A.get(i));
                System.out.println("inserting "+A.get(i));
                insertToTrie(root,A.get(i));
                System.out.println(A.get(i)+" iXOR: "+iminxor);
                if(iminxor<res){
                    res=iminxor;
                }
            }
        }
        return res;
    }
    
    public void insertToTrie(TrieNode root,Integer n){
        
        int ii=n;
        for(int i=0;i<Integer.SIZE;i++){
            // r->1->true
            boolean b = (ii&(1<<31))==(1<<31);
            ii=ii<<1;
            if(b){
                if(root.r==null){
                    root.r=new TrieNode();
                }else{
                    
                }
                root=root.r;
            }else{
                if(root.l==null){
                    root.l=new TrieNode();
                }else{
                    
                }
                root=root.l;
            }
        }
    }
    
    public int findMinT(TrieNode root,Integer n){
        int res = 0;
        int ii=n;
        for(int i=0;i<Integer.SIZE;i++){
            res=res<<1;
            // r->1->true
            boolean b = (ii&(1<<31))==(1<<31);
            ii=ii<<1;
            if(b){
                if(root.r==null){
                    res|=1;
                    root=root.l;
                }else{
                    root=root.r;
                }
                
            }else{
                if(root.l==null){
                    res|=1;
                    root=root.r;
                }else{
                    root=root.l;
                }
            }
            
        }
        return res;
    }
    
    public static void main(String[] args)
	{
    	ArrayList<Integer> inp = new ArrayList<>(Arrays.asList(12, 4, 6, 2));
		System.out.println("Solution:"+new FindMinXOR().findMinXor(inp));
	}
}