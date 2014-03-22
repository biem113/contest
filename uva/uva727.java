import java.io.*;
import java.util.*;
import java.lang.*;

class Opr 
{
	public char op;
	public int  depth;
}

class Node 
{
	public Opr val;
	public Node l = null;
	public Node r = null;
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
	
	public static void addRight(Node root, Opr op)
	{
		if (root.r == null)
		{
			root.r = new Node();
			root.r.val = op;
		}
		else {
			root.r = addNode(root.r, op);
		}
	}
	
	public static void travel(Node root,LinkedList<Character> params)
	{
		if (root == null)
		{
			if (!params.isEmpty())
				System.out.printf("%c ", params.pop());
			return;
		}
		travel(root.l, params);
		travel(root.r, params);
		
		System.out.printf("%c ", root.val.op);
	}
	
	public static Node addNode(Node root, Opr rop)
	{
		if (rop.depth == root.val.depth)
			{
				int cr = comparePrio(root.val.op, rop.op);
				if (cr == 0 || cr > 0) //root have higher priority => push it left down
				{
					//replace root
					Node newroot = new Node();
					newroot.val = rop;
					newroot.l = root;
					root = newroot;
				}
				else // rop have higher priority // push rop right down
				{	// add rop to right
					addRight(root, rop);
				}
			} else if (rop.depth < root.val.depth)
			{
				//replace root
				Node newroot = new Node();
				newroot.val = rop;
				newroot.l = root;
				root = newroot;
			} else
			{
				// add rop right down
				addRight(root, rop);
			}
		return root;
	}
	
	public static Node makeTree(List<Opr> ops)
	{
		if (ops.size() <= 0) return null;
		Node root = new Node();
		root.val = ops.get(0);
		for (int i = 1; i < ops.size(); i++)
		{
			Opr rop = ops.get(i);
			root = addNode(root, rop);
		}
		return root;
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
					rank--;
					break;
				case '+':					
				case '-':					
				case '*':
				case '/':
					Opr op = new Opr();
					op.op = c;
					op.depth = rank;
					ops.add(op);
					break;
				default: // number
					params.add(c);
					break;
				}
			}
			
			Node root = makeTree(ops);
			travel(root, params);
			fout.println();
			fout.flush();
		}
		
		fout.println();
		fout.close(); //also flush content to stream
					  // if missing this line there is no printed output 
		System.exit(0);
	}
}		
	
	