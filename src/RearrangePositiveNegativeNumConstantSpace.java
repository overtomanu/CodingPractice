import java.util.Arrays;
import java.util.stream.Collectors;

public class RearrangePositiveNegativeNumConstantSpace
{
	//https://www.geeksforgeeks.org/rearrange-positive-and-negative-numbers/
	public static int[] rearrangePositiveNegativeNumConstantSpace(int a[])
	{
		int n;
		if(a!=null && (n=a.length)>1)
		{
			int i,pi=0;//positive number index
			while(pi<n && a[pi]<0)
			{
				pi++;
			}
			if(pi>n-2)
			{
				return a;
			}
			i=pi+1;
			while(i<n)
			{
				if(a[i]<0)
				{
					int temp=a[i];
					//shift positive numbers
					for(int j=i;j>pi;j--)
					{
						a[j]=a[j-1];
					}
					a[pi]=temp;
					pi++;
				}
				i++;
			}
		}
		return a;
	}
	
	public static void main(String[] args)
	{
		int a[]=new int[] {1,-1,-9,-2,3,3,3,5,-4,0};
		System.out.println(Arrays.stream(rearrangePositiveNegativeNumConstantSpace(a)).boxed().collect(Collectors.toList()));
		a=new int[] {1,2,3,4,5,6};
		System.out.println(Arrays.stream(rearrangePositiveNegativeNumConstantSpace(a)).boxed().collect(Collectors.toList()));
		a=new int[] {-1,-2,-3,-4,-5,-6};
		System.out.println(Arrays.stream(rearrangePositiveNegativeNumConstantSpace(a)).boxed().collect(Collectors.toList()));
		a=new int[] {-1,-2,-3,-4,-5,-6,1,2,3,4,5,6};
		System.out.println(Arrays.stream(rearrangePositiveNegativeNumConstantSpace(a)).boxed().collect(Collectors.toList()));
		a=new int[] {-1,-3,-7,-6,2,1};
		System.out.println(Arrays.stream(rearrangePositiveNegativeNumConstantSpace(a)).boxed().collect(Collectors.toList()));
		a=new int[] {1,3,7,6,-2,-1};
		System.out.println(Arrays.stream(rearrangePositiveNegativeNumConstantSpace(a)).boxed().collect(Collectors.toList()));
		a=new int[] {-19,-48,74,-22,8,78,34,65,50,24,54,68,93,-21,-78,-100,-54,-50};
		System.out.println(Arrays.stream(rearrangePositiveNegativeNumConstantSpace(a)).boxed().collect(Collectors.toList()));

	}
}
