import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberPairSortedbyTheirProduct3Digit implements Comparable<NumberPairSortedbyTheirProduct3Digit>
{
	private int x,y,prod;
	
	public NumberPairSortedbyTheirProduct3Digit(int x, int y, int prod)
	{
		this.x=x;
		this.y=y;
		this.prod=prod;
	}
	
	public static void main(String[] args)
	{
		List<NumberPairSortedbyTheirProduct3Digit> list = new ArrayList<>();
		for(int i=999;i>=100;i--)
		{
			for(int j=999;j>=i;j--)
			{
				list.add(new NumberPairSortedbyTheirProduct3Digit(i, j, i*j));
			}
		}
		Collections.sort(list);
		Collections.reverse(list);
		for(NumberPairSortedbyTheirProduct3Digit num:list)
		{
			System.out.println(num.x+"*"+num.y+"="+num.prod+"|delta:"+(999-num.x+999-num.y));
		}
	}

	@Override
	public int compareTo(NumberPairSortedbyTheirProduct3Digit arg0)
	{
		if(arg0!=null)
		{
			return this.prod-arg0.getProd();
		}
		return 1;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getProd()
	{
		return prod;
	}
}
