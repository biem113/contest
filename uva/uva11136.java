import java.io.*;
import java.util.*;
import java.lang.*;

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
		
		while (true)
		{
			int nday = Integer.parseInt(fin.readLine());
			if (nday == 0) break;
			TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
			long total = 0;
			for (int i = 0; i < nday; i++)
			{
				ArrayList<Integer> line = readInts(fin.readLine());
				for (int j = 1; j < line.size(); j++)
				{
					int key = line.get(j);
					if (map.containsKey(key))
					{
						map.put(key, map.get(key) + 1);
					}
					else
						map.put(key, 1);
				}
				Map.Entry<Integer, Integer> mine = map.firstEntry();
				Map.Entry<Integer, Integer> maxe = map.lastEntry();
				if (mine != null && maxe != null)
				{
					total += maxe.getKey() - mine.getKey();
					if (mine.getValue() == 1)
						map.remove(mine.getKey());
					else
						map.put(mine.getKey(), mine.getValue() - 1);
						
					if (maxe.getValue() == 1)
						map.remove(maxe.getKey());
					else
						map.put(maxe.getKey(), maxe.getValue() - 1);
				}
			}
			fout.println(total);
		}
		
		fout.close(); //also flush content to stream
					  // if missing this line there is no printed output 
		System.exit(0);
	}
}		