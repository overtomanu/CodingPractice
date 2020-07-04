package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeautifulArrayVerificationCode
{
	public static Map<Integer, int[]> memo = new HashMap();

	public static void main(String[] args)
	{

		// int r[] =
		// {1,65,33,97,17,81,49,57,9,89,41,73,25,53,5,85,29,69,21,77,13,93,61,45,37,51,3,83,27,67,19,99,11,91,15,59,75,71,39,87,95,7,23,63,31,47,79,55,35,43,26,78,2,94,18,86,10,90,50,98,74,34,42,6,8,82,66,58,14,70,100,68,84,38,12,92,28,76,4,44,60,52,20,36,48,96,32,16,24,64,88,22,72,40,56,80,46,62,30,54};
		// printIntList(verifyArray(r));
		/*for (int i = 1; i <= 1000; i++)
		{
			int[] bArray = f(i);
			List<int[]> failed = verifyArray(bArray);
			if (failed.size() > 0)
			{
				System.out.println("*******Failed for number: " + i);
				System.out.println("Beautiful Array:\n");
				for (int l = 0; l < bArray.length; l++)
				{
					System.out.print(bArray[l] + ",");
				}
				System.out.println();
				printIntList(failed);
				break;
			}

		}*/
		for (int i = 1; i <= 10; i++)
		{
			int[] bArray = f(i);
			System.out.println("Beautiful Array:\n");
			for (int l = 0; l < bArray.length; l++)
			{
				System.out.print(bArray[l] + ",");
			}
			System.out.println();
		}
	}

	public static int[] beautifulArray1(int N)
	{
		int[] r = new int[N];
		for (int i = 0; i < N; i++)
		{
			r[i] = i + 1;
		}
		int flip = 0;
		for (int i = 0; i < N; i++)
		{
			for (int j = N - 1; j >= i + 2; j--)
			{
				for (int k = i; k <= j; k++)
				{
					if (2 * r[k] == r[i] + r[j])
					{
						if (flip == 0)
						{
							int temp = r[j];
							r[j] = r[k];
							r[k] = temp;
							flip = 1;
						}
						else
						{
							int temp = r[i];
							r[i] = r[k];
							r[k] = temp;
							flip = 0;
						}
					}

				}
			}
		}
		return r;
	}

	public static int[] beautifulArray(int N)
	{
		int[] r = new int[N];

		if (N == 1)
		{
			return new int[]
			{
				1
			};
		}
		if (N == 2)
		{
			return new int[]
			{
				1, 2
			};
		}

		int half = N / 2;
		if (N % 2 == 1)
		{
			r[N - 1] = half + 1;
		}
		for (int i = 0; i < half; i++)
		{
			r[half - 1 - i] = i + 1;
		}
		for (int i = half; i < 2 * half; i++)
		{
			r[half + half + half - 1 - i] = i + 1;
		}

		return r;
	}

	public static List<int[]> verifyArray(int r[])
	{
		int N = r.length;
		List<int[]> failed = new ArrayList<>();
		for (int i = 0; i < N; i++)
		{
			for (int j = i + 2; j < N; j++)
			{
				for (int k = i; k <= j; k++)
				{
					if (2 * r[k] == r[i] + r[j])
					{
						int[] fail = new int[]
						{
							i, k, j, r[i], r[k], r[j]
						};
						failed.add(fail);
					}

				}
			}
		}
		return failed;
	}

	public static int[] beautifulArray2(int N)
	{
		System.out.println("*****N=" + N + "\n--------------------");
		int[] r = new int[N];
		for (int i = 0; i < N; i++)
		{
			r[i] = i + 1;
		}
		for (int l = 0; l < r.length; l++)
		{
			System.out.print(r[l] + ",");
		}
		System.out.println();
		for (int d = 2; d < N; d++)
		{
			for (int i = 0; i < N; i++)
			{
				int j = i + d;
				if (j >= N)
				{
					continue;
				}
				for (int k = i; k <= j; k++)
				{
					if (2 * r[k] == r[i] + r[j])
					{
						int temp = r[k];
						for (int s = k; s < j; s++)
						{
							r[s] = r[s + 1];
						}
						r[j] = temp;

						for (int l = 0; l < r.length; l++)
						{
							System.out.print(r[l] + ",");
						}
						System.out.println();
					}

				}
			}
		}
		return r;
	}

	//https://leetcode.com/problems/beautiful-array/solution/
	public static int[] f(int N)
	{
		if (memo.containsKey(N))
			return memo.get(N);

		int[] ans = new int[N];
		if (N == 1)
		{
			ans[0] = 1;
		}
		else
		{
			int t = 0;
			for (int x : f((N + 1) / 2)) // odds
				ans[t++] = 2 * x - 1;
			for (int x : f(N / 2)) // evens
				ans[t++] = 2 * x;
		}
		memo.put(N, ans);
		return ans;
	}

	public static void printIntList(List<int[]> intList)
	{
		for (int i = 0; i < intList.size(); i++)
		{
			int[] a = intList.get(i);
			System.out.println(a[0] + "\t\t" + a[1] + "\t\t" + a[2] + "\n" + a[3] + "\t\t"
					+ a[4] + "\t\t" + a[5]);
			System.out.println("------------------------------------------");
		}
	}
}
