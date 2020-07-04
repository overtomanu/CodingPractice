package projectEuler;

public class PE_7_NthPrimeNumber
{
	public static Long getNthPrime(Long n)
	{
		if(n<3)
		{
			return n+1;
		}
		Long i=4L;
		Long nthPrime=0L;
		// 1 is not considered prime
		Long primeNumCount=2L;
		contWhile:while (primeNumCount<n)
		{
			for(Long j=2L;j<=i/2;j++)
			{
				//System.out.println(i+"%"+j+"=="+i%j);
				if(i%j==0)
				{
					i++;
					continue contWhile;
				}
			}
			primeNumCount++;
			nthPrime=i;
			System.out.println(primeNumCount+"th prime:"+i);
			i++;
		}
		return nthPrime;
	}
	
	public static void main(String[] args)
	{
		System.out.println("10001th prime: "+getNthPrime(10001L));
	}
}
