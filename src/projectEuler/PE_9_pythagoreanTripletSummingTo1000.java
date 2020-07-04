package projectEuler;

public class PE_9_pythagoreanTripletSummingTo1000
{
	public static void main(String[] args)
	{
		for(int i=1;i<1000;i++)
		{
			for(int j=i;j<1000;j++)
			{
				double k = Math.sqrt(i*i+j*j);
				int kInt = (int)k;
				if(k-kInt == 0)
				{
					if(i+j+k==1000)
					{
						System.out.println(i+","+j+","+k+"| product="+i*j*kInt);
					}
				}
			}
		}
	}
}
