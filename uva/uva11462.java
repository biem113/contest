import java.io.*;
import java.util.*;
import java.lang.*;

class Main 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader fin = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		//PrintWriter fout = new PrintWriter(new FileWriter("out.txt"));
		Boolean isFirst = true;
		while(true)
		{
			int nAge = Integer.parseInt(fin.readLine());
			if (nAge <= 0)
				break;
				
			if (!isFirst) {
				//fout.println("");				
			}
			
			StringTokenizer	tokenz = new StringTokenizer(fin.readLine());
			ArrayList<Integer> ages = new ArrayList<Integer>();
			while (tokenz.hasMoreTokens())
				ages.add(Integer.parseInt(tokenz.nextToken()));
			Collections.sort(ages);
			StringBuilder sb = new StringBuilder();
			for (int ag : ages)
				sb.append(ag + " ");
			
			fout.println(sb.toString().trim());
			isFirst = false;
		}
		
		fout.close();
		System.exit(0);
	}
}