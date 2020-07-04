package leetcode;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.BitSet;

public class ThreeEqualParts
{
	
	public static void main(String[] args)
	{
		
		//System.out.println(System.getProperties());
		try
		{
			String inpStr = IOUtil.ReaderToString(new FileReader("leetcodeThreeEqualParts.txt"));
			BitSet bitset = new BitSet();
			int i=0;
			for(Character c:inpStr.toCharArray())
			{
				if(c.equals('1')) {
					bitset.set(i++);
				}else if(c.equals('0')) {
					bitset.clear(i++);
				}else if(c.equals(',')) {
					//Do nothing
				}
			}
			int a[] = new int[bitset.length()];
			for(i=0;i<bitset.length();i++)
			{
				if(bitset.get(i)) {
					a[i]=1;
				}else {
					a[i]=0;
				}
			}
			//System.out.println(bitset);
			ThreeEqualParts sol = new ThreeEqualParts();
			a=new int[] {1,1,1,0,0,1,1,1,1,1,1};
			System.out.println("Solution:"+sol.arrayToStr(sol.threeEqualParts3(a)));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String arrayToStr(int[] a) {
    	String str="";
    	for(int i:a) {
    		str+=i+",";
    	}
    	return str;
    }
	
	public int[] threeEqualParts(int[] a) {
        int l = a.length;
        if(l<3){
            return  new int[]{-1,-1};
        }
        
        /*List<Integer> n1 = new ArrayList<>();
        List<Integer> n2 = new ArrayList<>();
        List<Integer> n3 = new ArrayList<>();*/
        
        for(int i=0;i<l-2;i++){
            for(int j=i+1;j<l-1;j++){
                int l1 = i+1;
                int l2 = j-i+1;
                int l3 = l-j;
                int max = l1;
                if(l2>max){
                    max=l2;
                }
                if(l3>max){
                    max=l3;
                }
                int i1 = i;
                int j1 = j;
                int k1 = l-1;
                boolean matched = true;
                //System.out.println("i j: "+i+","+j);
                for(int k=0;k<max;k++){
                    int v1,v2,v3;
                    //System.out.println("indexes:"+i1+","+j1+","+k1);
                    if(i1<0){
                        v1=0;
                    }else{
                        v1=a[i1--];
                    }
                    
                    if(j1<i+1){
                        v2=0;
                    }else{
                        v2=a[j1--];
                    }
                    
                    if(k1<j+1){
                        v3=0;
                    }else{
                        v3=a[k1--];
                    }
                    //System.out.println("Comparing: "+v1+","+v2+","+v3);
                    if(v1!=v2 || v1!=v3){
                        matched=false;
                    }
                }
                if(matched){
                    return new int[]{i,j+1};
                }
            }
        }
        return new int[]{-1,-1};
    }
	
	public int[] threeEqualParts2(int[] a) {
        int l = a.length;
        if(l<3){
            return  new int[]{-1,-1};
        }
        
        int sum = 0;
        int s[] = new int[a.length];
        for(int i=0;i<l;i++){
            sum+=a[i];
            s[i]=sum;
        }
        System.out.println("Sum:"+sum);
        if(sum%3!=0){
            return new int[]{-1,-1};
        }
        
        if(sum==0){
            return new int[]{0,1};
        }
        
        int ones = sum/3;
        int twoOnes = 2*ones;
        
        int imin=-1,imax=-1,jmin=-1,jmax=-1;
        for(int i=0;i<s.length;i++){
            if(imin==-1 && s[i]==ones){
                imin=i;
            }
            if(s[i]>ones && s[i-1]==ones){
                imax=i-1;
            }
            if(jmin==-1 && s[i]==twoOnes){
                jmin=i;
            }
            if(s[i]>twoOnes && s[i-1]==twoOnes){
                jmax=i-1;
            }
        }
        
        System.out.println(imin+","+imax+","+jmin+","+jmax);
        System.out.println(arrayToStr(s));
        
        if(imin==-1 || imax==-1 || jmin==-1 || jmax==-1){
            return new int[]{-1,-1};
        }
        for(int i=imin;i<=imax;i++){
            for(int j=jmin;j<=jmax;j++){
                int l1 = i+1;
                int l2 = j-i+1;
                int l3 = l-j;
                int max = l1;
                if(l2>max){
                    max=l2;
                }
                if(l3>max){
                    max=l3;
                }
                int i1 = i;
                int j1 = j;
                int k1 = l-1;
                boolean matched = true;
                //System.out.println("i j: "+i+","+j);
                for(int k=0;k<max;k++){
                    int v1,v2,v3;
                    //System.out.println("indexes:"+i1+","+j1+","+k1);
                    if(i1<0){
                        v1=0;
                    }else{
                        v1=a[i1--];
                    }
                    
                    if(j1<i+1){
                        v2=0;
                    }else{
                        v2=a[j1--];
                    }
                    
                    if(k1<j+1){
                        v3=0;
                    }else{
                        v3=a[k1--];
                    }
                    //System.out.println("Comparing: "+v1+","+v2+","+v3);
                    if(v1!=v2 || v1!=v3){
                        matched=false;
                    }
                }
                if(matched){
                    return new int[]{i,j+1};
                }
            }
        }
        return new int[]{-1,-1};
    }
	
	public int[] threeEqualParts3(int[] A) {
        int[] IMP = new int[]{-1, -1};
        int N = A.length;

        int S = 0;
        for (int x: A) S += x;
        if (S % 3 != 0) return IMP;
        int T = S / 3;
        if (T == 0)
            return new int[]{0, N - 1};

        int i1 = -1, j1 = -1, i2 = -1, j2 = -1, i3 = -1, j3 = -1;
        int su = 0;
        for (int i = 0; i < N; ++i) {
            if (A[i] == 1) {
                su += 1;
                if (su == 1) i1 = i;
                if (su == T) j1 = i;
                if (su == T+1) i2 = i;
                if (su == 2*T) j2 = i;
                if (su == 2*T + 1) i3 = i;
                if (su == 3*T) j3 = i;
            }
        }

        // The array is in the form W [i1, j1] X [i2, j2] Y [i3, j3] Z
        // where [i1, j1] is a block of 1s, etc.
        int[] part1 = Arrays.copyOfRange(A, i1, j1+1);
        int[] part2 = Arrays.copyOfRange(A, i2, j2+1);
        int[] part3 = Arrays.copyOfRange(A, i3, j3+1);

        if (!Arrays.equals(part1, part2)) return IMP;
        if (!Arrays.equals(part1, part3)) return IMP;

        // x, y, z: the number of zeros after part 1, 2, 3
        int x = i2 - j1 - 1;
        int y = i3 - j2 - 1;
        int z = A.length - j3 - 1;

        if (x < z || y < z) return IMP;
        return new int[]{j1+z, j2+z+1};
    }
}
