/* package whatever; // don't place package name! */

import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Main
{
	static int readInts(BufferedReader fin, ArrayList<Integer> ints) throws IOException
	{
		StringTokenizer st = new StringTokenizer(fin.readLine());
		while (st.hasMoreTokens()) {
			ints.add(Integer.parseInt(st.nextToken()));
		}
		return ints.size();
	}
	
	static int readStrings(BufferedReader fin, ArrayList<String> ints) throws IOException
	{
		StringTokenizer st = new StringTokenizer(fin.readLine());
		while (st.hasMoreTokens()) {
			ints.add(st.nextToken());
		}
		return ints.size();
	}
	
	static <E> void printList(PrintWriter fout, List<E> l) {
		for (E e:l) {
			fout.println(e);
		}
	}
	
	public static void main (String[] args) throws IOException
	{
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		//BufferedReader f = new BufferedReader(new FileReader("test.in"));
													  // input file name goes above
		//PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.out")));
		// Use StringTokenizer vs. readLine/split -- lots faster
		//StringTokenizer st = new StringTokenizer(f.readLine()); // Get line, break into tokens
		
		//Scanner cin = new Scanner(System.in);
		//System.out.println(s);
		BufferedReader fin = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter fout = new PrintWriter(System.out);
		int nTest = Integer.parseInt(fin.readLine());
		while(nTest-- > 0)
		{
			fin.readLine();
			ArrayList<Integer> lidx = new ArrayList<Integer>();
			ArrayList<String> lstrs = new ArrayList<String>();
			int n =readInts(fin, lidx);
			readStrings(fin, lstrs);
			ArrayList<String> louts = new ArrayList<String>(n);
			// ArrayList: Must add objects before get/set
			for (int i = 0; i < n; i++)
				louts.add(null);
				
			for (int i =0; i < n; i++) {
				louts.set(lidx.get(i)-1, lstrs.get(i));
			}
			printList(fout, louts);
			if (nTest > 0)
				fout.println("");
		}
		
		fout.close();
		System.exit(0);
	}
}