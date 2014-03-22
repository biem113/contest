import java.io.*;
import java.util.*;
import java.lang.*;

class Opr 
{
	public char op;
	public int  depth;
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
	
	public static int compareDepth(Opr a, Opr b)
	{
		//if (a.depth == b.depth)
		//	return comparePrio(a.op, b.op);
		return a.depth - b.depth;
	}
	
	public static int comparePrio(char a, char b)
	{
		if ((a == '+' || a == '-') && (b == '+' || b == '-'))
			return 0;
		if ((a == '+' || a == '-') && (b == '*' || b == '/'))
			return -1;
		if ((a == '*' || a == '/') && (b == '+' || b == '-'))
			return 1;
		if ((a == '*' || a == '/') && (b == '*' || b == '/'))
			return 0;
		return 0;
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader fin = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter fout = new PrintWriter(System.out);		
		int nTest = Integer.parseInt(fin.readLine());
		fin.readLine();
		
		while (nTest-- > 0)
		{
			LinkedList<Opr> ops = new LinkedList<Opr>();
			LinkedList<Character> params = new LinkedList<Character>();
			int rank = 0;
			char cand = 0;
			boolean pushOps = false;
			StringBuilder ret = new StringBuilder();
			while(true)
			{
				String s = fin.readLine();
				//System.out.println(s);
				if (s == null || s.length() <= 0)
					break;
				char c = s.charAt(0);
				switch (c)
				{
				case '(':
					rank++;					
					break;
				case ')':
					while (ops.size() > 0)
					{
						if (ops.peek().depth == rank)
							fout.printf(String.format("%c", ops.pop().op));	
						else
							break;
					}
					rank--;
					break;
				case '+':					
				case '-':					
				case '*':
				case '/':
					Opr op = new Opr();
					op.op = c;
					op.depth = rank;
					if (ops.size() == 0)
						ops.push(op);
					else
					{
						boolean added = false;
						while (ops.size() > 0)
						{
							int cdeep = compareDepth(op, ops.peek());
							if (cdeep < 0)
							{
								fout.printf(String.format("%c", ops.pop().op));																
							}
							else if (cdeep > 0)
							{
								ops.push(op);
								added = true;
								break;
							}
							else
							{
								int cret = comparePrio(op.op, ops.peek().op);
								if (cret == 0 || cret < 0)
								{
									fout.printf(String.format("%c", ops.pop().op));									
								}
								else
								{
									ops.push(op);
									added = true;
									break;
								}
							}							
						}
						if (!added) ops.push(op);
					}	
					break;
				default: // number
					fout.printf(String.format("%c", c));
					//if (ops.size() == 2)
					//	fout.printf(String.format("%c", ops.pop().op));
					break;
				}
			}
			for (Opr o : ops)
				fout.printf(String.format("%c", o.op));
			fout.println();
			if (nTest >= 1)
				fout.println();
			//fout.flush();
		}
		
		fout.println();
		fout.close(); //also flush content to stream
					  // if missing this line there is no printed output 
		System.exit(0);
	}
}		
/*	
pout, opout
pwait, opwait
2 + 3
2 + 3 * 4
2 + 3 * 4 * 5
(2 + 3) * 4 * 5

2 3 5 * +
2 3 * 5 +
2 3 * 4 5 * 6 * +
2 3 + 5 *
2 3 + 4 5 + *
2 3 + 4 + 5 +
2 3 + 5 * 4 + 6 *

if pa:
		out pa
		if opwait.size() == 2: //an op wait for right param
			out opwait.pop()
if op
	if opwait.size() == 0
		opwwait.push(op)
	if opwait.size() == 1 // 2 + 3 * 5
		cret = compare(op, opwait.top()) 
	   if cret == 0
			opwait.add(oop)
		if cret < 0:
			out opwait.pop()
		if cret > 0:
			opwait.push(op)	
*/
			