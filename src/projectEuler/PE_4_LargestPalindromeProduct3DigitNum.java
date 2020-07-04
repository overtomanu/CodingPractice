package projectEuler;

import java.util.ArrayList;
import java.util.List;

public class PE_4_LargestPalindromeProduct3DigitNum
{
	public static void main(String[] args)
	{
		int max=0;
		for(int i=999;i>=100;i--)
		{
			for(int j=999;j>=i;j--)
			{
				int product=i*j;
				//Improvement added to initial solution
				if(product<max)
				{
					break;
				}
				if(isPalindrome(product))
				{
					System.out.println(i+"*"+j+":"+product);
					if(product>max)
					{
						max=product;
					}
				}
			}
		}
		System.out.println("Largest Palindrome:"+max);
	}
	
	public static boolean isPalindrome(int n) 
	{
		List<Integer> numbers = new ArrayList<>();
		while(n>0)
		{
			int num = n%10;
			numbers.add(num);
			n=n/10;
		}
		int size = numbers.size();
		int half = size/2;
		for(int i=0;i<half;i++)
		{
			if(numbers.get(i)!=numbers.get(size-1-i))
			{
				return false;
			}
		}
		return true;
	}
}
