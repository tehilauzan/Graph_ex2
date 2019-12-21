package dataStructure;

import utils.Point3D;

public class nodeData implements node_data {
	 
	int key; 
	Point3D location;
	double Weight;
	String info;
	int tag;
	
	public nodeData(nodeData node) 
	{
		this.key = node.key;
		this.location = new Point3D (node.location);
		this.Weight = node.Weight;
		this.info = node.info;
		this.tag = node.tag;
	}
	public nodeData (int key,double Weight) 
	{
		this.key= key;
		this.Weight = Weight;
	}

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
		this.location = new Point3D (p);
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
