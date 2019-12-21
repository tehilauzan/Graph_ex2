package dataStructure;

public class edgeData implements edge_data {

	int src;
	int dest;
	double weight;
	String info;
	int tag;
	
	public edgeData (int src,int dest,double weight) 
	{
		this.src = src;
		this.dest = dest;
		this.weight = weight;
		this.info = "";
		this.tag = 0;
	}
	
	public int getSrc() 
	{
		return this.src;
	}

	@Override
	public int getDest() 
	{
		return this.dest;
	}

	@Override
	public double getWeight() 
	{
		return this.weight;
	}

	@Override
	public String getInfo() 
	{
		return this.info;
	}

	@Override
	public void setInfo(String s) 
	{
		this.info = s;
	}

	@Override
	public int getTag() 
	{
		return tag;
	}

	@Override
	public void setTag(int t) 
	{
		this.tag = t;
	}

}
