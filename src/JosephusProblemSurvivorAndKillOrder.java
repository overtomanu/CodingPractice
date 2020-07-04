import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JosephusProblemSurvivorAndKillOrder
{
	public static Map<Integer,List<Integer>> josephusKillOrder(int n,int m)
	{
		List<Integer> killOrder = new ArrayList<>();
		List<Integer> numbers = new ArrayList<>();
		for(int i=0;i<n;i++)
		{
			numbers.add(i+1);
		}
		int killno=m;//list index will be killno-1
		while(numbers.size()>1)
		{
			if(killno>=numbers.size())
			{
				killno%=numbers.size();
				if(killno==0)
				{
					killno=numbers.size();
				}
			}
			killOrder.add(numbers.remove(killno-1));
			killno--;//offsetting index by 1 to compensate for deletion of a number
			killno+=m;
		}
		Map<Integer,List<Integer>> returnMap = new HashMap<>();
		returnMap.put(numbers.get(0), killOrder);
		return returnMap;
	}
	
	public static void main(String[] args)
	{
		System.out.println(josephusKillOrder(100, 2));
		System.out.println(josephusKillOrder(100, 3));
		System.out.println(josephusKillOrder(100, 7));
	}
}
