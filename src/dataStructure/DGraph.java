package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import org.w3c.dom.Node;

public class DGraph implements graph
{
	Hashtable<Integer,node_data> node = new Hashtable<Integer,node_data>();
	Hashtable<Integer , Hashtable <Integer,edge_data>> edge = new Hashtable<Integer , Hashtable <Integer,edge_data>>();

	@Override
	public node_data getNode(int key) 
	{
		return node.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) 
	{
		return edge.get(src).get(dest);
	}

	@Override
	public void addNode(node_data n) 
	{
		node.put(n.getKey(), n);
	
	}

	@Override
	public void connect(int src, int dest, double w) 
	{
		
	}

	@Override
	public Collection<node_data> getV() 
	{
		return null;
	}

	@Override
	public Collection<edge_data> getE(int node_id) 
	{
		return null;
	}

	@Override
	public node_data removeNode(int key) 
	{
		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) 
	{
		return null;
	}

	@Override
	public int nodeSize() 
	{
		return 0;
	}

	@Override
	public int edgeSize() 
	{
		return 0;
	}

	@Override
	public int getMC() 
	{
		return 0;
	}

}
