package dataStructure;

import utils.Point3D;

public class nodeData implements node_data {
	 
	int key; 
	Point3D location;
	double Weight;
	String info;
	int tag;

	@Override
	public int getKey() 
	{
		return this.key;
	}

	@Override
	public Point3D getLocation() 
	{
		return this.location;
	}

	@Override
	public void setLocation(Point3D p) 
	{
		this.location = p;
	}

	@Override
	public double getWeight() 
	{
		return this.Weight;
	}

	@Override
	public void setWeight(double w) 
	{
		this.Weight = w;
	}

	@Override
	public String getInfo() 
	{
		return this.info;
	}

	@Override
	public void setInfo(String s) 
	{
		this.info =s;
	}

	@Override
	public int getTag() 
	{
		return this.tag;
	}

	@Override
	public void setTag(int t) 
	{
		this.tag=t;
	}

}
