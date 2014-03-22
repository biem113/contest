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
		
		while (nTest-- > 0)
		{
			ArrayList<Integer> list = readInts(fin.readLine());
			int flen = list.get(0) * 100;
			int ncar = list.get(1);
			
			LinkedList<Car> lcars = new LinkedList<Car>();
			LinkedList<Car> rcars = new LinkedList<Car>();
			//get cars
			for (int i = 0; i < ncar; i++)
			{
				StringTokenizer st = new StringTokenizer(fin.readLine());
				Car ct = new Car();
				ct.len = Integer.parseInt(st.nextToken());
				ct.isLeft = st.nextToken().trim().equals("left");
				if (ct.isLeft)
					lcars.add(ct);
				else
					rcars.add(ct);
			}
			
			int ncross = 0;
			boolean ferrLeft = true;
			while (lcars.size() > 0 || rcars.size() > 0)
			{
				int clen = 0;
				boolean isLoaded = true;
				LinkedList<Car> cars = ferrLeft ? lcars : rcars;
				Car ct = cars.peek();
				if (cars.size() <= 0)
				{
					ferrLeft = !ferrLeft;
					ncross++;
					continue;
				}
				
				while(cars.size() > 0)	//load car			
				{
					Car c = cars.peek();
					if ((c.len + clen) > flen || c.isLeft != ferrLeft)
					{
						if (c.len > flen)
						{	
							cars.clear();
							isLoaded = false;
						}						
						break;
					}
					clen += c.len;
					cars.pop();
				}
				
				if (!isLoaded)
					continue;
				
				// cross river
				ferrLeft = !ferrLeft;
				ncross++;
			}
			fout.println(ncross++);			
		}
		
		//fout.println();
		fout.close(); //also flush content to stream
					  // if missing this line there is no printed output 
		System.exit(0);
	}
}		