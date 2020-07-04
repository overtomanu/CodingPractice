import java.util.Arrays;
import java.util.stream.Collectors;

public class AlternatePositiveNegativeNumbers
{
	public static int[] alternatePosNegNum(int a[])
	{
		int n;
		if(a!=null && (n=a.length)>0)
		{
			int i=0,j=1;
			while(i<n && j<n)
			{
				if(a[i]<0 && a[j]>=0)
				{
					int temp=a[i];
					a[i]=a[j];
					a[j]=temp;
					i+=2;j+=2;
				}
				while(i<n && a[i]>=0)
				{
					i+=2;
				}
				while(j<n && a[j]<0)
				{
					j+=2;
				}
			}
			if(j>=n && i<n)
			{
				if(n%2==0)
				{
					j=n-2;
				}
				else
				{
					j=n-1;
				}
				while(i<j)
				{
					if(a[i]<0 && a[j]>=0)
					{
						int temp=a[i];
						a[i]=a[j];
						a[j]=temp;
						i+=2;j-=2;
					}
					while(i<n && a[i]>=0)
					{
						i+=2;
					}
					while(j>0 && a[j]<0)
					{
						j-=2;
					}
				}
			}
			if(i>=n && j<n)
			{
				if(n%2==0)
				{
					i=n-1;
				}
				else
				{
					i=n-2;
				}
				while(j<i)
				{
					if(a[i]<0 && a[j]>=0)
					{
						int temp=a[i];
						a[i]=a[j];
						a[j]=temp;
						i-=2;j+=2;
					}
					while(i>0 && a[i]>=0)
					{
						i-=2;
					}
					while(j<n && a[j]<0)
					{
						j+=2;
					}
				}
			}
			
		}
		return a;
	}
	
	public static void main(String[] args)
	{
		int a[]=new int[] {1,-1,-9,-2,3,3,3,5,-4,0};
		System.out.println(Arrays.stream(alternatePosNegNum(a)).boxed().collect(Collectors.toList()));
		a=new int[] {1,2,3,4,5,6};
		System.out.println(Arrays.stream(alternatePosNegNum(a)).boxed().collect(Collectors.toList()));
		a=new int[] {-1,-2,-3,-4,-5,-6,1,2,3,4,5,6};
		System.out.println(Arrays.stream(alternatePosNegNum(a)).boxed().collect(Collectors.toList()));
		a=new int[] {-1,-3,-7,-6,2,1};
		System.out.println(Arrays.stream(alternatePosNegNum(a)).boxed().collect(Collectors.toList()));
		a=new int[] {1,3,7,6,-2,-1};
		System.out.println(Arrays.stream(alternatePosNegNum(a)).boxed().collect(Collectors.toList()));
		a=new int[] {-19,-48,74,-22,8,78,34,65,50,24,54,68,93,-21,-78,-100,-54,-50};
		System.out.println(Arrays.stream(alternatePosNegNum(a)).boxed().collect(Collectors.toList()));

	}
}
