package projectEuler;

import java.util.ArrayList;
import java.util.List;

public class PE_3_LargestPrimeFactor
{
	public static long largestPrimeFactor(long n)
	{
		while(n%2==0 && n>2)
		{
			n=n/2;
		}
		//n is odd
		if(isPrime(n))
		{
			return n;
		}
		List<Long> factors = new ArrayList<>(); 
		long half = n/2;
		for(long i=3;i<half;i+=2)
		{
			if(n%i==0)
			{
				long factor=n/i;
				if(isPrime(factor))
				{
					return factor;
				}
				factors.add(i);
			}
		}
		for(int i=factors.size()-1;i>=0;i--)
		{
			long factor = factors.get(i);
			if(isPrime(factor))
			{
				return factor;
			}
		}
		return n;
	}
	
	public static boolean isPrime(long n)
	{
		if(n>3)
		{
			long half = n/2;
			for(int i=2;i<=half;i++)
			{
				if(n%i==0)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args)
	{
		System.out.println(largestPrimeFactor(4096));
	}
}
