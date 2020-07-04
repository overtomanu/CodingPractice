
public class BinarySearch
{
	public static int binarySearch(int a[],int key,boolean isAscending)
	{
		int index=-1;
		int n=a.length;
		int low=0,high=n-1;
		int mid;
		while(low<=high)
		{
			mid=(high+low)/2;
			//System.out.println("low:"+low+"|high:"+high+"|mid:"+mid);
			if(a[mid]==key)
			{
				return mid;
			}
			if(key>a[mid])
			{
				if(isAscending)
				{
					low=mid+1;
				}
				else
				{
					high=mid-1;
				}
			}//key is less than mid
			else
			{
				if(isAscending)
				{
					high=mid-1;
				}
				else
				{
					low=mid+1;
				}
			}
		}
		return index;
	}

	public static void main(String[] args)
	{
		int[] a=new int[] {1,2,3,4,5,6,7,8,9,10,11};
		System.out.println(binarySearch(a, 1, true));
		a=new int[] {11,10,9,8,7,6,5,4,3,2,1};
		System.out.println(binarySearch(a, 1, false));
	}
}
