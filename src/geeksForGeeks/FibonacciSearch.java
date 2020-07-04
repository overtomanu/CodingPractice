package geeksForGeeks;

class FibonacciSearch
{
	// Utility function to find minimum
	// of two elements
	public static int min(int x, int y)
	{
		return (x <= y) ? x : y;
	}

	/* Returns index of x if present, else returns -1 */
	public static int fibMonaccianSearch(int arr[], int x, int n)
	{
		/* Initialize fibonacci numbers */
		int fibMMm2 = 0; // (m-2)'th Fibonacci No.
		int fibMMm1 = 1; // (m-1)'th Fibonacci No.
		int fibM = fibMMm2 + fibMMm1; // m'th Fibonacci

		/* fibM is going to store the smallest 
		Fibonacci Number greater than or equal to n */
		while (fibM < n)
		{
			fibMMm2 = fibMMm1;
			fibMMm1 = fibM;
			fibM = fibMMm2 + fibMMm1;
		}

		// Marks the eliminated range from front
		int offset = 0;

		/* while there are elements to be inspected. 
		Note that we compare arr[fibMm2] with x. 
		When fibM becomes 1, fibMm2 becomes 0 */
		while (fibM > 1)
		{
			// Check if fibMm2 is a valid location
			int i = min(offset + fibMMm2, n - 1);
			System.out.println(fibM+"|"+fibMMm1+"|"+fibMMm2+"|offset="+offset+"|i="+i);
			/* If x is greater than the value at 
			index fibMm2, cut the subarray array 
			from offset to i */
			if (arr[i] < x)
			{
				fibM = fibMMm1;
				fibMMm1 = fibMMm2;
				fibMMm2 = fibM - fibMMm1;
				offset = i;
			}

			/* If x is greater than the value at index 
			fibMm2, cut the subarray after i+1 */
			else if (arr[i] > x)
			{
				fibM = fibMMm2;
				fibMMm1 = fibMMm1 - fibMMm2;
				fibMMm2 = fibM - fibMMm1;
			}

			/* element found. return index */
			else
				return i;
		}

		/* comparing the last element with x */
		System.out.println("Checking "+(offset));
		if (arr[offset] == x)
		{
			System.out.println("Returning "+(offset));
			return offset;
		}

		/*element not found. return -1 */
		return -1;
	}

	// driver code
	public static void main(String[] args)
	{
		System.out.println("Found at index: " + fibMonaccianSearch(new int[] {40}, 40, 1));
		int arr[] =
		{
			10, 22, 35, 40, 45, 50, 80, 82, 85, 90, 100,110,120,130,140,150,160,170,180,190,200
		};
		System.out.println("Found at index: " + fibMonaccianSearch(arr, 85, arr.length));
		System.out.println("Found at index: " + fibMonaccianSearch(arr, 10, arr.length));
		System.out.println("Found at index: " + fibMonaccianSearch(arr, 100, arr.length));
		System.out.println("Found at index: " + fibMonaccianSearch(arr, 200, arr.length));
		System.out.println("Found at index: " + fibMonaccianSearch(arr, 201, arr.length));
		
	}
}

// This code is contributed by rishabh_jain