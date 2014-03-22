import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;


class DNA
{
	public String dna;
	public int 	  score;
}
	
class Main
{
	static int dnaScore(String s)
	{
		int score = 0;
		for(int i = 0; i < s.length() - 1; i++)
		{
			for(int j = i + 1; j < s.length(); j++)
			{
				if(s.charAt(i) > s.charAt(j))
					score++;
			}
		}
		return score;
	}
	
	public static void main(String[] argv) throws IOException
	{
		Scanner fin = new Scanner(System.in);
		PrintWriter fout = new PrintWriter(System.out);
		int nTest = Integer.parseInt(fin.nextLine());
		long start = System.nanoTime();
		while (nTest-- > 0)
		{
			fin.nextLine();
			int len = fin.nextInt();
			int ndna = fin.nextInt();
			//ArrayList<DNA> seq = new ArrayList<DNA>();
			LinkedList<DNA> seq = new LinkedList<DNA>();
			while(ndna-- > 0)
			{
				DNA d = new DNA();
				d.dna = fin.next();
				d.score = dnaScore(d.dna);
				seq.add(d);
				//fout.printf("%s : %d\n",d.dna, d.score);
			}
			
			// for(int i = 0; i < seq.size(); i++) // bubble sort
			// {
				// for(int j = i + 1; j < seq.size(); j++)
				// {
					// if(seq.get(i).score <= seq.get(j).score)
						// continue;
					// DNA tmp = seq.get(i);
					// seq.set(i, seq.get(j));
					// seq.set(j, tmp);
				// }
			// }
			
			LinkedList<DNA> out = new LinkedList<DNA>();
			while(seq.size() > 0) {
				DNA mind = seq.get(0);
				int idx = 0;
				for (int i = 1; i < seq.size(); i++) {
					DNA d = seq.get(i);
					if (d.score < mind.score) {
						idx = i;
						mind = d;
					}					
				}
				out.add(mind);
				seq.remove(idx);
			}
			
			for(DNA d : out)
				fout.println(d.dna);
			
			if (nTest > 0)
				fout.println();
		}
		long end = System.nanoTime();
		fout.printf("Time: %d\n", end - start);
		fout.close();
		System.exit(0);
	}
}