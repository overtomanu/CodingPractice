
public class ArrayRotation
{
	public static int[] arrayRotate(int a[],int d)
	{
		int n=a.length;
		d=d%n;
		int gcd=gcd(n,d);
		for(int i=0;i<gcd;i++)
		{
			int temp=a[i];
			int j=i;
			while(true)
			{
				int k=j+d;
				if(k>=n)
				{
					k=k-n;
				}
				if(k==i)
				{
					break;
				}
				a[j]=a[k];
				j=k;
			}
			a[j]=temp;
		}
		return a;
	}
	
	public static int[] arrayRotateRight(int a[],int d)
	{
		int n=a.length;
		d=d%n;
		int gcd=gcd(n,d);
		for(int i=0;i<gcd;i++)
		{
			int temp=a[i];
			int j=i;
			while(true)
			{
				int k=j+d;
				if(k>=n)
				{
					k=k-n;
				}
				if(k==i)
				{
					break;
				}
				int temp2=a[k];
				a[k]=temp;
				j=k;
				temp=temp2;
			}
			a[i]=temp;
		}
		return a;
	}
	
	public static int gcd(int a,int b) {
		//System.out.println("Calculating gcd for "+a+","+b);
		int big,small;
		if(a==b)
		{
			return a;
		}
		if(a==0||b==0)
		{
			return 0;
		}
		if(a>b) 
		{
			big=a;small=b;
		}
		else
		{
			big=b;small=a;
		}
		//System.out.println("initial big,small:"+big+","+small);
		//System.out.println("big%small:"+big%small);
		while((big%small)!=0 && small!=1)
		{
			big=big%small;
			int temp=small;small=big;big=temp;
			//System.out.println(big+","+small);
		}
		return small;
	}
	
	public static void main(String[] args)
	{
		/*System.out.println("gcd 9,3:"+gcd(9, 3));
		System.out.println("gcd 7,3:"+gcd(7, 3));
		System.out.println("gcd 4,6:"+gcd(4, 6));*/
		
		int a[]=new int[] {1,2,3,4,5,6,7};
		printArray(a);
		printArray(arrayRotate(a, 4));
		a=new int[] {1,2,3,4,5,6,7,8,9,10};
		System.out.println();
		printArray(a);
		printArray(arrayRotate(a, 5));
		System.out.println();
		a=new int[] {1,2,3,4,5,6,7};
		printArray(a);
		printArray(arrayRotateRight(a, 4));
		a=new int[] {1,2,3,4,5,6,7,8,9,10};
		System.out.println();
		printArray(a);
		printArray(arrayRotateRight(a, 5));
	}
	
	public static void printArray(int a[])
	{
		String str="";
		for(int i:a)
		{
			str+=i+",";
		}
		if(str.length()>0) 
		{
			str=str.substring(0,str.length()-1);
		}
		System.out.println(str);
	}
}
