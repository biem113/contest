import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.*;

/***
1.
What's wrong with the following snippet?
ArrayList<int> nums; // {1, 2, 3}
int anum = nums[1]; // error array require but ArrayList found

2. ArrayList<int> // error require reference but found int
*/

class Main 
{
	static ArrayList<Integer> readInts(String s)
	{
		ArrayList<Integer> nums = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(s);
		while(st.hasMoreTokens())
			nums.add(Integer.parseInt(st.nextToken()));
		return nums;
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader fin = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter fout = new PrintWriter(System.out);
		int nTest = Integer.parseInt(fin.readLine());
		//fout.printf("helo\n");
		//System.out.println("hello system.out\n");
		while(nTest-- > 0)
		{
			int nCarr = Integer.parseInt(fin.readLine());
			ArrayList<Integer> carrs= readInts(fin.readLine());
			int nSwap = 0;
			while(nCarr-- > 0)
				for (int i = 0; i < carrs.size() - 1; i++)
				{
					if (carrs.get(i) < carrs.get(i+1))
						continue;
					int tmp = carrs.get(i);
					carrs.set(i, carrs.get(i+1));
					carrs.set((i+1), tmp);
					nSwap++;
				}
			
			fout.printf("Optimal train swapping takes %d swaps.\n", nSwap);			
		}
		
		fout.close(); //also flush content to stream
					  // if missing this line there is no printed output 
		System.exit(0);
	}
}