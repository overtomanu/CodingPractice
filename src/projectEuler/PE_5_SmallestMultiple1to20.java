package projectEuler;

public class PE_5_SmallestMultiple1to20
{
	public static void main(String[] args)
	{
		//16*9*5*7*11*13*17*19 = 232792560
		//above number is the multiple by taking into account all factors
		outer:for(int i=21;i<=232792560;i++)
		{
			//System.out.println("checking:"+i);
			for(int j=2;j<=20;j++)
			{
				if(i%j!=0)
				{
					continue outer;
				}
			}
			System.out.println(i);
			break;
		}
	}
}
