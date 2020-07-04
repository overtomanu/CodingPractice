package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class FreqStack {
	Comparator<int[]> cmp = (int[] a,int[] b)->b[0]-a[0];
    PriorityQueue<int[]> pq = new PriorityQueue<>(cmp);
    List<Integer> stack = new ArrayList<>();
    Map<Integer,int[]> m = new HashMap<>();
    Map<Integer,int[]> freqMap = new HashMap<>();

    public FreqStack() {
        
    }
    
    public void push(int x) {
        stack.add(x);
        int[] xArr = m.get(x);
        if(xArr==null) {
        	xArr = new int[] {0,x};
        	m.put(x, xArr);
        }
        xArr[0]+=1;
        pq.remove(xArr);
        pq.add(xArr);
    }
    
    public int pop() {
    	int[] freq = pq.remove();
    	freqMap.put(freq[1],freq);
    	try {
	        while(pq.element()[0]==freq[0]) {
	        	int[] freq2 = pq.remove();
	        	freqMap.put(freq2[1],freq2);
	        }
    	}catch(Exception e) {
    		
    	}
        System.out.println("freqMap:"+freqMap);
        int i = stack.size()-1;
        while(i>-1) {
        	Integer x = stack.get(i);
        	int[] xArr = freqMap.get(x);
        	if(xArr!=null) {
        		xArr[0]-=1;
        		if(xArr[0]==0) {
        			freqMap.remove(x);
        			m.remove(x);
        		}
        		for(int[] entries: freqMap.values()) {
        			pq.add(entries);
        		}
        		stack.remove(i);
        		freqMap.clear();
        		return x;
        	}
        	i--;
        }
        return -1;
    }
    
    public static void main(String[] args)
	{
		String[] ops = new String[]{"FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"};
		int[] numbers = new int[]{-1,5,7,5,7,4,5,-1,-1,-1,-1};
		FreqStack fq = null;
		for(int i=0;i<ops.length;i++) {
			if(ops[i].equals("FreqStack")) {
				fq = new FreqStack();
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