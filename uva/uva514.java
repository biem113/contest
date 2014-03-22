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
				
		boolean first = true;
		while(true)
		{
			int nCoach = Integer.parseInt(fin.readLine());
			if (!first && nCoach > 0)
				fout.println();
			
			first = false;
			while (nCoach > 0) //for 1 block of input
			{
				ArrayList<Integer> exp= readInts(fin.readLine());				
				if (exp.size() == 1 && exp.get(0) == 0)
				{
					break;
				}
								
				int curID = 1;
				int eIdx = 0;			
				boolean can_sort = true;
				
				LinkedList<Integer> act = new LinkedList<Integer>();
				while(eIdx < nCoach) 
				{					
					int topC = exp.get(eIdx);					
					while(curID <= topC)
						act.push((Integer)curID++);
					
					while (act.size() > 0)
					{
						// fout.printf("a.peek %d exp.get %d\n", act.peek(), exp.get(eIdx));
						// fout.printf("a.peek %d exp.get %d\n", act.peek(), exp.get(eIdx));
						// fout.println(act.peek() == exp.get(eIdx));
						// fout.println((int)act.peek() == 999);
						// fout.println(999 == (int)exp.get(eIdx));
						if ((int)act.peek() == (int)exp.get(eIdx))
						{
							eIdx++;
							act.pop();
						}
						else {
							if ((int)act.peek() > (int)exp.get(eIdx))
							{
								can_sort = false;
							}
							break;
						}
					}
					
					if (!can_sort)
						break;
				}
				//fout.println(act.size());
				//fout.println(can_sort);
				if (act.size() == 0 && can_sort)
					fout.println("Yes");
				else
					fout.println("No");
			}
			
			if (nCoach == 0 )
				break;
		}
		fout.println();
		fout.close(); //also flush content to stream
					  // if missing this line there is no printed output 
		System.exit(0);
	}
}		
	
	