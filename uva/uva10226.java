import java.io.*;
import java.util.*;
import java.lang.*;

class Car
{
	public int len;
	public boolean isLeft; 
}

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
		fin.readLine();
		while (nTest-- > 0)
		{			
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			float total = 0;
			while(true)
			{
				String s = fin.readLine();
				if (s == null || s.trim().equals(""))
					break;
				int v = 1;
				if (map.containsKey(s))
				{
					v = map.get(s);
					v++;
				}
				map.put(s, v);
				total += 1;
			}
			
			Set<String> keys = map.keySet();
			String[] arKeys = keys.toArray(new String[0]);
			Arrays.sort(arKeys);
			for( int i = 0; i < arKeys.length; i++)
				fout.printf("%s %.4f\n", arKeys[i], map.get(arKeys[i])*100/total);
			if (nTest >= 1)
				fout.println();
		}
		
		fout.close(); //also flush content to stream
					  // if missing this line there is no printed output 
		System.exit(0);
	}
}		