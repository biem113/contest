import java.util.*;
import java.io.*;
import java.lang.*;

class Main
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader fin = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter fout = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		while(true)
		{
			StringTokenizer tok = new StringTokenizer(fin.readLine());
			int count = Integer.parseInt(tok.nextToken());
			if (count == 0)
				break;
			List<Integer> seq = new ArrayList<Integer>();			
			while (tok.hasMoreTokens())
			{
				int n = Integer.parseInt(tok.nextToken());				
				seq.add(n);
			}
			
			long moves = 0;
			//long begin = System.nanoTime();
			// for(int i = 1; i < seq.size(); i++)
			// {
				// for (int j = 0; j < seq.size() - i; j++)
				// {
					// if (seq.get(j) < seq.get(j+1))
						// continue;
					// int t = seq.get(j);
					//seq.set(j, seq.get(j+1));
					//seq.set(j+1, t);
					//moves++;
				// }
			// }
			//long end = System.nanoTime();
			//long total1 = end - begin;
						
			//begin = System.nanoTime();
			moves = mergesort(seq);
			//end = System.nanoTime();
			//long total2 = end - begin;
			//fout.println(seq);
			//fout.println(total1);
			//fout.println(total2);
			//fout.println(moves);
			if (moves % 2 == 0)
				fout.println("Carlos");
			else
				fout.println("Marcelo");
			
		}

		fout.close();
		System.exit(0);
	}
	
	public static long mergesort(List<Integer> seq)
	{
		//System.out.println(seq);
		int count = seq.size();
		if (count <= 1)
			return 0;
			
		List<Integer> left = new ArrayList<Integer>(seq.subList(0, count/2));
		List<Integer> right = new ArrayList<Integer>(seq.subList(count/2, count));
		long lc = mergesort(left);
		long rc = mergesort(right);
		long sc = lc + rc;
		
		int ls = left.size();
		int rs = right.size();
		int li = 0;
		int ri = 0;
		for (int i = 0; i < count; i++)
		{
			int cand = 0;
			//System.out.printf("li %d ri %d\n", li, ri);
			if (li < ls) {
				cand = left.get(li);				
				//System.out.printf("left[li] %d\n", cand);
				if (ri < rs) {
					if (cand > right.get(ri)) {
						cand = right.get(ri);
						sc += ls - li;
						ri++;
					}
					else
						li++;
				}
				else
					li++;
			}
			else
			{
				if (ri < rs)
					cand = right.get(ri++);
				else
					break;
			}
			
			seq.set(i, cand);
				
		}
		
		//System.out.println(seq);
		return sc;
	}
}