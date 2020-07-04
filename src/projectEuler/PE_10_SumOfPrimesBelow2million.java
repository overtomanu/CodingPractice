package projectEuler;

public class PE_10_SumOfPrimesBelow2million
{
	public static void main(String[] args)
	{
		Long i=5L;
		Long sum=(long) (2+3);
		// 1 is not considered prime
		contWhile:while (i<2000000L)
		{
			for(Long j=3L;j<=i/2;j+=2)
			{
				//System.out.println(i+"%"+j+"=="+i%j);
				if(i%j==0)
				{
					i+=2;
					continue contWhile;
				}
			}
			System.out.println("Adding "+i+" to "+sum);
			sum+=i;
			i+=2;
		}
		System.out.println("Sum of primes below 2 million:"+sum);
	}
}
