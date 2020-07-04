package projectEuler;

public class PE_2_EvenFibonacciSumBelow4million
{
	public static void main(String[] args)
	{
		int cur=2,prev=1,sum=2;
		
		while(cur<=4000000)
		{
			int temp=cur;
			cur+=prev;
			prev=temp;
			if(cur%2==0) {
				sum+=cur;
			}
		}
		System.out.println(sum);
	}
}
