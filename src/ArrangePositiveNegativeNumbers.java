import java.util.Arrays;
import java.util.stream.Collectors;

public class ArrangePositiveNegativeNumbers
{
	//https://www.geeksforgeeks.org/rearrange-positive-and-negative-numbers-publish/
	public static int[] arrangePositiveNegativeNumbers(int[] a)
	{
		if(a!=null && a.length>0)
		{
			int i=0,j=a.length-1;
			while(i<j)
			{
				if(a[i]<0 && a[j]>=0)
				{
					int temp=a[i];
					a[i]=a[j];
					a[j]=temp;
					i++;j--;
				}
				
				while(i<a.length && a[i]>=0)
				{
					i++;
				}
				while(j>=0 && a[j]<0)
				{
					j--;
				}
			}
		}
		
		return a;
	}
	
	public static void main(String[] args)
	{
		int a[]=new int[] {1,-1,2,-2,3,3,3,-4,0};
		System.out.println(Arrays.stream(arrangePositiveNegativeNumbers(a)).boxed().collect(Collectors.toList()));
	}
}
