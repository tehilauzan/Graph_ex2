package algorithms;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.nodeData;
import dataStructure.node_data;
	
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
*/
public class Graph_Algo implements graph_algorithms{

	private DGraph graph;
	
	public Graph_Algo() 
	{
		this.graph = new DGraph();
	}
	@Override
	public void init(graph g) 
	{
		this.graph = (DGraph)g;
	}

	@Override
	public void init(String file_name) 
	{
		
	}

	@Override
	public void save(String file_name) 
	{
			
	}

	@Override
	public boolean isConnected() 
	{
		return false;
	}

	@Override
	public double shortestPathDist(int src, int dest) 
	{
		vertexToInfinity();
		//init the src to be zero weight;
		graph.getNode(src).setWeight(0);
		graph.getNode(src).setTag(1);
		nodeData srcTemp = (nodeData) graph.getNode(src);
		nodeData destTemp = (nodeData) graph.getNode(dest);
		helpShortest(srcTemp,destTemp);
		return graph.getNode(dest).getWeight();
	}
	
	public void helpShortest (node_data src, node_data dest) 
	{
       	if (src.getKey()== dest.getKey()|| src.getTag()==1) 
       	{
       		return;
       	}
		double weightEdge,weightDestNode,weightSrcNode,sumEdgeAndSrc;	
		if (graph.getE(src.getKey())!=null)
		{
		for(edge_data currSpcEntry:graph.getE(src.getKey()))
    		{
       			weightEdge = currSpcEntry.getWeight();
       			weightDestNode = graph.getNode(currSpcEntry.getDest()).getWeight();
       			weightSrcNode = src.getWeight();
       			sumEdgeAndSrc = weightEdge+weightSrcNode;
       			if (sumEdgeAndSrc<weightDestNode) 
       			{
       				graph.getNode(currSpcEntry.getDest()).setWeight(sumEdgeAndSrc);
       				src.setTag(1);
       				helpShortest(this.graph.getNode(currSpcEntry.getDest()), dest);
       			}
    		}
		}
	} 

	//add by us
	public void vertexToInfinity() 
	{
		Iterator<node_data> iter = this.graph.getV().iterator();
		while (iter.hasNext()) 
		{
			node_data currentNode = iter.next();
			currentNode.setWeight(Double.POSITIVE_INFINITY);
			currentNode.setTag(0);
		}
		
	}
	@Override
	public List<node_data> shortestPath(int src, int dest) 
	{
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) 
	{
		return null;
	}
		
	@Override
	public graph copy() 
	{
		return null;
	}
		
}
		
		
