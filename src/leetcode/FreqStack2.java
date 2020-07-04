package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class FreqStack2
{
	Map<Integer,Integer> icnt = new HashMap<>();
	Map<Integer,Set<Integer>> cntSet = new HashMap<>();
	List<Integer> stack = new ArrayList<>();
	int maxCount=0;
	
	public FreqStack2()
	{
		
	}

	public void push(int x)
	{
		stack.add(x);
		Integer count = icnt.get(x);
		if(count==null) {
			count=0;
		}
		Integer newCnt = count+1;
		icnt.put(x, newCnt);
		Set<Integer> newSet = cntSet.get(newCnt);
		if(newSet==null) {
			newSet = new HashSet<>();
			cntSet.put(newCnt, newSet);
		}
		newSet.add(x);
		if(newCnt>maxCount) {
			maxCount=newCnt;
		}
	}

	public int pop()
	{
		Set<Integer> maxSet = cntSet.get(maxCount);
		int i = stack.size()-1;
	    while(i>-1) {
	    	Integer x = stack.get(i);
	    	if(maxSet.contains(x)) {
	    		stack.remove(i);
	    		icnt.put(x, maxCount-1);
	    		maxSet.remove(x);
	    		while(maxCount>0 && cntSet.get(maxCount).size()==0) {
    				maxCount--;
    			}
	    		return x;
	    	}
	    	i--;
	    }
	    return -753;
	}
	
	public static void main(String[] args)
	{
		//String[] ops = new String[]{"FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"};
		//int[] numbers = new int[]{-1,5,7,5,7,4,5,-1,-1,-1,-1};
		String[] ops = new String[]{"FreqStack","push","push","push","push","push","push","pop","push","pop","push","pop","push","pop","push","pop","pop","pop","pop","pop","pop"};
		int[] numbers = new int[]{-1,4,0,9,3,4,2,-1,6,-1,1,-1,1,-1,4,-1,-1,-1,-1,-1,-1};
		FreqStack2 fq = null;
		for(int i=0;i<ops.length;i++) {
			if(ops[i].equals("FreqStack")) {
				fq = new FreqStack2();
			}else if(ops[i].equals("push")) {
				fq.push(numbers[i]);
			}else if(ops[i].equals("pop")) {
				numbers[i]=fq.pop();
			}else {
				System.out.println("Invalid operation:"+ops[i]);
			}
		}
		String res="";
		for(int i=0;i<numbers.length;i++) {
			res+=numbers[i]+",";
		}
		System.out.println(res);
	}
}
