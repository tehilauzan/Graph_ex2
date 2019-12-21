package dataStructure;

public class edgeData implements edge_data {

	int src;
	int dest;
	double weight;
	String info;
	int tag;
	
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
