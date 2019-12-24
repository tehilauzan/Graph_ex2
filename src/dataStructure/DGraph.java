package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Node;

public class DGraph implements graph
{
	Hashtable<Integer,node_data> node;
	Hashtable<Integer , Hashtable <Integer,edge_data>> edge;
	int mc;
	int edgeSize;

	public DGraph() 
	{
		node = new Hashtable<Integer,node_data>();
		edge = new Hashtable<Integer , Hashtable <Integer,edge_data>>();
		mc=0;
		edgeSize=0;
	}
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
		mc++;
	
	}

	@Override
	public void connect(int src, int dest, double w) throws Exception 
	{
		if (getNode(src)!=null && getNode(dest)!=null) 
		{
			edgeData tempEdge = new edgeData (src,dest,w);
			if (edge.get(src)==null)
			{
				Hashtable<Integer, edge_data> temp = new Hashtable<Integer,edge_data>();
				temp.put(dest,tempEdge);
				edge.put(src, temp);
			}
			else 
			{
				if (edge.get(src).get(dest)!=null) 
				{
					throw new Exception ("the edge alredy exist");
				}
				edge.get(src).put(dest,tempEdge);
			}
		mc ++; //to check
		edgeSize++;
		}
	}

	@Override
	public Collection<node_data> getV() 
	{
		return node.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) 
	{
		return edge.get(node_id).values();
	}

	@Override
	public node_data removeNode(int key) 
	{
		node_data removeNode = getNode(key);
		if (removeNode!=null) 
		{
			node.remove(key);
			Iterator it = this.edge.entrySet().iterator();
			//remove all edges with key as dest
			while(it.hasNext()) 
			{
				Map.Entry cureentEdge = (Entry) it.next();
				int keySrc = (int) cureentEdge.getKey();
				if(edge.get(keySrc).get(key)!=null) 
				{
					removeEdge(keySrc, key);
				}
			}
			//remove all edges with key as src
			if (edge.get(key)!=null) 
			{
				edge.remove(key);
			}
		}
		return removeNode;
	}

	@Override
	public edge_data removeEdge(int src, int dest) 
	{
		edge_data removeEdge = getEdge(src, dest);
		if (removeEdge !=null) 
		{
			edge.get(src).remove(dest);
			edgeSize--;
			mc++;
			if (edge.get(src).values().isEmpty()) 
			{
				edge.remove(src);
			}
		}
		return removeEdge;
	}

	@Override
	public int nodeSize() 
	{
		return node.size();
	}

	@Override
	public int edgeSize() 
	{
		return edgeSize;
	}

	@Override
	public int getMC() 
	{
		return mc;
	}
	
	public Hashtable getNodeHash()
	{
		return this.node;
	}

	public Hashtable getEdgeHash()
	{
		return this.edge;
	}

}
