import java.io.*;
import java.util.*;
import java.lang.*;

class Vertex
{
	public int id;
	public List<Vertex>  adjs = new LinkedList<Vertex>();
	
	public Vertex() {}
	public Vertex(int id) { this.id = id;}
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
	
	public static LinkedList<Vertex> frontier = new LinkedList<Vertex>();
	public static void travel(Map<Integer, Integer> visited)
	{
		if (frontier.size() == 0) return;
		
		while (frontier.size() > 0)
		{ 
			Vertex s = frontier.pop();
			if (s == null) continue;
			int ttl = visited.get(s.id);		
			if (ttl == 0) continue;
			
			
			for (int i = 0 ; i < s.adjs.size(); i++)
			{
				Vertex next = s.adjs.get(i);
				if (visited.containsKey(next.id)) continue;
				
				frontier.add(next);
				visited.put(next.id, ttl - 1);
			}		
		}
	}	
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader fin = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter fout = new PrintWriter(System.out);		
		int testID = 1;
				
		while (true)
		{
			int nc = Integer.parseInt(fin.readLine());
			//fout.println(nc);
			Map<Integer, Vertex> graph = new HashMap<Integer, Vertex>();
			if (nc == 0)
				break;
			ArrayList<Integer> inputs = new ArrayList<Integer>();
			while(true)
			{
				List<Integer> lint = readInts(fin.readLine());
				if(lint.size() == 0)
					break;
				inputs.addAll(lint);
			}
			
			for (int i = 0; i < nc; i++)
			{
				int id1 = inputs.get(i*2);
				int id2 = inputs.get(i*2 + 1);
				Vertex v1 = null;
				Vertex v2 = null;
				if (graph.containsKey(id1))
					v1 = graph.get(id1);
				else
				{
					v1 = new Vertex(id1);
					graph.put(id1, v1);
				}
				
				if (graph.containsKey(id2))
					v2 = graph.get(id2);
				else
				{
					v2 = new Vertex(id2);
					graph.put(id2, v2);
				}	
				v1.adjs.add(v2);
				v2.adjs.add(v1);
			}
			
			
			for (int i = nc; i < inputs.size(); i++)
			{
				int start = inputs.get(i*2);
				int ttl = inputs.get(i*2+1);
				//fout.printf("s %d ttl %d\n", start, ttl);
				if (start == 0 && ttl == 0)
					break;
				Map<Integer, Integer> visited = new HashMap<Integer, Integer>();				
				//mark reachable node
				if (graph.containsKey(start))
				{
					frontier.add(graph.get(start));
					visited.put(start, ttl);
					travel(visited);
				}
				//cal unreachable
				fout.printf("Case %d: %d nodes not reachable from node %d with TTL = %d.\n",
							testID++, graph.size() - visited.size(), start, ttl);
				
				//fout.println("size g " + graph.size());
				//fout.println("size v " + visited.size());				
			}
			fout.flush();
			
		}
		
		//fout.println();
		fout.close(); //also flush content to stream
					  // if missing this line there is no printed output 
		System.exit(0);
	}
}		